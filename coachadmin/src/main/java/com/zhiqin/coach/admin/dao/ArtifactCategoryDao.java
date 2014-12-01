package com.zhiqin.coach.admin.dao;

import java.util.List;

import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchArtifactDTO;





public interface ArtifactCategoryDao extends BaseDao{

	void save(Long artifactId, Long categoryId, int artifactCategoryOrder);

	void delete(int categoryId, String artifactIds);

}
