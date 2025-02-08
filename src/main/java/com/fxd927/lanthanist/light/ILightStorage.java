package com.fxd927.lanthanist.light;

public interface ILightStorage {
    int receiveLight(int var1, boolean var2);

    int extractLight(int var1, boolean var2);

    int getLightStored();

    int getMaxLightStored();

    boolean canExtract();

    boolean canReceive();
}
