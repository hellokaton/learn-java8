# Optional

## Optional类的方法

| 方法 | 描述 |
|:-----:|:-------|
| `empty` | 返回一个空的 Optional 实例 |
| `filter` | 如果值存在并且满足提供的断言， 就返回包含该值的 Optional 对象；否则返回一个空的 Optional 对象 |
| `map` | 如果值存在，就对该值执行提供的 mapping 函数调用 |
| `flatMap` | 如果值存在，就对该值执行提供的 mapping 函数调用，返回一个 Optional 类型的值，否则就返 回一个空的 Optional 对象 |
| `get` | 如果该值存在，将该值用 Optional 封装返回，否则抛出一个 NoSuchElementException 异常 |
| `ifPresent` | 如果值存在，就执行使用该值的方法调用，否则什么也不做 |
| `isPresent` | 如果值存在就返回 true，否则返回 false |
| `of` | 将指定值用 Optional 封装之后返回，如果该值为 null，则抛出一个 NullPointerException 异常 |
| `ofNullable` | 将指定值用 Optional 封装之后返回，如果该值为 null，则返回一个空的 Optional 对象 |
| `orElse` | 如果有值则将其返回，否则返回一个默认值 |
| `orElseGet` | 如果有值则将其返回，否则返回一个由指定的 Supplier 接口生成的值 |
| `orElseThrow` | 如果有值则将其返回，否则抛出一个由指定的 Supplier 接口生成的异常 |
