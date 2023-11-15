import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(coreCount);

        ArrayList<Future<Integer>> list = new ArrayList<>();

        for(int i = 0; i < 100; i++){
            list.add(executor.submit(new MultiThread()));
        }

        int sum = 0;

        for (Future<Integer> num : list)
            sum += num.get();

        executor.shutdown();

        System.out.println(sum);
    }
}

class MultiThread implements Callable<Integer> {
    private int num = 0;

    @Override
    public Integer call() throws Exception {
        try {
            System.out.println("Thread " + Thread.currentThread().getId() + " is running");
            for (int i = 0; i < 1000000; i++) {
                num++;
            }
        } catch (Exception e) {
            System.out.println("Exception is caught");
        }

        return num;
    }
}