package com.zhiqin.coach.admin.dao;

import java.util.List;

import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.CategoryDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchArtifactDTO;





public interface ArtifactCategoryDao extends BaseDao{

	void save(ArtifactDTO artifact);

	void delete(int categoryId, String artifactIds);

	List<CategoryDTO> getByArtifactId(long artifactId);

	void deleteByArtifactId(Long id);

	void delete(String artifactIds);

}
