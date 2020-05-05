package id.smkcoding.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_biodata_diri.*

class BiodataDiriActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biodata_diri)

        ambilData()

        backBiodataDiri.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        })

        editbiodata.setOnClickListener {navigasiKeEditProfil() }

        //memberi click listener ke tomboll call
        dial.setOnClickListener {dialPhoneNumber(telepon.text.toString()) }

    }

    companion object {
        val REQUEST_CODE = 100
    }


    //fungsi untuk berpindah ke EditProfilActivity
    private fun navigasiKeEditProfil() {
        val intent = Intent(this, EditActivity::class.java)
        val namaUser = nama.text.toString()
        intent.putExtra("nama", namaUser)
        startActivityForResult(intent, REQUEST_CODE)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK) {
                val result = data?.getStringExtra("nama")
                nama.text = result
            }else{
                Toast.makeText(this, "Edit failed",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        backButtonHandler()
        return
    }

    fun backButtonHandler() {
        Toast.makeText(this, "Gunakan Tombol Back Di Layout", Toast.LENGTH_SHORT).show()

    }

    private fun ambilData(){
        val bundle = intent.extras
        val namaa = bundle!!.getString("nama")
        val gender = bundle.getString("gender")
        val emaill = bundle.getString("email")
        val telp = bundle.getString("telp")
        val alamatt = bundle.getString("alamat")
        val umurr = bundle.getString("umur")
        nama.text = namaa
        jeniskelamin.text = gender
        email.text = emaill
        telepon.text = telp
        alamat.text = alamatt
        umur.text = umurr
    }

    //fungsi untuk melakukan dial
    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
