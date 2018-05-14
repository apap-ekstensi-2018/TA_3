package com.tugasakhir.sisurat.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tugasakhir.sisurat.controller.PageController;
import com.tugasakhir.sisurat.model.AsistenResponseModel;
import com.tugasakhir.sisurat.model.MahasiswaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MahasiswaDAOImpl implements MahasiswaDAO{
	@Autowired
	@Lazy
	private RestTemplate restTemplate;

	@Override
	public MahasiswaModel selectMahasiswa(String npm) {
		MahasiswaModel mahasiswa = 
				restTemplate.getForObject
				("https://apap-fasilkom.herokuapp.com/api/mahasiswa/view/npm/"+npm,
					MahasiswaModel.class);
		return mahasiswa;
	}

	@Override
	public List<MahasiswaModel> selectAllMahasiswa() {
		ResponseEntity<List<MahasiswaModel>> res =
				restTemplate.exchange("https://apap-fasilkom.herokuapp.com/api/mahasiswa/viewall",
				HttpMethod.GET, null,
				new ParameterizedTypeReference<List<MahasiswaModel>>() {});
	
		List<MahasiswaModel> selectAllStudents = res.getBody(); 
		return selectAllStudents;
	}
	
	@Override
	public AsistenResponseModel checkIsAsistant(String id) {
		AsistenResponseModel assist = restTemplate.getForObject("http://siasisten-2.herokuapp.com/asisten-dosen/cek-status?id="+id,
				AsistenResponseModel.class);

		log.info("USE rest "+ assist.toString());
		
		return assist;
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
}
