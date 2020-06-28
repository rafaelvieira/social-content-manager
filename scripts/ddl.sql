create table familycontent.user (

	id serial primary key,
	name varchar(100),
	username varchar(100),
	password varchar(256),
	googleId varchar(200)
);