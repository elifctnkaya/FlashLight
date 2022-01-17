package com.elf.flash

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elf.flash.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cameraManager: CameraManager

    var flashState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager


        binding.flashButton.setOnClickListener{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(!flashState)
                {
                    val cameraListId = cameraManager.cameraIdList[0]
                    cameraManager.setTorchMode(cameraListId,true)
                    flashState = true
                    binding.flashButton.setImageResource(R.drawable.flashon)
                }
                else
                {
                    val cameraListId = cameraManager.cameraIdList[0]
                    cameraManager.setTorchMode(cameraListId, false)
                    flashState = false
                    binding.flashButton.setImageResource(R.drawable.flashoff)
                }
            }
        }
    }
}