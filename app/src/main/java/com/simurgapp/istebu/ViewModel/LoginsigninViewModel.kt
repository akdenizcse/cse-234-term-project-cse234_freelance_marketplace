import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simurgapp.istebu.Model.AuthRepository
import com.simurgapp.istebu.Model.FirebaseAuthUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginsigninViewModel {
    val model = AuthRepository()
    private val _email = MutableStateFlow("")

    private val _signInState = MutableStateFlow<Result<FirebaseAuthUser>?>(null)
    val signInState: StateFlow<Result<FirebaseAuthUser>?> get() = _signInState

    private val _signUpState = MutableStateFlow<Result<FirebaseAuthUser>?>(null)
    val signUpState: StateFlow<Result<FirebaseAuthUser>?> get() = _signUpState

    val errorState = mutableStateOf<String?>(null)
    val UID = mutableStateOf<String?>(null)


    private val _password = MutableStateFlow("")
    suspend fun signIn(email : String, password : String) {
        val res = model.signIn(email, password)
        _signInState.value = res
        print(res)
        if(res.isSuccess){

            UID.value = res.getOrNull()?.uid
            print(UID.value)
        }
        else {
            errorState.value = res.exceptionOrNull()?.message
            print(res.exceptionOrNull()?.message)
        }

    }
     @SuppressLint("SuspiciousIndentation")
     suspend fun signUp(email : String, password : String) {
       val res = model.signUp(email, password)
         print(res)
         if(res.isSuccess){
             UID.value = res.getOrNull()?.uid
             print(UID.value)
              }
         else {
             errorState.value = res.exceptionOrNull()?.message
             print(res.exceptionOrNull()?.message)
         }

    }

}
