# udplistener

A command-line utility that creates a UDP socket on a specified interface and port and listens for packets from a specific host, or any host, on that port.

```
$ javac udplistener.java
$ java -classpath . udplistener
Syntax: udplistener <local ipaddress to listen on, or 0.0.0.0 for wildcard> <source ipaddress or ANY for all> <destination port>
```  
  
Example:
```
[root@foo ~]# java -classpath /tmp udplistener 127.0.0.1 ANY 162 &
[1] 180248

==== Listening on /127.0.0.1:162 for packets from ANY ====

[root@foo ~]# snmptrap -v2c -c public 127.0.0.1:162 '' .1.3.6.1.4.1.2636.4.5.0.1 .1.3.6.1.4.1.2636.1.2.3.4 s 'Something Happened!'
==== Got packet from /127.0.0.1:34025 -

        0lpublic�_▒z�0Q+C���0
+
 +�L0"
      +�LSomething Happened!
====

[root@foo ~]#
```
