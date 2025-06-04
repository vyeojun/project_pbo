-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 04, 2025 at 05:59 AM
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
-- Database: `bebek`
--

-- --------------------------------------------------------

--
-- Table structure for table `kandang`
--

CREATE TABLE `kandang` (
  `id` int(11) NOT NULL,
  `nomor_kandang` varchar(50) NOT NULL,
  `jumlah_bebek` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kandang`
--

INSERT INTO `kandang` (`id`, `nomor_kandang`, `jumlah_bebek`) VALUES
(1, '1', 51),
(2, '2', 44),
(3, '3', 63);

-- --------------------------------------------------------

--
-- Table structure for table `produksi_telur`
--

CREATE TABLE `produksi_telur` (
  `id` int(11) NOT NULL,
  `id_kandang` int(11) NOT NULL,
  `jumlah_telur` int(11) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `produksi_telur`
--

INSERT INTO `produksi_telur` (`id`, `id_kandang`, `jumlah_telur`, `tanggal`) VALUES
(3, 1, 28, '2025-06-01');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kandang`
--
ALTER TABLE `kandang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `produksi_telur`
--
ALTER TABLE `produksi_telur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_kandang` (`id_kandang`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kandang`
--
ALTER TABLE `kandang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `produksi_telur`
--
ALTER TABLE `produksi_telur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `produksi_telur`
--
ALTER TABLE `produksi_telur`
  ADD CONSTRAINT `produksi_telur_ibfk_1` FOREIGN KEY (`id_kandang`) REFERENCES `kandang` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
