package com.tugasakhir.sisurat.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tugasakhir.sisurat.model.MahasiswaModel;
import com.tugasakhir.sisurat.service.MahasiswaService;

@RestController
@RequestMapping("/rest")
public class MataKuliahRestController {
<<<<<<< HEAD
 @Autowired
 MahasiswaService mahasiswaService;
 
=======
// @Autowired
// MahasiswaService mahasiswaService;
// 
>>>>>>> 317e1b11847b22324357638b20a0ba80bc653dca
// @RequestMapping("pengajuan/riwayat/{npm}")
// public MahasiswaModel viewRiwayat(@PathVariable(value="npm") String npm) {
//	 MahasiswaModel mahasiswaModel = mahasiswaService.selectMahasiswa(npm);
//	 return mahasiswaModel;
// }
}
