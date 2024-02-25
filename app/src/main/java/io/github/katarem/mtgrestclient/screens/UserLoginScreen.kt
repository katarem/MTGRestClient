package io.github.katarem.mtgrestclient.screens

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import io.github.katarem.mtgrestclient.R
import io.github.katarem.mtgrestclient.navigation.Routes
import io.github.katarem.mtgrestclient.ui.theme.MTGTheme
import io.github.katarem.mtgrestclient.utils.API_STATE
import io.github.katarem.mtgrestclient.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserLoginScreen(navController: NavController?, userModel: UserViewModel){

    val logo = "https://logos-world.net/wp-content/uploads/2023/05/Magic-The-Gathering-Logo.png"
    val background = "https://i.pinimg.com/564x/50/3f/1c/503f1c85e899a332da58f33016ce348b.jpg"
    val errorMessage = userModel.errorMessage.collectAsState()
    val callStatus = userModel.callState.collectAsState()

    if(callStatus.value == API_STATE.SUCCESS){
        navController?.navigate(Routes.HOMESCREEN)
        Log.d("UserLoginScreen", "Se redirige a HomeScreen")
    }

    Box(modifier = Modifier.fillMaxSize()){
        AsyncImage(model = background,
            contentDescription = "background",
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = 10.dp, edgeTreatment = BlurredEdgeTreatment(RectangleShape)),
            contentScale = ContentScale.FillHeight
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
                ){
                AsyncImage(model = logo, contentDescription = "a", modifier = Modifier.padding(10.dp))
                LoginFields(userModel)
                LoginText(userModel)
                LoginButton(userModel)
                Text(text = errorMessage.value)
            }
        }
    }
}

@Composable
fun LoginFields(userModel: UserViewModel) {
    val username = userModel.username.collectAsState()
    val password = userModel.password.collectAsState()

    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username.value,
            onValueChange = { userModel.setUsername(it) },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .clip(CircleShape),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            ),
            placeholder = {
                Text(text = "Email")
            },
            trailingIcon = {
                Icon(painter = painterResource(id = R.drawable.baseline_email_24), contentDescription = "")
            }
        )
        TextField(
            value = password.value,
            onValueChange = { userModel.setPassword(it) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .clip(CircleShape),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            ),
            placeholder = {
                Text(text = "Password")
            },
            trailingIcon = {
                Icon(painter = painterResource(id = R.drawable.baseline_key_24), contentDescription = "")
            }
        )
    }
}

@Composable
fun LoginText(userModel: UserViewModel) {

    val isLogin = userModel.isLogin.collectAsState()
    val modifier = Modifier
        .clickable { userModel.swapLogin() }
        .padding(10.dp)

    val text = if (isLogin.value) "Don't you have an account? Sign in"
    else "Do you have an account? Log in"

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = text,
            fontSize = 14.5.sp,
            textAlign = TextAlign.Center,
            modifier = modifier,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun LoginButton(userModel: UserViewModel){

    val isLogin = userModel.isLogin.collectAsState()

    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center){
        Button(onClick = {
            if(isLogin.value)
                userModel.loginUser()
            else
                userModel.registerUser()
        },
        ) {
            if(isLogin.value)
                Text(text = "Login")
            else
                Text("Sign in")
        }
    }
}




@Composable
@Preview(showSystemUi = true, showBackground = true)
fun UserLoginScreenPreview(){
    MTGTheme {
        val userModel: UserViewModel = viewModel()
        UserLoginScreen(navController = null, userModel)
    }
}