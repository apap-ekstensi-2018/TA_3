package com.tugasakhir.sisurat.dao;

import java.util.List;

import com.tugasakhir.sisurat.model.MahasiswaModel;

public interface MahasiswaDAO {
	MahasiswaModel selectMahasiswa(String npm);
	List<MahasiswaModel> selectAllMahasiswa();
}
