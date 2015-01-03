package com.zhiqin.coach.admin.dao;

import java.util.List;

import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchArtifactDTO;
import com.zhiqin.coach.admin.dto.TagImageDTO;





public interface ArtifactDao extends BaseDao{

	Long getArtifactTotalNum(SearchArtifactDTO searchDto);

	List<ArtifactDTO> getArtifactList(SearchArtifactDTO searchDto,
			PageInfoDTO pageInfo);

	void insert(ArtifactDTO dto);

	void insertFromDownload(ArtifactDTO story);


	void deleteByIds(String ids);

	void updateFileName(ArtifactDTO image);

	ArtifactDTO getById(long artifactId);

	List<ArtifactDTO> getSubListByArtifactId(long artifactId);

	void updateArtifact(ArtifactDTO dto);

	void deleteByDownloadTaskId(Long id);

	void updateStatus(ArtifactDTO dto, int value);

	void hideByIds(String ids);

}
