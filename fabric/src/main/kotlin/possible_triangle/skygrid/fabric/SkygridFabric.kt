package possible_triangle.skygrid.fabric

import kotlinx.serialization.ExperimentalSerializationApi
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.core.Registry
import net.minecraft.data.BuiltinRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackType
import nl.adaptivity.xmlutil.ExperimentalXmlUtilApi
import possible_triangle.skygrid.SkygridMod
import possible_triangle.skygrid.SkygridMod.MOD_ID
import possible_triangle.skygrid.command.SkygridCommand
import possible_triangle.skygrid.data.XMLResource
import possible_triangle.skygrid.fabric.platform.FabricPlatformHelper
import possible_triangle.skygrid.world.SkygridPreset

@ExperimentalXmlUtilApi
@ExperimentalSerializationApi
@Suppress("unused")
object SkygridFabric : ModInitializer {

    override fun onInitialize() {
        SkygridMod.init()
        SkygridMod.setup()
        FabricPlatformHelper.register()

        XMLResource.register {
            val listener = FabricReloadListener(it, it.path)
            ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(listener)
        }

        ServerLifecycleEvents.SERVER_STARTING.register { XMLResource.reload(it) }
        ServerLifecycleEvents.SERVER_STOPPING.register { XMLResource.clear() }

        Registry.register(BuiltinRegistries.WORLD_PRESET, ResourceLocation(MOD_ID, MOD_ID), SkygridPreset())

        CommandRegistrationCallback.EVENT.register { it, _, _ -> SkygridCommand.register(it) }
    }

}