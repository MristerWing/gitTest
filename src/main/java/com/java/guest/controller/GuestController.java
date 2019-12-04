package com.java.guest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.java.guest.dto.GuestDto;
import com.java.guest.service.GuestService;

/**
 * @author KimJinsu
 * @date 2019. 11. 27.
 * @see GuestService
 * @see /mavenGuest/src/main/webapp/WEB-INF/spring/guest-context.xml
 * @apiNote Guest패키지의 Controller
 */
public class GuestController extends MultiActionController {
	private GuestService guestService;

	public GuestController() {
	}

	public GuestController(GuestService guestService) {
		super();
		this.guestService = guestService;
	}

	public void setGuestService(GuestService guestService) {
		this.guestService = guestService;
	}

	/**
	 * @see GuestService.guestWrite
	 * @param request
	 * @param response
	 * @return modelAndView
	 */
	public ModelAndView guestWriter(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("request", request);
		guestService.guestWrite(modelAndView);

		return modelAndView;
	}

	/**
	 * @see GuestService.guestWriteOk
	 * @param request
	 * @param response
	 * @param guestDto
	 * @return modelAndView
	 */
	public ModelAndView guestWriterOk(HttpServletRequest request,
			HttpServletResponse response, GuestDto guestDto) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("guestDto", guestDto);
		guestService.guestWriteOk(modelAndView);

		return modelAndView;
	}

}
