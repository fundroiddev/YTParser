package com.example.ytparser

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.ytparser.databinding.ActivityMainBinding
import com.example.ytparser.models.Resource
import com.example.ytparser.recyclerview.YTDataAdapter
import com.example.ytparser.viewmodel.YTParserViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import data.VideoWithInfoUi

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: YTParserViewModel by viewModels()

    private val viewBinding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(viewBinding.root)

        viewBinding.button.setOnClickListener {
            val query = viewBinding.editText.text.toString()
            viewModel.loadQuery(query)
        }

        viewModel.videoData.observe(this) { result ->
            when(result) {
                is Resource.Loading -> onLoading()
                is Resource.Success -> onSuccess(result)
                is Resource.Error -> onError(result)
            }
        }
    }

    private fun onError(result: Resource.Error<List<VideoWithInfoUi>>) {
        with(viewBinding) {
            progressBar.isVisible = false
            textInputLayout.isEnabled = true
            button.isEnabled = true
        }
        val dialog = MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.error))
            .setMessage(String.format(getString(R.string.error_expand), result.message))
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        dialog.show()
    }

    private fun onSuccess(resultSuccess: Resource.Success<List<VideoWithInfoUi>>) {
        with(viewBinding) {
            progressBar.isVisible = false
            textInputLayout.isEnabled = true
            button.isEnabled = true
            result.isVisible = true
            recycler.adapter = YTDataAdapter(resultSuccess.data!!)
        }
    }

    private fun onLoading() {
        with(viewBinding) {
            progressBar.isVisible = true
            textInputLayout.isEnabled = false
            button.isEnabled = false
        }
    }
}