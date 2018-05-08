package com.tugasakhir.sisurat.dao;

import java.util.List;

import com.tugasakhir.sisurat.model.MahasiswaModel;
import com.tugasakhir.sisurat.model.MatakuliahModel;
import com.tugasakhir.sisurat.model.PegawaiModel;


public interface SuratDAO {
	//MatakuliahModel selectMatakuliah();
	//List<MatakuliahModel> selectAllMatakuliah();
	MahasiswaModel selectStudent(String npm);
	List<MahasiswaModel> selectAllStudents();
	PegawaiModel selectPegawai(String nip);
}
