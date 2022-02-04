package dylanbricar.android.pictureupload.screens.listing.utils

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface LoadAPI {
    @Multipart
    @POST("images.php")
    fun load(
        @Part("user") user: RequestBody,
    ): Call<LoadResponse>
    
    companion object {
        operator fun invoke(): LoadAPI {
            return Retrofit.Builder()
                .baseUrl("https://site-concept.eu/cours/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LoadAPI::class.java)
        }
    }
}

data class LoadResponse(
    val error: Boolean,
    val images: ArrayList<Image>
)
