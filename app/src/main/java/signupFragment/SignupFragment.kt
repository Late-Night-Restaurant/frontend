package signupFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.simya.R
import com.example.simya.databinding.FragmentSignupAgreeBinding

class SignupFragment: Fragment() {
    private lateinit var binding: FragmentSignupAgreeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupAgreeBinding.inflate(layoutInflater)
        return binding.root
    }
}