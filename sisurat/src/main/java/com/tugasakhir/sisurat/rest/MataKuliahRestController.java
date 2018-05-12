package com.tugasakhir.sisurat.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tugasakhir.sisurat.model.MahasiswaModel;
import com.tugasakhir.sisurat.model.MataKuliahModel;
import com.tugasakhir.sisurat.service.MahasiswaService;
import com.tugasakhir.sisurat.service.MataKuliahService;

@RestController
@RequestMapping("/pengajuan/rest")
public class MataKuliahRestController {
 @Autowired
 MataKuliahService mataKuliahService;
 @Autowired
 static MataKuliahService mataKuliahServicee;
 
 @RequestMapping("matakuliah/viewall")
 public List<MataKuliahModel> viewall() {
	 List<MataKuliahModel> mataKuliahList = mataKuliahService.selectAllMataKuliah();
	 return mataKuliahList;
 }
}
