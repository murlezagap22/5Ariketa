package com.example.a5ariketa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.a5ariketa.ui.theme._5AriketaTheme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _5AriketaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GGIKalkulua (
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GGIKalkulua(modifier: Modifier = Modifier) {

    var pisua by remember { mutableStateOf("") }
    var altuera by remember { mutableStateOf("") }
    var adina by remember { mutableStateOf("") }
    var sexua by remember { mutableStateOf("") }

    val p = pisua.toDoubleOrNull()
    val a = altuera.toDoubleOrNull()
    val ad = adina.toIntOrNull()
    val s = sexua.trim().lowercase()

    val gmi = if (p != null && a != null && p > 0 && a > 0) {
        p / ((a / 100) * (a / 100))
    } else {
        null
    }

    val sexuBalioa = when (s) {
        "g", "h" -> 1
        "e", "m" -> 0
        else -> null
    }

    val ggi = if (
        gmi != null &&
        ad != null && ad >= 0 &&
        sexuBalioa != null
    ) {
        (1.20 * gmi) + (0.23 * ad) -
                (10.8 * sexuBalioa) - 5.4
    } else {
        null
    }

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.bermeo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(R.string.izenburua),
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = pisua,
                onValueChange = { pisua = it },
                label = { Text(stringResource(R.string.pisua)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = altuera,
                onValueChange = { altuera = it },
                label = { Text(stringResource(R.string.altuera)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = adina,
                onValueChange = { adina = it },
                label = { Text(stringResource(R.string.adina)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = sexua,
                onValueChange = { sexua = it },
                label = { Text("Sexua (g/h edo e/m)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            //Text(
            //text = if (gmi != null) {
            //"GMI (IMC): %.2f".format(gmi)
            //} else {
            //"GMI (IMC): -"
            //},
            //fontSize = 18.sp
            //)

            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(80.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(24.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = stringResource(
                        R.string.gmi,
                        gmi?.let { "%.2f".format(it) }
                            ?: stringResource(R.string.emaitza_ez)
                    ),
                    fontSize = 18.sp
                )

            }

            Spacer(modifier = Modifier.height(10.dp))

            //Text(
            //text = if (ggi != null) {
            //"Gorputz Gantzaren Ehunekoa (GGI): %.2f%%".format(ggi)
            //} else {
            //"Gorputz Gantzaren Ehunekoa (GGI): -"
            //},
            //fontSize = 18.sp
            //)

            Box(
                modifier = Modifier
                    .width(420.dp)
                    .height(80.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(24.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = stringResource(
                        R.string.ggi,
                        ggi?.let { "%.2f".format(it) }
                            ?: stringResource(R.string.emaitza_ez)
                    ),
                    fontSize = 18.sp
                )

            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun GGIKalkuluaPreview() {
    _5AriketaTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            GGIKalkulua(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}