package com.practicum.myweatherapp.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.practicum.myweatherapp.MainViewModel
import com.practicum.myweatherapp.R
import com.practicum.myweatherapp.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btLondon.setOnClickListener {
            MainFragment.city = "London"
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.placeHolder, MainFragment.newInstance())?.commit()
        }
        binding.btMoscow.setOnClickListener {
            MainFragment.city = "Moscow"
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.placeHolder, MainFragment.newInstance())?.commit()
        }
        binding.btNewYork.setOnClickListener {
            MainFragment.city = "New York"
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.placeHolder, MainFragment.newInstance())?.commit()
        }
        binding.btParis.setOnClickListener {
            MainFragment.city = "Paris"
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.placeHolder, MainFragment.newInstance())?.commit()
        }
        binding.btBerlin.setOnClickListener {
            MainFragment.city = "Berlin"
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.placeHolder, MainFragment.newInstance())?.commit()
        }
        binding.btRome.setOnClickListener {
            MainFragment.city = "Rome"
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.placeHolder, MainFragment.newInstance())?.commit()
        }
        binding.btBeijing.setOnClickListener {
            MainFragment.city = "Beijing"
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.placeHolder, MainFragment.newInstance())?.commit()
        }
        binding.btTokyo.setOnClickListener {
            MainFragment.city = "Tokyo"
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.placeHolder, MainFragment.newInstance())?.commit()
        }
        binding.btCairo.setOnClickListener {
            MainFragment.city = "Cairo"
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.placeHolder, MainFragment.newInstance())?.commit()
        }
        binding.btMadrid.setOnClickListener {
            MainFragment.city = "Madrid"
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.placeHolder, MainFragment.newInstance())?.commit()
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() = FirstFragment()
    }
}
