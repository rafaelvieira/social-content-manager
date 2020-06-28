package com.rafalabs.familycontent.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AppUser {

	private Integer id;
	private String name;
	private String username;
	private String password;
	private String googleId;
}
