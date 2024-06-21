create table if not exists t_data_config
(
    id         integer,
    data_name  TEXT,
    data_value text,
    type       integer
);

create table if not exists t_data_group
(
    id          integer
        constraint t_data_group_pk
            primary key autoincrement,
    group_num   integer
        constraint t_data_group_pk_2
            unique,
    server_name text,
    bot_switch  text default '[]',
    bot_num     integer
);

