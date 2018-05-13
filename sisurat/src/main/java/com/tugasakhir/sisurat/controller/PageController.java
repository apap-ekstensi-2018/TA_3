package com.tugasakhir.sisurat.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tugasakhir.sisurat.model.JenisSuratModel;
import com.tugasakhir.sisurat.model.MataKuliahModel;
import com.tugasakhir.sisurat.model.PengajuanSuratModel;
import com.tugasakhir.sisurat.service.MahasiswaService;
import com.tugasakhir.sisurat.service.MahasiswaServiceRest;
import com.tugasakhir.sisurat.service.SuratService;

import lombok.extern.slf4j.Slf4j;
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
	
	static List<MataKuliahModel> mataKuliahList;
	
	@Autowired
	PegawaiService pegawaiService;
	
	@RequestMapping("/login")
	public String login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	    if (!(auth instanceof AnonymousAuthenticationToken))
	    {
	        return "redirect:/home";
	    }
		return "form-login";
	}
	
	@RequestMapping("/")
	public String index() {
		return "redirect:/home";
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
		for(int i=0;i<list_pengajuan_surat.size();i++) {
			MahasiswaModel mahasiswa = mahasiswaService.selectMahasiswa(list_pengajuan_surat.get(i).getUsername_pengaju());
			list_pengajuan_surat.get(i).setPengaju(mahasiswa);
			if(list_pengajuan_surat.get(i).getUsername_pegawai()!=null) {
				PegawaiModel pegawai = pegawaiService.selectPegawai(list_pengajuan_surat.get(i).getUsername_pegawai());
				list_pengajuan_surat.get(i).setPegawai(pegawai);
			}
		}
		MahasiswaModel mahasiswa = mahasiswaService.selectMahasiswa(username);
		
		model.addAttribute("list_pengajuan_surat", list_pengajuan_surat);
		model.addAttribute("mahasiswa", mahasiswa);
		
		return "pengajuan-riwayat";
	}

	public void getMahasiswaList() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		mataKuliahList = mahasiswaService.selectMahasiswa(username).getMataKuliahList();
	}
	
	
	@RequestMapping("/pengajuan/viewall")
	public String viewall(Model model) {
		// get current user logged
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		log.info(auth.getName());
		
		List<PengajuanSuratModel> list_pengajuan_surat = suratService.selectAllPengajuanSurat();
		PegawaiModel pegawai = pegawaiService.selectPegawai(username);
		model.addAttribute("list_pengajuan_surat", list_pengajuan_surat);
		model.addAttribute("pegawai",pegawai);
		return "pengajuan-viewall";
	}
	
	@RequestMapping(value="/pengajuan/tambah", method = RequestMethod.GET)
	public String pengajuan_add(Model model) {
		getMahasiswaList();
		log.info("1");
		model = PageController.validateJenisSurat(model, suratService);
		return "form-pengajuan-tambah";
	}
	
	public static Model validateJenisSurat(Model model, SuratService surat) {
		// get username from auth
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		// validate
		boolean dummyIsAsistant = true;
		try {
		if(Integer.parseInt(username)% 2 == 1) {
			dummyIsAsistant = false;
		}
		}catch(Exception e) {
			dummyIsAsistant = false;
		}
		
		List<JenisSuratModel> jenis_surat = surat.selectJenisSurat();
		List<JenisSuratModel> dummyJenisSurat = surat.selectJenisSurat();
		for(int index = 0; index < jenis_surat.size();index++) {
			if(dummyIsAsistant == false && jenis_surat.get(index).getId() == 3) {
				dummyJenisSurat.remove(dummyJenisSurat.get(index));
			}
		}
		
		model.addAttribute("mata_kuliah", mataKuliahList);
		model.addAttribute("jenis_surat", dummyJenisSurat);
		model.addAttribute("pengajuan_surat", new PengajuanSuratModel());
		return model;
	}

}
