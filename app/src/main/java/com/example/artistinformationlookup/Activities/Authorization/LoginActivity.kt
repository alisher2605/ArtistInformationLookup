package com.example.artistinformationlookup.Activities.Authorization

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.artistinformationlookup.Activities.MainActivity
import com.example.artistinformationlookup.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private  val auth by lazy{ FirebaseAuth.getInstance()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title = "Sign In"
        goToRegister()
        signIn()
    }

    private fun goToRegister(){
        val intent = Intent(this, RegistrationActivity::class.java)
        go_to_register_button.setOnClickListener{
            startActivity(intent)
            finish()
        }
    }



    private fun signIn(){
        val intent = Intent(this, MainActivity::class.java)
        btnSignIn.setOnClickListener {
            if (login_username.text!!.isEmpty()){
                login_username.error = getString(R.string.empty_username_error)
            }
            if (login_password.text!!.isEmpty()){
                login_password.error = "Enter your password"
            }

            val email =  login_username.text.toString().trim()
            val password = login_password.text.toString().trim()

            if (login_username.text!!.isNotEmpty() && login_password.text!!.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this,"Success!", Toast.LENGTH_LONG).show()
                        startActivity(intent)
                        finish()
                        return@addOnCompleteListener
                    }
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
