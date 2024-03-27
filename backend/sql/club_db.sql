drop table if exists club_activity;
drop table if exists user_activity;
drop table if exists user_club;
drop table if exists user;
drop table if exists activity;
drop table if exists club;

-- 用户信息表
create table user
(
    id           bigint auto_increment comment '用户id' primary key,
    username     varchar(255) not null comment '名称',
    password     varchar(255) not null comment '密码',
    name         varchar(255) default '' not null comment '昵称',
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
    club_id        bigint comment '社团id',
    name           varchar(255) not null comment '活动名称',
    info           varchar(1023) not null comment '活动信息',
    title          varchar(255) not null comment '活动主题',
    begin_time     datetime not null comment '活动开始时间',
    end_time       datetime not null comment '活结束时间',
    address        varchar(255) not null comment '活动地点',
    sign           varchar(255) not null comment '报名方式',
    money          bigint comment '活动缴费',
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
    activity_number int default 0 not null comment '活动数量',
    money        int     default 0 not null comment '公费（单位是分）',
    president_id   bigint not null comment '社长id',
    is_admitted 	tinyint  default 0 not null comment '是否审核通过(0-审核中, 1-通过)',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint  default 0 not null comment '是否删除'
)comment '社团';

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
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (activity_id) REFERENCES activity(id)
)comment '社员和活动关系表';


create table user_club
(
    user_club_id   bigint auto_increment comment '主键' primary key,
    user_id     bigint not null comment '用户id',
    club_id     bigint not null comment '社团id',
    is_passed   tinyint  default 0 not null comment '是否审核通过(0-审核中, 1-通过)',
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
-- 用户报名社团
drop view if exists on_join_club;
delimiter //
create trigger on_join_club after insert on user_club
    for each row begin
    update club set member = member + 1 where id = new.club_id;
end //
delimiter ;

-- 社长发布活动
drop view if exists on_release_activity;
delimiter //
create trigger on_release_activity after insert on activity
    for each row begin
    update club set activity_number = club.activity_number + 1 where club_id = new.club_id;
end //
delimiter ;

-- 用户报名活动
drop view if exists on_sign_up_activity;
delimiter //
create trigger on_sign_up_activity after insert on user_activity
    for each row begin
    update activity set join_people = activity.join_people + 1 where activity_id = new.activity_id;
end //
delimiter ;

-- 用户活动缴费
drop view if exists after_user_activity_pay_status_update;
DELIMITER //
CREATE TRIGGER after_user_activity_pay_status_update
    AFTER UPDATE ON user_activity
    FOR EACH ROW
BEGIN
    DECLARE activity_money DECIMAL(10, 2);
    DECLARE club_id INT;

    update activity
    SET join_people = join_people + 1
    where activity_id = NEW.activity_id;

    -- 检查pay_status是否从非1更新为1
    IF NEW.pay_status = 1 AND OLD.pay_status != 1 THEN
        -- 获取activity的money字段值
        SELECT money INTO activity_money FROM activity WHERE id = NEW.activity_id;
        -- 如果获取到了activity_money，则进行更新操作
        IF activity_money IS NOT NULL THEN
            -- 更新activity所属club的money字段
            SELECT club_id INTO club_id FROM activity WHERE id = NEW.activity_id;
            IF club_id IS NOT NULL THEN
                UPDATE club
                SET money = money + activity_money
                WHERE id = club_id;
            END IF;

            -- 更新user的money字段
            UPDATE user
            SET money = money + activity_money
            WHERE id = NEW.user_id;
        END IF;
    END IF;
END;
//
DELIMITER ;

-- 用户活动签到
drop view if exists after_user_activity_join_status_update;
delimiter //
create trigger after_user_activity_join_status_update
    AFTER UPDATE ON user_activity
    for each row begin
    update activity set attendance_people = activity.attendance_people  + 1 where activity_id = new.activity_id;
end //
delimiter ;