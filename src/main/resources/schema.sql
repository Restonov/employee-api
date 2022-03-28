CREATE TABLE categories
(
    id   INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(50)     NOT NULL
);

CREATE TABLE employees
(
    id          INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name        VARCHAR(50)     NOT NULL,
    password    VARCHAR(500),
    category_id INT,
    foreign key (category_id) references categories (id)
);

INSERT INTO categories (name)
VALUES ('Admin');

INSERT INTO employees (name, password, category_id)
VALUES ('Admin', '$2a$10$zRnpvN8Y2JA6t/6kK.6EvOqfKtizsd5PLvT22iN0GmJJsxs2STJ1q', 1);
