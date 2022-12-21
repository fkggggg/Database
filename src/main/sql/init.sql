DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                            `user_id` int(20) NOT NULL AUTO_INCREMENT,
                            `user_name` varchar(20) NOT NULL,
                            `password` varchar(64) NOT NULL,
                            `permission` int(2) DEFAULT 4,
                            PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

/* permission:0:超级管理员，1：学院管理员，2：班级辅导员，3：学生*/
INSERT into `user` VALUES ('1','test0','123','0');
INSERT into `user` VALUES ('2','test1','123','1');
INSERT into `user` VALUES ('3','test2','123','2');
INSERT into `user` VALUES ('4','张三','123','3');
INSERT into `user` VALUES ('5','李四','123','3');

DROP TABLE IF EXISTS `super_user`;
CREATE TABLE `super_user` (
                                     `user_id` int(20) NOT NULL,
                                     PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='超级用户';

/*这个表有存在的必要吗？？？不是很懂……*/
INSERT into `super_user` VALUES ('1');

DROP TABLE IF EXISTS `college_administrator`;
CREATE TABLE `college_administrator` (
                                         `user_id` int(20) NOT NULL,
                                         `teacher_id` char(5) NOT NULL,
                                         `name` varchar(20) NOT NULL,
                                         `college_name` varchar (20) NOT NULL,
                                         PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='院系管理员';

INSERT into `college_administrator` VALUES ('2','00001','院系管理员测试','学院测试');

DROP TABLE IF EXISTS `class_instructor`;
CREATE TABLE `class_instructor` (
                                         `user_id` int(20) NOT NULL,
                                         `teacher_id` char(5) NOT NULL,
                                         `name` varchar(20) NOT NULL,
                                         `college_name` varchar (20) NOT NULL,
                                         `class_name` varchar (20) NOT NULL,
                                         PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='班级辅导员';

INSERT into `class_instructor` VALUES ('3','00002','班级辅导员测试','学院测试','班级测试');

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
                                    `user_id` int(20) NOT NULL,
                                    `student_id` char(11) NOT NULL,
                                    `name` varchar(20) NOT NULL,
                                    `college_name` varchar (20) NOT NULL,
                                    `class_name` varchar (20) NOT NULL,
                                    `phone` char (11) DEFAULT NULL,
                                    `email` varchar(30) DEFAULT NULL ,
                                    `dormitory` varchar (20) DEFAULT NULL,
                                    `address` varchar (100) DEFAULT NULL ,
                                    `id_type` varchar (20) DEFAULT NULL,
                                    `id_number` varchar (30) DEFAULT NULL ,
                                    `limits_H` int (1) DEFAULT 0,
                                    `limits_J` int (1) DEFAULT 0,
                                    `limits_F` int (1) DEFAULT 0,
                                    `limits_Z` int (1) DEFAULT 0,
                                    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学生';

INSERT INTO `student` VALUES ('4', '20301234567', '张三', '软件学院', '1班','18112345678','20301234567@fudan.edu.cn',null,null,'二代身份证',null,0,0,0,0);
INSERT INTO `student` VALUES ('5', '20301234568', '李四', '计算机科学技术学院','1班','18212345678','20301234568@fudan.edu.cn',null,null,'二代身份证',null,0,0,0,0);

DROP TABLE IF EXISTS `daily_health_report`;
CREATE TABLE `daily_health_report` (
                            `daily_report_id` int(20) NOT NULL AUTO_INCREMENT,
                           `student_id` char(11) NOT NULL,
                           `date` date NOT NULL,
                           `time` time NOT NULL,
                           `health_condition` int(1) NOT NULL,
                           `abnormal_description` varchar(200),
                           `temperature` char (10) NOT NULL,
                           `location` varchar (50) NOT NULL,
                            PRIMARY KEY (`daily_report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='健康日报';
/* health_condition:健康状况，1表示健康，0表示存在异常，具体异常可填写abnormal_condition进行描述*/
INSERT INTO `daily_health_report` VALUES ('1', '20301234567', '2022-12-19', '15:30:00', '1', '无', '36.2','上海市杨浦区松花江路2500号');
INSERT INTO `daily_health_report` VALUES ('2', '20301234568', '2022-12-19', '15:30:00', '1', '无', '36.2','上海市杨浦区松花江路2500号');
INSERT INTO `daily_health_report` VALUES ('3', '20301234567', '2022-12-20', '1:30:00', '1', '无', '36.2','上海市杨浦区松花江路2500号');


DROP TABLE IF EXISTS `check_report`;
CREATE TABLE `check_report` (
                                       `check_report_id` int(20) NOT NULL AUTO_INCREMENT,
                                       `student_id` char(11) NOT NULL,
                                       `date` date NOT NULL,
                                       `time` time NOT NULL,
                                       `state` int (1) NOT NULL,
                                       `campus` char (1) NOT NULL,
                                       PRIMARY KEY (`check_report_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='打卡记录';
/* state:状态，0表示出校，1表示入校*/
/* campus:校区，输入值为H/J/F/Z */
INSERT INTO `check_report` VALUES ('1', '20301234567', '2022-12-19', '15:30:00', '0','H');
INSERT INTO `check_report` VALUES ('2', '20301234567', '2022-12-19', '15:31:00', '1','H');
INSERT INTO `check_report` VALUES ('3', '20301234567', '2022-12-19', '15:32:00', '0','H');
INSERT INTO `check_report` VALUES ('4', '20301234567', '2022-12-20', '1:30:00', '0','H');
INSERT INTO `check_report` VALUES ('5', '20301234567', '2022-12-20', '1:31:00', '1','H');
INSERT INTO `check_report` VALUES ('6', '20301234567', '2022-12-20', '1:32:00', '0','H');
INSERT INTO `check_report` VALUES ('7', '20301234567', '2022-12-20', '1:34:00', '1','H');

DROP TABLE IF EXISTS `departure_form`;
CREATE TABLE `departure_form` (
                                `deform_id` int(20) NOT NULL AUTO_INCREMENT,
                                `application_date` date NOT NULL,
                                `student_id` char(11) NOT NULL,
                                `name` varchar(20) NOT NULL,
                                `college_name` varchar (20) NOT NULL,
                                `class_name` varchar (20) NOT NULL,
                                `reason` varchar (200) DEFAULT NULL,
                                `destination` varchar (50) DEFAULT NULL ,
                                `estimated_date` date NOT NULL,
                                `return_date` date NOT NULL,
                                `state` int(1) DEFAULT 0,
                                `reject_reason` varchar(200) DEFAULT NULL,
                                PRIMARY KEY (`deform_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出校申请表';
/* state:状态，0学生填表提交，班级辅导员审核中。1：班级辅导员同意，院系负责人审核中。2：院系负责人同意。-1：班级辅导员拒绝。-2：院系管理员拒绝。-3：学生取消申请*/


DROP TABLE IF EXISTS `admission_form`;
CREATE TABLE `admission_form` (
                                         `adform_id` int(20) NOT NULL AUTO_INCREMENT,
                                         `application_date` date NOT NULL,
                                         `student_id` char(11) NOT NULL,
                                         `name` varchar(20) NOT NULL,
                                         `college_name` varchar (20) NOT NULL,
                                         `class_name` varchar (20) NOT NULL,
                                         `reason` varchar (200) DEFAULT NULL,
                                         `estimated_date` date NOT NULL,
                                         `estimated_time` time NOT NULL,
                                         `state` int(1) DEFAULT 0,
                                         `reject_reason` varchar(200) DEFAULT NULL,
                                         PRIMARY KEY (`adform_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入校申请表';
