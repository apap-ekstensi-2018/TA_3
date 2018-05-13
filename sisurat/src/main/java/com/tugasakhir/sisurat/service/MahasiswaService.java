package com.tugasakhir.sisurat.service;

import java.util.List;

import com.tugasakhir.sisurat.model.AsistenResponseModel;
import com.tugasakhir.sisurat.model.MahasiswaModel;

public interface MahasiswaService {
	MahasiswaModel selectMahasiswa(String npm);
	List<MahasiswaModel> selectAllMahasiswa();
	AsistenResponseModel checkIsAsistant(String id);
}
