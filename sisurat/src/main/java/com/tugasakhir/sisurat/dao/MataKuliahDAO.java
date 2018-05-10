package com.tugasakhir.sisurat.dao;

import java.util.List;

import com.tugasakhir.sisurat.model.MataKuliahModel;

public interface MataKuliahDAO {
	MataKuliahModel selectMatakuliahById(int id);
	MataKuliahModel selectMatakuliahByKode(String kode);
	List<MataKuliahModel> selectAllMatakuliah();
}
