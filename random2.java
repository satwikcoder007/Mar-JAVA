class MyThread extends Thread{
    static int count=1;
    static Object ob = new Object();
    MyThread(){
        start();
    }
    public void run(){
        synchronized(ob){    //we have taken a lock on ob
            
            while(count<10){
                while(count%2==0){
                    try {
                        System.out.println("odd thread blocked");
                        ob.wait(); // if the number id even then we put the thread in wait that is it release its hold
                                    //and wait here
                    } catch (Exception e) {}
                }
                System.out.println(count+"printed from odd thread");
                count++;
                ob.notify();//notifying even thread 
                            //once even thread get lock on ob it will resume its work fromthe point it have left the work
            }
        }
    }
    public static void main(String[] args) {
        MyThread t = new MyThread();
        synchronized(ob){
            
            while(count<10){
                
                while(count%2!=0){
                    try {
                        System.out.println("even thread blocked");
                        ob.wait();
                       //if even thread get blocked it will release the lock on ob and wait here in hope someone will notify him to teck lock
                       //once he get nptified it will agin regain the lock and continue to run
                    } catch (Exception e) {};
                }
                System.out.println(count+"printed from even thread");
                count++;
                ob.notify();
                //notifying the odd thread to continue once this thread release the lock
                //once notify the odd thread does not imideatly take lock on ob but becme elligible to take lock and wait unitl even get to wait state
            }
        }
    }
}
