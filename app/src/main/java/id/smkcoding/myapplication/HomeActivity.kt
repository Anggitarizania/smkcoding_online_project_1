package id.smkcoding.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        biodatadiri.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, BiodataDiriActivity::class.java)
            startActivity(intent)
            finish()
        })

        about.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
            finish()
        })
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
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }


}
