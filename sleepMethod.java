public class sleepMethod {
    public static void main(String[] args) {
        try{
            while(true){
                System.out.println(new java.util.Date());
                Thread.sleep(2000); //here the thread is suspended for 2 second and it agai start wprking after that
            }
        }
        catch(InterruptedException e){

        }
    }
}
