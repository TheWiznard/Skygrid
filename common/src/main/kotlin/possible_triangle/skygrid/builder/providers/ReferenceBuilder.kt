package possible_triangle.skygrid.builder.providers

import net.minecraft.core.RegistryAccess
import possible_triangle.skygrid.data.xml.Extra
import possible_triangle.skygrid.data.xml.FilterOperator
import possible_triangle.skygrid.data.xml.Transformer
import possible_triangle.skygrid.data.xml.impl.Reference

class ReferenceBuilder(
    private val id: String,
    private val weight: Double = 1.0,
    override val registries: RegistryAccess,
) :
    BlockProviderBuilder<Reference>() {

    override fun buildWith(extras: List<Extra>, transformers: List<Transformer>, filters: List<FilterOperator>): Reference {
        return Reference(id, weight, extras, transformers, filters)
    }
}