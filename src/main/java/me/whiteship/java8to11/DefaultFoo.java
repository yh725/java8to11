package me.whiteship.java8to11;

public class DefaultFoo implements Foo2, Bar {

	String name;

	public DefaultFoo(String name) {
		this.name = name;
	}

	//충돌할 경우 직접 구현
	@Override
	public void printNameUpperCase() {
		System.out.println(this.name.toUpperCase());
	}

	@Override
	public void printName() {
//		System.out.println("DefaultFoo");
		System.out.println(this.name);
	}

	@Override
	public String getName() {
		return this.name;
	}
}
