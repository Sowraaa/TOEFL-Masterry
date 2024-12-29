package com.example.quiztoefl

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileActivity : AppCompatActivity() {

    private lateinit var fullNameTextView: TextView
    private lateinit var usernameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var passwordTextView: TextView

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val backButton: Button = findViewById(R.id.back_button)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        fullNameTextView = findViewById(R.id.full_name)
        usernameTextView = findViewById(R.id.username)
        emailTextView = findViewById(R.id.emaill)
        passwordTextView = findViewById(R.id.password)

        val userId = auth.currentUser?.uid

        if (userId != null) {
            getUserData(userId)
        } else {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
        }
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getUserData(userId: String) {
        val userRef = database.getReference("users").child(userId)

        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val name = snapshot.child("name").getValue(String::class.java)
                    val username = snapshot.child("username").getValue(String::class.java)
                    val email = snapshot.child("email").getValue(String::class.java)
                    val password = snapshot.child("password").getValue(String::class.java)

                    fullNameTextView.text = name ?: "No name available"
                    usernameTextView.text = username ?: "No username available"
                    emailTextView.text = email ?: "No email available"
                    passwordTextView.text = password ?: "No password available"
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ProfileActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
