package com.zhiqin.coach.admin.service;

import java.util.List;

import com.zhiqin.coach.admin.dto.ContactDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchContactDTO;



public interface ContactService{

	void create(String phoneNumbers);

	Long getTotalNum(SearchContactDTO searchDto);

	List<ContactDTO> getContactList(SearchContactDTO searchDto, PageInfoDTO pageInfo);


}
