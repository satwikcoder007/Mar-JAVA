class MyThread extends Thread{  //creating thread by extending Thread class
    int arr[];
    MyThread(int arr[]){
        this.arr = arr;
        start();
    }
    public void run(){
        try {
            
            Thread.sleep(3000);
            java.util.Arrays.sort(arr);
            System.out.println("Array is sorted");
        } catch (InterruptedException e) {
            // TODO: handle exception
        }
    }
}
public class ThreadJoin  {
    public static void main(String[] args) throws Exception
    {
        int arr[]={3,2,4,5,1};
        MyThread t1 = new MyThread(arr);
        t1.join();                              //This line basically make the main traid wait for t1 thread to finish its process
                                                //if we donot give this then the output will be 32451
        for(int i=0;i<5;i++){
            System.out.print(arr[i]);
        }
    }
}
