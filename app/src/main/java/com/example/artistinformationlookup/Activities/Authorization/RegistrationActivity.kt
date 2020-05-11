package com.example.artistinformationlookup.Activities.Authorization

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.artistinformationlookup.Activities.MainActivity
import com.example.artistinformationlookup.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    private  val auth by lazy{ FirebaseAuth.getInstance()}

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

            signUp(register_login.text.toString(), register_password.text.toString())
        }
    }

    private fun openMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun signUp(
        email: String,
        password: String
    ){
        auth.createUserWithEmailAndPassword(email, password).
            addOnCompleteListener{task ->
                if (task.isSuccessful){
                    openMainActivity()
                    return@addOnCompleteListener
                }
                Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
            }
    }


}
