use `online_store`;

create table `user_table`
(
    `id`       INT         not null auto_increment,
    `name`     varchar(20) not null default 'username',
    `login`    varchar(20) not null default 'userlogin',
    `email`    varchar(50) not null default 'useremail',
    `password` varchar(60) not null default 'password',
    primary key (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


create table `products`
(
    `id` int not null auto_increment,
    `name` varchar(20),
    `image` varchar(100),
    `quantity` int(10),
    `description` varchar(500),
    `price` int(10) ,
    primary key (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


create table `orders`(
    `id` int not null auto_increment,
    `customer_id` int(10),
    `product_id` int(10),
    `quantity` int(10),
    primary key (`id`),
    constraint `fk_customer` foreign key (`customer_id`) references `user_table` (`id`),
    constraint `fk_products` foreign key (`product_id`) references `products` (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;