package com.zhiqin.coach.admin.service;


import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.api.auth.AuthException;
import com.zhiqin.coach.admin.dto.ArtifactArrayDTO;
import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.TagArrayDTO;
import com.zhiqin.coach.admin.dto.TopDTO;

public interface TopService{

	void create(TopDTO dto, ArtifactArrayDTO artifacts, MultipartFile imageFile, MultipartFile detailImageFile) throws IOException, AuthException, JSONException;

	Long getTopTotalNum(String name);

	List<TopDTO> getTopList(String name, PageInfoDTO pageInfo);

	void deleteByIds(String ids);

	TopDTO getById(int topId);

	List<ArtifactDTO> getArtifactByTopId(int topId);

	void update(TopDTO dto, ArtifactArrayDTO artifacts,
			MultipartFile listImageFile, MultipartFile detailImageFile) throws AuthException, JSONException, IOException;

}
