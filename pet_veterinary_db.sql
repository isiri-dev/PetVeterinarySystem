-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 28, 2026 at 07:59 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pet_veterinary_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `appointment_id` int(11) NOT NULL,
  `pet_id` int(11) NOT NULL,
  `vet_id` int(11) NOT NULL,
  `appointment_date` datetime NOT NULL,
  `service_description` varchar(255) NOT NULL,
  `service_fee` decimal(10,2) NOT NULL DEFAULT 0.00,
  `status` varchar(30) NOT NULL DEFAULT 'Scheduled'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`appointment_id`, `pet_id`, `vet_id`, `appointment_date`, `service_description`, `service_fee`, `status`) VALUES
(1, 1, 1, '2026-10-20 10:00:00', 'General Checkup', 1550.00, 'Scheduled'),
(2, 1, 1, '2026-10-25 10:00:00', 'General Checkup', 1500.00, 'Pending'),
(9, 13, 4, '2026-10-28 09:00:00', 'General Health Check', 1800.00, 'Scheduled'),
(10, 14, 5, '2026-10-28 10:00:00', 'Vaccination', 2500.00, 'Scheduled'),
(11, 15, 6, '2026-10-28 11:00:00', 'Skin Examination', 2200.00, 'Pending'),
(12, 16, 3, '2026-10-28 12:00:00', 'Routine Checkup', 1500.00, 'Scheduled'),
(13, 17, 4, '2026-10-29 09:30:00', 'Dental Examination', 3000.00, 'Pending'),
(14, 18, 5, '2026-10-29 10:30:00', 'Vaccination', 2500.00, 'Scheduled'),
(15, 19, 6, '2026-10-29 11:30:00', 'General Health Check', 1800.00, 'Completed'),
(16, 20, 1, '2026-10-30 09:00:00', 'Nutritional Consultation', 2000.00, 'Completed'),
(17, 17, 4, '2026-12-02 23:00:00', 'need to check my dog', 1550.00, 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `customer_id` int(11) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customer_id`, `full_name`, `phone`, `email`, `address`) VALUES
(1, 'Test Customer1', '0771234567', 'test@example.com', 'Galle,Karapitiya'),
(2, 'Test Customer2', '0723456789', 'test2@example.com', 'Colombo'),
(4, 'Nethmi Silva', '0712345678', 'nethmi@example.com', 'Galle,Karapitiya'),
(5, 'Kasun Perera', '0774567890', 'kasun@example.com', 'Karapitiya'),
(6, 'Amaya Fernando', '0763456789', 'amaya@example.com', 'Matara'),
(7, 'Dinuka Jayasinghe', '0755678901', 'dinuka@example.com', 'Colombo'),
(8, 'Tharushi Peris', '0786789012', 'tharushi@example.com', 'Hikkaduwa'),
(9, 'Sachini Gunawardena', '0707890123', 'sachini@example.com', 'Galle'),
(10, 'Pasindu Wijesinghe', '0748901234', 'pasindu@example.com', 'Unawatuna'),
(11, 'Kavindu Senanayake', '0729012345', 'kavindu@example.com', 'Ambalangoda');

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `payment_id` int(11) NOT NULL,
  `appointment_id` int(11) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `payment_date` datetime NOT NULL DEFAULT current_timestamp(),
  `payment_method` varchar(30) NOT NULL,
  `payment_status` varchar(30) NOT NULL DEFAULT 'Paid'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`payment_id`, `appointment_id`, `amount`, `payment_date`, `payment_method`, `payment_status`) VALUES
(13, 1, 2000.00, '2026-10-21 16:27:09', 'Card', 'Paid'),
(14, 2, 2500.00, '2026-10-21 16:27:35', 'Bank Transfer', 'Pending'),
(16, 9, 1800.00, '2026-10-26 09:00:00', 'Cash', 'Paid'),
(17, 10, 2500.00, '2026-10-26 10:00:00', 'Card', 'Paid'),
(18, 11, 2200.00, '2026-10-26 11:00:00', 'Bank Transfer', 'Pending'),
(19, 12, 1500.00, '2026-10-26 12:00:00', 'Cash', 'Paid'),
(20, 13, 3000.00, '2026-10-26 13:00:00', 'Card', 'Pending'),
(21, 14, 2500.00, '2026-10-26 14:00:00', 'Cash', 'Paid'),
(22, 15, 1800.00, '2026-10-26 15:00:00', 'Bank Transfer', 'Paid'),
(23, 16, 2000.00, '2026-10-26 16:00:00', 'Card', 'Paid');

-- --------------------------------------------------------

--
-- Table structure for table `pets`
--

CREATE TABLE `pets` (
  `pet_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `pet_name` varchar(100) NOT NULL,
  `species` varchar(50) NOT NULL,
  `breed` varchar(100) DEFAULT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pets`
--

INSERT INTO `pets` (`pet_id`, `customer_id`, `pet_name`, `species`, `breed`, `age`) VALUES
(1, 1, 'Milo', 'Dog', 'Labrador', 5),
(13, 2, 'Bella', 'Dog', 'Golden Retriever', 3),
(14, 2, 'Luna', 'Cat', 'Persian', 2),
(15, 4, 'Rocky', 'Dog', 'German Shepherd', 4),
(16, 4, 'Coco', 'Bird', 'Cockatiel', 1),
(17, 5, 'Max', 'Dog', 'Beagle', 5),
(18, 5, 'Misty', 'Cat', 'Siamese', 2),
(19, 6, 'Charlie', 'Dog', 'Pomeranian', 3),
(20, 7, 'Oreo', 'Cat', 'British Shorthair', 4),
(21, 8, 'Kiwi', 'Bird', 'Parakeet', 1),
(22, 9, 'Daisy', 'Dog', 'Labrador', 2),
(23, 10, 'Snowy', 'Rabbit', 'Lionhead', 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('Admin','Veterinarian') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `vet_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `role`, `created_at`, `vet_id`) VALUES
(1, 'admin', 'admin123', 'Admin', '2026-10-21 09:14:39', NULL),
(2, 'vet', 'vet123', 'Veterinarian', '2026-10-21 09:14:39', 1);

-- --------------------------------------------------------

--
-- Table structure for table `veterinarians`
--

CREATE TABLE `veterinarians` (
  `vet_id` int(11) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `specialization` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `veterinarians`
--

INSERT INTO `veterinarians` (`vet_id`, `full_name`, `phone`, `specialization`) VALUES
(1, 'Dr. Silva', '0771234567', 'Animal Surgery'),
(3, 'Dr.Ajith Kumara', '0755422907', 'Animal Nutrition'),
(4, 'Dr. Nadeesha Perera', '0714567890', 'General Veterinary Medicine'),
(5, 'Dr. Kasun Fernando', '0765678901', 'Animal Dentistry'),
(6, 'Dr. Tharindu Silva', '0756789012', 'Dermatology');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`appointment_id`),
  ADD KEY `pet_id` (`pet_id`),
  ADD KEY `vet_id` (`vet_id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`payment_id`),
  ADD KEY `appointment_id` (`appointment_id`);

--
-- Indexes for table `pets`
--
ALTER TABLE `pets`
  ADD PRIMARY KEY (`pet_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `veterinarians`
--
ALTER TABLE `veterinarians`
  ADD PRIMARY KEY (`vet_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `appointment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `pets`
--
ALTER TABLE `pets`
  MODIFY `pet_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `veterinarians`
--
ALTER TABLE `veterinarians`
  MODIFY `vet_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`pet_id`),
  ADD CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`vet_id`) REFERENCES `veterinarians` (`vet_id`);

--
-- Constraints for table `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`appointment_id`) REFERENCES `appointments` (`appointment_id`);

--
-- Constraints for table `pets`
--
ALTER TABLE `pets`
  ADD CONSTRAINT `pets_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
