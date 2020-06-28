package com.rafalabs.familycontent.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.common.collect.ImmutableMap;
import com.rafalabs.familycontent.domain.AppUser;

@Repository
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public void create(AppUser user) {
		jdbcTemplate
			.update(
				"insert into familycontent.user(username, password) values(:username, :password)", 
				ImmutableMap.of(
					"username", user.getUsername(), 
					"password", user.getPassword()
				)
			);
	}

	public void savePassword(AppUser appUser) {
		jdbcTemplate
			.update(
				"update familycontent.user set password = :password where username = :username", 
				ImmutableMap.of(
					"password", appUser.getPassword(), 
					"username", appUser.getUsername()
				)
			);
	}
	
	public AppUser findByGoogleId(String googleId) {
		return jdbcTemplate
			.queryForObject(
				"select id, name from familycontent.user where googleId = :googleId", 
				ImmutableMap.of("googleId", googleId),
				(RowMapper<AppUser>) (rs, rowNum) -> 
					AppUser.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.username(rs.getString("username"))
						.build()
				);
	}
	

	public AppUser findByUsername(String username) {
		try {
			return jdbcTemplate
				.queryForObject(
					"select id, name, username, password from familycontent.user where username = :username", 
					ImmutableMap.of("username", username),
					(RowMapper<AppUser>) (rs, rowNum) -> 
						AppUser.builder()
							.id(rs.getInt("id"))
							.name(rs.getString("name"))
							.username(rs.getString("username"))
							.password(rs.getString("password"))
							.build()
					);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<AppUser> list() {
		
		return jdbcTemplate
			.query(
				"select id, name from familycontent.user", 
				(RowMapper<AppUser>) (rs, rowNum) -> 
					AppUser.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.build()
				);
	}

}
