package com.tugasakhir.sisurat.service;

import java.util.List;
import com.tugasakhir.sisurat.model.MataKuliahModel;

public interface MataKuliahService {
	MataKuliahModel selectMataKuliah(int id);
	MataKuliahModel selectMataKuliah(String kode);
	List<MataKuliahModel> selectAllMataKuliah();
}