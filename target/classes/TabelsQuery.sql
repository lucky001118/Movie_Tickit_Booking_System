
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

select * from seats;
desc seats;

select * from screens;

select * from theaters;
update  theaters set location = 'Bhilai' where id = 1;


-- know the available seats of any specific mavie title
SELECT 
    s.seat_number,
    s.seat_type,
    sc.screen_name,
    m.title AS movie_name,
    sh.show_time,
    sc.screen_name as screen
FROM 
    seats s
INNER JOIN 
    screens sc ON s.screen_id = sc.id
INNER JOIN 
    shows sh ON sc.id = sh.screen_id
INNER JOIN 
    movies m ON sh.movie_id = m.id
WHERE 
    m.title = 'Interstellar'
    AND s.id NOT IN (
        SELECT seat_id 
        FROM bookings 
        WHERE show_id = sh.id
    )
ORDER BY 
    sh.show_time, s.seat_number;


-- dummy values for the seat table
INSERT INTO seats (screen_id, seat_number, seat_type) VALUES
-- Screen 1
(1, 'A1', 'REGULAR'),
(1, 'A2', 'REGULAR'),
(1, 'A3', 'PREMIUM'),
(1, 'A4', 'PREMIUM'),
(1, 'A5', 'VIP'),
(1, 'A6', 'REGULAR'),
(1, 'B1', 'REGULAR'),
(1, 'B2', 'PREMIUM'),
(1, 'B3', 'PREMIUM'),
(1, 'B4', 'VIP'),
(1, 'B5', 'VIP'),
(1, 'C1', 'REGULAR'),
(1, 'C2', 'REGULAR'),
(1, 'C3', 'PREMIUM'),
(1, 'C4', 'PREMIUM'),
(1, 'C5', 'VIP'),
(1, 'C6', 'REGULAR'),
(1, 'D1', 'REGULAR'),
(1, 'D2', 'PREMIUM'),
(1, 'D3', 'VIP'),
(1, 'D4', 'REGULAR'),
(1, 'D5', 'PREMIUM'),
(1, 'E1', 'REGULAR'),
(1, 'E2', 'REGULAR'),
(1, 'E3', 'PREMIUM'),
(1, 'E4', 'VIP'),
(1, 'E5', 'VIP'),
(1, 'F1', 'REGULAR'),
(1, 'F2', 'REGULAR'),
(1, 'F3', 'PREMIUM'),
(1, 'F4', 'VIP'),
(1, 'F5', 'PREMIUM'),
(1, 'G1', 'REGULAR'),
(1, 'G2', 'VIP'),
(1, 'G3', 'PREMIUM'),
(1, 'G4', 'REGULAR'),
(1, 'G5', 'PREMIUM'),
(1, 'H1', 'REGULAR'),
(1, 'H2', 'REGULAR'),
(1, 'H3', 'PREMIUM'),
(1, 'H4', 'VIP'),

-- Screen 2
(2, 'A1', 'REGULAR'),
(2, 'A2', 'REGULAR'),
(2, 'A3', 'PREMIUM'),
(2, 'A4', 'VIP'),
(2, 'A5', 'REGULAR'),
(2, 'B1', 'REGULAR'),
(2, 'B2', 'PREMIUM'),
(2, 'B3', 'VIP'),
(2, 'B4', 'REGULAR'),
(2, 'B5', 'PREMIUM'),
(2, 'C1', 'REGULAR'),
(2, 'C2', 'REGULAR'),
(2, 'C3', 'PREMIUM'),
(2, 'C4', 'PREMIUM'),
(2, 'C5', 'VIP'),
(2, 'C6', 'REGULAR'),
(2, 'D1', 'REGULAR'),
(2, 'D2', 'PREMIUM'),
(2, 'D3', 'VIP'),
(2, 'D4', 'REGULAR'),
(2, 'E1', 'REGULAR'),
(2, 'E2', 'PREMIUM'),
(2, 'E3', 'VIP'),
(2, 'E4', 'REGULAR'),
(2, 'E5', 'PREMIUM'),
(2, 'F1', 'REGULAR'),
(2, 'F2', 'VIP'),
(2, 'F3', 'PREMIUM'),
(2, 'F4', 'REGULAR'),
(2, 'F5', 'VIP'),
(2, 'G1', 'REGULAR'),
(2, 'G2', 'REGULAR'),
(2, 'G3', 'PREMIUM'),
(2, 'G4', 'VIP'),
(2, 'G5', 'PREMIUM'),
(2, 'H1', 'REGULAR'),
(2, 'H2', 'VIP'),
(2, 'H3', 'PREMIUM'),
(2, 'H4', 'REGULAR'),

-- Screen 3
(3, 'A1', 'REGULAR'),
(3, 'A2', 'PREMIUM'),
(3, 'A3', 'VIP'),
(3, 'A4', 'REGULAR'),
(3, 'A5', 'PREMIUM'),
(3, 'B1', 'REGULAR'),
(3, 'B2', 'REGULAR'),
(3, 'B3', 'PREMIUM'),
(3, 'B4', 'VIP'),
(3, 'B5', 'REGULAR'),
(3, 'C1', 'REGULAR'),
(3, 'C2', 'REGULAR'),
(3, 'C3', 'PREMIUM'),
(3, 'C4', 'PREMIUM'),
(3, 'C5', 'VIP'),
(3, 'D1', 'REGULAR'),
(3, 'D2', 'PREMIUM'),
(3, 'D3', 'VIP'),
(3, 'D4', 'REGULAR'),
(3, 'E1', 'REGULAR'),
(3, 'E2', 'PREMIUM'),
(3, 'E3', 'VIP'),
(3, 'E4', 'REGULAR'),
(3, 'E5', 'PREMIUM'),
(3, 'F1', 'REGULAR'),
(3, 'F2', 'VIP'),
(3, 'F3', 'PREMIUM'),
(3, 'F4', 'REGULAR'),
(3, 'G1', 'REGULAR'),
(3, 'G2', 'PREMIUM'),
(3, 'G3', 'VIP'),
(3, 'G4', 'REGULAR'),
(3, 'H1', 'REGULAR'),
(3, 'H2', 'VIP'),

-- Screen 4
(4, 'A1', 'REGULAR'),
(4, 'A2', 'REGULAR'),
(4, 'A3', 'PREMIUM'),
(4, 'A4', 'VIP'),
(4, 'B1', 'REGULAR'),
(4, 'B2', 'PREMIUM'),
(4, 'B3', 'VIP'),
(4, 'B4', 'REGULAR'),
(4, 'C1', 'REGULAR'),
(4, 'C2', 'PREMIUM'),
(4, 'C3', 'VIP'),
(4, 'C4', 'REGULAR'),
(4, 'D1', 'REGULAR'),
(4, 'D2', 'PREMIUM'),
(4, 'D3', 'VIP'),
(4, 'D4', 'REGULAR'),
(4, 'E1', 'REGULAR'),
(4, 'E2', 'PREMIUM'),
(4, 'E3', 'VIP'),
(4, 'E4', 'REGULAR');

-- booking
select * from movies;
select * from bookings;
select * from payments;

-- booking records by user id
SELECT 
    b.id AS booking_id,
    u.name AS user_name,
    u.email AS user_email,
    m.title AS movie_title,
    m.genre AS movie_genre,
    m.language AS movie_language,
    s.show_time,
    s.ticket_price,
    seats.seat_number,
    seats.seat_type,
    b.booking_date,
    b.status
FROM 
    bookings b
JOIN 
    users u ON b.user_id = u.id
JOIN 
    shows s ON b.show_id = s.id
JOIN 
    movies m ON s.movie_id = m.id
JOIN 
    seats ON b.seat_id = seats.id
WHERE 
    b.user_id = 1;
    
    -- search seat id by giving it movie title and seat number of the wosh 
    SELECT 
    s.id AS seat_id
FROM 
    seats s
INNER JOIN 
    screens sc ON s.screen_id = sc.id
INNER JOIN 
    shows sh ON sc.id = sh.screen_id
INNER JOIN 
    movies m ON sh.movie_id = m.id
WHERE 
    m.title = 'Inception'
    AND s.seat_number = 'A1'
    AND sh.id NOT IN (
        SELECT b.show_id 
        FROM bookings b 
        WHERE b.seat_id = s.id
    )
ORDER BY 
    sh.show_time, s.seat_number;
    
    
    -- searching the show id by using the movie title
    SELECT 
    sh.id AS show_id
FROM 
    shows sh
INNER JOIN 
    movies m ON sh.movie_id = m.id
INNER JOIN 
    screens sc ON sh.screen_id = sc.id
WHERE 
    m.title = 'Interstellar'
ORDER BY 
    sh.show_time;

-- booking
desc bookings;



