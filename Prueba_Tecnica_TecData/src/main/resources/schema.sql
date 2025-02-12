CREATE TABLE IF NOT EXISTS `PRICES` (
  `brand_id` int AUTO_INCREMENT  PRIMARY KEY,
  `start_date` varchar(100) NOT NULL,
  `end_date` varchar(100) NOT NULL,
  `price_list` int,
  `product_id` int,
  `priority` int,
  `price` int,
  `curr` varchar(100) NOT NULL
);