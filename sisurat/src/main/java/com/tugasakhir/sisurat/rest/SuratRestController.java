
package com.tugasakhir.sisurat.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tugasakhir.sisurat.model.SuratModel;
import com.tugasakhir.sisurat.service.SuratService;


@RestController
@RequestMapping("/api")
public class SuratRestController
{
 @Autowired
 SuratService suratService;
 @RequestMapping("/surat/view/{no_surat}")
 public String view (@PathVariable(value = "no_surat") String no_surat) throws JsonProcessingException {
	 ObjectMapper mapper = new ObjectMapper();
	 SuratModel surat = suratService.selectSurat(no_surat);
	 String jsonInString = mapper.writeValueAsString(surat);
	 return  jsonInString;
 }
 
 @RequestMapping("/surat/viewall")
 public String view (Model model) throws JsonProcessingException  {
     ObjectMapper mapper = new ObjectMapper();
     List<SuratModel> surats =  suratService.selectAllSurats();
     String jsonInString = mapper.writeValueAsString(surats);
	 return  jsonInString;
 }
 
 
}

