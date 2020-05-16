package com.example.artistinformationlookup.Activities.Authorization

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.artistinformationlookup.Activities.MainActivity
import com.example.artistinformationlookup.Networking.Responses.UserItem
import com.example.artistinformationlookup.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    private  val auth by lazy{ FirebaseAuth.getInstance()}
    private  val database by lazy{ FirebaseFirestore.getInstance()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        title = "Sign up"
        addUser()
    }



    private fun addUser(){
        btnSignUp.setOnClickListener {

            if (register_username.text!!.isEmpty())
            {
                register_username.error = "Username can't be empty!"
            }
            if (register_login.text!!.isEmpty()){
                register_login.error = "Enter your name"
            }
            if (register_password.text!!.isEmpty()){
                register_password.error = "Enter your password"
            }
            if(register_password.text.toString() != register_repeat_password.text.toString()){
                register_repeat_password.error = "Passwords should be the same"
            }
            else{
                signUp(register_login.text.toString(), register_password.text.toString(), register_username.text.toString())
            }
        }
    }

    private fun openMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun signUp(
        email: String,
        password: String,
        username: String
    ){
        auth.createUserWithEmailAndPassword(email, password).
            addOnCompleteListener{task ->
                if (task.isSuccessful){
                    val user =
                        UserItem(
                            task.result?.user?.uid!!, email, username, emptyList()
                        )
                    saveUser(task.result?.user?.uid!!,user)
                    openMainActivity()
                    return@addOnCompleteListener
                }
                Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
            }
    }

    private fun saveUser(
        id: String,
        user: UserItem
    ){
        database.collection("users")
            .document(id)
            .set(user).addOnSuccessListener {
                Log.d("taaag","Success")
            }
    }
}
