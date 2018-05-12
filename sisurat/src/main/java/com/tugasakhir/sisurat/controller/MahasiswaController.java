package com.tugasakhir.sisurat.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tugasakhir.sisurat.model.JenisSuratModel;
import com.tugasakhir.sisurat.model.PengajuanSuratModel;
import com.tugasakhir.sisurat.service.SuratService;


@Controller
public class MahasiswaController {
	
	@Autowired
	SuratService suratService;
	
	@RequestMapping("/mahasiswa")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/mahasiswa/pengajuan/riwayat")
	public String riwayat() {
		return "pengajuan-riwayat";
	}
	
	@RequestMapping("/mahasiswa/pengajuan/tambah")
	public String pengajuan_add(Model model) {
		List<JenisSuratModel> jenis_surat = suratService.selectJenisSurat();
		model.addAttribute("jenis_surat", jenis_surat);
		model.addAttribute("pengajuan_surat", new PengajuanSuratModel());
		return "form-pengajuan-tambah";
	}
}