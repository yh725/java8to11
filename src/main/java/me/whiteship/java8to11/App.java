package me.whiteship.java8to11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args) {
//		System.out.println(Thread.currentThread().getName());
		MyThread myThread = new MyThread();
		myThread.start();

		System.out.println("Hello: " + Thread.currentThread().getName());

/*
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Thread Runnable: " + Thread.currentThread().getName());
			}
		});
*/
		Thread thread = new Thread(() -> {
/*
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
*/
			while (true) {
				System.out.println("Thread Runnable: " + Thread.currentThread().getName());
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					System.out.println("exit!");
					return;
				}
			}
//			System.out.println("Thread Runnable: " + Thread.currentThread().getName());
		});
		thread.start();

		System.out.println("Hello2: " + Thread.currentThread().getName());
//		Thread.sleep(3000L);
		thread.interrupt();

		Thread thread2 = new Thread(() -> {
			System.out.println("Thread Runnable2: " + Thread.currentThread().getName());
			try {
				Thread.sleep(3000L);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
		});
		thread2.start();
		try {
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(thread2 + " is finished");

/*
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(() -> {
			System.out.println("Thread ExecutorService: " + Thread.currentThread().getName());
		});
*/

		ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.submit(getRunnable("Thread ExecutorService: "));
		executorService.submit(getRunnable("Thread ExecutorService2: "));
		executorService.submit(getRunnable("Thread ExecutorService3: "));
		executorService.submit(getRunnable("Thread ExecutorService4: "));
		executorService.submit(getRunnable("Thread ExecutorService5: "));

		executorService.shutdown();

		ScheduledExecutorService executorService1 = Executors.newSingleThreadScheduledExecutor();
//		executorService1.schedule(getRunnable("Hello"), 3, TimeUnit.SECONDS);
		executorService1.scheduleAtFixedRate(getRunnable("Hello"), 1, 2, TimeUnit.SECONDS);
	}

	private static Runnable getRunnable(String message) {
		return () -> {
			System.out.println(message + Thread.currentThread().getName());
		};
	}

	static class MyThread extends Thread {
		@Override
		public void run() {
			System.out.println("Thread: " + Thread.currentThread().getName());
		}
	}
}
