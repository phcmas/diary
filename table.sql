drop table user_role;
drop table project_member;
drop table project_card;
drop table project;
drop table user;
drop table file_info;
drop table algorithm_card;
drop table algorithm;

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
	name varchar(50) not null,
	role_name varchar(50) not null,
	primary key(id),
	foreign key(user_id) references user (id)) engine=InnoDB default charset=utf8;

create table project (
	id int(11) not null auto_increment,
	author varchar(50) not null,
	title varchar(150) not null,
	start_date datetime not null,
	end_date datetime not null,
	project_type varchar(40) not null,
	situation text null,
	content text null,
	test_scenario text null,
	create_date datetime null,
	modify_date datetime null,
	primary key(id)) engine=InnoDB default charset=utf8;

create table project_member (
	id int(11) not null auto_increment,
	name varchar(50) not null,
	project_id int(11) not null,
	primary key(id),
	foreign key(project_id) references project(id)) engine=InnoDB default charset=utf8;

create index pm_idx on project_member(project_id);

create table project_card ( 
	id int(11) not null auto_increment,
	project_id int(11) not null,
	project_type varchar(40) null,
	short_title varchar(60) not null,
	short_content varchar(100) not null,
	member_count int(11) not null,
	start_date datetime null,
	end_date datetime null,
	primary key(id),
	foreign key(project_id) references project(id)) engine=InnoDB default charset=utf8;

create index pc_idx on project_card(project_id);

create table algorithm (
	id int(11) not null auto_increment,
	author varchar(50) not null,
	title varchar(150) not null,
	solved_date datetime not null,
	language varchar(50) not null,
	type varchar(40) not null,
	source varchar(40) not null,
	difficulty varchar(40) not null,
	explanation text null,
	content	text null,
	create_date datetime null,
	modify_date datetime null,
	file_id int(11) null,
	primary key(id)) engine=InnoDB default charset=utf8;

create index al_idx on algorithm(language);

create table file_info (
	id int(11) not null auto_increment,
	algorithm_id int(11) not null,
	file_name varchar(100) unique not null,
	save_file_name varchar(150) not null,
	content_type varchar(50) not null,
	primary key(id)) engine=InnoDB default charset=utf8;

create table algorithm_card (
	id int(11) not null auto_increment,
	algorithm_id int(11) not null,
	short_title varchar(50) null,
	short_explanation varchar(100) null,
	type varchar(40) null,
	difficulty varchar(40) null,
	solved_date datetime null,
	primary key(id),
	foreign key(algorithm_id) references algorithm(id)) engine=InnoDB default charset=utf8;


















