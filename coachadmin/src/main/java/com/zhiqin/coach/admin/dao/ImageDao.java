package com.zhiqin.coach.admin.dao;



import java.util.List;

import com.zhiqin.coach.admin.dto.CategoryDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchTagImageDTO;
import com.zhiqin.coach.admin.dto.TagImageDTO;


public interface ImageDao extends BaseDao{

	Long getTagImageTotalNum(SearchTagImageDTO searchDto);

	List<TagImageDTO> getTagImage(SearchTagImageDTO searchDto, PageInfoDTO pageInfo);

	List<String> getTagNameListByImageId(Long id);



}
