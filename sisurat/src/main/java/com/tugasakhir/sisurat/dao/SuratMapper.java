package com.tugasakhir.sisurat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tugasakhir.sisurat.model.JenisSuratModel;
import com.tugasakhir.sisurat.model.PengajuanSuratModel;
import com.tugasakhir.sisurat.model.StatusSuratModel;
import com.tugasakhir.sisurat.model.SuratModel;

@Mapper
public interface SuratMapper
{
	@Select("SELECT * FROM jenis_surat")
	List<JenisSuratModel> selectJenisSurat();

	@Select ("SELECT ps.no_surat"
			+ " ,js.nama as jenis_surat"
			+ " ,ps.tanggal_mohon"
			+ " ,ss.nama as status"
			+ " FROM pengajuan_surat ps, jenis_surat js, status_surat ss"
			+ " WHERE ps.id_jenis_surat = js.id"
			+ " AND ps.id_status_surat = ss.id")
	List<PengajuanSuratModel> selectAllPengajuanSurat();

	@Select("SELECT * FROM status_surat")
	List<StatusSuratModel> selectStatusSurat();
	
	@Insert("Insert INTO pengajuan_surat ("
			+ "no_surat, username_pengaju, tanggal_mohon, id_jenis_surat, keterangan, alasan_izin, tanggal_mulai_izin, tanggal_selesai_izin, id_matkul_terkait)"
			+ "values ("
			+ "#{no_surat},#{username_pengaju},#{tanggal_mohon},#{id_jenis_surat},#{keterangan},#{alasan_izin},#{tanggal_mulai_izin}, #{tanggal_selesai_izin},#{id_matkul_terkait})")
void insertPengajuan (PengajuanSuratModel pengajuan_surat);
	
	@Select("SELECT id FROM pengajuan_surat ORDER BY id DESC LIMIT 1")
	int getLastIdSurat();
}
