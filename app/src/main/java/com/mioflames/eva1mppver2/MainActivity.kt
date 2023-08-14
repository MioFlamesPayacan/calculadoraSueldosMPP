package com.mioflames.eva1mppver2


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            pantallaInicio()
        }
    }
}

/*Clase abstracta para implementar el diagrama de clases entregado
generando calcularLiquido como una función general que después
será usada por las clases hijas empleadoHonorario y empleadoRegular.
 */
abstract class Empleado(val sueldoBruto:  Double) {
    abstract fun calcularLiquido() : Double
}
//Clase empleado honorario con un porcentaje de retención del 13%
class empleadoHonorarios(sueldoBruto: Double) : Empleado(sueldoBruto){
    override fun calcularLiquido():Double = sueldoBruto * (1 - 0.13)
}
//Clase empleado regular con porcentaje de retención del 20%
class empleadoRegular(sueldoBruto: Double) : Empleado(sueldoBruto){
    override fun calcularLiquido(): Double = sueldoBruto * (1 - 0.20)
}

@Preview
@Composable
//función para la pantalla de inicio que contendrá el Título y dos botones que dirigirán
// a la pantalla de la calculadora correspondiente según elija el usuario
fun pantallaInicio(){
    /* 
        Se agrega una variable de contexto para hacer uso del objeto intent y realizar
        la transición entre pantallas
     */
    val contexto = LocalContext.current
    //Uso de column con modificadores para que todo el contenido quede alineado en el centro
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = "CALCULADORA DE PAGOS LIQUIDOS",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(400.dp))
        Text(text = "Ingrese su tipo de contrato para comenzar")

        Row(modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            //Objeto intent llamando a la actividad de destino asignada en val contexto, en este caso
            //a la actividad de la pantallaempleadoregular.
            Button(onClick = { val intent:Intent = Intent(contexto, PantallaEmpleadoRegular::class.java)
                             contexto.startActivity(intent)},
                Modifier.padding(5.dp)) {
                Text(text = "Empleado")
            }

            Button(onClick = { val intent:Intent = Intent(contexto, PantallaHonorarios::class.java)
                             contexto.startActivity(intent)},
                Modifier.padding(5.dp))
            {
                Text(text = "Honorario")
            }
        }
    }
}


