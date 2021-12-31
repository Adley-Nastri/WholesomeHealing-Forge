package com.github.adleynastri.wholsomehealing.item;


import com.github.adleynastri.wholsomehealing.config.ConfigFile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Random;


public class BandageItem extends Item {


    public BandageItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public InteractionResult useOn(UseOnContext pContext) {


        if (!pContext.getLevel().isClientSide()) {
            Level level = pContext.getLevel();

            BlockPos posClicked = pContext.getClickedPos();
            Block blockClicked = level.getBlockState(posClicked).getBlock();


            //pContext.getPlayer().sendMessage(new TextComponent("Used bandage on " + blockClicked.getName()), Util.NIL_UUID);
            if (blockClicked == Blocks.GOLD_BLOCK) {
                return InteractionResult.SUCCESS;
            }


        }
        return InteractionResult.FAIL;

    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand pUsedHand) {

        ItemStack stack = player.getItemInHand(pUsedHand);


        if (!level.isClientSide) {


//            for (LivingEntity le : level.getEntitiesOfClass(LivingEntity.class,
//                    new AABB(
//                            player.getX() - 20.0, player.getY() - 20.0,player.getZ() - 20.0,
//                            player.getX() + 20.0, player.getY() + 20.0,player.getZ() + 20.0)))
//
//            {
//
//
//
//
//                if(le instanceof Parrot){
//
//                    if(((Parrot) le).isOwnedBy(player)){
//                        le.kill();
//                    }
//                }
//
//            }


            if (player.getHealth() < player.getMaxHealth()) {
                stack.shrink(1);
                player.heal(ConfigFile.BANDAGE_HEAL_AMT.get());
                player.getCooldowns().addCooldown(stack.getItem(), ConfigFile.BANDAGE_HEAL_COOLDOWN.get());
                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.AMBIENT, 1.0F, 1.0F);

                for (int i = 0; i < ConfigFile.BANDAGE_HEAL_AMT.get(); i++) {

                    spawnParticle(ParticleTypes.HEART, player.getRandomX(1.0D), player.getRandomY() + 0.5D,
                            player.getRandomZ(1.0D), (ServerLevel) level);
                }


                return InteractionResultHolder.consume(stack);
            }

        }
        return InteractionResultHolder.fail(stack);
    }


    private void spawnParticle(SimpleParticleType pType, double x, double y, double z, ServerLevel serverLvl) {


        Random rand = new Random();

        serverLvl.sendParticles(pType, x, y, z, 1, 0, 0, 0, rand.nextGaussian() * 0.02D);

    }


}
