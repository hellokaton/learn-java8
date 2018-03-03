# Java 8 新的日期时间 API

## ZoneId

Java 8中的时区操作被很大程度上简化了，新的时区类 `java.time.ZoneId` 是原有的 `java.util.TimeZone` 类的替代品。
ZoneId对象可以通过 `ZoneId.of()` 方法创建，也可以通过 `ZoneId.systemDefault()` 获取系统默认时区：

```java
ZoneId shanghaiZoneId = ZoneId.of("Asia/Shanghai");
ZoneId systemZoneId = ZoneId.systemDefault();
```

`of()` 方法接收一个“区域/城市”的字符串作为参数，你可以通过 `getAvailableZoneIds()` 方法获取所有合法的“区域/城市”字符串：

```java
Set<String> zoneIds = ZoneId.getAvailableZoneIds();
```

对于老的时区类 `TimeZone`，Java 8也提供了转化方法：

```java
ZoneId oldToNewZoneId = TimeZone.getDefault().toZoneId();
```

有了 `ZoneId`，我们就可以将一个 `LocalDate`、`LocalTime` 或 `LocalDateTime` 对象转化为 `ZonedDateTime` 对象：

```java
LocalDateTime localDateTime = LocalDateTime.now();
ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, shanghaiZoneId);
```

`ZonedDateTime` 对象由两部分构成，`LocalDateTime` 和 `ZoneId`，其中 `2018-03-03T15:26:56.147` 部分为 `LocalDateTime`，`+08:00[Asia/Shanghai]` 部分为ZoneId。

另一种表示时区的方式是使用 `ZoneOffset`，它是以当前时间和 **世界标准时间（UTC）/格林威治时间（GMT）** 的偏差来计算，例如：

```java
ZoneOffset zoneOffset = ZoneOffset.of("+09:00");
LocalDateTime localDateTime = LocalDateTime.now();
OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, zoneOffset);
```

## Instant

Instant类在Java日期与时间功能中，表示了时间线上一个确切的点，定义为距离初始时间的时间差（初始时间为GMT 1970年1月1日00:00）经测量一天有86400秒，从初始时间开始不断向前移动。

**创建一个Instant实例**

你可以通过Instant类的工厂方法创建一个Instant实例，例如你可以调用instant.now()来创建一个确切的表达当前时间的Instant对象：

```java
Instant now = Instant.now();
```

另外也有一些其它方法能创建Instant，具体请查阅Java官方文档。

**访问Instant的时间**

一个Instant对象里有两个域：距离初始时间的秒钟数、在当前一秒内的第几纳秒，他们的组合表达了当前时间点。你可以通过以下两个方法得到它们的值：

```java
long seconds =  getEpochSecond()
int nanos   =   getNano()
```

**Instant的计算**

Instant类有一些方法，可以用于获得另一Instant的值，例如：

- `plusSeconds()`
- `plusMillis()`
- `plusNanos()`
- `minusSeconds()`
- `minusMillis()`
- `minusNanos()`

我下面将向你展示两个例子，来说明这些方法如何使用：

```java
Instant now     = Instant.now();
Instant later   = now.plusSeconds(3);
Instant earlier = now.minusSeconds(3);
```

第一行获得了一个Instant对象，表示当前时间。第二行创建了一个Instant表示三秒后，第三行创建了一个Instant表示三秒前。

> seconds 表示从 `1970-01-01 00:00:00` 开始到现在的秒数，nanos 表示纳秒部分（nanos的值不会超过999,999,999）

## Clock

Clock类提供了访问当前日期和时间的方法，Clock是时区敏感的，可以用来取代 `System.currentTimeMillis()` 来获取当前的微秒数。
某一个特定的时间点也可以使用Instant类来表示，Instant 类也可以用来创建老的 `java.util.Date` 对象。

```java
Clock clock = Clock.systemDefaultZone();  
long millis = clock.millis();    
Instant instant = clock.instant();  
Date legacyDate = Date.from(instant);   // legacy java.util.Date  
```

## LocalDate 

LocalDate类是Java 8中日期时间功能里表示一个本地日期的类，它的日期是无时区属性的。
可以用来表示生日、节假日期等等。这个类用于表示一个确切的日期，而不是这个日期所在的时间（如java.util.Date中的2000.01.01表示的实际是这一天的00:00这个瞬间）。

LocalDate类位于java.time包下，人名叫java.time.LocalDate，创建出来的实例也是不可变对象，所以涉及它的计算方法将返回一个新的LocalDate。

**创建一个LocalDate实例**

我们有多种方式可以创建出 `LocalDate` 实例。第一种方法是使用 `now()` 方法获得值为今天当日的 `LocalDate` 对象：

```java
LocalDate localDate = LocalDate.now();
```

另一种方法是使用年月日信息构造出LocalDate对象：

```java
LocalDate localDate2 = LocalDate.of(2018, 3, 3);
```

LocalDate 的 `of()` 方法创建出一个指定年月日的日期，并且没有时区信息。

**访问日期信息**

可以用如下方法访问LocalDate中的日期信息：
 
```java
int   year       = localDate.getYear();
Month month      = localDate.getMonth();
int   dayOfMonth = localDate.getDayOfMonth();
int   dayOfYear  = localDate.getDayOfYear();
DayOfWeek dayOfWeek = localDate.getDayOfWeek();
```

可以注意到getMonth()与getDayOfWeek()方法返回了一个枚举类型代替一个int。你可以通过枚举类型中的getValue()来获得信息。

**LocalDate计算**

你可以进行一堆简单的日期计算，只要使用如下的方法：

- `plusDays()`
- `plusWeeks()`
- `plusMonths()`
- `plusYears()`
- `minusDays()`
- `minusWeeks()`
- `minusMonths()`
- `minusYears()`

以下举几个使用的例子来帮助理解使用：

```java
LocalDate d  = LocalDate.of(2018, 3, 5);
LocalDate d1 = localDate.plusYears(3);
LocalDate d2 = localDate.minusYears(3);
```

1. 第一行创建出一个新的LocalDate对象d，表示2018.3.5。
2. 第二行创建了值等于d日期3年后的LocalDate对象，第三行也是一样，只是值改为d日期的三年前。 

## LocalTime 

LocalTime类是Java 8中日期时间功能里表示一整天中某个时间点的类，它的时间是无时区属性的（早上10点等等）。比如你需要描述学校几点开学，这个时间不涉及在什么城市，这个描述是对任何国家城市都适用的，此时使用无时区的LocalTime就足够了。
LocalTime类的对象也是不可变的，所以计算方法会返回一个新的LocalTime实例。

**创建一个LocatTime实例**

有多种方式可以新建LocalTime实例。比如使用当前时间作为值新建对象：

```java
LocalTime localTime = LocalTime.now();
```

另一种方式是使用指定的时分秒和纳秒来新建对象：

```java
LocalTime localTime2 = LocalTime.of(21, 30, 59, 11001);
```

也有另一种版本的 `of()` 方法只需要小时分钟两项，或时分秒三项值作为参数。

**访问LocalTime对象的时间**

你可以通过这些方法访问其时、分、秒、纳秒：

- `getHour()`
- `getMinute()`
- `getSecond()`
- `getNano()`

**LocalTime的计算**

LocalTime类包含一系列方法，能帮你完成时间计算：
 
- `plusHours()`
- `plusMinutes()`
- `plusSeconds()`
- `plusNanos()`
- `minusHours()`
- `minusMinutes()`
- `minusSeconds()`
- `minusNanos()`

以下举一个例子：

```java
LocalTime localTime2 = LocalTime.of(21, 30, 59, 11001);
LocalTime localTimeLater   = localTime.plusHours(3);
LocalTime localTimeEarlier = localTime.minusHours(3);
```

1. 第一行新建一个LocalTime实例，表示21:30:50的第11001纳秒。
2. 第二行新建了一个LocalTime实例表示这个时间的三小时后，第三行表示三小时前。
3. LocalTime类是Java 8中日期时间功能里表示一整天中某个时间点的类，它的时间是无时区属性的（早上10点等等）。比如你需要描述学校几点开学，这个时间不涉及在什么城市，这个描述是对任何国家城市都适用的，此时使用无时区的LocalTime就足够了。

LocalTime类的对象也是不可变的，所以计算方法会返回一个新的LocalTime实例。

## LocalDateTime 

LocalDateTime类是Java 8中日期时间功能里，用于表示当地的日期与时间的类，它的值是无时区属性的。你可以将其视为Java 8中LocalDate与LocalTime两个类的结合。

LocalDateTime类的值是不可变的，所以其计算方法会返回一个新的LocalDateTime实例。

**创建一个LocatDateTime实例**

可以通过LocalDateTime的静态工厂方法来创建LocalDateTime实例。以下举例使用 `now()` 方法创建：

```java
LocalDateTime localDateTime = LocalDateTime.now();
```

另一种方式是使用指定的年月日、时分秒、纳秒来新建对象：

```java
LocalDateTime localDateTime2 = LocalDateTime.of(2018, 11, 26, 13, 55, 36, 123);
```

**访问LocalDateTime对象的时间**

你可以通过这些方法访问其日期时间：

- `getYear()`
- `getMonth()`
- `getDayOfMonth()`
- `getDayOfWeek()`
- `getDayOfYear()`
- `getHour()`
- `getMinute()`
- `getSecond()`
- `getNano()`

这些方法中有一些返回int有一些返回枚举类型，你可以通过枚举类型中的 `getValue()` 方法来获得int值。

**LocalDateTime的计算**

LocalDateTime 类包含一系列方法，能帮你完成时间计算：
 
- `plusYears()`
- `plusMonths()`
- `plusDays()`
- `plusHours()`
- `plusMinutes()`
- `plusSeconds()`
- `plusNanos()`
- `minusYears()`
- `minusMonths()`
- `minusDays()`
- `minusHours()`
- `minusMinutes()`
- `minusSeconds()`
- `minusNanos()`

以下举一个例子：

```java
LocalDateTime localDateTime  = LocalDateTime.now();
LocalDateTime localDateTime1 = localDateTime.plusYears(3);
LocalDateTime localDateTime2 = localDateTime.minusYears(3);
```

1. 第一行新建一个LocalDateTime实例表示当前这个时间。
2. 第二行新建了一个LocalDateTime实例表示三年后。
3. 第三行也新建了一个LocalDateTime实例表示三小时前。

## ZonedDateTime 

ZonedDateTime类是Java 8中日期时间功能里，用于表示带时区的日期与时间信息的类。可以用于表示一个真实事件的开始时间，如某火箭升空时间等等。

ZonedDateTime 类的值是不可变的，所以其计算方法会返回一个新的ZonedDateTime 实例。

**创建一个ZonedDateTime实例**

有多种方式可以新建ZonedDateTime实例。比如使用当前时间作为值新建对象：

```java
ZonedDateTime dateTime = ZonedDateTime.now();
```

另一种方式是使用指定的年月日、时分秒、纳秒以及时区ID来新建对象：

```java
ZoneId zoneId = ZoneId.of("UTC+1");
ZonedDateTime dateTime2 = ZonedDateTime.of(2015, 11, 30, 23, 45, 59, 1234, zoneId);
```

**访问ZonedDateTime对象的时间**

你可以通过这些方法访问其日期时间：
 
- `getYear()`
- `getMonth()`
- `getDayOfMonth()`
- `getDayOfWeek()`
- `getDayOfYear()`
- `getHour()`
- `getMinute()`
- `getSecond()`
- `getNano()`

这些方法中有一些返回int有一些返回枚举类型，但可以通过枚举类型中的getValue()方法来获得int值。

**ZonedDateTime的计算**

ZonedDateTime类包含一系列方法，能帮你完成时间计算：

- `plusYears()`
- `plusMonths()`
- `plusDays()`
- `plusHours()`
- `plusMinutes()`
- `plusSeconds()`
- `plusNanos()`
- `minusYears()`
- `minusMonths()`
- `minusDays()`
- `minusHours()`
- `minusMinutes()`
- `minusSeconds()`
- `minusNanos()`

但注意计算时，若不巧跨越了夏令时（会补一小时或减一小时），可能得不到希望的结果。一个替代的正确做法是使用Period：

```java
ZonedDateTime zoneDateTime = previousDateTime.plus(Period.ofDays(3));
```

**时区**

时区是用ZoneId类表示的，你可以使用ZoneId.now()或ZoneId.of(“xxx”)来实例化：

```java
ZoneId zoneId = ZoneId.of("UTC+1");
```

传给 `of()` 方法的参数是时区的ID，如“UTC+1”指距离UTC（格林威治时间）有一小时的时差，你可以使用你想要的时差来表示ZoneId（如+1与-5等等）
你也可以使用另一种方式表示zone id，即使用地区名字，也是可以的：

```java
ZoneId zoneId2 = ZoneId.of("Europe/Copenhagen");
ZoneId zoneId3 = ZoneId.of("Europe/Paris");
```

## DateTimeFormatter 

DateTimeFormatter类是Java 8中日期时间功能里，用于解析和格式化日期时间的类，位于 `java.time.format` 包下。

**预定义的DateTimeFormatter实例**

DateTimeFormatter类包含一系列预定义（常量）的实例，可以解析和格式化一些标准时间格式。这将让你免除麻烦的时间格式定义，类中包含如下预定义的实例：

```java
BASIC_ISO_DATE

ISO_LOCAL_DATE
ISO_LOCAL_TIME
ISO_LOCAL_DATE_TIME

ISO_OFFSET_DATE
ISO_OFFSET_TIME
ISO_OFFSET_DATE_TIME

ISO_ZONED_DATE_TIME

ISO_INSTANT

ISO_DATE
ISO_TIME
ISO_DATE_TIME

ISO_ORDINAL_TIME
ISO_WEEK_DATE

RFC_1123_DATE_TIME
```

每个预定义的DateTimeFormatter实例都有不同的日期格式，我就不解释全部的了。具体的可以查阅Java官方文档，但我在这篇的后续中会解释其中几个，以方便理解。

**格式化日期**

当你获取一个DateTimeFormatter实例后，就可以用format()方便来将一个日期格式化为某种字符串，例如：

```java
DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
String formattedDate = formatter.format(LocalDate.now());
System.out.println(formattedDate);
```

这个样例把LocalDate对象格式化了，并输出20150703，这个输出表示现在2018年，3月5日。
再举一个关于ZonedDateTime的例子：

```java
DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
String formattedZonedDate = formatter.format(ZonedDateTime.now());
System.out.println("formattedZonedDate = " + formattedZonedDate);
```

这个例子会输出：20180305+0800
表示今年2018年，3月5日，位于UTC+8时区。

## Duration 

一个Duration对象表示两个Instant间的一段时间，是在Java 8中加入的新功能。

一个Duration实例是不可变的，当创建出对象后就不能改变它的值了。你只能通过Duration的计算方法，来创建出一个新的Durtaion对象。你会在之后的教程中见到的。

**创建Duration实例**

使用 `Duration` 类的工厂方法来创建一个 `Duration` 对象，以下是一个使用 `between()` 的例子：

```java
Instant first = Instant.now();
// wait some time while something happens
Instant second = Instant.now();
Duration duration = Duration.between(first, second);
```

**访问Duration的时间**

一个Duration对象里有两个域：纳秒值（小于一秒的部分），秒钟值（一共有几秒），他们的组合表达了时间长度。注意屯使用System.getCurrentTimeMillis()时不同，Duration不包含毫秒这个属性。
你可以通过以下两个方法得到它们的值：

```java
long seconds =  getSeconds()
int nanos   =   getNano()
```

你也可以转换整个时间到其它单位如纳秒、分钟、小时、天：

- `toNanos()`
- `toMillis()`
- `toMinutes()`
- `toHours()`
- `toDays()`

举例而言：`toNanos()` 与 `getNano()` 不同，`toNanos()` 获得的是 `Duration` 整个时间共有多少纳秒，
而 `getNano()` 只是获得这段时间中小于一秒的部分。

你也许会问，为什么没有 `toSeconds()` 方法，因为已经有 `getSeconds()` 这个方法能达到同样的功能了。

**Duration计算**

Duration类包含一系列的计算方法：

- `plusNanos()`
- `plusMillis()`
- `plusSeconds()`
- `plusMinutes()`
- `plusHours()`
- `plusDays()`
- `minusNanos()`
- `minusMillis()`
- `minusSeconds()`
- `minusMinutes()`
- `minusHours()`
- `minusDays()`

这些方法所做的事都是相似的，我在这儿也不展示内部实现细节了，就展示一个加减的例子吧：

```java
Duration start = ... //obtain a start duration
Duration added      = start.plusDays(3);
Duration subtracted = start.minusDays(3);
```

1. 第一行创建了一个Duration对象叫start，具体怎么创建可以参考前面的代码。
2. 第二三行样例创建了两个新的Duration，通过调用start的加减操作，使得added对象表示的时间比start多三天，而substracted则少三天。

所有的计算方法都会返回一个新的Duration，以保证Duration的不可变属性。

```java
long days = duration.toDays();              // 这段时间的总天数
long hours = duration.toHours();            // 这段时间的小时数
long minutes = duration.toMinutes();        // 这段时间的分钟数
long seconds = duration.getSeconds();       // 这段时间的秒数
long milliSeconds = duration.toMillis();    // 这段时间的毫秒数
long nanoSeconds = duration.toNanos();      // 这段时间的纳秒数
```

## 其他操作

### 增加和减少日期

Java 8中的日期/时间类都是不可变的，这是为了保证线程安全。当然，新的日期/时间类也提供了方法用于创建对象的可变版本，比如增加一天或者减少一天：

```java
LocalDate date = LocalDate.of(2017, 1, 5);          // 2017-01-05

LocalDate date1 = date.withYear(2016);              // 修改为 2016-01-05
LocalDate date2 = date.withMonth(2);                // 修改为 2017-02-05
LocalDate date3 = date.withDayOfMonth(1);           // 修改为 2017-01-01

LocalDate date4 = date.plusYears(1);                // 增加一年 2018-01-05
LocalDate date5 = date.minusMonths(2);              // 减少两个月 2016-11-05
LocalDate date6 = date.plus(5, ChronoUnit.DAYS);    // 增加5天 2017-01-10
```

上面例子中对于日期的操作比较简单，但是有些时候我们要面临更复杂的时间操作，比如将时间调到下一个工作日，
或者是下个月的最后一天，这时候我们可以使用 `with()` 方法的另一个重载方法，它接收一个TemporalAdjuster参数，
可以使我们更加灵活的调整日期：

```java
LocalDate date7 = date.with(nextOrSame(DayOfWeek.SUNDAY));      // 返回下一个距离当前时间最近的星期日
LocalDate date9 = date.with(lastInMonth(DayOfWeek.SATURDAY));   // 返回本月最后一个星期六
```

要使上面的代码正确编译，你需要使用静态导入 `TemporalAdjusters` 对象：

`import static java.time.temporal.TemporalAdjusters.*;`

`TemporalAdjusters` 类中包含了很多静态方法可以直接使用，下面的表格列出了一些方法：

| 方法名 | 描述 |
|:-----:|:-------|
| `dayOfWeekInMonth` | 返回同一个月中每周的第几天 |	
| `firstDayOfMonth` | 返回当月的第一天 |
| `firstDayOfNextMonth` | 返回下月的第一天 |
| `firstDayOfNextYear` | 返回下一年的第一天 |
| `firstDayOfYear` | 返回本年的第一天 |
| `firstInMonth` | 返回同一个月中第一个星期几 |
| `lastDayOfMonth` | 返回当月的最后一天 |
| `lastDayOfNextMonth` | 返回下月的最后一天 |
| `lastDayOfNextYear` | 返回下一年的最后一天 |
| `lastDayOfYear` | 返回本年的最后一天 |
| `lastInMonth` | 返回同一个月中最后一个星期几 |
| `next / previous` | 返回后一个/前一个给定的星期几 |
| `nextOrSame / previousOrSame` | 返回后一个/前一个给定的星期几，如果这个值满足条件，直接返回 |

如果上面表格中列出的方法不能满足你的需求，你还可以创建自定义的 `TemporalAdjuster` 接口的实现，
`TemporalAdjuster` 也是一个函数式接口，所以我们可以使用Lambda表达式：

```java
@FunctionalInterface
public interface TemporalAdjuster {
    Temporal adjustInto(Temporal temporal);
}
```

比如给定一个日期，计算该日期的下一个工作日（不包括星期六和星期天）：

```java
LocalDate date = LocalDate.of(2017, 1, 5);
date.with(temporal -> {
    // 当前日期
    DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));

    // 正常情况下，每次增加一天
    int dayToAdd = 1;

    // 如果是星期五，增加三天
    if (dayOfWeek == DayOfWeek.FRIDAY) {
        dayToAdd = 3;
    }
    
    // 如果是星期六，增加两天
    if (dayOfWeek == DayOfWeek.SATURDAY) {
        dayToAdd = 2;
    }

    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
});
```

### 其他历法

Java中使用的历法是ISO 8601日历系统，它是世界民用历法，也就是我们所说的公历。平年有365天，闰年是366天。闰年的定义是：非世纪年，能被4整除；世纪年能被400整除。为了计算的一致性，公元1年的前一年被当做公元0年，以此类推。


此外Java 8还提供了4套其他历法（很奇怪为什么没有汉族人使用的农历），每套历法都包含一个日期类，分别是：

- `ThaiBuddhistDate`：泰国佛教历
- `MinguoDate`：中华民国历
- `JapaneseDate`：日本历
- `HijrahDate`：伊斯兰历

每个日期类都继承 `ChronoLocalDate` 类，所以可以在不知道具体历法的情况下也可以操作。不过这些历法一般不常用，除非是有某些特殊需求情况下才会使用。

这些不同的历法也可以用于向公历转换：

```java
LocalDate date = LocalDate.now();
JapaneseDate jpDate = JapaneseDate.from(date);
```

由于它们都继承ChronoLocalDate类，所以在不知道具体历法情况下，可以通过ChronoLocalDate类操作日期：

```java
Chronology jpChronology = Chronology.ofLocale(Locale.JAPANESE);
ChronoLocalDate jpChronoLocalDate = jpChronology.dateNow();
```

我们在开发过程中应该尽量避免使用 `ChronoLocalDate`，尽量用与历法无关的方式操作时间，因为不同的历法计算日期的方式不一样，
比如开发者会在程序中做一些假设，假设一年中有12个月，如果是中国农历中包含了闰月，一年有可能是13个月，
但开发者认为是12个月，多出来的一个月属于明年的。
再比如假设年份是累加的，过了一年就在原来的年份上加一，但日本天皇在换代之后需要重新纪年，所以过了一年年份可能会从1开始计算。

在实际开发过程中建议使用 `LocalDate`，包括存储、操作、业务规则的解读；除非需要将程序的输入或者输出本地化，
这时可以使用 `ChronoLocalDate` 类。

### 资料

- [SimpleDateFormat的线程安全问题与解决方案](http://www.cnblogs.com/zemliu/p/3290585.html)
- [为什么SimpleDateFormat不是线程安全的？](http://blog.csdn.net/yiifaa/article/details/73499053)
- [Java获取N天前，N天后的日期（如3天）](http://blog.csdn.net/liuwei0376/article/details/13620879)
