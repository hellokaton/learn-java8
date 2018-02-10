
## Java 8中的常用函数式接口

| 函数式接口 | 函数描述符 | 原始类型特化 |
|:-----:|:--------|:-------|
| `Predicate<T>` | `T->boolean` | `IntPredicate,LongPredicate, DoublePredicate` |
| `Consumer<T>` | `T->void` | `IntConsumer,LongConsumer, DoubleConsumer` |
| `Function<T,R>` | `T->R` | `IntFunction<R>, IntToDoubleFunction, IntToLongFunction, LongFunction<R>, LongToDoubleFunction, LongToIntFunction, DoubleFunction<R>, ToIntFunction<T>, ToDoubleFunction<T>, ToLongFunction<T>` |
| `Supplier<T>` | `()->T` | `BooleanSupplier,IntSupplier, LongSupplier, DoubleSupplier` |
| `UnaryOperator<T>` | `T->T` | `IntUnaryOperator, LongUnaryOperator, DoubleUnaryOperator` |
| `BinaryOperator<T>` | `(T,T)->T` | `IntBinaryOperator, LongBinaryOperator, DoubleBinaryOperator` |
| `BiPredicate<L,R>` | `(L,R)->boolean` |  |
| `BiConsumer<T,U>` | `(T,U)->void` | `ObjIntConsumer<T>, ObjLongConsumer<T>, ObjDoubleConsumer<T>` |
| `BiFunction<T,U,R>` | `(T,U)->R` | `ToIntBiFunction<T,U>, ToLongBiFunction<T,U>, ToDoubleBiFunction<T,U>` |


## Lambdas及函数式接口的例子

| 使用案例 | Lambda 的例子 | 对应的函数式接口 |
|:-----:|:--------|:-------|
| 布尔表达式 | `(List<String> list) -> list.isEmpty()` | `Predicate<List<String>>` |
| 创建对象 | `() -> new Project()` | `Supplier<Project>` |
| 消费一个对象 | `(Project p) -> System.out.println(p.getStars())` | `Consumer<Project>` |
| 从一个对象中选择/提取 | `(int a, int b) -> a * b` | `IntBinaryOperator` |
| 比较两个对象 | `(Project p1, Project p2) -> p1.getStars().compareTo(p2.getStars())` | `Comparator<Project>或 BiFunction<Project, Project, Integer> 或 ToIntBiFunction<Project, Project>` |


