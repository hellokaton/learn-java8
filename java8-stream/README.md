# Stream

## 中间操作

| 操作 | 类型 | 返回类型 | 操作参数 | 函数描述符 |
|:-----:|:--------|:-------|:-------|:-------|
| `filter` | 中间 | `Stream<T>` | `Predicate<T>` | `T -> boolean` |
| `map` | 中间 | `Stream<R>` | `Function<T, R>` | `T -> R` |
| `limit` | 中间 | `Stream<T>` |  | |
| `sorted` | 中间 | `Stream<T>` | `Comparator<T>` | `(T, T) -> int` |
| `distinct` | 中间 | `Stream<T>` |  |  |

## 收集操作（终端操作）

| 操作 | 类型 | 目的 |
|:-----:|:--------|:-------|
| `forEach` | 收集 | 消费流中的每个元素并对其应用 Lambda。这一操作返回 void |
| `count` | 收集 | 返回流中元素的个数。这一操作返回 long |
| `collect` | 收集 | 把流归约成一个集合，比如 List、Map 甚至是 Integer。 |
