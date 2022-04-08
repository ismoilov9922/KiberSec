package uz.roboticslab.kibersec.utils

import android.content.Context
import android.os.Vibrator
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import uz.roboticslab.kibersec.R

fun errorToast(context: Context, message: String) {
    val layoutInflater = LayoutInflater.from(context)
    val view = layoutInflater.inflate(R.layout.error_toast, null)
    val textView = view.findViewById<TextView>(R.id.message)
    textView.text = message
    val myToast = Toast(context)
    myToast.view = view
    myToast.duration = Toast.LENGTH_LONG
    myToast.setGravity(Gravity.CENTER, 0, 0)
    myToast.show()
}

fun vibrate(context: Context, duration: Int) {
    val vibration: Vibrator =
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    vibration.vibrate(duration.toLong())
}