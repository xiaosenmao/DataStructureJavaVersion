import java.util.Random;

public class QueuesComparison {

    private static double testQueue(Queue<Integer> q, int opCount) {

        long startTime = System.nanoTime();

        Random random = new Random();

        for (int i = 0; i < opCount; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }

        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int opCount = 100000;

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, opCount);  // O(n^2)
        System.out.println("ArrayQueue, time1: " + time1 + " S");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue, time2: " + time2 + " S");

        LinkedListQueue<Integer> linedListQueue = new LinkedListQueue<>();
        double time3 = testQueue(linedListQueue, opCount);
        System.out.println("linedListQueue, time3: " + time3 + " S");

        /*
            Result on my device:
            ArrayQueue, time1: 3.58870863 S
            LoopQueue, time2: 0.009388672 S
            linedListQueue, time3: 0.007742912 S
         */
    }
}
