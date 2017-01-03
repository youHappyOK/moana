-- 数据库初始化脚本
create DATABASE moana;

-- 使用数据库
use moana;

-- 创建电影票库存表
create table MOVIE_INFO (
	`MOVIE_ID` bigint NOT NULL AUTO_INCREMENT COMMENT '电影票id',
	`NAME` varchar(120) NOT NULL COMMENT '电影名称',
	`NUMBER` int NOT NULL COMMENT '库存数量',
	`START_TIME` timestamp NOT NULL COMMENT '抢购开启时间',
	`END_TIME` timestamp NOT NULL COMMENT '抢购结束时间',
	`CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY (MOVIE_ID),
	key idx_start_time(start_time),
	key idx_end_time(end_time),
	key idx_create_time(create_time)
)engine=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='电影票库存表';

-- 初始化电影票库存表数据
insert into 
	MOVIE_INFO(NAME, NUMBER, START_TIME, END_TIME)
values
	('星际迷航3：暗黑无界',100, '2016-12-15 00:00:00', '2017-01-15 00:00:00'),
	('教父2',100, '2016-12-20 00:00:00', '2017-01-20 00:00:00'),
	('指环王3：王者归来',100, '2017-01-08 00:00:00', '2017-01-15 00:00:00'),
	('君の名は',100, '2017-01-10 00:00:00', '2017-01-15 00:00:00');

-- 抢购成功明细表
-- 用户登录认证相关信息
create table SUCCESS_RUSH(
	`MOVIE_ID` bigint NOT NULL COMMENT '抢购电影票id',
	`USER_MAIL` varchar(120) NOT NULL COMMENT '用户邮箱',
	`STATE` tinyint NOT NULL DEFAULT -1 COMMENT '状态标识：-1：无效，0：成功，1：已付款',
	`CREATE_TIME` timestamp NOT NULL COMMENT '创建时间',
	PRIMARY KEY (MOVIE_ID, USER_MAIL), /*联合主键*/
	key idx_create_time (CREATE_TIME)
)engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='抢购成功明细表';