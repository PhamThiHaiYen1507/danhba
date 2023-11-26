package com.example.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class AddContactFragment : Fragment() {
    lateinit var txtName: EditText

    lateinit var txtPhoneNumber: EditText

    lateinit var txtEmail: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.add_contact_fragment, container, false)

        txtName = view.findViewById<EditText>(R.id.txtName)

        txtPhoneNumber = view.findViewById<EditText>(R.id.txtPhoneNumber)

        txtEmail = view.findViewById<EditText>(R.id.txtEmail)

        val addButton = view.findViewById<Button>(R.id.addContact)

        addButton.setOnClickListener {
            addContact()
        }

        return view
    }

    private fun addContact() {
        if (txtName.text.trim().isNullOrEmpty()) {
            Toast.makeText(context, "Ten khong duoc de trong", Toast.LENGTH_LONG).show()
        } else if (txtPhoneNumber.text.trim().isNullOrEmpty()) {
            Toast.makeText(context, "So dien thoai khong duoc de trong", Toast.LENGTH_LONG).show()
        } else if (txtEmail.text.trim().isNullOrEmpty()) {
            Toast.makeText(context, "Email khong duoc de trong", Toast.LENGTH_LONG).show()
        } else {

        }
    }
}