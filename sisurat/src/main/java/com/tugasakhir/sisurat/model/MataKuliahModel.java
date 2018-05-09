package com.tugasakhir.sisurat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MataKuliahModel {
	private int id;
	private String kode_matkul;
	private String nama_matkul;
}
