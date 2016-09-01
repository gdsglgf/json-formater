package com.cims.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cims.dto.RecordDTO;
import com.cims.model.JSONRecord;
import com.cims.service.RecordService;
import com.cims.util.HttpSessionParser;

/**
 * 处理历史信息的请求.
 * 
 * @author Luo Guofu
 */

@Controller
@RequestMapping(value = "/records")
public class RecordController {
	public static Logger log = Logger.getLogger(RecordController.class);

	@Autowired
	private RecordService recordService;

	/**
	 * 保存登录用户的历史数据.
	 * @param content - json 字符串
	 * @param request HttpRequest对象
	 * @return 包含保存json字符串信息的Map<String, Object>对象
	 */
	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveAction(
			@RequestParam(value = "content", required = true) String content,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		Long uid = HttpSessionParser.getUid(session);
		Map<String, Object> result = new HashMap<String, Object>();
		Boolean isLogin = uid != null;
		result.put("isSuccessful", isLogin);
		if (isLogin) {
			JSONRecord record = new JSONRecord(uid, content);
			recordService.saveRecord(record);
		}

		log.info(String.format("isLogin:%s, content:[%s]", isLogin, content));

		return result;
	}

	/**
	 * 显示历史查询信息页面.
	 * @param request - HttpServletRequest对象
	 * @param response - HttpServletResponse对象
	 * @return 历史查询信息页面的ModelAndView对象
	 */
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public ModelAndView historyView(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = null;
		view = new ModelAndView("records/history");
		return view;
	}

	/**
	 * 处理历史信息查询请求.
	 * @param request - HttpServletRequest对象
	 * @param response - HttpServletResponse对象
	 * @return 
	 */
	@RequestMapping(value = "/search.action", method = RequestMethod.POST)
	public @ResponseBody List<RecordDTO> searchAction(
			HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		Long uid = HttpSessionParser.getUid(session);
		log.info(String.format("uid: %d", uid));
		List<RecordDTO> result = new ArrayList<RecordDTO>();
		if (uid != null) {
			List<JSONRecord> list = recordService.searchAll(uid);
			int size = list.size();
			log.info(String.format("resule size: %d", size));
			for (int i = 0; i < size; i++) {
				RecordDTO dto = new RecordDTO(list.get(i));
				result.add(dto);
			}
		}

		return result;
	}
}
