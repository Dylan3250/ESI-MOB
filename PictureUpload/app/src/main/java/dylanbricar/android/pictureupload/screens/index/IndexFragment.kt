package dylanbricar.android.pictureupload.screens.index

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dylanbricar.android.pictureupload.databinding.FragmentIndexBinding


class IndexFragment : Fragment() {
    private lateinit var viewModel: IndexViewModel
    private lateinit var binding: FragmentIndexBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            dylanbricar.android.pictureupload.R.layout.fragment_index, container, false
        )

        viewModel = ViewModelProvider(this)[IndexViewModel::class.java]
        binding.indexViewModel = viewModel

        val imm =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)

        val acct = GoogleSignIn.getLastSignedInAccount(requireActivity())
        if (acct == null) {
            findNavController().navigate(
                IndexFragmentDirections.actionIndexFragmentToConnectionFragment()
            )
        }

        binding.apply {
            uploadBtn.setOnClickListener {
                findNavController().navigate(
                    IndexFragmentDirections.actionIndexFragmentToUploadFragment()
                )
            }

            listingBtn.setOnClickListener {
                findNavController().navigate(
                    IndexFragmentDirections.actionIndexFragmentToListingFragment()
                )
            }

            aboutBtn.setOnClickListener {
                findNavController().navigate(
                    IndexFragmentDirections.actionIndexFragmentToAboutFragment()
                )
            }

            logsBtn.setOnClickListener {
                findNavController().navigate(
                    IndexFragmentDirections.actionIndexFragmentToLogsFragment()
                )
            }

            disconnectBtn.setOnClickListener {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
                val googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
                googleSignInClient.signOut()

                findNavController().navigate(
                    IndexFragmentDirections.actionIndexFragmentToConnectionFragment()
                )
            }
        }

        return binding.root
    }
}

