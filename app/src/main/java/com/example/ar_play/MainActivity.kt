package com.example.ar_play

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isGone
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.PlacementMode

class MainActivity : AppCompatActivity() {

    lateinit var sceneView: ArSceneView
    lateinit var placeButton : ExtendedFloatingActionButton
    lateinit var modelNode : ArModelNode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sceneView = findViewById(R.id.sceneView)
        placeButton = findViewById(R.id.extendedButton)

        placeButton.setOnClickListener{
            placeModel ()
        }
        modelNode  = ArModelNode(sceneView.engine, PlacementMode.INSTANT).apply {
        loadModelGlbAsync(
            glbFileLocation = "models/sportsCars.glb"

        ){
            sceneView.planeRenderer.isVisible = true
        }
            onAnchorChanged = {
                placeButton.isGone
            }
        }


        sceneView.addChild(modelNode)

    }
    private fun placeModel () {
        modelNode?.anchor()
        sceneView.planeRenderer.isVisible = false
    }
}