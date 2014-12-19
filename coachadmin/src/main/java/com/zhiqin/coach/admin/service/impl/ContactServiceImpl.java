package com.zhiqin.coach.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.zhiqin.coach.admin.dao.AreaDao;
import com.zhiqin.coach.admin.dao.ContactDao;
import com.zhiqin.coach.admin.dto.ContactDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchContactDTO;
import com.zhiqin.coach.admin.service.ContactService;
import com.zhiqin.coach.admin.util.HttpUtil;
import com.zhiqin.coach.admin.util.JsonBinder;

@Service
public class ContactServiceImpl implements ContactService {
	private static Log log = LogFactory.getLog(ContactServiceImpl.class);
	private static final String URL = "http://v.showji.com/locating/showji.com1118.aspx?m={phoneNumber}&output=json";
	@Resource private AreaDao areaDao;
	@Resource private ContactDao contactDao;

	@Override
	public void create(String phoneNumbers) {
		String[] phoneNumberArr = StringUtils.split(phoneNumbers, ",");
		for(String phoneNumber : phoneNumberArr){
			String phone = StringUtils.trimToEmpty(phoneNumber);
			if(StringUtils.isNotBlank(phone) && checkMobileNumber(phone)){
				Long contactId = contactDao.getByPhoneNumber(phone);
				if(contactId == null || contactId == 0){
					ContactDTO contact = new ContactDTO();
					contact.setPhoneNumber(phone);
					contact.setStatus(0);
					try{
						String response = HttpUtil.getServer(StringUtils.replace(URL, "{phoneNumber}", phone), "");
						String areaCode = (String)JsonBinder.buildNormalBinder().getValue(response, "AreaCode");
						Integer areaId = areaDao.getByAreaCode(areaCode);
						String spName = (String)JsonBinder.buildNormalBinder().getValue(response, "Corp");
						contact.setAreaId(areaId);
						contact.setSpName(spName);
					}catch(Throwable e){
						log.error(e);
					}
					contactDao.save(contact);
				}
			}
		}
		
	}

	private static boolean checkMobileNumber(String mobileNumber){
	  try{
	    Long.valueOf(mobileNumber);
	    return true;
	   }catch(Exception e){
	   }
	  	return false;
	 }

	@Override
	public Long getTotalNum(SearchContactDTO searchDto) {
		return contactDao.getTotalNum(searchDto);
	}

	@Override
	public List<ContactDTO> getContactList(SearchContactDTO searchDto,
			PageInfoDTO pageInfo) {
		return contactDao.getContactList(searchDto, pageInfo);
	}

}
