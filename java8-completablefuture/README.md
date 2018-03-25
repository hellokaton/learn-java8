# CompletableFuture

## 为什么要引入 CompletableFuture

- Future.get() 方法会阻塞线程，一直阻塞，直到其所对应的任务完成或因为异常退出。在不恰当的地方调用此方法会因为线程阻塞而降低系统响应度。就像文中提到的。
- Future.get(long, TimeUnit) 可以一定的时间内超时退出，而不会像前一个方法那样一直阻塞线程。但是这对系统响应性的改进是治标不治本。
- Java 8 引入了 CompletableFuture，可用通过 CompletableFuture<Void> thenAccept(Consumer<? super T> action) 方法异步触发 send(Response) 方法。这时 serve() 方法会很快结束。而当 responseFuture 所对应的任务完成时，send(Response) 方法便会被调用，其执行线程同 responseFuture 所对应的任务的执行线程相同。
- 对于上一点，有人会问，何必这么麻烦？直接在 responseFuture 所对应任务 (Runnable or Callable) 里面调用 send(Response) 方法。各种在实际工作中可能会遇到的问题暂且不说。只说两点：
    1. responseFuture 所对应任务是不可修改的，比如调用自第三方模块
    2. 即便代码可修改，但在 responseFuture 所对应的任务中去调用 send(Response) 方法表明的含义是后者的功能从属于前者。这可能从业务角度上看是不合理的。即在本例中，responseFuture 所对应的任务和 send(Response) 方法在业务角度讲是属于同一级的。违反这一点会对代码可读性和可维护性不利。

## 创建 CompletableFuture

以下四个静态方法用来为一段异步执行的代码创建 `CompletableFuture` 对象：

```java
static     CompletableFuture<Void>  runAsync(Runnable runnable)
static     CompletableFuture<Void>  runAsync(Runnable runnable, Executor executor)
static <U> CompletableFuture<U>     supplyAsync(Supplier<U> supplier)
static <U> CompletableFuture<U>     supplyAsync(Supplier<U> supplier, Executor executor)
```

以 `Async` 结尾并且没有指定 `Executor` 的方法会使用 `ForkJoinPool.commonPool()` 作为它的线程池执行异步代码。

## 计算结果完成时的处理

当 `CompletableFuture` 的计算结果完成，或者抛出异常的时候，我们可以执行特定的 `Action`。

```javap
CompletableFuture<T> whenComplete(BiConsumer<? super T,? super Throwable> action)
CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
CompletableFuture<T> exceptionally(Function<Throwable,? extends T> fn)
```

同时进行计算和转换

```java
<U> CompletableFuture<U> handle(BiFunction<? super T,Throwable,? extends U> fn)
<U> CompletableFuture<U> handleAsync(BiFunction<? super T,Throwable,? extends U> fn)
<U> CompletableFuture<U> handleAsync(BiFunction<? super T,Throwable,? extends U> fn, Executor executor)
```

## 结果转换(map)

```java
<U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
<U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
<U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
```

## flatMap

```java
<U> CompletableFuture<U> thenCompose(Function<? super T,? extends CompletionStage<U>> fn)
<U> CompletableFuture<U> thenComposeAsync(Function<? super T,? extends CompletionStage<U>> fn)
<U> CompletableFuture<U> thenComposeAsync(Function<? super T,? extends CompletionStage<U>> fn, Executor executor)
```

## 消耗型

```java
CompletableFuture<Void> thenAccept(Consumer<? super T> action)
CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action)
CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor)
```

`thenAccept(Consumer<? super T> action)` 这个方法的命名采用了类似 Promise 的命名风格。
如果把这个方法命名为 addListener 会更容易理解，但是命名为 addListener 不能体现出 thenAccept 能返回 CompletableFuture 从而形成链式调用的特点。

当两个 `CompletionStage` 都正常完成计算的时候,执行一个 `Runnable`

```java
<U> CompletableFuture<Void> thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action)
<U> CompletableFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action)
<U> CompletableFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T,? super U> action, Executor executor)
    CompletableFuture<Void> runAfterBoth(CompletionStage<?> other,  Runnable action)
```

对上一步的计算结果不关心，执行下一个操作

```java
CompletableFuture<Void> thenRun(Runnable action)
CompletableFuture<Void> thenRunAsync(Runnable action)
CompletableFuture<Void> thenRunAsync(Runnable action, Executor executor)
```

## 组合

```java
<U,V> CompletableFuture<V> 	thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)
<U,V> CompletableFuture<V> 	thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)
<U,V> CompletableFuture<V> 	thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn, Executor executor)
```

## Either

```java
    CompletableFuture<Void>  acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action)
    CompletableFuture<Void>  acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action)
    CompletableFuture<Void>  acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action, Executor executor)
<U> CompletableFuture<U>     applyToEither(CompletionStage<? extends T> other, Function<? super T,U> fn)
<U> CompletableFuture<U>     applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T,U> fn)
<U> CompletableFuture<U>     applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T,U> fn, Executor executor)
```

## allOf、anyOf

```java
static CompletableFuture<Void> 	 allOf(CompletableFuture<?>... cfs)
static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs)
```

[CompletableFuture 的 20 个例子](https://zhuanlan.zhihu.com/p/34921166)
