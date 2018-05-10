package com.tugasakhir.sisurat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.tugasakhir.sisurat.dao.PegawaiDAO;
import com.tugasakhir.sisurat.model.PegawaiModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Primary
public class PegawaiServiceRest implements PegawaiService{
	@Autowired
	private PegawaiDAO pegawaiDAO;

	@Override
	public PegawaiModel selectPegawai (String nip) {
		log.info ("REST - select pegawai with nip" + nip);
		return pegawaiDAO.selectPegawai(nip);
	}
	
	@Override
	public List <PegawaiModel> selectAllPegawai(){
		log.info ("REST - select all staff");
		return pegawaiDAO.selectAllPegawai();
	}
}
