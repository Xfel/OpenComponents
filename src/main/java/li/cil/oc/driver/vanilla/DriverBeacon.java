package li.cil.oc.driver.vanilla;

import li.cil.oc.api.network.Arguments;
import li.cil.oc.api.network.Callback;
import li.cil.oc.api.network.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverBlock;
import li.cil.oc.driver.ManagedTileEntityEnvironment;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class DriverBeacon extends DriverBlock {
    public DriverBeacon() {
        super(new ItemStack(Block.beacon, 1, OreDictionary.WILDCARD_VALUE));
    }

    @Override
    public ManagedEnvironment createEnvironment(final World world, final int x, final int y, final int z) {
        return new Environment((TileEntityBeacon) world.getBlockTileEntity(x, y, z));
    }

    public class Environment extends ManagedTileEntityEnvironment<TileEntityBeacon> {
        public Environment(final TileEntityBeacon tileEntity) {
            super(tileEntity, "beacon");
        }

        @Callback
        public Object[] getLevels(final Context context, final Arguments args) {
            return new Object[]{tileEntity.getLevels()};
        }

        @Callback
        public Object[] getPrimaryEffect(final Context context, final Arguments args) {
            return new Object[]{getEffectName(tileEntity.getPrimaryEffect())};
        }

        @Callback
        public Object[] getSecondayEffect(final Context context, final Arguments args) {
            return new Object[]{getEffectName(tileEntity.getSecondaryEffect())};
        }
    }

    protected static String getEffectName(final int id) {
        return (id >= 0 && id < Potion.potionTypes.length && Potion.potionTypes[id] != null)
                ? Potion.potionTypes[id].getName()
                : null;
    }
}