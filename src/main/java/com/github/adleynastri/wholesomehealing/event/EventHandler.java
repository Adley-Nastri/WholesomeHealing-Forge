package com.github.adleynastri.wholesomehealing.event;


import com.github.adleynastri.wholesomehealing.WholesomeHealing;
import com.github.adleynastri.wholesomehealing.config.ConfigFile;
import com.github.adleynastri.wholesomehealing.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

import java.util.Random;


@EventBusSubscriber(modid = WholesomeHealing.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class EventHandler {

    @SubscribeEvent
    public static void onPetInteract(final PlayerInteractEvent.EntityInteract event) {

        Player player = event.getPlayer();

        ItemStack stack = player.getMainHandItem();


        //player.sendMessage(new TextComponent(" " +), Util.NIL_UUID);


        if (!player.level.isClientSide) {
            //player.sendMessage(new TextComponent(event.getResult().name()  ), Util.NIL_UUID);
            if (event.getItemStack().getItem().equals(ModItems.BANDAGE.get())) {



                if (event.getTarget() instanceof TamableAnimal animal) {


                    if (animal.isOwnedBy(player)) {

                        if (animal.getHealth() < animal.getMaxHealth()) {


                            if (!player.getCooldowns().isOnCooldown(stack.getItem())) {
                                player.getCooldowns().addCooldown(stack.getItem(), ConfigFile.BANDAGE_HEAL_COOLDOWN.get());
                                animal.heal(ConfigFile.BANDAGE_HEAL_AMT.get());
                                stack.shrink(1);
                                animal.setOrderedToSit(!animal.isInSittingPose());
                                animal.level.playSound(null, animal.getX(), animal.getY(), animal.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.AMBIENT, 1.0F, 1.0F);


                                for (int i = 0; i < ConfigFile.BANDAGE_HEAL_AMT.get() ; i++) {


                                    double minX = -1.1D;
                                    double maxX = 1.1D;

                                    double minZ = -1.1D;
                                    double maxZ = 1.1D;


                                    Random randX = new Random();
                                    Random randZ = new Random();
                                    Random randNew = new Random();


                                    double randValX = minX + (maxX - minX) * randX.nextDouble();
                                    double randValZ = minZ + (maxZ - minZ) * randZ.nextDouble();

                                    //((ServerLevel) animal.level).sendParticles(ParticleTypes.HEART, animal.getX() + randValX, animal.getY() + 1.5, animal.getZ() + randValZ, 1, 0, 0, 0, 0.05);
                                    ((ServerLevel) animal.level).sendParticles(ParticleTypes.HEART, animal.getRandomX(1.0D),
                                            animal.getRandomY() + 0.5D, animal.getRandomZ(1.0D), 1, 0, 0, 0,
                                            randNew.nextGaussian() * 0.02D);







                                    //animal.level.addParticle(ParticleTypes.HEART, animal.getRandomX(1.0D), animal.getRandomY() + 0.5D, animal.getRandomZ(1.0D), d0, d1, d2);



                                }


                            }


                        }
                    }


                    //event.setCancellationResult(InteractionResult.FAIL);

                    //event.setCanceled(true);

                    //

                }

            }
        }


    }

}
