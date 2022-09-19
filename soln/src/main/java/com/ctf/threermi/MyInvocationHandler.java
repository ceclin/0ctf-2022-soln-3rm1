// Decompiled with: CFR 0.152
// Class Version: 8
package com.ctf.threermi;

import com.ctf.threermi.FactoryInter;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler
        implements InvocationHandler,
        Serializable {
    FactoryInter object;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(this.object.getObject(), args);
    }
}
