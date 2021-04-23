drop table user_role;
drop table user;

create table user (
	id int(11) auto_increment,
	name varchar(255) not null unique,
	password varchar(255) not null,
	create_date datetime null default null,
	modify_date datetime null default null,
	primary key (id)) engine=InnoDB default charset=utf8;

create table user_role (
	id int(11) auto_increment,
	user_id int(11) not null,
	role_name varchar(100) not null,
	primary key(id),
	foreign key(user_id) references user (id)) engine=InnoDB default charset=utf8;
