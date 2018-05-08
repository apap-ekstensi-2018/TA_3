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
import com.tugasakhir.sisurat.model.SuratModel;

@Mapper
public interface SuratMapper
{
	@Select("SELECT * FROM jenis_surat")
	List<JenisSuratModel> selectJenisSurat();
	
	@Insert("INSERT INTO pengajuan_surat ("
			+ "username_pengaju, "
			+ "tanggal_mohon, "
			+ "id_jenis_surat, "
			+ "keterangan, "
			+ "alasan_izin, "
			+ "tanggal_mulai_izin, "
			+ "tanggal_selesai_izin, "
			+ "id_matkul_terkait)"
			+ "VALUES ("
			+ "'test',"
			+ "'2018-01-01',"
			+ "#{id_jenis_surat},"
			+ "#{keterangan},"
			+ "#{alasan_izin},"
			+ "#{tanggal_mulai_izin},"
			+ "#{tanggal_selesai_izin},"
			+ "#{id_matkul_terkait})")
	void insertPengajuan (PengajuanSuratModel pengajuan_surat);
}
