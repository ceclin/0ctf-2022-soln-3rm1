// Decompiled with: CFR 0.152
// Class Version: 8
package com.ctf.threermi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FactoryInter
        extends Remote {
    public Object getObject() throws RemoteException;
}
