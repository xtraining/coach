package com.zhiqin.coach.admin.dao;

import java.util.List;

import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchTagImageDTO;
import com.zhiqin.coach.admin.dto.TagDTO;
import com.zhiqin.coach.admin.dto.TagImageDTO;






public interface TagImageDao extends BaseDao{

	void insert(Long tagId, Long imageId);

	Long getTagImageTotalNum(SearchTagImageDTO searchDto);

	List<TagImageDTO> getTagImage(SearchTagImageDTO searchDto,
			PageInfoDTO pageInfo);

	List<String> getTagNameListByImageId(Long id);

	List<TagDTO> getTagByImageId(long imageId);

	void deleteByImageId(Long imageId);

}
