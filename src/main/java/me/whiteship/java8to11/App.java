package me.whiteship.java8to11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class App {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		Callable<String> hello = () -> {
			Thread.sleep(2000L);
			return "Hello";
		};

		Callable<String> java = () -> {
			Thread.sleep(3000L);
			return "Java";
		};

		Callable<String> lee = () -> {
			Thread.sleep(1000L);
			return "Lee";
		};

		List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, lee));
		for (Future<String> future : futures) {
			System.out.println("future.get() = " + future.get());
		}

		ExecutorService executorService1 = Executors.newFixedThreadPool(4);
		String s = executorService1.invokeAny(Arrays.asList(hello, java, lee));
		System.out.println(s);

/*
		Future<String> helloFuture = executorService.submit(hello);
		System.out.println(helloFuture.isDone());
		System.out.println("Started!");

		helloFuture.cancel(false);
		helloFuture.get(); // 블로킹
		helloFuture.cancel(false);

		System.out.println(helloFuture.isDone());
		System.out.println("End!!");
*/
		executorService.shutdown();
		executorService1.shutdown();

		ExecutorService executorService2 = Executors.newFixedThreadPool(4);
		Future<String> future = executorService2.submit(() -> "Hello");

		future.get();

//		executorService2.shutdown();

		CompletableFuture<String> future1 = new CompletableFuture<>();
		future1.complete("Lee1");

		System.out.println(future1.get());

		CompletableFuture<String> future2 = CompletableFuture.completedFuture("Lee2");
		System.out.println(future2.get());

		CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
			System.out.println("Hello future3: " + Thread.currentThread().getName());
		});
		future3.get();

		CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
			System.out.println("Hello future4: " + Thread.currentThread().getName());
			return "Hello";
		}).thenApply((str) -> {
			System.out.println(Thread.currentThread().getName());
			return str.toUpperCase();
		});

		System.out.println(future4.get());

		CompletableFuture<Void> future5 = CompletableFuture.supplyAsync(() -> {
			System.out.println("Hello future5: " + Thread.currentThread().getName());
			return "Hello";
		}).thenAccept((str) -> {
			System.out.println(Thread.currentThread().getName());
		});

		System.out.println(future5.get());

		CompletableFuture<Void> future6 = CompletableFuture.supplyAsync(() -> {
			System.out.println("Hello future6: " + Thread.currentThread().getName());
			return "Hello";
		}, executorService2).thenRunAsync(() -> {
			System.out.println(Thread.currentThread().getName());
		}, executorService2);

		System.out.println(future6.get());

		executorService2.shutdown();

		CompletableFuture<String> hello2 = CompletableFuture.supplyAsync(() -> {
			System.out.println("Hello2: " + Thread.currentThread().getName());
			return "Hello2";
		});

		CompletableFuture<String> hello3 = CompletableFuture.supplyAsync(() -> {
			System.out.println("Hello3: " + Thread.currentThread().getName());
			return "Hello3";
		});

//		hello2.get();
//		world.get();
		CompletableFuture<String> future7 = hello2.thenCompose(App::getWorld);

		System.out.println(future7.get());

		CompletableFuture<String> future8 = hello2.thenCombine(hello3, (h2, h3) -> h2 + " " + h3);
		System.out.println(future8.get());

		CompletableFuture<Void> future9 = CompletableFuture.allOf(hello2, hello3)
				.thenAccept(System.out::println);

		System.out.println(future9.get());

		List<CompletableFuture<String>> futures1 = Arrays.asList(hello2, hello3);
		CompletableFuture[] futuresArray = futures1.toArray(new CompletableFuture[futures1.size()]);

		CompletableFuture<List<String>> results = CompletableFuture.allOf(futuresArray)
				.thenApply(v -> futures1.stream()
						.map(CompletableFuture::join)
						.collect(Collectors.toList()));

		results.get().forEach(System.out::println);

		CompletableFuture<Void> future10 = CompletableFuture.anyOf(hello2, hello3)
				.thenAccept(System.out::println);

		future10.get();

		boolean throwError = true;

		CompletableFuture<String> hello4 = CompletableFuture.supplyAsync(() -> {
			if (throwError) {
				throw new IllegalArgumentException();
			}

			System.out.println("Hello " + Thread.currentThread().getName());
			return "Hello";
		}).exceptionally(ex -> {
			return "ERROR!";
		});

		System.out.println(hello4.get());

		CompletableFuture<String> hello5 = CompletableFuture.supplyAsync(() -> {
			if (throwError) {
				throw new IllegalArgumentException();
			}

			System.out.println("Hello " + Thread.currentThread().getName());
			return "Hello";
		}).handle((result, ex) -> {
			if (ex != null) {
				System.out.println(ex);
				return "ERROR!!";
			}
			return result;
		});

		System.out.println(hello4.get());
	}

	private static CompletableFuture<String> getWorld(String message) {
		return CompletableFuture.supplyAsync(() -> {
			System.out.println("World: " + Thread.currentThread().getName());
			return message + " World";
		});
	}
}
