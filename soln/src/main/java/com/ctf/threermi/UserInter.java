// Decompiled with: CFR 0.152
// Class Version: 8
package com.ctf.threermi;

import com.ctf.threermi.Friend;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserInter
        extends Remote {
    public String sayHello(String var1) throws RemoteException;

    public Friend getGirlFriend() throws RemoteException;
}
