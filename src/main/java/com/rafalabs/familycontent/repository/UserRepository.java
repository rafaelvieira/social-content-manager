package com.rafalabs.familycontent.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rafalabs.familycontent.domain.User;

@Repository
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<User> list() {
		
		return jdbcTemplate.query("select id, name from familycontent.user", 
				(RowMapper<User>) (rs, rowNum) -> 
					User.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.build()
				);
	}

}
