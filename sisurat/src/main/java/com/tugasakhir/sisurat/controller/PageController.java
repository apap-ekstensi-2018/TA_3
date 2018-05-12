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
public class PageController {
	
	@Autowired
	SuratService suratService;
	
	@RequestMapping("/login")
	public String login() {
		return "form-login";
	}
	
	@RequestMapping("/")
	public String index() {
		return "form-login";
	}

	@RequestMapping("/home")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/pengajuan/riwayat")
	public String riwayat() {
		return "pengajuan-riwayat";
	}
	
	@RequestMapping("/pengajuan/tambah")
	public String pengajuan_add(Model model) {
		List<JenisSuratModel> jenis_surat = suratService.selectJenisSurat();
		model.addAttribute("jenis_surat", jenis_surat);
		model.addAttribute("pengajuan_surat", new PengajuanSuratModel());
		return "form-pengajuan-tambah";
	}
	@RequestMapping("/pengajuan/viewall")
	public String viewall(Model model) {
		List<PengajuanSuratModel> allSurat = suratService.selectAllPengajuanSurat();
		model.addAttribute("pengajuan_surat2", allSurat);
		
		return "viewall-surat";
	}
	
	
}
