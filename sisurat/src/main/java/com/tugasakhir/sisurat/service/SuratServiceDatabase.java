package com.tugasakhir.sisurat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tugasakhir.sisurat.dao.SuratMapper;
import com.tugasakhir.sisurat.model.JenisSuratModel;
import com.tugasakhir.sisurat.model.PengajuanSuratModel;
import com.tugasakhir.sisurat.model.SuratModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class SuratServiceDatabase implements SuratService
{
	@Autowired
	private SuratMapper suratMapper;

	@Override
	public List<JenisSuratModel> selectJenisSurat() {
		log.info("Tampilkan data jenis surat");
		return suratMapper.selectJenisSurat();
	}
}
