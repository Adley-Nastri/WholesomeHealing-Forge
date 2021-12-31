package com.github.adleynastri.wholesomehealing.config;


import net.minecraftforge.common.ForgeConfigSpec;


public final class ConfigFile {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> BANDAGE_HEAL_AMT;
    public static final ForgeConfigSpec.ConfigValue<Integer> BANDAGE_HEAL_COOLDOWN;

    static {

        BUILDER.push("Config for WholesomeHealing!");

        BANDAGE_HEAL_AMT = BUILDER.comment("Amount of HP a bandage should heal by. Default value is 2").define("BandageHealAmount", 2);
        BANDAGE_HEAL_COOLDOWN = BUILDER.comment("Length of bandage usage cooldown in ticks. Default value is 100").define("BandageCooldown", 100);

        BUILDER.pop();
        SPEC = BUILDER.build();

    }


}
