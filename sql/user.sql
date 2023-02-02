-- 创建数据库
create database if not exists dapi;
-- 切换到数据库
use dapi;
-- 创建user表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userName     varchar(256)                           null comment '用户昵称',
    userAccount  varchar(256)                           not null comment '账号',
    userAvatar   varchar(256)                          null comment '用户头像',
    gender       tinyint                                null comment '性别',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user / admin',
    userPassword varchar(256)                           not null comment '密码',
    createTime   timestamp     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   timestamp    comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    constraint uni_userAccount
        unique (userAccount)
) comment '用户' ROW_FORMAT=DYNAMIC;


