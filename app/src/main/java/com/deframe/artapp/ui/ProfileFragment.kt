package com.deframe.artapp.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.deframe.artapp.R
import com.deframe.artapp.helper.User
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

/**
 * This class hosts the profile screen fragment
 */
class ProfileFragment : Fragment() {

    companion object {
        val TAG: String = ProfileFragment::class.java.simpleName
        fun newInstance() = ProfileFragment()
    }

    /**
     *Handles onCreate actions of the activity
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_profile)
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        return view
    }

    /**
     *Handles onViewCreated actions of the activity
     *
     * @param view
     * @param savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //sets current user info
        profile_name.text = User.firstname
        profile_picture.setImageDrawable(User.profilepic)

        //assign onclick listener for terms and conditions button
        terms_and_conditions.setOnClickListener {
            var termsIntent = Intent(this.context, TermsAndConditionsActivity::class.java)
            startActivity(termsIntent)
        }

        //assign onclick listener for privacy button
        privacy.setOnClickListener {
            val privacyIntent = Intent(this.context, PrivacyActivity::class.java)
            startActivity(privacyIntent)
        }

        //assign onclick listener for feedback button
        feedback.setOnClickListener{
            val feedbackIntent = Intent(this.context, FeedbackActivity::class.java)
            startActivity(feedbackIntent)

        }

        //assign onclick listener for logout button
        logout.setOnClickListener{
            val loginIntent = Intent(this.context, LoginActivity::class.java)
            startActivity(loginIntent)
            getActivity()!!.finish()
        }
    }
}
