package com.github.adleynastri.wholesomehealing.item;

import com.github.adleynastri.wholesomehealing.WholesomeHealing;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WholesomeItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, WholesomeHealing.MOD_ID);


    public static final RegistryObject<BandageItem> BANDAGE = ITEMS.register("bandage",
            () -> new BandageItem((new Item.Properties().tab(WholesomeHealingCreativeTab.WHOLESOME_TAB).stacksTo(16))));



    public static final RegistryObject<Item> ICON_ITEM = ITEMS.register("icon_item",
            () -> new Item((new Item.Properties().stacksTo(1))));




    public static void register(IEventBus eventBus) {

        ITEMS.register(eventBus);


    }

}
