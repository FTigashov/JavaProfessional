package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskB {
    private static final int size = 10000000;
    private static final int h = size / 2;
    private static ExecutorService service = Executors.newFixedThreadPool(2);
    public static void main(String[] args) {

        float[] arr = new float[size];
        inputOneInArray(arr);

        long a = System.currentTimeMillis();

        firstWay(arr);
        System.out.println("Время выполнения c одним потоком: " + (System.currentTimeMillis() - a) + " c.");

        inputOneInArray(arr);

        a = System.currentTimeMillis();
        secondWayWithThreads(arr);
        System.out.println("Время выполнения с несколькими потоками: " + (System.currentTimeMillis() - a) + " c.");

    }


    public static void inputOneInArray(float[] data) {
        for (float d : data) {
            d = 1;
        }
    }

    public static void firstWay(float[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = (float) (data[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    public static void secondWayWithoutThreads(float[] data, int h) {
        for (int i = 0; i < data.length; i++) {
            data[i] = (float) (data[i] * Math.sin(0.2f + (i + h) / 5) * Math.cos(0.2f + (i + h) / 5) * Math.cos(0.4f + (i + h) / 2));
        }
    }

    public static void secondWayWithThreads(float[] data) {
        float[] data1 = new float[h];
        float[] data2 = new float[h];

        System.arraycopy(data, 0, data1, 0, h);
        System.arraycopy(data, 0, data2, 0, h);

       service.execute(() -> TaskB.secondWayWithoutThreads(data1, 0));
       service.execute(() -> TaskB.secondWayWithoutThreads(data2, h));

        System.arraycopy(data1, 0, data, 0, h);
        System.arraycopy(data2, 0, data, 0, h);

        service.shutdown();
    }
}
