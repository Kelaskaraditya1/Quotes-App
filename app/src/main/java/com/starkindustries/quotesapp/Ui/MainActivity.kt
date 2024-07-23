package com.starkindustries.quotesapp.Ui
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.starkindustries.quotesapp.Data.Quote
import com.starkindustries.quotesapp.Factory.MainActivityViewModelFactory
import com.starkindustries.quotesapp.R
import com.starkindustries.quotesapp.Singleton.VolleySingleton
import com.starkindustries.quotesapp.ViewModel.MainActivityViewModel
import com.starkindustries.quotesapp.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var viewModel:MainActivityViewModel
    lateinit var arrayList: ArrayList<Quote>
    val URL:String="https://type.fit/api/quotes"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel=ViewModelProvider(this,MainActivityViewModelFactory(applicationContext)).get(MainActivityViewModel::class.java)
        arrayList= ArrayList<Quote>()
        arrayList=viewModel.getList()
        Log.d("ValueListner",arrayList.get(0).author)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}