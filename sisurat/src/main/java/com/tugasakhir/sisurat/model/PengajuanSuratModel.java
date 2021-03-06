package com.tugasakhir.sisurat.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PengajuanSuratModel {
	private int id;
	private String no_surat;
	private String username_pengaju;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String tanggal_mohon;
	
	private Integer id_jenis_surat;
	private String keterangan;
	private String alasan_izin;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String tanggal_mulai_izin;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String tanggal_selesai_izin;
		
	private Integer id_matkul_terkait;
	
	private Integer id_status_surat;
	private String username_pegawai;
	private String status;
	private String nama_dokumen;
	
	private StatusSuratModel status_surat;
	private JenisSuratModel jenis_surat;
	private MahasiswaModel pengaju;
	private PegawaiModel pegawai;
	private MataKuliahModel mata_kuliah;
}
