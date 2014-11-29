package com.zhiqin.coach.admin.dao;

import com.zhiqin.coach.admin.common.Constants.IMAGE_FROM;
import com.zhiqin.coach.admin.common.Constants.IMAGE_TYPE;






public interface ArtifactImageDao extends BaseDao{

	void insertFromDownload(Long objectId, String fileName, IMAGE_FROM type, IMAGE_TYPE detail);

}
