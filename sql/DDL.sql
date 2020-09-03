drop table if exists user;
create table user (
    id bigint auto_increment primary key,
    name varchar(30) not null,
    birth date not null,
    note longtext,
    photo longblob
);