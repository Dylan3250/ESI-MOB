package dylanbricar.android.pictureupload.screens.upload

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dylanbricar.android.pictureupload.screens.upload.utils.FileUtils
import dylanbricar.android.pictureupload.screens.upload.utils.UploadAPI
import dylanbricar.android.pictureupload.screens.upload.utils.UploadRequestBody
import dylanbricar.android.pictureupload.screens.upload.utils.UploadResponse
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UploadViewModel : ViewModel() {

    val pictureNameContent = MutableLiveData<String>()

    private val _eventToast = MutableLiveData("")
    val eventToast: LiveData<String>
        get() = _eventToast

    fun resetToast() {
        _eventToast.value = ""
    }

    fun uploadImage(url: Uri?, context: Context, email: String) {
        if (url == null) {
            _eventToast.value = "Pas d'image chargée."
            return
        }

        if (pictureNameContent.value == null) {
            _eventToast.value = "Aucun nom d'image renseigné."
            return
        }

        val file: File? = FileUtils.getFile(context, url)
        val body = UploadRequestBody(file!!)

        UploadAPI().upload(
            MultipartBody.Part.createFormData(
                "image",
                file.name,
                body
            ),
            RequestBody.create(MediaType.parse("multipart/form-data"), pictureNameContent.value!!),
            RequestBody.create(MediaType.parse("multipart/form-data"), email)
        ).enqueue(object : Callback<UploadResponse> {
            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                _eventToast.value = "Une erreur est survenue, merci de réessayer."
            }

            override fun onResponse(
                call: Call<UploadResponse>,
                response: Response<UploadResponse>
            ) {
                response.body()?.let {
                    pictureNameContent.value = ""
                    _eventToast.value = "L'image a correctement été envoyée !"
                }
            }
        })
    }
}