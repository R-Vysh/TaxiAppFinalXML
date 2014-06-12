CREATE TABLE cars(
    car_id integer IDENTITY primary key,
    model varchar(20),
    brand varchar(20),
    year smallint,
    registrational_number varchar(8),
    price_per_kilometer double
);
ALTER TABLE cars ADD CONSTRAINT registrational_number_uq UNIQUE (registrational_number);

create table users (
    user_id integer identity primary key,
	  password VARCHAR(30),
	  mobile VARCHAR(13),
	  is_taxist BIT(1),
	  created TIMESTAMP default current_timestamp,
	  username VARCHAR(30)
);
ALTER TABLE users ADD CONSTRAINT mobile_uq UNIQUE (mobile);
ALTER TABLE users ADD CONSTRAINT username_uq UNIQUE (username);

create table customers (
  customer_id integer identity primary key,
  order_id integer,
  user_id integer
);
ALTER TABLE customers ADD CONSTRAINT customers_order_id_fk FOREIGN KEY (order_id) REFERENCES orders (order_id) ON UPDATE CASCADE ON DELETE SET NULL;
ALTER TABLE customers ADD CONSTRAINT customers_user_id_fk FOREIGN KEY (user_id) REFERENCES users (user_id) ON UPDATE CASCADE ON DELETE CASCADE;

CREATE TABLE orders (
order_id integer identity primary key,
taxist_id integer,
customer_id integer not null,
address_from VARCHAR(71) NOT NULL,
address_to VARCHAR(71) NOT NULL,
coordinates_from integer,
coordinates_to integer,
created TIMESTAMP default current_timestamp,
total_price DOUBLE,
status VARCHAR (15) NOT NULL,
is_blamed BIT(1)
);

CONSTRAINT `orders_coordinates_from_fk` FOREIGN KEY (`coordinates_from`) REFERENCES `coordinates` (`coordinates_id`),
CONSTRAINT `orders_coordinates_to_fk` FOREIGN KEY (`coordinates_to`) REFERENCES `coordinates` (`coordinates_id`),
CONSTRAINT `orders_customer_id_fk` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
CONSTRAINT `orders_taxist_id_fk` FOREIGN KEY (`taxist_id`) REFERENCES `taxists` (`taxist_id`) ON UPDATE CASCADE ON DELETE SET NULL
