create table `tokens`
(
    `id`          int not null auto_increment,
    `user_id` int (20),
    `token`   varchar (100),
    primary key (`id`),
    constraint `fk_user_token` foreign key (`user_id`) references `user_table` (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;