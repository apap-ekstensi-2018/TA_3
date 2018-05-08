package com.tugasakhir.sisurat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PegawaiModel {
	private int id;
	private String nip;
	private String username;
	private String nama;
	private boolean is_staf;
}