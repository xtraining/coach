package com.zhiqin.coach.admin.service;

import java.io.IOException;
import java.util.List;

import jxl.read.biff.BiffException;

import org.springframework.web.multipart.MultipartFile;

import com.zhiqin.coach.admin.dto.AreaDTO;
import com.zhiqin.coach.admin.dto.ContactDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchContactDTO;



public interface ContactService{

	void create(String tagName, String phoneNumbers);

	Long getTotalNum(SearchContactDTO searchDto);

	List<ContactDTO> getContactList(SearchContactDTO searchDto, PageInfoDTO pageInfo);

	List<AreaDTO> getProvinceList();

	List<String> getSpNameList();

	void importContact(String tagName, MultipartFile excelFile) throws BiffException, IOException;

	List<ContactDTO> getContactListWithoutAreaId(int maxNum);

	void updateArea(ContactDTO dto);

	List<String> getTagNameList();


}
