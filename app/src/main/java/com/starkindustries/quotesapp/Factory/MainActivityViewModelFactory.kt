package com.starkindustries.quotesapp.Factory
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.starkindustries.quotesapp.ViewModel.MainActivityViewModel
class MainActivityViewModelFactory(val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(context) as T
    }
}