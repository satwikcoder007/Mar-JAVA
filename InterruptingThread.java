
class MyThread extends Thread{  
    public void run(){
       try {
       while(true){
        System.out.println(new java.util.Date());
        Thread.sleep(1000);   //This thread will print f=date then go to sleep for 1 sec and again print date
       }

       } catch (InterruptedException e) {
            System.out.println("This thread is intrrupted");  //after 5sec main thread will agin awake and will interrup this thread thus 
                                                                // Interrupted flag is set so will throw an error which is caught here
       }
    }
}
public class InterruptingThread {
    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread();
        t1.start();
        Thread.sleep(5000);
        t1.interrupt();
    }

}
