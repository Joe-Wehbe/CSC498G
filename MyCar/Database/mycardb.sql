-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 15, 2022 at 10:18 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mycardb`
--

-- --------------------------------------------------------

--
-- Table structure for table `cars`
--

CREATE TABLE `cars` (
  `id` int(11) NOT NULL,
  `brand` varchar(45) NOT NULL,
  `model` int(4) NOT NULL,
  `color` varchar(45) NOT NULL,
  `plate` varchar(45) NOT NULL,
  `engine_oil` int(11) NOT NULL,
  `engine_coolant` int(11) NOT NULL,
  `transmission_fluid` int(11) NOT NULL,
  `power_steering_fluid` int(11) NOT NULL,
  `breaks_fluid` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cars`
--

INSERT INTO `cars` (`id`, `brand`, `model`, `color`, `plate`, `engine_oil`, `engine_coolant`, `transmission_fluid`, `power_steering_fluid`, `breaks_fluid`, `user_id`) VALUES
(8, 'Nissan', 2018, 'Black', '9382754', 100, 100, 100, 100, 100, 17),
(15, 'Kia', 2021, 'White', '4783923', 100, 76, 84, 88, 76, 17),
(16, 'Mercedes', 2022, 'Black', '7778889', 71, 93, 96, 97, 93, 17),
(17, 'BMW', 2017, 'Black', '3728769', 89, 98, 99, 99, 98, 19),
(18, 'Range Rover', 2016, 'Gray', '2094826', 69, 93, 95, 97, 93, 19),
(19, 'Jeep', 2011, 'Black', '2893675', 100, 100, 100, 100, 100, 17);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `country`, `email`, `password`) VALUES
(17, 'Joe', 'Wehbe', 'Lebanon', 'joewehbe@hotmail.com', '8cb2237d0679ca88db6464eac60da96345513964'),
(19, 'John', 'John', 'USA', 'john@gmail.com', '011c945f30ce2cbafc452f39840f025693339c42'),
(20, 'Jason', 'smith', 'USA', 'jason.smith@yahoo.com', '7b21848ac9af35be0ddb2d6b9fc3851934db8420');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cars`
--
ALTER TABLE `cars`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
