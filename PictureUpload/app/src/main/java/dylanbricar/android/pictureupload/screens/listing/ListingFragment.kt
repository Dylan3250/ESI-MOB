package dylanbricar.android.pictureupload.screens.listing

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.GridView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import dylanbricar.android.pictureupload.databinding.FragmentListingBinding
import dylanbricar.android.pictureupload.screens.listing.utils.MainAdapter
import dylanbricar.android.pictureupload.sendToast

class ListingFragment : Fragment() {
    private lateinit var viewModel: ListingViewModel
    private lateinit var binding: FragmentListingBinding
    private var androidGridView: GridView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            dylanbricar.android.pictureupload.R.layout.fragment_listing, container, false
        )

        viewModel = ViewModelProvider(this)[ListingViewModel::class.java]
        binding.listingViewModel = viewModel

        val acct = GoogleSignIn.getLastSignedInAccount(requireActivity())
        viewModel.loadPictures(acct!!.email!!)

        viewModel.eventToast.observe(viewLifecycleOwner, { msg ->
            if (msg == "LOAD_GRID") {
                loadGrid(requireContext())
            }
        })

        val imm =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)

        return binding.root
    }

    private fun loadGrid(requireContext: Context) {
        val mainAdapter = MainAdapter(requireContext, viewModel.images)
        androidGridView = binding.gridView
        androidGridView!!.adapter = mainAdapter
        androidGridView!!.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, pos, _ ->
                val clip = ClipData.newPlainText("label", viewModel.images[pos].url)
                val application = requireNotNull(this.activity).application
                val clipboard: ClipboardManager =
                    application.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                clipboard.setPrimaryClip(clip)
                sendToast(
                    "Lien de \"" + viewModel.images[pos].name + "\" copié !",
                    requireContext()
                )
            }
    }
}
