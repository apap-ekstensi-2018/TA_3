package com.tugasakhir.sisurat.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tugasakhir.sisurat.model.JenisSuratModel;
import com.tugasakhir.sisurat.model.MahasiswaModel;
import com.tugasakhir.sisurat.model.PegawaiModel;
import com.tugasakhir.sisurat.model.PengajuanSuratModel;
import com.tugasakhir.sisurat.service.MahasiswaService;
import com.tugasakhir.sisurat.service.PegawaiService;
import com.tugasakhir.sisurat.service.SuratService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PageController {
	
	@Autowired
	SuratService suratService;
	
	@Autowired
	MahasiswaService mahasiswaService;
	
	@Autowired
	PegawaiService pegawaiService;
	
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
	public String riwayat(Model model) {
		// get current user logged
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		log.info(auth.getName());
		
		List<PengajuanSuratModel> list_pengajuan_surat = suratService.selectPengajuanSuratByMhs(username);
		MahasiswaModel mahasiswa = mahasiswaService.selectMahasiswa(username);
		PegawaiModel pegawai = pegawaiService.selectPegawai(username);
		
		model.addAttribute("list_pengajuan_surat", list_pengajuan_surat);
		model.addAttribute("mahasiswa", mahasiswa);
		model.addAttribute("pegawai",pegawai);
		return "pengajuan-riwayat";
	}
	
	@RequestMapping("/pengajuan/viewall")
	public String viewall(Model model) {
		List<PengajuanSuratModel> list_pengajuan_surat = suratService.selectAllPengajuanSurat();
		
		model.addAttribute("list_pengajuan_surat", list_pengajuan_surat);
		return "pengajuan-viewall";
	}
	
	@RequestMapping("/pengajuan/tambah")
	public String pengajuan_add(Model model) {
		List<JenisSuratModel> jenis_surat = suratService.selectJenisSurat();
		model.addAttribute("jenis_surat", jenis_surat);
		model.addAttribute("pengajuan_surat", new PengajuanSuratModel());
		return "form-pengajuan-tambah";
	}
	
}
