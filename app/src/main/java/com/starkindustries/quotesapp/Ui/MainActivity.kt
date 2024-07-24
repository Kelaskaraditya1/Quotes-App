package com.starkindustries.quotesapp.Ui
import android.content.Intent
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
import com.starkindustries.quotesapp.ViewModel.QuotesCallback
import com.starkindustries.quotesapp.databinding.ActivityMainBinding
import org.json.JSONObject
class MainActivity : AppCompatActivity(){
    lateinit var binding:ActivityMainBinding
    lateinit var viewModel:MainActivityViewModel
    lateinit var arrayList: ArrayList<Quote>
    val URL:String="https://type.fit/api/quotes"
    companion object{
        var quotesCount=0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel=ViewModelProvider(this,MainActivityViewModelFactory(applicationContext)).get(MainActivityViewModel::class.java)
        arrayList= ArrayList<Quote>()
        var request = JsonArrayRequest(URL,Response.Listener{
            for(i in 0..it.length()-1)
            {
                var jsonObject:JSONObject = it.getJSONObject(i)
                var quoteItem:Quote = Quote(i,jsonObject.getString("text").toString().trim(),jsonObject.getString("author").toString().trim())
                arrayList.add(quoteItem)
            }
        },Response.ErrorListener{
            Log.d("ErrorListner"," "+it.message.toString())
        })
        VolleySingleton.getInstance(applicationContext).addRequest(request)
        Log.d("ValueListner"," "+arrayList.get(0).quote.toString().trim())
//        binding.quotes.setText(arrayList.get(0).quote)
//        binding.quotesAuthor.setText(arrayList.get(0).author)
        binding.shareButton.setOnClickListener()
        {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT,binding.quotes.text.toString().trim())
            startActivity(Intent.createChooser(intent,"Share via"))
        }
        binding.nextButton.setOnClickListener()
        {
            quotesCount= (quotesCount+1)%arrayList.size
            binding.quotes.setText(arrayList.get(quotesCount).quote)
            binding.quotesAuthor.setText(arrayList.get(quotesCount).author)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}