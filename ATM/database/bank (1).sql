-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 14, 2025 at 05:58 PM
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
-- Database: `bank`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `getBalance` (IN `cardNo` BIGINT, OUT `bal` DOUBLE)   select balance into bal from login where card_number = cardNo$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `bank_detail`
--

CREATE TABLE `bank_detail` (
  `b_id` int(11) NOT NULL,
  `card_number` bigint(20) NOT NULL,
  `transaction_date` varchar(50) NOT NULL,
  `transaction_type` varchar(50) NOT NULL,
  `transaction_amount` double NOT NULL,
  `acc_balance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bank_detail`
--

INSERT INTO `bank_detail` (`b_id`, `card_number`, `transaction_date`, `transaction_type`, `transaction_amount`, `acc_balance`) VALUES
(1, 1618585636301719, 'Sat Aug 24 13:06:26 IST 2024', 'Deposit', 1000, 11000),
(2, 1618585636301719, 'Sat Aug 24 13:09:07 IST 2024', 'Withdraw', 1000, 10000),
(3, 1618585636301719, 'Sat Aug 24 13:09:11 IST 2024', 'Withdraw', 2000, 8000),
(4, 1618585636301719, 'Mon Sep 01 21:00:51 IST 2025', 'Deposit', 2000, 10000),
(5, 1618585636301719, 'Mon Sep 01 21:01:02 IST 2025', 'Withdraw', 100, 9900),
(6, 9743071678048472, 'Sun Sep 14 19:07:57 IST 2025', 'Deposit', 1000, 11000),
(7, 9743071678048472, 'Sun Sep 14 19:09:24 IST 2025', 'Withdraw', 100, 10900);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL,
  `form_number` int(11) NOT NULL,
  `card_number` bigint(20) NOT NULL,
  `pin_number` int(11) NOT NULL,
  `balance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`login_id`, `form_number`, `card_number`, `pin_number`, `balance`) VALUES
(1, 4811, 1618585636301719, 5723, 9900),
(2, 5119, 9743071678048472, 7410, 10900);

-- --------------------------------------------------------

--
-- Table structure for table `signupone`
--

CREATE TABLE `signupone` (
  `form_number` int(11) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `father_or_husband_name` varchar(50) NOT NULL,
  `dob` varchar(15) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `marital_status` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL,
  `city` varchar(30) NOT NULL,
  `state` varchar(30) NOT NULL,
  `pincode` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `signupone`
--

INSERT INTO `signupone` (`form_number`, `full_name`, `father_or_husband_name`, `dob`, `gender`, `email`, `marital_status`, `address`, `city`, `state`, `pincode`) VALUES
(4811, 'Tanisha Kavani', 'Rajubhai Kavani', '12-May-2006', 'female', 'kavanitanisha@gmail.com', 'unmarried', 'Varachha', 'Surat', 'Gujarat', 365874),
(5119, 'fefvr', 'fvrdgv', '15/09/2025', 'female', 'vserv@kfr.fef', 'unmarried', 'ergvrg', 'grgvr', 'grg', 484526);

-- --------------------------------------------------------

--
-- Table structure for table `signuptwo`
--

CREATE TABLE `signuptwo` (
  `form_number` int(11) NOT NULL,
  `education` varchar(20) NOT NULL,
  `occupation` varchar(20) NOT NULL,
  `income` varchar(20) NOT NULL,
  `religion` varchar(20) NOT NULL,
  `category` varchar(20) NOT NULL,
  `pan_number` varchar(30) NOT NULL,
  `aadhar_number` bigint(20) NOT NULL,
  `card_number` bigint(20) NOT NULL,
  `pin_number` int(11) NOT NULL,
  `facility` varchar(200) NOT NULL,
  `acc_type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `signuptwo`
--

INSERT INTO `signuptwo` (`form_number`, `education`, `occupation`, `income`, `religion`, `category`, `pan_number`, `aadhar_number`, `card_number`, `pin_number`, `facility`, `acc_type`) VALUES
(4811, 'Graduate', 'Student', 'Null', 'Hindu', 'General', 'abc145xyz', 785412369025, 1618585636301719, 5723, 'ATM Card, ', 'Saving Account'),
(5119, 'Non-Graduate', 'Self-Employed', 'Null', 'Hindu', 'General', 'BUHIJ7415D', 741258963258, 9743071678048472, 7410, 'ATM Card, Internet Banking, Cheque Book', 'Current Account');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bank_detail`
--
ALTER TABLE `bank_detail`
  ADD PRIMARY KEY (`b_id`),
  ADD KEY `card_number` (`card_number`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`login_id`),
  ADD KEY `form_number` (`form_number`),
  ADD KEY `card_number` (`card_number`);

--
-- Indexes for table `signupone`
--
ALTER TABLE `signupone`
  ADD PRIMARY KEY (`form_number`);

--
-- Indexes for table `signuptwo`
--
ALTER TABLE `signuptwo`
  ADD PRIMARY KEY (`card_number`),
  ADD KEY `form_number` (`form_number`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bank_detail`
--
ALTER TABLE `bank_detail`
  MODIFY `b_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `login_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bank_detail`
--
ALTER TABLE `bank_detail`
  ADD CONSTRAINT `bank_detail_ibfk_1` FOREIGN KEY (`card_number`) REFERENCES `signuptwo` (`card_number`);

--
-- Constraints for table `login`
--
ALTER TABLE `login`
  ADD CONSTRAINT `login_ibfk_1` FOREIGN KEY (`form_number`) REFERENCES `signupone` (`form_number`),
  ADD CONSTRAINT `login_ibfk_2` FOREIGN KEY (`card_number`) REFERENCES `signuptwo` (`card_number`);

--
-- Constraints for table `signuptwo`
--
ALTER TABLE `signuptwo`
  ADD CONSTRAINT `signuptwo_ibfk_1` FOREIGN KEY (`form_number`) REFERENCES `signupone` (`form_number`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
