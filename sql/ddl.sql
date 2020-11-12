drop table if exists user;
create table user (
    id bigint auto_increment primary key,
    name varchar(30) not null,
    birth date not null,
    note longtext,
    photo longblob
);

DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orig_file_name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `file_name` varchar(255) NOT NULL,
  `file_id` varchar(255) NOT NULL,
  `file_path` varchar(255) NOT NULL,
  `request_ip` varchar(255) NOT NULL,
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `secret_token`;
CREATE TABLE `secret_token` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`app_key` varchar(255) CHARACTER SET utf8 NOT NULL,
`app_name` varchar(255) NOT NULL,
`created_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

