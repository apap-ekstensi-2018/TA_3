package com.tugasakhir.sisurat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.tugasakhir.sisurat.service.SuratService;
import com.tugasakhir.sisurat.storage.StorageService;
 
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
 
	@RequestMapping(value="/upload", method= RequestMethod.POST)
	public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
		try {
			storageService.store(file);
			
			model.addAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
			files.add(file.getOriginalFilename());
			if(suratDAO.insertDokumenName(file.getOriginalFilename(), 6)) {
				System.out.println("BERHASIL");
			}else {
				System.out.println("GAGAL");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "FAIL to upload " + file.getOriginalFilename() + "!");
		}
		return "upload-form";
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
 
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}