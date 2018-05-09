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

create database if not exists `si_surat`;
use  `si_surat`;

# Dump of table jenis_surat
# ------------------------------------------------------------

DROP TABLE IF EXISTS `jenis_surat`;

CREATE TABLE `jenis_surat` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nama` varchar(35) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `jenis_surat` (`id`, `nama`) VALUES
(1, 'Surat Izin Ikut Kegiatan'),
(2, 'Surat Izin UTS/UAS'),
(3, 'Surat Keterangan Asisten Dosen'),
(4, 'Surat Keterangan Berkelakuan Baik'),
(5, 'Surat Keterangan Lulus'),
(6, 'Surat Rekomendasi Beasiswa'),
(7, 'Transkrip Nilai');


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


INSERT INTO `status_surat` (`id`, `nama`) VALUES
(1, 'Baru Diajukan'),
(2, 'Ditolak'),
(3, 'Diproses'),
(4, 'Selesai');



# Dump of table user_account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_account`;

CREATE TABLE `user_account` (
  `username` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `role` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user_account` (`username`, `password`, `role`) VALUES
('1506721756', 'password', 'mahasiswa'),
('1506721762', 'password', 'mahasiswa'),
('1506721775', 'password', 'mahasiswa'),
('1506721781', 'password', 'mahasiswa'),
('1506721794', 'password', 'mahasiswa'),
('1506721806', 'password', 'mahasiswa'),
('1506721812', 'password', 'mahasiswa'),
('1506721825', 'password', 'mahasiswa'),
('1506721831', 'password', 'mahasiswa'),
('1506721844', 'password', 'mahasiswa'),
('1506721863', 'password', 'mahasiswa'),
('1506721876', 'password', 'mahasiswa'),
('1506721882', 'password', 'mahasiswa'),
('1506721895', 'password', 'mahasiswa'),
('1506721900', 'password', 'mahasiswa'),
('1506757642', 'password', 'mahasiswa'),
('1506757655', 'password', 'mahasiswa'),
('1506757661', 'password', 'mahasiswa'),
('1506757680', 'password', 'mahasiswa'),
('1506757693', 'password', 'mahasiswa'),
('1290578809', 'password', 'dosen'),
('1290578803', 'password', 'dosen'),
('1290578805', 'password', 'dosen'),
('1290578897', 'password', 'dosen'),
('1290578843', 'password', 'dosen'),
('1290578845', 'password', 'dosen'),
('1290578815', 'password', 'dosen'),
('1290578817', 'password', 'dosen'),
('1290578811', 'password', 'dosen'),
('1290578813', 'password', 'dosen'),
('1290578823', 'password', 'dosen'),
('1290578825', 'password', 'dosen'),
('1290578819', 'password', 'dosen'),
('1290578821', 'password', 'dosen'),
('1290578831', 'password', 'dosen'),
('1290578833', 'password', 'dosen'),
('1290578827', 'password', 'dosen'),
('1290578829', 'password', 'dosen'),
('1290578839', 'password', 'dosen'),
('1290578841', 'password', 'dosen'),
('1290578835', 'password', 'dosen'),
('1290578837', 'password', 'dosen'),
('1290578783', 'password', 'dosen'),
('1290578785', 'password', 'dosen'),
('1290578781', 'password', 'dosen'),
('1290578791', 'password', 'dosen'),
('1290578793', 'password', 'dosen'),
('1290578787', 'password', 'dosen'),
('1290578789', 'password', 'dosen'),
('1290578799', 'password', 'dosen'),
('1290578801', 'password', 'dosen'),
('1290578795', 'password', 'dosen'),
('1290578797', 'password', 'dosen'),
('1290578807', 'password', 'dosen'),
('1506737823', 'password', 'staf'),
('1506689692', 'password', 'staf'),
('1506723231', 'password', 'staf'),
('1406575815', 'password', 'staf');

