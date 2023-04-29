package me.whiteship.java8to11;

import java.util.function.*;

public class Foo {

	public static void main(String[] args) {
		// 익명 내부 클래스 anonymous inner class
		RunSomething runSomething = new RunSomething() {
			@Override
			public void doIt() {
				System.out.println("Hello");
			}
		};

		RunSomething runSomething2 = () -> System.out.println("Hello");

		RunSomething runSomething3 = () -> {
			System.out.println("Hello");
			System.out.println("Lambda");
		};

		runSomething.doIt();

		RunSomething2 runSomething4 = (number) -> number +10;

		System.out.println(runSomething4.doIt(1));
		System.out.println(runSomething4.doIt(1));

		System.out.println(runSomething4.doIt(2));
		System.out.println(runSomething4.doIt(2));

		Plus10 plus10 = new Plus10();
		System.out.println(plus10.apply(1));

//		Function<Integer, Integer> plus10_2 = (i) -> i + 10;
		UnaryOperator<Integer> plus10_2 = (i) -> i + 10; //입력과 결과 값이 같은 경우
		System.out.println(plus10_2.apply(1));

		Function<Integer, Integer> multiply2 = (i) -> i * 2;

		System.out.println(plus10.compose(multiply2).apply(2)); //multiply2 실행 후 plus10 실행
		System.out.println(plus10.andThen(multiply2).apply(2)); //plus10 후 multiply2 실행

		Consumer<Integer> printT = (i) -> System.out.println(i);
		printT.accept(10);

		Supplier<Integer> get10 = () -> 10;
		System.out.println(get10.get());

		//조합 가능
		Predicate<String> startsWithLee = (s) -> s.startsWith("Lee");
		Predicate<Integer> isEven = (i) -> i % 2 == 0;

	}
}
