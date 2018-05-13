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

	@Select ("SELECT * FROM pengajuan_surat where username_pengaju=#{username}")
	List<PengajuanSuratModel> selectPengajuanSuratByMhs(String username);

	@Select ("SELECT ps.no_surat"
			+ " ,js.nama as jenis_surat"
			+ " ,ps.tanggal_mohon"
			+ " ,ss.nama as status"
			+ " FROM pengajuan_surat ps, jenis_surat js, status_surat ss"
			+ " WHERE ps.id_jenis_surat = js.id"
			+ " AND ps.id_status_surat = ss.id")
	List<PengajuanSuratModel> selectAllPengajuanSurat();

	@Select ("SELECT ps.username_pengaju as id_mahasiswa"
			+ " ,js.nama as jenis_surat"
			+ " ,ps.keterangan"
			+ " ,ss.nama as status_surat"
			+ " FROM pengajuan_surat ps, jenis_surat js, status_surat ss"
			+ " WHERE ps.id_jenis_surat = js.id"
			+ " AND ps.id_status_surat = ss.id"
			+ " AND ps.no_surat = #{no_surat}")
	 SuratModel selectSurat (@Param("no_surat") String no_surat);

	@Select("SELECT * FROM status_surat")
	List<StatusSuratModel> selectStatusSurat();
	

	@Insert("Insert INTO pengajuan_surat ("
			+ "no_surat, username_pengaju, tanggal_mohon, id_jenis_surat, keterangan, alasan_izin, tanggal_mulai_izin, tanggal_selesai_izin, id_matkul_terkait)"
			+ "values ("
			+ "#{no_surat},#{username_pengaju},#{tanggal_mohon},#{id_jenis_surat},#{keterangan},#{alasan_izin},#{tanggal_mulai_izin}, #{tanggal_selesai_izin},#{id_matkul_terkait})")
	void insertPengajuan (PengajuanSuratModel pengajuan_surat);
	
	@Select("SELECT id FROM pengajuan_surat ORDER BY id DESC LIMIT 1")
	int getLastIdSurat();
	
	@Select("SELECT * FROM pengajuan_surat where id=#{id}")
	PengajuanSuratModel selectPengajuanSuratById(int id);
	
	@Update("UPDATE pengajuan_surat SET nama_dokumen=#{nama_dokumen} WHERE id=#{id}")
    boolean insertNamaDokumen(@Param("nama_dokumen")String nama_dokumen, @Param("id")int id);

	@Select("SELECT * FROM jenis_surat where id=#{id}")
	JenisSuratModel selectJenisSuratById(int id);
	

	@Select("SELECT * FROM status_surat where id=#{id}")
	StatusSuratModel selectStatusSuratById(int id);
	
	@Update("UPDATE pengajuan_surat SET no_surat=#{no_surat}, id_status_surat=#{id_status_surat}, username_pegawai=#{username_pegawai} where id=#{id}")
    void updatePengajuan (PengajuanSuratModel pengajuan_surat);
	
	@Select("SELECT * FROM pengajuan_surat where no_surat=#{no_surat}")
	PengajuanSuratModel selectPengajuanSuratByNoSurat(String noSurat);
}
