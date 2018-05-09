package com.tugasakhir.sisurat.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tugasakhir.sisurat.model.PegawaiModel;
import com.tugasakhir.sisurat.service.PegawaiService;

@RestController
@RequestMapping ("/rest")
public class PegawaiRestController {
	@Autowired
	PegawaiService pegawaiService;
	
	@RequestMapping ("pengajuan/riwayat/{nip}")
	public PegawaiModel viewRiwayat (@PathVariable (value="nip")String nip){
		PegawaiModel pegawaiModel = pegawaiService.selectPegawai(nip);
		return pegawaiModel;
	}
}
