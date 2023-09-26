package com.example.headsandhands.monster

import com.example.headsandhands.creature.Creature
import com.example.headsandhands.data.CreatureAttr

class Monster(
    override var creatureState: CreatureAttr
):Creature {

    override fun damage(damage: Int) {
        super.damage(damage)

    }
}