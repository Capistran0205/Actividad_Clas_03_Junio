package com.example.secondapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class ShowData : AppCompatActivity() {
    private lateinit var editTextUsuario: EditText
    private lateinit var editTextNombre: EditText
    private lateinit var editTextTelefono: EditText
    private lateinit var buttonOK: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_show_data) // Asegúrate de que este sea el nombre correcto de tu layout

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.showInfo)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializar las vistas
        editTextUsuario = findViewById(R.id.editTextUsuario)
        editTextNombre = findViewById(R.id.editTextNombre)
        editTextTelefono = findViewById(R.id.editTextTelefono)
        buttonOK = findViewById(R.id.buttonOK)

        // Recibir datos del Intent
        val usuario = intent.getStringExtra("USUARIO") ?: ""
        val nombre = intent.getStringExtra("NOMBRE") ?: ""
        val telefono = intent.getStringExtra("TELEFONO") ?: ""
        val database = intent.getStringExtra("DATABASE") ?: ""

        // Mostrar los datos en los campos (pre-llenados)
        editTextUsuario.setText(usuario)
        editTextNombre.setText(nombre)
        editTextTelefono.setText(telefono)

        // Mostrar un mensaje de bienvenida
        Toast.makeText(
            this,
            "Bienvenido $nombre\nConectado a: $database",
            Toast.LENGTH_LONG
        ).show()

        // Configurar el botón OK
        buttonOK.setOnClickListener {
            val usuarioFinal = editTextUsuario.text.toString().trim()
            val nombreFinal = editTextNombre.text.toString().trim()
            val telefonoFinal = editTextTelefono.text.toString().trim()

            // Validaciones
            when {
                usuarioFinal.isEmpty() -> {
                    Toast.makeText(this, "Por favor ingrese el usuario", Toast.LENGTH_SHORT).show()
                    editTextUsuario.requestFocus()
                }
                nombreFinal.isEmpty() -> {
                    Toast.makeText(this, "Por favor ingrese el nombre", Toast.LENGTH_SHORT).show()
                    editTextNombre.requestFocus()
                }
                telefonoFinal.isEmpty() -> {
                    Toast.makeText(this, "Por favor ingrese el teléfono", Toast.LENGTH_SHORT).show()
                    editTextTelefono.requestFocus()
                }
                !isValidPhone(telefonoFinal) -> {
                    Toast.makeText(this, "Por favor ingrese un teléfono válido", Toast.LENGTH_SHORT).show()
                    editTextTelefono.requestFocus()
                }
                else -> {
                    // Datos válidos - mostrar confirmación
                    Toast.makeText(
                        this,
                        "Datos guardados correctamente:\n" +
                                "Usuario: $usuarioFinal\n" +
                                "Nombre: $nombreFinal\n" +
                                "Teléfono: $telefonoFinal",
                        Toast.LENGTH_LONG
                    ).show()

                    // Aquí puedes agregar lógica adicional como:
                    // - Guardar en base de datos
                    // - Enviar a servidor
                    // - Regresar a la actividad anterior
                    // finish() // Para cerrar esta actividad
                }
            }
        }
    }

    // Función para validar teléfono
    private fun isValidPhone(phone: String): Boolean {
        return phone.matches(Regex("^[0-9\\-\\s\\+\\(\\)]{10,15}$"))
    }
}