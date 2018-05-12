## Web

### List
* [/api/surat/view/{no_surat}](#apicheckstatus)

### /api/surat/view/{no_surat}
#### URL : `/api/surat/view/{no_surat}`
#### Method : `GET`

### Response
```json
{
  status: 200,
  body: true
}
```

The above command returns JSON structured like this:
```json
{
  "id_mahasiswa":"1506721756",
  "jenis_surat":"Surat Izin Ikut Kegiatan",
  "keterangan":"Mengikuti kegiatan workshop di IBM",
  "status_surat":"Baru Diajukan"
}
```
