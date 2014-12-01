package com.zhiqin.coach.admin.dao;



import java.util.List;

import com.zhiqin.coach.admin.dto.CategoryDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchTagImageDTO;
import com.zhiqin.coach.admin.dto.TagDTO;
import com.zhiqin.coach.admin.dto.ArtifactImageDTO;


public interface ImageDao extends BaseDao{


	void insert(ArtifactImageDTO image);

	void updateFileName(ArtifactImageDTO image);

	void deleteByIds(String ids);
}
