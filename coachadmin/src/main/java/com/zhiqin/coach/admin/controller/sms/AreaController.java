package com.zhiqin.coach.admin.controller.sms;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhiqin.coach.admin.controller.BaseController;
import com.zhiqin.coach.admin.dto.AreaDTO;
import com.zhiqin.coach.admin.service.AreaService;
import com.zhiqin.coach.admin.util.JsonBinder;
import com.zhiqin.coach.admin.util.JsonUtils;

/**
 * 
 * @author lanyuan
 * 2013-11-19
 * @Email: mmm333zzz520@163.com
 * @version 1.0v
 */
@Controller
@RequestMapping("/sms/area/")
public class AreaController extends BaseController{
	@Resource
	private AreaService areaService;
	@RequestMapping("list")
	@ResponseBody
	public void list(int provinceId, HttpServletRequest request, HttpServletResponse response) {
		List<AreaDTO> areaList = null;
		if(provinceId > 0){
			areaList = areaService.getSubareaById(provinceId);
		} else {
			areaList = new ArrayList<AreaDTO>();
		}
		buildAreaList("全部", areaList, response);
	}
	
	private void buildAreaList(String defaultSelect, List<AreaDTO> areaList, HttpServletResponse response) {
		StringBuilder s = new StringBuilder("[");
		s.append("[\"\", \""+defaultSelect+"\"],");
		if(areaList != null && areaList.size() > 0){
			for(AreaDTO area : areaList){
				s.append("[\"" + area.getId() + "\", \"" + area.getName() + "\"],");
			}
		}
		s.setLength(s.length() - 1);
		s.append("]");
		JsonUtils.write(response, s.toString());
	}
}