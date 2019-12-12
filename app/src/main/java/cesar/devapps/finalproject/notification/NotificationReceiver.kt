package cesar.devapps.finalproject.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import cesar.devapps.finalproject.views.LoginActivity


class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val repeatingIntent = Intent(context,LoginActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(context,100,repeatingIntent,PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context,"MyApp")
        builder.setContentIntent(pendingIntent)
        builder.setSmallIcon(android.R.drawable.arrow_up_float)
        builder.setContentTitle("notificação diária")
        builder.setContentText("entrar para registrar nova atividade")
        builder.setAutoCancel(true)
        builder.priority = NotificationCompat.PRIORITY_HIGH

        val notification = builder.build()

        notificationManager.notify(100,notification)
    }
}
