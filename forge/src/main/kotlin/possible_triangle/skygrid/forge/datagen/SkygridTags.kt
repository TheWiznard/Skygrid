package possible_triangle.skygrid.forge.datagen

import net.minecraft.data.DataGenerator
import net.minecraft.data.tags.BlockTagsProvider
import net.minecraft.world.level.block.Blocks
import net.minecraftforge.common.Tags
import net.minecraftforge.common.data.ExistingFileHelper
import possible_triangle.skygrid.SkygridMod
import possible_triangle.skygrid.SkygridMod.MOD_ID

class SkygridTags(generator: DataGenerator, files: ExistingFileHelper) :
    BlockTagsProvider(generator, MOD_ID, files) {

    override fun addTags() {

        tag(SkygridMod.AMETHYST_CLUSTERS).add(Blocks.AMETHYST_CLUSTER,
            Blocks.SMALL_AMETHYST_BUD,
            Blocks.MEDIUM_AMETHYST_BUD,
            Blocks.LARGE_AMETHYST_BUD
        )

        tag(SkygridMod.LOOT_CONTAINERS)
            .add(Blocks.CHEST)
            .add(Blocks.DISPENSER)
            .add(Blocks.DROPPER)
            .add(Blocks.BARREL)
            .addOptionalTag(Tags.Blocks.BARRELS.location)
            .addOptionalTag(Tags.Blocks.CHESTS.location)

        tag(SkygridMod.CHESTS)
            .add(Blocks.CHEST)
            .addOptionalTag(Tags.Blocks.CHESTS_WOODEN.location)

        tag(SkygridMod.BARRELS)
            .add(Blocks.BARREL)
            .addOptionalTag(Tags.Blocks.BARRELS_WOODEN.location)

    }

}