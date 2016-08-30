create table t_user(
	uid bigint(20) not null auto_increment primary key,
	username varchar(16) not null unique,
	password varchar(32) not null,
	email varchar(64) not null unique,
	register_time timestamp not null default current_timestamp
);

create table t_history(
	hid bigint(20) not null auto_increment primary key,
	uid bigint(20) not null,
	content varchar(1024) not null,

	foreign key (uid) references t_user(uid)
);