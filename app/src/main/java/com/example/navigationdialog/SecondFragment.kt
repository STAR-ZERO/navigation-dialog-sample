package com.example.navigationdialog

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.navigationdialog.databinding.FragmentSecondBinding
import timber.log.Timber

class SecondFragment : Fragment() {

    companion object {
        private const val DIALOG_REQUEST_CODE = 1
    }

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.buttonBack.setOnClickListener { view ->
            view.findNavController().navigateUp()
        }

        binding.buttonDialog.setOnClickListener { view ->
            val action = SimpleDialogDirections.actionGlobalSimpleDialog(
                "Sample Title2",
                "Sample Message2",
                DIALOG_REQUEST_CODE
            )
            view.findNavController().navigate(action)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (DIALOG_REQUEST_CODE == requestCode && DialogInterface.BUTTON_POSITIVE == resultCode) {
            Timber.d("Click button positive")
        }
    }
}
