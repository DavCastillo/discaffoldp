package com.example.discaffoldp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ContentAlpha
import androidx.wear.compose.material.LocalContentAlpha


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViewContainer()

        }

    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun ViewContainer() {
    Scaffold(
        topBar = { Toolbar() },
        bottomBar = { Toolbar2() },
        content = { Content() },
        floatingActionButton = { FAB() },
        floatingActionButtonPosition = FabPosition.End

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {
    val context = LocalContext.current
    TopAppBar(

        title = {
            Text(
                text = "Top bar",
                color = colorResource(id = R.color.white),
                textAlign = TextAlign.Start
            )
        },
        colors = topAppBarColors(containerColor = colorResource(R.color.background)),
        /*navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.baseline_share_24),
                contentDescription = "si",
                modifier = Modifier
                    .size(30.dp)
                    .fillMaxWidth()
            )
        },*/

        actions = {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {

                IconButton(onClick = {

                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain" // Tipo de dato que se va a compartir
                        putExtra(Intent.EXTRA_SUBJECT, "Mira mi sitio web")
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "parangaricutirimicuaro"
                        ) // El contenido a compartir
                    }

                    // Crear un chooser para mostrar las opciones disponibles de aplicaciones para compartir
                    context.startActivity(Intent.createChooser(shareIntent, "Compartir con"))

                }) {
                    Icon(
                        imageVector = Icons.Outlined.Share,
                        contentDescription = "Botón compartir", tint = Color.White
                    )
                }
            }
        }


    )


}

@SuppressLint("QueryPermissionsNeeded")
@Composable
fun Toolbar2() {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(R.color.background))
            .height(60.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_mail_24),
            contentDescription = "Boton para enviar un mail", modifier = Modifier
                .size(60.dp)
                .fillMaxWidth()
                .padding(start = 10.dp)
                .clickable(
                    enabled = true,
                    onClickLabel = "Clickable image",
                    onClick = {
                        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:") // Solo abre aplicaciones de correo
                            putExtra(
                                Intent.EXTRA_EMAIL,
                                arrayOf("ejemplo@correo.com")
                            ) // Correo destinatario
                            putExtra(Intent.EXTRA_SUBJECT, "Asunto del correo") // Asunto del correo
                        }
                        if (emailIntent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(emailIntent)
                        }
                    }
                )
        )
    }


}


@Composable
fun FAB() {
    val context = LocalContext.current
    FloatingActionButton(
        onClick = { Toast.makeText(context, "Like", Toast.LENGTH_SHORT).show() },
        modifier = Modifier
            .padding(start = 15.dp, top = 15.dp, end = 8.dp, bottom = 15.dp)
            .background(Color.Transparent)
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_favorite_24),
            contentDescription = "fab",
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(Color.Black)
        )
    }
}


@Composable
fun Content() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .verticalScroll(scrollState)
            .padding(horizontal = 20.dp, vertical = 70.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.fotoperfil),
            contentDescription = "Imagen",
            modifier = Modifier
                .size(200.dp)
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
        )
        Text(
            text = "David Castillo",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 50.sp
        )

        Text(
            text = "Actualmente estoy cursando el segundo año de formacion profesional en desarrollo de aplicacion multiplataforma en el instituto CIFP Txurdinaga, tengo un gran interes por las nuevas tecnologias y me gustan los videojuegos",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )

        Column(modifier = Modifier.padding(top = 20.dp, start = 5.dp, end = 5.dp)) {
            Row(modifier = Modifier.padding(bottom = 20.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_book_24),
                    contentDescription = "si",
                    modifier = Modifier.size(90.dp)
                )

                Column {

                    Text(
                        text = "Estudios",
                        textAlign = TextAlign.Center,

                        fontSize = 30.sp
                    )
                    Text(
                        text = "Graduado de bachiller en ciencias, Cursando desarrollo de aplicaciones multiplataforma",
                        textAlign = TextAlign.Start,
                        fontSize = 15.sp
                    )
                }

            }
            Row(modifier = Modifier.padding(bottom = 20.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_gamepad_24),
                    contentDescription = "si",
                    modifier = Modifier.size(90.dp)
                )

                Column {

                    Text(
                        text = "Hobby",
                        textAlign = TextAlign.Center,

                        fontSize = 30.sp
                    )
                    Text(
                        text = "Los videojugeos. Nier:Automata, Sagas Souls, Ace Attorney y muchos otros",
                        textAlign = TextAlign.Start,
                        fontSize = 15.sp
                    )
                }
            }
            Row(modifier = Modifier.padding(bottom = 20.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_code_24),
                    contentDescription = "si",
                    modifier = Modifier.size(90.dp)
                )

                Column {

                    Text(
                        text = "Tecnologias",
                        textAlign = TextAlign.Center,

                        fontSize = 30.sp
                    )
                    Text(
                        text = "Telefonos, computadoras, IA, desarrollo de aplicaciones",
                        textAlign = TextAlign.Start,
                        fontSize = 15.sp
                    )
                }
            }

        }

        Row(
            modifier = Modifier
                .padding(top = 30.dp, bottom = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.linkedin),
                contentDescription = "si",
                modifier = Modifier
                    .size(90.dp)
                    .clickable(
                        enabled = true,
                        onClickLabel = "Clickable image",
                        onClick = {
                            imagenurl(context, "https://www.linkedin.com/")
                        }
                    )
            )
            Image(
                painter = painterResource(id = R.drawable.githublogo),
                contentDescription = "si",
                modifier = Modifier
                    .size(90.dp)
                    .clickable(
                        enabled = true,
                        onClickLabel = "Clickable image",
                        onClick = {
                            imagenurl(context, "https://github.com/DavCastillo")
                        })
            )
            Image(
                painter = painterResource(id = R.drawable.instagramlogo),
                contentDescription = "si",
                modifier = Modifier
                    .size(90.dp)
                    .clickable(
                        enabled = true,
                        onClickLabel = "Clickable image",
                        onClick = {
                            imagenurl(context, "https://www.instagram.com/?hl=es")
                        }
                    )
            )
        }
    }
}


fun imagenurl(context: Context, linkausar: String) {

    val webIntent = Intent(Intent.ACTION_VIEW).apply {
        data =
            Uri.parse(linkausar) // La URL que se quiere abrir
    }
    // Iniciar el intent para abrir el navegador
    context.startActivity(webIntent)
}

