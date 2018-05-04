package com.tugasakhir.sisurat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tugasakhir.sisurat.model.JenisSuratModel;
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
		
		return "form-pengajuan-add";
    }
}
