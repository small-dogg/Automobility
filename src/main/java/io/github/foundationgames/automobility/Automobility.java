package io.github.foundationgames.automobility;

import io.github.foundationgames.automobility.block.AutomobilityBlocks;
import io.github.foundationgames.automobility.entity.AutomobilityEntities;
import io.github.foundationgames.automobility.item.AutomobilityItems;
import io.github.foundationgames.automobility.particle.AutomobilityParticles;
import io.github.foundationgames.automobility.recipe.AutoMechanicTableRecipe;
import io.github.foundationgames.automobility.recipe.AutoMechanicTableRecipeSerializer;
import io.github.foundationgames.automobility.resource.AutomobilityData;
import io.github.foundationgames.automobility.screen.AutoMechanicTableScreenHandler;
import io.github.foundationgames.automobility.screen.SingleSlotScreenHandler;
import io.github.foundationgames.automobility.sound.AutomobilitySounds;
import io.github.foundationgames.automobility.util.AUtils;
import io.github.foundationgames.automobility.util.midnightcontrols.ControllerUtils;
import io.github.foundationgames.automobility.util.network.PayloadPackets;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Automobility implements ModInitializer {
    public static final String MOD_ID = "automobility";

    public static final ItemGroup GROUP = FabricItemGroupBuilder.build(id("automobility"), AUtils::createGroupIcon);
    public static final ItemGroup COURSE_ELEMENTS = FabricItemGroupBuilder.build(id("automobility_course_elements"), AUtils::createCourseElementsIcon);
    public static final ItemGroup PREFABS = FabricItemGroupBuilder.build(id("automobility_prefabs"), AUtils::createPrefabsIcon);

    public static final TagKey<Block> SLOPES = TagKey.of(Registry.BLOCK_KEY, id("slopes"));
    public static final TagKey<Block> STEEP_SLOPES = TagKey.of(Registry.BLOCK_KEY, id("steep_slopes"));
    public static final TagKey<Block> NON_STEEP_SLOPES = TagKey.of(Registry.BLOCK_KEY, id("non_steep_slopes"));
    public static final TagKey<Block> STICKY_SLOPES = TagKey.of(Registry.BLOCK_KEY, id("sticky_slopes"));

    public static final ScreenHandlerType<AutoMechanicTableScreenHandler> AUTO_MECHANIC_SCREEN =
            Registry.register(Registry.SCREEN_HANDLER, Automobility.id("auto_mechanic_table"), new ScreenHandlerType<>(AutoMechanicTableScreenHandler::new));
    public static final ScreenHandlerType<SingleSlotScreenHandler> SINGLE_SLOT_SCREEN =
            Registry.register(Registry.SCREEN_HANDLER, Automobility.id("single_slot"), new ScreenHandlerType<>(SingleSlotScreenHandler::new));

    @Override
    public void onInitialize() {
        AutomobilityBlocks.init();
        AutomobilityItems.init();
        AutomobilityEntities.init();
        AutomobilityParticles.init();
        AutomobilitySounds.init();
        initOther();

        PayloadPackets.init();
        AutomobilityData.setup();
        ControllerUtils.initMidnightControlsHandler();
    }

    public static void initOther() {
        Registry.register(Registry.RECIPE_TYPE, AutoMechanicTableRecipe.ID, AutoMechanicTableRecipe.TYPE);
        Registry.register(Registry.RECIPE_SERIALIZER, AutoMechanicTableRecipe.ID, AutoMechanicTableRecipeSerializer.INSTANCE);
    }

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
}
