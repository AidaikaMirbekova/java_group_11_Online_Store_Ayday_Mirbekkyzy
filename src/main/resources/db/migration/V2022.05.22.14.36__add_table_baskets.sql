
create table `baskets`(
                         `id` int not null auto_increment,
                         `customers_id` int(10),
                         `products_id` int(10),
                         `quantity` int(10),
                         `price` int (10),
                         primary key (`id`),
                         constraint `fk_customer_busket` foreign key (`customers_id`) references `user_table` (`id`),
                         constraint `fk_products_basket` foreign key (`products_id`) references `products` (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;