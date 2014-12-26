package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.ContactDao;
import com.zhiqin.coach.admin.dto.AreaDTO;
import com.zhiqin.coach.admin.dto.ContactDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchContactDTO;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ContactDaoImpl extends BaseDaoImpl implements ContactDao
{
	private static final Logger log = LoggerFactory
			.getLogger(ContactDaoImpl.class);

	@Override
	public void save(ContactDTO contact) {
		try{
			this.getSqlSession().insert("contact.save", contact);
		} catch(RuntimeException e){
			log.error("save", e);
			throw e;
		}
	}

	@Override
	public Long getByPhoneNumber(String phone) {
		try{
			return this.getSqlSession().selectOne("contact.getByPhoneNumber", phone);
		} catch(RuntimeException e){
			log.error("getByPhoneNumber", e);
			throw e;
		}
	}

	@Override
	public List<ContactDTO> getContactList(SearchContactDTO searchDto,
			PageInfoDTO pageInfo) {
		try{
			Map map = new HashMap();
			map.put("searchDto", searchDto);
			map.put("p", pageInfo);
			return this.getSqlSession().selectList("contact.getContactList", map);
		} catch(RuntimeException e){
			log.error("getContactList", e);
			throw e;
		}
	}

	@Override
	public Long getTotalNum(SearchContactDTO searchDto) {
		try{
			Map map = new HashMap();
			map.put("searchDto", searchDto);
			return this.getSqlSession().selectOne("contact.getContactTotalNum", map);
		} catch(RuntimeException e){
			log.error("getTotalNum", e);
			throw e;
		}
	}

	@Override
	public List<AreaDTO> getExistingProvinceList() {
		try{
			return this.getSqlSession().selectList("contact.getExistingProvinceList");
		} catch(RuntimeException e){
			log.error("getExistingProvinceList", e);
			throw e;
		}
	}

	@Override
	public List<String> getSpNameList() {
		try{
			return this.getSqlSession().selectList("contact.getSpNameList");
		} catch(RuntimeException e){
			log.error("getSpNameList", e);
			throw e;
		}
	}

	@Override
	public List<Long> getContactIdList(SearchContactDTO dto) {
		try{
			Map map = new HashMap();
			map.put("searchDto", dto);
			return this.getSqlSession().selectList("contact.getContactIdList", map);
		} catch(RuntimeException e){
			log.error("getContactIdList", e);
			throw e;
		}
	}

	@Override
	public void updateRepeatTimes(Long contactId, String description) {
		try{
			Map map = new HashMap();
			map.put("contactId", contactId);
			map.put("description", "; "+description);
			this.getSqlSession().update("contact.updateRepeatTimes", map);
		} catch(RuntimeException e){
			log.error("updateRepeatTimes", e);
			throw e;
		}
		
	}

	@Override
	public List<String> getBySendSubtaskId(Long subtaskId) {
		try{
			return this.getSqlSession().selectList("contact.getBySendSubtaskId", subtaskId);
		} catch(RuntimeException e){
			log.error("getBySendSubtaskId", e);
			throw e;
		}
	}

	@Override
	public List<ContactDTO> getContactListWithoutAreaId(int maxNum) {
		try{
			return this.getSqlSession().selectList("contact.getContactListWithoutAreaId", maxNum);
		} catch(RuntimeException e){
			log.error("getContactListWithoutAreaId", e);
			throw e;
		}
	}

	@Override
	public void updateArea(ContactDTO dto) {
		try{
			this.getSqlSession().update("contact.updateArea", dto);
		} catch(RuntimeException e){
			log.error("updateArea", e);
			throw e;
		}
		
	}

	@Override
	public List<String> getTagNameList() {
		try{
			return this.getSqlSession().selectList("contact.getTagNameList");
		} catch(RuntimeException e){
			log.error("getTagNameList", e);
			throw e;
		}
	}


}
