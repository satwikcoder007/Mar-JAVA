
class Mythread extends Thread{  //creating thread by extending Thread class
    public void run(){
        System.out.println("Inside my thread");
    }
}
class YourThread implements Runnable{ //creatind thread by implementing runnable Interface
                                        //This method is better
    public void run(){
        System.out.println("Inside your thread");
    }
}
public class CreatingThread {
    public static void main(String[] args) {
        System.out.println("Inside main thread");
        Mythread t1 = new Mythread();
        Thread t2 = new Thread(new YourThread());
        t1.start();
        t2.start();
    }
}
