package com.maimoona.exam


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import java.util.*

class DialogAddNewStudent: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view=activity?.layoutInflater?.inflate(R.layout.dailog_add_new_student,null)
        val nameEditText=view?.findViewById(R.id.EditTx_Name) as EditText
        val numEditText=view?.findViewById(R.id.EditTx_Number) as EditText
        val passCheckBox=view?.findViewById(R.id.std_pass_CheckBox) as CheckBox


        return AlertDialog.Builder(requireContext(),R.style.ThemeOverlay_AppCompat_Dialog_Alert)
            .setView(view)
            .setPositiveButton("Add"){ dialog,_ ->
                val std=Students(
                    UUID.randomUUID(),numEditText.text.toString().toInt(),
                    nameEditText.text.toString(),
                    passCheckBox.isChecked)

                targetFragment?.let { fragment ->
                    (fragment as Callbacks).onStudentAdded(std)
                }
            }.setNegativeButton("Cancel"){dialog,_ ->
                dialog.cancel()
            }.create()

    }

    interface Callbacks {
        fun onStudentAdded(student: Students)
        fun onStudentDeleted(position: Int)
    }
}