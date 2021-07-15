# udplistener

A command-line utility that creates a UDP socket on a specified interface and port and listens for packets from a specific host, or any host, on that port.

```
$ javac udplistener.java
$ java -classpath . udplistener
Syntax: udplistener <local ipaddress to listen on, or 0.0.0.0 for wildcard> <source ipaddress or ANY for all> <destination port>
```  
  
