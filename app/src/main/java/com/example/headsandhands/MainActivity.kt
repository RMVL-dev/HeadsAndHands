package com.example.headsandhands

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.headsandhands.creature.Creature
import com.example.headsandhands.creature.CreatureViewModel
import com.example.headsandhands.data.monsterAttr
import com.example.headsandhands.data.playerAttr
import com.example.headsandhands.monster.Monster
import com.example.headsandhands.player.Player
import com.example.headsandhands.ui.theme.HeadsAndHandsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeadsAndHandsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val player = Player(creatureState = playerAttr, maxHP = playerAttr.healthPoints)
                    val monster = Monster(creatureState = monsterAttr)
                    val monsterViewModel = CreatureViewModel(monster)
                    val playerViewModel = CreatureViewModel(player)
                    Game(
                        player = player,
                        playerViewModel = playerViewModel,
                        monster = monster,
                        monsterViewModel = monsterViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun Game(
    player: Player,
    playerViewModel: CreatureViewModel,
    monster: Monster,
    monsterViewModel: CreatureViewModel
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Field(
            creature = monster,
            creatureViewModel = monsterViewModel
        ){
            player.damage(monster.attack(player.creatureState.protectionPoints))
            monsterViewModel.refresh()
            playerViewModel.refresh()
            Log.d("GAME","player hp: ${player.creatureState.healthPoints} monster hp: ${monster.creatureState.healthPoints}")
        }
        Field(
            creature = player,
            creatureViewModel = playerViewModel
        ){
            monster.damage(player.attack(monster.creatureState.protectionPoints))
            playerViewModel.refresh()
            monsterViewModel.refresh()
            Log.d("GAME","player hp: ${player.creatureState.healthPoints} monster hp: ${monster.creatureState.healthPoints}")
        }
    }
}


@Composable
fun Field(
    creature: Creature,
    creatureViewModel: CreatureViewModel,
    attack:()->Unit
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {
        Column {
            Button(onClick = {
                attack()
            }) {
                Text(text = "attack")
            }
            if (creature is Player) {
                Button(onClick = {
                    creature.heal()
                    creatureViewModel.refresh()
                }) {
                    Text(text = "heal")
                }
            }
        }
        Column {
            Text(text = "hp: ${creatureViewModel.uiState.healthPoints}")
            Text(text = "death: ${creatureViewModel.uiState.death}")
        }
    }
}

