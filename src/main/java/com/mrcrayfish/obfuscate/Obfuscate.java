package com.mrcrayfish.obfuscate;

import com.mrcrayfish.obfuscate.common.data.SyncedPlayerData;
import com.mrcrayfish.obfuscate.network.PacketHandler;
import com.mrcrayfish.obfuscate.proxy.ClientProxy;
import com.mrcrayfish.obfuscate.proxy.CommonProxy;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Author: MrCrayfish
 */
@Mod(Reference.MOD_ID)
public class Obfuscate
{
    public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_NAME);

    public static final CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public Obfuscate()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupCommon);
    }

    private void setupCommon(FMLCommonSetupEvent event)
    {
        SyncedPlayerData.init();
        PacketHandler.register();
    }

    private void setupClient(FMLClientSetupEvent event)
    {
        PROXY.setupClient();
    }
}
