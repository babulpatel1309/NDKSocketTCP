package com.testproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket

class MainActivity : AppCompatActivity() {

    lateinit var server: Server

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Example of a call to a native method

        Log.e("TAG", Utils.getLocalIpAddress())

        server = Server(this)
        sample_text.text = "${server.ipAddress}:${server.port}"

        val encryptedString = Security.RSAEncrypt(sample_text.text.toString())
        Log.e("Encryption", encryptedString.toString())
        Log.e("Decryption", Security.RSADecrypt(encryptedString))
//        sample_text.text = startServer()
//        createSocketServer()
    }

    fun updateMsg(msg: String) {
        sample_text.text = msg
    }

    fun createSocketServer() {

        try {
            val socketServer = ServerSocket()
            socketServer.reuseAddress = true
            socketServer.bind(InetSocketAddress(1309))
            var socket: Socket

            async(UI) {
                bg {
                    socket = socketServer.accept()
                    val commThread = CommThread(socket)
                    Thread(commThread).start()
                }

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    inner class CommThread(var socket: Socket) : Runnable {

        var buffer: BufferedReader = BufferedReader(InputStreamReader(socket.getInputStream()))

        override fun run() {

            async(UI) {
                sample_text.text = "From Thread  : ${buffer.readLine()}"
            }

        }
    }
}
