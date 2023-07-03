package possible_triangle.skygrid.data.xml.impl

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.minecraft.core.BlockPos
import net.minecraft.core.Registry
import net.minecraft.world.level.block.Block
import possible_triangle.skygrid.data.xml.BlockProvider
import possible_triangle.skygrid.data.xml.Extra
import kotlin.random.Random

@Serializable
@SerialName("offset")
data class Offset(
    val x: Int  = 0,
    val y: Int  = 0,
    val z: Int  = 0,
    override val providers: List<BlockProvider>,
    override val probability: Double = 1.0,
    override val shared: Boolean = false,
) : Extra() {

    override fun internalValidate(blocks: Registry<Block>): Boolean {
        return true
    }

    override fun offset(pos: BlockPos, random: Random): BlockPos {
        return pos.offset(x, y, z)
    }

}