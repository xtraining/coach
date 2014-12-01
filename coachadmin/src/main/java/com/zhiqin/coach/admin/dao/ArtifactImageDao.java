package com.zhiqin.coach.admin.dao;

import java.util.List;

import com.zhiqin.coach.admin.common.Constants.IMAGE_FROM;
import com.zhiqin.coach.admin.common.Constants.IMAGE_STYLE;
import com.zhiqin.coach.admin.dto.ArtifactImageDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchTagImageDTO;
import com.zhiqin.coach.admin.dto.TagDTO;






public interface ArtifactImageDao extends BaseDao{

	void insert(Long artifactId, Long imageId, IMAGE_FROM imageFrom,
			IMAGE_STYLE imageStyle);

	Long getTagImageTotalNum(SearchTagImageDTO searchDto);

	List<ArtifactImageDTO> getTagImage(SearchTagImageDTO searchDto,
			PageInfoDTO pageInfo);

	List<String> getTagNameListByImageId(Long id);

	List<TagDTO> getTagByImageId(long imageId);

	void deleteByImageId(Long imageId, IMAGE_FROM imageFrom);

}
