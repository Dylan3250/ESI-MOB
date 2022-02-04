package dylanbricar.android.pictureupload.screens.logs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import dylanbricar.android.pictureupload.R
import dylanbricar.android.pictureupload.database.UserLogsDatabase
import dylanbricar.android.pictureupload.database.UserLogsRepository
import dylanbricar.android.pictureupload.databinding.FragmentLogsBinding

class LogsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var binding: FragmentLogsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_logs, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = UserLogsDatabase.getInstance(application).userLogsDatabaseDao
        val repository = UserLogsRepository(dataSource)
        val viewModelFactory = LogsViewModelFactory(repository)
        val logsViewModel = ViewModelProvider(this, viewModelFactory)[LogsViewModel::class.java]
        binding.logsViewModel = logsViewModel

        val imm =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)

        val acct = GoogleSignIn.getLastSignedInAccount(requireActivity())
        if (acct != null) {
            // Remember the connection
            logsViewModel.loadDatas(acct.email!!)
        }

        return binding.root
    }
}
