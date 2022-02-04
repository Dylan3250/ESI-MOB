package dylanbricar.android.pictureupload.screens.listing.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import dylanbricar.android.pictureupload.R
import java.io.BufferedInputStream
import java.io.IOException
import java.net.URL

internal class MainAdapter(
    private val context: Context,
    private val images: ArrayList<Image>,
) :
    BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView

    override fun getCount(): Int {
        return images.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        var convertView = convertView
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.row_item, null)
        }

        imageView = convertView!!.findViewById(R.id.imageView)
        textView = convertView.findViewById(R.id.textView)
        imageView.setImageBitmap(convertBitmap(images[position].url))
        textView.text = images[position].name
        return convertView
    }

    private fun convertBitmap(url: String): Bitmap? {
        var bm: Bitmap?
        try {
            val aURL = URL(url)
            val conn = aURL.openConnection()
            conn.connect()
            val iss = conn.getInputStream()
            val bis = BufferedInputStream(iss)
            bm = BitmapFactory.decodeStream(bis)
            bis.close()
            iss.close()
        } catch (e: IOException) {
            throw IOException(e)
        }
        return bm
    }
}