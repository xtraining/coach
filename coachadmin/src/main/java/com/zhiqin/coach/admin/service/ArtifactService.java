package com.zhiqin.coach.admin.service;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.api.auth.AuthException;
import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.CategoryArrayDTO;
import com.zhiqin.coach.admin.dto.CategoryDTO;
import com.zhiqin.coach.admin.dto.DownloadTaskDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchArtifactDTO;
import com.zhiqin.coach.admin.dto.TagArrayDTO;
import com.zhiqin.coach.admin.dto.TagDTO;

public interface ArtifactService{

	Long getArtifactTotalNum(SearchArtifactDTO searchDto);

	List<ArtifactDTO> getArtifactList(SearchArtifactDTO searchDto,
			PageInfoDTO pageInfo);

	void create(ArtifactDTO dto, MultipartFile listImageFile, MultipartFile mediaFile) throws IOException, AuthException, JSONException;

	void saveAssign(String artifactIds, int tagId);

	void addNewStoryFromDownload(DownloadTaskDTO dto, String imageFileName, File imageFilePath,
			String voiceFileName, File voiceFilePath) throws JSONException, AuthException;

	void deleteByIds(String ids);

	ArtifactDTO getById(long artifactId);

	List<TagDTO> getTagByArtifactId(long artifactId);

	List<CategoryDTO> getCategoryByArtifactId(long artifactId);

	List<ArtifactDTO> getSublistById(long artifactId);

	void update(ArtifactDTO dto, 
			MultipartFile listImageFile, MultipartFile mediaFile) throws IOException, AuthException, JSONException;

	void saveCategory(String artifactIds, int categoryId);


}
