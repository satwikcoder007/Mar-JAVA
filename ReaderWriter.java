import java.util.concurrent.Semaphore;

public class ReadersWriters {
    private int readersCount = 0; // Count of readers
    private final Semaphore readersSemaphore = new Semaphore(1); // Semaphore for readers count
    private final Semaphore resourceSemaphore = new Semaphore(1); // Semaphore for resource access

    // Reader class
    class Reader implements Runnable {
        public void run() {
            try {
                // Ensure exclusive access to readersCount
                readersSemaphore.acquire();
                readersCount++;
                if (readersCount == 1) {
                    // First reader locks the resource
                    resourceSemaphore.acquire();
                }
                readersSemaphore.release();

                // Perform reading (critical section)
                System.out.println(Thread.currentThread().getName() + " is READING");

                // Simulate reading time
                Thread.sleep(1000);

                // Reader done reading
                readersSemaphore.acquire();
                readersCount--;
                if (readersCount == 0) {
                    // Last reader unlocks the resource
                    resourceSemaphore.release();
                }
                readersSemaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Writer class
    class Writer implements Runnable {
        public void run() {
            try {
                // Ensure exclusive access to the resource
                resourceSemaphore.acquire();

                // Perform writing (critical section)
                System.out.println(Thread.currentThread().getName() + " is WRITING");

                // Simulate writing time
                Thread.sleep(1000);

                // Writer done writing
                resourceSemaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ReadersWriters problem = new ReadersWriters();

        // Create reader and writer threads
        Thread[] readers = new Thread[5];
        Thread[] writers = new Thread[2];

        for (int i = 0; i < readers.length; i++) {
            readers[i] = new Thread(problem.new Reader(), "Reader " + (i + 1));
        }

        for (int i = 0; i < writers.length; i++) {
            writers[i] = new Thread(problem.new Writer(), "Writer " + (i + 1));
        }

        // Start reader and writer threads
        for (int i = 0; i < readers.length; i++) {
            readers[i].start();
        }

        for (int i = 0; i < writers.length; i++) {
            writers[i].start();
        }
    }
}
