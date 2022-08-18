Experimenting with setting pid limit on kubelet and its effect on JVM threads
=============================================================================

Creating local k3s cluster with pids.max limit
----------------------------------------------

```
$ k3d cluster create --port 8089:80@loadbalancer --agents 2 --k3s-arg "--kubelet-arg=pod-max-pids=20@agent:*"
```

Build image and apply deployment
--------------------------------

```
$ docker build -t forklift ./
$ k3d image import forklift
$ kubectl apply -f deployment.yaml
```

Log of pod
----------
```
$ kubectl logs forklift-5f78ff4c55-gsmbc
Thread 0 created
Thread 1 created
Thread 2 created
Thread 0 started
Thread 1 started
Thread 3 created
Thread 2 started
Thread 4 created
Thread 3 started
Thread 5 created
Thread 4 started
[0.083s][warning][os,thread] Failed to start thread "Unknown thread" - pthread_create failed (EAGAIN) for attributes: stacksize: 1024k, guardsize: 0k, detached.
[0.083s][warning][os,thread] Failed to start the native thread for java.lang.Thread "Thread-5"
Exception in thread "main" java.lang.OutOfMemoryError: unable to create native thread: possibly out of memory or process/resource limits reached
        at java.base/java.lang.Thread.start0(Native Method)
        at java.base/java.lang.Thread.start(Thread.java:802)
        at fi.hunludvig.forklift.Forklift.main(Forklift.java:19)
```