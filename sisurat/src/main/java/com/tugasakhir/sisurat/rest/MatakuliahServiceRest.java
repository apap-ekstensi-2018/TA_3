package com.tugasakhir.sisurat.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.tugasakhir.sisurat.dao.SuratDAO;
import com.tugasakhir.sisurat.dao.SuratMapper;
import com.tugasakhir.sisurat.model.JenisSuratModel;
import com.tugasakhir.sisurat.model.MatakuliahModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Primary
public class MatakuliahServiceRest {
	@Autowired
	private SuratDAO suratDAO;
	
//	@Override
//	public List<MatakuliahModel>selectAllMatakuliah()
//	{
//		log.info("Rest - pilih semua matakuliah");
//		return suratDAO.selectAllMatakuliah();
//	}
}
