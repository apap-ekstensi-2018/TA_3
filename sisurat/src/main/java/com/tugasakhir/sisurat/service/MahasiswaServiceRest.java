package com.tugasakhir.sisurat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.tugasakhir.sisurat.dao.MahasiswaDAO;
import com.tugasakhir.sisurat.model.MahasiswaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Primary
public class MahasiswaServiceRest implements MahasiswaService {
	
	@Autowired
	private MahasiswaDAO mahasiswaDAO;

	@Override
	public MahasiswaModel selectMahasiswa(String npm) {
		log.info("REST - select mahasiswa with npm " + npm);
		return mahasiswaDAO.selectMahasiswa(npm);
	}

	@Override
	public List<MahasiswaModel> selectAllMahasiswa() {
		log.info("REST - select all mahasiswa");
		return mahasiswaDAO.selectAllMahasiswa();
	}

}
