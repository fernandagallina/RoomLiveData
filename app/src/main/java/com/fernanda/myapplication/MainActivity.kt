package com.fernanda.myapplication

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.recycler_view.*
import android.arch.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {

        private const val REQUEST_CODE = 1
    }

    private lateinit var itemViewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)

        val adapter = ItemListAdapter(itemViewModel)
        recyclerView.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        itemViewModel.getItems().observe(this,
            Observer<List<Item>> { items ->
                if (items != null) {
                    adapter.setItems(items)
                }
            })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> {
                val intent = Intent(this, NewItemActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE)
            }
            R.id.delete_all -> {
                itemViewModel.deleteAllItems()
            }
        }
        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val item = Item(data!!.getStringExtra(NewItemActivity.EXTRA))
            itemViewModel.insert(item)
        }
    }
}
