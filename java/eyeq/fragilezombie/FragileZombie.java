package eyeq.fragilezombie;

import eyeq.fragilezombie.client.renderer.entity.RenderFragileZombie;
import eyeq.fragilezombie.entity.monster.EntityFragileZombie;
import eyeq.util.client.renderer.ResourceLocationFactory;
import eyeq.util.client.resource.ULanguageCreator;
import eyeq.util.client.resource.lang.LanguageResourceManager;
import eyeq.util.common.registry.UEntityRegistry;
import eyeq.util.world.biome.BiomeUtils;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.util.List;

import static eyeq.fragilezombie.FragileZombie.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
public class FragileZombie {
    public static final String MOD_ID = "eyeq_fragilezombie";

    @Mod.Instance(MOD_ID)
    public static FragileZombie instance;

    private static final ResourceLocationFactory resource = new ResourceLocationFactory(MOD_ID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        registerEntities();
        if(event.getSide().isServer()) {
            return;
        }
        registerEntityRenderings();
        createFiles();
    }

    public static void registerEntities() {
        UEntityRegistry.registerModEntity(resource, EntityFragileZombie.class, "FragileZombie", 0, instance, 0x497533, 0x451D1C);
        List<Biome> biomes = BiomeUtils.getSpawnBiomes(EntityZombie.class, EnumCreatureType.MONSTER);
        EntityRegistry.addSpawn(EntityFragileZombie.class, 10, 1, 2, EnumCreatureType.MONSTER, biomes.toArray(new Biome[0]));
    }

    @SideOnly(Side.CLIENT)
    public static void registerEntityRenderings() {
        RenderingRegistry.registerEntityRenderingHandler(EntityFragileZombie.class, RenderFragileZombie::new);
    }

    public static void createFiles() {
        File project = new File("../1.11.2-FragileZombie");

        LanguageResourceManager language = new LanguageResourceManager();

        language.register(LanguageResourceManager.EN_US, EntityFragileZombie.class, "Fragile Zombie");
        language.register(LanguageResourceManager.JA_JP, EntityFragileZombie.class, "損壊したゾンビ");

        ULanguageCreator.createLanguage(project, MOD_ID, language);
    }
}
