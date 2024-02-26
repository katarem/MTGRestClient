package io.github.katarem.mtgrestclient.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import io.github.katarem.mtgrestclient.model.User
import io.github.katarem.mtgrestclient.utils.UserDefaults
import io.github.katarem.mtgrestclient.viewmodel.UserViewModel

@Composable
fun UserDetails(navController: NavController?, userModel: UserViewModel, deckCount: Int) {
    val user = userModel.user.collectAsState().value
    val newProfilePhoto = userModel.newProfilePhoto.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        AsyncImage(
            model = user.profileImg?: UserDefaults.DEFAULT_PROFILE_PIC,
            contentDescription = "",
            modifier = Modifier
                .size(150.dp)
                .padding(10.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            )
        Text(text = user.username!!)
        Text(text = "Número de decks: $deckCount")
        Text(text = "Si desea cambiar su foto de perfil introduzca la URL de la imagen")
        TextField(value = newProfilePhoto.value, onValueChange = { userModel.changeProfilePhoto(it) })
        Button(onClick = { userModel.submitNewProfilePhoto() }) {
            Text(text = "Cambiar foto de perfil")
        }
    }


}