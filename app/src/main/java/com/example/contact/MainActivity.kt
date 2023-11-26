package com.example.contact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contact.model.ContactInfoModel


val contacts = arrayListOf<ContactInfoModel>(
    ContactInfoModel("1", "Nam", "0989889998", "nam@gmail.com"),
    ContactInfoModel("2", "Yen", "0989881298", "yen@gmail.com"),
    ContactInfoModel("3", "Thao", "0989881234", "Thao@gmail.com")
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}