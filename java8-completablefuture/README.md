

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

## 结果转换

```java
<U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
<U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
<U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
```

## 消耗型


```java
CompletableFuture<Void> thenAccept(Consumer<? super T> action)
CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action)
CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor)
```

## 组合

```java
<U> CompletableFuture<U> thenCompose(Function<? super T,? extends CompletionStage<U>> fn)
<U> CompletableFuture<U> thenComposeAsync(Function<? super T,? extends CompletionStage<U>> fn)
<U> CompletableFuture<U> thenComposeAsync(Function<? super T,? extends CompletionStage<U>> fn, Executor executor)
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

