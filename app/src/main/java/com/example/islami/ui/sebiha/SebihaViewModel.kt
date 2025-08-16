package com.example.islami.ui.sebiha

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SebihaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Sebiha Fragment"
    }
    val text: LiveData<String> = _text
}