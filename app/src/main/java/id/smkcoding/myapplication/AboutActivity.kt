package id.smkcoding.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.activity_biodata_diri.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        backAbout.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "Gunakan Tombol Back Di Layout", Toast.LENGTH_SHORT).show()
        })
    }

    override fun onBackPressed() {
        backButtonHandler()
        return
    }

    fun backButtonHandler() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
