package com.zhiqin.coach.admin.service;


import java.util.List;

import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.TagDTO;

public interface TagService{

	Long getTagTotalNum(String name);

	List<TagDTO> getTagList(String name, PageInfoDTO pageInfo);

	void create(TagDTO dto);

	void deleteByIds(String ids);

	TagDTO getById(int tagId);

	void update(TagDTO dto);


}
