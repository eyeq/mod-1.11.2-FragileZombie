package eyeq.fragilezombie.client.renderer.entity;

import eyeq.util.client.renderer.EntityRenderResourceLocation;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;

import static eyeq.fragilezombie.FragileZombie.MOD_ID;

public class RenderFragileZombie extends RenderZombie {
    protected static final ResourceLocation textures = new EntityRenderResourceLocation(MOD_ID, "fragile_zombie");

    public RenderFragileZombie(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityZombie entity) {
        return textures;
    }
}
