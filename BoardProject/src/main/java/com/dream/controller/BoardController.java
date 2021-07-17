package com.dream.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dream.service.BoardService;
import com.dream.vo.ResultVO;

@Controller
@RequestMapping("board/")
public class BoardController {

	private static final Logger LOG = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	BoardService service;

	@ResponseBody
	@RequestMapping(value = "get-board-list.do", method = RequestMethod.GET)
	public ResultVO getBoardList() 
	{	
		// 호출 시 찍히게 될 로그
		LOG.info("[GET] getBoardList");
		// 결과 값을 담을 ResultVO를 선언한 생성자를 통해서 만드는데 기본값은 success는 false, result는 null로 세팅
		ResultVO result = new ResultVO(false, null);

		try {
			result.setResult(service.getBoardList());
			result.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			LOG.error("[Board] getBoardList : " + e.getMessage(), e);
		}

		return result;

	}
}
