package com.example.contact

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.model.ContactInfoModel


class PhoneBookFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.phone_book_fragment, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.phone_book_recycle)

        recyclerView.adapter = context?.let { it ->
            PhoneBookRecycleViewAdapter(
                it, contacts, this::onClickNavigate, this::onShowContextMenu
            )
        }
        recyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    private fun onClickNavigate(info: ContactInfoModel) {
//        val intent = Intent(context, ContactInfoDetail::class.java)
//
//        intent.putExtra("contactId", info.id)
//        intent.putExtra("contactName", info.name)
//        intent.putExtra("contactPhoneNumber", info.phoneNumber)
//        intent.putExtra("contactEmail", info.email)
//        startActivity(intent)

        val manager = parentFragmentManager
        val transaction = manager.beginTransaction()
        val fragment = ContactInfoDetail.newInstance(
            info.id, info.name, info.phoneNumber, info.email
        )

        fragment.view?.setBackgroundColor(Color.WHITE)

        transaction.add(R.id.phone_book, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun onShowContextMenu(info: ContactInfoModel, itemView: View) {
        val popupMenu =
            PopupMenu(context, itemView, Gravity.NO_GRAVITY, R.style.PopupMenuOverlapAnchor, 0)
        popupMenu.inflate(R.menu.menu_action)

        popupMenu.setOnMenuItemClickListener {
            if (it.itemId == R.id.call_action) {
                callAction(info)
            } else if (it.itemId == R.id.sms_action) {
                smsAction(info)
            } else if (it.itemId == R.id.email_action) {
                emailAction(info)
            } else if (it.itemId == R.id.add_contact_action) {
                addContactAction(info)
            }
            true
        }
        popupMenu.show()
    }

    private fun callAction(info: ContactInfoModel) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:${info.phoneNumber}")

        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it, android.Manifest.permission.CALL_PHONE
                )
            } != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                context as Activity, arrayOf(android.Manifest.permission.CALL_PHONE), 1
            )
        }

        try {
            startActivity(callIntent)
        } catch (e: SecurityException) {

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Khong co sim", Toast.LENGTH_LONG).show()
        }
    }

    private fun emailAction(info: ContactInfoModel) {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:${info.email}")
        }

        try {
            startActivity(emailIntent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun smsAction(info: ContactInfoModel) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("sms:${info.phoneNumber}")

        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it, android.Manifest.permission.SEND_SMS
                )
            } != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                context as Activity, arrayOf(android.Manifest.permission.SEND_SMS), 1
            )
        }

        try {
            startActivity(callIntent)
        } catch (e: Exception) {
            e.printStackTrace()

        }
    }

    private fun addContactAction(info: ContactInfoModel) {
        val manager = parentFragmentManager
        val transaction = manager.beginTransaction()
        val fragment = AddContactFragment()

        fragment.view?.setBackgroundColor(Color.WHITE)

        transaction.add(R.id.phone_book, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}