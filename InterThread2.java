class MyThread extends Thread{  //creating thread by extending Thread class
    static int count = 1;
    static Object ob = new Object();

    MyThread(){
        start();
    }

    public void run(){
        synchronized(ob){
            while(count<10){
                while(count%2==0){
                    try {
                        ob.wait();
                    } catch (Exception e) {
                        
                    }
                }
                System.out.println(count +"printed from odd thread");
                count++;
                ob.notify();
            }
        }
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        synchronized(ob){
            while(count<10){
                while(count%2!=0){
                   try {
                    ob.wait();
                   } catch (Exception e) {
                    
                   }
                }
                System.out.println(count +"printed from even thread");
                count++;
                ob.notify();
                
            }
        }
    }
}