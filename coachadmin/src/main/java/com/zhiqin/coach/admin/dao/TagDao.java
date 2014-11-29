package com.zhiqin.coach.admin.dao;



import java.util.List;

import com.zhiqin.coach.admin.dto.CoachDTO;
import com.zhiqin.coach.admin.dto.CourseDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchCoachDTO;
import com.zhiqin.coach.admin.dto.SearchCourseDTO;
import com.zhiqin.coach.admin.dto.TagDTO;


public interface TagDao extends BaseDao{

	Long getTagTotalNum(String name);

	List<TagDTO> getTagList(String name, PageInfoDTO pageInfo);

	void create(TagDTO dto);

	void deleteByIds(String ids);

	TagDTO getById(int tagId);

	void update(TagDTO dto);


}
