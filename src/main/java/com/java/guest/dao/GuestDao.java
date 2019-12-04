package com.java.guest.dao;

import java.util.List;

import com.java.guest.dto.GuestDto;

public interface GuestDao {
	public int guestWrite(GuestDto guestDto);

	public int getCount();

	public List<GuestDto> guestList(int startRow, int endRow);
}
