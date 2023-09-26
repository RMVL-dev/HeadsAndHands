package com.example.headsandhands.creature

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.headsandhands.creature.Creature
import com.example.headsandhands.data.CreatureAttr

class CreatureViewModel(
    private val creature: Creature
):ViewModel() {

    var uiState:CreatureAttr by mutableStateOf(creature.creatureState)

    fun refresh(){
        uiState = creature.creatureState
        Log.d("PLAYER", "uiState hp: ${uiState.healthPoints}")
    }
}