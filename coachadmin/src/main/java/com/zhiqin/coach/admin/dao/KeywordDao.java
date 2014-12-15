package com.zhiqin.coach.admin.dao;



import java.util.List;

import com.zhiqin.coach.admin.dto.ArtifactDTO;
import com.zhiqin.coach.admin.dto.KeywordDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;


public interface KeywordDao extends BaseDao{

	Long getKeywordTotalNum(String name);

	List<KeywordDTO> getKeywordList(String name, PageInfoDTO pageInfo);

	void create(KeywordDTO dto);

	KeywordDTO getById(Long keywordId);

	Long getStoryTotalNumByKeyword(String name);

	List<ArtifactDTO> getStoryListByKeyword(String name, PageInfoDTO pageInfo);

	void deleteOrder(int type, long objectId, long keywordId);

	void insertOrder(int type, long objectId, long keywordId, int keywordOrder);

}
