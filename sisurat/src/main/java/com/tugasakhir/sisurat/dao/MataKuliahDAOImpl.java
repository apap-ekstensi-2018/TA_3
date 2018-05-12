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

import com.tugasakhir.sisurat.model.MataKuliahModel;
@Service
public class MataKuliahDAOImpl implements MataKuliahDAO{
	@Autowired
	@Lazy
	private RestTemplate restTemplate;
	
	@Override
	public MataKuliahModel selectMatakuliahById(int id) {
		MataKuliahModel matakuliah = 
				restTemplate.getForObject("https://apap-fasilkom.herokuapp.com//api/matkul/view/id/"+id,
				MataKuliahModel.class);
		return matakuliah;
	}

	@Override
	public MataKuliahModel selectMatakuliahByKode(String kode) {
		MataKuliahModel matakuliah = 
				restTemplate.getForObject("https://apap-fasilkom.herokuapp.com//api/matkul/view/kode/"+kode,
				MataKuliahModel.class);
		return matakuliah;
	}

	@Override
	public List<MataKuliahModel> selectAllMatakuliah() {
		ResponseEntity<List<MataKuliahModel>> res =
				restTemplate.exchange("https://apap-fasilkom.herokuapp.com/api/matkul/viewall",
				HttpMethod.GET, null,
				new ParameterizedTypeReference<List<MataKuliahModel>>() {});
	
		List<MataKuliahModel> selectAllMatakuliah = res.getBody(); 
		return selectAllMatakuliah;
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
