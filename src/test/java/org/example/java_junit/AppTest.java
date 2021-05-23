package org.example.java_junit;

import org.junit.*;

public class AppTest {
    @Test
    public void testPrint() {
        new App().print("Hello");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("beforeClass");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("afterClass");
    }

    @Before
    public void before() {
        System.out.println("before");
    }

    @After
    public void after() {
        System.out.println("after");
    }

    @Before
    public void before2() {
        System.out.println("before2");
    }

    @Test
    public void testPrint2() {
        new App().print("World");
    }
}
