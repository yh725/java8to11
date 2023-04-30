package me.whiteship.java8to11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {

	public static void main(String[] args) {
		Function<Integer, String> intToString = (i) -> "number";

		UnaryOperator<String> hi = (s) -> "hi " + s;
		UnaryOperator<String> hi2 = Greeting::hi;

		Greeting greeting = new Greeting();
		UnaryOperator<String> hello = greeting::hello;
		System.out.println(hello.apply("Lee"));

		Supplier<Greeting> newGreeting = Greeting::new;
		Greeting greeting2 = newGreeting.get();

		Function<String, Greeting> leeGreeting
				= Greeting::new;

		Greeting lee = leeGreeting.apply("lee");
		System.out.println(lee.getName());

		Supplier<Greeting> newGreeting2 = Greeting::new;

		String[] names = {"lee", "whiteship", "toby"};
		Arrays.sort(names, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return 0;
			}
		});

		Arrays.sort(names, (o1, o2) -> 0);
		Arrays.sort(names, String::compareToIgnoreCase);
	}
}
