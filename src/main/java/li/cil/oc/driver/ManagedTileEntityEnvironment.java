package li.cil.oc.driver;

import li.cil.oc.api.Network;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.ManagedEnvironment;
import net.minecraft.tileentity.TileEntity;

public class ManagedTileEntityEnvironment<T> extends ManagedEnvironment {
    protected final T tileEntity;

    public ManagedTileEntityEnvironment(final T tileEntity, final String name) {
        this.tileEntity = tileEntity;
        node = Network.newNode(this, Visibility.Network).
                withComponent(name).
                create();
    }
}