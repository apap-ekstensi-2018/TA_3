-- Create syntax for TABLE 'jenis_surat'
CREATE TABLE `jenis_surat` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nama` varchar(35) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'status_surat'
CREATE TABLE `status_surat` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nama` varchar(15) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;