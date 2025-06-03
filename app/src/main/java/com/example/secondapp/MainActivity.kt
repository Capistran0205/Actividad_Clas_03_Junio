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
        val databases = arrayOf("MySQL", "PostgreSQL", "Oracle", "SQL Server", "SQLite")
        val adapter =
            android.widget.ArrayAdapter(this, android.R.layout.simple_spinner_item, databases)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDatabases.adapter = adapter

        // Objeto para obtener el evento del botón
        val secondScreen = findViewById<Button>(R.id.btnConnect)
        secondScreen.setOnClickListener() {
            // SEGUNDO: Ahora sí puedes usar las variables después de inicializarlas
            val database = spinnerDatabases.selectedItem.toString()
            val username = editTextUser.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            // Debug: Mostrar los valores ingresados
            /*Toast.makeText(this, "Usuario: '$username', Password: '$password'", Toast.LENGTH_LONG)
                .show()*/

            if (username == "admin" && password == "123456") {
                Toast.makeText(
                    this,
                    "Credenciales correctas, abriendo segunda pantalla...",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this, MainActivity2::class.java)
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