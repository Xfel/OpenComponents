package li.cil.oc.driver.vanilla;

import li.cil.oc.api.network.Arguments;
import li.cil.oc.api.network.Callback;
import li.cil.oc.api.network.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverBlock;
import li.cil.oc.driver.ManagedTileEntityEnvironment;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class DriverFurnace extends DriverBlock {
    public DriverFurnace() {
        super(new ItemStack(Block.furnaceIdle, 1, OreDictionary.WILDCARD_VALUE),
                new ItemStack(Block.furnaceBurning, 1, OreDictionary.WILDCARD_VALUE));
    }

    @Override
    public ManagedEnvironment createEnvironment(final World world, final int x, final int y, final int z) {
        return new Environment((TileEntityFurnace) world.getBlockTileEntity(x, y, z));
    }

    public class Environment extends ManagedTileEntityEnvironment<TileEntityFurnace> {
        public Environment(final TileEntityFurnace tileEntity) {
            super(tileEntity, "furnace");
        }

        @Callback
        public Object[] getBurnTime(final Context context, final Arguments args) {
            return new Object[]{tileEntity.furnaceBurnTime};
        }

        @Callback
        public Object[] getCookTime(final Context context, final Arguments args) {
            return new Object[]{tileEntity.furnaceCookTime};
        }

        @Callback
        public Object[] getCurrentItemBurnTime(final Context context, final Arguments args) {
            return new Object[]{tileEntity.currentItemBurnTime};
        }

        @Callback
        public Object[] isBurning(final Context context, final Arguments args) {
            return new Object[]{tileEntity.isBurning()};
        }
    }
}