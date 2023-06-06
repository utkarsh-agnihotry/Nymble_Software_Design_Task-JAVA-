CREATE TABLE travel_packages (
    id INT PRIMARY KEY AUTO_INCREMENT,
    packageName VARCHAR(100) NOT NULL,
    passenger_capacity INT NOT NULL
);


CREATE TABLE destinations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    travel_package_id INT NOT NULL,
    destination_name VARCHAR(100) NOT NULL,
    FOREIGN KEY (travel_package_id) REFERENCES travel_packages(id)
);

CREATE TABLE activities (
    id INT PRIMARY KEY AUTO_INCREMENT,
    destination_id INT NOT NULL,
    activity_name VARCHAR(100) NOT NULL,
    DESCRIPTION VARCHAR(255) NOT NULL,
    cost DECIMAL(10, 2) NOT NULL,
    capacity INT NOT NULL,
    FOREIGN KEY (destination_id) REFERENCES destinations(id)
);
CREATE TABLE passengers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    travel_package_id INT NOT NULL,
    passenger_name VARCHAR(100) NOT NULL,
    passenger_number INT NOT NULL,
    balance DECIMAL(10, 2),
    passenger_type ENUM('standard', 'gold', 'premium') NOT NULL,
    FOREIGN KEY (travel_package_id) REFERENCES travel_packages(id)
);


