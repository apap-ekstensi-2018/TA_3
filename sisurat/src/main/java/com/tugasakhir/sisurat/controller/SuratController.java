package com.tugasakhir.sisurat.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.xml.ws.RequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tugasakhir.sisurat.model.JenisSuratModel;
import com.tugasakhir.sisurat.model.MahasiswaModel;
import com.tugasakhir.sisurat.model.MataKuliahModel;
import com.tugasakhir.sisurat.model.PegawaiModel;
import com.tugasakhir.sisurat.model.PengajuanSuratModel;
import com.tugasakhir.sisurat.model.StatusSuratModel;
import com.tugasakhir.sisurat.model.SuratModel;
import com.tugasakhir.sisurat.service.MahasiswaService;
import com.tugasakhir.sisurat.service.MahasiswaServiceRest;
import com.tugasakhir.sisurat.service.MataKuliahService;
import com.tugasakhir.sisurat.service.PegawaiService;
import com.tugasakhir.sisurat.service.SuratService;
import com.tugasakhir.sisurat.storage.StorageService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class SuratController {

	@Autowired
	SuratService suratDAO;

	@Autowired
	MahasiswaService mahasiswaService;

	@Autowired
	PegawaiService pegawaiService;

	@Autowired
	 MataKuliahService mkService;
	
	@Autowired
	StorageService storageService;
	
	@RequestMapping(value="/pengajuan/tambah", method= RequestMethod.POST)
	public String addSubmit(@ModelAttribute("pengajuan_surat") PengajuanSuratModel pengajuan_surat, Model model) {
		if (pengajuan_surat.getAlasan_izin().isEmpty()) {
			pengajuan_surat.setAlasan_izin("");
			pengajuan_surat.setTanggal_mulai_izin(null);
			pengajuan_surat.setTanggal_selesai_izin(null);
			pengajuan_surat.setId_matkul_terkait(null);
		}
		pengajuan_surat.setNo_surat(null);

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
		
		model = PageController.validateJenisSurat(model, suratDAO);
		
		return "form-pengajuan-tambah";
	}

	@RequestMapping(value="/pengajuan/riwayat", method=RequestMethod.GET)
	public String riwayat(@ModelAttribute("pengajuan_surat") PengajuanSuratModel pengajuan_surat, Model model) {
		// get current user logged
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		log.info(auth.getName());
		
		List<JenisSuratModel> jenis_surat = suratDAO.selectJenisSurat();
		List<StatusSuratModel> status_surat = suratDAO.selectStatusSurat();
		List<PengajuanSuratModel> list_pengajuan_surat = suratDAO.selectPengajuanSuratByMhs(username);
		log.info(list_pengajuan_surat.toString());
		for(int i=0;i<list_pengajuan_surat.size();i++) {
			MahasiswaModel mahasiswa = mahasiswaService.selectMahasiswa(list_pengajuan_surat.get(i).getUsername_pengaju());
			list_pengajuan_surat.get(i).setPengaju(mahasiswa);
			if(list_pengajuan_surat.get(i).getUsername_pegawai()!=null) {
				PegawaiModel pegawai = pegawaiService.selectPegawai(list_pengajuan_surat.get(i).getUsername_pegawai());
				list_pengajuan_surat.get(i).setPegawai(pegawai);
			}
		}
		model.addAttribute("jenis_surat", jenis_surat);
		model.addAttribute("status_surat", status_surat);
		model.addAttribute("list_pengajuan_surat", list_pengajuan_surat);
		return "pengajuan-riwayat";
	}

	@RequestMapping (value="/pengajuan/riwayat/filterByJenis", method= RequestMethod.GET)
	public String filterByJenisMhs (Model model,@ModelAttribute("pengajuan_surat") PengajuanSuratModel pengajuan_surat,
			@RequestParam(value="id_jenis_surat", required=true) Integer id_jenis_surat) 
	{
			// get current user logged
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			log.info(auth.getName());
			
			List<JenisSuratModel> jenis_surat = suratDAO.selectJenisSurat();
			List<StatusSuratModel> status_surat = suratDAO.selectStatusSurat();
			List<PengajuanSuratModel> list_pengajuan_surat = suratDAO.selectPengajuanSuratByJenisSuratMhs(id_jenis_surat,username);
			log.info(list_pengajuan_surat.toString());
			for(int i=0;i<list_pengajuan_surat.size();i++) {
				MahasiswaModel mahasiswa = mahasiswaService.selectMahasiswa(list_pengajuan_surat.get(i).getUsername_pengaju());
				list_pengajuan_surat.get(i).setPengaju(mahasiswa);
				if(list_pengajuan_surat.get(i).getUsername_pegawai()!=null) {
					PegawaiModel pegawai = pegawaiService.selectPegawai(list_pengajuan_surat.get(i).getUsername_pegawai());
					list_pengajuan_surat.get(i).setPegawai(pegawai);
				}
			}
			model.addAttribute("jenis_surat", jenis_surat);
			model.addAttribute("status_surat", status_surat);
			model.addAttribute("list_pengajuan_surat", list_pengajuan_surat);
		return "pengajuan-riwayat";
	}
	
	@RequestMapping (value="/pengajuan/riwayat/filterByStatus", method= RequestMethod.GET)
	public String filterByStatusMhs (Model model,@ModelAttribute("pengajuan_surat") PengajuanSuratModel pengajuan_surat,
			@RequestParam(value="id_status_surat", required=true) Integer id_status_surat) 
	{
			// get current user logged
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			List<JenisSuratModel> jenis_surat = suratDAO.selectJenisSurat();
			List<StatusSuratModel> status_surat = suratDAO.selectStatusSurat();
			List<PengajuanSuratModel> list_pengajuan_surat = suratDAO.selectPengajuanSuratByStatusSuratMhs(id_status_surat,username);
			for(int i=0;i<list_pengajuan_surat.size();i++) {
				MahasiswaModel mahasiswa = mahasiswaService.selectMahasiswa(list_pengajuan_surat.get(i).getUsername_pengaju());
				list_pengajuan_surat.get(i).setPengaju(mahasiswa);
				if(list_pengajuan_surat.get(i).getUsername_pegawai()!=null) {
					PegawaiModel pegawai = pegawaiService.selectPegawai(list_pengajuan_surat.get(i).getUsername_pegawai());
					list_pengajuan_surat.get(i).setPegawai(pegawai);
				}
			}
			model.addAttribute("jenis_surat", jenis_surat);
			model.addAttribute("status_surat", status_surat);
			model.addAttribute("list_pengajuan_surat", list_pengajuan_surat);
		return "pengajuan-riwayat";
	}
	
	@RequestMapping (value="/pengajuan/riwayat/filterByDate", method= RequestMethod.GET)
	public String filterByDateMhs (Model model,@ModelAttribute("pengajuan_surat") PengajuanSuratModel pengajuan_surat,
			@RequestParam(value="tanggal_mulai_izin", required=true, defaultValue = "1900-01-01")  @DateTimeFormat(pattern = "yyyy-MM-dd")Date tanggal_mulai_izin,
			@RequestParam(value="tanggal_selesai_izin", required=true,defaultValue="1900-01-01")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date tanggal_selesai_izin) 
	{
			// get current user logged
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			List<JenisSuratModel> jenis_surat = suratDAO.selectJenisSurat();
			List<StatusSuratModel> status_surat = suratDAO.selectStatusSurat();
			List<PengajuanSuratModel> list_pengajuan_surat = suratDAO.selectPengajuanSuratByTanggalSuratMhs(tanggal_mulai_izin,tanggal_selesai_izin,username);
			for(int i=0;i<list_pengajuan_surat.size();i++) {
				MahasiswaModel mahasiswa = mahasiswaService.selectMahasiswa(list_pengajuan_surat.get(i).getUsername_pengaju());
				list_pengajuan_surat.get(i).setPengaju(mahasiswa);
				if(list_pengajuan_surat.get(i).getUsername_pegawai()!=null) {
					PegawaiModel pegawai = pegawaiService.selectPegawai(list_pengajuan_surat.get(i).getUsername_pegawai());
					list_pengajuan_surat.get(i).setPegawai(pegawai);
				}
			}
			model.addAttribute("jenis_surat", jenis_surat);
			model.addAttribute("status_surat", status_surat);
			model.addAttribute("list_pengajuan_surat", list_pengajuan_surat);
		return "pengajuan-riwayat";
	}
	
	@RequestMapping(value="/pengajuan/viewall")
	public String viewall(@ModelAttribute("pengajuan_surat") PengajuanSuratModel pengajuan_surat,Model model) {
		List<JenisSuratModel> jenis_surat = suratDAO.selectJenisSurat();
		List<StatusSuratModel> status_surat = suratDAO.selectStatusSurat();
		List<PengajuanSuratModel> list_pengajuan_surat = suratDAO.selectAllPengajuanSurat();
		log.info(list_pengajuan_surat.toString());
		for(int i=0;i<list_pengajuan_surat.size();i++) {
			MahasiswaModel mahasiswa = mahasiswaService.selectMahasiswa(list_pengajuan_surat.get(i).getUsername_pengaju());
			list_pengajuan_surat.get(i).setPengaju(mahasiswa);
			if(list_pengajuan_surat.get(i).getUsername_pegawai()!=null) {
				PegawaiModel pegawai = pegawaiService.selectPegawai(list_pengajuan_surat.get(i).getUsername_pegawai());
				list_pengajuan_surat.get(i).setPegawai(pegawai);
			}
			
		}
		model.addAttribute("jenis_surat", jenis_surat);
		model.addAttribute("status_surat", status_surat);
		model.addAttribute("list_pengajuan_surat", list_pengajuan_surat);
		return "pengajuan-riwayat";
	}

	
	@RequestMapping (value="/pengajuan/viewall/filterByJenis", method= RequestMethod.GET)
	public String filterByJenis(Model model,@ModelAttribute("pengajuan_surat") PengajuanSuratModel pengajuan_surat,
			@RequestParam(value="id_jenis_surat", required=true) Integer id_jenis_surat) 
	{
			List<JenisSuratModel> jenis_surat = suratDAO.selectJenisSurat();
			List<StatusSuratModel> status_surat = suratDAO.selectStatusSurat();
			List<PengajuanSuratModel> list_pengajuan_surat = suratDAO.selectPengajuanSuratByJenisSurat(id_jenis_surat);
			log.info(list_pengajuan_surat.toString());
			for(int i=0;i<list_pengajuan_surat.size();i++) {
				MahasiswaModel mahasiswa = mahasiswaService.selectMahasiswa(list_pengajuan_surat.get(i).getUsername_pengaju());
				list_pengajuan_surat.get(i).setPengaju(mahasiswa);
				if(list_pengajuan_surat.get(i).getUsername_pegawai()!=null) {
					PegawaiModel pegawai = pegawaiService.selectPegawai(list_pengajuan_surat.get(i).getUsername_pegawai());
					list_pengajuan_surat.get(i).setPegawai(pegawai);
				}
			}
			model.addAttribute("jenis_surat", jenis_surat);
			model.addAttribute("status_surat", status_surat);
			model.addAttribute("list_pengajuan_surat", list_pengajuan_surat);
		return "pengajuan-riwayat";
	}
	
	@RequestMapping (value="/pengajuan/viewall/filterByStatus", method= RequestMethod.GET)
	public String filterByStatus (Model model,@ModelAttribute("pengajuan_surat") PengajuanSuratModel pengajuan_surat,
			@RequestParam(value="id_status_surat", required=true) Integer id_status_surat) 
	{
			// get current user logged
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			List<JenisSuratModel> jenis_surat = suratDAO.selectJenisSurat();
			List<StatusSuratModel> status_surat = suratDAO.selectStatusSurat();
			List<PengajuanSuratModel> list_pengajuan_surat = suratDAO.selectPengajuanSuratByStatusSurat(id_status_surat);
			for(int i=0;i<list_pengajuan_surat.size();i++) {
				MahasiswaModel mahasiswa = mahasiswaService.selectMahasiswa(list_pengajuan_surat.get(i).getUsername_pengaju());
				list_pengajuan_surat.get(i).setPengaju(mahasiswa);
				if(list_pengajuan_surat.get(i).getUsername_pegawai()!=null) {
					PegawaiModel pegawai = pegawaiService.selectPegawai(list_pengajuan_surat.get(i).getUsername_pegawai());
					list_pengajuan_surat.get(i).setPegawai(pegawai);
				}
			}
			model.addAttribute("jenis_surat", jenis_surat);
			model.addAttribute("status_surat", status_surat);
			model.addAttribute("list_pengajuan_surat", list_pengajuan_surat);
		return "pengajuan-riwayat";
	}
	
	@RequestMapping (value="/pengajuan/viewall/filterByDate", method= RequestMethod.GET)
	public String filterByDate (Model model,@ModelAttribute("pengajuan_surat") PengajuanSuratModel pengajuan_surat,
			@RequestParam(value="tanggal_mulai_izin", required=true, defaultValue = "1900-01-01")  @DateTimeFormat(pattern = "yyyy-MM-dd")Date tanggal_mulai_izin,
			@RequestParam(value="tanggal_selesai_izin", required=true,defaultValue="1900-01-01")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date tanggal_selesai_izin) 
	{
			// get current user logged
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = auth.getName();
			List<JenisSuratModel> jenis_surat = suratDAO.selectJenisSurat();
			List<StatusSuratModel> status_surat = suratDAO.selectStatusSurat();
			List<PengajuanSuratModel> list_pengajuan_surat = suratDAO.selectPengajuanSuratByTanggalSurat(tanggal_mulai_izin,tanggal_selesai_izin);
			for(int i=0;i<list_pengajuan_surat.size();i++) {
				MahasiswaModel mahasiswa = mahasiswaService.selectMahasiswa(list_pengajuan_surat.get(i).getUsername_pengaju());
				list_pengajuan_surat.get(i).setPengaju(mahasiswa);
				if(list_pengajuan_surat.get(i).getUsername_pegawai()!=null) {
					PegawaiModel pegawai = pegawaiService.selectPegawai(list_pengajuan_surat.get(i).getUsername_pegawai());
					list_pengajuan_surat.get(i).setPegawai(pegawai);
				}
			}
			model.addAttribute("jenis_surat", jenis_surat);
			model.addAttribute("status_surat", status_surat);
			model.addAttribute("list_pengajuan_surat", list_pengajuan_surat);
		return "pengajuan-riwayat";
	}
	
	 @RequestMapping("/pengajuan/riwayat/{idSurat}")
	 public String pengajuanRiwayatByIdSurat (Model model, @PathVariable(value = "idSurat") String idSurat){
		 PengajuanSuratModel pengajuanSuratModel = suratDAO.selectPengajuan(Integer.parseInt(idSurat));
		 MahasiswaModel mahasiswa = mahasiswaService.selectMahasiswa(pengajuanSuratModel.getUsername_pengaju());
		 pengajuanSuratModel.setPengaju(mahasiswa);
		 if(pengajuanSuratModel.getId_matkul_terkait()!=null) {
			 MataKuliahModel mataKuliahModel = mkService.selectMataKuliah(pengajuanSuratModel.getId_matkul_terkait());
			 pengajuanSuratModel.setMata_kuliah(mataKuliahModel);
		 }
		 if(pengajuanSuratModel.getUsername_pegawai()!=null) {
			 PegawaiModel pegawai = pegawaiService.selectPegawai(pengajuanSuratModel.getUsername_pegawai());
			 pengajuanSuratModel.setPegawai(pegawai);
		 }
		 model.addAttribute("pengajuan_surat", pengajuanSuratModel);
		 return "pengajuan-riwayat-detail";	    
	 }
	 
	 
	 @RequestMapping("/pengajuan/view/{idSurat}")
	 public String pengajuanView (Model model, @PathVariable(value = "idSurat") String idSurat){
		 PengajuanSuratModel pengajuanSuratModel = suratDAO.selectPengajuan(Integer.parseInt(idSurat));
		 MahasiswaModel mahasiswa = mahasiswaService.selectMahasiswa(pengajuanSuratModel.getUsername_pengaju());
		 pengajuanSuratModel.setPengaju(mahasiswa);
		 if(pengajuanSuratModel.getId_matkul_terkait()!=null) {
			 MataKuliahModel mataKuliahModel = mkService.selectMataKuliah(pengajuanSuratModel.getId_matkul_terkait());
			 pengajuanSuratModel.setMata_kuliah(mataKuliahModel);
		 }
		 if(pengajuanSuratModel.getUsername_pegawai()!=null) {
			 PegawaiModel pegawai = pegawaiService.selectPegawai(pengajuanSuratModel.getUsername_pegawai());
			 pengajuanSuratModel.setPegawai(pegawai);
		 }
		 
		 List<StatusSuratModel> statusSuratModels = suratDAO.selectStatusSurat();
		 model.addAttribute("pengajuan_surat", pengajuanSuratModel);
		 model.addAttribute("status_surats", statusSuratModels);
		 return "pengajuan-riwayat-detail-updateable";
	 }
	 
	 @RequestMapping(value="/pengajuan/ubah/{idSurat}",method=RequestMethod.POST)
	 public String pengajuanUbah (Model model, @PathVariable(value = "idSurat") String idSurat,
			 @ModelAttribute("pengajuan_surat") PengajuanSuratModel pengajuan_surat){
		 if(pengajuan_surat.getId()!=Integer.parseInt(idSurat)) {
			 log.info(pengajuan_surat.toString());
			 return "redirect:/pengajuan/view/"+idSurat;
		 }
		 log.info("Terima");
		 
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 String username = auth.getName();
		 pengajuan_surat.setUsername_pegawai(username);
		 if(pengajuan_surat.getId_status_surat()==4) {
			// get current date
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
			LocalDate localDate = LocalDate.now();
	
			pengajuan_surat.setNo_surat(pengajuan_surat.getId_jenis_surat()+dtf.format(localDate).toString()+pengajuan_surat.getId());
		 }
		 suratDAO.updatePengajuan(pengajuan_surat);
		 return "redirect:/pengajuan/view/"+idSurat;
	 }
	 
	 @RequestMapping(value="/pengajuan/riwayat/download/{noSurat}")
	 public String pengajuanUnduh (Model model, @PathVariable(value = "noSurat") String noSurat){
		 log.info(noSurat);
		 PengajuanSuratModel pengajuanSuratModel = suratDAO.selectPengajuan(noSurat);
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 String username = auth.getName();
		 log.info(username);
		 if(pengajuanSuratModel.getStatus_surat().getId()!=4 || !pengajuanSuratModel.getUsername_pengaju().equalsIgnoreCase(username)) {
			 return "redirect:/home";
		 }else {
			 return "forward:/files/"+pengajuanSuratModel.getNo_surat();
		 }
	 }
}
