package io.github.katarem.mtgrestclient.viewmodel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.katarem.mtgrestclient.api.MTGAPI
import io.github.katarem.mtgrestclient.model.User
import io.github.katarem.mtgrestclient.utils.API_STATE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class UserViewModel: ViewModel() {

    private var service = MTGAPI.service.value

    private var _user = MutableStateFlow(User())
    val user = _user.asStateFlow()

    private var _username = MutableStateFlow("")
    val username = _username.asStateFlow()

    private var _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private var _isLogin = MutableStateFlow(true)
    val isLogin = _isLogin.asStateFlow()

    private var _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    private var _callState = MutableStateFlow(API_STATE.LOADING)
    val callState = _callState.asStateFlow()

    private var _newProfilePhoto = MutableStateFlow("")
    val newProfilePhoto = _newProfilePhoto.asStateFlow()

    fun changeProfilePhoto(new: String){
        _newProfilePhoto.value = new
    }

    fun swapLogin(){
        _isLogin.value = !_isLogin.value
    }

    fun setUsername(new: String){
        _username.value = new
    }

    fun setPassword(new: String){
        _password.value = new
    }

    private fun encapsulate(): User{
        val u = User()
        u.username = _username.value
        u.password = _password.value
        return u
    }

    fun registerUser(){
        viewModelScope.launch(Dispatchers.IO) {
            val user = encapsulate()
            val response = service.registerUser(user)
            when(response.code()){
                201 -> {
                    _errorMessage.value = "Registro exitoso!"
                    _callState.value = API_STATE.SUCCESS
                    loginUser()
                }
                400 -> {
                    _errorMessage.value = "Campos Incompletos"
                    _callState.value = API_STATE.ERROR
                }
                406 -> {
                    _errorMessage.value = "Usuario ya existe"
                    _callState.value = API_STATE.ERROR
                }
            }
        }
    }

    fun loginUser(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = service.loginUser(_username.value, _password.value)
            when(response.code()){
                200 -> {
                    _user.value = response.body()!!
                    Log.d("UserViewModel", "login existoso")
                    _callState.value = API_STATE.SUCCESS
                }
                400 -> {
                    _errorMessage.value = "Campos Incompletos"
                    _callState.value = API_STATE.ERROR
                }
                404 -> {
                    _errorMessage.value = "Usuario o contraseña incorrectos"
                    _callState.value = API_STATE.ERROR
                }
            }
        }
    }

    fun submitNewProfilePhoto(){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                Log.d("UserViewModel", "voy a hacer la llamada")
                val response = service.changeProfilePhoto(_user.value.id!!, _newProfilePhoto.value)
                Log.d("UserViewModel", "hice la llamada")
                when(response.code()){
                    200 -> {
                        _user.value = response.body()!!
                        _errorMessage.value = "Foto de perfil cambiada"
                        _callState.value = API_STATE.SUCCESS
                    }
                    400 -> {
                        _errorMessage.value = "Debes incluir la URL de la imagen"
                        _callState.value = API_STATE.ERROR
                    }
                    404 -> {
                        _errorMessage.value = "Usuario no encontrado"
                        _callState.value = API_STATE.ERROR
                    }
                }
            } catch(e: Exception){
                Log.d("UserViewModel", "${e.localizedMessage}\n${e.message}")
            }
        }
    }


}