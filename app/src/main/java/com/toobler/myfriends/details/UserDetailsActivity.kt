package com.toobler.myfriends.details

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.toobler.myfriends.R
import com.toobler.myfriends.databinding.ActivityUserDetailsBinding
import com.toobler.myfriends.model.UserData
import com.toobler.myfriends.utils.PublicConstance
import com.toobler.myfriends.utils.Utils

class UserDetailsActivity : Activity() {

    private lateinit var binding: ActivityUserDetailsBinding

    private var userData: UserData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userData = intent.getParcelableExtra(PublicConstance.ARG_USER_DATA)

        initView()
    }

    /*init the view and handle the btn click*/
    private fun initView() {
        binding.tvUserName.text = userData?.userName
        binding.tvMailId.text = userData?.emailId

        Glide.with(binding.ivAvatar).load(userData?.avatar).error(R.mipmap.ic_launcher)
            .into(binding.ivAvatar)

        binding.btSendMail.setOnClickListener {
            if (Utils.isValidEmail(userData?.emailId!!)) {
                sendAnEmail(userData?.emailId!!)
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.mail_id_validation_warning),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    /*method for send an email to the corresponding user*/
    private fun sendAnEmail(emailId: String) {
        val i = Intent(Intent.ACTION_SEND)
        i.type = "text/plain"
        i.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailId))
        i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.invitaion_title))
        i.putExtra(Intent.EXTRA_TEXT, getString(R.string.sample_invitaion_content))
        try {
            startActivity(Intent.createChooser(i, getString(R.string.mail_chooser_title)))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                this,
                getString(R.string.email_client_warning),
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}