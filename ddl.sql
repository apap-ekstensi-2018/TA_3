-- MySQL dump 10.13  Distrib 5.7.21, for osx10.12 (x86_64)
--
-- Host: localhost    Database: si_surat
-- ------------------------------------------------------
-- Server version	5.7.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `jenis_surat`
--

DROP TABLE IF EXISTS `jenis_surat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jenis_surat` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nama` varchar(35) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jenis_surat`
--

LOCK TABLES `jenis_surat` WRITE;
/*!40000 ALTER TABLE `jenis_surat` DISABLE KEYS */;
INSERT INTO `jenis_surat` VALUES (1,'Surat Izin Ikut Kegiatan'),(2,'Surat Izin UTS/UAS'),(3,'Surat Keterangan Asisten Dosen'),(4,'Surat Keterangan Berkelakuan Baik'),(5,'Surat Keterangan Lulus'),(6,'Surat Rekomendasi Beasiswa'),(7,'Transkrip Nilai');
/*!40000 ALTER TABLE `jenis_surat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pengajuan_surat`
--

DROP TABLE IF EXISTS `pengajuan_surat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pengajuan_surat` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `no_surat` varchar(255) NOT NULL,
  `username_pengaju` varchar(255) NOT NULL,
  `tanggal_mohon` date DEFAULT NULL,
  `id_jenis_surat` int(11) unsigned DEFAULT NULL,
  `keterangan` text,
  `alasan_izin` text,
  `tanggal_mulai_izin` date DEFAULT NULL,
  `tanggal_selesai_izin` date DEFAULT NULL,
  `id_matkul_terkait` int(11) DEFAULT NULL,
  `username_pegawai` varchar(255) DEFAULT NULL,
  `id_status_surat` int(11) unsigned DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `peng_sur_usern_peng_user_acc_username` (`username_pengaju`),
  KEY `peng_sur_usern_peg_user_acc_username` (`username_pegawai`),
  KEY `peng_sur_id_jen_sur_jenis_surat_id` (`id_jenis_surat`),
  KEY `peng_sur_id_stat_sur_status_surat_id` (`id_status_surat`),
  CONSTRAINT `peng_sur_id_jen_sur_jenis_surat_id` FOREIGN KEY (`id_jenis_surat`) REFERENCES `jenis_surat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `peng_sur_id_stat_sur_status_surat_id` FOREIGN KEY (`id_status_surat`) REFERENCES `status_surat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `peng_sur_usern_peg_user_acc_username` FOREIGN KEY (`username_pegawai`) REFERENCES `user_account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `peng_sur_usern_peng_user_acc_username` FOREIGN KEY (`username_pengaju`) REFERENCES `user_account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pengajuan_surat`
--

LOCK TABLES `pengajuan_surat` WRITE;
/*!40000 ALTER TABLE `pengajuan_surat` DISABLE KEYS */;
INSERT INTO `pengajuan_surat` VALUES (2,'1','1506721825',NULL,1,'scsc','males',NULL,NULL,1,NULL,1),(5,'PS-3','1506721756','2018-05-12',1,'HELLO','CUTI','2018-05-30','2018-05-31',1,NULL,1);
/*!40000 ALTER TABLE `pengajuan_surat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_surat`
--

DROP TABLE IF EXISTS `status_surat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status_surat` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nama` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_surat`
--

LOCK TABLES `status_surat` WRITE;
/*!40000 ALTER TABLE `status_surat` DISABLE KEYS */;
INSERT INTO `status_surat` VALUES (1,'Baru Diajukan'),(2,'Ditolak'),(3,'Diproses'),(4,'Selesai');
/*!40000 ALTER TABLE `status_surat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES ('1290578781','password','dosen'),('1290578783','password','dosen'),('1290578785','password','dosen'),('1290578787','password','dosen'),('1290578789','password','dosen'),('1290578791','password','dosen'),('1290578793','password','dosen'),('1290578795','password','dosen'),('1290578797','password','dosen'),('1290578799','password','dosen'),('1290578801','password','dosen'),('1290578803','password','dosen'),('1290578805','password','dosen'),('1290578807','password','dosen'),('1290578809','password','dosen'),('1290578811','password','dosen'),('1290578813','password','dosen'),('1290578815','password','dosen'),('1290578817','password','dosen'),('1290578819','password','dosen'),('1290578821','password','dosen'),('1290578823','password','dosen'),('1290578825','password','dosen'),('1290578827','password','dosen'),('1290578829','password','dosen'),('1290578831','password','dosen'),('1290578833','password','dosen'),('1290578835','password','dosen'),('1290578837','password','dosen'),('1290578839','password','dosen'),('1290578841','password','dosen'),('1290578843','password','dosen'),('1290578845','password','dosen'),('1290578897','password','dosen'),('1406575815','password','staf'),('1506689692','password','staf'),('1506721756','password','ROLE_MAHASISWA'),('1506721762','password','mahasiswa'),('1506721775','password','mahasiswa'),('1506721781','password','mahasiswa'),('1506721794','password','mahasiswa'),('1506721806','password','mahasiswa'),('1506721812','password','mahasiswa'),('1506721825','password','mahasiswa'),('1506721831','password','mahasiswa'),('1506721844','password','mahasiswa'),('1506721863','password','mahasiswa'),('1506721876','password','mahasiswa'),('1506721882','password','mahasiswa'),('1506721895','password','mahasiswa'),('1506721900','password','mahasiswa'),('1506723231','password','staf'),('1506737823','password','staf'),('1506757642','password','mahasiswa'),('1506757655','password','mahasiswa'),('1506757661','password','mahasiswa'),('1506757680','password','mahasiswa'),('1506757693','password','mahasiswa');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-12 12:09:57
