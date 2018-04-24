# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.19)
# Database: sisurat
# Generation Time: 2018-04-24 14:06:20 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table jenis_surat
# ------------------------------------------------------------

DROP TABLE IF EXISTS `jenis_surat`;

CREATE TABLE `jenis_surat` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nama` varchar(35) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table pengajuan_surat
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pengajuan_surat`;

CREATE TABLE `pengajuan_surat` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `no_surat` varchar(255) NOT NULL DEFAULT '',
  `username_pengaju` varchar(255) NOT NULL DEFAULT '',
  `tanggal_mohon` date DEFAULT NULL,
  `id_jenis_surat` int(11) unsigned DEFAULT NULL,
  `keterangan` text,
  `alasan_izin` text,
  `tanggal_mulai_izin` date DEFAULT NULL,
  `tanggal_selesai_izin` date DEFAULT NULL,
  `id_matkul_terkait` int(11) DEFAULT NULL,
  `username_pegawai` varchar(255) DEFAULT '',
  `id_status_surat` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `peng_sur_usern_peng_user_acc_username` (`username_pengaju`),
  KEY `peng_sur_usern_peg_user_acc_username` (`username_pegawai`),
  KEY `peng_sur_id_jen_sur_jenis_surat_id` (`id_jenis_surat`),
  KEY `peng_sur_id_stat_sur_status_surat_id` (`id_status_surat`),
  CONSTRAINT `peng_sur_id_jen_sur_jenis_surat_id` FOREIGN KEY (`id_jenis_surat`) REFERENCES `jenis_surat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `peng_sur_id_stat_sur_status_surat_id` FOREIGN KEY (`id_status_surat`) REFERENCES `status_surat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `peng_sur_usern_peg_user_acc_username` FOREIGN KEY (`username_pegawai`) REFERENCES `user_account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `peng_sur_usern_peng_user_acc_username` FOREIGN KEY (`username_pengaju`) REFERENCES `user_account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table status_surat
# ------------------------------------------------------------

DROP TABLE IF EXISTS `status_surat`;

CREATE TABLE `status_surat` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nama` varchar(15) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table user_account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_account`;

CREATE TABLE `user_account` (
  `username` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `role` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
