package by.baranovdev.testgithub.presentation.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.baranovdev.testgithub.databinding.FragmentWebviewBinding

class WebViewFragment: Fragment() {

    private lateinit var binding: FragmentWebviewBinding

    private val args: WebViewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tvName.text = args.fileName
        with(binding.webView){
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(args.url)
        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}