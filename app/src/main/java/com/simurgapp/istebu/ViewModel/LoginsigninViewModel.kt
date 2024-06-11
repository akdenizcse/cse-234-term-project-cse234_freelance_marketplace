import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simurgapp.istebu.Model.AuthRepository
import com.simurgapp.istebu.Model.FirebaseAuthUser
import com.simurgapp.istebu.Model.FirestoreUserRepository
import com.simurgapp.istebu.Model.SharedPreferencesHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginsigninViewModel(val sharedPreferencesHelper: SharedPreferencesHelper) : ViewModel() {
    val model = AuthRepository()

    private val _signInState = MutableStateFlow<Result<FirebaseAuthUser>?>(null)
    val signInState: StateFlow<Result<FirebaseAuthUser>?> get() = _signInState
    private val _signUpState = MutableStateFlow<Result<FirebaseAuthUser>?>(null)
    val signUpState: StateFlow<Result<FirebaseAuthUser>?> get() = _signUpState
    val errorState = mutableStateOf<String?>(null)
    private val UID = mutableStateOf<String?>(null)
    val email = mutableStateOf<String?>(null)

    private val _isLoggedIn = MutableStateFlow<Boolean>(false)
    val isLoggedIn : StateFlow<Boolean> get() = _isLoggedIn
    val firebaseModel = FirestoreUserRepository()
    private  val _isUserCreated = MutableStateFlow<Boolean>(sharedPreferencesHelper.isUserCreated())
    val isUserCreated : StateFlow<Boolean> get() = _isUserCreated
    private val _isFreelancerCreated = MutableStateFlow<Boolean>(sharedPreferencesHelper.isFreelancerCreated())
    val isFreelancerCreated : StateFlow<Boolean> get() = _isFreelancerCreated


    private val _password = MutableStateFlow("")

    init {
        UID.value = sharedPreferencesHelper.getUID()
        updateIsLoggedIn(sharedPreferencesHelper.isLoggedIn())
        email.value = sharedPreferencesHelper.getEmail()

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
                    sharedPreferencesHelper.saveEmail(email)
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
                    sharedPreferencesHelper.saveEmail(email)
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

    fun addFreelancer( education: String , careerFields: MutableList<String>, definition: String , dailyPrice: Int ){
        viewModelScope.launch {
            firebaseModel.addFreelancer(UID.value!!,education, careerFields, definition,  dailyPrice){
                sharedPreferencesHelper.saveFreelancerCreated(true)
                _isFreelancerCreated.value = true
                Log.d(TAG, "Freelancer successfully added!")
            }

        }
    }
    fun addUser(name: String , surname: String , country: String , city: String  ,
                phoneNumber: String , job: String){
        viewModelScope.launch {
            firebaseModel.addUserBYUID(UID.value!!,name, surname, country, city, email.value!!, phoneNumber, job,onSuccess = {
                sharedPreferencesHelper.saveUserCreated(true)
                _isUserCreated.value = true
                Log.d(TAG, "User successfully added!")
            },)
        }
    }


}