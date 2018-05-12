package com.tugasakhir.sisurat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuratModel
{
	
	private String id_mahasiswa;
	private String jenis_surat;
	private String keterangan;
	private String status_surat;
	
}