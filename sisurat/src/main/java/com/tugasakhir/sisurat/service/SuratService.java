package com.tugasakhir.sisurat.service;

import java.util.List;

import com.tugasakhir.sisurat.model.JenisSuratModel;
import com.tugasakhir.sisurat.model.MatakuliahModel;
import com.tugasakhir.sisurat.model.PengajuanSuratModel;
import com.tugasakhir.sisurat.model.SuratModel;

public interface SuratService
{
	List<JenisSuratModel> selectJenisSurat ();
//	List<MatakuliahModel> selectAllMatakuliah();
	void insertPengajuan(PengajuanSuratModel pengajuan_surat);
}
