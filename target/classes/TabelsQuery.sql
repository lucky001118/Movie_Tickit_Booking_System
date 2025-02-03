
create DATABASE anudeep_movie_tickit_booking_system;
USE anudeep_movie_tickit_booking_system;
show Tables;
drop database anudeep_movie_tickit_booking_system;
-- 1️⃣ Users Table
CREATE TABLE Users (
    id      INT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    email        VARCHAR(255) UNIQUE NOT NULL,
    password     VARCHAR(255) NOT NULL,
    role         ENUM('USER', 'ADMIN') DEFAULT 'USER',
    address      VARCHAR(255),
    mobileNo     VARCHAR(100) NOT NULL
);

-- 2️⃣ Theaters Table
CREATE TABLE Theaters (
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    location     VARCHAR(255) NOT NULL
);

-- 3️⃣ Screens Table
CREATE TABLE Screens (
    id    INT AUTO_INCREMENT PRIMARY KEY,
    theater_id   INT NOT NULL,
    screen_name  VARCHAR(50) NOT NULL,
    seat_capacity INT NOT NULL,
    FOREIGN KEY (theater_id) REFERENCES Theaters(id) ON DELETE CASCADE
);

-- 4️⃣ Movies Table
CREATE TABLE Movies (
    id     INT AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    genre        VARCHAR(100) NOT NULL,
    duration     INT NOT NULL,  -- Duration in minutes
    language     VARCHAR(50) NOT NULL,
    release_date DATE NOT NULL
);

-- 5️⃣ Shows Table
CREATE TABLE Shows (
    id      INT AUTO_INCREMENT PRIMARY KEY,
    movie_id     INT NOT NULL,
    screen_id    INT NOT NULL,
    show_time    DATETIME NOT NULL,
    ticket_price DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES Movies(id) ON DELETE CASCADE,
    FOREIGN KEY (screen_id) REFERENCES Screens(id) ON DELETE CASCADE
);

-- 6️⃣ Seats Table
CREATE TABLE Seats (
    id      INT AUTO_INCREMENT PRIMARY KEY,
    screen_id    INT NOT NULL,
    seat_number  VARCHAR(10) NOT NULL,
    seat_type    ENUM('REGULAR', 'PREMIUM', 'VIP') DEFAULT 'REGULAR',
    FOREIGN KEY (screen_id) REFERENCES Screens(id) ON DELETE CASCADE
);

-- 7️⃣ Bookings Table
CREATE TABLE Bookings (
    id   INT AUTO_INCREMENT PRIMARY KEY,
    user_id      INT NOT NULL,
    show_id      INT NOT NULL,
    seat_id      INT NOT NULL,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status       ENUM('CONFIRMED', 'CANCELLED') DEFAULT 'CONFIRMED',
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (show_id) REFERENCES Shows(id) ON DELETE CASCADE,
    FOREIGN KEY (seat_id) REFERENCES Seats(id) ON DELETE CASCADE
);

-- 8️⃣ Payments Table
CREATE TABLE Payments (
    id   INT AUTO_INCREMENT PRIMARY KEY,
    booking_id   INT NOT NULL,
    amount       DECIMAL(10,2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_status ENUM('SUCCESS', 'FAILED', 'PENDING') DEFAULT 'PENDING',
    FOREIGN KEY (booking_id) REFERENCES Bookings(id) ON DELETE CASCADE
);

-- DUMMY DATA FOR ALL THE TABLES

-- 1️⃣ Insert Users
INSERT INTO Users (name, email, password, role, mobileNo) VALUES
('Alice Johnson', 'alice@example.com', 'password123', 'USER','1234567890'),
('Bob Smith', 'bob@example.com', 'password123', 'USER','1234567890'),
('Charlie Admin', 'admin@example.com', 'admin123', 'ADMIN','1234567890');

-- 2️⃣ Insert Theaters
INSERT INTO Theaters (name, location) VALUES
('PVR Cinemas', 'New York, USA'),
('INOX Multiplex', 'Los Angeles, USA'),
('Cinepolis', 'San Francisco, USA');

-- 3️⃣ Insert Screens
INSERT INTO Screens (theater_id, screen_name, seat_capacity) VALUES
(1, 'Screen 1', 100),
(1, 'Screen 2', 120),
(2, 'Screen 1', 150),
(3, 'Screen 1', 80);

-- 4️⃣ Insert Movies
INSERT INTO Movies (title, genre, duration, language, release_date) VALUES
('Inception', 'Sci-Fi', 148, 'English', '2010-07-16'),
('Avengers: Endgame', 'Action', 181, 'English', '2019-04-26'),
('Interstellar', 'Sci-Fi', 169, 'English', '2014-11-07'),
('The Dark Knight', 'Action', 152, 'English', '2008-07-18');

-- 5️⃣ Insert Shows
INSERT INTO Shows (movie_id, screen_id, show_time, ticket_price) VALUES
(1, 1, '2025-02-01 18:00:00', 12.50),
(2, 2, '2025-02-01 21:00:00', 15.00),
(3, 3, '2025-02-02 17:30:00', 13.00),
(4, 4, '2025-02-02 20:00:00', 14.50);

-- 6️⃣ Insert Seats
INSERT INTO Seats (screen_id, seat_number, seat_type) VALUES
(1, 'A1', 'REGULAR'),
(1, 'A2', 'REGULAR'),
(1, 'B1', 'PREMIUM'),
(2, 'C1', 'VIP'),
(2, 'C2', 'VIP'),
(3, 'D1', 'REGULAR'),
(4, 'E1', 'PREMIUM');

-- 7️⃣ Insert Bookings
INSERT INTO Bookings (user_id, show_id, seat_id, booking_date, status) VALUES
(1, 1, 1, '2025-01-30 12:00:00', 'CONFIRMED'),
(2, 2, 3, '2025-01-30 14:30:00', 'CONFIRMED'),
(1, 3, 6, '2025-01-31 09:45:00', 'CONFIRMED'),
(2, 4, 7, '2025-01-31 10:15:00', 'CANCELLED');

-- 8️⃣ Insert Payments
INSERT INTO Payments (booking_id, amount, payment_date, payment_status) VALUES
(1, 12.50, '2025-01-30 12:05:00', 'SUCCESS'),
(2, 15.00, '2025-01-30 14:35:00', 'SUCCESS'),
(3, 13.00, '2025-01-31 09:50:00', 'SUCCESS'),
(4, 14.50, '2025-01-31 10:20:00', 'FAILED');

desc users;
select * from users;

-- Giving the information about the movies
SELECT 
    m.id AS movie_id, 
    m.title, 
    m.genre, 
    m.duration, 
    m.language, 
    m.release_date,
    t.id AS theater_id,
    t.name AS theater_name,
    t.location AS theater_location,
    s.id AS screen_id,
    s.screen_name,
    s.seat_capacity,
    sh.id AS show_id,
    sh.show_time,
    sh.ticket_price
FROM movies m
JOIN shows sh ON m.id = sh.movie_id
JOIN screens s ON sh.screen_id = s.id
JOIN theaters t ON s.theater_id = t.id;

SELECT 
    m.title AS movie_title, 
    m.genre, 
    m.duration, 
    m.language, 
    m.release_date,
    t.name AS theater_name,
    t.location AS theater_location,
    s.screen_name,
    s.seat_capacity,
    sh.show_time,
    sh.ticket_price
FROM movies m
JOIN shows sh ON m.id = sh.movie_id
JOIN screens s ON sh.screen_id = s.id
JOIN theaters t ON s.theater_id = t.id;

