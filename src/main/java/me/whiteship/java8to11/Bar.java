package me.whiteship.java8to11;

public interface Bar {

//	void printNameUpperCase();

	default void printNameUpperCase() {
		System.out.println("BAR");
	}
}
