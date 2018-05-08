package com.tugasakhir.sisurat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MahasiswaModel {
	private int id;
	private String npm;
	private String username;
	private String nama;
	private String status;
}