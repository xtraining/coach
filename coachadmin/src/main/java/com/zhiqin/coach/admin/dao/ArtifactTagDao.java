package com.zhiqin.coach.admin.dao;

import java.util.List;

import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchArtifactDTO;





public interface ArtifactTagDao extends BaseDao{

	void save(String artifactId, int tagId);

	void delete(int tagId, String artifactIds);

}
