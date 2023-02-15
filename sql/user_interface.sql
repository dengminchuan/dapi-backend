create table if not exists user_interface
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `userId` varchar(256) not null comment '调用用户Id',
    `status` int default 0 not null comment '0关闭 1开启',
    `createTime` timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` timestamp  comment '更新时间',
    `isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)',
    `totalCount` bigint default 0 not null comment '总调用次数',
    `leftCount` bigint default 0 not null comment '剩余调用次数'
) comment '用户调用接口表';