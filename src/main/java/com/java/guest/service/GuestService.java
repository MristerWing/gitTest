package com.java.guest.service;

import org.springframework.web.servlet.ModelAndView;

public interface GuestService {
	public void guestWrite(ModelAndView modelAndView);

	public void guestWriteOk(ModelAndView modelAndView);
}
