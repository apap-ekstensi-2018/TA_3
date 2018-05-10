package com.tugasakhir.sisurat.service;

import java.util.List;

import com.tugasakhir.sisurat.model.PegawaiModel;

public interface PegawaiService {
	PegawaiModel selectPegawai(String nip);
	List<PegawaiModel> selectAllPegawai ();
}
