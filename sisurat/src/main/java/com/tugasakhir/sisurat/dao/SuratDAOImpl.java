package com.tugasakhir.sisurat.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.tugasakhir.sisurat.model.MatakuliahModel;

@Service
public class SuratDAOImpl implements SuratDAO{
//	@Autowired
//	private RestTemplate restTemplate;
	
//	@Override
//	public MatakuliahModel selectMatakuliah(){
//		MatakuliahModel matakuliah = restTemplate.getForObject("https://apap-fasilkom.herokuapp.com/api/matkul/view/id/"+npm,MatakuliahModel.class);
//		return matakuliah;
//	}
	
//	@Override
//	public List<MatakuliahModel> selectAllMatakuliah()
//	{
//		List <MatakuliahModel> matakuliah2 = restTemplate.getForObject("https://apap-fasilkom.herokuapp.com/api/matkul/viewall", List.class);
//		return matakuliah2;
//	}
}
