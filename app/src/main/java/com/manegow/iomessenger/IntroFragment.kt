package com.manegow.iomessenger

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.manegow.iomessenger.databinding.IntroFragmentBinding
import com.manegow.iomessenger.databinding.SignupFragmentBinding
import kotlinx.android.synthetic.main.intro_fragment.*
import java.lang.ClassCastException

class IntroFragment : Fragment() {

    private lateinit var callback: MainActivityFragmentsListener

    private lateinit var binding : IntroFragmentBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            callback = context as MainActivityFragmentsListener
        }catch (ex: ClassCastException){
            throw ClassCastException("El activity debe ser implementado en MainActivityFragmentListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = IntroFragmentBinding.inflate(inflater, container, false)

        binding.signUp.setOnClickListener { callback.onSignUpClick() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animationX = ObjectAnimator.ofFloat(logo, "scaleX", 0f, 1f)
        val animationY = ObjectAnimator.ofFloat(logo, "scaleY", 0f, 1f)
        animationX.duration = 500
        animationY.duration = 500

        val scaleUp = AnimatorSet()
        scaleUp.playTogether(animationX, animationY)
        scaleUp.start()
    }
}