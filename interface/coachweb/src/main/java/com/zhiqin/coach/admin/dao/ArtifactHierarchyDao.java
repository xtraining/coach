package com.zhiqin.coach.admin.dao;







public interface ArtifactHierarchyDao extends BaseDao{

	void insertFromDownload(Long artifactId, Integer taskId);

	void deleteByArtifactIds(String ids);


}
