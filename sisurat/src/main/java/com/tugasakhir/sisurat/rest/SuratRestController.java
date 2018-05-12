
package com.tugasakhir.sisurat.rest;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tugasakhir.sisurat.model.SuratModel;
import com.tugasakhir.sisurat.service.SuratService;


@RestController
@RequestMapping("/api")
public class SuratRestController
{
 @Autowired
 SuratService suratService;
 @RequestMapping("/surat/view/{no_surat}")
 public SuratModel view (@PathVariable(value = "no_surat") String no_surat) {
	 SuratModel surat = suratService.selectSurat(no_surat);
	 return surat;
 }
  
 
}

