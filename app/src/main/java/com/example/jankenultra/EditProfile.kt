package com.example.jankenultra

import android.content.Intent
import android.graphics.Typeface
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EditProfile : AppCompatActivity() {
    private lateinit var back: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var editUser: ImageButton
    private lateinit var editEmail: ImageButton
    private lateinit var editPass: ImageButton
    private lateinit var editImage: ImageButton
    private lateinit var pass: TextView
    private lateinit var emailPlayer: TextView
    private lateinit var usernamePlayer: TextView
    private lateinit var editUserShow: TextView
    private lateinit var editEmailShow: TextView
    private lateinit var editPassShow: TextView

    //Efectos de sonido
    private lateinit var soundPool: SoundPool
    private var soundId: Int = 0


    private var user: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        val tf = Typeface.createFromAsset(assets, "fonts/edosz.ttf")
        val tf2 = Typeface.createFromAsset(assets, "fonts/retro.ttf")
        editImage = findViewById(R.id.editImage)
        editUser = findViewById(R.id.editUser)
        editEmail = findViewById(R.id.editEmail)
        editPass = findViewById(R.id.editPassword)
        editUserShow = findViewById(R.id.editNameShow)
        editEmailShow = findViewById(R.id.editEmailShow)
        editPassShow = findViewById(R.id.editPasswordShow)

        usernamePlayer = findViewById(R.id.nameShow)
        emailPlayer = findViewById(R.id.emailShow)
        pass = findViewById(R.id.passwordShow)

        //Efectos de sonido
        soundPool = SoundPool.Builder().setMaxStreams(1).build()
        soundId = soundPool.load(this, R.raw.menu, 1)
        back = findViewById(R.id.GoBack)
        back.setOnClickListener {
            playSound()
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }
        back.typeface = tf
        editUserShow.typeface = tf
        editEmailShow.typeface = tf
        editPassShow.typeface = tf
        pass.typeface = (tf2)
        emailPlayer.typeface = (tf2)
        usernamePlayer.typeface = (tf2)
        val database: FirebaseDatabase =
            FirebaseDatabase.getInstance("https://junkerultra-default-rtdb.europe-west1.firebasedatabase.app/")
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser
        val myRef = user?.uid?.let { database.reference.child("DATA_BASE_JUGADORS").child(it) }
        myRef?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val email = dataSnapshot.child("Email").getValue(String::class.java)
                val name = dataSnapshot.child("Nom").getValue(String::class.java)
                val password = dataSnapshot.child("Password").getValue(String::class.java)

                // utilizar email y name según sea necesario
                pass.text = password
                emailPlayer.text = email
                usernamePlayer.text = name

                editUser.setOnClickListener {
                    val builder = AlertDialog.Builder(this@EditProfile)
                    builder.setTitle("Cambiar nombre de usuario")
                    val input = EditText(this@EditProfile)
                    input.inputType = InputType.TYPE_CLASS_TEXT
                    builder.setView(input)

                    builder.setPositiveButton("Aceptar") { dialog, _ ->
                        val newName = input.text.toString()
                        if (newName.isNotEmpty() && newName != name) {
                            myRef.child("Nom").setValue(newName)
                            usernamePlayer.text = newName
                        }
                        dialog.dismiss()
                    }

                    builder.setNegativeButton("Cancelar") { dialog, _ ->
                        dialog.cancel()
                    }

                    val dialog = builder.create()
                    dialog.show()
                }

                editEmail.setOnClickListener {
                    val builder = AlertDialog.Builder(this@EditProfile)
                    builder.setTitle("Cambiar email")
                    val input = EditText(this@EditProfile)
                    input.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                    builder.setView(input)

                    builder.setPositiveButton("Aceptar") { dialog, _ ->
                        val newEmail = input.text.toString()
                        if (newEmail.isNotEmpty() && newEmail != email && newEmail.endsWith("@gmail.com")) {
                            myRef.child("Email").setValue(newEmail)
                            emailPlayer.text = newEmail
                        } else if (newEmail.isNotEmpty() && !newEmail.endsWith("@gmail.com")) {
                            Toast.makeText(
                                this@EditProfile,
                                "El email debe ser de @gmail.com",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        dialog.dismiss()
                    }

                    builder.setNegativeButton("Cancelar") { dialog, _ ->
                        dialog.cancel()
                    }

                    val dialog = builder.create()
                    dialog.show()
                }

                editPass.setOnClickListener {
                    val builder = AlertDialog.Builder(this@EditProfile)
                    builder.setTitle("Cambiar contraseña")
                    val input = EditText(this@EditProfile)
                    input.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    builder.setView(input)

                    builder.setPositiveButton("Aceptar") { dialog, _ ->
                        val newPassword = input.text.toString()
                        if (newPassword.length >= 6 && newPassword != password && isPasswordValid(
                                newPassword
                            )
                        ) {
                            myRef.child("Password").setValue(newPassword)
                            pass.text = newPassword
                        } else if (newPassword.length < 6) {
                            Toast.makeText(
                                this@EditProfile,
                                "La contraseña debe tener al menos 6 caracteres",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (newPassword == password) {
                            Toast.makeText(
                                this@EditProfile,
                                "La nueva contraseña debe ser diferente a la anterior",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (!isPasswordValid(newPassword)) {
                            Toast.makeText(
                                this@EditProfile,
                                "La nueva contraseña debe ser diferente a las anteriores",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        dialog.dismiss()
                    }

                    builder.setNegativeButton("Cancelar") { dialog, _ ->
                        dialog.cancel()
                    }

                    val dialog = builder.create()
                    dialog.show()
                }


            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Firebase", "Error al leer los valores.", error.toException())
            }



            private fun isPasswordValid(newPassword: String): Boolean {
                val passwordHistory = ArrayList<String>()
                // get the last 5 passwords from the database
                myRef.child("PasswordHistory").limitToLast(5)
                    ?.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            for (childSnapshot in snapshot.children) {
                                val password = childSnapshot.getValue(String::class.java)
                                password?.let { passwordHistory.add(it) }
                            }
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.w("Firebase", "Error al leer los valores.", error.toException())
                        }


                    })
                // check if the new password is different from the previous ones
                return !passwordHistory.contains(newPassword)
            }

        })

    }
    private fun playSound() {
        soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
    }

}
