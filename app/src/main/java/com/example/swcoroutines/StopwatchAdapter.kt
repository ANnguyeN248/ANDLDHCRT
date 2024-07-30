package com.example.swcoroutines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StopwatchAdapter : RecyclerView.Adapter<StopwatchAdapter.StopwatchViewHolder>() {
    private val stopwatches = mutableListOf<Stopwatch>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StopwatchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stopwatch_item, parent, false)
        return StopwatchViewHolder(view)
    }

    override fun onBindViewHolder(holder: StopwatchViewHolder, position: Int) {
        val stopwatch = stopwatches[position]
        holder.bind(stopwatch)
    }

    override fun getItemCount(): Int = stopwatches.size

    fun addStopwatch() {
        stopwatches.add(Stopwatch())
        notifyItemInserted(stopwatches.size - 1)
    }

    fun startAll() {
        stopwatches.forEach { it.start { time -> notifyDataSetChanged() } }
    }

    fun pauseAll() {
        stopwatches.forEach { it.pause() }
    }

    fun continueAll() {
        stopwatches.forEach { it.continueTimer { time -> notifyDataSetChanged() } }
    }

    fun resetAll() {
        stopwatches.forEach { it.reset { time -> notifyDataSetChanged() } }
    }

    class StopwatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        private val startButton: Button = itemView.findViewById(R.id.startButton)
        private val pauseButton: Button = itemView.findViewById(R.id.pauseButton)
        private val continueButton: Button = itemView.findViewById(R.id.continueButton)
        private val resetButton: Button = itemView.findViewById(R.id.resetButton)

        fun bind(stopwatch: Stopwatch) {
            startButton.setOnClickListener {
                stopwatch.start { time ->
                    updateUI(time)
                }
            }
            pauseButton.setOnClickListener {
                stopwatch.pause()
            }
            continueButton.setOnClickListener {
                stopwatch.continueTimer { time ->
                    updateUI(time)
                }
            }
            resetButton.setOnClickListener {
                stopwatch.reset { time ->
                    updateUI(time)
                }
            }
        }

        private fun updateUI(timeInSeconds: Int) {
            val minutes = timeInSeconds / 60
            val seconds = timeInSeconds % 60
            timeTextView.text = String.format("%02d:%02d", minutes, seconds)
        }
    }
}
