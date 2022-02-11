package hr.algebra.android_api_manager

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import hr.algebra.android_api_manager.databinding.FragmentSoundBoardBinding
import kotlinx.coroutines.NonCancellable.start

class SoundBoardFragment : Fragment() {

    private lateinit var binding: FragmentSoundBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSoundBoardBinding.inflate(inflater, container, false)
        initButton()
        return binding.root
    }

    private fun initButton() {

        val mp : MediaPlayer = MediaPlayer.create(requireContext(), R.raw.cat_meow)
        binding.btnOne.setOnClickListener { mp.start() }
    }


}