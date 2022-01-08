package com.github.adleynastri.wholesomehealing.api;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;

import java.util.Random;

public class HealingAesthetics extends AbstractHealingAesthetics {

    Entity entity;


    public HealingAesthetics(Entity targetEntity, Integer healParticlesAmt, SimpleParticleType healParticle, SoundEvent healSound) {
        super(healParticlesAmt, healParticle, healSound);
        this.entity = targetEntity;

    }


    @Override
    public void setHealingParticles() {

        for (int i = 0; i < healParticlesAmt; i++) {

            Random randNew = new Random();
            ((ServerLevel) entity.level).sendParticles(
                    healParticle,
                    entity.getRandomX(1.0D),
                    entity.getRandomY() + 0.5D,
                    entity.getRandomZ(1.0D), 1, 0, 0, 0,
                    randNew.nextGaussian() * 0.02D


            );


        }


    }


    @Override
    public void setHealingSound() {

        entity.level.playSound(
                null, entity.getX(),
                entity.getY(), entity.getZ(),
                healSound,
                SoundSource.AMBIENT, 1.0F, 1.0F
        );

    }

    public int getHealParticlesAmt() {

        return this.healParticlesAmt;
    }

    public SimpleParticleType getHealParticleType() {

        return this.healParticle;
    }


    public SoundEvent getHealSound() {

        return this.healSound;
    }


    public void setHealParticlesAmt(int amount) {

        this.healParticlesAmt = amount;
    }

    public void setHealParticleType(SimpleParticleType particleType) {

        this.healParticle = particleType;
    }

    public void setHealSound(SoundEvent soundType) {

        this.healSound = soundType;

    }

}
