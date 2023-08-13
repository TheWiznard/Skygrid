package com.possible_triangle.skygrid.test.mocks

import com.possible_triangle.skygrid.platform.services.IConfigs
import com.possible_triangle.skygrid.world.BlockNbtModifiers

class ConfigMocks : IConfigs {

    override val client = object : IConfigs.IClientConfig {
        override val showBlockTags = false
        override val showProbabilities = false
    }

    override val server = object : IConfigs.IServerConfig {
        override val warnInvalid = false
        override val modifierStrategy = BlockNbtModifiers.ModifierStrategy.FIRST_ONLY
    }

}