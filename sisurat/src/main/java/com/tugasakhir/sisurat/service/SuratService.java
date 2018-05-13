package com.tugasakhir.sisurat.service;

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
//	List<MatakuliahModel> selectAllMatakuliah();
	List<PengajuanSuratModel> selectPengajuanSuratByMhs(String name);
	List<PengajuanSuratModel> selectAllPengajuanSurat();
	void insertPengajuan(PengajuanSuratModel pengajuan_surat);
	void updatePengajuan(PengajuanSuratModel pengajuan_surat);
	SuratModel selectSurat(String no_surat);
	
	int getLastidSurat();
	PengajuanSuratModel selectPengajuan (int idSurat);
	PengajuanSuratModel selectPengajuan (String noSurat);
	
	boolean insertDokumenName(String nama_dokumen, int id);
}
