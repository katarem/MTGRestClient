package io.github.katarem.mtgrestclient.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import io.github.katarem.mtgrestclient.model.Card
import io.github.katarem.mtgrestclient.model.User
import io.github.katarem.mtgrestclient.navigation.Routes
import io.github.katarem.mtgrestclient.viewmodel.MTGViewModel

@Composable
fun AllCardsScreen(navController: NavController?, user: User, mtgViewModel: MTGViewModel) {

    val allCards = mtgViewModel.allCards.collectAsState()

    DisposableEffect(Unit){
        mtgViewModel.getAllCards()
        onDispose {
            Log.d("AllCardsScreen", "Se ha desechado AllCardsScreen")
        }
    }

    Column {
        Header(user = user) { navController?.navigate(Routes.USERDETAILS) }
        LazyColumn {
            items(allCards.value) { card ->
                SimpleCardView(card = card)
            }
        }
    }

}

@Composable
fun SimpleCardView(card: Card) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(600.dp),
            contentScale = ContentScale.Fit,
            model = card.img, contentDescription = ""
        )
    }
}

