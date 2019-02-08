package com.uttampanchasara.baseprojectkotlin

import android.content.Context
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.uttampanchasara.baseprojectkotlin.ui.base.BaseActivity
import java.text.SimpleDateFormat


/**
 *
 * @author <a href="https://github.com/UttamPanchasara">Uttam Panchasara</a>
 * @since 11/15/2018
 */
fun BaseActivity.showSnackbar(@StringRes message: Int,
                              @StringRes actionName: Int,
                              onActionClick: View.OnClickListener?) {
    Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
            .setAction(actionName, onActionClick)
            .show()
}

fun BaseActivity.showSnackbar(@StringRes message: Int) {
    Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
}

fun BaseActivity.showSnackbar(message: String) {
    Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
}

fun BaseActivity.showAlertDialog(dialogBuilder: AlertDialog.Builder.() -> Unit) {
    val builder = AlertDialog.Builder(this)
    builder.dialogBuilder()
    val dialog = builder.create()

    dialog.show()
}

fun getDateInFormat(date: String): String {
    var spf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val newDate = spf.parse(date)
    spf = SimpleDateFormat("dd/MM/yy")

    return spf.format(newDate)
}

fun getDateWithTFormat(date: String): String {
    var spf = SimpleDateFormat("dd/MM/yyyy")
    val newDate = spf.parse(date)
    spf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    return spf.format(newDate)
}

fun Context.toast(message: String, isLong: Boolean = false) {
    Toast.makeText(this, message, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_LONG).show()
}