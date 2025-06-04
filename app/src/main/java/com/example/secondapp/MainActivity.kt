package com.example.secondapp

import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var spinnerDatabases: Spinner
    private lateinit var editTextUser: EditText
    private lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // PRIMERO: Inicializar las vistas con findViewById
        spinnerDatabases = findViewById(R.id.spinnerDatabases)
        editTextUser = findViewById(R.id.editTextUser)
        editTextPassword = findViewById(R.id.editTextPassword)

        // Configurar el Spinner con datos
        val validUsers = mapOf(
            "admin" to "123456",
            "prodriguez" to "234567",
            "jorduno" to "345678"
        )

        // HashMap con información completa de usuarios
        val userProfiles = mapOf(
            "admin" to mapOf(
                "nombre" to "Administrador Root",
                "telefono" to "555-0000"
            ),
            "prodriguez" to mapOf(
                "nombre" to "Pedro Rodriguez",
                "telefono" to "555-1234"
            ),
            "jorduno" to mapOf(
                "nombre" to "Jorge Duno",
                "telefono" to "555-5678"
            )
        )

        // Objeto para obtener el evento del botón
        val secondScreen = findViewById<Button>(R.id.btnConnect)
        secondScreen.setOnClickListener() {
            // SEGUNDO: Ahora sí puedes usar las variables después de inicializarlas
            // val database = spinnerDatabases.selectedItem.toString()
            val username = editTextUser.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            // Debug: Mostrar los valores ingresados
            /*Toast.makeText(this, "Usuario: '$username', Password: '$password'", Toast.LENGTH_LONG)
                .show()*/

            if (validUsers[username] == password) {
                Toast.makeText(
                    this,
                    "Credenciales correctas, abriendo segunda pantalla...",
                    Toast.LENGTH_SHORT
                ).show()

                // Crear Intent y pasar datos del usuario autenticado
                val intent = Intent(this, ShowData::class.java)
                intent.putExtra("USUARIO", username)
                intent.putExtra("NOMBRE", userProfiles[username]?.get("nombre") ?: "Usuario")
                intent.putExtra("TELEFONO", userProfiles[username]?.get("telefono") ?: "")
                // intent.putExtra("DATABASE", database)
                // Iniciar la actividad
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Datos Incorrectos. Usuario: '$username', Password: '$password'",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}