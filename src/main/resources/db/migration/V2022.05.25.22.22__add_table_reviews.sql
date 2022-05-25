create table `reviews`
(
    `id`          int not null auto_increment,
    `user_id` int (10),
    `order_id`   int (10),
    `review` varchar (500),
    `date` datetime,
    primary key (`id`),
    constraint `fk_user_review` foreign key (`user_id`) references `user_table` (`id`),
    constraint `fk_order_review` foreign key (`order_id`) references `orders` (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;