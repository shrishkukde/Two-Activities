package com.example.twoactivitiespractice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.twoactivitiespractice.MainActivity.Companion.FIRST_SEND_KEY

/**
 * This activity receives data from Main Activity and provides a response accordingly
 *
 */
class SecondActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var editTextView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        textView = findViewById(R.id.text_message_rcvd)
        editTextView = findViewById(R.id.returnReply)

        val messageReceived = intent.getStringExtra(FIRST_SEND_KEY)
        textView.text = messageReceived
    }

    fun returnReply(view: View) {
        // Hide Keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

        val reply = editTextView.text.toString()
        val replyIntent = Intent()
        replyIntent.putExtra(SECOND_SEND_KEY, reply)
        setResult(RESULT_OK, replyIntent)
        finish()
    }

    companion object {
        const val SECOND_SEND_KEY = "KEY"
    }
}