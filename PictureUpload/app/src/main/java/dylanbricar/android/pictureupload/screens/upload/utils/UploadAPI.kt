package dylanbricar.android.pictureupload.screens.upload.utils

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadAPI {

    @Multipart
    @POST("upload.php")
    fun upload(
        @Part image: MultipartBody.Part,
        @Part("name") name: RequestBody,
        @Part("user") user: RequestBody
    ): Call<UploadResponse>

    companion object {
        operator fun invoke(): UploadAPI {
            return Retrofit.Builder()
                .baseUrl("https://site-concept.eu/cours/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UploadAPI::class.java)
        }
    }
}

data class UploadResponse(
    val error: Boolean,
    val message: String,
    val image: String
)
