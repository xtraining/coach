package com.zhiqin.coach.admin.service;


import java.io.File;
import java.util.List;

import org.json.JSONException;

import com.qiniu.api.auth.AuthException;
import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.DownloadTaskDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchArtifactDTO;

public interface ArtifactService{

	Long getArtifactTotalNum(SearchArtifactDTO searchDto);

	List<ArtifactDTO> getArtifactList(SearchArtifactDTO searchDto,
			PageInfoDTO pageInfo);

	void create(ArtifactDTO dto);

	void saveAssign(String artifactIds, int tagId);

	void addNewStoryFromDownload(DownloadTaskDTO dto, String imageFileName, File imageFilePath,
			String voiceFileName, File voiceFilePath) throws JSONException, AuthException;

	void deleteByIds(String ids);


}
