
//Thread Explaining Synchronized block and taking lock over a dummy object

import java.util.*;

class Counter {
	private int val1 = 0;
	

	// Synchronized methods to ensure thread safety
	void inc(Object lock) {
		synchronized(lock){
			
			val1++;
		}
	}

	 void dec(Object lock) {
		synchronized(lock){
			// System.out.println("Hello");
			val1--;
		}
	}

	void getVal() {
		System.out.println(val1);;
	}
}

class MyThread extends Thread {
	private Counter c;
	Object lock;
	MyThread(Counter c,Object lock) {
		this.c = c;
		this.lock=lock;
	}
	
	public void run() {

		for (int i = 0; i < 100000000; i++) {
			c.dec(lock);
		}

	}
}

class Main {
	public static void main(String[] args) throws Exception {
		Counter c = new Counter();
		final Object lock1  = new Object();
		final Object lock2 = new Object();
		MyThread T = new MyThread(c,lock1);
		
		// Start the child thread
		T.start();

		for (int i = 0; i < 100000000; i++) {
			c.inc(lock2);
		}

		T.join();

		// Output the final value of the counter
		c.getVal();
	}
}
