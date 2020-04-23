package possibletriangle.skygrid;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.storage.SaveHandler;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.WorldPersistenceHooks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import possibletriangle.skygrid.command.SkygridCommand;
import possibletriangle.skygrid.data.loading.DimensionLoader;
import possibletriangle.skygrid.generator.SkygridWorldType;

import java.util.List;
import java.util.Set;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Skygrid.MODID)
public class Skygrid implements WorldPersistenceHooks.WorldPersistenceHook {
    public static final String MODID = "skygrid";

    public Skygrid() {
        WorldPersistenceHooks.addHook(this);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        new SkygridWorldType();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {}

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onServerWillStart(final FMLServerAboutToStartEvent event) {
        MinecraftServer server = event.getServer();
        server.getResourceManager().addReloadListener(new DimensionLoader(server));
    }

    @SubscribeEvent
    public void onServerStarting(final FMLServerStartingEvent event) {
        SkygridCommand.register(event.getCommandDispatcher());
    }

    private Set<ResourceLocation> getTags(Item item) {
        if (item instanceof BlockItem) return ((BlockItem) item).getBlock().getTags();
        return item.getTags();
    }

    @SubscribeEvent
    public void onTooltip(final ItemTooltipEvent event) {
        Item item = event.getItemStack().getItem();
        List<ITextComponent> tooltip = event.getToolTip();

        if (event.getFlags().isAdvanced())
            getTags(item).stream()
                    .map(ResourceLocation::toString)
                    .map(s -> '#' + s)
                    .map(StringTextComponent::new)
                    .map(t -> t.applyTextStyle(TextFormatting.GRAY))
                    .forEachOrdered(tooltip::add);
    }

    @Override
    public String getModId() {
        return MODID;
    }

    @Override
    public CompoundNBT getDataForWriting(SaveHandler handler, WorldInfo info) {
        CompoundNBT data = new CompoundNBT();
        CompoundNBT dims = new CompoundNBT();
        DimensionManager.writeRegistry(dims);
        if (!dims.isEmpty())
            data.put("dims", dims);
        return data;
    }

    @Override
    public void readData(SaveHandler handler, WorldInfo info, CompoundNBT tag) {
        if (tag.contains("dims", 10))
            DimensionLoader.readRegistry(tag.getCompound("dims"));
    }
}
