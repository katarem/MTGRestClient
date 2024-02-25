package io.github.katarem.mtgrestclient.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import io.github.katarem.mtgrestclient.model.Card
import io.github.katarem.mtgrestclient.model.Deck
import io.github.katarem.mtgrestclient.model.User
import io.github.katarem.mtgrestclient.utils.API_STATE
import io.github.katarem.mtgrestclient.viewmodel.MTGViewModel

@Composable
fun HomeScreen(navController: NavController?, user: User?, model: MTGViewModel) {
    val decks = model.listaDecks.collectAsState()
    val estadoCall = model.estadoCall.collectAsState()

    DisposableEffect(Unit) {
        model.getDecksByUser(user?.id!!)
        onDispose {
            Log.d("HomeScreen", "Se ha desechado HomeScreen")
        }
    }

    Column(
        Modifier.fillMaxSize()
    ) {
        Header(user){
            navController?.navigate("userDetails")
        }
        if (estadoCall.value == API_STATE.SUCCESS) {
            Text(
                text = "Mis decks",
                fontSize = 20.sp,
                modifier = Modifier.padding(20.dp)
            )
            if (decks.value.isNotEmpty())
                DecksView(decks.value)
            else
                Text(text = "No hay decks :(", Modifier.padding(20.dp))
        }
    }


}

@Composable
fun Header(user: User?, onClick: () -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "MTGCLIENT",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            AsyncImage(
                model = user?.profileImg
                    ?: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRrmZHUKoUrzRvnJzpzqr4ZPhh9B2D-zm450YPGKepXNw&s",
                contentDescription = "a",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .clickable { onClick() },
            )
        }
    }
}

@Composable
fun DecksView(decks: List<Deck>) {
    LazyRow {
        items(decks) { deck ->
            DeckComponent(deck)
        }
    }
}

@Composable
fun DeckComponent(deck: Deck) {

    val showCards = remember { mutableStateOf(false) }

    Column {
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.Yellow
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier.padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(text = deck.deckName ?: "sin nombre")
                        Text(text = deck.color ?: "incoloro")
                        Text(
                            text = "MOSTRAR CARTAS",
                            modifier = Modifier.clickable { showCards.value = !showCards.value })
                    }
                    if (deck.user != null)
                        UserComponent(user = deck.user!!)
                }

            }

        }
        if (showCards.value) {
            if (deck.cards.size > 0)
                LazyColumn(
                    modifier = Modifier
                        .height(600.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(deck.getCardsByUUID().toList()) {cardInfo ->
                        CardView(card = cardInfo.card, amount = cardInfo.amount)
                    }
                }
            else
                Text(text = "(Sin cartas... :/ )", Modifier.padding(20.dp))
        }

    }

}

@Composable
fun UserComponent(user: User) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = user.profileImg
                ?: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRrmZHUKoUrzRvnJzpzqr4ZPhh9B2D-zm450YPGKepXNw&s",
            contentDescription = "a",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(text = user.username ?: "desconocido")
    }
}

@Composable
fun CardView(card: Card, amount: Int) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(300.dp),
            contentScale = ContentScale.Fit,
            model = card.img, contentDescription = ""
        )
        Text(
            text = "x$amount",
            fontSize = 20.sp,
        )
    }
}

/*
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeScreenPreview() {
    HomeScreen(null, null)
}*/