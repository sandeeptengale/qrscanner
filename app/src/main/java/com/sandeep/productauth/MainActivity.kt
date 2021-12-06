package com.sandeep.productauth

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class MainActivity : AppCompatActivity() {

    private lateinit var textNftLink: TextView
    private lateinit var btnVerifyNft: Button
    private lateinit var nftLink: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initizeUi()
    }

    private fun initizeUi() {
        var scanQrBtn = findViewById<Button>(R.id.btn_scan_qr)
        textNftLink = findViewById(R.id.txt_qr_result)
        btnVerifyNft = findViewById(R.id.btn_verify_nft)
        scanQrBtn.setOnClickListener {
            var integrator = IntentIntegrator(this)
            integrator.setPrompt("Scan QR code")
            integrator.setOrientationLocked(false)
            integrator.initiateScan()
        }

        var createNftBtn = findViewById<Button>(R.id.btn_create_nft)
        createNftBtn.setOnClickListener {
            Toast.makeText(this, "Still under development", Toast.LENGTH_SHORT).show()
        }

        btnVerifyNft.setOnClickListener {
            var browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(nftLink))
            startActivity(browserIntent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null && result.contents != null) {
            Toast.makeText(this, "Scan successful", Toast.LENGTH_SHORT).show()
            nftLink = result.contents
            textNftLink?.visibility = View.VISIBLE
            btnVerifyNft?.visibility = View.VISIBLE
            textNftLink?.text = result.contents

        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}