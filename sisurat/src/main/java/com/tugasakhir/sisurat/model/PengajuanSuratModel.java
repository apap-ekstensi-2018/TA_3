package com.tugasakhir.sisurat.model;

import java.util.Date;

public class PengajuanSuratModel {
	private int id;
	private String no_surat;
	private String username_pengaju;
	private Date tanggal_mohon;
	private int id_jenis_surat;
	private String keterangan;
	private String alasan_izin;
	private Date tanggal_mulai_izin;
	private Date tanggal_selesai_izin;
	private int id_matkul_terkait;
	private int username_pegawai;
	private int id_status_surat;
}
