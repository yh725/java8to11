package me.whiteship.java8to11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

	public static void main(String[] args) {
		List<String> names = new ArrayList<>();
		names.add("lee");
		names.add("whiteship");
		names.add("toby");
		names.add("foo");

//		Stream<String> stringStream = names.stream().map(String::toUpperCase);
		List<String> collect = names.stream().map((s) -> {
			System.out.println(s);
			return s.toUpperCase();
		}).collect(Collectors.toList());
		collect.forEach(System.out::println);

		System.out.println("=====");

		names.forEach(System.out::println);

		for (String name : names) {
			if (name.startsWith("l")) {
				System.out.println(name.toUpperCase());
			}
		}

		//병렬처리
		List<String> collect1 = names.parallelStream().map((s) -> {
			System.out.println(s + " " + Thread.currentThread().getName());
			return s.toUpperCase();
		}).collect(Collectors.toList());
	}
}
