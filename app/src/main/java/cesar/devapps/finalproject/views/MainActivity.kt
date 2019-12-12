package cesar.devapps.finalproject.views

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import cesar.devapps.finalproject.R
import cesar.devapps.finalproject.notification.NotificationReceiver
import java.util.*


class MainActivity : AppCompatActivity() {
    private val notificationReceiver = NotificationReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonRegister = findViewById<Button>(R.id.button_register)
        buttonRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val buttonLogin = findViewById<Button>(R.id.button_login)
        buttonLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        notificationRepeatDaily()
        registerReceiver(notificationReceiver, IntentFilter("NOTFICICATION_RECEIVER"))
    }

    override fun onPause() {
        super.onPause()

        unregisterReceiver(notificationReceiver)

    }

    private fun notificationRepeatDaily(){
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY,1)
        calendar.set(Calendar.MINUTE,50)
        calendar.set(Calendar.SECOND,10)
        val intentNotification = Intent(applicationContext, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(applicationContext,100,intentNotification,PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent)

    }
}
