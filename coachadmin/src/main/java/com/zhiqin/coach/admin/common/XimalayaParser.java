package com.zhiqin.coach.admin.common;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.common.Constants.DOWNLOAD_SOURCE_FROM;
import com.zhiqin.coach.admin.common.Constants.DOWNLOAD_TASK_STATUS;
import com.zhiqin.coach.admin.common.Constants.DOWNLOAD_TASK_TYPE;
import com.zhiqin.coach.admin.common.Constants.TASK_STATUS;
import com.zhiqin.coach.admin.dto.DownloadTaskDTO;
import com.zhiqin.coach.admin.dto.TaskDTO;

public class XimalayaParser extends MyHtmlParser {
	private static final Logger log = LoggerFactory.getLogger(XimalayaParser.class);
	private static final String DOWNLOAD_URL = "http://www.ximalaya.com/album/{id}.xml";

	@Override
	public TaskDTO getTask(String id) {
		TaskDTO dto = new TaskDTO();
		dto.setSourceFrom(DOWNLOAD_SOURCE_FROM.XIMALAYA.getValue());
		dto.setTaskNum(0);
		dto.setStatus(TASK_STATUS.DRAFT.getValue());
		String htmlUrl = StringUtils.replace(DOWNLOAD_URL, "{id}", id);
		dto.setUrl(htmlUrl);
		dto.setDescription(DESC.INIT.getValue());
		return dto;
	}
	
	@SuppressWarnings("unused")
	@Override
	public List<DownloadTaskDTO> getDownloadTask(TaskDTO task) {
		List<DownloadTaskDTO> list = new ArrayList<DownloadTaskDTO>();
		SAXReader saxReader = new SAXReader();
        Document document = null;
		try {
			document = saxReader.read(new URL(task.getUrl()));
		} catch (Throwable e1) {
			log.error("getDownloadTask error" + task.getUrl(), e1);
			return list;
		}
        // 获取根元素
        Element root = document.getRootElement();
        Element channelElement = root.element("channel");
        Element titleElement = channelElement.element("title");
        DownloadTaskDTO coverDto = new DownloadTaskDTO();
        coverDto.setStatus(DOWNLOAD_TASK_STATUS.SUCCESS.getValue());
        coverDto.setType(DOWNLOAD_TASK_TYPE.COVER.getValue());
        coverDto.setTaskId(task.getId());
        coverDto.setTitle(StringUtils.trim(titleElement.getText()));
        coverDto.setSize(0);
        coverDto.setDuration(0);
        Element imageElement = channelElement.element("image"); 
        coverDto.setImageUrl(StringUtils.trim(imageElement.attributeValue("href")));
        list.add(coverDto);
        
        @SuppressWarnings("unchecked")
		List<Element> itemList = channelElement.elements("item");
        for(Element item : itemList){
        	 DownloadTaskDTO artifactDto = new DownloadTaskDTO();
        	 artifactDto.setStatus(DOWNLOAD_TASK_STATUS.DRAFT.getValue());
        	 artifactDto.setType(DOWNLOAD_TASK_TYPE.ARTIFACT.getValue());
        	 artifactDto.setTaskId(task.getId());
             
             Element itemTitleElement = item.element("title");
             artifactDto.setTitle(StringUtils.trim(itemTitleElement.getText()));
             
             imageElement = item.element("image"); 
             artifactDto.setImageUrl(StringUtils.trim(imageElement.attributeValue("href")));
             
             Element fileElement = item.element("guid"); 
             artifactDto.setFileUrl(StringUtils.trim(fileElement.getText()));
             try{
            	 Double size = Double.valueOf(StringUtils.trim(fileElement.attributeValue("length")));
            	 artifactDto.setSize(size.intValue());
             } catch(Throwable e){
             }
             
             Element durationElement = item.element("duration"); 
             try{
            	 String str = StringUtils.trim(durationElement.getText());
            	 String dr[] = StringUtils.split(str, ":");
            	 Double duration = 0D;
            	 if(dr != null){
            		 if(dr.length == 1){
            			 duration = Double.valueOf(dr[0]);
            		 } else if(dr.length == 2){
            			 duration = Double.valueOf(dr[0])*60 + Double.valueOf(dr[1]);
            		 } else if(dr.length == 3){
            			 duration = Double.valueOf(dr[0])*3600 + Double.valueOf(dr[1])*60 + Double.valueOf(dr[2]);
            		 }
            	 }
            	 artifactDto.setDuration(duration.intValue());
             } catch(Throwable e){
             }
             
             list.add(artifactDto);
        }
        
		return list;
	}

}
