package com.zhiqin.coach.admin.dao;

import java.util.List;

import com.zhiqin.coach.admin.dto.AreaDTO;
import com.zhiqin.coach.admin.dto.ContactDTO;
import com.zhiqin.coach.admin.dto.PageInfoDTO;
import com.zhiqin.coach.admin.dto.SearchContactDTO;





public interface ContactDao extends BaseDao{

	void save(ContactDTO contact);

	Long getByPhoneNumber(String phone);

	List<ContactDTO> getContactList(SearchContactDTO searchDto,
			PageInfoDTO pageInfo);

	Long getTotalNum(SearchContactDTO searchDto);

	List<AreaDTO> getExistingProvinceList();

	List<String> getSpNameList();

	List<Long> getContactIdList(SearchContactDTO dto);

	void updateRepeatTimes(Long contactId, String description);

	List<String> getBySendSubtaskId(Long id);

	List<ContactDTO> getContactListWithoutAreaId(int maxNum);

	void updateArea(ContactDTO dto);

	List<String> getTagNameList();

	void deleteByIds(String ids);


}
