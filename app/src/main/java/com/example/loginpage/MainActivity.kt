package com.example.loginpage

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginpage.ui.theme.Green1
import com.example.loginpage.ui.theme.LoginPageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginPageTheme {
                Box(modifier = Modifier
                    .fillMaxSize()
                ){
                    val navController = rememberNavController()
                    NavGraph(navController = navController)
                }
            }
        }
    }
}

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current.applicationContext

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 26.dp, vertical = 140.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Image(
            painter = painterResource(id = R.drawable.android_img),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 40.dp)
                .size(90.dp)
        )
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors( //focused means we have clicked the text field
                focusedLeadingIconColor = Green1, //icon color
                unfocusedLeadingIconColor = Green1,
                focusedLabelColor = Green1, //title color
                unfocusedLabelColor = Green1,
                focusedContainerColor = Color.White, //background color
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Green1, //border color
                unfocusedIndicatorColor = Green1,
                unfocusedPlaceholderColor = Green1 //hint color
            ),
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 10.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors( //focused means we have clicked the text field
                focusedLeadingIconColor = Green1, //icon color
                unfocusedLeadingIconColor = Green1,
                focusedLabelColor = Green1, //title color
                unfocusedLabelColor = Green1,
                focusedContainerColor = Color.White, //background color
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Green1, //border color
                unfocusedIndicatorColor = Green1,
                unfocusedPlaceholderColor = Green1 //hint color
            ), leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            visualTransformation = PasswordVisualTransformation() //hide the password by displaying *
        )

        Button(onClick = {
            if (authenticate(username, password) ) {
                onLoginSuccess()
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }, colors = ButtonDefaults.buttonColors(Green1),
            border = BorderStroke(1.dp, Color.Gray),
            contentPadding = PaddingValues(start =60.dp, end = 60.dp, top =  8.dp, bottom = 8.dp), //internal padding
            modifier = Modifier.padding(top = 18.dp) //external padding
        ){
            Text(text = "Login", fontSize = 21.sp)
        }
    }
}

@Composable
fun NavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = "Login") {
        composable("Login"){
            LoginScreen(onLoginSuccess = {
                navController.navigate("Home"){
                    popUpTo(0)
                }
            })
        }
        composable("Home"){
            HomeScreen()
        }
    }
}

fun authenticate(username: String, password: String): Boolean {
    return username == "admin" && password == "admin"
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoginPageTheme {
        LoginScreen(onLoginSuccess = {})
        }
}