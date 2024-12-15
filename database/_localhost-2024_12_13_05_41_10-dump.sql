-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: osahan-eat
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `is_active` bit(1) DEFAULT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `username` varchar(10) NOT NULL,
  `id` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKk8h1bgqoplx0rkngj01pm1rgp` (`username`),
  KEY `FKt3wava8ssfdspnh3hg4col3m1` (`role_id`),
  CONSTRAINT `FKt3wava8ssfdspnh3hg4col3m1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (_binary '','2024-12-13 03:18:41.030000','2024-12-13 03:18:41.030000','0869885513','af295471-bfd0-4f26-928b-4e41320e7c6e','$2a$10$eP62lNW9.wkL1JqgHX4Cq.fOeDuOSmo8jqXckLrRYjWt/oBCCsZZi','MANAGER'),(_binary '','2024-12-13 03:06:03.727000','2024-12-13 03:06:03.729000','0869885512','f915c88c-401c-4be5-9a67-88ad45dd0027','$2a$10$rGpiMJlqC1cHzVGxIZ.FZeGxWGgxB6o8RcOHvAa3KnpWW0P/YlxWy','MANAGER');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
  `is_active` bit(1) DEFAULT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `quantity` bigint DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `account_id` varchar(255) DEFAULT NULL,
  `dish_id` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhu7yrgm18fpcjyw1vcimil0fl` (`account_id`),
  KEY `FKqf96jt4hthdxw36s3ebnq1yns` (`dish_id`),
  CONSTRAINT `FKhu7yrgm18fpcjyw1vcimil0fl` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`),
  CONSTRAINT `FKqf96jt4hthdxw36s3ebnq1yns` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `is_active` bit(1) DEFAULT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (_binary '','2024-12-13 04:51:44.860000','2024-12-13 04:51:44.860000','Danh mục các món nướng như thịt nướng, hải sản nướng, và rau củ nướng.','52026283-7ab1-42d7-ba9f-1a412534ca9e','roast.png','Đồ nướng'),(_binary '','2024-12-13 04:51:53.339000','2024-12-13 04:51:53.339000','Danh mục các loại trà sữa bao gồm trà sữa truyền thống, trân châu đường đen, và hồng trà sữa.','84979f55-3a0a-464f-80f1-735aa132894a','bubble-tea.png','Trà sữa'),(_binary '','2024-12-13 04:51:59.796000','2024-12-13 04:51:59.796000','Danh mục dành cho các loại lẩu như lẩu Thái, lẩu hải sản, và lẩu nấm.','8db1ea8d-11ea-4d10-9a87-70ab40e2e2f2','hot-pot.png','Lẩu'),(_binary '','2024-12-13 04:47:32.320000','2024-12-13 04:47:32.320000','Danh mục bao gồm nước ngọt, trà, cà phê, và nước ép trái cây.','dcbb7868-0e7c-4ac7-8afb-89a8c64a73a5','drink.png','Đồ uống');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dishes`
--

DROP TABLE IF EXISTS `dishes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dishes` (
  `is_active` bit(1) DEFAULT NULL,
  `is_free_ship` bit(1) DEFAULT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `time_ship` decimal(38,2) DEFAULT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `category_id` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `restaurant_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKg9v3f8f18je2t2ou8fvwse3kq` (`name`),
  KEY `FKgbu6erefir17660qutbbjnma7` (`category_id`),
  KEY `FKpslsa9mci7gsfhwukb3mx7s6n` (`restaurant_id`),
  CONSTRAINT `FKgbu6erefir17660qutbbjnma7` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `FKpslsa9mci7gsfhwukb3mx7s6n` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dishes`
--

LOCK TABLES `dishes` WRITE;
/*!40000 ALTER TABLE `dishes` DISABLE KEYS */;
INSERT INTO `dishes` VALUES (NULL,_binary '',20000.00,5.00,'2024-12-12 22:34:08.000000','2024-12-12 22:34:08.000000','dcbb7868-0e7c-4ac7-8afb-89a8c64a73a5','Nước giải khát Coca-Cola ngọt ngào, sảng khoái.','3374ce36-b8d9-11ef-8ed6-0242ac120002','coca.jpg','Coca','0d145cfc-7b86-46dc-9d9c-ce71c50bb7d6'),(NULL,_binary '',25000.00,5.00,'2024-12-12 22:34:08.000000','2024-12-12 22:34:08.000000','dcbb7868-0e7c-4ac7-8afb-89a8c64a73a5','Nước cam tươi mát, bổ sung vitamin C tự nhiên.','33767b1f-b8d9-11ef-8ed6-0242ac120002','nuoc-cam.jpg','Nước Cam','0d145cfc-7b86-46dc-9d9c-ce71c50bb7d6'),(NULL,_binary '\0',20000.00,5.00,'2024-12-12 22:34:08.000000','2024-12-12 22:34:08.000000','dcbb7868-0e7c-4ac7-8afb-89a8c64a73a5','Pepsi - nước ngọt đậm đà, sảng khoái mọi lúc mọi nơi.','33769aee-b8d9-11ef-8ed6-0242ac120002','pepsi.jpg','Pepsi','0d145cfc-7b86-46dc-9d9c-ce71c50bb7d6'),(NULL,_binary '',200000.00,15.00,'2024-12-12 22:30:41.000000','2024-12-12 22:30:41.000000','52026283-7ab1-42d7-ba9f-1a412534ca9e','Thịt nướng BBQ thơm ngon, được tẩm gia vị đặc biệt','b83cdabf-b8d8-11ef-8ed6-0242ac120002','BBQ-la-gi-01.png','BBQ Thịt Nướng','0d145cfc-7b86-46dc-9d9c-ce71c50bb7d6'),(NULL,_binary '\0',250000.00,20.00,'2024-12-12 22:30:41.000000','2024-12-12 22:30:41.000000','52026283-7ab1-42d7-ba9f-1a412534ca9e','Nầm lợn nướng thấm gia vị, ăn kèm với rau sống cuốn và sốt đặc biệt.','b84051eb-b8d8-11ef-8ed6-0242ac120002','nem-nuong-cuon-rau.jpg','Nầm Nướng Cuốn Rau','0d145cfc-7b86-46dc-9d9c-ce71c50bb7d6'),(NULL,_binary '',180000.00,18.00,'2024-12-12 22:30:41.000000','2024-12-12 22:30:41.000000','52026283-7ab1-42d7-ba9f-1a412534ca9e','Lươn nướng được ướp gia vị muối ớt cay cay, món ăn cực kỳ hấp dẫn.','b8407ae9-b8d8-11ef-8ed6-0242ac120002','Lươn Nướng Muối Ớt.jpg','Lươn Nướng Muối Ớt','0d145cfc-7b86-46dc-9d9c-ce71c50bb7d6');
/*!40000 ALTER TABLE `dishes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `is_active` bit(1) DEFAULT NULL,
  `price` decimal(38,2) NOT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `quantity` bigint NOT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `dish_id` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1f9qgvn9fugqkk40bxkgy6g7p` (`dish_id`),
  KEY `FKjyu2qbqt8gnvno9oe9j2s2ldk` (`order_id`),
  CONSTRAINT `FK1f9qgvn9fugqkk40bxkgy6g7p` FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`),
  CONSTRAINT `FKjyu2qbqt8gnvno9oe9j2s2ldk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `is_active` bit(1) DEFAULT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `phone_number` varchar(10) NOT NULL,
  `account_id` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) NOT NULL,
  `id` varchar(255) NOT NULL,
  `restaurant_id` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKagh5svlor3slbay6tq5wqor1o` (`account_id`),
  KEY `FK2m9qulf12xm537bku3jnrrbup` (`restaurant_id`),
  CONSTRAINT `FK2m9qulf12xm537bku3jnrrbup` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`id`),
  CONSTRAINT `FKagh5svlor3slbay6tq5wqor1o` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profiles`
--

DROP TABLE IF EXISTS `profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profiles` (
  `data_of_birth` date DEFAULT NULL,
  `gender` bit(1) NOT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `full_name` varchar(100) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKlnk8iosvsrn5614xw3lgnybgk` (`email`),
  CONSTRAINT `FKph94xatq3eb421xema3y7p7b8` FOREIGN KEY (`id`) REFERENCES `accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profiles`
--

LOCK TABLES `profiles` WRITE;
/*!40000 ALTER TABLE `profiles` DISABLE KEYS */;
INSERT INTO `profiles` VALUES ('2003-12-29',_binary '\0',_binary '','2024-12-13 03:18:41.047000','2024-12-13 03:18:41.047000','Nguyễn Văn Hưng','Nam Định',NULL,'nguyenhung291204@gmail.com','af295471-bfd0-4f26-928b-4e41320e7c6e'),('2003-12-29',_binary '\0',_binary '','2024-12-13 03:06:03.788000','2024-12-13 03:06:03.788000','Nguyễn Văn Hưng','Nam Định',NULL,'nguyenhung291203@gmail.com','f915c88c-401c-4be5-9a67-88ad45dd0027');
/*!40000 ALTER TABLE `profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurants`
--

DROP TABLE IF EXISTS `restaurants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurants` (
  `close_time` time(6) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `is_free_ship` bit(1) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `open_time` time(6) DEFAULT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `title` varchar(50) NOT NULL,
  `address` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `subtitle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKpm32sqdemjifotja5iqvaeqeg` (`title`),
  UNIQUE KEY `UK649xu8el35c5um9x7q7pxkhx` (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurants`
--

LOCK TABLES `restaurants` WRITE;
/*!40000 ALTER TABLE `restaurants` DISABLE KEYS */;
INSERT INTO `restaurants` VALUES ('21:00:00.000000',_binary '',_binary '',21.0374,105.8395,'09:00:00.000000','2024-12-13 05:00:23.092000','2024-12-13 05:00:23.092000','Nướng Ngon','78 Trần Phú, Hà Nội','Nướng Ngon mang đến những món nướng với gia vị đặc biệt, đảm bảo hương vị đậm đà khó quên.','0d145cfc-7b86-46dc-9d9c-ce71c50bb7d6','nuong_ngon.jpg','Thực phẩm nướng tươi ngon mỗi ngày'),('22:00:00.000000',_binary '',_binary '',21.0221,105.8499,'10:00:00.000000','2024-12-13 05:00:51.375000','2024-12-13 05:00:51.375000','Sweet Tea House','12 Quang Trung, Hà Nội','Sweet Tea House mang đến những ly trà sữa ngọt ngào, thơm mát và đặc biệt là các món tráng miệng đi kèm.','0f304fe4-b333-487c-aea1-eeb94ef8e52a','Sweet Tea House.jpg','Trà sữa ngọt ngào hảo hạng'),('22:00:00.000000',_binary '',_binary '',21.0285,105.854,'10:00:00.000000','2024-12-13 05:01:22.841000','2024-12-13 05:01:22.841000','Korean BBQ','100 Bạch Đằng, Hà Nội','Korean BBQ chuyên phục vụ món nướng kiểu Hàn với các loại thịt nướng trộn gia vị đặc biệt và rau ăn kèm.','12c5e1b6-c39d-49ab-982c-27d43d20c8fd','Korean BBQ.jpg','Nướng kiểu Hàn Quốc'),('22:00:00.000000',_binary '',_binary '',21.0285,105.8542,'10:00:00.000000','2024-12-13 04:58:39.209000','2024-12-13 04:58:39.209000','Grill Master','123 Đường Láng, Hà Nội','Grill Master chuyên cung cấp các món nướng ngon miệng từ thịt, hải sản đến rau củ.','4c4d3eab-a6e6-48fe-8277-fd9728ed8170','Grill-Yums.jpg','Thưởng thức các món nướng tuyệt vời!'),('23:00:00.000000',_binary '',_binary '\0',21.0222,105.8556,'11:00:00.000000','2024-12-13 05:00:57.133000','2024-12-13 05:00:57.133000','Hot Pot Delight','17 Nguyễn Thị Minh Khai, Hà Nội','Hot Pot Delight là nơi chuyên các món lẩu hải sản tươi ngon và lẩu nấm, thích hợp cho các buổi sum họp gia đình.','524bd054-c2b5-49c1-b69e-1436fcd0bf83','hot hot pot pot.jpg','Lẩu hải sản tươi sống'),('22:00:00.000000',_binary '',_binary '',21.0272,105.8525,'09:00:00.000000','2024-12-13 05:00:30.166000','2024-12-13 05:00:30.166000','Milk Tea Time','35 Phan Chu Trinh, Hà Nội','Trà sữa Milk Tea Time với trân châu đường đen và các loại topping hấp dẫn.','75c72d9a-b018-4090-aefe-58ff1c6222d0','Milk Tea Time.jpg','Trà sữa ngon, thơm mát mỗi ngày'),('21:30:00.000000',_binary '',_binary '\0',21.022,105.861,'08:30:00.000000','2024-12-13 05:01:44.076000','2024-12-13 05:01:44.076000','Choco Tea','88 Võ Thị Sáu, Hà Nội','Choco Tea mang đến những ly trà sữa kết hợp với chocolate, dành cho những tín đồ mê ngọt.','82a5a984-658b-4f1d-83c1-48f4292891da','Choco Tea.jpg','Trà sữa cùng chocolate ngọt ngào'),('23:00:00.000000',_binary '',_binary '\0',21.0265,105.8545,'11:00:00.000000','2024-12-13 05:00:06.710000','2024-12-13 05:00:06.710000','BBQ Heaven','45 Nguyễn Trãi, Hà Nội','BBQ Heaven chuyên các món thịt nướng và hải sản tươi sống. Hãy đến thưởng thức không khí BBQ tuyệt vời.','88a672e2-8242-4398-9389-f4da1ecffd25','BBQ Heaven.jpg','Đồ nướng đậm đà hương vị truyền thống'),('22:30:00.000000',_binary '',_binary '',21.0331,105.8513,'10:00:00.000000','2024-12-13 05:01:03.963000','2024-12-13 05:01:03.963000','Lẩu Nấm','30 Lý Thường Kiệt, Hà Nội','Lẩu Nấm nổi tiếng với các món lẩu nấm phong phú, có thể kết hợp với các loại thịt tươi ngon.','bc163ba9-55a9-49d1-b3e5-63b12849977d','quán lẩu nấm.jpg','Khám phá các món lẩu đặc biệt'),('22:30:00.000000',_binary '',_binary '',21.043,105.8272,'10:30:00.000000','2024-12-13 05:01:50.043000','2024-12-13 05:01:50.043000','The Hot Pot','20 Ngọc Hà, Hà Nội','The Hot Pot cung cấp món lẩu đặc biệt, từ lẩu gà, lẩu cá đến lẩu thập cẩm cùng nhiều loại gia vị hấp dẫn.','cc9618d9-f132-4da3-8455-ff82d3e0e6f4','hotpot-story.jpg','Lẩu ngon thịnh soạn'),('23:00:00.000000',_binary '',_binary '\0',21.0205,105.856,'08:00:00.000000','2024-12-13 05:00:35.737000','2024-12-13 05:00:35.737000','Bubble Tea World','55 Lê Hồng Phong, Hà Nội','Bubble Tea World cung cấp trà sữa với đa dạng hương vị từ matcha đến cacao và trân châu khổng lồ.','e2a79867-b0d9-4f54-9e43-17c1d6d0010f','Bubble Tea World','Những ly trà sữa độc đáo'),('23:00:00.000000',_binary '',_binary '\0',21.028,105.8585,'09:00:00.000000','2024-12-13 05:01:13.111000','2024-12-13 05:01:13.111000','Lẩu Thái Xịn','10 Đinh Tiên Hoàng, Hà Nội','Lẩu Thái Xịn phục vụ món lẩu Thái với nước lẩu đậm đà, chua cay khó quên.','f02fb931-9d96-4529-b451-cc599bcd6bb0','Lẩu Thái Xịn.jpg','Trải nghiệm lẩu Thái ngon khó cưỡng');
/*!40000 ALTER TABLE `restaurants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `code` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('CUSTOMER','Khách hàng'),('EMPLOYEE','Nhân viên'),('IT_ADMIN','Quản trị viên'),('MANAGER','Quản lý');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tokens`
--

DROP TABLE IF EXISTS `tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tokens` (
  `expired` bit(1) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `revoked` bit(1) DEFAULT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `expires_at` datetime(6) DEFAULT NULL,
  `refresh_token_expires_at` datetime(6) DEFAULT NULL,
  `update_at` datetime(6) DEFAULT NULL,
  `account_id` varchar(255) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `token` varchar(255) NOT NULL,
  `token_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`token`),
  KEY `FKkxd4xsavefdtlpkvnxpgojn6` (`account_id`),
  CONSTRAINT `FKkxd4xsavefdtlpkvnxpgojn6` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tokens`
--

LOCK TABLES `tokens` WRITE;
/*!40000 ALTER TABLE `tokens` DISABLE KEYS */;
INSERT INTO `tokens` VALUES (_binary '\0',_binary '',_binary '\0','2024-12-13 03:09:41.696000','2024-12-13 06:09:41.651155','2024-12-13 07:09:41.651155','2024-12-13 03:09:41.696000','f915c88c-401c-4be5-9a67-88ad45dd0027','f4e75480-f139-43ad-a956-8f7bce41bd2b','eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIwODY5ODg1NTEyIiwiaWF0IjoxNzM0MDM0MTgxLCJleHAiOjE3MzQwNDQ5ODF9.ODi_C6hpNsi80MPwf8Y2jK6UUTsGEAiLurs_zyLB7WEZvnC-Y3EFhVehzn0BzAyT','Bearer'),(_binary '\0',_binary '',_binary '','2024-12-13 03:18:46.539000','2024-12-13 06:18:46.521246','2024-12-13 07:18:46.522245','2024-12-13 03:27:14.573000','f915c88c-401c-4be5-9a67-88ad45dd0027','eff0a52c-dfd6-48a0-930b-6b7fc9b77b3e','eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIwODY5ODg1NTEyIiwiaWF0IjoxNzM0MDM0NzI2LCJleHAiOjE3MzQwNDU1MjZ9.JJFvVcrioC9pvXnjCf_V91ikaG7byfw_g0YlRNQwPg8K-mlD1endEM6b0VbXvHUm','Bearer'),(_binary '\0',_binary '',_binary '\0','2024-12-13 03:06:08.130000','2024-12-13 06:06:08.101387','2024-12-13 07:06:08.101387','2024-12-13 03:06:08.130000','f915c88c-401c-4be5-9a67-88ad45dd0027','3581b543-6a96-4e51-8e68-f9e332e4584b','eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIwODY5ODg1NTEyIiwiaWF0IjoxNzM0MDMzOTY4LCJleHAiOjE3MzQwNDQ3Njh9.SBYoZ8ZbPYoxDriBo6dpyVv9lu0zvE88z0UZFZcTc_OUg0PGOwUs2sUz5zhrhsRo','Bearer'),(_binary '\0',_binary '',_binary '\0','2024-12-13 04:47:30.031000','2024-12-13 07:47:29.927941','2024-12-13 08:47:29.927941','2024-12-13 04:47:30.031000','f915c88c-401c-4be5-9a67-88ad45dd0027','69eda3e5-9431-4be2-a0cb-a9258722d569','eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIwODY5ODg1NTEyIiwiaWF0IjoxNzM0MDQwMDQ5LCJleHAiOjE3MzQwNTA4NDl9.OXXrSnPBD002ryqgc_ZiuqnMN3wl1Z0pgGpNugVCCqYPJa9XLNdk2QWrIamciaE8','Bearer');
/*!40000 ALTER TABLE `tokens` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-13  5:41:11
