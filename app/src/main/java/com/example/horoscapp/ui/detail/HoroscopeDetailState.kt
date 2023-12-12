package com.example.horoscapp.ui.detail

import com.example.horoscapp.domain.model.HoroscopeModel

sealed  class HoroscopeDetailState {
    data object  loading:HoroscopeDetailState()
     data class Error(val error:String):HoroscopeDetailState()
    data class Success(val prediction:String,val sing:String, val horoscopeModel : HoroscopeModel ):HoroscopeDetailState()
}