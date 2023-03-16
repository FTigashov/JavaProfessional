package multithreading;

public class TaskA {
    private char symbol = 'A';
    private Object monitor = new Object();

    public static void main(String[] args) {
        new TaskA().createThreads();
    }

    private void createThreads() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                printA();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                printB();
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                printC();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }

    private void printA() {
        synchronized (monitor) {
            try {
                while (symbol != 'A') {
                    monitor.wait();
                }
                System.out.println(symbol);
                symbol = 'B';
                monitor.notifyAll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void printB() {
        synchronized (monitor) {
            try {
                while (symbol != 'B') {
                    monitor.wait();
                }
                System.out.println(symbol);
                symbol = 'C';
                monitor.notifyAll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void printC() {
        synchronized (monitor) {
            try {
                while (symbol != 'C') {
                    monitor.wait();
                }
                System.out.println(symbol);
                symbol = 'A';
                monitor.notifyAll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


