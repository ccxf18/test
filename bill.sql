-- phpMyAdmin SQL Dump
-- version 4.4.15.10
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2023-06-09 21:56:03
-- 服务器版本： 5.6.50-log
-- PHP Version: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bill`
--

-- --------------------------------------------------------

--
-- 表的结构 `chat`
--

CREATE TABLE IF NOT EXISTS `chat` (
  `id` int(11) NOT NULL,
  `content` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `collect` int(11) DEFAULT '0',
  `like` int(11) DEFAULT '0',
  `author` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `chat`
--

INSERT INTO `chat` (`id`, `content`, `time`, `collect`, `like`, `author`) VALUES
(2, '这个月又花超了', '2022-11-30 13:12:47', 34, 6, 'test'),
(3, '姐妹们，这个月已经支出快8000了', '2022-10-29 06:15:25', 37, 44, 'test'),
(4, '又要剁手了', '2022-11-22 10:45:33', 2, 40, 'test1'),
(5, '双十一来了，又到了剁手的季节', '2022-11-08 04:11:30', 46, 22, 'test1'),
(6, '日常打卡', '2022-11-16 08:50:57', 34, 13, 'cynq'),
(9, '不看不要紧，一看吓一跳', '2022-11-21 09:14:27', 6, 6, 'grsw'),
(10, '工资已经见底了', '2022-11-24 17:51:38', 16, 36, 'gtfk'),
(11, '用了这个app确实能督促自己', '2022-12-02 12:51:21', 15, 38, 'gtwp'),
(12, '省钱确实有用', '2022-12-07 05:34:22', 32, 34, 'idmn'),
(13, '大家有出来聊天的吗', '2022-11-11 02:30:10', 47, 10, 'iwan'),
(14, '这个统计数据好害怕', '2022-11-16 17:36:44', 43, 48, 'jaks'),
(15, '以后不敢大手大脚花钱了', '2022-11-17 20:26:32', 16, 6, 'lckc'),
(16, '一年到头下来，看看钱都花在哪里了', '2022-12-04 01:56:33', 29, 34, 'leql'),
(21, '怎么办怎么办', '2022-11-12 19:23:33', 0, 0, NULL),
(22, '加油呀', '2022-11-12 19:24:39', 0, 0, NULL),
(23, '很不错', '2022-11-12 21:01:33', 1, 0, NULL),
(24, '加油加油', '2022-11-12 21:05:36', 2, 0, NULL),
(25, '很不错', '2022-11-12 21:11:51', 1, 0, NULL),
(26, '11111111111111', '2022-11-25 16:56:27', 1, 1, NULL),
(32, '我试试', '2023-02-07 10:42:15', 0, 0, NULL),
(33, 'zheenhao', '2023-02-09 09:31:14', 0, 0, NULL),
(34, '测试', '2023-06-08 22:06:32', 1, 1, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `collect`
--

CREATE TABLE IF NOT EXISTS `collect` (
  `id` int(11) NOT NULL,
  `userName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `chatId` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `collect`
--

INSERT INTO `collect` (`id`, `userName`, `chatId`) VALUES
(2, 'test1', 8),
(4, 'test', 10),
(5, 'cynq', 15),
(6, 'iwan', 8),
(7, 'jaks', 6),
(8, 'lckc', 1),
(17, 'test', 5),
(18, 'test', 12),
(19, 'test', 23),
(20, 'test', 24),
(25, 'test', 4),
(27, 'test', 3),
(29, 'test', 25),
(34, 'test', 26),
(35, 'test', 34);

-- --------------------------------------------------------

--
-- 表的结构 `expend`
--

CREATE TABLE IF NOT EXISTS `expend` (
  `id` int(11) NOT NULL,
  `userName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `way` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sum` int(11) DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `expend`
--

INSERT INTO `expend` (`id`, `userName`, `time`, `name`, `way`, `sum`, `remark`) VALUES
(3, 'test', '2022-11-12 22:22:27', '宠物消费', '支付宝', 396, NULL),
(4, 'test1', '2022-11-03 15:06:50', '衣服', '银行卡', 257, NULL),
(5, 'test', '2022-11-13 15:30:53', '购物', '现金', 415, NULL),
(6, 'test1', '2022-10-24 16:02:23', '零食', '微信', 275, NULL),
(7, 'test', '2022-11-26 00:44:25', '餐饮', '银行卡', 120, NULL),
(8, 'test', '2022-11-06 20:03:11', '零食', '支付宝', 144, NULL),
(9, 'test1', '2022-10-15 12:48:19', '购物', '微信', 171, NULL),
(10, 'test', '2022-11-03 22:58:14', '衣服', '银行卡', 448, NULL),
(11, 'test', '2022-11-12 22:07:57', '聚餐', '支付宝', 100, NULL),
(12, 'test', '2022-11-12 22:18:37', '测试11', '测试11', 100, NULL),
(13, 'test', '2022-11-12 22:53:34', '测试支出', '聚餐', 1234, NULL),
(14, 'test', '2022-11-12 23:01:58', '测试支出', '测试支出', 123, NULL),
(15, 'test', '2022-11-12 23:14:33', '户外运动', '支付宝', 100, NULL),
(16, 'test', '2022-11-25 16:53:28', '吃饭', '现金', 90, NULL),
(17, 'test', '2022-11-25 16:55:22', '测试', '现金', 1000, NULL),
(18, '123', '2023-02-07 10:40:06', '吃饭', '银行卡', 123, NULL),
(20, 'test', '2023-02-09 08:57:22', '吃饭', '银行卡', 1234, NULL),
(21, 'test', '2023-02-09 09:30:22', '吃饭', '微信', 123, NULL),
(22, 'test', '2023-06-08 22:06:04', '测试', '支付宝', 10000, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `income`
--

CREATE TABLE IF NOT EXISTS `income` (
  `id` int(11) NOT NULL,
  `userName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `way` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sum` int(11) DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `income`
--

INSERT INTO `income` (`id`, `userName`, `time`, `name`, `way`, `sum`, `remark`) VALUES
(3, 'test', '2022-11-18 10:15:34', '工资', '现金', 1087, NULL),
(4, 'test1', '2022-10-10 19:20:07', '工资', '现金', 457, NULL),
(5, 'test', '2022-10-23 09:56:21', '图像剪辑收入', '现金', 709, NULL),
(6, 'test1', '2022-11-08 15:26:01', '其他兼职收入', '支付宝', 482, NULL),
(7, 'test', '2022-11-26 18:46:27', '图像剪辑收入', '现金', 1240, NULL),
(8, 'test', '2022-11-19 09:51:19', '其他兼职收入', '微信', 1974, NULL),
(9, 'test1', '2022-11-07 07:25:56', '其他兼职收入', '银行卡', 868, NULL),
(10, 'test', '2022-10-10 17:54:02', '其他兼职收入', '支付宝', 1210, NULL),
(11, NULL, '2022-11-12 22:03:51', '测试收入', '支付宝', NULL, NULL),
(12, NULL, '2022-11-12 22:05:44', '测试收入', '兼职', 100, NULL),
(13, 'test', '2022-11-12 22:13:14', '打工', '工资', 10, NULL),
(14, 'test', '2022-11-12 23:01:27', '测试收入', '测试来源', 111, NULL),
(15, '123', '2023-02-07 10:40:57', '工作', '工作', 12345, NULL),
(16, 'test', '2023-02-09 09:30:41', '工作', '工作', 1234, NULL),
(17, 'test', '2023-06-08 22:05:34', '测试', '微信', 10000, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `like`
--

CREATE TABLE IF NOT EXISTS `like` (
  `id` int(11) NOT NULL,
  `userName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `chatId` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `like`
--

INSERT INTO `like` (`id`, `userName`, `chatId`) VALUES
(2, 'test1', 8),
(4, 'test', 10),
(5, 'cynq', 15),
(6, 'iwan', 8),
(7, 'jaks', 6),
(8, 'lckc', 1),
(19, 'test', 3),
(23, 'test', 6),
(24, 'test', 26),
(25, 'test', 34);

-- --------------------------------------------------------

--
-- 表的结构 `sign`
--

CREATE TABLE IF NOT EXISTS `sign` (
  `id` int(11) NOT NULL,
  `userName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `time` datetime DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `sign`
--

INSERT INTO `sign` (`id`, `userName`, `time`) VALUES
(1, 'test', '2022-11-02 00:56:26'),
(2, 'test1', '2022-10-31 10:15:50'),
(3, 'test', '2022-11-07 08:42:12'),
(4, 'test', '2022-10-27 03:20:52'),
(5, 'test1', '2022-10-17 13:53:21'),
(6, 'test', '2022-10-25 13:31:38'),
(7, 'test', '2022-10-15 23:31:21'),
(8, 'test1', '2022-10-27 17:20:15'),
(9, 'test', '2022-10-24 07:29:29'),
(10, 'test1', '2022-11-01 01:14:26'),
(11, 'test', '2022-11-08 16:00:12'),
(17, 'test', '2022-11-12 20:21:24'),
(18, 'cynq', '2022-11-12 20:31:08'),
(19, 'test', '2022-11-13 15:28:07'),
(20, 'test', '2022-11-24 18:18:06'),
(21, 'test', '2022-11-25 15:53:25'),
(22, '123', '2023-02-07 10:42:33'),
(23, 'test', '2023-06-08 22:06:58');

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  `userpwd` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `disable` int(11) DEFAULT '0',
  `time` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `realname` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`username`, `userpwd`, `disable`, `time`, `email`, `realname`, `gender`, `address`, `phone`, `age`) VALUES
('123', 'test', 1, NULL, '528271621@qq.com', NULL, NULL, NULL, NULL, NULL),
('abc', 'test', 0, NULL, '528271621@qqcom', NULL, NULL, NULL, NULL, NULL),
('ceshi', 'ceshi', 1, NULL, '1111@qq.com', NULL, NULL, NULL, NULL, NULL),
('cynq', 'qPIgb1Jh7s', 0, '2022-11-23 16:58:56', 'weizh@mail.com', '魏震南', '男', '中国广州市白云区小坪东路767号12楼', '2074023982', 50),
('dtvj', 'mWx9HpFNRc', 0, '2022-11-29 02:26:54', 'qianshi@outlook.com', '钱詩涵', '女', '中国广州市天河区天河路847号14号楼', '204365402', 57),
('fkhe', 'jJ5C7euIsY', 0, '2022-11-15 19:27:37', 'jiehongshao7@hotmail.com', '邵杰宏', '男', '中国成都市成华区双庆路808号华润大厦42室', '14111467854', 35),
('grsw', 'ri8wnQxMsy', 0, '2022-11-30 07:09:30', 'ruidai9@mail.com', '戴睿', '男', '中国广州市白云区小坪东路602号32楼', '13153383809', 62),
('gtfk', 'jkx0xm7eSx', 0, '2022-12-02 13:48:12', 'cui5@icloud.com', '崔震南', '男', '中国广州市白云区小坪东路307号华润大厦24室', '18577584480', 65),
('gtwp', 'rQqvmf21HL', 0, '2022-12-18 18:42:08', 'zhennhou10@mail.com', '侯震南', '男', '中国中山天河区大信商圈大信南路639号40号楼', '17120533299', 45),
('idmn', '1ZXkodYSGj', 0, '2022-11-08 20:31:07', 'li2010@mail.com', '黎嘉伦', '男', '中国北京市西城区西長安街882号43楼', '1010182735', 42),
('iwan', '0FPR5kXSdH', 0, '2022-12-16 19:20:41', 'ruiwei@gmail.com', '韦睿', '男', '中国中山紫马岭商圈中山五路824号43楼', '16871715759', 29),
('jaks', 'sjEzyKyQ2b', 0, '2022-11-15 19:11:17', 'xjiehong@outlook.com', '熊杰宏', '男', '中国广州市白云区小坪东路34号15号楼', '2068531413', 47),
('jniz', 'SOR2nt2taV', 0, '2022-11-30 22:07:14', 'yuning02@hotmail.com', '汤宇宁', '男', '中国广州市白云区机场路棠苑街五巷634号4栋', '17444871658', 31),
('jqbd', 'jx78IjZul5', 0, '2022-11-01 04:35:23', 'zhennzo@icloud.com', '邹震南', '男', '中国北京市房山区岳琉路239号24室', '19603719885', 31),
('kijk', 'LyUrekDZJO', 0, '2022-12-13 03:53:42', 'xiaomingjiang@gmail.com', '姜晓明', '男', '中国北京市延庆区028县道484号41号楼', '17117664857', 31),
('laiy', 'lMZnsRo4WH', 0, '2022-10-31 06:40:22', 'zhouanqi6@gmail.com', '周安琪', '女', '中国上海市浦东新区橄榄路634号19号楼', '2192285847', 42),
('lckc', 'nEYSjLsFw5', 0, '2022-12-21 00:30:16', 'hu10@yahoo.com', '胡致远', '男', '中国东莞坑美十五巷154号华润大厦17室', '76966880903', 64),
('leql', 'dv7TiefFFk', 0, '2022-11-28 20:56:46', 'yuanzi@mail.com', '袁子异', '男', '中国广州市天河区天河路76号华润大厦13室', '208922849', 65),
('lkie', 'B8dzViTJZg', 0, '2022-10-30 04:38:46', 'ziyi50@gmail.com', '黎子异', '男', '中国中山京华商圈华夏街142号华润大厦39室', '18899953429', 33),
('lrfe', 'L22uCI7C7R', 0, '2022-11-27 21:05:46', 'yxiaoming1119@gmail.com', '于晓明', '男', '中国东莞坑美十五巷662号19室', '7697788877', 61),
('mhcr', '6DailppFid', 0, '2022-11-13 11:18:53', 'yuanq@outlook.com', '于安琪', '女', '中国广州市越秀区中山二路977号华润大厦3室', '2027900977', 38),
('mokt', '1ORUNOlkwe', 0, '2022-12-21 12:51:08', 'yuningxiao@gmail.com', '萧宇宁', '男', '中国东莞珊瑚路318号44号楼', '76986542233', 42),
('nkhh', 'Vcz4pssit3', 0, '2022-11-29 15:20:48', 'lan1014@outlook.com', '萧岚', '女', '中国东莞坑美十五巷742号华润大厦12室', '14106315549', 63),
('nmmb', 'DvX23s9Nzb', 0, '2022-12-18 12:02:24', 'yz52@outlook.com', '阎致远', '男', '中国成都市成华区玉双路6号617号3室', '289738951', 48),
('npwy', '8haPrQRSbp', 0, '2022-11-27 19:11:22', 'zhig2002@icloud.com', '郭致远', '男', '中国成都市成华区玉双路6号633号华润大厦9室', '15805252702', 59),
('nvyf', '0gk40k7z1H', 0, '2022-12-19 18:22:34', 'xs7@icloud.com', '沈晓明', '男', '中国东莞东泰五街594号17室', '13495502113', 31),
('nwea', 'mc5rL2DMWz', 0, '2022-12-29 12:53:32', 'haolan904@gmail.com', '郝岚', '女', '中国深圳龙岗区学园一巷566号30号楼', '19749850187', 42),
('nynf', 'QUyMEVQpxn', 0, '2022-11-12 16:51:19', 'ma725@mail.com', '马安琪', '女', '中国深圳罗湖区清水河一路987号华润大厦49室', '75582126701', 36),
('oago', 'GNRTBboHb9', 0, '2022-12-24 07:57:17', 'gonglan@gmail.com', '龚岚', '女', '中国上海市浦东新区健祥路124号华润大厦6室', '18011828089', 36),
('odym', 'wUk8jEYx7O', 0, '2022-10-31 16:35:19', 'ziyimao@hotmail.com', '毛子异', '男', '中国中山乐丰六路241号29号楼', '15934353626', 63),
('okfv', 'QsFCdfndqD', 0, '2022-11-28 19:13:40', 'rx1026@gmail.com', '许睿', '男', '中国东莞珊瑚路161号1号楼', '7697086199', 33),
('opsh', '18eivmuwIx', 0, '2022-11-11 16:44:33', 'yuning7@gmail.com', '钱宇宁', '男', '中国深圳福田区深南大道548号20号楼', '15887348142', 49),
('pwjx', 'bKeUqt27j0', 0, '2022-11-28 13:35:45', 'shihand526@outlook.com', '邓詩涵', '女', '中国中山京华商圈华夏街928号16栋', '7605742575', 65),
('qjfg', 'mc4p5rJwVy', 0, '2022-12-11 04:30:22', 'ziyis@gmail.com', '沈子异', '男', '中国中山京华商圈华夏街386号20室', '7606402948', 41),
('rntx', 's9qfCvyXf6', 0, '2022-10-31 22:37:18', 'ruim@icloud.com', '莫睿', '男', '中国上海市浦东新区橄榄路671号华润大厦6室', '13791115901', 41),
('rsef', 'nszVOfGEyv', 0, '2022-11-01 20:12:53', 'mol@outlook.com', '莫璐', '女', '中国成都市成华区二仙桥东三路116号34楼', '16704930586', 59),
('scek', 'KD7j3Q8tNk', 0, '2022-12-19 21:06:23', 'mo6@gmail.com', '莫璐', '女', '中国中山京华商圈华夏街339号20室', '7604871820', 57),
('sgjt', 'UbMFnamcdn', 0, '2022-11-03 00:19:22', 'yuningcai729@gmail.com', '蔡宇宁', '男', '中国上海市浦东新区健祥路915号华润大厦41室', '2124187359', 42),
('sgpm', '8FG5JDzemG', 0, '2022-12-03 04:21:45', 'dinjie@hotmail.com', '丁杰宏', '男', '中国成都市成华区玉双路6号819号41室', '285688066', 40),
('smfg', 'Ma4KYwWYzO', 0, '2022-11-21 10:20:24', 'lzi@icloud.com', '刘子异', '男', '中国中山天河区大信商圈大信南路85号29栋', '16451112517', 63),
('test', 'test', 0, '2022-11-29 13:25:02', 'yunxyu@yahoo.com', 'hhh', '男', '中国东莞环区南街二巷895号40号楼', '19148111170', 39),
('test1', 'vf9ghvAYvQ', 0, '2022-12-07 23:31:08', 'sonz@gmail.com', '宋震南', '男', '中国北京市海淀区清河中街68号947号华润大厦20室', '104312651', 49),
('tsgy', 'UlBJtdHzoV', 0, '2022-11-07 04:40:30', 'anqimao@yahoo.com', '毛安琪', '女', '中国东莞珊瑚路514号45栋', '76963498585', 51),
('ttpx', 'RLx8lKPkkg', 0, '2022-12-02 06:21:05', 'cui1014@gmail.com', '崔詩涵', '女', '中国上海市黄浦区淮海中路789号4楼', '16737433455', 59),
('twbd', 'qOiqvFzHGe', 0, '2022-12-22 00:54:10', 'yuzhe@mail.com', '郑宇宁', '男', '中国中山紫马岭商圈中山五路827号11室', '7600459407', 38),
('uwuv', 'cBhZY1BFqW', 0, '2022-11-30 10:22:34', 'anqi7@yahoo.com', '郭安琪', '女', '中国东莞珊瑚路51号24室', '18173475877', 47),
('vasa', '3UczyjwCgP', 0, '2022-11-24 05:09:39', 'wuziyi@gmail.com', '武子异', '男', '中国成都市锦江区人民南路四段8号4楼', '17923263639', 39),
('wcin', 'QVkZQkLtaR', 0, '2022-11-20 10:48:09', 'zhiyuanwei2@icloud.com', '魏致远', '男', '中国上海市黄浦区淮海中路19号31栋', '19199767536', 55),
('xktp', '0MLn5fyLuw', 0, '2022-11-23 19:22:27', 'shshen4@gmail.com', '沈詩涵', '女', '中国成都市锦江区人民南路四段353号25栋', '285486492', 47),
('xlfd', 'svW69XYzwt', 0, '2022-11-05 14:08:44', 'luo10@outlook.com', '罗云熙', '男', '中国深圳罗湖区清水河一路781号8号楼', '15351554961', 60),
('xneq', 'oxgHz3L9ks', 0, '2022-12-14 01:56:08', 'yinziy@gmail.com', '尹子异', '男', '中国成都市成华区二仙桥东三路759号49号楼', '282855121', 62),
('yqin', 'ANhArh1a3A', 0, '2022-11-04 13:51:20', 'zouzhennan902@outlook.com', '邹震南', '男', '中国广州市海珠区江南西路62号华润大厦1室', '209838620', 47),
('zghn', 'koCro2q95G', 0, '2022-12-25 23:44:41', 'jh1966@gmail.com', '黄嘉伦', '男', '中国东莞坑美十五巷272号华润大厦9室', '7692517121', 42);

-- --------------------------------------------------------

--
-- 表的结构 `way_list`
--

CREATE TABLE IF NOT EXISTS `way_list` (
  `id` int(11) NOT NULL,
  `way` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `way_list`
--

INSERT INTO `way_list` (`id`, `way`) VALUES
(1, '银行卡'),
(2, '现金'),
(3, '微信'),
(4, '支付宝');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chat`
--
ALTER TABLE `chat`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `collect`
--
ALTER TABLE `collect`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `expend`
--
ALTER TABLE `expend`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `income`
--
ALTER TABLE `income`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `like`
--
ALTER TABLE `like`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sign`
--
ALTER TABLE `sign`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `way_list`
--
ALTER TABLE `way_list`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chat`
--
ALTER TABLE `chat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=35;
--
-- AUTO_INCREMENT for table `collect`
--
ALTER TABLE `collect`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT for table `expend`
--
ALTER TABLE `expend`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT for table `income`
--
ALTER TABLE `income`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `like`
--
ALTER TABLE `like`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT for table `sign`
--
ALTER TABLE `sign`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT for table `way_list`
--
ALTER TABLE `way_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
