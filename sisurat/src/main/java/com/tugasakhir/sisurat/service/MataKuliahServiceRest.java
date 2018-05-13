package com.tugasakhir.sisurat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.tugasakhir.sisurat.dao.MahasiswaDAO;
import com.tugasakhir.sisurat.dao.MataKuliahDAO;
import com.tugasakhir.sisurat.model.MataKuliahModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Primary
public class MataKuliahServiceRest implements MataKuliahService {
	
	@Autowired
	private MataKuliahDAO mataKuliahDAO;

	@Override
	public MataKuliahModel selectMataKuliah(int id) {
		log.info("REST - select mata kuliah by Id");
		return mataKuliahDAO.selectMatakuliahById(id);
	}

	@Override
	public MataKuliahModel selectMataKuliah(String kode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MataKuliahModel> selectAllMataKuliah() {
		log.info("REST - select all mata kuliah");
		return mataKuliahDAO.selectAllMatakuliah();
	}

}
