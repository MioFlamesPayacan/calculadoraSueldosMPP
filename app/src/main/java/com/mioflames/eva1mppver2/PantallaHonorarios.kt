package com.mioflames.eva1mppver2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mioflames.eva1mppver2.ui.theme.Eva1MPPver2Theme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class PantallaHonorarios : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            pantallaHonorarios()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
//funcion que contendrá la calculadora de honorarios
fun pantallaHonorarios() {
    //varibale contexto para hacer uso del objeto intent y hacer la transición a la pantalla
    //de inicio
    val contexto = LocalContext.current
    //Variable sueldoBruto que contendrá el sueldo ingresado por el usuario en el textfield
    var sueldoBruto by remember { mutableStateOf(TextFieldValue()) }
    //variable resultado para asignar el resultado al final del calculo.
    var resultado by remember { mutableStateOf("")}

    //Uso del layout column con modificadores para que todo el contenido quede centrado.
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Ingrese su sueldo bruto")
        /*
            Textfield para crear los componentes que permitan el ingreso
            de texto.
         */
        TextField(
            //value lleva el nombre de la varibale
            value = sueldoBruto,
            //On value asignará un nuevo valor a la variable cada vez que el
            //usuario la modifique
            onValueChange = { sueldoBruto = it },
            //texto que se verá dentro del campo
            label = { Text(text = "Ej 500000") },
            //configuracion para mostrar el teclado numerico al ingresar datos
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        //separador
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = {
            //val empleado llama a la clase empleadoHonorario ingresando como parametro el sueldo
            //ingresado por el usuario, convirtiendolo en double, en caso de no poder convertir
            //a double retornará 0
            val empleado = empleadoHonorarios(sueldoBruto.text.toDoubleOrNull() ?: 0.0)
            //llamado a la funcion calcular liquido, pasando los valores de la variable empleado
            //devolviendo el resultado del calculo
            val liquido = empleado.calcularLiquido()
            //Establece el texto de la variable resultado
            resultado = "Tu sueldo liquido es de: $liquido"
        }) {
            Text(text = "Calcular")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(resultado)
        Spacer(modifier = Modifier.height(100.dp))
        //Objeto intent llamando a la actividad de destino asignada en val contexto, en este caso
        //se configura para realizar la transición a la pantalla de inicio.
        Button(onClick = {
            val intent: Intent = Intent(contexto, MainActivity::class.java)
            contexto.startActivity(intent)}) {
            Text(text = "Volver")
        }
    }

}

