## Web Service

### List
* [/api/surat/view/{no_surat}](#apicheckstatus)
* [/api/surat/viewall](#apicheckstatus)

### /api/surat/view/{no_surat}
#### URL : `/api/surat/view/{no_surat}`
#### Method : `GET`

The above command returns JSON structured like this:
```json
{
  "id_mahasiswa":"1506721756",
  "jenis_surat":"Surat Izin Ikut Kegiatan",
  "keterangan":"Mengikuti kegiatan workshop di IBM",
  "status_surat":"Baru Diajukan"
}
```

### /api/surat/viewall
#### URL : `/api/surat/viewall
#### Method : `GET`

[
{
"id_mahasiswa":"1506721756",
"jenis_surat":"Surat Izin Ikut Kegiatan",
"keterangan":"Izin ",
"status_surat":"Baru Diajukan"
},
{
"id_mahasiswa":"1506721775",
"jenis_surat":"Surat Keterangan Berkelakuan Baik",
"keterangan":"Untuk keperluan intervew kerj",
"status_surat":"Baru Diajukan"
},
{
"id_mahasiswa":"1506721781",
"jenis_surat":"Surat Keterangan Lulus",
"keterangan":"Untuk keperluan lanjut studi S2",
"status_surat":"Baru Diajukan"
},
{
"id_mahasiswa":"1506721794",
"jenis_surat":"Surat Rekomendasi Beasiswa",
"keterangan":"Untuk keperluan beasiswa",
"status_surat":"Baru Diajukan"
}
]
```
