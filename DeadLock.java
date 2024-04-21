//deadlock is a situation when thread wait for each other indefinitely to release the lock 

// p1(s1)--------->(s2)
// p2(s2)--------->(s1)


class MyThread extends Thread{  //creating thread by extending Thread class
    String s1;
    String s2;
    int id;
    MyThread(String s1,String s2,int id){
        this.s1 = s1;
        this.s2 = s2;
        this.id = id;
        start();
    }

    public void run(){
       synchronized(s1){
            System.out.println("thread "+id+" aquired lock on "+s1); //after taking lock on s1 it goes to sleep and in meantime other thread took lock on s2
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                // TODO: handle exception
            }
            System.out.println("thread "+id+" is wating to  aquire lock on "+s2); //after waking up thread would like to take lock on s2 is already taken by a thread 
                                                                                //taken by a thread which want s1 so this is a deadlock situation
            synchronized(s2){
                System.out.println("thread "+id+" aquired lock on "+s2);
            }
       }
    }
}
public class DeadLock {
    public static void main(String[] args) {
        String s1 = "Object 1";
        String s2 = "Object 2";
        MyThread t1 = new MyThread(s1, s2, 1);
        MyThread t2 = new MyThread(s2, s1, 2);  //Here both thread are taking lock in diffrent order;
        //MyThread t2 = new MyThread(s1, s2, 2)  //This shoud be the right method of taking lock

    }
}
