package me.whiteship.java8to11;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

@Chicken("1")
@Chicken("2")
public class App {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
//		List<@Chicken("") String> names = Arrays.asList("Lee");
		Chicken[] chickens = App.class.getAnnotationsByType(Chicken.class);
		Arrays.stream(chickens).forEach(System.out::println);

		ChickenContainer chickenContainer = App.class.getAnnotation(ChickenContainer.class);
		Arrays.stream(chickenContainer.value()).forEach(c -> System.out.println(c.value()));

		int size = 1500;
		int[] numbers = new int[size];
		Random random = new Random();
		IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());

		long start = System.nanoTime();
		Arrays.sort(numbers);
		System.out.println("serial sorting took " + (System.nanoTime() - start));

		IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());

		start = System.nanoTime();
		Arrays.parallelSort(numbers);
		System.out.println("parallel sorting took " + (System.nanoTime() - start));
	}

	static class FeelsLikeChicken<@Chicken("") T> {

		public static <@Chicken("") C> void print(@Chicken("") C c) {
			System.out.println(c);
		}
	}
}
