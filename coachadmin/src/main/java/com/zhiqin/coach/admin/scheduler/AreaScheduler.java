/**
 * @author Zhang Zhipeng
 *
 * 2013-12-22
 */
package com.zhiqin.coach.admin.scheduler;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zhiqin.coach.admin.common.Constants.DOWNLOAD_TASK_STATUS;
import com.zhiqin.coach.admin.common.Constants.FILE_NAME_PREFIX;
import com.zhiqin.coach.admin.dto.ContactDTO;
import com.zhiqin.coach.admin.dto.DownloadTaskDTO;
import com.zhiqin.coach.admin.service.AreaService;
import com.zhiqin.coach.admin.service.ContactService;
import com.zhiqin.coach.admin.util.Config;
import com.zhiqin.coach.admin.util.DownloadUtils;
import com.zhiqin.coach.admin.util.HttpUtil;
import com.zhiqin.coach.admin.util.JsonBinder;

/**
 * @author Lenovo
 * 
 */
@Component
public class AreaScheduler {
	private static final Logger log = LoggerFactory.getLogger(AreaScheduler.class);
	private static final int MAX_NUM = 60;
	private static final String URL = "http://v.showji.com/locating/showji.com1118.aspx?m={phoneNumber}&output=json";
	
	@Autowired
	private ContactService contactService;
	@Autowired
	private AreaService areaService;
	@Scheduled(fixedRate = 60000)
	void getArea() {
		if(StringUtils.equals(Config.getProperty("area_schedule_switch"), "1")){
			log.info("获取地区");
			List<ContactDTO> contactList = contactService.getContactListWithoutAreaId(MAX_NUM);
			if(contactList.size() > 0){
			for(ContactDTO dto : contactList){
				try{
					String response = HttpUtil.getServer(StringUtils.replace(URL, "{phoneNumber}", dto.getPhoneNumber()), "");
					String areaCode = (String)JsonBinder.buildNormalBinder().getValue(response, "AreaCode");
					if(StringUtils.isNotBlank(areaCode)){
						Integer areaId = areaService.getByAreaCode(areaCode);
						String spName = (String)JsonBinder.buildNormalBinder().getValue(response, "Corp");
						dto.setAreaId(areaId);
						dto.setSpName(spName);
						contactService.updateArea(dto);
					}
				}catch(Throwable e){
					log.error("获取地区错误", e);
				}
			}
			}
		}
	}
}
