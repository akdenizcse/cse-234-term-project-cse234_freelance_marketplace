import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simurgapp.istebu.Model.AuthRepository
import com.simurgapp.istebu.Model.FirebaseAuthUser
import com.simurgapp.istebu.Model.SharedPreferencesHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginsigninViewModel(private val sharedPreferencesHelper: SharedPreferencesHelper) : ViewModel() {
    val model = AuthRepository()

    private val _signInState = MutableStateFlow<Result<FirebaseAuthUser>?>(null)
    val signInState: StateFlow<Result<FirebaseAuthUser>?> get() = _signInState
    private val _signUpState = MutableStateFlow<Result<FirebaseAuthUser>?>(null)
    val signUpState: StateFlow<Result<FirebaseAuthUser>?> get() = _signUpState
    val errorState = mutableStateOf<String?>(null)
    val UID = mutableStateOf<String?>(null)

    private val _isLoggedIn = MutableStateFlow<Boolean>(false)
    val isLoggedIn : StateFlow<Boolean> get() = _isLoggedIn




    private val _password = MutableStateFlow("")

    init {
        UID.value = sharedPreferencesHelper.getUID()
        updateIsLoggedIn(sharedPreferencesHelper.isLoggedIn())
        println("veri çekildi : ${sharedPreferencesHelper.getUID()}")
        println("veri çekildi : ${sharedPreferencesHelper.isLoggedIn()}")
    }
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                val res = model.signIn(email, password)
                _signInState.value = res
                println("isSuccess: ${res.isSuccess}")
                if (res.isSuccess) {
                    UID.value = res.getOrNull()?.uid
                    sharedPreferencesHelper.saveUID(res.getOrNull()?.uid!!)
                    sharedPreferencesHelper.saveLoginState(true)
                    updateIsLoggedIn(true)

                } else {
                    errorState.value = res.exceptionOrNull()?.message
                    print(res.exceptionOrNull()?.message)
                }
            } catch (e: Exception) {
                errorState.value = e.message
                print(e.message)
            }
        }
    }
    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            try {
                val res = model.signUp(email, password)
                print(res)
                if (res.isSuccess) {
                    UID.value = res.getOrNull()?.uid
                    sharedPreferencesHelper.saveUID(UID.value!!)
                    sharedPreferencesHelper.saveLoginState(true)
                    updateIsLoggedIn(true)
                } else {
                    errorState.value = res.exceptionOrNull()?.message
                    print(res.exceptionOrNull()?.message)
                }
            } catch (e: Exception) {
                // Hata durumunda yapılacak işlemler buraya gelebilir
                errorState.value = e.message
                print(e.message)
            }
        }
    }
    fun updateIsLoggedIn(logged: Boolean){
        _isLoggedIn.value = logged
    }

}
