package me.whiteship.java8to11;

import java.util.function.*;

public class Foo {

	public static void main(String[] args) {
		Supplier<Integer> get10 = () -> 10;
		BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
		BinaryOperator<Integer> sum2 = (a, b) -> a + b;
		BinaryOperator<Integer> sum3 = (Integer a, Integer b) -> a + b;

		Foo foo = new Foo();
		foo.run();
	}

	private void run() {
		final int baseNumber = 10; //사실상 final인 경우 생략 가능

		// 로컬 클래스
		class LocalClass {
			void printBaseNumber() {
				int baseNumber = 11;
				System.out.println(baseNumber); //11
			}
		}

		// 익명 클래스
		Consumer<Integer> integerConsumer = new Consumer<Integer>() {
			@Override
			public void accept(Integer baseNumber) {
				System.out.println(baseNumber);
			}
		};

		// 람다
		IntConsumer printInt = (i) -> {
			System.out.println(i + baseNumber);
		};

		printInt.accept(10);
	}
}
