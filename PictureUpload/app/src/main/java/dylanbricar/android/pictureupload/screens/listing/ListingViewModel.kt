package dylanbricar.android.pictureupload.screens.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dylanbricar.android.pictureupload.screens.listing.utils.Image
import dylanbricar.android.pictureupload.screens.listing.utils.LoadAPI
import dylanbricar.android.pictureupload.screens.listing.utils.LoadResponse
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListingViewModel : ViewModel() {
    var images = ArrayList<Image>()

    private val _eventToast = MutableLiveData("")
    val eventToast: LiveData<String>
        get() = _eventToast

    fun loadPictures(email: String) {
        LoadAPI().load(
            RequestBody.create(MediaType.parse("multipart/form-data"), email)
        ).enqueue(object : Callback<LoadResponse> {
            override fun onFailure(call: Call<LoadResponse>, t: Throwable) {
                _eventToast.value = "Une erreur est survenue, merci de réessayer."
            }

            override fun onResponse(
                call: Call<LoadResponse>,
                response: Response<LoadResponse>
            ) {
                response.body()?.let {
                    images = it.images
                    _eventToast.value = "LOAD_GRID"
                }
            }
        })
    }
}