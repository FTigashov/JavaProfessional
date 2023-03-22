package annotations;

public class TestClass {

    @BeforeSuite
    public void start() {
        System.out.println("started");
    }

    @Test(priority = 1)
    public void testOne() {
        System.out.println("This is test one");
    }

    @Test(priority = 5)
    public void testTwo() {
        System.out.println("This is test two");
    }

    @Test(priority = 3)
    public void testThree() {
        System.out.println("This is test three");
    }

    @Test(priority = 2)
    public void testFour() {
        System.out.println("This is test four");
    }

    @Test(priority = 4)
    public void testFive() {
        System.out.println("This is test five");
    }

    @AfterSuite
    public void end() {
        System.out.println("ended");
    }

}
