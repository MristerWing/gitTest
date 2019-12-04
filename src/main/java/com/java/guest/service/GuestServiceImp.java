package com.java.guest.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.java.aop.LogAspect;
import com.java.guest.dao.GuestDao;
import com.java.guest.dto.GuestDto;

/**
 * @author KimJinsu
 * @date 2019. 11. 27.
 * @apiNote Guest의 서비스 객체
 */
public class GuestServiceImp implements GuestService {

	private GuestDao guestDao;
	private GuestDto guestDto;

	public GuestServiceImp() {
	}

	public GuestServiceImp(GuestDao guestDao, GuestDto guestDto) {
		this.guestDao = guestDao;
		this.guestDto = guestDto;
	}

	public void setGuestDao(GuestDao guestDao) {
		this.guestDao = guestDao;
	}

	public void setGuestDto(GuestDto guestDto) {
		this.guestDto = guestDto;
	}

	/**
	 * @apiNote 방명록 글작성 확인
	 */
	@Override
	public void guestWrite(ModelAndView modelAndView) {
		Map<String, Object> modelMap = modelAndView.getModelMap();
		HttpServletRequest request = (HttpServletRequest) modelMap
				.get("request");

		String pageNumber = request.getParameter("pageNumber");
		if (pageNumber == null)
			pageNumber = "1";

		int currentPage = Integer.parseInt(pageNumber); // 1page
		int boardSize = 3; // page당 출력 게시물 수
		int startRow = (currentPage - 1) * boardSize + 1; // start num
		int endRow = boardSize * currentPage; // end num
		LogAspect.logger.info(LogAspect.logMsg + currentPage);

		int count = guestDao.getCount();
		LogAspect.logger.info(LogAspect.logMsg + count);

		List<GuestDto> guestList = null;
		if (count > 0) {
			guestList = guestDao.guestList(startRow, endRow);
		}
		LogAspect.logger.info(LogAspect.logMsg + guestList.size());

		request.setAttribute("guestList", guestList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("boardSize", boardSize);
		request.setAttribute("count", count);

		modelAndView.setViewName("guest/write");
	}

	/**
	 * @see GuestDao.guestWrite
	 * @apiNote 방명록 글 작성
	 */
	@Override
	public void guestWriteOk(ModelAndView modelAndView) {
		Map<String, Object> modelMap = modelAndView.getModelMap();

		guestDto = (GuestDto) modelMap.get("guestDto");
		guestDto.setWriteDate(new Date());
		guestDto.setMessage(guestDto.getMessage().replace("\r\n", "<br>"));

		LogAspect.logger.info(LogAspect.logMsg + guestDto.toString());

		int check = guestDao.guestWrite(guestDto);

		modelAndView.addObject("check", check);
		modelAndView.setViewName("guest/writeOk");
	}

}
