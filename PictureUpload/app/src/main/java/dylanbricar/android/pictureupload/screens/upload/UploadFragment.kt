package dylanbricar.android.pictureupload.screens.upload

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.gms.auth.api.signin.GoogleSignIn
import dylanbricar.android.pictureupload.R
import dylanbricar.android.pictureupload.databinding.FragmentUploadBinding
import dylanbricar.android.pictureupload.sendToast
import kotlinx.coroutines.launch

class UploadFragment : Fragment() {
    private lateinit var viewModel: UploadViewModel
    private var fileUri: Uri? = null
    private val currentInstance = this
    private lateinit var binding: FragmentUploadBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_upload,
            container,
            false
        )
        viewModel = ViewModelProvider(this)[UploadViewModel::class.java]

        val imm =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)

        val acct = GoogleSignIn.getLastSignedInAccount(requireActivity())

        binding.apply {
            uploadViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner

            gallery.setOnClickListener {
                ImagePicker.with(currentInstance)
                    .galleryOnly()
                    .maxResultSize(1080, 1080)
                    .galleryMimeTypes(arrayOf("image/*"))
                    .compress(1024)
                    .crop()
                    .start()
            }

            camera.setOnClickListener {
                ImagePicker.with(currentInstance)
                    .cameraOnly()
                    .maxResultSize(1080, 1080)
                    .compress(1024)
                    .crop()
                    .start()
            }

            sendPicture.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.uploadImage(fileUri, requireContext(), acct!!.email!!)
                }
                binding.pickerImage.setImageResource(0)
                fileUri = null
                imm.hideSoftInputFromWindow(view?.windowToken, 0)
            }
        }

        viewModel.eventToast.observe(viewLifecycleOwner, { msg ->
            if (msg != "") {
                sendToast(msg, requireContext())
                viewModel.resetToast()
            }
        })

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == ImagePicker.REQUEST_CODE) {
            fileUri = data?.data
            binding.pickerImage.setImageURI(data?.data)
        }
    }
}
