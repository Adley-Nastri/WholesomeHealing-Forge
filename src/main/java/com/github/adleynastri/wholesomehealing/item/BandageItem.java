package com.github.adleynastri.wholesomehealing.item;


import com.github.adleynastri.wholesomehealing.api.HealingAesthetics;
import com.github.adleynastri.wholesomehealing.config.ConfigFile;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class BandageItem extends Item {


    public BandageItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand pUsedHand) {

        ItemStack stack = player.getItemInHand(pUsedHand);

        HealingAesthetics aesthetics = new HealingAesthetics(player, ConfigFile.BANDAGE_HEAL_AMT.get(), ParticleTypes.HEART, SoundEvents.EXPERIENCE_ORB_PICKUP);


        if (!level.isClientSide) {

            if (player.getHealth() < player.getMaxHealth()) {

                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                    player.getCooldowns().addCooldown(stack.getItem(), ConfigFile.BANDAGE_HEAL_COOLDOWN.get());
                }
                player.heal(ConfigFile.BANDAGE_HEAL_AMT.get());

                aesthetics.setHealingSound();
                aesthetics.setHealingParticles();


                return InteractionResultHolder.consume(stack);
            }

        }
        return InteractionResultHolder.fail(stack);
    }


}
