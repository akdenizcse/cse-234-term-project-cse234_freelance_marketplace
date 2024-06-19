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
    fun signIn(email: String, password: String , onError: (String) -> Unit) {
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
                    onError(res.exceptionOrNull()?.message!!)
                }
            } catch (e: Exception) {
                errorState.value = e.message
                print(e.message)
                onError(e.message!!)
            }
        }
    }
    fun signUp(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
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
                    onSuccess() // Call the success callback
                } else {
                    val errorMessage = res.exceptionOrNull()?.message ?: "Unknown error"
                    errorState.value = errorMessage
                    print(errorMessage)
                    onError(errorMessage) // Call the error callback
                }
            } catch (e: Exception) {
                // Handle exception
                val errorMessage = e.message ?: "Unknown error"
                errorState.value = errorMessage
                print(errorMessage)
                onError(errorMessage) // Call the error callback
            }
        }
    }
    fun updateIsLoggedIn(logged: Boolean){
        _isLoggedIn.value = logged
    }

    fun addFreelancer( education: String , careerFields: MutableList<String>, definition: String , dailyPrice: Int , subBranches: MutableList<String>){
        viewModelScope.launch {
            firebaseModel.addFreelancer(UID.value!!,education, careerFields,subBranches,definition,  dailyPrice ){
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