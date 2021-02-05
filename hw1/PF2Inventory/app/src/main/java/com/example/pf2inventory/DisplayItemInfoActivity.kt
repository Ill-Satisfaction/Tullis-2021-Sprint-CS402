package com.example.pf2inventory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import com.example.pf2inventory.Models.Item

class DisplayItemInfoActivity : AppCompatActivity() {
    var item = Item()
    var quantity = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_item_info)

        item = intent.getSerializableExtra("ITEM_INFO") as Item

        //  INITIALIZE VALUES
        var name = findViewById<TextView>(R.id.tv_item_name)
        var desc = findViewById<TextView>(R.id.et_description)
        var note = findViewById<TextView>(R.id.et_notes)

        var qAccess = findViewById<Switch>(R.id.swt_quick_access)
        var carried = findViewById<Switch>(R.id.swt_carried)
        var equipped = findViewById<Switch>(R.id.swt_equipped)

        var quantDownBtn = findViewById<ImageButton>(R.id.quantDownBtn)
        var quantUpBtn = findViewById<ImageButton>(R.id.quantUpBtn)
        var quant = findViewById<TextView>(R.id.tv_item_quantity)

        name.text = item.itemName
        desc.text = item.description
        note.text = item.notes
        quant.text = item.itemQuanity.toString()
        qAccess.isChecked = item.isQuickAccess
        carried.isChecked = item.isCarried
        equipped.isChecked = item.isEquipped

        quantDownBtn.setOnClickListener() {
            quantity--
            quant.text = quantity.toString()
        }
        quantUpBtn.setOnClickListener() {
            quantity++
            quant.text = quantity.toString()
        }
    }





}