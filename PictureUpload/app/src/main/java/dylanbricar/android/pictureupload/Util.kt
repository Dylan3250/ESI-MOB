package dylanbricar.android.pictureupload

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.Toast
import androidx.core.text.HtmlCompat
import dylanbricar.android.pictureupload.database.UserLogs
import java.text.SimpleDateFormat

fun formatHTMLUsers(allUserDatabase: List<UserLogs>): Spanned {
    val sb = StringBuilder()
    sb.apply {
        allUserDatabase.forEach {
            append("<br>${convertLongToDateString(it.lastDate)}<br>")
        }
    }

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}

@SuppressLint("SimpleDateFormat")
private fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("EEEE dd/mm/yyyy' à 'HH:mm")
        .format(systemTime).toString()
}

fun sendToast(msg: String, context: Context) {
    Toast.makeText(
        context, msg, Toast.LENGTH_SHORT
    ).show()
}
