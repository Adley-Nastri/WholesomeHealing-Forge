package com.github.adleynastri.wholesomehealing.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class WholesomeHealingCreativeTab {

    public static final CreativeModeTab WHOLESOME_TAB = new CreativeModeTab("wholesomeHealingTab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(WholesomeItems.ICON_ITEM.get());
        }
    };
}
