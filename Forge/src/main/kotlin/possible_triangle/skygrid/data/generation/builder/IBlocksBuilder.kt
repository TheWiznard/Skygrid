package possible_triangle.skygrid.data.generation.builder

import net.minecraft.tags.Tag
import net.minecraft.world.level.block.Block
import possible_triangle.skygrid.data.generation.builder.providers.*

fun interface IBlocksBuilder {

    fun add(block: BlockProviderBuilder<*>)

    fun block(block: Block, weight: Double = 1.0, builder: BlockBuilder.() -> Unit = {}) {
        val key = block.registryName
        require(key != null)
        return block(key.path, key.namespace, weight, builder)
    }

    fun block(id: String, mod: String? = null, weight: Double = 1.0, builder: BlockBuilder.() -> Unit = {}) {
        BlockBuilder(id, mod, weight).also {
            builder(it)
            add(it)
        }
    }

    fun tag(
        tag: Tag.Named<Block>,
        weight: Double = 1.0,
        expand: Boolean = false,
        random: Boolean = true,
        builder: TagBuilder.() -> Unit = {},
    ) {
        return tag(tag.name.path, tag.name.namespace, weight, expand, random, builder)
    }

    fun tag(
        id: String,
        mod: String? = null,
        weight: Double = 1.0,
        expand: Boolean = false,
        random: Boolean = true,
        builder: TagBuilder.() -> Unit = {},
    ) {
        TagBuilder(id, mod, weight, random, expand).also {
            builder(it)
            add(it)
        }
    }

    fun list(name: String? = null, weight: Double = 1.0, builder: BlockListBuilder.() -> Unit = {}) {
        BlockListBuilder(name, weight).also {
            builder(it)
            add(it)
        }
    }

    fun reference(id: String, weight: Double = 1.0, builder: ReferenceBuilder.() -> Unit = {}) {
        ReferenceBuilder(id, weight).also {
            builder(it)
            add(it)
        }
    }

}