package eyeq.fragilezombie.entity.monster;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityFragileZombie extends EntityZombie {
    public EntityFragileZombie(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if(world.isRemote) {
            return;
        }
        if(rand.nextInt(200) == 0) {
            this.dropItem(Items.ROTTEN_FLESH, 1);
            this.damageEntity(DamageSource.causeMobDamage(this), 5.0F);
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if(!super.attackEntityFrom(source, amount)) {
            return false;
        }

        EntityLivingBase entity = (EntityLivingBase) source.getEntity();
        if(entity != null) {
            entity.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 600, 0));
        }
        return true;
    }
}
