-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 04, 2020 at 09:44 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `zgjedhjet`
--

-- --------------------------------------------------------

--
-- Table structure for table `kandidati`
--

CREATE TABLE `kandidati` (
  `id` int(11) NOT NULL,
  `emri_kandidatit` varchar(30) NOT NULL,
  `mosha` varchar(30) NOT NULL,
  `data_regjistrimit` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kandidati`
--

INSERT INTO `kandidati` (`id`, `emri_kandidatit`, `mosha`, `data_regjistrimit`) VALUES
(1, 'Kandidati1', '30', '2020-01-01'),
(2, 'Kandidati2', '30', '2020-01-01'),
(3, 'Kandidati3', '30', '2020-01-01'),
(4, 'Kandidati4', '30', '2020-01-01'),
(5, 'Kandidati5', '30', '2020-01-01'),
(6, 'Kandidati6', '30', '2020-01-01'),
(7, 'Kandidati7', '30', '2020-01-01'),
(8, 'Kandidati8', '30', '2020-01-01'),
(10, 'Kandidati10 :P', '30', '2020-01-01');

-- --------------------------------------------------------

--
-- Table structure for table `lajmet`
--

CREATE TABLE `lajmet` (
  `id` int(11) NOT NULL,
  `titulli_lajmit` varchar(50) NOT NULL,
  `pershkrimi_lajmit` varchar(400) NOT NULL,
  `data_lajmit` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lajmet`
--

INSERT INTO `lajmet` (`id`, `titulli_lajmit`, `pershkrimi_lajmit`, `data_lajmit`) VALUES
(1, 'Përfundon afati për aplikim për certifikim të subj', 'Prishtinë, 17 shkurt 2020 – Komisioni Qendror i Zgjedhjeve njofton se ka përfunduar afati për aplikim për certifikim të subjekteve politike që duan të marrin pjesë në zgjedhjet e jashtëzakonshme për kryetar të komunës së Podujevës që do të mbahen më 15 mars 2020.', '2020-05-11'),
(2, 'KQZ kufizon shpenzimet e subjekteve politike për f', 'Në këtë mbledhje, KQZ mori vendim për kufizimin e shpenzimeve të fushatës zgjedhore për subjektet politike që do të marrin pjesë në zgjedhjet e jashtëzakonshme për kryetar të komunës së Podujevës.\r\n\r\nNëpërmjet këtij vendimi, Komisioni Qendror i Zgjedhjeve përcakton kufirin e sipërm të shpenzimeve të fushatës zgjedhore për subjektet politike që do të certifikohen për pjesëmarrje në këto zgjedhje, n', '2020-11-03'),
(3, 'test', 'test', '2019-12-12'),
(4, 'test', 'test', '2019-12-12'),
(5, 'test', 'test', '2019-12-12'),
(6, 'test', 'test', '2019-12-12');

-- --------------------------------------------------------

--
-- Table structure for table `partia`
--

CREATE TABLE `partia` (
  `id` int(11) NOT NULL,
  `emri_partis` varchar(30) NOT NULL,
  `logo_partis` varchar(30) NOT NULL,
  `data_regjistrimit` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `partia`
--

INSERT INTO `partia` (`id`, `emri_partis`, `logo_partis`, `data_regjistrimit`) VALUES
(1, 'Partia Molla', 'test logo', '2020-01-01'),
(2, 'Partia Dardha', 'test2 logo', '2020-01-01'),
(3, 'Partia Qershia', 'test3 logo', '2020-01-01');

-- --------------------------------------------------------

--
-- Table structure for table `votimi`
--

CREATE TABLE `votimi` (
  `id` int(11) NOT NULL,
  `partia` varchar(50) DEFAULT NULL,
  `kandidati1` varchar(50) DEFAULT NULL,
  `kandidati2` varchar(50) DEFAULT NULL,
  `kandidati3` varchar(50) DEFAULT NULL,
  `kandidati4` varchar(50) DEFAULT NULL,
  `kandidati5` varchar(50) DEFAULT NULL,
  `data_votes` varchar(30) DEFAULT NULL,
  `statusi` varchar(10) DEFAULT NULL,
  `numri_zgjedhjeve` varchar(30) DEFAULT NULL,
  `vendi` varchar(50) DEFAULT NULL,
  `komuna` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `votimi`
--

INSERT INTO `votimi` (`id`, `partia`, `kandidati1`, `kandidati2`, `kandidati3`, `kandidati4`, `kandidati5`, `data_votes`, `statusi`, `numri_zgjedhjeve`, `vendi`, `komuna`) VALUES
(1, 'Partia 1', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'Partia 1', 'Kandidati 2', 'Kandidati 4', 'Kandidati 6', 'Kandidati 8', 'Kandidati 9', NULL, NULL, NULL, NULL, NULL),
(3, 'partia', 'kandidati1', 'kandidati2', 'kandidati3', 'kandidati4', 'kandidati5', 'data_votes', 'statusi', NULL, NULL, NULL),
(4, 'partia', 'kandidati1', 'kandidati2', 'kandidati3', 'kandidati4', 'kandidati5', NULL, NULL, NULL, NULL, NULL),
(5, 'Partia 1', 'Kandidati 3', 'Kandidati 1', 'Kandidati 10', 'Kandidati 7', 'Kandidati 8', NULL, NULL, NULL, NULL, NULL),
(6, 'Partia 1', 'Kandidati 1', 'Kandidati 2', 'Kandidati 3', 'Kandidati 4', 'Kandidati 5', '2020-01-01', NULL, NULL, NULL, NULL),
(7, 'Partia 1', 'Kandidati 1', 'Kandidati 2', 'Kandidati 3', 'Kandidati 4', 'Kandidati 5', '2020-01-01', '', NULL, NULL, NULL),
(8, '', '', '', '', '', '', '', '', NULL, NULL, NULL),
(9, 'Partia x', 'Kandidati x', 'Kandidati x', 'Kandidati x', 'Kandidati x', 'Kandidati x', '2020-12-12', 'statusi', '', '', ''),
(10, 'Partia x', 'Kandidati x', 'Kandidati x', 'Kandidati x', 'Kandidati x', 'Kandidati x', '2020-12-12', 'statusi', '123456', 'test', 'test'),
(11, 'Partia x', 'Kandidati x', 'Kandidati x', 'Kandidati x', 'Kandidati x', 'Kandidati x', '2020-12-12', 'statusi', '3124124', 'test', 'Prishtine'),
(12, '', '', '', '', '', '', '2020-12-12', 'statusi', '', '', ''),
(13, 'Partia 2', 'Kandidati 3', 'Kandidati 1', 'Kandidati 4', 'Kandidati 3', 'Kandidati 7', '2020-12-12', 'Po', '3124124', 'Prishtinë', 'Prishtinë'),
(14, 'Partia 2', 'Kandidati 3', 'Kandidati 1', 'Kandidati 4', 'Kandidati 3', 'Kandidati 7', '2020-12-12', 'Po', '3124124', 'Prishtinë', 'Prishtinë'),
(15, '', '', '', '', '', '', '2020-12-12', '', '', '', ''),
(16, 'Partia 2', 'Kandidati 3', 'Kandidati x', 'Kandidati x', 'Kandidati x', 'Kandidati x', '2020-12-12', 'Po', '3124124', 'test', 'Prishtine'),
(17, 'Partia 2', 'Kandidati 3', 'Kandidati 5', 'Kandidati 3', 'Kandidati 3', 'Kandidati 5', '2020-12-12', 'Po', '3124124', 'test', 'Prishtine'),
(18, 'Partia 3', 'Kandidati 1', 'Kandidati 9', 'Kandidati 1', 'Kandidati 2', 'Kandidati 7', '2020-12-12', 'Po', '3124124', 'test', 'Prishtinë'),
(19, 'Partia 2', 'Kandidati 1', 'Kandidati 2', 'Kandidati 3', 'Kandidati 4', 'Kandidati 5', '2020-12-12', 'Po', '3124124', 'albii', 'Drenas'),
(20, 'Partia 2', 'Kandidati 1', 'Kandidati 2', 'Kandidati 4', 'Kandidati 5', 'Kandidati 5', '2020-12-12', 'Po', '3124124', 'Prishtine', 'Prishtinë'),
(21, 'Partia 2', 'Kandidati 1', 'Kandidati 2', 'Kandidati 4', 'Kandidati 5', 'Kandidati 4', '2020-12-12', 'Po', '3124124', 'Prishtine', 'Prishtinë'),
(22, 'Partia 3', 'Kandidati 1', 'Kandidati 2', 'Kandidati 3', 'Kandidati 4', 'Kandidati 5', '2020-12-12', 'Po', '3124124', 'Prishtine', 'Prishtinë');

-- --------------------------------------------------------

--
-- Table structure for table `votuesi`
--

CREATE TABLE `votuesi` (
  `id` int(11) NOT NULL,
  `emri` varchar(50) DEFAULT NULL,
  `mbiemri` varchar(50) NOT NULL,
  `emaili` varchar(50) NOT NULL,
  `fjalkalimi` varchar(50) NOT NULL,
  `adresa` varchar(50) NOT NULL,
  `vendi` varchar(50) NOT NULL,
  `komuna` varchar(50) NOT NULL,
  `numri_leternjoftimit` varchar(50) NOT NULL,
  `data_lindjes` date NOT NULL,
  `data_regjistrimit` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `votuesi`
--

INSERT INTO `votuesi` (`id`, `emri`, `mbiemri`, `emaili`, `fjalkalimi`, `adresa`, `vendi`, `komuna`, `numri_leternjoftimit`, `data_lindjes`, `data_regjistrimit`) VALUES
(1, 'Filaniiyy', 'Fisteku', 'test', 'test', 'test', 'testtt', 'Prishtinë', 'hh', '2020-05-09', '2020-05-09'),
(25, 'Filan', 'Fisteku', 'test@gmail.com', 'test', 'Rruga Filan Fisteku', 'Prishtinë', 'Prishtinë', '839839273729', '2019-02-11', '2020-05-14'),
(26, 'tiki', 'tiki', 'tiki', 'tiki', 'tiki', 'tiki', 'Prishtinë', '12355', '0000-00-00', '2020-05-25'),
(27, 'h', 'b', 'n', 'nyy', 'n', 'n', 'Prishtinë', 'bb', '0000-00-00', '2020-06-14'),
(28, 'albi', 'albi', 'albi', 'albi', 'albi', 'albii', 'Drenas', '27737373', '0000-00-00', '2020-06-16'),
(31, 'Vedat', 'Muriqi', 'vedatm@gmail.com', 'vedatf2', 'Rruga xxx', 'Prishtine', 'Prishtinë', '7374829483987', '0000-00-00', '2020-10-30');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kandidati`
--
ALTER TABLE `kandidati`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lajmet`
--
ALTER TABLE `lajmet`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `partia`
--
ALTER TABLE `partia`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `votimi`
--
ALTER TABLE `votimi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `votuesi`
--
ALTER TABLE `votuesi`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kandidati`
--
ALTER TABLE `kandidati`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `lajmet`
--
ALTER TABLE `lajmet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `partia`
--
ALTER TABLE `partia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `votimi`
--
ALTER TABLE `votimi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `votuesi`
--
ALTER TABLE `votuesi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
