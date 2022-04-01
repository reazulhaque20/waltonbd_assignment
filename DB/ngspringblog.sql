-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 22, 2022 at 07:42 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.4.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ngspringblog`
--

-- --------------------------------------------------------

--
-- Table structure for table `blogComments`
--

CREATE TABLE `blogComments` (
  `blogComment_id` bigint(20) NOT NULL,
  `post_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `comment` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `blog_comments`
--

CREATE TABLE `blog_comments` (
  `blog_comments_id` bigint(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `id` bigint(20) NOT NULL,
  `content` longtext DEFAULT NULL,
  `created_on` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `content`, `created_on`, `title`, `updated_on`, `username`, `status`) VALUES
(1, 'TEST', '2022-01-18 23:54:30', 'TEST', '2022-01-18 23:54:30', 'reazulhaque20', 'submit'),
(2, 'Dummy Content', '2022-01-19 19:06:00', 'Dummy Title', '2022-01-19 19:06:00', 'reazulhaque20', 'approved'),
(3, 'Dummy Content1', '2022-01-19 19:06:53', 'Dummy Title1', '2022-01-19 19:06:53', 'reazulhaque20', 'delete'),
(4, 'Dummy Content2', '2022-01-19 19:18:18', 'Dummy Title2', '2022-01-19 19:18:21', 'reazulhaque20', 'Active'),
(5, 'Dummy Content3', '2022-01-19 22:40:12', 'Dummy Title3', '2022-01-19 22:40:12', 'reazulhaque20', 'inactive'),
(6, 'Dummy Content4', '2022-01-19 22:47:09', 'Dummy Title4', '2022-01-19 22:47:09', 'reazulhaque20', 'inactive'),
(7, 'Dummy Content4', '2022-01-19 22:48:06', 'Dummy Title4', '2022-01-19 22:48:06', 'reazulhaque20', 'inactive');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `roles` varchar(10) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `user_name`, `roles`, `status`) VALUES
(1, 'admin@loc.com', '$2a$10$CSiCVmpmHxFY4ZhcnJtZHeumYwBcBlGFuHCfw2b1OdxarALnRbeIK', 'reazulhaque20', 'admin', 'active'),
(2, 'blogger@loc.com', '$2a$10$UlKcXqmGVBNK6t3LL7PeheKbztRp9jvSfVv.f2cufMhc3xOCeNKji', 'blogger', 'user', 'rere'),
(3, 'blog_xyz.com', '123', 'blogger', NULL, NULL),
(4, 'xyz@loc.com', '$2a$10$.ZLD4Pzk87FaBEu/Gh/wUeWWrP./YA7siU1DBskLPlGFIYqcwPg/q', 'xyz', 'user', 'active');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `blogComments`
--
ALTER TABLE `blogComments`
  ADD PRIMARY KEY (`blogComment_id`);

--
-- Indexes for table `blog_comments`
--
ALTER TABLE `blog_comments`
  ADD PRIMARY KEY (`blog_comments_id`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `blogComments`
--
ALTER TABLE `blogComments`
  MODIFY `blogComment_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `blog_comments`
--
ALTER TABLE `blog_comments`
  MODIFY `blog_comments_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
