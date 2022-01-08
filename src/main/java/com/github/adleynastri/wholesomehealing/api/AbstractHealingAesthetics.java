package com.github.adleynastri.wholesomehealing.api;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;


public abstract class AbstractHealingAesthetics {

    public Integer healParticlesAmt;
    public SimpleParticleType healParticle;
    public SoundEvent healSound;

    public AbstractHealingAesthetics(Integer healParticlesAmt, SimpleParticleType healParticle, SoundEvent healSound) {
        this.healParticlesAmt = healParticlesAmt;
        this.healParticle = healParticle;
        this.healSound = healSound;
    }


    public abstract void setHealingParticles();


    public abstract void setHealingSound();

}
