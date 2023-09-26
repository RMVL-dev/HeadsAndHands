package com.example.headsandhands.creature

import android.util.Log
import com.example.headsandhands.data.CreatureAttr

interface Creature {

    var creatureState:CreatureAttr

    fun deathCheck(){
        creatureState = creatureState.copy(
            death = creatureState.healthPoints < 1
        )
    }

    fun attack(protectionEnemy:Int):Int{
        var attackModifier = creatureState.attackPoints - protectionEnemy + 1
        if (attackModifier<1){
            attackModifier=1
        }

        var enemyDamage:Int = 0
        for(i in 0..attackModifier) {
            when((1..6).random()){
                5,6 -> {
                    enemyDamage = (0..creatureState.damagePoints).random()
                    break
                }
            }
        }
        return enemyDamage
    }

    fun damage(damage:Int){
        creatureState = creatureState.copy(
            healthPoints = if (creatureState.healthPoints-damage>0) creatureState.healthPoints - damage else 0
        )
        deathCheck()
        Log.d("PLAYER", "player hp: ${creatureState.healthPoints} player death ${creatureState.death}")
    }
}