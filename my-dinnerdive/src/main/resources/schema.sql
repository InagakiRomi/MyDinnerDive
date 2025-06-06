DROP TABLE IF EXISTS restaurants;

CREATE TABLE restaurants (
    restaurant_id INT PRIMARY KEY AUTO_INCREMENT,
    restaurant_name VARCHAR(64),
    category VARCHAR(255),
    image_url VARCHAR(256),
    visited_count INT,
    last_eat TIMESTAMP,
    last_visited_at TIMESTAMP,
    note VARCHAR(512)
);