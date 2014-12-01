package com.zhiqin.coach.admin.dao;

import java.util.List;

import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchArtifactDTO;
import com.zhiqin.coach.admin.dto.TagDTO;





public interface ArtifactTagDao extends BaseDao{

	void save(Long artifactId, Long tagId, int artifactTagOrder);

	void delete(int tagId, String artifactIds);

	List<TagDTO> getByArtifactId(long artifactId);

	void deleteByArtifactId(Long id);

}
