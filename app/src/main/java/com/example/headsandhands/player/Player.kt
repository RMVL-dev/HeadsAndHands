package com.example.headsandhands.player

import com.example.headsandhands.creature.Creature
import com.example.headsandhands.data.CreatureAttr

class Player(
    override var creatureState: CreatureAttr,
    private val maxHP:Int
) :Creature {

    private var healCounter:Int = 0

    fun heal(){
        if ((healCounter<5) && (!creatureState.death)){
            creatureState = creatureState.copy(
                healthPoints = if (creatureState.healthPoints + (0.3*maxHP).toInt() <= maxHP){
                    creatureState.healthPoints + (0.3*maxHP).toInt()
                }else{
                    maxHP
                }
            )
            healCounter++
        }
    }

}