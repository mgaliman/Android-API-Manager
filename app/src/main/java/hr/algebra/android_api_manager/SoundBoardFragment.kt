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

        val mp01 : MediaPlayer = MediaPlayer.create(requireContext(), R.raw.a)
        binding.btnOne.setOnClickListener { mp01.start() }

        val mp02 : MediaPlayer = MediaPlayer.create(requireContext(), R.raw.cepanje)
        binding.btnTwo.setOnClickListener { mp02.start() }

        val mp03 : MediaPlayer = MediaPlayer.create(requireContext(), R.raw.dobra)
        binding.btnThree.setOnClickListener { mp03.start() }

        val mp04 : MediaPlayer = MediaPlayer.create(requireContext(), R.raw.dobro)
        binding.btnFour.setOnClickListener { mp04.start() }

        val mp05 : MediaPlayer = MediaPlayer.create(requireContext(), R.raw.nani)
        binding.btnFive.setOnClickListener { mp05.start() }

        val mp06 : MediaPlayer = MediaPlayer.create(requireContext(), R.raw.skra)
        binding.btnSix.setOnClickListener { mp06.start() }
    }
}