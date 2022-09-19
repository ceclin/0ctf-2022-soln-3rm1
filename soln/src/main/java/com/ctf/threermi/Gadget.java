// Decompiled with: CFR 0.152
// Class Version: 8
package com.ctf.threermi;

import com.ctf.threermi.UserInter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Method;

public class Gadget
        implements Serializable {
    UserInter user;
    String mName;

    private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        inputStream.defaultReadObject();
        try {
            Method method = this.findMethod(this.user.getGirlFriend().getClass(), this.mName);
            method.invoke(this.user.getGirlFriend(), new Object[0]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Method findMethod(Class clazz, String methodName) throws NoSuchMethodException {
        Method method = clazz.getDeclaredMethod(methodName, new Class[0]);
        method.setAccessible(true);
        return method;
    }
}
