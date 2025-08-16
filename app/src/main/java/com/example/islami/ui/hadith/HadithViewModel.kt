package com.example.islami.ui.hadith

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HadithViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Hadith Fragment"
    }
    val text: LiveData<String> = _text
}