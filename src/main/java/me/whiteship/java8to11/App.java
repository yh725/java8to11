package me.whiteship.java8to11;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {

	public static void main(String[] args) {
		Foo2 foo = new DefaultFoo("Lee");
		foo.printName();
		foo.printNameUpperCase();

		Foo2.printAnything();

		List<String> name = new ArrayList<>();
		name.add("lee");
		name.add("whiteship");
		name.add("foo");

		System.out.println("=====");

		name.forEach(System.out::println);

		for (String n : name) {
			System.out.println(n);
		}

		System.out.println("=====");

		Spliterator<String> spliterator = name.spliterator();
		Spliterator<String> spliterator1 = spliterator.trySplit();
		while (spliterator.tryAdvance(System.out::println));
		System.out.println("=====");
		while (spliterator1.tryAdvance(System.out::println));

		long l = name.stream().map(String::toUpperCase)
				.filter(s -> s.startsWith("L"))
				.count();

		System.out.println(l);

//		name.removeIf(s -> s.startsWith("l"));

		name.sort(String::compareToIgnoreCase);
		name.forEach(System.out::println);

		Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
		name.sort(compareToIgnoreCase.reversed());

		name.forEach(System.out::println);
	}
}
