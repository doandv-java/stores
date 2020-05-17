/*
 Navicat Premium Data Transfer

 Source Server         : PostgresSQL
 Source Server Type    : PostgreSQL
 Source Server Version : 120001
 Source Host           : localhost:5432
 Source Catalog        : doandv-java
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 120001
 File Encoding         : 65001

 Date: 18/05/2020 01:00:16
*/


-- ----------------------------
-- Sequence structure for advertises_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."advertises_id_seq";
CREATE SEQUENCE "public"."advertises_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for categories_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."categories_id_seq";
CREATE SEQUENCE "public"."categories_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for order_detail_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."order_detail_id_seq";
CREATE SEQUENCE "public"."order_detail_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for orders_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."orders_id_seq";
CREATE SEQUENCE "public"."orders_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for products_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."products_id_seq";
CREATE SEQUENCE "public"."products_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for supplies_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."supplies_id_seq";
CREATE SEQUENCE "public"."supplies_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for users_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."users_id_seq";
CREATE SEQUENCE "public"."users_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for warehouse_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."warehouse_id_seq";
CREATE SEQUENCE "public"."warehouse_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for advertises
-- ----------------------------
DROP TABLE IF EXISTS "public"."advertises";
CREATE TABLE "public"."advertises" (
  "id" int4 NOT NULL GENERATED ALWAYS AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
),
  "content" varchar(255) COLLATE "pg_catalog"."default",
  "image_link" varchar(255) COLLATE "pg_catalog"."default",
  "active" int2
)
;

-- ----------------------------
-- Records of advertises
-- ----------------------------
INSERT INTO "public"."advertises" VALUES (4, 'Lenovo giảm giá', '/upload/1588955209-800-170-800x170-44.png', 1);
INSERT INTO "public"."advertises" VALUES (5, 'Tháng Acer', '/upload/1588955226-thang-acer-800-170-800x170.png', 1);
INSERT INTO "public"."advertises" VALUES (6, 'Laptop chất', '/upload/1588955257-800-300-800x300.gif', 1);
INSERT INTO "public"."advertises" VALUES (8, 'Acer Predator', '/upload/1588955380-800-170-800x170-1.png', 1);
INSERT INTO "public"."advertises" VALUES (3, 'Intel thế hệ mới', '/upload/1588955142-800-170-800x170-(39).png', 0);
INSERT INTO "public"."advertises" VALUES (7, 'MacBook Gift', '/upload/1588955287-macbook-800-170-800x170.png', 1);

-- ----------------------------
-- Table structure for categories
-- ----------------------------
DROP TABLE IF EXISTS "public"."categories";
CREATE TABLE "public"."categories" (
  "id" int4 NOT NULL GENERATED ALWAYS AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
),
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "active" int2,
  "detail" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of categories
-- ----------------------------
INSERT INTO "public"."categories" VALUES (4, 'Office', 0, 'Office Gaming');
INSERT INTO "public"."categories" VALUES (6, 'Graphics', 0, 'Dòng máy đồ họa');
INSERT INTO "public"."categories" VALUES (8, 'Science', 0, 'Dòng máy khoa học có cấu hình cao yêu cầu với các cấu hình cao đáp ứng nhu cầu tính toán lớn');
INSERT INTO "public"."categories" VALUES (9, 'Bussiness', 0, 'Dòng máy sang trọng cung cấp cho các doanh nhân với cấu hình ổn ngoại hình bắt mắt');
INSERT INTO "public"."categories" VALUES (10, 'Developer', 0, 'Laptop dùng cho các develop');
INSERT INTO "public"."categories" VALUES (5, 'Study', 0, 'Dòng máy dùng cho công việc học tập và văn phòng');
INSERT INTO "public"."categories" VALUES (12, 'Test1236', 1, 'Test');
INSERT INTO "public"."categories" VALUES (1, 'Test', 1, 'Tesdfsdf');
INSERT INTO "public"."categories" VALUES (2, 'ABC', 1, 'Tesst');
INSERT INTO "public"."categories" VALUES (13, 'Test 123', 1, 'Test');
INSERT INTO "public"."categories" VALUES (11, 'Machine Learning', 1, 'Máy tính dòng machine learning');
INSERT INTO "public"."categories" VALUES (7, 'Gaming', 1, 'Dòng máy thích hợp cho các gamer chơi game ');
INSERT INTO "public"."categories" VALUES (3, 'Gaming', 1, 'Gaming');
INSERT INTO "public"."categories" VALUES (14, 'Gaming', 0, 'Dòng laptop có cấu hình cao để có thể chơi các game có cấu hình nặng');
INSERT INTO "public"."categories" VALUES (15, '123', 1, '12');

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS "public"."order_detail";
CREATE TABLE "public"."order_detail" (
  "id" int4 NOT NULL GENERATED ALWAYS AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
),
  "product_id" int4,
  "order_id" int4,
  "quantity" int4
)
;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO "public"."order_detail" VALUES (3, 4, 2, 1);
INSERT INTO "public"."order_detail" VALUES (2, 3, 2, 1);
INSERT INTO "public"."order_detail" VALUES (38, 4, 21, 12);
INSERT INTO "public"."order_detail" VALUES (39, 7, 22, 1);
INSERT INTO "public"."order_detail" VALUES (40, 8, 22, 1);
INSERT INTO "public"."order_detail" VALUES (41, 9, 23, 1);
INSERT INTO "public"."order_detail" VALUES (5, 2, 2, 1);
INSERT INTO "public"."order_detail" VALUES (4, 5, 2, 2);
INSERT INTO "public"."order_detail" VALUES (42, 4, 24, 1);
INSERT INTO "public"."order_detail" VALUES (43, 5, 24, 1);
INSERT INTO "public"."order_detail" VALUES (8, 2, 4, 3);
INSERT INTO "public"."order_detail" VALUES (44, 5, 25, 5);
INSERT INTO "public"."order_detail" VALUES (45, 5, 26, 1);
INSERT INTO "public"."order_detail" VALUES (6, 4, 3, 3);
INSERT INTO "public"."order_detail" VALUES (9, 6, 3, 3);
INSERT INTO "public"."order_detail" VALUES (10, 3, 5, 1);
INSERT INTO "public"."order_detail" VALUES (11, 8, 5, 1);
INSERT INTO "public"."order_detail" VALUES (12, 6, 6, 2);
INSERT INTO "public"."order_detail" VALUES (13, 7, 6, 2);
INSERT INTO "public"."order_detail" VALUES (14, 4, 6, 1);
INSERT INTO "public"."order_detail" VALUES (46, 4, 27, 5);
INSERT INTO "public"."order_detail" VALUES (16, 3, 8, 2);
INSERT INTO "public"."order_detail" VALUES (17, 4, 8, 1);
INSERT INTO "public"."order_detail" VALUES (18, 2, 8, 1);
INSERT INTO "public"."order_detail" VALUES (19, 5, 8, 1);
INSERT INTO "public"."order_detail" VALUES (20, 7, 8, 1);
INSERT INTO "public"."order_detail" VALUES (47, 4, 28, 1);
INSERT INTO "public"."order_detail" VALUES (48, 6, 28, 1);
INSERT INTO "public"."order_detail" VALUES (49, 6, 29, 1);
INSERT INTO "public"."order_detail" VALUES (50, 7, 29, 1);
INSERT INTO "public"."order_detail" VALUES (51, 10, 29, 1);
INSERT INTO "public"."order_detail" VALUES (52, 13, 29, 1);
INSERT INTO "public"."order_detail" VALUES (53, 5, 29, 1);
INSERT INTO "public"."order_detail" VALUES (54, 15, 29, 1);
INSERT INTO "public"."order_detail" VALUES (55, 16, 30, 4);
INSERT INTO "public"."order_detail" VALUES (56, 13, 31, 3);
INSERT INTO "public"."order_detail" VALUES (22, 6, 9, 17);
INSERT INTO "public"."order_detail" VALUES (21, 5, 9, 17);
INSERT INTO "public"."order_detail" VALUES (25, 4, 12, 1);
INSERT INTO "public"."order_detail" VALUES (24, 4, 11, 2);
INSERT INTO "public"."order_detail" VALUES (26, 9, 13, 1);
INSERT INTO "public"."order_detail" VALUES (27, 9, 14, 1);
INSERT INTO "public"."order_detail" VALUES (30, 4, 17, 1);
INSERT INTO "public"."order_detail" VALUES (29, 6, 16, 5);
INSERT INTO "public"."order_detail" VALUES (31, 5, 16, 1);
INSERT INTO "public"."order_detail" VALUES (33, 9, 18, 1);
INSERT INTO "public"."order_detail" VALUES (34, 6, 18, 1);
INSERT INTO "public"."order_detail" VALUES (36, 4, 20, 1);
INSERT INTO "public"."order_detail" VALUES (37, 5, 20, 2);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS "public"."orders";
CREATE TABLE "public"."orders" (
  "id" int4 NOT NULL GENERATED ALWAYS AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
),
  "user_id" int4,
  "status" int4,
  "create_day" timestamp(6),
  "last_update" timestamp(6),
  "total" int8
)
;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO "public"."orders" VALUES (14, 16, 3, '2020-05-17 01:25:42.346', '2020-05-17 01:25:42.346', 800);
INSERT INTO "public"."orders" VALUES (28, 22, 2, '2020-05-18 00:40:53.863', '2020-05-18 00:40:53.863', 1310);
INSERT INTO "public"."orders" VALUES (3, 1, 1, '2020-05-09 09:31:22.825', '2020-05-09 10:16:34.449', 1000);
INSERT INTO "public"."orders" VALUES (4, 1, 1, '2020-05-09 09:34:48.493', '2020-05-09 09:34:51.312', 500);
INSERT INTO "public"."orders" VALUES (2, 1, 1, '2020-05-09 06:32:21.088', '2020-05-09 06:47:06.066', 1000);
INSERT INTO "public"."orders" VALUES (9, 16, 2, '2020-05-16 12:51:46.835', '2020-05-16 12:51:51.836', 24735);
INSERT INTO "public"."orders" VALUES (11, 16, 1, '2020-05-16 19:47:27.803', '2020-05-17 01:15:25.166', 1000);
INSERT INTO "public"."orders" VALUES (29, 22, 2, '2020-05-18 00:52:17.922', '2020-05-18 00:52:17.922', 7035);
INSERT INTO "public"."orders" VALUES (30, 22, 2, '2020-05-18 00:54:20.949', '2020-05-18 00:54:20.949', 2344);
INSERT INTO "public"."orders" VALUES (31, 22, 3, '2020-05-18 00:58:11.798', '2020-05-18 00:58:11.798', 6000);
INSERT INTO "public"."orders" VALUES (22, 16, 2, '2020-05-17 23:22:57.956', '2020-05-17 23:22:57.956', 1575);
INSERT INTO "public"."orders" VALUES (23, 16, 0, '2020-05-17 23:48:34.755', '2020-05-17 23:48:34.755', 800);
INSERT INTO "public"."orders" VALUES (24, 21, 2, '2020-05-18 00:26:51.402', '2020-05-18 00:26:51.402', 1145);
INSERT INTO "public"."orders" VALUES (12, 16, 3, '2020-05-16 19:47:54.345', '2020-05-16 19:47:54.345', 500);
INSERT INTO "public"."orders" VALUES (27, 21, 2, '2020-05-18 00:33:13.419', '2020-05-18 00:33:13.419', 2500);
INSERT INTO "public"."orders" VALUES (26, 21, 2, '2020-05-18 00:32:58.791', '2020-05-18 00:32:58.791', 645);
INSERT INTO "public"."orders" VALUES (25, 21, 2, '2020-05-18 00:32:42.303', '2020-05-18 00:32:42.303', 3225);
INSERT INTO "public"."orders" VALUES (21, 16, 2, '2020-05-17 23:21:54.447', '2020-05-17 23:21:54.447', 6000);
INSERT INTO "public"."orders" VALUES (20, 16, 2, '2020-05-17 21:57:28.737', '2020-05-17 21:57:28.737', 1790);
INSERT INTO "public"."orders" VALUES (18, 16, 2, '2020-05-17 13:01:48.05', '2020-05-17 19:03:43.412', 2610);
INSERT INTO "public"."orders" VALUES (17, 16, 2, '2020-05-17 01:57:56.389', '2020-05-17 01:57:11.414', 500);
INSERT INTO "public"."orders" VALUES (16, 16, 2, '2020-05-17 01:57:30.312', '2020-05-17 02:00:54.494', 4695);
INSERT INTO "public"."orders" VALUES (15, 16, 2, '2020-05-17 01:29:09.64', '2020-05-17 01:29:05.743', 900);
INSERT INTO "public"."orders" VALUES (13, 16, 2, '2020-05-17 01:24:37.111', '2020-05-17 01:24:37.111', 800);
INSERT INTO "public"."orders" VALUES (8, 16, 2, '2020-05-16 09:17:59.632', '2020-05-16 11:52:11.144', 1000);
INSERT INTO "public"."orders" VALUES (6, 1, 2, '2020-05-09 10:47:19.638', '2020-05-09 11:17:28.057', 1000);
INSERT INTO "public"."orders" VALUES (5, 1, 2, '2020-05-09 10:22:14.407', '2020-05-09 10:22:22.692', 2000);

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS "public"."products";
CREATE TABLE "public"."products" (
  "id" int4 NOT NULL GENERATED ALWAYS AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
),
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "producer" varchar(255) COLLATE "pg_catalog"."default",
  "price" numeric(10,2),
  "category_id" int4,
  "detail" varchar(255) COLLATE "pg_catalog"."default",
  "image_link" varchar(255) COLLATE "pg_catalog"."default",
  "os" varchar(255) COLLATE "pg_catalog"."default",
  "cpu" varchar(255) COLLATE "pg_catalog"."default",
  "ram" varchar(255) COLLATE "pg_catalog"."default",
  "gpu" varchar(255) COLLATE "pg_catalog"."default",
  "screen" varchar(255) COLLATE "pg_catalog"."default",
  "storage" varchar(255) COLLATE "pg_catalog"."default",
  "weight" float8,
  "release_year" varchar(255) COLLATE "pg_catalog"."default",
  "deleted" int4
)
;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO "public"."products" VALUES (13, 'Dell XPS 15 7590', 'DELL', 2000.00, 9, 'Là sản phẩm mạnh mẽ', '/upload/1589733199-5726_5026_new_xps_15_7590_anh3.jpg', 'Window', ' Intel Core i9-9980HK', '64GB DDR4-2666MHz', 'NVIDIA® GeForce® GTX', '15.6" 4K Oled', '2TB SSD', 2000, '2020', 0);
INSERT INTO "public"."products" VALUES (14, 'Dell Vostro 3490', 'DELL', 650.00, 8, 'Sản phẩm mới nhiều tính năng', '/upload/1589733308-5701_dell_vostro_3490_anh3.jpg', 'Window', 'Intel® Core™ i5-10210U', '4GB, 4Gx1, DDR4, 2666MHz', 'AMD Radeon™ 610 2GB', '14.0" HD', '1TB HDD 5400rpm', 1720, '2020', 0);
INSERT INTO "public"."products" VALUES (15, 'Dell Inspiron 7490', 'DELL', 1200.00, 14, 'Sản phẩm có nhiều tính năng mới', '/upload/1589733481-120_5870_dell_inspiron_3593_anh4.jpg', 'Window', 'Intel Core i5-10210U', '8GB onboard LPDDDR3', 'NVIDIA GeForce MX250', '14.0-inch FHD', '512Gb SSD', 1200, '2019', 0);
INSERT INTO "public"."products" VALUES (16, 'Dell XPS 1563', 'DELL', 586.00, 4, 'Sản phẩm có nhiều tính năng', '/upload/1589733639-120_5870_dell_inspiron_3593_anh4.jpg', 'Linux', 'Intel Core i5', '4GB DDR3', 'Intel Graphics 300', '14 Full HD', '512 SSD', 2365, '2017', 0);
INSERT INTO "public"."products" VALUES (17, 'ASUS K55A', 'Asus', 400.00, 4, 'Sản phẩm có nhiều tính năng', '/upload/1589733731-asus-vivobook-x509f-i7-8565u-8gb-mx230-win10-ej13-5-2-1-2-1-600x600.jpg', 'Window', 'Intel Core i3', '4GB DDR3', 'Intel Graphics 3000', 'Full HD', '256 SSD', 1863, '2018', 0);
INSERT INTO "public"."products" VALUES (18, 'Mac Book Air 1', 'Mac Book', 500.00, 4, ' Sản phẩm có nhiều tính năng mới', '/upload/1589733829-acer-aspire-a515-53-5112-i5-8265u-4gb-16gb-1tb-win3-600x600.jpg', 'Window', 'Intel core i5', '4GB DDR3', 'Intel Graphics 4000', '14'' Full HD', '256GB SSD', 2145, '2019', 0);
INSERT INTO "public"."products" VALUES (19, 'Mac Book MB1', 'Mac Book', 500.00, 4, ' Sản phẩm có nhiều tính năng', '/upload/1589733924-120_5870_dell_inspiron_3593_anh4.jpg', 'Window', 'Intel Core i5', '4GB DDR3', 'Intel Graphics', '14'' Full HD', '256GB SSD', 1523, '2018', 0);
INSERT INTO "public"."products" VALUES (4, 'Laptop Acer Aspire A315', 'Acer', 500.00, 5, 'có thiết kế nhỏ gọn, hợp thời trang, màn hình chân thực, sắc nét cùng cấu hình ổn định đáp ứng nhu cầu học tập', '/upload/1588953042-acer1.jpg', 'Window', 'Intel Core i3 Comet Lake', '4 GB, DDR4', 'Intel UHD Graphics', 'Full HD (1920 x 1080)', 'SSD 500GB', 1700, '2018', 0);
INSERT INTO "public"."products" VALUES (5, 'Acer Aspire A515', 'Acer', 645.00, 5, 'hiết kế trẻ trung, năng động cùng với cấu hình khỏe đáp ứng tốt nhu cầu xử lý các ứng dụng văn phòng', '/upload/1588953390-acer-aspire-a515-53-5112-i5-8265u-4gb-16gb-1tb-win3-600x600.jpg', 'Window', 'Intel Core i5 Coffee Lake', '4GB DDR4', 'Intel® UHD Graphics 620', 'Full HD', 'SSD 256GB', 1800, '2018', 0);
INSERT INTO "public"."products" VALUES (6, 'Laptop Acer Swift 3 SF315', 'Acer', 810.00, 4, 'Thiết kế mỏng nhẹ giúp bạn có thể di chuyển nhiều nơi một cách dễ dàng. ', '/upload/1588953606-acer-swift-sf315-52-50t9-i5-8250u-8gb-256gb-win10-19-600x600.jpg', 'Linux', 'Intel Core i5 Coffee Lake', '4GB DDR3', 'Intel HD Graphics 3000', 'Full HD', 'SSD 256GB', 1900, '2019', 0);
INSERT INTO "public"."products" VALUES (7, 'Laptop Acer Spin SP513', 'Acer', 980.00, 4, 'hiết kế tinh tế, nhỏ gọn thuận tiện cho việc di chuyển.', '/upload/1588953799-acer-sp513-52n-556v-133fhd-8250u-8gb-256gb-win10hs-21-600x600.jpg', 'Window', 'Intel Core i5 Coffee Lake', '8 GB DDR4 ', ' Intel® UHD Graphics 620', 'Full HD', 'SSD 256GB', 2100, '2020', 0);
INSERT INTO "public"."products" VALUES (8, 'Asus VivoBook X509FA ', 'Asus', 595.00, 5, 'cấu hình ổn định, phù hợp mang theo đi làm mỗi ngày ', '/upload/1588954001-asus-vivobook-x509fa-i3-8145u-4gb-512gb-chuot-win1-220575copy-600x600.jpg', 'Linux', 'Intel Core i3 Coffee Lake', '4GB DDR4', ' Intel® UHD Graphics 620', 'Full HD', 'SSD 256GB', 2200, '2019', 0);
INSERT INTO "public"."products" VALUES (9, 'Asus VivoBook X509F', 'Asus', 800.00, 4, 'hiệu năng mạnh mẽ cùng hình ảnh chân thực đáp ứng mọi nhu cầu', '/upload/1588954166-asus-vivobook-x509f-i7-8565u-8gb-mx230-win10-ej13-5-2-1-2-1-600x600.jpg', 'Window', 'Intel Core i7 Coffee Lake', '8GB DDR4', 'NVIDIA GeForce MX230 2GB', 'Full HD', 'SSD 256GB', 1890, '2019', 0);
INSERT INTO "public"."products" VALUES (1, 'Abc', 'test2', 800.00, 1, 'Detail', '/upload/1588948088-14917052_1774317632806550_2736653952193158932_o.jpg', 'Linux', 'core i5', '8gb', 'GTX 1060i ', '14 inch', '500GB', 800, '2018', 1);
INSERT INTO "public"."products" VALUES (2, '899', 'Test', 432423.00, 2, 'dfgdfgdfg', '/upload/1588950113-12208727_1638637983041183_808378615334250505_n.jpg', 'MacOS', '34234', '234', 'GTX 2000', '64inch', '8GB', 4234, '2018', 1);
INSERT INTO "public"."products" VALUES (20, 'Asus K34', 'Asus', 564.00, 4, 'Sản phẩm có nhiều tính năng', '/upload/1589734003-120_4784_dell_inspiron_15_g3_3590_anh5.jpg', 'Window', 'Intel Core i5', ' 4GB DDR3', ' Intel Graphic HD 2000', '14'' FHD', '512GB', 2000, '2017', 0);
INSERT INTO "public"."products" VALUES (21, 'Asus K78', 'Asus', 489.00, 4, 'Sản phẩm có nhiều tính năng', '/upload/1589734110-120_5870_dell_inspiron_3593_anh4.jpg', 'Window', 'Intel Core i3', '4GB DDR3', 'Intel Graphics HD', '14'' Full HD', '512GB SSD', 2100, '2019', 0);
INSERT INTO "public"."products" VALUES (22, 'Acer A21', 'Acer', 468.00, 4, 'Sản phẩm có nhiều tính năng', '/upload/1589734261-395_dell_7490.jpg', 'Window', 'Intel Core i3', '4GB DDR3', 'Intel Graphics HD', 'Full HD', '256GB SSD', 2456, '2019', 0);
INSERT INTO "public"."products" VALUES (10, 'Asus Gaming ROG Strix G531G ', 'Mac Book', 1400.00, 14, ' tập trung tối đa vào trải nghiệm của game thủ, mang trong mình những công nghệ tiên tiến nhất ', '/upload/1588954324-asus-gaming-rog-strix-g531g-i7-9750h-8gb-512gb-gtx-6-600x600.jpg', 'Window', 'Intel Core i7 Coffee Lake', '8GB DDR4', 'NVIDIA GeForce GTX 1650 4GB', 'Full HD', 'SSD 512 GB', 2400, '2020', 0);
INSERT INTO "public"."products" VALUES (3, '1258', 'test2', 56546.00, 1, '4645', '/upload/1588950995-12208727_1638637983041183_808378615334250505_n.jpg', 'Linux', '5445', '56', '45645', '445645', '46456', 456456, '2018', 1);
INSERT INTO "public"."products" VALUES (11, 'Dell Latitude 7490', 'DELL', 900.00, 10, 'Dell Latitude 7490 cung cấp nhiều biện pháp bảo mật ', '/upload/1589732917-395_dell_7490.jpg', 'Linux', '8th Gen Intel® Core™ i7-8650U', '16GB DDR4 2400MHz', 'Share Intel® UHD Graphics 620', 'Full HD', '256GB SSD', 1400, '2019', 0);
INSERT INTO "public"."products" VALUES (12, 'Dell Inspiron 15 G3 3590', 'DELL', 1100.00, 6, 'thế hệ laptop gaming được Dell thiết kế với nhiều cải tiến, kích cỡ mỏng nhẹ hơn', '/upload/1589733065-120_4784_dell_inspiron_15_g3_3590_anh5.jpg', 'Window', 'Intel® Core™ i5-9300H', '8GB DDR4 2666MHz', 'NVIDIA Geforce GTX', 'Full HD', '512GB SSD', 2340, '2019', 0);
INSERT INTO "public"."products" VALUES (23, 'Acer A56', 'Acer', 536.00, 4, 'Sản phẩm có nhiều tính năng', '/upload/1589734324-120_5870_dell_inspiron_3593_anh4.jpg', 'Window', 'Intel core i5', '4GB DDR3', 'Intel Graphic HD', '14 HD', '512GB SSD', 2563, '2018', 0);
INSERT INTO "public"."products" VALUES (24, 'HP H7', 'HP', 512.00, 4, 'Sản phẩm có nhiều tính năng', '/upload/1589734417-395_dell_7490.jpg', 'Window', 'Intel Core i5', '4GB DDR4', 'Intel Graphics HD', '14 Full HD', '512GB SSD', 2345, '2018', 0);
INSERT INTO "public"."products" VALUES (25, 'HP  H8', 'HP', 486.00, 4, 'Sản phẩm có nhiều tính năng', '/upload/1589734491-asus-vivobook-x509fa-i3-8145u-4gb-512gb-chuot-win1-220575copy-600x600.jpg', 'Window', 'Intel Core i3', '4GB DDR3', 'Intel Graphics ', 'Full HD', '512GB SSD', 2463, '2018', 0);
INSERT INTO "public"."products" VALUES (26, 'Lenovo V4', 'Lenovo', 568.00, 4, 'Sản phẩm có nhiều tính năng', '/upload/1589734567-120_5870_dell_inspiron_3593_anh4.jpg', 'Window', 'Intel Core i5', '4GB DDR3', 'Intel Graphics HD', '14 Full HD', '256GB SSD', 1486, '2018', 0);
INSERT INTO "public"."products" VALUES (27, 'Dell Graphics G3', 'DELL', 905.00, 6, 'Sản phẩm có nhiều tính năng', '/upload/1589734667-120_5870_dell_inspiron_3593_anh4.jpg', 'Window', 'Intel Core i5', '16GB DDR3', 'Intel Graphics HD', '14  HD', '512GB SSD', 2430, '2018', 0);
INSERT INTO "public"."products" VALUES (28, 'Mac Book MB4', 'Mac Book', 850.00, 6, 'Sản phẩm có nhiều tính năng', '/upload/1589734784-120_5870_dell_inspiron_3593_anh4.jpg', 'Window', 'Intel Core i5', '16GB DDR4', 'Intel Graphics', ' Full HD', '512 GB SSD', 2563, '2020', 0);

-- ----------------------------
-- Table structure for supplies
-- ----------------------------
DROP TABLE IF EXISTS "public"."supplies";
CREATE TABLE "public"."supplies" (
  "id" int4 NOT NULL GENERATED ALWAYS AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
),
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "phone" varchar(255) COLLATE "pg_catalog"."default",
  "email" varchar(255) COLLATE "pg_catalog"."default",
  "address" varchar(255) COLLATE "pg_catalog"."default",
  "active" int2,
  "deleted" int4,
  "detail" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of supplies
-- ----------------------------
INSERT INTO "public"."supplies" VALUES (3, 'Test', '1', 'ae@gmail.com', 'admin', 0, 1, '1');
INSERT INTO "public"."supplies" VALUES (6, 'Mac Book', '12536569', 'mack@gmail.com', 'Số 8 Hà Nội', 1, 0, 'Dòng máy sang trọng của Apple sử dụng hệ điều hàng MacOS');
INSERT INTO "public"."supplies" VALUES (7, 'DELL', '896563645', 'dell@gmail.com', 'Số 10 Hà Nội', 1, 0, 'Dòng máy bền thường là nguyên khối của dòng DELL Mỹ thường dòng Workstation');
INSERT INTO "public"."supplies" VALUES (9, 'Asus', '1896563356', 'asus@gmail.com', 'Nguyễn Trãi Hà Nội', 1, 0, 'Dòng máy cấu hình cao giá rẻ phù hợp nhiều loại đối tượng');
INSERT INTO "public"."supplies" VALUES (2, 'Test', '1', 'ae@gmail.com', 'admin', 0, 1, '1');
INSERT INTO "public"."supplies" VALUES (1, 'TEst', '0975155726', 'test@gmail.com', 'Test', 0, 1, 'Dssdfsdfs');
INSERT INTO "public"."supplies" VALUES (10, 'Acer', '1896658', 'acer@gmail.com', 'Thanh Xuân Hà Nội', 1, 0, 'Dòng máy thích hợp office');
INSERT INTO "public"."supplies" VALUES (5, 'test2', '0975155726', 'admin@gmail.com', 'admin@gmail.com', 0, 1, 'sdfsd');
INSERT INTO "public"."supplies" VALUES (4, 'Test', '1', '2', '3', 0, 1, '4');
INSERT INTO "public"."supplies" VALUES (12, 'Test', '0975155726', 'test@gmail.com', 'Thanh Xuan ', 0, 1, 'Test');
INSERT INTO "public"."supplies" VALUES (8, 'HP', '1236598425', 'hp@gmail.com', 'Số 10  Hồ Tùng Mậu Cầu Giấy Hà Nội', 1, 0, 'Dòng máy mỏng nhẹ thích hợp văn phòng');
INSERT INTO "public"."supplies" VALUES (11, 'Lenovo', '896655622', 'lenovo@gmail.com', 'Thanh Xuân Hà Nội', 1, 0, 'Dòng máy có thương hiệu lâu đời');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS "public"."users";
CREATE TABLE "public"."users" (
  "id" int4 NOT NULL GENERATED ALWAYS AS IDENTITY (
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
),
  "username" varchar(255) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default",
  "role" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "birth_day" date,
  "gender" int2,
  "address" varchar(255) COLLATE "pg_catalog"."default",
  "phone" varchar(255) COLLATE "pg_catalog"."default",
  "image_link" varchar(255) COLLATE "pg_catalog"."default",
  "state" int2 DEFAULT 0,
  "deleted" int2 DEFAULT 0
)
;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO "public"."users" VALUES (15, 'abcd@gmail.com', '$2a$10$fDemQg8In1XXNI3mV5bDyegEVh2H6LqXg6tn6kJVtywjUpT4jY2fe', 'ROLE_EMP', 'ABCD', '1995-12-01', 1, 'Thanh Xuân, Hà Nội', '0975155726', '/upload/1589515361-12190081_1635713766666938_3948283990444673815_n.jpg', 0, 0);
INSERT INTO "public"."users" VALUES (11, 'test@gmail.com', '$2a$10$FTxH4sKYBgxLg0NiWPOyG.xgijDT9lIvJC/OKFTt7QlvGrFkCaj7a', 'ROLE_EMP', 'Nguyễn Thanh Hoa', '1996-10-02', 1, 'Bắc Ninh', '059632148', '/upload/1588954904-12190081_1635713766666938_3948283990444673815_n.jpg', 0, 1);
INSERT INTO "public"."users" VALUES (17, 'namnt@gmail.com', '$2a$10$iXxINv4bmcE849Hi26paZeqMeIFjScId/qq89ViwXYMd09GAcMFjm', 'ROLE_EMP', 'Nguyễn Thành Nam', '1995-04-29', 1, 'Thanh Hóa', '0975155726', '/upload/1589623940-12208727_1638637983041183_808378615334250505_n.jpg', 0, 1);
INSERT INTO "public"."users" VALUES (12, 'trung@gmail.com', '$2a$10$Hh9zBHCeNGX6ukFKMpbWxunMpis/INGMh0z/ZDafUq8KH.YxVDdPm', 'ROLE_EMP', 'Nguyễn Thành Trung', '1997-12-09', 0, 'Vĩnh Phúc', '1234569855', '/upload/1589624163-89772988_505094063468741_1381651422474403840_o.jpg', 0, 0);
INSERT INTO "public"."users" VALUES (18, 'haint@gmail.com', '$2a$10$Oc8ZxpZJAxOlYoKrIRa59eLIfDaUArWDKC5ScQOgua14JuOVytG96', 'ROLE_EMP', 'Nguyễn Thị Hải', '1998-12-08', 1, 'Bắc Giang', '023695636', '/upload/1589624217-17504249_1840921306146182_4590391945999868942_o.jpg', 0, 0);
INSERT INTO "public"."users" VALUES (19, 'doandv188@gmail.com', '$2a$10$VAc5ATaEutpAyJrGO4NDR..Q2It34E7iwYJA/XN2pASK3/61gNq.K', 'ROLE_EMP', 'Duong Van Doan', '1995-12-08', 0, 'Thanh Xuan', '0975155726', '/upload/1589626743-12208727_1638637983041183_808378615334250505_n.jpg', 0, 0);
INSERT INTO "public"."users" VALUES (16, 'user@gmail.com', '$2a$10$JVAjjluLCFpr89YgswWHp.KqxZ9Cg4I96.ViYZF.gZWX/XxEyo./O', 'ROLE_CUSTOMER', 'Dương Văn Đoàn', '1996-12-08', 0, '127 Mai Dịch, Cầu Giấy,Hà Nội', '123456798', '/upload/1589657292-12208727_1638637983041183_808378615334250505_n.jpg', 0, 0);
INSERT INTO "public"."users" VALUES (1, 'admin@gmail.com', '$2a$10$Zw3fGmBrK1AJTesK0r9NOeFNQPlIinBTg14flo/Akg6Dg6TqoztBK', 'ROLE_ADMIN', 'Admin', '1995-08-18', 0, 'Bắc Ninh', '0975155726', '/upload/1589732054-12208727_1638637983041183_808378615334250505_n.jpg', 0, 0);
INSERT INTO "public"."users" VALUES (20, 'anhnt@gmail.com', '$2a$10$oOl/VgcW8fHdP05MZhrLFulFg7M8WgV2cDxbhoyePaF6ooEbh8MQS', 'ROLE_EMP', 'Tran Ngọc Anh', '1994-12-01', 1, ' Thanh Xuân Đống Đa Hà Nội', '0975155726', '/upload/1589732143-12208727_1638637983041183_808378615334250505_n.jpg', 0, 0);
INSERT INTO "public"."users" VALUES (7, 'cuc@gmail.com', '$2a$10$lYnGpOiPdeEclcTZ9gH42ueVbf/pvd1hZsofIZxbFqrQjKXs1PkN6', 'ROLE_EMP', 'Hoàng Đỗ Cúc', '1998-12-11', 0, 'Thanh Hóa', '0975263562', '/upload/1588954694-53926466_2220219384883037_7248556991960842240_n.jpg', 0, 0);
INSERT INTO "public"."users" VALUES (8, 'bon@gmail.com', '$2a$10$FffQ.I8gQ0fv8/Dye4O6vegGNz9SLyYLoqMuSZtgt53kEwLHl0/uG', 'ROLE_EMP', 'Đỗ Ngọc Bốn', '1998-02-02', 0, 'Thanh Hóa', '0975155726', '/upload/1588954751-89772988_505094063468741_1381651422474403840_o.jpg', 0, 0);
INSERT INTO "public"."users" VALUES (9, 'ngoc@gmail.com', '$2a$10$By/QlbCNq19tQkLdGtg7euoXKMd/2B1P/M8drX71gadr2PZNxNKD2', 'ROLE_EMP', 'Lê Thị Bích Ngọc', '1997-08-08', 1, 'Thanh Hóa', '098652655', '/upload/1588954803-12190081_1635713766666938_3948283990444673815_n.jpg', 0, 0);
INSERT INTO "public"."users" VALUES (10, 'thuy@gmail.com', '$2a$10$1j3JHQfCdZBWGzIo2NoaXeZ0FjEuGDX0azQtId8kPS01wUv2IFv0a', 'ROLE_EMP', 'Nguyễn Thanh Thúy', '1995-02-01', 1, ' Hà Nội', '0123456789', '/upload/1588954850-17504249_1840921306146182_4590391945999868942_o.jpg', 0, 0);
INSERT INTO "public"."users" VALUES (21, 'an@gmail.com', '$2a$10$O5iIx68kLvOsrKzNNN9xrelFLIJMoCSOrxpxypB3KDSs9RNTwix8K', 'ROLE_CUSTOMER', 'Trần Van An', '2020-05-05', 0, '147 Hoàng Quốc Việt', '0975155726', '/upload/1589737792-12190081_1635713766666938_3948283990444673815_n.jpg', 0, 0);
INSERT INTO "public"."users" VALUES (22, 'abcth@gmail.com', '$2a$10$1F3IinSa0Xuq5KNXDvi7uuifZTmAgzAY20To3IZXOGNeBlvpsuwc6', 'ROLE_CUSTOMER', 'ABC', '2020-05-12', 0, 'Thanh Xuân', '0975155726', '/upload/1589737891-67364439_2302743859963922_6486911543505059840_n.jpg', 0, 0);

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS "public"."warehouse";
CREATE TABLE "public"."warehouse" (
  "id" int8 NOT NULL DEFAULT nextval('warehouse_id_seq'::regclass),
  "last_update" timestamp(6),
  "product_id" int8,
  "quantity" int8,
  "user_id" int8
)
;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO "public"."warehouse" VALUES (4, '2020-05-09 02:15:02.354', 3, 200, 1);
INSERT INTO "public"."warehouse" VALUES (9, '2020-05-18 00:14:23.577', 8, 2021, 20);
INSERT INTO "public"."warehouse" VALUES (2, '2020-05-09 02:18:39.025', 2, 800, 1);
INSERT INTO "public"."warehouse" VALUES (18, '2020-05-18 00:14:31.372', 20, 11, 20);
INSERT INTO "public"."warehouse" VALUES (19, '2020-05-18 00:14:48.37', 11, 11, 20);
INSERT INTO "public"."warehouse" VALUES (20, '2020-05-18 00:14:58.005', 12, 11, 20);
INSERT INTO "public"."warehouse" VALUES (17, '2020-05-18 00:15:07.802', 27, 31, 20);
INSERT INTO "public"."warehouse" VALUES (21, '2020-05-18 00:15:15.995', 21, 11, 20);
INSERT INTO "public"."warehouse" VALUES (22, '2020-05-18 00:15:25.955', 22, 11, 20);
INSERT INTO "public"."warehouse" VALUES (23, '2020-05-18 00:15:35.308', 25, 11, 20);
INSERT INTO "public"."warehouse" VALUES (1, '2020-05-09 02:43:41.227', 1, 80, 1);
INSERT INTO "public"."warehouse" VALUES (24, '2020-05-18 00:15:45.235', 24, 11, 20);
INSERT INTO "public"."warehouse" VALUES (25, '2020-05-18 00:15:59.605', 26, 11, 20);
INSERT INTO "public"."warehouse" VALUES (26, '2020-05-18 00:16:07.846', 28, 11, 20);
INSERT INTO "public"."warehouse" VALUES (3, '2020-05-18 00:13:55.495', 4, 13, 20);
INSERT INTO "public"."warehouse" VALUES (7, '2020-05-18 00:14:03.838', 6, 2038, 20);
INSERT INTO "public"."warehouse" VALUES (8, '2020-05-18 00:14:15.487', 7, 2020, 20);
INSERT INTO "public"."warehouse" VALUES (6, '2020-05-18 00:14:40.437', 10, 2010, 20);
INSERT INTO "public"."warehouse" VALUES (5, '2020-05-09 02:43:29.139', 5, 990, 1);
INSERT INTO "public"."warehouse" VALUES (13, '2020-05-18 00:11:57.563', 15, 19, 20);
INSERT INTO "public"."warehouse" VALUES (14, '2020-05-18 00:12:56.261', 16, 16, 20);
INSERT INTO "public"."warehouse" VALUES (11, '2020-05-18 00:11:39.8', 13, 19, 20);
INSERT INTO "public"."warehouse" VALUES (12, '2020-05-18 00:11:46.951', 14, 20, 20);
INSERT INTO "public"."warehouse" VALUES (15, '2020-05-18 00:13:11.296', 17, 20, 20);
INSERT INTO "public"."warehouse" VALUES (16, '2020-05-18 00:13:18.055', 18, 20, 20);
INSERT INTO "public"."warehouse" VALUES (10, '2020-05-18 00:13:32.819', 9, 3020, 20);

-- ----------------------------
-- View structure for top_product
-- ----------------------------
DROP VIEW IF EXISTS "public"."top_product";
CREATE VIEW "public"."top_product" AS  SELECT order_detail.product_id,
    sum(order_detail.quantity) AS sum
   FROM order_detail
  GROUP BY order_detail.product_id
  ORDER BY (sum(order_detail.quantity)) DESC;

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."advertises_id_seq"
OWNED BY "public"."advertises"."id";
SELECT setval('"public"."advertises_id_seq"', 9, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."categories_id_seq"
OWNED BY "public"."categories"."id";
SELECT setval('"public"."categories_id_seq"', 16, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."order_detail_id_seq"
OWNED BY "public"."order_detail"."id";
SELECT setval('"public"."order_detail_id_seq"', 57, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."orders_id_seq"
OWNED BY "public"."orders"."id";
SELECT setval('"public"."orders_id_seq"', 32, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."products_id_seq"
OWNED BY "public"."products"."id";
SELECT setval('"public"."products_id_seq"', 29, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."supplies_id_seq"
OWNED BY "public"."supplies"."id";
SELECT setval('"public"."supplies_id_seq"', 13, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."users_id_seq"
OWNED BY "public"."users"."id";
SELECT setval('"public"."users_id_seq"', 23, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."warehouse_id_seq"
OWNED BY "public"."warehouse"."id";
SELECT setval('"public"."warehouse_id_seq"', 27, true);

-- ----------------------------
-- Primary Key structure for table advertises
-- ----------------------------
ALTER TABLE "public"."advertises" ADD CONSTRAINT "advertises_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table categories
-- ----------------------------
ALTER TABLE "public"."categories" ADD CONSTRAINT "categories_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table order_detail
-- ----------------------------
ALTER TABLE "public"."order_detail" ADD CONSTRAINT "order_detail_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table order_detail
-- ----------------------------
ALTER TABLE "public"."order_detail" ADD CONSTRAINT "fk_order_detail_orders_1" FOREIGN KEY ("order_id") REFERENCES "public"."orders" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."order_detail" ADD CONSTRAINT "fk_order_detail_products_1" FOREIGN KEY ("product_id") REFERENCES "public"."products" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Primary Key structure for table orders
-- ----------------------------
ALTER TABLE "public"."orders" ADD CONSTRAINT "orders_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table orders
-- ----------------------------
ALTER TABLE "public"."orders" ADD CONSTRAINT "fk_orders_users_1" FOREIGN KEY ("user_id") REFERENCES "public"."users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Primary Key structure for table products
-- ----------------------------
ALTER TABLE "public"."products" ADD CONSTRAINT "products_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table products
-- ----------------------------
ALTER TABLE "public"."products" ADD CONSTRAINT "fk_products_categories_1" FOREIGN KEY ("category_id") REFERENCES "public"."categories" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Primary Key structure for table supplies
-- ----------------------------
ALTER TABLE "public"."supplies" ADD CONSTRAINT "supplies_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table users
-- ----------------------------
ALTER TABLE "public"."users" ADD CONSTRAINT "users_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table warehouse
-- ----------------------------
ALTER TABLE "public"."warehouse" ADD CONSTRAINT "warehouse_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table warehouse
-- ----------------------------
ALTER TABLE "public"."warehouse" ADD CONSTRAINT "fkl4hggrkrw8ervx1vtli8afnrc" FOREIGN KEY ("user_id") REFERENCES "public"."users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."warehouse" ADD CONSTRAINT "fkow13o6v2o8btmca0nw5pblpss" FOREIGN KEY ("product_id") REFERENCES "public"."products" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
