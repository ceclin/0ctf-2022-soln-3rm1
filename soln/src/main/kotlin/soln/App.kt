package soln

fun main() {
    println("This is a solution to 3rm1")
    println("Usage: (replace L_HOST, L_PORT, R_HOST, R_PORT and CMD with yours)")
    println("java -Djava.rmi.server.logCalls=true -Djava.rmi.server.hostname=L_HOST -cp soln-all.jar soln.ServerKt CMD L_PORT")
    println("java -cp soln-all.jar soln.RebindKt L_PORT R_HOST R_PORT")
}
