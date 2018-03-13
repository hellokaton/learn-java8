# java8 concurrent

1. [知乎专栏 - Threads和Executors](https://zhuanlan.zhihu.com/p/33266682)
2. [知乎专栏 - 同步和锁](http://zhuanlan.zhihu.com/p/33267015)
3. [知乎专栏 - 原子变量和 ConcurrentMap](https://zhuanlan.zhihu.com/p/33267165)

## Thread 和 Runnable

所有的现代操作系统都通过进程和线程来支持并发。进程是通常彼此独立运行的程序的实例，比如，如果你启动了一个Java程序，操作系统产生一个新的进程，与其他程序一起并行执行。
在这些进程的内部，我们使用线程并发执行代码，因此，我们可以最大限度的利用CPU可用的核心（core）。

Java从JDK1.0开始执行线程。在开始一个新的线程之前，你必须指定由这个线程执行的代码，通常称为task。这可以通过实现Runnable——一个定义了一个无返回值无参数的 `run()` 方法的函数接口。

## 线程池

在执行一个异步任务或并发任务时，往往是通过直接 `new Thread()` 方法来创建新的线程，这样做弊端较多，更好的解决方案是合理地利用线程池，线程池的优势很明显，如下：

1. 降低系统资源消耗，通过重用已存在的线程，降低线程创建和销毁造成的消耗；
2. 提高系统响应速度，当有任务到达时，无需等待新线程的创建便能立即执行；
3. 方便线程并发数的管控，线程若是无限制的创建，不仅会额外消耗大量系统资源，更是占用过多资源而阻塞系统或oom等状况，从而降低系统的稳定性。线程池能有效管控线程，统一分配、调优，提供资源使用率；
4. 更强大的功能，线程池提供了定时、定期以及可控线程数等功能的线程池，使用方便简单。

### 线程池用法

**newCachedThreadPool**

创建一个可缓存的无界线程池，该方法无参数。当线程池中的线程空闲时间超过60s则会自动回收该线程，当任务超过线程池的线程数则创建新线程。线程池的大小上限为 `Integer.MAX_VALUE`，可看做是无限大。

```java
public void cachedThreadPoolDemo(){
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    for (int i = 0; i < 5; i++) {
        final int index = i;

        cachedThreadPool.execute(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+", index="+index);
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

运行结果：

```bash
pool-1-thread-1, index=0
pool-1-thread-1, index=1
pool-1-thread-1, index=2
pool-1-thread-1, index=3
pool-1-thread-1, index=4
```

从运行结果可以看出，整个过程都在同一个线程pool-1-thread-1中运行，后面线程复用前面的线程。

**newFixedThreadPool**

创建一个固定大小的线程池，该方法可指定线程池的固定大小，对于超出的线程会在 `LinkedBlockingQueue` 队列中等待。

```java
public void fixedThreadPoolDemo(){
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
    for (int i = 0; i < 6; i++) {
        final int index = i;

        fixedThreadPool.execute(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+", index="+index);
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

运行结果：

```bash
pool-1-thread-1, index=0
pool-1-thread-2, index=1
pool-1-thread-3, index=2
pool-1-thread-1, index=3
pool-1-thread-2, index=4
pool-1-thread-3, index=5
```

从运行结果可以看出，线程池大小为3，每休眠1s后将任务提交给线程池的各个线程轮番交错地执行。线程池的大小设置，可参数 `Runtime.getRuntime().availableProcessors()`。

**newSingleThreadExecutor**

创建一个只有线程的线程池，该方法无参数，所有任务都保存队列LinkedBlockingQueue中，等待唯一的单线程来执行任务，并保证所有任务按照指定顺序(FIFO或优先级)执行。

```java
public void singleThreadExecutorDemo(){
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    for (int i = 0; i < 3; i++) {
        final int index = i;

        singleThreadExecutor.execute(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+", index="+index);
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

运行结果：

```bash
pool-1-thread-1, index=0
pool-1-thread-1, index=1
pool-1-thread-1, index=2
```

从运行结果可以看出，所有任务都是在单一线程运行的。

**newScheduledThreadPool**

创建一个可定时执行或周期执行任务的线程池，该方法可指定线程池的核心线程个数。

```java
public void scheduledThreadPoolDemo(){
    ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
    //定时执行一次的任务，延迟1s后执行
    scheduledThreadPool.schedule(new Runnable() {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+", delay 1s");
        }
    }, 1, TimeUnit.SECONDS);

    //周期性地执行任务，延迟2s后，每3s一次地周期性执行任务
    scheduledThreadPool.scheduleAtFixedRate(new Runnable() {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+", every 3s");
        }
    }, 2, 3, TimeUnit.SECONDS);
}
```

运行结果：

```bash
pool-1-thread-1, delay 1s
pool-1-thread-1, every 3s
pool-1-thread-2, every 3s
pool-1-thread-2, every 3s
...
```

- `schedule(Runnable command, long delay, TimeUnit unit)`: 延迟一定时间后执行 `Runnable` 任务；
- `schedule(Callable callable, long delay, TimeUnit unit)`: 延迟一定时间后执行 `Callable` 任务；
- `scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)`: 延迟一定时间后，以间隔period时间的频率周期性地执行任务；
- `scheduleWithFixedDelay(Runnable command, long initialDelay, long delay,TimeUnit unit)`: 与 `scheduleAtFixedRate()` 方法很类似，
但是不同的是scheduleWithFixedDelay()方法的周期时间间隔是以上一个任务执行结束到下一个任务开始执行的间隔，
而scheduleAtFixedRate()方法的周期时间间隔是以上一个任务开始执行到下一个任务开始执行的间隔，也就是这一些任务系列的触发时间都是可预知的。

> ScheduledExecutorService 功能强大，对于定时执行的任务，建议多采用该方法。

**方法对比**

| 工厂方法 | corePoolSize | maximumPoolSize | keepAliveTime | workQueue |
|:-----:|:--------|:-------|:-------|:-------|
| `newCachedThreadPool` | 0 | Integer.MAX_VALUE | 60s	`SynchronousQueue` |
| `newFixedThreadPool` | nThreads | nThreads | 0 | `LinkedBlockingQueue` |
| `newSingleThreadExecutor` | 1 | 1 | 0 | `LinkedBlockingQueue` |
| `newScheduledThreadPool` | corePoolSize | Integer.MAX_VALUE | 0 | `DelayedWorkQueue` |

其他参数都相同，其中线程工厂的默认类为 `DefaultThreadFactory`，线程饱和的默认策略为 `ThreadPoolExecutor.AbortPolicy`。

## 简单使用 Lock 锁

Java 5 中引入了新的锁机制——java.util.concurrent.locks 中的显式的互斥锁：Lock 接口，它提供了比 `synchronized` 更加广泛的锁定操作。
Lock 接口有 3 个实现它的类：ReentrantLock、ReetrantReadWriteLock.ReadLock 和 ReetrantReadWriteLock.WriteLock，即重入锁、读锁和写锁。
lock 必须被显式地创建、锁定和释放，为了可以使用更多的功能，一般用 ReentrantLock 为其实例化。为了保证锁最终一定会被释放（可能会有异常发生），要把互斥区放在 try 语句块内，并在 finally 语句块中释放锁，尤其当有 return 语句时，return 语句必须放在 try 字句中，以确保 unlock()不会过早发生，从而将数据暴露给第二个任务。因此，采用 lock 加锁和释放锁的一般形式如下：

```java
//默认使用非公平锁，如果要使用公平锁，需要传入参数true
Lock lock = new ReentrantLock();  
lock.lock();  
try {  
    // 更新对象的状态  
    // 捕获异常，必要时恢复到原来的不变约束  
    // 如果有return语句，放在这里  
} finally {  
    //锁必须在finally块中释放
    lock.unlock();
}
```

可重入锁，也叫做递归锁，指的是同一线程外层函数获得锁之后，内层递归函数仍然有获取该锁的代码，但不受影响。
在JAVA环境下 `ReentrantLock` 和 `synchronized` 都是可重入锁。

**ReentrantReadWriteLock**

读写锁：分为读锁和写锁，多个读锁不互斥，读锁与写锁互斥，这是由jvm自己控制的，你只要上好相应的锁即可。
如果你的代码只读数据，可以很多人同时读，但不能同时写，那就上读锁；如果你的代码修改数据，只能有一个人在写，且不能同时读取，那就上写锁。
总之，读的时候上读锁，写的时候上写锁！

`ReentrantReadWriteLock` 会使用两把锁来解决问题，一个读锁，一个写锁

- 线程进入读锁的前提条件
    - 没有其他线程的写锁
    - 没有写请求或者有写请求，但调用线程和持有锁的线程是同一个
- 线程进入写锁的前提条件
    - 没有其他线程的读锁
    - 没有其他线程的写锁
    
## StampedLock

`StampedLock` 是 java 8 在 `java.util.concurrent.locks` 新增的一个API。

`ReentrantReadWriteLock` 在沒有任何读锁和写锁时，才可以取得写入锁，这可用于实现了悲观读取。
然而，如果读取很多，写入很少的情况下，使用 `ReentrantReadWriteLock` 可能会使写入线程遭遇饥饿问题，也就是写入线程无法竞争到锁定而一直处于等待状态。
`StampedLock` 有三种模式的锁，用于控制读取/写入访问，StampedLock 的状态由版本和模式组成。
锁获取操作返回一个用于展示和访问锁状态的票据（stamp）变量，它用相应的锁状态表示并控制访问，数字0表示没有写锁被授权访问。
在读锁上分为悲观锁和乐观锁，锁释放以及其他相关方法需要使用邮戳（stamps）变量作为参数，如果他们和当前锁状态不符则失败，这三种模式为：

- 写入：方法writeLock可能为了获取独占访问而阻塞当前线程，返回一个stamp变量，能够在unlockWrite方法中使用从而释放锁。也提供了tryWriteLock。
当锁被写模式所占有，没有读或者乐观的读操作能够成功。
- 读取：方法readLock可能为了获取非独占访问而阻塞当前线程，返回一个stamp变量，能够在unlockRead方法中用于释放锁。也提供了tryReadLock。
- 乐观读取：方法 `tryOptimisticRead` 返回一个非 0 邮戳变量，仅在当前锁没有以写入模式被持有。如果在获得stamp变量之后没有被写模式持有，方法validate将返回true。
这种模式可以被看做一种弱版本的读锁，可以被一个写入者在任何时间打断。乐观读取模式仅用于短时间读取操作时经常能够降低竞争和提高吞吐量。

> 悲观锁（Pessimistic Lock），顾名思义，就是很悲观，每次去拿数据的时候都认为别人会修改，所以每次在拿数据的时候都会上锁，这样别人想拿这个数据就会block直到它拿到锁。
> 悲观锁：假定会发生并发冲突，屏蔽一切可能违反数据完整性的操作。
> Java synchronized 就属于悲观锁的一种实现，每次线程要修改数据时都先获得锁，保证同一时刻只有一个线程能操作数据，其他线程则会被block。
  
> 乐观锁（Optimistic Lock），顾名思义，就是很乐观，每次去拿数据的时候都认为别人不会修改，所以不会上锁，但是在提交更新的时候会判断一下在此期间别人有没有去更新这个数据。
乐观锁适用于读多写少的应用场景，这样可以提高吞吐量。
> 乐观锁：假设不会发生并发冲突，只在提交操作时检查是否违反数据完整性。

## AtomicInteger

JDK1.5之后的java.util.concurrent.atomic包里，多了一批原子处理类。
AtomicBoolean、AtomicInteger、AtomicLong、AtomicReference。
主要用于在高并发环境下的高效程序处理,来帮助我们简化同步处理.

AtomicInteger，一个提供原子操作的Integer的类。
在Java语言中，++i和i++操作并不是线程安全的，在使用的时候，不可避免的会用到synchronized关键字。
而AtomicInteger则通过一种线程安全的加减操作接口。

```java
public final int get()                  //获取当前的值
public final int getAndSet(int newValue)//获取当前的值，并设置新的值
public final int getAndIncrement()      //获取当前的值，并自增
public final int getAndDecrement()      //获取当前的值，并自减
public final int getAndAdd(int delta)   //获取当前的值，并加上预期的值
```

## LongAccumulator

`LongAdder` 是jdk1.8提供的累加器，基于 `Striped64` 实现。
它常用于状态采集、统计等场景。
AtomicLong也可以用于这种场景，但在线程竞争激烈的情况下，LongAdder要比AtomicLong拥有更高的吞吐量，但会耗费更多的内存空间。

`LongAccumulator` 和 `LongAdder` 类似，也基于Striped64实现。但要比LongAdder更加灵活(要传入一个函数接口)，
LongAdder相当于是LongAccumulator的一种特例。

## Semaphore

Semaphore（信号量）是用来控制同时访问特定资源的线程数量，它通过协调各个线程，以保证合理的使用公共资源。
很多年以来，我都觉得从字面上很难理解Semaphore所表达的含义，只能把它比作是控制流量的红绿灯，比如XX马路要限制流量，只允许同时有一百辆车在这条路上行使，
其他的都必须在路口等待，所以前一百辆车会看到绿灯，可以开进这条马路，后面的车会看到红灯，不能驶入XX马路，
但是如果前一百辆中有五辆车已经离开了XX马路，那么后面就允许有5辆车驶入马路，这个例子里说的车就是线程，驶入马路就表示线程在执行，
离开马路就表示线程执行完成，看见红灯就表示线程被阻塞，不能执行。

**应用场景**

Semaphore可以用于做流量控制，特别公用资源有限的应用场景，比如数据库连接。
假如有一个需求，要读取几万个文件的数据，因为都是IO密集型任务，我们可以启动几十个线程并发的读取，
但是如果读到内存后，还需要存储到数据库中，而数据库的连接数只有10个，
这时我们必须控制只有十个线程同时获取数据库连接保存数据，否则会报错无法获取数据库连接。
这个时候，我们就可以使用Semaphore来做流控。

## 参考资料

- [Java线程池分析](http://gityuan.com/2016/01/16/thread-pool/)
- [浅谈Java中的锁](http://zhwbqd.github.io/2015/02/13/lock-in-java.html)
- [Java原子操作AtomicInteger的用法](https://www.jianshu.com/p/509aca840f6d)
- [Jdk1.8 JUC源码增量解析 LongAdder和LongAccumulator](http://brokendreams.iteye.com/blog/2259858)
- [StampedLock将是解决同步问题的新宠](http://www.importnew.com/14941.html)
- [Java8 StampedLock](https://coderbee.net/index.php/concurrent/20140628/947)
- [控制并发线程数的Semaphore](http://ifeve.com/tag/semaphore/)