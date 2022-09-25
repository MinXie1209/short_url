create table long_short_url_relation
(
    id          bigint auto_increment comment '自增id' primary key,
    long_url    varchar(255) not null comment '长链',
    short_url   varchar(255) not null comment '短链',
    create_time datetime     not null default current_timestamp comment '创建时间',
    update_time datetime     not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '长短链映射表' engine = innodb;

create unique index uk_long_url_short_url
    on long_short_url_relation (long_url, short_url) comment '唯一键';
create index idx_short_url
    on long_short_url_relation (short_url) comment '索引';


