package com.github.adleynastri.wholesomehealing.event;

import com.github.adleynastri.wholesomehealing.WholesomeHealing;
import com.github.adleynastri.wholesomehealing.api.HealingAesthetics;
import com.github.adleynastri.wholesomehealing.config.ConfigFile;
import com.github.adleynastri.wholesomehealing.item.WholesomeItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;


@EventBusSubscriber(modid = WholesomeHealing.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class EventHandler {

    @SubscribeEvent
    public static void onPetInteract(final PlayerInteractEvent.EntityInteract event) {

        Player player = event.getPlayer();

        ItemStack stack = player.getMainHandItem();


        if (!player.level.isClientSide) {
            if (event.getItemStack().getItem().equals(WholesomeItems.BANDAGE.get())) {

                if (event.getTarget() instanceof TamableAnimal animal) {

                    if (animal.isOwnedBy(player) && animal.getHealth() < animal.getMaxHealth()) {

                        if (!player.getCooldowns().isOnCooldown(stack.getItem())) {

                            if (!player.getAbilities().instabuild) {
                                stack.shrink(1);
                                player.getCooldowns().addCooldown(stack.getItem(), ConfigFile.BANDAGE_HEAL_COOLDOWN.get());
                            }

                            animal.heal(ConfigFile.BANDAGE_HEAL_AMT.get());
                            animal.setOrderedToSit(!animal.isInSittingPose());

                            HealingAesthetics aesthetics = new HealingAesthetics(animal, ConfigFile.BANDAGE_HEAL_AMT.get(), ParticleTypes.HEART, SoundEvents.EXPERIENCE_ORB_PICKUP);

                            aesthetics.setHealingSound();
                            aesthetics.setHealingParticles();


                        }


                    }


                } else if (event.getTarget() instanceof Animal animal) {


                    if (animal.getHealth() < animal.getMaxHealth()) {


                        if (!player.getCooldowns().isOnCooldown(stack.getItem())) {

                            if (!player.getAbilities().instabuild) {
                                stack.shrink(1);
                                player.getCooldowns().addCooldown(stack.getItem(), ConfigFile.BANDAGE_HEAL_COOLDOWN.get());
                            }

                            animal.heal(ConfigFile.BANDAGE_HEAL_AMT.get());

                            HealingAesthetics aesthetics = new HealingAesthetics(animal, ConfigFile.BANDAGE_HEAL_AMT.get(), ParticleTypes.HEART, SoundEvents.EXPERIENCE_ORB_PICKUP);

                            aesthetics.setHealingSound();
                            aesthetics.setHealingParticles();


                        }
                    }


                }


            }
        }


    }

}
