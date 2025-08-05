-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 29, 2023 at 01:12 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `resortfrontdesk`
--

-- --------------------------------------------------------

--
-- Table structure for table `guestcheckin`
--

CREATE TABLE `guestcheckin` (
  `GuestID` int(250) NOT NULL,
  `name` varchar(250) NOT NULL,
  `phnum` int(12) NOT NULL,
  `email` varchar(250) NOT NULL,
  `room` varchar(14) NOT NULL,
  `checkin` date NOT NULL,
  `checkout` date NOT NULL,
  `checkinTime` time(4) NOT NULL,
  `checkoutTime` time(4) NOT NULL,
  `isDaytour` tinyint(1) NOT NULL,
  `isOvernight` tinyint(1) NOT NULL,
  `isSeniorCitizen` tinyint(1) NOT NULL,
  `isPWD` tinyint(1) NOT NULL,
  `totalcost` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `guestcheckin`
--

INSERT INTO `guestcheckin` (`GuestID`, `name`, `phnum`, `email`, `room`, `checkin`, `checkout`, `checkinTime`, `checkoutTime`, `isDaytour`, `isOvernight`, `isSeniorCitizen`, `isPWD`, `totalcost`) VALUES
(24, 'John Doe', 89326, 'johndoe@gmail.com', 'Room1', '2023-11-30', '2023-12-01', '07:30:00.0000', '07:00:00.0000', 0, 1, 1, 0, 4620);

-- --------------------------------------------------------

--
-- Table structure for table `guestcheckinlogs`
--

CREATE TABLE `guestcheckinlogs` (
  `GuestID` int(250) NOT NULL,
  `name` varchar(250) NOT NULL,
  `phnum` int(12) NOT NULL,
  `email` varchar(250) NOT NULL,
  `room` varchar(14) NOT NULL,
  `checkin` date NOT NULL,
  `checkout` date NOT NULL,
  `checkinTime` time(4) NOT NULL,
  `checkoutTime` time(4) NOT NULL,
  `isDaytour` tinyint(1) NOT NULL,
  `isOvernight` tinyint(1) NOT NULL,
  `isSeniorCitizen` tinyint(1) NOT NULL,
  `isPWD` tinyint(1) NOT NULL,
  `totalcost` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `guestcheckinlogs`
--

INSERT INTO `guestcheckinlogs` (`GuestID`, `name`, `phnum`, `email`, `room`, `checkin`, `checkout`, `checkinTime`, `checkoutTime`, `isDaytour`, `isOvernight`, `isSeniorCitizen`, `isPWD`, `totalcost`) VALUES
(24, 'John Doe', 89326, 'johndoe@gmail.com', 'Room1', '2023-11-30', '2023-12-01', '07:30:00.0000', '07:00:00.0000', 0, 1, 1, 0, 4620);

-- --------------------------------------------------------

--
-- Table structure for table `guestreservation`
--

CREATE TABLE `guestreservation` (
  `ReservationID` int(250) NOT NULL,
  `name` varchar(250) NOT NULL,
  `phnum` int(12) NOT NULL,
  `email` varchar(250) NOT NULL,
  `room` varchar(14) NOT NULL,
  `checkin` date NOT NULL,
  `checkout` date NOT NULL,
  `checkinTime` time(4) NOT NULL,
  `checkoutTime` time(4) NOT NULL,
  `isDaytour` tinyint(1) NOT NULL,
  `isOvernight` tinyint(1) NOT NULL,
  `isSeniorCitizen` tinyint(1) NOT NULL,
  `isPWD` tinyint(1) NOT NULL,
  `totalCost` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `guestreservation`
--

INSERT INTO `guestreservation` (`ReservationID`, `name`, `phnum`, `email`, `room`, `checkin`, `checkout`, `checkinTime`, `checkoutTime`, `isDaytour`, `isOvernight`, `isSeniorCitizen`, `isPWD`, `totalCost`) VALUES
(15, 'Jane Doe', 278313, 'janedoe@gmail.com', 'Room3', '2023-12-02', '2023-12-03', '09:30:00.0000', '10:00:00.0000', 0, 1, 0, 0, 9000);

-- --------------------------------------------------------

--
-- Table structure for table `guestreservationlogs`
--

CREATE TABLE `guestreservationlogs` (
  `ReservationID` int(250) NOT NULL,
  `name` varchar(250) NOT NULL,
  `phnum` int(12) NOT NULL,
  `email` varchar(250) NOT NULL,
  `room` varchar(14) NOT NULL,
  `checkin` date NOT NULL,
  `checkout` date NOT NULL,
  `checkinTime` time(4) NOT NULL,
  `checkoutTime` time(4) NOT NULL,
  `isDaytour` tinyint(1) NOT NULL,
  `isOvernight` tinyint(1) NOT NULL,
  `isSeniorCitizen` tinyint(1) NOT NULL,
  `isPWD` tinyint(1) NOT NULL,
  `totalCost` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `guestreservationlogs`
--

INSERT INTO `guestreservationlogs` (`ReservationID`, `name`, `phnum`, `email`, `room`, `checkin`, `checkout`, `checkinTime`, `checkoutTime`, `isDaytour`, `isOvernight`, `isSeniorCitizen`, `isPWD`, `totalCost`) VALUES
(14, 'John Cruz', 737827, 'johncruz@gmail.com', 'Room2', '2023-12-02', '2023-12-02', '09:30:00.0000', '17:30:00.0000', 1, 0, 0, 1, 2765),
(15, 'Jane Doe', 278313, 'janedoe@gmail.com', 'Room3', '2023-12-02', '2023-12-03', '09:30:00.0000', '10:00:00.0000', 0, 1, 0, 0, 9000);

-- --------------------------------------------------------

--
-- Table structure for table `guestwalkin`
--

CREATE TABLE `guestwalkin` (
  `WalkIn_ID` int(250) NOT NULL,
  `name` varchar(250) NOT NULL,
  `phnum` int(12) NOT NULL,
  `address` varchar(250) NOT NULL,
  `isadultratings` tinyint(1) NOT NULL,
  `iskidratings` tinyint(1) NOT NULL,
  `daytimeswim` tinyint(1) NOT NULL,
  `nighttimeswim` tinyint(1) NOT NULL,
  `isSeniorCitizen` tinyint(1) NOT NULL,
  `isPWD` tinyint(1) NOT NULL,
  `totalCost` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `guestwalkin`
--

INSERT INTO `guestwalkin` (`WalkIn_ID`, `name`, `phnum`, `address`, `isadultratings`, `iskidratings`, `daytimeswim`, `nighttimeswim`, `isSeniorCitizen`, `isPWD`, `totalCost`) VALUES
(1, 'Juan Dela Cruz', 1337, '1337', 1, 0, 1, 0, 0, 0, 200),
(2, 'Kyle Tecson', 907251235, '138 Timog Avenue, Quezon City', 1, 0, 0, 1, 0, 0, 230),
(3, 'Spencer Mercado', 9267507, '68 Dr. Sixto Ave, Metro Manila, Pasig', 1, 0, 1, 0, 1, 0, 190),
(4, 'Nimrod Castro', 905705, 'nimrodcastro@gmail.com', 0, 1, 0, 1, 0, 1, 133);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `guestcheckin`
--
ALTER TABLE `guestcheckin`
  ADD PRIMARY KEY (`GuestID`);

--
-- Indexes for table `guestcheckinlogs`
--
ALTER TABLE `guestcheckinlogs`
  ADD PRIMARY KEY (`GuestID`);

--
-- Indexes for table `guestreservation`
--
ALTER TABLE `guestreservation`
  ADD PRIMARY KEY (`ReservationID`);

--
-- Indexes for table `guestreservationlogs`
--
ALTER TABLE `guestreservationlogs`
  ADD PRIMARY KEY (`ReservationID`);

--
-- Indexes for table `guestwalkin`
--
ALTER TABLE `guestwalkin`
  ADD PRIMARY KEY (`WalkIn_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `guestcheckin`
--
ALTER TABLE `guestcheckin`
  MODIFY `GuestID` int(250) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `guestcheckinlogs`
--
ALTER TABLE `guestcheckinlogs`
  MODIFY `GuestID` int(250) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `guestreservation`
--
ALTER TABLE `guestreservation`
  MODIFY `ReservationID` int(250) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `guestreservationlogs`
--
ALTER TABLE `guestreservationlogs`
  MODIFY `ReservationID` int(250) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `guestwalkin`
--
ALTER TABLE `guestwalkin`
  MODIFY `WalkIn_ID` int(250) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
