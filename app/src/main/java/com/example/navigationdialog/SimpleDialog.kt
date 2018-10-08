package com.example.navigationdialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class SimpleDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val args = SimpleDialogArgs.fromBundle(arguments)

        return AlertDialog.Builder(requireActivity())
                .setTitle(args.title)
                .setMessage(args.message)
                .setPositiveButton("OK") { _, which ->
                    sendResult(which)
                }
                .create()
    }

    private fun sendResult(which: Int) {
        targetFragment?.onActivityResult(targetRequestCode, which, null)
    }
}
