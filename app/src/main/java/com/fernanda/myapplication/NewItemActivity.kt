package com.fernanda.myapplication

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_add_item.*

class NewItemActivity : AppCompatActivity() {

    companion object {

        const val EXTRA = ".REPLY"
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        save_item.setOnClickListener {
            val reply = Intent()
            if (add_item.text.isEmpty()) {
                setResult(Activity.RESULT_CANCELED, reply)
            } else {
                val item = add_item.text.toString()
                reply.putExtra(EXTRA, item)
                setResult(Activity.RESULT_OK, reply)
            }
            finish()
        }
    }
}
