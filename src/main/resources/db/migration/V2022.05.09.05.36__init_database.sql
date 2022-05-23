use `online_store`;

create table `user_table`
(
    `id`       INT         not null auto_increment,
    `name`     varchar(20),
    `login`    varchar(20),
    `email`    varchar(50),
    `password` varchar(60),
    `enabled`  boolean     NOT NULL default true,
    `role`     varchar(50) not null default "USER",
    primary key (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


create table `products`
(   `id`          INT not null auto_increment,
    `name`        varchar(20),
    `image`       varchar(100),
    `quantity`    int(10),
    `description` varchar(500),
    `price`       float (10),
    primary key (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into products (`name`, `image`, `quantity`, `description`, `price`)
values ('ASUS', '1.jpg', '500', 'Notebook ASUS', '54000.50');
insert into products (`name`, `image`, `quantity`, `description`, `price`)
values ('lenovo', '4.jpg', '694', 'Notebook Lenovo', '36500');
insert into products (`name`, `image`, `quantity`, `description`, `price`)
values ('HP', '3.jpg', '180', 'Notebook HP', '45500');
insert into products (`name`, `image`, `quantity`, `description`, `price`)
values ('Samsung', '5.jpg', '236', 'Notebook Lenovo', '36500');
insert into products (`name`, `image`, `quantity`, `description`, `price`)
values ('Dell', '6.jpg', '89', 'Notebook Dell', '45921');
insert into products (`name`, `image`, `quantity`, `description`, `price`)
values ('Xiomi', '7.jpg', '45', 'Notebook Xiomi', '50000');
insert into products (`name`, `image`, `quantity`, `description`, `price`)
values ('Acer', '1.jpg', '582', 'Notebook Acer', '159902');