package dylanbricar.android.pictureupload.screens.connection

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dylanbricar.android.pictureupload.R
import dylanbricar.android.pictureupload.database.UserLogsDatabase
import dylanbricar.android.pictureupload.database.UserLogsRepository
import dylanbricar.android.pictureupload.databinding.FragmentConnectionBinding
import dylanbricar.android.pictureupload.sendToast

const val RC_SIGN_IN = 123

class ConnectionFragment : Fragment() {
    private lateinit var connectionViewModel: ConnectionViewModel
    private lateinit var binding: FragmentConnectionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_connection, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = UserLogsDatabase.getInstance(application).userLogsDatabaseDao
        val repository = UserLogsRepository(dataSource)
        val viewModelFactory = ConnectionViewModelFactory(repository)
        connectionViewModel =
            ViewModelProvider(this, viewModelFactory)[ConnectionViewModel::class.java]
        binding.connectionViewModel = connectionViewModel

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        binding.signInButton.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        val acct = GoogleSignIn.getLastSignedInAccount(requireActivity())
        if (acct != null) {
            // Remember the connection
            doConnect(acct)
        }

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                // Connected first time
                doConnect(account)
            } catch (e: ApiException) {
                sendToast("Une erreur est survenue, veuillez réesayer.", requireContext())
            }
        }
    }

    private fun doConnect(acct: GoogleSignInAccount) {
        connectionViewModel.saveConnection(acct.email!!)
        findNavController().navigate(
            ConnectionFragmentDirections.actionConnectionFragmentToIndexFragment()
        )
    }
}
