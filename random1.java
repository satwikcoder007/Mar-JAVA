class MyThread extends Thread {
    int arr[];
    int n;
    static int count;
    static int flag=0;
    static Object ob = new Object();
    
    int searchPrime() {
        while(true) {
            int count = 0;
            for(int i = 2; i <= Math.sqrt(n); i++) {
                if(n % i == 0) 
                    count++;
            }
            if(count == 0) {
                return n++;
            } else {
                n++;
            }
        }
    }

    MyThread(int arr[]) {
        this.arr = arr;
        n = 2;
        start();
    }

    public void run() {
        synchronized(ob) {
            while(count <= 10) {
                while(flag == 1) {
                    try {
                        ob.wait();
                    } catch (InterruptedException e) {
                        
                    }
                }
                int no = searchPrime();
                arr[0] = no;
                flag = 1;
                ob.notify();
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = new int[2];
        MyThread t1 = new MyThread(arr);
        synchronized(ob) {
            while(count <= 10) {
                while(flag == 0) {
                    try {
                        ob.wait();
                    } catch (InterruptedException e) {
                        
                    }
                }
                System.out.println(arr[0]);
                count++;
                flag = 0;
                ob.notify();
            }
        }
    }
}
