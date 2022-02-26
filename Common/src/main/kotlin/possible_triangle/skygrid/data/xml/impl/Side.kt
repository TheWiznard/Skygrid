package possible_triangle.skygrid.data.xml.impl

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.Registry
import net.minecraft.tags.TagContainer
import net.minecraft.world.level.block.Block
import possible_triangle.skygrid.data.xml.BlockProvider
import possible_triangle.skygrid.data.xml.Extra

@Serializable
@SerialName("side")
data class Side(
    val on: String,
    override val providers: List<BlockProvider>,
    val offset: Int = 1,
    override val probability: Double = 1.0,
    override val shared: Boolean = false,
) : Extra() {

    override fun internalValidate(blocks: Registry<Block>, tags: TagContainer): Boolean {
        return Direction.byName(on) != null && offset > 0
    }

    override fun offset(pos: BlockPos): BlockPos {
        val direction = Direction.byName(on) ?: throw NullPointerException("Unknown direction '$on'")
        return pos.relative(direction, offset)
    }

}