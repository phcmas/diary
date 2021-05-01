drop table user_role;
drop table project_member;
drop table project_card;
drop table project;
drop table user;

create table user (
	id int(11) not null auto_increment,
	name varchar(50) not null unique,
	password varchar(150) not null,
	create_date datetime null default null,
	modify_date datetime null default null,
	primary key (id)) engine=InnoDB default charset=utf8;

create table user_role (
	id int(11) not null auto_increment,
	user_id int(11) not null,
	role_name varchar(50) not null,
	primary key(id),
	foreign key(user_id) references user (id)) engine=InnoDB default charset=utf8;

create table project (
	id int(11) not null auto_increment,
	title varchar(150) not null,
	start_date datetime null,
	finish_date datetime null,
	project_type varchar(50) null,
	situation text not null,
	content text not null,
	test_scenario text not null,
	create_date datetime null,
	modify_date datetime null,
	primary key(id)) engine=InnoDB default charset=utf8;

create table project_member (
	id int(11) not null auto_increment,
	user_id int(11) not null,
	project_id int(11) not null,
	primary key(id),
	foreign key(user_id) references user(id),
	foreign key(project_id) references project(id)) engine=InnoDB default charset=utf8;

create table project_card ( 
	id int(11) not null auto_increment,
	project_id int(11) not null,
	primary key(id),
	foreign key(project_id) references project(id)) engine=InnoDB default charset=utf8;




