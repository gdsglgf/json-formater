create table t_user(
	uid bigint(20) not null auto_increment primary key,
	username varchar(16) not null unique,
	password varchar(32) not null,
	email varchar(64) not null unique,
	register_time timestamp not null default current_timestamp
);

INSERT INTO `t_user` VALUES 
	(1,'gdsglgf','e10adc3949ba59abbe56e057f20f883e','gdsglgf@test.com','2016-09-01 00:49:30'),
	(2,'test','e10adc3949ba59abbe56e057f20f883e','1@2.com','2016-09-01 03:29:14'),
	(3,'another-user','e10adc3949ba59abbe56e057f20f883e','json@formater.com','2016-09-01 03:41:57'),
	(4,'mac','e10adc3949ba59abbe56e057f20f883e','support@mac.com','2016-09-01 03:47:15');

create table t_history(
	hid bigint(20) not null auto_increment primary key,
	uid bigint(20) not null,
	content varchar(1024) not null,
	submit_time timestamp not null default current_timestamp,

	foreign key (uid) references t_user(uid)
);


INSERT INTO `t_history` VALUES 
	(1,1,'{\"number\":1,\"array\":[],\"null\":null,\"string\":\"test\",\"boolean\":true,\"obj\":{},\"level1\":{\"level2\":{\"level3\":{\"level4\":{\"level5\":{\"level6\":{\"level7\":{\"level8\":{}}}}}}}}}','2016-09-01 07:33:56'),
	(2,1,'{ \"firstName\":\"Bill\" , \"lastName\":\"Gates\" }','2016-09-01 07:35:00'),
	(3,1,'{\"number\":1,\"array\":[{\"hid\":1000, \"pdt\":\"2016-08-31 09:02:00\", \"content\":{ \"firstName\":\"John\" , \"lastName\":\"Doe\" }}, {\"hid\":1001, \"pdt\":\"2016-08-31 09:02:00\", \"content\":{ \"firstName\":\"Anna\" , \"lastName\":\"Smith\" }}],\"null\":null,\"string\":\"test\",\"boolean\":true,\"obj\":{},\"level1\":{\"level2\":{\"level3\":{\"level4\":{\"level5\":{\"level6\":{\"level7\":{\"level8\":{}}}}}}}}}','2016-09-02 01:51:32'),
	(4,2,'{\"number\":1,\"array\":[],\"null\":null,\"string\":\"test\",\"boolean\":true,\"obj\":{},\"level1\":{\"level2\":{\"level3\":{\"level4\":{\"level5\":{\"level6\":{\"level7\":{\"level8\":{}}}}}}}}}','2016-09-02 03:28:22'),
	(5,3,'{\"number\":1,\"array\":[],\"null\":null,\"string\":\"test\",\"boolean\":true,\"obj\":{},\"level1\":{\"level2\":{\"level3\":{\"level4\":{\"level5\":{\"level6\":{\"level7\":{\"level8\":{}}}}}}}}}','2016-09-02 05:16:34'),
	(6,1,'{\"number\":1,\"array\":[],\"null\":null,\"string\":\"test\",\"boolean\":true,\"obj\":{},\"level1\":{\"level2\":{\"level3\":{\"level4\":{\"level5\":{\"level6\":{\"level7\":{\"level8\":{}}}}}}}}}','2016-09-02 07:28:17');