package com.zhiqin.coach.admin.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.common.Constants.DOWNLOAD_SOURCE_FROM;
import com.zhiqin.coach.admin.common.Constants.DOWNLOAD_TASK_STATUS;
import com.zhiqin.coach.admin.common.Constants.DOWNLOAD_TASK_TYPE;
import com.zhiqin.coach.admin.common.Constants.TASK_STATUS;
import com.zhiqin.coach.admin.dto.DownloadTaskDTO;
import com.zhiqin.coach.admin.dto.TaskDTO;
import com.zhiqin.coach.admin.util.JsonBinder;

public class QingtingFmParser extends MyHtmlParser {
	private static final Logger log = LoggerFactory.getLogger(QingtingFmParser.class);
	private static final String DOWNLOAD_URL = " http://od.qingting.fm";

	@Override
	public TaskDTO getTask(String htmlUrl) {
		TaskDTO dto = new TaskDTO();
		dto.setSourceFrom(DOWNLOAD_SOURCE_FROM.QINGTINGFM.getValue());
		dto.setStatus(TASK_STATUS.ACTIVE.getValue());
		dto.setUrl(StringUtils.replace(htmlUrl, "#", "s"));
		return dto;
	}
	
	@Override
	public List<DownloadTaskDTO> getDownloadTask(TaskDTO task) {
		Document doc = null;
		try {
			doc = (Document) Jsoup.connect(task.getUrl()).get();
		} catch (IOException e1) {
			log.error("Parse Qingting FM url error error: " + task.getUrl(), e1);
			return null;
		}
		List<DownloadTaskDTO> list = new ArrayList<DownloadTaskDTO>();
        DownloadTaskDTO coverDto = new DownloadTaskDTO();
        coverDto.setStatus(DOWNLOAD_TASK_STATUS.DRAFT.getValue());
        coverDto.setType(DOWNLOAD_TASK_TYPE.COVER.getValue());
        coverDto.setTaskId(task.getId());
        Elements titleElement = doc.getElementsByClass("channel-name");
        try{
        	coverDto.setTitle(StringUtils.trim(titleElement.get(0).text()));
        } catch(Throwable e){
        	log.error("Qingting FM error: ", e);
        }
        coverDto.setSize(0);
        coverDto.setDuration(0);
        Elements coverElement = doc.getElementsByClass("cover");
        try{
        	String imageUrl = coverElement.get(0).child(0).attr("src");
        	coverDto.setImageUrl(StringUtils.trim(imageUrl));
        } catch(Throwable e){
        	log.error("Qingting FM error: ", e);
        }
        list.add(coverDto);
        
		Elements itemList = doc.getElementsByTag("li");
		JsonBinder binder = JsonBinder.buildNonDefaultBinder();
        for(Element item : itemList){
        	String dataPlayInfo = item.attr("data-play-info");
			
        	 DownloadTaskDTO artifactDto = new DownloadTaskDTO();
        	 artifactDto.setStatus(DOWNLOAD_TASK_STATUS.DRAFT.getValue());
        	 artifactDto.setType(DOWNLOAD_TASK_TYPE.ARTIFACT.getValue());
        	 artifactDto.setTaskId(task.getId());
             
        	 String name = (String) binder.getValue(dataPlayInfo, "name");
             artifactDto.setTitle(StringUtils.trim(name));
             
             String parentname = (String) binder.getValue(dataPlayInfo, "parentname");
             artifactDto.setSubtitle(StringUtils.trim(parentname));
             
             String thumb = (String) binder.getValue(dataPlayInfo, "thumb");
             artifactDto.setImageUrl(StringUtils.trim(thumb));
             
             @SuppressWarnings("unchecked")
			 List<String> urls = (List<String>) binder.getValue(dataPlayInfo, "urls");
             if(urls != null && urls.size() > 0){
            	 artifactDto.setFileUrl(StringUtils.trim(DOWNLOAD_URL + urls.get(0)));
             }
             artifactDto.setSize(0);
             
             try{
            	 Double duration = (Double) binder.getValue(dataPlayInfo, "duration");
            	 artifactDto.setDuration(duration.intValue());
             } catch(Throwable e){
             }
             
             list.add(artifactDto);
        }
        
		return list;
	}
	
	public static void main(String[] args) throws IOException {
		TaskDTO t = new TaskDTO();
		t.setUrl("http://www.qingting.fm/s/vchannels/63526/programs/3713919");
		QingtingFmParser p = new QingtingFmParser();
		List<DownloadTaskDTO> list = p.getDownloadTask(t);
		System.out.println(list);
	}

}
