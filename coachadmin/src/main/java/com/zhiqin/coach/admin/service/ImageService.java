package com.zhiqin.coach.admin.service;


import java.util.List;

import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchTagImageDTO;
import com.zhiqin.coach.admin.dto.TagDTO;
import com.zhiqin.coach.admin.dto.TagImageDTO;

public interface ImageService{

	Long getTagImageTotalNum(SearchTagImageDTO searchDto);

	List<TagImageDTO> getTagImageList(SearchTagImageDTO searchDto,
			PageInfoDTO pageInfo);



}
