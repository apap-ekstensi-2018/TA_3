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

import com.tugasakhir.sisurat.model.PegawaiModel;

@Service
public class PegawaiDAOImpl implements PegawaiDAO{
	@Autowired
	@Lazy
	private RestTemplate restTemplate;
	
	@Override
	public PegawaiModel selectPegawai(String nip) {
		PegawaiModel pegawai = null;
		try {
			pegawai = 
					restTemplate.getForObject
					("https://apap-fasilkom.herokuapp.com/api/staf/view/nip/"+nip,
						PegawaiModel.class);
			if(nip.equalsIgnoreCase(pegawai.getNip())) {
				pegawai.set_staf(true);
				return pegawai;
			}
		}catch(org.springframework.web.client.HttpServerErrorException e) {
			
		}
		try {
		
			pegawai = 
					restTemplate.getForObject
					("https://apap-fasilkom.herokuapp.com/api/dosen/view/nip/"+nip,
						PegawaiModel.class);
			pegawai.set_staf(false);
			return pegawai;
		}catch(org.springframework.web.client.HttpServerErrorException e) {
			
		}
		return pegawai;
	
	}
	
	@Override
	public List <PegawaiModel>selectAllPegawai(){
		ResponseEntity<List<PegawaiModel>>dosen = restTemplate.exchange("https://apap-fasilkom.herokuapp.com/api/dosen/viewall", HttpMethod.GET,null,new ParameterizedTypeReference<List<PegawaiModel>>() {});
		ResponseEntity<List<PegawaiModel>>staf = restTemplate.exchange("https://apap-fasilkom.herokuapp.com/api/staf/viewall", HttpMethod.GET,null,new ParameterizedTypeReference<List<PegawaiModel>>() {});
		List <PegawaiModel>selectAllPegawai = dosen.getBody();
		for(int idx=0; idx < staf.getBody().size();idx++) {
			selectAllPegawai.add(staf.getBody().get(idx));
		}
		return selectAllPegawai;
	}
	
	@Bean
	public RestTemplate restTemplate (RestTemplateBuilder builder) {
		return builder.build();
	}
}
