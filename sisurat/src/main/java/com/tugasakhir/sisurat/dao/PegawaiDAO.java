package com.tugasakhir.sisurat.dao;

import java.util.List;

import com.tugasakhir.sisurat.model.PegawaiModel;

public interface PegawaiDAO {
	PegawaiModel selectPegawai (String nip);
	List <PegawaiModel> selectAllPegawai();
}
