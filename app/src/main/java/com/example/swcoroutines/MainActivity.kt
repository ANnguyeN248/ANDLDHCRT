package com.example.swcoroutines

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StopwatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StopwatchAdapter()
        recyclerView.adapter = adapter

        findViewById<Button>(R.id.addButton).setOnClickListener {
            adapter.addStopwatch()
        }

        findViewById<Button>(R.id.startAllButton).setOnClickListener {
            adapter.startAll()
        }

        findViewById<Button>(R.id.pauseAllButton).setOnClickListener {
            adapter.pauseAll()
        }

        findViewById<Button>(R.id.continueAllButton).setOnClickListener {
            adapter.continueAll()
        }

        findViewById<Button>(R.id.resetAllButton).setOnClickListener {
            adapter.resetAll()
        }
    }
}

