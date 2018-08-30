package com.testproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.toast
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.Socket

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("TAG", Utils.getLocalIpAdress(this))
        // Example of a call to a native method
        btnConnect.setOnClickListener {
            if (sample_text.text.isNotEmpty()) {
                val encryptedMsg = Security.RSAEncrypt(sample_text.text.toString())
                initiateTcpConnection(Utils.getLocalIpAdress(this), sample_text.text.toString(), encryptedMsg)
            } else toast("Please enter message.")
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun initiateTcpConnection(ipAddress: String, msg: String, bytes: ByteArray): String


    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
