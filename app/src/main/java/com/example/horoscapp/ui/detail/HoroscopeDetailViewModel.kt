package com.example.horoscapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.domain.model.PredictionModel
import com.example.horoscapp.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(private val getPredictionUseCase: GetPredictionUseCase) :
    ViewModel() {
    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.loading)
    val state: StateFlow<HoroscopeDetailState> = _state

    lateinit var horoscope :HoroscopeModel
    fun getHoroscope(sing: HoroscopeModel) {
        horoscope= sing
        viewModelScope.launch {
            _state.value = HoroscopeDetailState.loading
            //  Hilo principal
            val result: PredictionModel? =
                withContext(Dispatchers.IO) { getPredictionUseCase(sing.name) } // Hilo secundario }
            //Hilo pprincipal
            if (result != null) {
                _state.value = HoroscopeDetailState.Success(result.horoscope, result.sign, horoscope)
            } else {
             _state.value = HoroscopeDetailState.Error("Ha ocurrido un error intentelo mas tarde")
            }
        }
    }
}