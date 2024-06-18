package com.simurgapp.istebu.ViewModel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.simurgapp.istebu.Model.Chats
import com.simurgapp.istebu.Model.FirestoreUserRepository
import com.simurgapp.istebu.Model.Message
import com.simurgapp.istebu.Model.UserClass
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MessagesViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> get() = _messages

    private val _chats = MutableStateFlow<List<Chats>>(emptyList())
    val chats: StateFlow<List<Chats>> get() = _chats
    private val db = Firebase.firestore
    private val firestoreRepository = FirestoreUserRepository()

    init {
        fetchChatsByUID("pO9wrCpQaSOvykFb6p0Bd2THMhi1")
        println(_chats.value)
    }
    fun fetchChatsByUID(uid: String) {
        viewModelScope.launch {
            firestoreRepository.fetchChatsByUID(
                uid = uid,
                onSuccess = { chatList -> _chats.value = chatList
                    println("Chats fetched by UID: $chatList")
                },
                onFailure = { exception -> println("Error fetching chats by UID: ${exception.message}") }
            )
        }
    }

    fun sendMessage(chatId: String, message: Message) {
        val chatRef = db.collection("chats").document(chatId)
        db.runTransaction { transaction ->
            val chatSnapshot = transaction.get(chatRef)

            if (!chatSnapshot.exists()) {
                val newChat = Chats(
                    chatId = chatId,
                    chatMembers = listOf(message.senderId, message.receiverId),
                    createdDate = System.currentTimeMillis(),
                    lastMessage = message,
                )
                transaction.set(chatRef, newChat)

                chatRef.collection("messages").add(message)

            } else {
                val chat = chatSnapshot.toObject<Chats>()!!
                val newChat = chat.copy(
                    lastMessage = message,
                )
                transaction.update(chatRef, "lastMessage", message)

                chatRef.collection("messages").add(message)
            }
        }.addOnSuccessListener {
            println("Transaction success!")
        }.addOnFailureListener { e ->
            println("Transaction failure: $e")
        }
    }
    fun fetchMessages(chatId: String) {
        try {
            val registration: ListenerRegistration = db.collection("chats").document(chatId).collection("messages")
                .addSnapshotListener { messageSnapshot, error ->
                    if (error != null) {
                        println("Error fetching messages: ${error.message}")
                        return@addSnapshotListener
                    }

                    if (messageSnapshot != null) {
                        val messageList = messageSnapshot.documents.mapNotNull { it.toObject<Message>() }
                        _messages.value = messageList.sortedBy { it.timestamp }
                    }
                }
        } catch (e: Exception) {
            println("Exception occurred: ${e.message}")
        }
    }
    fun getChatIDs(currentUserID: String) {
        db.collection("chats").whereArrayContains("chatMembers", currentUserID).get().addOnSuccessListener { chats ->
            chats.documents.forEach { chat ->
                println(chat.id)
            }
        }
    }
    fun getChatID(currentUserID: String, otherUserID: String, callback: (String?) -> Unit) {
        println("getChatID: $currentUserID, $otherUserID")
        db.collection("chats")
            .whereArrayContains("chatMembers", currentUserID)
            .get()
            .addOnSuccessListener { chats ->
                if (chats != null && !chats.isEmpty) {
                    val chat = chats.documents.find { document ->
                        val chatMembers = document.get("chatMembers") as? List<*>
                        chatMembers?.contains(otherUserID) == true
                    }
                    if (chat != null) {
                        callback(chat.id)
                        println("getChatID: ${chat.id}")
                    } else {
                        callback(null)
                        println("bulunamadı")
                    }
                } else {
                    callback(null)
                    println("bulunamadı")
                }
            }
            .addOnFailureListener { e ->
                callback(null)
                println("Error getting chat ID: $e")
            }
    }


    fun getUserByUID(uid: String, onSuccess:  (UserClass) -> Unit, onFailure: (Exception) -> Unit) {
        viewModelScope.launch {
            println("girdi" + uid)
            firestoreRepository.getUserByUIDTwo(
                UID = uid,
                onSuccess = onSuccess,
                onFailure = onFailure
            )
        }
    }


}