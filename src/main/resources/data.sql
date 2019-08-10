# CREATE DATABASE IF NOT EXISTS `wam`;
# USE `wam`;
#
# --
# -- Clean old db if exist
# --
#
# DROP TABLE IF EXISTS `users`;
# DROP TABLE IF EXISTS `workshops`;
# DROP TABLE IF EXISTS `appointments`;
#
# --
# -- Table structure for table `users`
# --
#
# CREATE TABLE `users`
# (
#     `user_id`     int(11) unsigned NOT NULL AUTO_INCREMENT,
# #     `username`    varchar(255)     NOT NULL,
# #     `email`       varchar(255)     NOT NULL,
# #     `city`        varchar(255) DEFAULT NULL,
# #     `postal_code` varchar(255) DEFAULT NULL,
# #     `country`     varchar(255) DEFAULT NULL,
#     PRIMARY KEY (`user_id`)
# ) ENGINE = InnoDB
#   AUTO_INCREMENT = 9
#   DEFAULT CHARSET = utf8;
#
# --
# -- Table structure for table `workshops`
# --
#
# # CREATE TABLE `workshops`
# # (
# #     `workshop_id`  int(11) unsigned NOT NULL AUTO_INCREMENT,
# #     `company_name` varchar(255)     DEFAULT NULL,
# #     `trademarks`   varchar(255)     DEFAULT NULL,
# #     `city`         int(11)          DEFAULT NULL,
# #     `postal_code`  varchar(255)     DEFAULT NULL,
# #     `country`      varchar(255)     DEFAULT NULL,
# #     `user_id`      int(11) unsigned DEFAULT NULL,
# #     PRIMARY KEY (`workshop_id`)
# # #     KEY `user_id` (`user_id`),
# # #     CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
# # ) ENGINE = InnoDB
# #   DEFAULT CHARSET = utf8;
#
# --
# -- Table structure for table `appointments`
# --
#
# # CREATE TABLE `appointments`
# # (
# #     `appointment_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
# #     `username`       varchar(255)     DEFAULT NULL,
# #     `trademarks`     varchar(255)     DEFAULT NULL,
# #     `company_name`   varchar(255)     DEFAULT NULL,
# #     `time`           varchar(255)     DEFAULT NULL,
# #     `user_id`        int(11) unsigned DEFAULT NULL,
# #     PRIMARY KEY (`appointment_id`)
# # #     KEY `user_id` (`user_id`),
# # #     CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
# # ) ENGINE = InnoDB
# #   DEFAULT CHARSET = utf8;
