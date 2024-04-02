drop table if exists club_activity;
drop table if exists user_activity;
drop table if exists user_club;
drop table if exists activity;
drop table if exists club;
drop table if exists user;

-- 用户信息表
create table user
(
    id           bigint auto_increment comment '用户id' primary key,
    username     varchar(255) not null comment '名称',
    password     varchar(255) not null comment '密码',
    name         varchar(255) default '' not null comment '昵称',
    avatar       varchar(1023) null comment '头像',
    gender       tinyint null comment '性别（0-男，1-女）',
    phone        varchar(255) null comment '电话',
    email        varchar(255) null comment '邮箱',
    role         int     default 0 not null comment '用户 0-用户 1-管理员',
    money        int     default 0 not null comment '贡献金额（单位是分）',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint  default 0 not null comment '是否删除'
) comment '用户';

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
    activity_number int default 0 not null comment '活动数量',
    money        int     default 0 not null comment '公费（单位是分）',
    president_id   bigint not null comment '社长id',
    is_admitted 	tinyint  default 0 not null comment '是否审核通过(0-审核中, 1-通过)',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint  default 0 not null comment '是否删除',
    FOREIGN KEY (president_id) REFERENCES user(id) on delete cascade
)comment '社团';

-- 活动表
create table activity
(
    id             bigint auto_increment comment '主键' primary key,
    club_id        bigint not null comment '社团id',
    name           varchar(255) not null comment '活动名称',
    info           varchar(1023) not null comment '活动信息',
    title          varchar(255) not null comment '活动主题',
    begin_time     datetime not null comment '活动开始时间',
    end_time       datetime not null comment '活结束时间',
    sign_begin_time datetime not null comment  '最早签到时间' ,
    sign_end_time  datetime not null comment  '最迟签到时间' ,
    address        varchar(255) not null comment '活动地点',
    sign           varchar(255) not null comment '报名方式',
    money          bigint not null comment '活动缴费（单位是分）',
    join_people          int default 0 not null comment '参加人数',
    attendance_people      int default 0 not null comment '签到人数',
    summary        varchar(1023) null comment '活动总结',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint  default 0 not null comment '是否删除',
    FOREIGN KEY (club_id) REFERENCES club(id) on delete cascade
)comment '活动';

create table user_activity
(
    user_activity_id           bigint auto_increment comment '主键' primary key,
    user_id                 bigint not null comment '用户id',
    activity_id                bigint not null comment '活动id',
    pay_status              int default 0 not null comment '0-没缴费 1-缴费',
    join_status             int default 0 not null comment '0-没签到 1-签到',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint  default 0 not null comment '是否删除',
    FOREIGN KEY (user_id) REFERENCES user(id) on delete cascade,
    FOREIGN KEY (activity_id) REFERENCES activity(id) on delete cascade
)comment '社员和活动关系表';


create table user_club
(
    user_club_id   bigint auto_increment comment '主键' primary key,
    user_id     bigint not null comment '用户id',
    club_id     bigint not null comment '社团id',
    is_passed   tinyint  default 0 not null comment '是否审核通过(0-审核中, 1-通过)',
    contribution_money   bigint default 0 comment '社团贡献（单位是分）',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint  default 0 not null comment '是否删除',
    FOREIGN KEY (user_id) REFERENCES user(id) on delete cascade ,
    FOREIGN KEY (club_id) REFERENCES club(id) on delete cascade
)comment '社员和社团关系表';


-- 查询社员、社团关系表，带上名字
-- drop view if exists view_club_users;
-- CREATE VIEW view_club_users AS select user_id, club_id, u.name as name, c.name as club_name
--                                from user_club uc left join user u on uc.user_id = u.id left join club c on uc.club_id = c.id
--                                where uc.is_delete=0 and u.is_delete=0 and c.is_delete=0;

drop trigger if exists on_join_club;
drop trigger if exists on_release_activity;
drop trigger if exists on_sign_up_activity;
drop trigger if exists on_user_activity_status_update;
drop trigger if exists on_exit_club;
drop trigger if exists on_delete_activity;
drop trigger if exists on_cancel_activity;

-- 社长通过用户报名
delimiter //
create trigger on_join_club after update on user_club
    for each row begin
    IF NEW.is_passed = 1 AND OLD.is_passed != 1 THEN
        update club set member = member + 1 where id = new.club_id;
    END IF;
end //
delimiter ;

-- 社长发布活动
delimiter //
create trigger on_release_activity after insert on activity
    for each row begin
    update club set activity_number = club.activity_number + 1 where club.id = new.club_id;
end //
delimiter ;

-- 用户报名活动
delimiter //
create trigger on_sign_up_activity after insert on user_activity
    for each row begin
    update activity set join_people = activity.join_people + 1 where activity.id = new.activity_id;
end //
delimiter ;

-- 用户活动缴费和用户活动签到
DELIMITER //
CREATE TRIGGER on_user_activity_status_update
    AFTER UPDATE ON user_activity
    FOR EACH ROW
BEGIN
    DECLARE activity_money bigint;
    -- 检查pay_status是否从非1更新为1
    IF NEW.pay_status = 1 AND OLD.pay_status != 1 THEN
        -- 获取activity的money字段值
        SELECT money INTO activity_money FROM activity WHERE id = NEW.activity_id;
        -- 如果获取到了activity_money，则进行更新操作
        IF activity_money IS NOT NULL THEN
            -- 更新club的money字段
            UPDATE club
            SET money = money + activity_money
            WHERE id = (select club_id from activity where id = NEW.activity_id);
            -- 更新user的money字段
            UPDATE user
            SET money = money + activity_money
            WHERE id = NEW.user_id;
            -- 更新user_club的contribution_money字段
            UPDATE user_club
            SET contribution_money = contribution_money + activity_money
            WHERE user_id = NEW.user_id and club_id = (select club_id from activity where id = NEW.activity_id);
        END IF;
    END IF;
    IF NEW.join_status = 1 AND OLD.join_status != 1 THEN
        update activity set attendance_people = activity.attendance_people + 1 where activity.id = new.activity_id;
    END IF;
END //
DELIMITER ;

-- 用户退出社团和社长踢人
delimiter //
create trigger on_exit_club after delete on user_club
    for each row begin
    IF OLD.is_passed = 1 THEN
        update club set member = member - 1 where id = OLD.club_id;
    END IF;
end //
delimiter ;

-- 社长删除活动
delimiter //
create trigger on_delete_activity after delete on activity
    for each row begin
    update club set activity_number = club.activity_number - 1 where club.id = OLD.club_id;
end //
delimiter ;

-- 用户取消报名
delimiter //
create trigger on_cancel_activity after delete on user_activity
    for each row begin
    update activity set join_people = activity.join_people - 1 where activity.id = OLD.activity_id;
end //
delimiter ;