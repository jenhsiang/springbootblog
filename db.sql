SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `mdContent` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `summary` text,
  `state` int DEFAULT NULL COMMENT '0表示草稿箱，1表示已發表，2表示已删除',
  `pageView` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
BEGIN;
INSERT INTO `article` VALUES (123, 'ff', 'ff', 'ff', 1, 0);
INSERT INTO `article` VALUES (125, 'ddddd', 'sssss', 'ffff', 1, 0);
INSERT INTO `article` VALUES (126, ' 好好玩', '這是測試', '測試', 1, 0);
INSERT INTO `article` VALUES (127, 'dddd', 'd', 'dddd', 1, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
