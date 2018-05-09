package com.tugasakhir.sisurat.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
public class SuratController
{

	@Autowired
	SuratService suratDAO;
    
    @RequestMapping("/")
    public String index ()
    {
        return "index";
    }
    
    @RequestMapping("/pengajuan/tambah")
    public String pengajuan_add (Model model)
    {
    	List<JenisSuratModel> jenis_surat = suratDAO.selectJenisSurat();
		model.addAttribute("jenis_surat", jenis_surat);
		model.addAttribute("pengajuan_surat", new PengajuanSuratModel());
		
		return "form-pengajuan-tambah";
    }
    
    @RequestMapping(value = "/pengajuan/tambah/submit", method = RequestMethod.POST)
<<<<<<< HEAD
    public String addSubmit (@ModelAttribute("pengajuan_surat") PengajuanSuratModel pengajuan_surat, Model model)
    {
    	if(pengajuan_surat.getAlasan_izin().isEmpty()) {
    		pengajuan_surat.setAlasan_izin("-");
    		pengajuan_surat.setTanggal_mulai_izin("0000-00-00");
    		pengajuan_surat.setTanggal_selesai_izin("0000-00-00");
    		pengajuan_surat.setId_matkul_terkait(0);
    	}
    	suratDAO.insertPengajuan(pengajuan_surat);
        model.addAttribute ("pengajuan_surat", pengajuan_surat);
        model.addAttribute("standardDate",new Date());
        return "success-add";
=======
    public String addSubmit (
//    		@ModelAttribute PengajuanSuratModel pengajuan_surat, Model model)
//    {
//    	suratDAO.insertPengajuan(pengajuan_surat);
//        model.addAttribute ("pengajuan_surat", pengajuan_surat);
//        model.addAttribute("standardDate",new Date());
//        
//    }
//    
    @RequestParam(value = "id_jenis_surat", required = false) int id_jenis_surat,
	@RequestParam(value = "keterangan", required = false) String keterangan,
	@RequestParam(value = "alasan_izin", required = false) String alasan_izin,
	@RequestParam(value = "tanggal_mulai_izin", required = false) Date tanggal_mulai_izin,
	@RequestParam(value = "tanggal_selesai_izin", required = false) Date tanggal_selesai_izin,
	@RequestParam(value = "id_matkul_terkait", required = false) int id_matkul_terkait
	
    		) {
    	PengajuanSuratModel surat = new PengajuanSuratModel("1506721756",id_jenis_surat, keterangan, alasan_izin,tanggal_mulai_izin,tanggal_selesai_izin,id_matkul_terkait,1);
    	suratDAO.insertPengajuan(surat);
return "success-add";
>>>>>>> 40910565475ad48d167e21711e98ff4fd1ea4d6a
    }
    
}
