package com.java.guest.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.java.guest.dto.GuestDto;

/**
 * @author KimJinsu
 * @date 2019. 11. 27.
 * @apiNote Guest의 DAO객체
 */
public class GuestDaoImp implements GuestDao {
	private SqlSessionTemplate sqlSessionTemplate;

	public GuestDaoImp() {
	}

	public GuestDaoImp(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public int guestWrite(GuestDto guestDto) {
		return sqlSessionTemplate.insert("mapper.GuestMapper.guestInsert",
				guestDto);
	}

	@Override
	public int getCount() {
		return sqlSessionTemplate.selectOne("guestCountSelect");
	}

	@Override
	public List<GuestDto> guestList(int startRow, int endRow) {
		Map<String, Integer> rowMap = new HashMap<String, Integer>();
		rowMap.put("startRow", startRow);
		rowMap.put("endRow", endRow);

		return sqlSessionTemplate.selectList("guestListSelect", rowMap);
	}

}
