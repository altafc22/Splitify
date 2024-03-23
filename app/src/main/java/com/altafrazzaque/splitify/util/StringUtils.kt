import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.formatDate() : String {
    return if (this.isNotEmpty()) {
        val date = SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(this)
        date.let {
            val format = SimpleDateFormat("dd/MM/yyy", Locale.getDefault())
            format.format(it)
        }
    } else { this }
}
