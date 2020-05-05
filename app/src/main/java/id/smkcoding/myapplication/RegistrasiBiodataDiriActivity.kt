package id.smkcoding.myapplication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_registrasi_biodata_diri.*

class RegistrasiBiodataDiriActivity : AppCompatActivity() {

    //inisialisasi variabel global untuk menampung data inputan user
    private var namaInput: String = ""
    private var emailInput: String = ""
    private var umurInput: String = ""
    private var telpInput: String = ""
    private var alamatInput: String = ""
    private var genderInput: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrasi_biodata_diri)

        //set data spinner Gender
        setDataSpinnerGender()
        //memberi click listener ke tombol Save.
        //jika tombol save ditekan, maka akan menjalankan method
        validasiInput()
        btnSimpan.setOnClickListener { validasiInput() }

    }

    override fun onBackPressed() {
        backButtonHandler()
        return
    }

    fun backButtonHandler() {
        AlertDialog.Builder(this, R.style.MyDialogTheme)
            .setTitle("Keluar")
            .setMessage("Anda Yakin Ingin Keluar?")
            .setPositiveButton("Ya") { dialog, which ->
                finishAffinity()
                finish()
            }
            .setNegativeButton("Tidak") { dialog, which ->
                val intent = Intent(this, RegistrasiBiodataDiriActivity::class.java)
                startActivity(intent)
                finish()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    //fungsi untuk set data pada spinner gender dengan string-array jenis kelamin
    private fun setDataSpinnerGender(){
        val adapter = ArrayAdapter.createFromResource(this,
            R.array.jenis_kelamin, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerJk.adapter = adapter
    }

    //fungsi untuk melakukan validasi input
    private fun validasiInput(){
        //mendapatkan value/data dari tiap EditText dan menyimpannya ke dalam variabel yang telah dibuat
        namaInput = regnama.text.toString()
        emailInput = regemail.text.toString()
        telpInput = regtelp.text.toString()
        alamatInput = regalamat.text.toString()
        umurInput = regumur.text.toString()
        genderInput = spinnerJk.selectedItem.toString()
        when{
            //cek di tiap inputan apakah kosong atau tidak, jika kosong maka tampilkan error
            namaInput.isEmpty() -> regnama.error = "Nama tidak boleh kosong"
            genderInput.equals("Pilih Jenis Kelamin", ignoreCase = true) -> tampilToast("Jenis Kelamin harus dipilih")
            emailInput.isEmpty() -> regemail.error = "Email tidak boleh kosong"
            telpInput.isEmpty() -> regtelp.error = "Telp tidak boleh kosong"
            alamatInput.isEmpty() -> regalamat.error = "Alamat tidak boleh kosong"
            umurInput.isEmpty() -> regumur.error = "Umur tidak boleh kosong"
            else -> {
                //jika semua inputan telah diisi, maka jalankan method goToProfilActivity
                tampilToast("Navigasi ke halaman profil")

                goToProfilActivity()

            }
        }
    }

    //fungsi untuk menampilkan toast
    private fun tampilToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    //fungsi untuk berpindah ke ProfilActivity, sekaligus mengirimkan data hasil input user
    private fun goToProfilActivity() {
        val intent = Intent(this, BiodataDiriActivity::class.java)
        val bundle = Bundle()
        bundle.putString("nama", namaInput)
        bundle.putString("gender", genderInput)
        bundle.putString("email", emailInput)
        bundle.putString("telp", telpInput)
        bundle.putString("alamat", alamatInput)
        bundle.putString("umur", umurInput)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
