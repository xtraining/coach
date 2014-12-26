package com.zhiqin.coach.admin.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zhiqin.coach.admin.common.Constants.MEMBER_STATUS;
import com.zhiqin.coach.admin.common.Constants.MEMBER_TYPE;
import com.zhiqin.coach.admin.dao.AreaDao;
import com.zhiqin.coach.admin.dao.ContactDao;
import com.zhiqin.coach.admin.dto.AreaDTO;
import com.zhiqin.coach.admin.dto.ContactDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchContactDTO;
import com.zhiqin.coach.admin.service.ContactService;
import com.zhiqin.coach.admin.util.HttpUtil;
import com.zhiqin.coach.admin.util.JsonBinder;

@Service
public class ContactServiceImpl implements ContactService {
	private static Log log = LogFactory.getLog(ContactServiceImpl.class);

	@Resource private ContactDao contactDao;

	@Override
	public void create(String tagName, String phoneNumbers) {
		String[] phoneNumberArr = StringUtils.split(phoneNumbers, ",");
		for(String phoneNumber : phoneNumberArr){
			savePhoneNumber(tagName, phoneNumber, null);
		}
		
	}

	private void savePhoneNumber(String tagName, String phoneNumber, String description) {
		String phone = StringUtils.trimToEmpty(phoneNumber);
		if(StringUtils.isNotBlank(phone) && checkMobileNumber(phone)){
			Long contactId = contactDao.getByPhoneNumber(phone);
			if(contactId == null || contactId == 0){
				ContactDTO contact = new ContactDTO();
				contact.setPhoneNumber(phone);
				contact.setDescription(description);
				contact.setStatus(0);
				contact.setSpName("未知");
				contact.setTagName(StringUtils.trimToEmpty(tagName));
				contactDao.save(contact);
			} else {
				contactDao.updateRepeatTimes(contactId, description);
			}
		}
	}

	private static boolean checkMobileNumber(String mobileNumber){
	  try{
	    Long.valueOf(mobileNumber);
	    return true;
	   }catch(Exception e){
	   }
	  	return false;
	 }

	@Override
	public Long getTotalNum(SearchContactDTO searchDto) {
		return contactDao.getTotalNum(searchDto);
	}

	@Override
	public List<ContactDTO> getContactList(SearchContactDTO searchDto,
			PageInfoDTO pageInfo) {
		return contactDao.getContactList(searchDto, pageInfo);
	}

	@Override
	public List<AreaDTO> getProvinceList() {
		return contactDao.getExistingProvinceList();
	}

	@Override
	public List<String> getSpNameList() {
		return contactDao.getSpNameList();
	}

	@Override
	public void importContact(String tagName, MultipartFile excelFile) throws BiffException, IOException {
		Workbook book = Workbook.getWorkbook(excelFile.getInputStream());
		Sheet[] allSheet = book.getSheets();
		Sheet memberSheet = allSheet[0];
		int nSheetCount = memberSheet.getRows();	
        for (int i = 0; i < nSheetCount; i++) {
            // 获得一行的所有单元格
            Cell[] row = memberSheet.getRow(i);
            String phoneNumber = getString(row, 0);
            String description = getString(row, 1);
            savePhoneNumber(tagName, phoneNumber, description);
        }	
	}


	/**
	 * @param cell
	 * @return
	 */
	private String getString(Cell[] row, int i) {
		if(i < row.length){
			if(row[i] == null){
				return "";
			} else {
				if(row[i].getContents() == null){
					return "";
				} else {
					return row[i].getContents();
				}
			}
		} else {
			return "";
		}
	}

	@Override
	public List<ContactDTO> getContactListWithoutAreaId(int maxNum) {
		return contactDao.getContactListWithoutAreaId(maxNum);
	}

	@Override
	public void updateArea(ContactDTO dto) {
		contactDao.updateArea(dto);
		
	}

	@Override
	public List<String> getTagNameList() {
		return contactDao.getTagNameList();
	}


}
