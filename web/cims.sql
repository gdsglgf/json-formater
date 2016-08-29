create table cims_user(
    uid bigint(20) not null auto_increment primary key,
    username varchar(16) not null unique,
    password varchar(32) not null,
    phone varchar(16) not null unique,
    email varchar(64) not null unique,
    register_time timestamp not null default current_timestamp
);