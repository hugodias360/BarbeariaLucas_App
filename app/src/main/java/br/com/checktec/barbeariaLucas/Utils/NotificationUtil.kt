package br.com.checktec.barbeariaLucas.Utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import br.com.checktec.barbeariaLucas.Controladores.TelaInicialActivity
import br.com.checktec.barbeariaLucas.R
import br.com.fernandosousa.lmsapp.BarberApplication
import com.google.firebase.messaging.RemoteMessage


object NotificationUtil {
    internal val CHANNEL_ID = "1"
    // criar canal para mostrar a notificação
    fun createChannel(context: Context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val appName = context.getString(R.string.app_name)
            val c = NotificationChannel(CHANNEL_ID, appName, NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(c)
        }
    }
    fun create(contexto: Context, id: Int, intent: Intent, titulo: String, texto: String) {
// criar canal para mostrar notificação
        createChannel(BarberApplication.getInstance())
// Intent para disparar broadcast
        val p = PendingIntent.getActivity(contexto, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
// Cria notificação
        val builder = NotificationCompat.Builder(contexto, CHANNEL_ID)

                .setContentIntent(p)
                .setContentTitle(titulo)
                .setContentText(texto)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

// disparar notificacao
        with(NotificationManagerCompat.from(BarberApplication.getInstance())) {
            val n = builder.build()
            notify(id, n)
        }
    }

}