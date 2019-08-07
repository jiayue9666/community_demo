create table QUESTION
(
    ID            INTEGER primary key auto_increment,
    TITLE         VARCHAR(50),
    DESCRIPTION   CLOB,
    GMT_CREATE    BIGINT,
    GMT_MODIFIED  BIGINT,
    CREATOR       INTEGER,
    COMMENT_COUNT INTEGER default 0,
    VIEW_COUNT    INTEGER default 0,
    LIKE_COUNT    INTEGER default 0,
    TAG           VARCHAR(256),

);
