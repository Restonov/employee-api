CREATE TABLE IF NOT EXISTS `categories` (
    `id` int NOT NULL PRIMARY KEY,
    `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `employees` (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(50) NOT NULL,
    `password` varchar(500),
    `category_id` INT,
    foreign key (category_id) references categories (id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;