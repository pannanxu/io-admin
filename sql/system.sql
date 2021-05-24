CREATE TABLE `SYS_ACCOUNT_TAB`
(
    `ID`                      BIGINT(19) UNSIGNED NOT NULL COMMENT 'ID',
    `USERNAME`                VARCHAR(16)         NOT NULL COMMENT '登陆用户名',
    `PASSWORD`                VARCHAR(20)         NOT NULL COMMENT '登陆密码',
    `EMAIL`                   VARCHAR(30)                  DEFAULT '' COMMENT '邮箱',
    `PHONE`                   VARCHAR(11)                  DEFAULT '' COMMENT '手机号',
    `SOURCE_FROM`             TINYINT(1)          NOT NULL DEFAULT 0 COMMENT '用户来源',
    `VERSION`                 INT(10)             NOT NULL DEFAULT 0 COMMENT '版本号',
    `STATUS`                  TINYINT(1)          NOT NULL DEFAULT 0 COMMENT '账号状态0:正常,1:异常',
    `ACCOUNT_NON_EXPIRED`     TINYINT(1)                   DEFAULT 0 COMMENT '账户是否过期(0:未过期,1:已过期)',
    `ACCOUNT_NON_LOCKED`      TINYINT(1)                   DEFAULT 0 COMMENT '账户是否锁定(0:未锁定,1:已锁定)',
    `CREDENTIALS_NON_EXPIRED` TINYINT(1)                   DEFAULT 0 COMMENT '凭证是否过期(0:未过期,1:已过期)',
    `CREATE_TIME`             INT(11)             NOT NULL DEFAULT 0 COMMENT '用户创建时间',
    `CREATE_IP`               VARCHAR(12)         NOT NULL DEFAULT '' COMMENT '创建者IP',
    `LAST_LOGIN_TIME`         INT(11)                      DEFAULT 0 COMMENT '用户最后一次登陆时间',
    `LAST_LOGIN_IP`           INT(11)                      DEFAULT 0 COMMENT '用户最后一次登陆IP',
    `LOGIN_COUNT`             INT(10)                      DEFAULT 0 COMMENT '登陆次数',
    `IS_DEL`                  TINYINT(1)          NOT NULL DEFAULT 0 COMMENT '逻辑删除(0:未删除,1:已删除)',
    PRIMARY KEY (`ID`),
    UNIQUE `IDX_PHONE` (`PHONE`),
    UNIQUE `IDX_USERNAME` (`USERNAME`),
    KEY `IDX_EMAIL` (`EMAIL`),
    KEY `IDX_IS_DEL` (`IS_DEL`)
) COMMENT = '账户模型';

INSERT INTO `SYS_ACCOUNT_TAB`
(ID, USERNAME, PASSWORD, PHONE, SOURCE_FROM, VERSION, STATUS, CREATE_TIME, CREATE_IP, IS_DEL)
values (1, 'root', 'root', '17777777777', 0, 1, 0, 0, '127.0.0.1', 0),
       (2, 'admin', 'admin', '17777777778', 0, 1, 0, 0, '127.0.0.1', 0),
       (3, 'user', 'user', '17777777779', 0, 1, 0, 0, '127.0.0.1', 0);

CREATE TABLE `SYS_USER_DETAIL`
(
    `ID`          BIGINT(19) UNSIGNED NOT NULL COMMENT '账户ID',
    `NICKNAME`    VARCHAR(30)         NOT NULL DEFAULT '' COMMENT '昵称',
    `AVATAR`      VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '头像',
    `GENDER`      TINYINT(1)                   DEFAULT 0 COMMENT '性别',
    `WHISPERS`    varchar(300)                 default '' comment '微语(类似个性签名)',
    `CREATE_TIME` INT(11)             NOT NULL DEFAULT 0 COMMENT '创建时间',
    `UPDATE_TIME` INT(11)             NOT NULL DEFAULT 0 COMMENT '更新时间',
    `REMARK`      VARCHAR(255)                 DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`ID`)
) COMMENT ='用户详细信息';

CREATE TABLE `SYS_PLATFORM_ACCOUNT`
(
    `ID`             BIGINT(19) UNSIGNED NOT NULL COMMENT 'ID',
    `UID`            BIGINT(19) UNSIGNED NOT NULL COMMENT '用户ID',
    `PLATFORM_ID`    VARCHAR(60)         NOT NULL COMMENT '平台ID',
    `PLATFORM_TOKEN` VARCHAR(60)         NOT NULL COMMENT 'ACCESS_TOKEN',
    `TYPE`           TINYINT(1)          NOT NULL DEFAULT 0 COMMENT '平台类型 0:未知,1:facebook,2:google,3:wechat,4:qq,5:weibo,6:twitter',
    `NICKNAME`       VARCHAR(60)                  DEFAULT '' COMMENT '昵称',
    `AVATAR`         VARCHAR(255)                 DEFAULT '' COMMENT '头像',
    `CREATE_TIME`    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '创建时间',
    `UPDATE_TIME`    INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '更新时间',
    `STATUS`         TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态0:正常,1:异常',
    PRIMARY KEY (`ID`),
    KEY `IDX_UID` (`UID`),
    KEY `IDX_TYPE` (`TYPE`)
) COMMENT = '第三方平台账户';

CREATE TABLE `SYS_RESOURCE_TAB`
(
    `ID`          INT(11) UNSIGNED    NOT NULL COMMENT 'ID',
    `PARENT_ID`   INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '父菜单ID',
    `TITLE`       VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '菜单标题',
    `DESCRIPTION` VARCHAR(255)                 DEFAULT '' COMMENT '菜单描述',
    `URI`         VARCHAR(155)        NOT NULL DEFAULT '' COMMENT '菜单URI',
    `MARK`        VARCHAR(255)        NOT NULL DEFAULT '' COMMENT '菜单标识',
    `TARGET`      VARCHAR(9)          NOT NULL DEFAULT '_self' COMMENT 'url打开方式(_blank,_parent,_self,_top)',
    `icon`        VARCHAR(100)        NOT NULL DEFAULT '' COMMENT 'ICON图标',
    `PARAMS`      VARCHAR(500)                 DEFAULT '' COMMENT '菜单扩展字段',
    `IS_SHOW`     TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否展示菜单(0:展示,1:不展示)',
    `IS_REFRESH`  TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否刷新(0:刷新,1:不刷新)',
    `MENU_SORT`   INT(4)                       DEFAULT 0 COMMENT '排序',
    `TYPE`        TINYINT(1) UNSIGNED          DEFAULT 0 COMMENT '资源类型(0:API,1:菜单,...)',
    `CREATE_AT`   INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '创建时间',
    `CREATE_BY`   BIGINT(19) UNSIGNED NOT NULL COMMENT '创建人UID',
    `UPDATE_TIME` INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '更新时间',
    `UPDATE_BY`   BIGINT(19) UNSIGNED NOT NULL COMMENT '修改人UID',
    `STATUS`      TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态(0:正常,1:异常)',
    PRIMARY KEY (`ID`),
    KEY `IDX_PARENT_ID` (`PARENT_ID`),
    UNIQUE `IDX_URL` (`URI`)
) COMMENT ='系统资源';

CREATE TABLE `SYS_ROLE_TAB`
(
    `ID`          INT(11) UNSIGNED    NOT NULL COMMENT '角色ID',
    `ROLE_NAME`   VARCHAR(50)         NOT NULL COMMENT '角色名称',
    `ROLE_DESC`   VARCHAR(500)                 DEFAULT '' COMMENT '角色描述',
    `CREATE_TIME` INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '创建时间',
    `CREATE_BY`   BIGINT(19) UNSIGNED NOT NULL COMMENT '创建人UID',
    `UPDATE_TIME` INT(11) UNSIGNED    NOT NULL DEFAULT 0 COMMENT '更新时间',
    `UPDATE_BY`   BIGINT(19) UNSIGNED NOT NULL COMMENT '修改人UID',
    `STATUS`      TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '状态',
    primary key (`ID`)
) COMMENT = '角色';

CREATE TABLE `SYS_RESOURCE_ROLE_MAPPING`
(
    `RESOURCE_ID` INT(11) UNSIGNED NOT NULL COMMENT '资源ID',
    `ROLE_ID`     INT(11) UNSIGNED NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`RESOURCE_ID`, `ROLE_ID`)
) COMMENT = '系统资源和角色的关联';

CREATE TABLE `SYS_USER_ROLE_MAPPING`
(
    `ID`  INT(11)    NOT NULL COMMENT 'ID',
    `UID` BIGINT(19) NOT NULL COMMENT '用户ID',
    `RID` INT(11)    NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`ID`),
    KEY (`UID`),
    KEY (`RID`)
) COMMENT = '账户和角色的关联';

CREATE TABLE `SYS_LOG_TAB`
(
    `ID`         INT(11) NOT NULL COMMENT 'ID',
    `URI`        VARCHAR(255) DEFAULT '' COMMENT '请求URI',
    `METHOD`     VARCHAR(6)   DEFAULT '' COMMENT '请求类型(GET,POST,PUT,DELETE)',
    `BODY`       VARCHAR(500) DEFAULT '' COMMENT '请求参数BODY',
    `IP`         VARCHAR(12)  DEFAULT '' COMMENT '请求者IP',
    `START_TIME` INT(11)      DEFAULT 0 COMMENT '请求开始时间',
    `END_TIME`   INT(11)      DEFAULT 0 COMMENT '请求结束时间',
    `UID`        BIGINT(19)   DEFAULT 0 COMMENT '用户ID，未登录情况下是0',
    `STATUS`     TINYINT(1)   DEFAULT 0 COMMENT '状态,0正常，1异常',
    `EXCEPTION`  VARCHAR(100) COMMENT '失败原因',
    PRIMARY KEY (`ID`)
) COMMENT = '系统日志';

CREATE TABLE `SYS_LOGIN_LOG`
(
    `ID`         INT(11)     NOT NULL COMMENT 'ID',
    `USERNAME`   VARCHAR(16) NOT NULL COMMENT '登陆账户',
    `IP`         VARCHAR(12) DEFAULT '' COMMENT '请求者IP',
    `START_TIME` INT(11)     DEFAULT 0 COMMENT '请求开始时间',
    `END_TIME`   INT(11)     DEFAULT 0 COMMENT '请求结束时间',
    `STATUS`     TINYINT(1)  DEFAULT 0 COMMENT '状态,0正常，1异常',
    `EXCEPTION`  VARCHAR(100) COMMENT '失败原因',
    PRIMARY KEY (`ID`),
    KEY (`USERNAME`)
) comment = '登陆日志(仅存储存在的用户日志)';

CREATE TABLE `SYS_OPTIONS_TAB`
(
    `ID`    INT(11)     NOT NULL COMMENT 'ID',
    `KEY`   VARCHAR(20) NOT NULL COMMENT 'KEY',
    `VALUE` VARCHAR(255) COMMENT 'VALUE',
    PRIMARY KEY (`ID`),
    KEY (`KEY`)
) COMMENT = '系统设置';
