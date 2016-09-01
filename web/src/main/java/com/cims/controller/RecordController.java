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

@Controller
@RequestMapping(value = "/records")
public class RecordController {
	public static Logger log = Logger.getLogger(RecordController.class);

	@Autowired
	private RecordService recordService;

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

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public ModelAndView historyView(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = null;
		view = new ModelAndView("records/history");
		return view;
	}

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
