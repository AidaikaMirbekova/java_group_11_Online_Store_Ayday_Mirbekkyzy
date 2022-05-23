create table `orders`
(
    `id`          int not null auto_increment,
    `customer_id` int(10),
    `product_id`   int(10),
    `quantity` int(10),
    `price` float(10),
    `date`        datetime,
    `status`      varchar(50),
    primary key (`id`),
    constraint `fk_customer_order` foreign key (`customer_id`) references `user_table` (`id`),
    constraint `fk_product_order` foreign key (`product_id`) references `products` (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

