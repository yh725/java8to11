package me.whiteship.java8to11;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

	public static void main(String[] args) {
		List<OnlineClass> springClasses = new ArrayList<>();
		springClasses.add(new OnlineClass(1, "spring boot", true));
		springClasses.add(new OnlineClass(2, "spring data jpa", true));
		springClasses.add(new OnlineClass(3, "spring mvc", false));
		springClasses.add(new OnlineClass(4, "spring core", false));
		springClasses.add(new OnlineClass(5, "rest api development", false));

		OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
//		Duration studyDuration = spring_boot.getProgress().getStudyDuration();
//		System.out.println("studyDuration = " + studyDuration);

//		Progress progress = spring_boot.getProgress();
//		if (progress != null) {
//			System.out.println("progress.getStudyDuration() = " + progress.getStudyDuration());
//		}

		Optional<Progress> progress = spring_boot.getProgress();
		progress.ifPresent(p -> System.out.println(p.getStudyDuration()));

		Optional.of(10);
		OptionalInt.of(10);

		List<OnlineClass> springClasses2 = new ArrayList<>();
		springClasses2.add(new OnlineClass(1, "spring boot", true));
		springClasses2.add(new OnlineClass(2, "spring data jpa", true));

		Optional<OnlineClass> optional = springClasses2.stream()
				.filter(oc -> oc.getTitle().startsWith("spring"))
				.findFirst();

		boolean present = optional.isPresent();
		System.out.println("present = " + present);

		if (optional.isPresent()) {
			OnlineClass onlineClass = optional.get();
			System.out.println("onlineClass = " + onlineClass.getTitle());
		}

		optional.ifPresent(oc -> System.out.println(oc.getTitle()));
		OnlineClass onlineClass1 = optional.orElse(createNewClass());
		OnlineClass onlineClass2 = optional.orElseGet(() -> createNewClass());
		OnlineClass onlineClass3 = optional.orElseGet(App::createNewClass);
		OnlineClass onlineClass4 = optional.orElseThrow(() -> {
			return new IllegalStateException();
		});
		OnlineClass onlineClass5 = optional.orElseThrow(IllegalStateException::new);
		Optional<OnlineClass> onlineClass = optional
				.filter(oc -> !oc.isClosed());

		System.out.println("onlineClass.isEmpty() = " + onlineClass.isEmpty());

		Optional<Integer> integer = optional.map(OnlineClass::getId);
		System.out.println("integer.isPresent() = " + integer.isPresent());

		Optional<Optional<Progress>> progress1 = optional.map(OnlineClass::getProgress);
		Optional<Progress> progress2 = progress1.orElse(Optional.empty());

		Optional<Progress> progress3 = optional.flatMap(OnlineClass::getProgress);
	}

	private static OnlineClass createNewClass() {
		System.out.println("creating new online class");
		return new OnlineClass(10, "New class", false);
	}
}
