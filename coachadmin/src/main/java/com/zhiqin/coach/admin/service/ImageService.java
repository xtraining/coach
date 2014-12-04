package com.zhiqin.coach.admin.service;


import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.api.auth.AuthException;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchTagImageDTO;
import com.zhiqin.coach.admin.dto.TagArrayDTO;
import com.zhiqin.coach.admin.dto.TagDTO;
import com.zhiqin.coach.admin.dto.TagImageDTO;

public interface ImageService{

	Long getTagImageTotalNum(SearchTagImageDTO searchDto);

	List<TagImageDTO> getTagImageList(SearchTagImageDTO searchDto,
			PageInfoDTO pageInfo);

	void createFiles(TagArrayDTO items, MultipartFile[] imageFile) throws AuthException, JSONException, IOException;

	void deleteByIds(String ids);

	List<TagDTO> getTagByImageId(long imageId);

	void saveAssignTag(TagArrayDTO items, Long imageId);



}
