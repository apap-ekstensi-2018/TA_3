package com.tugasakhir.sisurat.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.tugasakhir.sisurat.model.PengajuanSuratModel;
import com.tugasakhir.sisurat.service.SuratService;
import com.tugasakhir.sisurat.storage.StorageService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class UploadController {
 
	@Autowired
	StorageService storageService;
 
	@Autowired
	SuratService suratDAO;
	
	List<String> files = new ArrayList<String>();
 
	@RequestMapping(value="/upload", method= RequestMethod.GET)
	public String listUploadedFiles(Model model) {
		return "upload-form";
	}
 
	@RequestMapping(value="/pengajuan/view/{idSurat}/upload", method= RequestMethod.POST)
	public ModelAndView handleFileUpload(@PathVariable(value = "idSurat") int idSurat, @RequestParam("file") MultipartFile file, ModelMap model) {
		PengajuanSuratModel pengajuanSuratModel = suratDAO.selectPengajuan(idSurat);
		try {
			// get current date
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");
			LocalDate localDate = LocalDate.now();
			String nama_dokumen=pengajuanSuratModel.getId_jenis_surat()+dtf.format(localDate).toString()+pengajuanSuratModel.getId()+".pdf";
			
			storageService.store(file, nama_dokumen);
			
			model.addAttribute("message", "You successfully uploaded file!");
			files.add(nama_dokumen);
			if(suratDAO.insertDokumenName(nama_dokumen, pengajuanSuratModel.getId())) {
				System.out.println("BERHASIL");
			}else {
				System.out.println("GAGAL");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "FAIL to upload !");
		}
		return new ModelAndView("redirect:/pengajuan/view/"+pengajuanSuratModel.getId(),model);
	}
	
	
 
	@GetMapping("/gellallfiles")
	public String getListFiles(Model model) {
		model.addAttribute("files",
				files.stream()
						.map(fileName -> MvcUriComponentsBuilder
								.fromMethodName(UploadController.class, "getFile", fileName).build().toString())
						.collect(Collectors.toList()));
		model.addAttribute("totalFiles", "TotalFiles: " + files.size());
		return "list-file";
	}
	
	@GetMapping("/files/{noSurat:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String noSurat) {
		PengajuanSuratModel pengajuanSuratModel = suratDAO.selectPengajuan(noSurat);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 String username = auth.getName();
		 if(pengajuanSuratModel.getStatus_surat().getId()!=4 && !pengajuanSuratModel.getUsername_pengaju().equalsIgnoreCase(username)) {
			 return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + noSurat + "\"")
						.body(null);
		 }
		log.info(pengajuanSuratModel.getNama_dokumen());
		Resource file = storageService.loadFile(pengajuanSuratModel.getNama_dokumen());
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
 
	
}
