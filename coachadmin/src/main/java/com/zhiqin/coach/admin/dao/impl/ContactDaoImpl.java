package com.zhiqin.coach.admin.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhiqin.coach.admin.dao.ContactDao;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalNum(SearchContactDTO searchDto) {
		// TODO Auto-generated method stub
		return null;
	}


}
