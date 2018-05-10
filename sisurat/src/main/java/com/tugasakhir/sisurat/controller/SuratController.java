package com.tugasakhir.sisurat.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tugasakhir.sisurat.model.JenisSuratModel;
import com.tugasakhir.sisurat.model.PengajuanSuratModel;
import com.tugasakhir.sisurat.model.SuratModel;
import com.tugasakhir.sisurat.service.SuratService;

@Controller
public class SuratController {

	@Autowired
	SuratService suratDAO;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/home")
	public String home() {
		return "index";
	}

	@RequestMapping("/pengajuan/tambah")
	public String pengajuan_add(Model model) {
		List<JenisSuratModel> jenis_surat = suratDAO.selectJenisSurat();
		model.addAttribute("jenis_surat", jenis_surat);
		model.addAttribute("pengajuan_surat", new PengajuanSuratModel());

		return "form-pengajuan-tambah";
	}

	@RequestMapping("/pengajuan/tambah/submit")
	public String addSubmit(@ModelAttribute("pengajuan_surat") PengajuanSuratModel pengajuan_surat, Model model) {
		if (pengajuan_surat.getAlasan_izin().isEmpty()) {
			pengajuan_surat.setAlasan_izin("");
			pengajuan_surat.setTanggal_mulai_izin("0000-00-00");
			pengajuan_surat.setTanggal_selesai_izin("0000-00-00");
			pengajuan_surat.setId_matkul_terkait(0);
		}
		System.out.println("ID SURAT: "+pengajuan_surat.getId_jenis_surat());
		// set no surat
		int lastIdSurat = 0;
		try {
			lastIdSurat = suratDAO.getLastidSurat();
		} catch (Exception e) {
			e.printStackTrace();	
		}
		int no_surat = lastIdSurat+1;
		pengajuan_surat.setNo_surat("PS-" +no_surat);

		// get current logged in username
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();

		// set username pengaju
		pengajuan_surat.setUsername_pengaju(name);

		// get current date
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();

		// set tanggal mohon from current date
		pengajuan_surat.setTanggal_mohon(dtf.format(localDate).toString());

		suratDAO.insertPengajuan(pengajuan_surat);
		model.addAttribute("pengajuan_surat", pengajuan_surat);
		model.addAttribute("standardDate", new Date());
		return "success-add";
	}

}
