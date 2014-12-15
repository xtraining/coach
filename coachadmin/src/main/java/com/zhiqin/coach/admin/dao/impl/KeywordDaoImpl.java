package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.KeywordDao;
import com.zhiqin.coach.admin.dto.KeywordDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchTagImageDTO;
import com.zhiqin.coach.admin.dto.TagDTO;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class KeywordDaoImpl extends BaseDaoImpl implements KeywordDao
{
	private static final Logger log = LoggerFactory
			.getLogger(KeywordDaoImpl.class);

	@Override
	public void create(KeywordDTO dto) {
		try{
			this.getSqlSession().insert("keyword.insert", dto);
		} catch(RuntimeException e){
			log.error("insert", e);
			throw e;
		}
		
	}

	@Override
	public Long getKeywordTotalNum(String name) {
		try{
			Map map = new HashMap();
			map.put("name", name);
			return this.getSqlSession().selectOne("keyword.getKeywordTotalNum", map);
		} catch(RuntimeException e){
			log.error("getTagImageTotalNum", e);
			throw e;
		}
	}

	@Override
	public List<KeywordDTO> getKeywordList(String name,
			PageInfoDTO pageInfo) {
		try{
			Map map = new HashMap();
			map.put("name", name);
			map.put("p", pageInfo);
			return this.getSqlSession().selectList("keyword.getKeywordList", map);
		} catch(RuntimeException e){
			log.error("getKeywordList", e);
			throw e;
		}
	}

	@Override
	public KeywordDTO getById(Long keywordId) {
		try{
			return this.getSqlSession().selectOne("keyword.getById", keywordId);
		} catch(RuntimeException e){
			log.error("getById", e);
			throw e;
		}
	}
}
