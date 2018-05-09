package com.tugasakhir.sisurat.dao;

import java.util.List;

import com.tugasakhir.sisurat.model.MahasiswaModel;
import com.tugasakhir.sisurat.model.MataKuliahModel;
import com.tugasakhir.sisurat.model.PegawaiModel;


public interface SuratDAO {
	MataKuliahModel selectMatakuliah(int id );
	List<MataKuliahModel> selectAllMatakuliah();
	MahasiswaModel selectStudent(String npm);
	List<MahasiswaModel> selectAllStudents();
	PegawaiModel selectPegawai(String nip);
}
