package com.starkindustries.quotesapp.ViewModel
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.starkindustries.quotesapp.Data.Quote
import com.starkindustries.quotesapp.Singleton.VolleySingleton
import org.json.JSONObject
class MainActivityViewModel(val context:Context):ViewModel() {
    val URL:String="https://type.fit/api/quotes"
    lateinit var quotesList:ArrayList<Quote>
    fun getList():ArrayList<Quote>
    {
        quotesList= ArrayList<Quote>()
        var request = JsonArrayRequest(URL,Response.Listener{
            for(i in 0..it.length()-1)
            {
                var jsonObject:JSONObject = it.getJSONObject(i)
                var quote:Quote = Quote(i,jsonObject.getString("text"),jsonObject.getString("author"))
                quotesList.add(quote)
            }
        },Response.ErrorListener{
            Log.d("ErrorListner"," "+it.message.toString())
        })
        VolleySingleton.getInstance(context.applicationContext).addRequest(request)
        return quotesList
    }
}