package com.tugasakhir.sisurat.service;

import java.util.Date;
import java.util.List;

import com.tugasakhir.sisurat.model.JenisSuratModel;
import com.tugasakhir.sisurat.model.MataKuliahModel;
import com.tugasakhir.sisurat.model.PengajuanSuratModel;
import com.tugasakhir.sisurat.model.StatusSuratModel;
import com.tugasakhir.sisurat.model.SuratModel;

public interface SuratService
{
	List<JenisSuratModel> selectJenisSurat ();
	List<StatusSuratModel> selectStatusSurat ();
	List<PengajuanSuratModel> selectPengajuanSuratByMhs(String name);
	List<PengajuanSuratModel> selectAllPengajuanSurat();
	void insertPengajuan(PengajuanSuratModel pengajuan_surat);
	void updatePengajuan(PengajuanSuratModel pengajuan_surat);
	SuratModel selectSurat(String no_surat);
	List<SuratModel> selectAllSurats();
	
	int getLastidSurat();
	PengajuanSuratModel selectPengajuan (int idSurat);
	PengajuanSuratModel selectPengajuan (String noSurat);
	
	boolean insertDokumenName(String nama_dokumen, int id);
	List<PengajuanSuratModel> selectPengajuanSuratByJenisSuratMhs(int id_jenis_surat,String name);
	List<PengajuanSuratModel> selectPengajuanSuratByStatusSuratMhs(int id_status_surat,String name);
	List<PengajuanSuratModel> selectPengajuanSuratByTanggalSuratMhs(Date tanggal_mulai_izin,Date tanggal_selesai_izin,String name);
	List<PengajuanSuratModel> selectPengajuanSuratByJenisSurat(int id_jenis_surat);
	List<PengajuanSuratModel> selectPengajuanSuratByStatusSurat(int id_status_surat);
	List<PengajuanSuratModel> selectPengajuanSuratByTanggalSurat(Date tanggal_mulai_izin,Date tanggal_selesai_izin);
}
