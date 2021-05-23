# [Java-JUnit](https://github.com/happyflyer/Java-JUnit)

- [JUnit—Java 单元测试必备工具](https://www.imooc.com/learn/356)

## 1. JUnit4 使用详解

### 1.1. 测试规范

1. 测试方法上必须使用 `@Test` 进行修饰
2. 测试方法必须使用 `public void` 进行修饰，不能带任何的参数
3. 新建一个源代码目录来存放我们的测试代码
4. 测试类的包应该和被测试类保持一致
5. 测试单元中的每个方法必须可以独立测试，测试方法间不能有任何的依赖
6. 测试类使用 `Test` 作为类名的后缀（不是必须）
7. 测试方法使用 `test` 作为方法名的前缀（不是必须）

### 1.2. 测试失败的两种情况

测试用例不是用来证明你是对的，而是用来证明你没有错。

测试用例用来达到想要的预期结果，但对于逻辑错误无能为力。

- Failure
  - 一般由单元测试使用的断言方法判断失败所引起的
  - 表示测试点发现了问题，就是说程序输出的结果和我们预期的不一样
- Error
  - 是由代码异常引起的
  - 它可以产生于测试代码本身的错误
  - 也可是被测试代码中的一个隐藏的 bug

### 1.3. 测试流程

- `@BeforeClass`
  - 修饰的方法会在所有方法被调用前被执行
  - 而且该方法是静态的
  - 所以当测试类被夹在后接着就会运行它
  - 而且在内存中它只会存在一份实例
  - 比较适合夹在配置文件
- `@AfterClass`
  - 所修饰的方法通常用来对资源的清理
  - 如关闭数据库的连接
- `@Before` 和 `@After`
  - 会在每个测试方法的前后各执行一次

### 1.4. 常用注解

- `@Test`：将一个普通的方法修饰成为一个测试方法
  - `@Test(expected=XX.class)`
  - `@Test(timeout=毫秒)`
- `@BeforeClass`：它会在所有的方法运行前被执行，`static` 修饰
- `@AfterClass`：它会在所有的方法运行结束后被执行，`static` 修饰
- `@Before`：会在每一个测试方法被运行前执行一次
- `@After`：会在每一个测试方法运行后被执行一次
- `@Ignore`：所修饰的测试方法会被测试运行前忽略
- `@RunWith`：可以更改测试运行器 `org.junit.runner.Runner`

## 2. JUnit4 的深入使用

### 2.1. 测试套件

```java
// 将测试类改为测试套件类
@RunWith(Suite.class)
// 用数组的形式将测试的类添加到测试套件中
@Suite.SuiteClasses({TaskTest1.class, TaskTest2.class, TaskTest3.class})
public ClassSuiteTest{
    // 要用 public 修饰，套件测试类要为空。
    // 不能有方法。
}
```

测试套件就是组织测试类一起运行的。

1. 写一个作为测试套件的入口类，这个类里不包含其它的方法！！！
2. 更改测试运行器 `Suite.class`
3. 将要测试的类作为数组传入到 `Suite.SuiteClasses({})`

### 2.2. 参数化测试

1. 更改默认的测试运行器为 `@RunWith(ParameTerized.class)`
2. 声明变量来存放预期值和结果值
3. 声明一个返回值为 `Collection` 的公共静态方法，并使用 `@Parameters` 进行修饰
4. 为测试类声明一个带有参数的公共构造函数，并在其中为之声明变量赋值

```java
@RunWith(Parameterized.class)
public class ParameterTest {
    int expected = 0;
    int input1 = 0;
    int input2 = 0;
    @Parameterized.Parameters
    public static Collection<Object[]> t() {
        return Arrays.asList(new Object[][]{
                {3, 1, 2},
                {4, 2, 2}
        });
    }
    public ParameterTest(int expected, int input1, int input2) {
        this.expected = expected;
        this.input1 = input1;
        this.input2 = input2;
    }
    @Test
    public void testAdd() {
        Assert.assertEquals(expected, input1 + input2);
    }
}
```

## 3. JUnit4 在 web 项目中的使用

Spring 与 Hibernate 的整合测试

1. 添加 Spring，Hibernate，MySQL 等 jar 包。
2. 添加 Spring 配置文件，Hibernate 配置文件。
3. `@BeforeClass` 获得 Spring 的配置文件 `ClassPathXmlApplicationContext("配置文件")`。
4. 测试通过 `getBean` 获得 Spring 管理的 bean 是否成功。（Hibernate、Spring+Hibernate 同理测试可得）
