package com.example.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ContactInfoDetail : Fragment() {
    companion object {
        fun newInstance(
            contactId: String,
            contactName: String,
            contactPhoneNumber: String,
            contactEmail: String,
        ): ContactInfoDetail {
            val args = Bundle()
            args.putString("contactId", contactId)
            args.putString("contactName", contactName)
            args.putString("contactPhoneNumber", contactPhoneNumber)
            args.putString("contactEmail", contactEmail)
            val fragment = ContactInfoDetail()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {


        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.activity_contact_info_detail, container, false)

        val contactId = view.findViewById<TextView>(R.id.contactId)
        val contactName = view.findViewById<TextView>(R.id.contactName)
        val phoneNumber = view.findViewById<TextView>(R.id.phoneNumber)
        val email = view.findViewById<TextView>(R.id.email)

        contactId.text = this.arguments?.getString("contactId")
        contactName.text = this.arguments?.getString("contactName")
        phoneNumber.text = this.arguments?.getString("contactPhoneNumber")
        email.text = this.arguments?.getString("contactEmail")

        return view
    }
}