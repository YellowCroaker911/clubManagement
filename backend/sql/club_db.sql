drop table if exists user;
drop table if exists activity;
drop table if exists club;
drop table if exists club_activity;
drop table if exists user_activity;
drop table if exists user_club;

-- 用户信息表
create table user
(
    id           bigint auto_increment comment '用户id' primary key,
    username     varchar(255) not null comment '名称',
    password     varchar(255) not null comment '密码',
    name         varchar(255) default '' not null comment '名称',
    account      varchar(255) null comment '账号',
    avatar       varchar(1023) null comment '头像',
    gender       tinyint null comment '性别',
    phone        varchar(255) null comment '电话',
    email        varchar(255)  comment '邮箱',
    role         int     default 0 not null comment '用户 0-用户 1-管理员',
    money        int     default 0 not null comment '贡献金额（单位是分）',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint  default 0 not null comment '是否删除'
) comment '用户';



-- 活动表
create table activity
(
    id             bigint auto_increment comment '主键' primary key,
    name           varchar(255) not null comment '活动名称',
    info           varchar(1023) null comment '活动信息',
    title          varchar(255) null comment '活动主题',
    begin_time     datetime null comment '活动开始时间',
    end_time       datetime null comment '活结束时间',
    address        varchar(255) null comment '活动地点',
    sign           varchar(255) null comment '报名方式',
    join_people          int default 0 not null comment '参加人数',
    attendance_people      int default 0 not null comment '签到人数',
    summary        varchar(1023) null comment '活动总结',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint  default 0 not null comment '是否删除'
)comment '活动';




-- 社团表
create table club
(
    id             bigint auto_increment comment '社团id' primary key,
    name           varchar(255) not null comment '名称',
    avatar         varchar(1023) null comment '头像',
    info           varchar(1023) null comment '社团描述',
    address        varchar(1023) null comment '活动场地',
    contact        varchar(255) null comment '联系方式',
    member         int default 0 not null comment '成员数量',
    money        int     default 0 not null comment '公费（单位是分）',
    president_id   bigint not null comment '社长id',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint  default 0 not null comment '是否删除'
)comment '社团';


create table club_activity
(
    club_activity_id    bigint auto_increment comment '主键' primary key,
    club_id             bigint not null comment '社团id',
    activity_id         bigint not null comment '活动id',
    expense             int default 0 not null comment '社团对该活动的花费（单位是分）',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint  default 0 not null comment '是否删除',
    FOREIGN KEY (club_id) REFERENCES club(id),
    FOREIGN KEY (activity_id) REFERENCES activity(id)
)comment '社团和活动关系表';




create table user_activity
(
    user_activity_id           bigint auto_increment comment '主键' primary key,
    user_id                 bigint not null comment '用户id',
    activity_id                bigint not null comment '活动id',
    join_status             int default 0 not null comment '0-没签到 1-签到',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint  default 0 not null comment '是否删除',
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (activity_id) REFERENCES activity(id)
)comment '社员和活动关系表';


create table user_club
(
    user_club_id   bigint auto_increment comment '主键' primary key,
    user_id     bigint not null comment '用户id',
    club_id     bigint not null comment '社团id',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint  default 0 not null comment '是否删除',
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (club_id) REFERENCES user(id),
    index user_index (user_id),
    index club_index (club_id)
)comment '社员和社团关系表';

drop view if exists view_club_users;

-- 查询社员、社团关系表，带上名字
CREATE VIEW view_club_users AS select user_id, club_id, u.name as name, c.name as club_name
            from user_club uc left join user u on uc.user_id = u.id left join club c on uc.club_id = c.id
            where uc.is_delete=0 and u.is_delete=0 and c.is_delete=0;