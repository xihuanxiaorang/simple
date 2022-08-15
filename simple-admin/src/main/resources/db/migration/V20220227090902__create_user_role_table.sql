CREATE DATABASE IF NOT EXISTS `simple` DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_bin;

USE `simple`;

CREATE TABLE IF NOT EXISTS `sys_user`
(
    id              VARCHAR(32)       NOT NULL
        PRIMARY KEY COMMENT '用户ID',
    username        VARCHAR(64)       NOT NULL UNIQUE COMMENT '用户名',
    nickname        VARCHAR(64)       NULL COMMENT '用户昵称',
    password        VARCHAR(64)       NOT NULL COMMENT '加密后的密码',
    gender          tinyint           NOT NULL NULL COMMENT '性别，0-男，1-女，2-未知',
    locked          tinyint DEFAULT 0 NOT NULL COMMENT '是否锁定，1-是，0-否',
    enabled         tinyint DEFAULT 1 NOT NULL COMMENT '是否可用，1-是，0-否',
    phone           VARCHAR(32)       NOT NULL UNIQUE COMMENT '手机号',
    last_login_ip   VARCHAR(64)       NULL COMMENT '最后登录IP',
    last_login_time datetime(6)       NULL COMMENT '最后登录IP',
    created_time    datetime(6)       NOT NULL COMMENT '创建时间',
    updated_time    datetime(6)       NOT NULL COMMENT '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT '用户表';

CREATE TABLE IF NOT EXISTS `sys_role`
(
    id           VARCHAR(32)  NOT NULL
        PRIMARY KEY COMMENT '角色ID',
    name         VARCHAR(128) NULL UNIQUE COMMENT '角色名称',
    title        VARCHAR(128) NULL COMMENT '角色标识',
    created_time datetime(6)  NOT NULL COMMENT '创建时间',
    updated_time datetime(6)  NOT NULL COMMENT '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT '角色表';


CREATE TABLE IF NOT EXISTS `sys_user_role`
(
    user_id VARCHAR(32) NOT NULL COMMENT '用户ID',
    role_id VARCHAR(32) NOT NULL COMMENT '角色ID',
    CONSTRAINT c_user_id
        FOREIGN KEY (user_id) REFERENCES sys_user (id),
    CONSTRAINT c_role_id
        FOREIGN KEY (role_id) REFERENCES sys_role (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT '用户角色表';