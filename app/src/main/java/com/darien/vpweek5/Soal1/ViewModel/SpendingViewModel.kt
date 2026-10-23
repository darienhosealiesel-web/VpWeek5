package com.darien.vpweek5.Soal1.ViewModel

import androidx.lifecycle.ViewModel
import com.darien.vpweek5.Soal1.Model.Spending
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SpendingViewModel : ViewModel() {
    private val _spending = MutableStateFlow<List<Spending>>(emptyList())
    val spending: StateFlow<List<Spending>> = _spending.asStateFlow()
    private val _inputName = MutableStateFlow("")
    val inputName: StateFlow<String> = _inputName.asStateFlow()
    private val _inputPrice = MutableStateFlow("")
    val inputPrice: StateFlow<String> = _inputPrice.asStateFlow()
    private val _errorName = MutableStateFlow<String?>(null)
    val errorName: StateFlow<String?> = _errorName.asStateFlow()
    private val _errorPrice = MutableStateFlow<String?>(null)
    val errorPrice: StateFlow<String?> = _errorPrice.asStateFlow()
    private var counter = 1

    fun grandTotal(): Int {
        return _spending.value.sumOf { it.price }
    }

    fun onNameChange(text: String) {
        _inputName.value = text
        _errorName.value = null
    }

    fun onPriceChange(text: String) {
        _inputPrice.value = text.filter { it.isDigit() }
        _errorPrice.value = null
    }

    fun addSpending() {
        val newPrice = _inputPrice.value.toIntOrNull()

        if (_inputName.value.isBlank()) {
            _errorName.value = "Name must not be empty"
            return
        }
        if (newPrice == null || newPrice <= 0) {
            _errorPrice.value = "Amount must be greater than 0"
            return
        }
        _spending.update {
            it + Spending(id = counter, name = _inputName.value.trim(), price = newPrice)
        }
        counter += 1
        _inputName.value = ""
        _inputPrice.value = ""
        _errorName.value = null
        _errorPrice.value = null
    }

    fun removeSpending(id: Int) {
        _spending.update { list -> list.filter { it.id != id } }
    }

    fun changePrice(id: Int, newValue: String): Boolean {
        val newPrice = newValue.toIntOrNull()
        if (newPrice == null || newPrice <= 0) {
            return false
        }
        _spending.update { list ->
            list.map {
                if (it.id == id) it.copy(price = newPrice) else it
            }
        }
        return true
    }
}