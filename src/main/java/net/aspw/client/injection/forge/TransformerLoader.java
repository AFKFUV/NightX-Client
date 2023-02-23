package net.aspw.client.injection.forge;

import net.aspw.client.features.special.script.remapper.injection.transformers.AbstractJavaLinkerTransformer;
import net.aspw.client.injection.transformers.ForgeNetworkTransformer;
import net.aspw.client.injection.transformers.OptimizeTransformer;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.8.9")
public class TransformerLoader implements IFMLLoadingPlugin {

    public TransformerLoader() {
        MixinBootstrap.init();
        Mixins.addConfiguration("client.forge.mixins.json");
        MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{ForgeNetworkTransformer.class.getName(), OptimizeTransformer.class.getName(), AbstractJavaLinkerTransformer.class.getName()};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
