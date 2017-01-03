-- ���ݿ��ʼ���ű�
create DATABASE moana;

-- ʹ�����ݿ�
use moana;

-- ������ӰƱ����
create table MOVIE_INFO (
	`MOVIE_ID` bigint NOT NULL AUTO_INCREMENT COMMENT '��ӰƱid',
	`NAME` varchar(120) NOT NULL COMMENT '��Ӱ����',
	`NUMBER` int NOT NULL COMMENT '�������',
	`START_TIME` timestamp NOT NULL COMMENT '��������ʱ��',
	`END_TIME` timestamp NOT NULL COMMENT '��������ʱ��',
	`CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
	PRIMARY KEY (MOVIE_ID),
	key idx_start_time(start_time),
	key idx_end_time(end_time),
	key idx_create_time(create_time)
)engine=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='��ӰƱ����';

-- ��ʼ����ӰƱ��������
insert into 
	MOVIE_INFO(NAME, NUMBER, START_TIME, END_TIME)
values
	('�Ǽ��Ժ�3�������޽�',100, '2016-12-15 00:00:00', '2017-01-15 00:00:00'),
	('�̸�2',100, '2016-12-20 00:00:00', '2017-01-20 00:00:00'),
	('ָ����3�����߹���',100, '2017-01-08 00:00:00', '2017-01-15 00:00:00'),
	('��������',100, '2017-01-10 00:00:00', '2017-01-15 00:00:00');

-- �����ɹ���ϸ��
-- �û���¼��֤�����Ϣ
create table SUCCESS_RUSH(
	`MOVIE_ID` bigint NOT NULL COMMENT '������ӰƱid',
	`USER_MAIL` varchar(120) NOT NULL COMMENT '�û�����',
	`STATE` tinyint NOT NULL DEFAULT -1 COMMENT '״̬��ʶ��-1����Ч��0���ɹ���1���Ѹ���',
	`CREATE_TIME` timestamp NOT NULL COMMENT '����ʱ��',
	PRIMARY KEY (MOVIE_ID, USER_MAIL), /*��������*/
	key idx_create_time (CREATE_TIME)
)engine=InnoDB DEFAULT CHARSET=utf8 COMMENT='�����ɹ���ϸ��';