package com.tugasakhir.sisurat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tugasakhir.sisurat.dao.SuratMapper;
import com.tugasakhir.sisurat.model.JenisSuratModel;
import com.tugasakhir.sisurat.model.MataKuliahModel;
import com.tugasakhir.sisurat.model.PengajuanSuratModel;
import com.tugasakhir.sisurat.model.StatusSuratModel;
import com.tugasakhir.sisurat.model.SuratModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class SuratServiceDatabase implements SuratService
{
	@Autowired
	private SuratMapper suratMapper;

	@Override
	public List<JenisSuratModel> selectJenisSurat() {
		log.info("Tampilkan data jenis surat");
		return suratMapper.selectJenisSurat();
	}
	@Override
	public List<StatusSuratModel> selectStatusSurat() {
		log.info("Tampilkan data status surat");
		return suratMapper.selectStatusSurat();
	}
	@Override
	public List<PengajuanSuratModel> selectAllPengajuanSurat() {
		log.info("Tampilkan data pengajuan surat");
		return suratMapper.selectAllPengajuanSurat();
	}
	@Override
	public void insertPengajuan (PengajuanSuratModel pengajuan_surat)
    {
		suratMapper.insertPengajuan(pengajuan_surat);
    }
	
	@Override
	public int getLastidSurat() {
		return suratMapper.getLastIdSurat();
	}
	
	@Override
	public List<PengajuanSuratModel> selectPengajuanSuratByMhs(String name) {
		log.info("Tampilkan data status surat");
		List<PengajuanSuratModel> pengajuanSuratModels = suratMapper.selectPengajuanSuratByMhs(name);
		for(int i=0;i<pengajuanSuratModels.size();i++) {
			JenisSuratModel jenisSuratModel = suratMapper.selectJenisSuratById(((ArrayList<PengajuanSuratModel>)pengajuanSuratModels).get(i).getId_jenis_surat());
			pengajuanSuratModels.get(i).setJenis_surat(jenisSuratModel);
			StatusSuratModel statusSuratModel = suratMapper.selectStatusSuratById(pengajuanSuratModels.get(i).getId_status_surat());
			pengajuanSuratModels.get(i).setStatus_surat(statusSuratModel);
		}
		return pengajuanSuratModels;
	}
	
	@Override
	public PengajuanSuratModel selectPengajuan(int idSurat) {
		PengajuanSuratModel pengajuanSuratModel = suratMapper.selectPengajuanSuratById(idSurat);
		JenisSuratModel jenisSuratModel = suratMapper.selectJenisSuratById(pengajuanSuratModel.getId_jenis_surat());
		pengajuanSuratModel.setJenis_surat(jenisSuratModel);
		StatusSuratModel statusSuratModel = suratMapper.selectStatusSuratById(pengajuanSuratModel.getId_status_surat());
		pengajuanSuratModel.setStatus_surat(statusSuratModel);
		return pengajuanSuratModel;
	}
	@Override
	public SuratModel selectSurat(String no_surat) {
		log.info ("select surat with no_surat {}", no_surat);
        return suratMapper.selectSurat(no_surat);
		
	}
	@Override
	public void updatePengajuan(PengajuanSuratModel pengajuan_surat) {
		suratMapper.updatePengajuan(pengajuan_surat);
	}
}
