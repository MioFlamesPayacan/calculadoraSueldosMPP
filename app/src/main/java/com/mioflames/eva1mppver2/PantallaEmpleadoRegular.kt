package com.mioflames.eva1mppver2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class PantallaEmpleadoRegular : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_empleado_regular)

        val buttonCalcular = findViewById<Button>(R.id.buttonCalcular)
        //Configuración del botón calcular.
        buttonCalcular.setOnClickListener{
            //Variables para asignar los elementos de texto y botones buscandolos por el id
            //agregado en el archivo xml
            val resultado = findViewById<TextView>(R.id.textResultado)
            //val sueldo bruto que contendra lo ingresado por el usuario
            val sueldoBruto = findViewById<TextView>(R.id.editTextSueldoBruto)
            //val empleado llamando a la clase empleado regular, ingresando el parámetro que
            // obtendrá el texto ingresado en el edit text, convierte a double lo que ingrese el
            //usuario, en caso de no ingresar numero retornara 0
            val empleado = empleadoRegular(sueldoBruto.text.toString().toDoubleOrNull()?:0.0)
            //Llamado a la función calcular liquido pasando los valores del val anterior y dando
            //el resultado del sueldo liquido.
            val liquido = empleado.calcularLiquido()
            //Se establece el texto del val resultado mostrando el resultado del calculo.
            resultado.text = "Tu sueldo liquido es de ${liquido}"
        }
    }
    //función que genera la transición a la pantalla de inicio
    fun volverAtras(view: View){
        finish()
    }
}