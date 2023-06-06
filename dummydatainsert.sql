-- Dummy data for travel_packages table
INSERT INTO travel_packages (packageName, passenger_capacity) VALUES
    ('Europe Tour', 10),
    ('Asia Adventure', 8);

-- Dummy data for destinations table
INSERT INTO destinations (travel_package_id, destination_name) VALUES
    (1, 'Paris'),
    (1, 'Rome'),
    (2, 'Tokyo'),
    (2, 'Bangkok');

-- Dummy data for activities table
INSERT INTO activities (destination_id, activity_name, DESCRIPTION, cost, capacity) VALUES
    (1, 'Eiffel Tower Visit', 'Guided tour of the Eiffel Tower', 25.99, 50),
    (1, 'Louvre Museum Tour', 'Art tour at the Louvre Museum', 19.99, 40),
    (2, 'Colosseum Tour', 'Guided tour of the Colosseum', 18.50, 30),
    (2, 'Vatican City Tour', 'Tour of Vatican City and St. Peter''s Basilica', 24.99, 35),
    (3, 'Sumo Wrestling Experience', 'Witness a live sumo wrestling match', 35.50, 20),
    (3, 'Shinjuku Night Tour', 'Explore the vibrant nightlife of Shinjuku', 15.99, 25),
    (4, 'Grand Palace Visit', 'Tour of the Grand Palace in Bangkok', 22.50, 30),
    (4, 'Floating Market Experience', 'Visit the famous floating markets of Bangkok', 12.99, 40);

-- Dummy data for passengers table
INSERT INTO passengers (passenger_name, passenger_number, balance, passenger_type) VALUES
    ('John Doe', 12345, 500.00, 'standard'),
    ('Jane Smith', 54321, 750.00, 'gold'),
    ('Mike Johnson', 98765, NULL, 'premium'),
    ('Sarah Lee', 11111, 350.00, 'standard'),
    ('David Brown', 22222, 900.00, 'gold');
    
