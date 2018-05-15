package com.tugasakhir.sisurat.dao;

import java.util.Date;

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

	@Select ("SELECT * FROM pengajuan_surat")
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

	@Select ("SELECT ps.username_pengaju as id_mahasiswa"
			+ " ,js.nama as jenis_surat"
			+ " ,ps.keterangan"
			+ " ,ss.nama as status_surat"
			+ " FROM pengajuan_surat ps, jenis_surat js, status_surat ss"
			+ " WHERE ps.id_jenis_surat = js.id"
			+ " AND ps.id_status_surat = ss.id")
	List<SuratModel> selectAllSurats ();
	
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
	
	@Select("SELECT * FROM pengajuan_surat where id_jenis_surat=#{id_jenis_surat}")
	List<PengajuanSuratModel> selectPengajuanSuratByJenisSurat(int id_jenis_surat);
	
	@Select("SELECT * FROM pengajuan_surat where id_status_surat=#{id_status_surat}")
	List<PengajuanSuratModel> selectPengajuanSuratByStatusSurat(int id_status_surat);
	
	@Select("SELECT * FROM pengajuan_surat where tanggal_mohon between #{tanggal_mulai_izin} and #{tanggal_selesai_izin}")
	List<PengajuanSuratModel> selectPengajuanSuratByTanggalSurat(@Param("tanggal_mulai_izin")Date tanggal_mulai_izin, @Param("tanggal_selesai_izin") Date tanggal_selesai_izin);
	
	@Select("SELECT * FROM pengajuan_surat where id_jenis_surat =#{id_jenis_surat} and username_pengaju=#{username_pengaju}")
	List<PengajuanSuratModel> selectPengajuanSuratByJenisSuratMhs(@Param("id_jenis_surat")int id_jenis_surat, @Param("username_pengaju") String username_pengaju);
	
	@Select("SELECT * FROM pengajuan_surat where id_status_surat =#{id_status_surat} and username_pengaju=#{username_pengaju}")
	List<PengajuanSuratModel> selectPengajuanSuratByStatusSuratMhs(@Param("id_status_surat")int id_jenis_surat, @Param("username_pengaju") String username_pengaju);
	
	@Select("SELECT * FROM pengajuan_surat where tanggal_mohon between #{tanggal_mulai_izin} and #{tanggal_selesai_izin} and username_pengaju=#{username_pengaju}")
	List<PengajuanSuratModel> selectPengajuanSuratByTanggalSuratMhs(@Param("tanggal_mulai_izin")Date tanggal_mulai_izin, @Param("tanggal_selesai_izin") Date tanggal_selesai_izin, @Param("username_pengaju") String username_pengaju);
	
}
