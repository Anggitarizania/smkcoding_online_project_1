package id.smkcoding.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_biodata_diri.*
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        //menerima data yang dikirimkan dari ProfilActivity.kt
        val intentData = intent.extras
        val namaUser = intentData!!.getString("nama")
        //set edittext dengan data yang dikirimkan tadi
        editnama.setText(namaUser)
        //memberikan action click ke tombol Simpan
        btneditSimpan.setOnClickListener { saveData() }

        backEdit.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        })
    }

    override fun onBackPressed() {
        backButtonHandler()
        return
    }

    fun backButtonHandler() {
        Toast.makeText(this, "Gunakan Tombol Back Di Layout", Toast.LENGTH_SHORT).show()
    }

    //mengirimkan kembali ke BiodataActivity.kt
    private fun saveData(){
        val namaEdit = editnama.text.toString()
        if (!namaEdit.isEmpty()) {
        //jika editText namaEdit tidak kosong, maka kirimkan value nya ke BiodataActivity, dan beri tanda RESULT_OK
            val result = Intent()
            result.putExtra("nama", namaEdit)
            setResult(Activity.RESULT_OK, result)
        } else {
        //jika editText namaEdit kosong, maka kirimkan tanda RESULT_CANCELED
            setResult(Activity.RESULT_CANCELED)
        }
        finish()
    }
}
