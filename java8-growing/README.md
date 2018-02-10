# Java 8 的发展

## JDK 5

**自动装箱与拆箱**

JDK1.5为每一个基本数据类型定义了一个封装类。使java中的基本数据类型也有自己的对象

```bash
int -->Integer
double --> Double
long --> Long
char --> Character
float --> Float
boolean --> Boolean
short --> Short
byte -- > Byte
```

- 自动装包：将基本类型转换成为对象，例如：`int --> Integer`
- 自动拆包：将对象转换成为基本数据类型，例如：`Integer --> int`

对于 JDK1.5 之前集合总不能存放基本数据类型的问题，现在也能够解决。

**枚举**

枚举是 JDK1.5 推出的一个比较重要的特性。其关键字为 `enum`
例如：定义代表交通灯的枚举

```java
public enum MyEnum{
    RED,GREEN,YELLOW
}
```

**静态导入**

- 优点：使用静态导入可以使被导入类的所有静态变量和静态方法在当前类直接可见，使用这些静态成员无需再给出他们的类名。
- 缺点：过度使用会降低代码的可读性

**变长参数**

在JDK1.5以前，当我们要为一个方法传递多个类型相同的参数时，
我们有两种方法解决

1. 直接传递一个数组过去
2. 有多少个参数就传递多少个参数。

例如：

```java
public void printColor(String red,String green,String yellow){ 
}
```

或者

```java
public void printColor(String[] colors){

}
```

这样编写方法参数虽然能够实现我们想要的效果，但是，这样是不是有点麻烦呢？
再者，如果参数个数不确定，我们怎么办呢？Java JDK1.5为我们提供的可变参数就能够完美的解决这个问题.

例如：

```java
public void printColor(String... colors){

}
```

如果参数的类型相同，那么可以使用 `类型+三个点` ，后面跟一个参数名称的形式。
这样的好处就是，只要参数类型相同，无论传递几个参数都没有限制
注意：可变参数必须是参数列表的最后一项（该特性对对象和基本数据类型都适用）

**泛型**

```java
//给集合指定存入类型，上面这个集合在存入数据的时候必须存入String类型的数据，否则编译器会报错
List<String> strs = new ArrayList<String>();
```

“泛型” 意味着编写的代码可以被不同类型的对象所重用。
可见泛型的提出是为了编写重用性更好的代码。
泛型的本质是参数化类型，也就是说所操作的数据类型被指定为一个参数。
 
比如常见的集合类 `LinkedList`，其实现的接口名后有个特殊的部分 `<>`，而且它的成员的类型 Link 也包含一个 `<>`，这个符号的就是类型参数，
它使得在运行中，创建一个 LinkedList 时可以传入不同的类型，比如 `new LinkedList`，这样它的成员存放的类型也是 `String`。

**For-Each循环**

例如上面这个集合我们可以通过for-each遍历，这样更加简单清晰
    
```java
for(String s : strs){ 
     System.out.println(s); 
}
```

> 注意：使用for-each遍历集合时，要遍历的集合必须实现了Iterator接口

**线程并发库**

线程并发库是 Java1.5 提出的关于多线程处理的高级功能，所在包：`java.util.concurrent` 包括

1. 线程互斥工具类：Lock，ReadWriteLock
2. 线程通信：Condition
3. 线程池：ExecutorService
3. 同步队列：ArrayBlockingQueue
4. 同步集合：ConcurrentHashMap，CopyOnWriteArrayList
5. 线程同步工具：Semaphore

## JDK 6

**Desktop类和SystemTray类**

前者可以用来打开系统默认浏览器浏览指定的URL，打开系统默认邮件客户端给指定的邮箱发邮件，
用默认应用程序打开或编辑文件(比如，用记事本打开以 txt 为后缀名的文件)，    
用系统默认的打印机打印文档；后者可以用来在系统托盘区创建一个托盘程序。

**使用Compiler API**

现在我们可以用JDK1.6 的Compiler API(JSR 199)去动态编译Java源文件，
Compiler API结合反射功能就可以实现动态的产生Java代码并编译执行这些代码，有点动态语言的特征。

这个特性对于某些需要用到动态编译的应用程序相当有用，比如JSP Web Server，当我们手动修改JSP后，
是不希望需要重启Web Server才可以看到效果的，这时候我们就可以用Compiler API来实现动态编译JSP文件。
当然，现在的JSP Web Server也是支持JSP热部署的，现在的JSP Web Server通过在运行期间通过Runtime.exec或ProcessBuilder来调用javac来编译代码，
这种方式需要我们产生另一个进程去做编译工作，不够优雅而且容易使代码依赖与特定的操作系统；
Compiler API通过一套易用的标准的API提供了更加丰富的方式去做动态编译，而且是跨平台的。

**轻量级Http Server API**

JDK1.6 提供了一个简单的 Http Server API，据此我们可以构建自己的嵌入式 Http Server，
它支持Http和Https协议，提供了HTTP1.1的部分实现，没有被实现的那部分可以通过扩展已有的 Http Server API来实现，
程序员必须自己实现 HttpHandler 接口，HttpServer 会调用 `HttpHandler` 实现类的回调方法来处理客户端请求，
在这里，我们把一个 Http 请求和它的响应称为一个交换，包装成 `HttpExchange` 类，`HttpServer` 负责将 `HttpExchange` 传给 `HttpHandler` 实现类的回调方法。

**用Console开发控制台程序**

JDK1.6 中提供了 `java.io.Console` 类专用来访问基于字符的控制台设备。
你的程序如果要与 Windows 下的 cmd 或者 Linux 下的 Terminal 交互，就可以用 `Console` 类代劳。
但我们不总是能得到可用的 Console，一个JVM是否有可用的 Console 依赖于底层平台和 JVM 如何被调用。
如果JVM是在交互式命令行(比如 Windows 的 cmd)中启动的，并且输入输出没有重定向到另外的地方，那么就可以得到一个可用的 Console 实例。

**对脚本语言的支持**

如：ruby，groovy，javascript。

## JDK 7

**数字变量对下滑线的支持**

JDK1.7可以在数值类型的变量里添加下滑线，但是有几个地方是不能添加的

1. 数字的开头和结尾 
2. 小数点前后 
3. F或者L前
 
例如：

```java
int num = 1234_5678_9; 
float num2 = 222_33F; 
long num3 = 123_000_111L;
```

**switch对String的支持**

```java
String status = "orderState";     
switch (status) {   
    case "ordercancel":   
        System.out.println("订单取消");   
        break;   
    case "orderSuccess":   
        System.out.println("预订成功");   
        break;   
    default:   
        System.out.println("状态未知");   
}  
```

**try-with-resource**

- `try-with-resources` 是一个定义了一个或多个资源的 try 声明，这个资源是指程序处理完它之后需要关闭它的对象。
- `try-with-resources` 确保每一个资源在处理完成后都会被关闭。
 
可以使用try-with-resources的资源有： 任何实现了 `java.lang.AutoCloseable` 接口 `java.io.Closeable` 接口的对象。

例如：

```java
public static String readFirstLineFromFile(String path) throws IOException {   

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {   
        return br.readLine();   
    }   
}   
```

在 java 7 以及以后的版本里，`BufferedReader` 实现了 `java.lang.AutoCloseable` 接口。 
由于 `BufferedReader` 定义在 `try-with-resources` 声明里，无论 `try` 语句正常还是异常的结束，
它都会自动的关掉。而在 java7 以前，你需要使用 `finally` 块来关掉这个对象。
    
**捕获多种异常并用改进后的类型检查来重新抛出异常**
 
```java
public static void first(){   
    try {   
        BufferedReader reader = new BufferedReader(new FileReader(""));   
        Connection con = null;   
        Statement stmt = con.createStatement();   
    } catch (IOException | SQLException e) {   
        //捕获多个异常，e就是final类型的   
        e.printStackTrace();   
    }   
} 
```

优点：用一个 `catch` 处理多个异常，比用多个 `catch` 每个处理一个异常生成的字节码要更小更高效。
 
**创建泛型时类型推断**

只要编译器可以从上下文中推断出类型参数，你就可以用一对空着的尖括号 `<>` 来代替泛型参数。
这对括号私下被称为菱形(diamond)。 在Java SE 7之前，你声明泛型对象时要这样

```java
List<String> list = new ArrayList<String>();
```
     
而在Java SE7以后，你可以这样

```java
List<String> list = new ArrayList<>();
``` 

因为编译器可以从前面(List)推断出推断出类型参数，所以后面的 `ArrayList` 之后可以不用写泛型参数了，只用一对空着的尖括号就行。
当然，你必须带着菱形 `<>`，否则会有警告的。 
Java SE7 只支持有限的类型推断：只有构造器的参数化类型在上下文中被显著的声明了，你才可以使用类型推断，否则不行。

```java
List<String> list = new ArrayList<>();l
list.add("A"); 
//这个不行 
list.addAll(new ArrayList<>()); 
// 这个可以 
List<? extends String> list2 = new ArrayList<>(); 
list.addAll(list2);
``` 

## JDK 8

**Lambda表达式和函数式接口**

Lambda表达式（也称为闭包）是Java 8中最大和最令人期待的语言改变。它允许我们将函数当成参数传递给某个方法，
或者把代码本身当作数据处理：函数式开发者非常熟悉这些概念。很多JVM平台上的语言（Groovy、Scala等）从诞生之日就支持Lambda表达式，但是Java开发者没有选择，只能使用匿名内部类代替Lambda表达式。
Lambda的设计耗费了很多时间和很大的社区力量，最终找到一种折中的实现方案，可以实现简洁而紧凑的语言结构。最简单的Lambda表达式可由逗号分隔的参数列表、->符号和语句块组成。

Lambda的设计者们为了让现有的功能与Lambda表达式良好兼容，考虑了很多方法，于是产生了函数接口这个概念。函数接口指的是只有一个函数的接口，这样的接口可以隐式转换为Lambda表达式。java.lang.Runnable和java.util.concurrent.Callable是函数式接口的最佳例子。在实践中，函数式接口非常脆弱：只要某个开发者在该接口中添加一个函数，则该接口就不再是函数式接口进而导致编译失败。为了克服这种代码层面的脆弱性，并显式说明某个接口是函数式接口，Java 8 提供了一个特殊的注解@FunctionalInterface（Java 库中的所有相关接口都已经带有这个注解了），举个简单的函数式接口的定义

**接口的默认方法和静态方法**

Java 8使用两个新概念扩展了接口的含义：默认方法和静态方法。默认方法使得接口有点类似traits，不过要实现的目标不一样。默认方法使得开发者可以在 不破坏二进制兼容性的前提下，往现存接口中添加新的方法，即不强制那些实现了该接口的类也同时实现这个新加的方法。
默认方法和抽象方法之间的区别在于抽象方法需要实现，而默认方法不需要。接口提供的默认方法会被接口的实现类继承或者覆写
由于JVM上的默认方法的实现在字节码层面提供了支持，因此效率非常高。默认方法允许在不打破现有继承体系的基础上改进接口。该特性在官方库中的应用是：给java.util.Collection接口添加新方法，如stream()、parallelStream()、forEach()和removeIf()等等。
尽管默认方法有这么多好处，但在实际开发中应该谨慎使用：在复杂的继承体系中，默认方法可能引起歧义和编译错误。如果你想了解更多细节，可以参考官方文档。

**更好的类型推断**

Java 8 编译器在类型推断方面有很大的提升，在很多场景下编译器可以推导出某个参数的数据类型，从而使得代码更为简洁。

参数 `Value.defaultValue()` 的类型由编译器推导得出，不需要显式指明。在Java 7中这段代码会有编译错误，除非使用 `Value.<String>defaultValue()`。

**Optional**

Java应用中最常见的bug就是空值异常。在Java 8之前，Google Guava引入了 `Optionals` 类来解决 `NullPointerException`，
从而避免源码被各种 `null` 检查污染，以便开发者写出更加整洁的代码。Java 8也将Optional加入了官方库。
`Optional` 仅仅是一个容易存放T类型的值或者null。它提供了一些有用的接口来避免显式的null检查，可以参考Java 8官方文档了解更多细节。

如果Optional实例持有一个非空值，则 `isPresent()` 方法返回true，否则返回false；`orElseGet()` 方法，Optional实例持有null，
则可以接受一个lambda表达式生成的默认值；map()方法可以将现有的 `Optional` 实例的值转换成新的值；orElse()方法与orElseGet()方法类似，
但是在持有null的时候返回传入的默认值。

**Streams**

新增的Stream API（java.util.stream）将生成环境的函数式编程引入了Java库中。
这是目前为止最大的一次对Java库的完善，以便开发者能够写出更加有效、更加简洁和紧凑的代码。

Task 类有一个分数（或伪复杂度）的概念，另外还有两种状态：OPEN 或者 CLOSED。现在假设有一个task集合，
首先看一个问题：在这个task集合中一共有多少个OPEN状态的点？在Java 8之前，要解决这个问题，则需要使用foreach循环遍历task集合；
但是在Java 8中可以利用steams解决：包括一系列元素的列表，并且支持顺序和并行处理。

```java
final Collection<Task> tasks = Arrays.asList(
        new Task(Status.OPEN, 5),
        new Task(Status.OPEN, 13),
        new Task(Status.CLOSED, 8)
);

// Calculate total points of all active tasks using sum()
final long totalPointsOfOpenTasks = tasks
        .stream()
        .filter(task -> task.getStatus() == Status.OPEN)
        .mapToInt(Task::getPoints)
        .sum();

System.out.println("Total points: " + totalPointsOfOpenTasks);
```

这里有很多知识点值得说。首先，tasks集合被转换成steam表示；其次，在steam上的filter操作会过滤掉所有CLOSED的task；
第三，mapToInt操作基于每个task实例的Task::getPoints方法将task流转换成Integer集合；最后，通过sum方法计算总和，得出最后的结果。

**新的日期时间 API**

Java 8引入了新的Date-Time API(JSR 310)来改进时间、日期的处理。时间和日期的管理一直是最令Java开发者痛苦的问题。
java.util.Date 和后来的 java.util.Calendar 一直没有解决这个问题（甚至令开发者更加迷茫）。

因为上面这些原因，诞生了第三方库Joda-Time，可以替代Java的时间管理API。
Java 8中新的时间和日期管理API深受Joda-Time影响，并吸收了很多Joda-Time的精华。
新的java.time包包含了所有关于日期、时间、时区、Instant（跟日期类似但是精确到纳秒）、duration（持续时间）和时钟操作的类。
新设计的API认真考虑了这些类的不变性（从java.util.Calendar吸取的教训），如果某个实例需要修改，则返回一个新的对象。

第二，关注下LocalDate和LocalTime类。LocalDate仅仅包含ISO-8601日历系统中的日期部分；LocalTime则仅仅包含该日历系统中的时间部分。这两个类的对象都可以使用Clock对象构建得到。
LocalDateTime类包含了LocalDate和LocalTime的信息，但是不包含ISO-8601日历系统中的时区信息。这里有一些关于LocalDate和LocalTime的例子：
如果你需要特定时区的data/time信息，则可以使用ZoneDateTime，它保存有ISO-8601日期系统的日期和时间，而且有时区信息。

**Nashorn JavaScript引擎**

Java 8提供了新的Nashorn JavaScript引擎，使得我们可以在JVM上开发和运行JS应用。
Nashorn JavaScript引擎是javax.script.ScriptEngine的另一个实现版本，这类Script引擎遵循相同的规则，允许Java和JavaScript交互使用，例子代码如下：

**Base64**

对 Base64 编码的支持已经被加入到Java 8官方库中，这样不需要使用第三方库就可以进行Base64编码，例子代码如下：

```java
final String text = "Lets Learn Java 8!";

final String encoded = Base64
        .getEncoder()
        .encodeToString(text.getBytes(StandardCharsets.UTF_8));
System.out.println(encoded);

final String decoded = new String(
        Base64.getDecoder().decode(encoded),
        StandardCharsets.UTF_8);
System.out.println(decoded);
```

新的Base64API也支持URL和MINE的编码解码。
