public class Test {

    public static void main(String[] args){
        Resource resource = new Resource();
        Thread p1 = new Producer(resource,"p1");
        Thread p2 = new Producer(resource,"p2");
        Thread p3 = new Producer(resource,"p3");

        Thread c1 = new Consumer(resource,"c1");
        Thread c2 = new Consumer(resource,"c2");
        Thread c3 = new Consumer(resource,"c3");

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();
    }
}
