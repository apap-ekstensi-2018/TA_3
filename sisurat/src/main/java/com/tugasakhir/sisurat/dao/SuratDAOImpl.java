package com.tugasakhir.sisurat.dao;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tugasakhir.sisurat.model.MahasiswaModel;
import com.tugasakhir.sisurat.model.MataKuliahModel;
import com.tugasakhir.sisurat.model.PegawaiModel;

@Service
public class SuratDAOImpl implements SuratDAO{

	private RestTemplate restTemplate;
	
	@Override
	public MahasiswaModel selectStudent(String npm) {
		MahasiswaModel student = 
				restTemplate.getForObject
				("https://apap-fasilkom.herokuapp.com/api/mahasiswa/view/npm/"+npm,
					MahasiswaModel.class);
		
		return student;
	}

	@Override
	public List<MahasiswaModel> selectAllStudents() {
		ResponseEntity<List<MahasiswaModel>> res =
				restTemplate.exchange("https://apap-fasilkom.herokuapp.com/api/mahasiswa/viewall",
				HttpMethod.GET, null,
				new ParameterizedTypeReference<List<MahasiswaModel>>() {});
	
		List<MahasiswaModel> selectAllStudents = res.getBody(); 
		return selectAllStudents;
	}


	@Override
	public PegawaiModel selectPegawai(String nip) {
		
			PegawaiModel pegawai = 
					restTemplate.getForObject
					("https://apap-fasilkom.herokuapp.com/api/staf/view/nip/"+nip,
						PegawaiModel.class);
			if(nip.equalsIgnoreCase(pegawai.getNip())) return pegawai;
			else{
				pegawai = 
						restTemplate.getForObject
						("https://apap-fasilkom.herokuapp.com/api/dosen/view/nip/"+nip,
							PegawaiModel.class);
				
				return pegawai;
			}
			
			
	}
	
	@Override
	public MataKuliahModel selectMatakuliah(int id){
		MataKuliahModel matakuliah = 
				restTemplate.getForObject("https://apap-fasilkom.herokuapp.com//api/matkul/view/id/"+id,
				MataKuliahModel.class);
		return matakuliah;
	}
	
	@Override
	public List<MataKuliahModel> selectAllMatakuliah()
	{
		ResponseEntity<List<MataKuliahModel>> res =
				restTemplate.exchange("https://apap-fasilkom.herokuapp.com/api/matkul/viewall",
				HttpMethod.GET, null,
				new ParameterizedTypeReference<List<MataKuliahModel>>() {});
	
		List<MataKuliahModel> selectAllMatakuliah = res.getBody(); 
		return selectAllMatakuliah;
	}
}
