package com.example.twoactivitiespractice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.twoactivitiespractice.SecondActivity.Companion.SECOND_SEND_KEY

/**
 * This activity send data to the Second Activity via an Intent and then received the response from
 * Second Activity.
 *
 */

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var textView: TextView
    lateinit var headerTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText_main)
        textView = findViewById(R.id.rcvd_text)
        headerTextView = findViewById(R.id.text_header_reply)
    }

    fun launchSecondActivity(view: View) {
        // Hide Keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

        Log.d(LOG_TAG, "Button clicked!");
        val message = editText.text.toString()
        editText.text.clear()
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(FIRST_SEND_KEY, message)
        startActivityForResult(intent, TEXT_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                val replyFromSecondActivity = data?.getStringExtra(SECOND_SEND_KEY)
                headerTextView.visibility = View.VISIBLE
                textView.text = replyFromSecondActivity
                textView.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        val LOG_TAG = MainActivity::class.simpleName
        const val FIRST_SEND_KEY = "KEY"
        const val TEXT_REQUEST = 1
    }
}