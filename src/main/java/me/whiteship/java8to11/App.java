package me.whiteship.java8to11;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class App {

	public static void main(String[] args) throws InterruptedException {
		Date date = new Date();
//		Calendar calendar = new GregorianCalendar();
//		SimpleDateFormat dateFormat = new SimpleDateFormat();

		long time = date.getTime();
		System.out.println(date);
		System.out.println(time);

		Thread.sleep(1000 * 3);
		Date after3Seconds = new Date();
		System.out.println(after3Seconds);
		after3Seconds.setTime(time);
		System.out.println(after3Seconds);

		Calendar leeBirthDay = new GregorianCalendar(1990, Calendar.JULY, 25);
		System.out.println(leeBirthDay.getTime());
		leeBirthDay.add(Calendar.DAY_OF_YEAR, 1);
		System.out.println(leeBirthDay.getTime());

		Instant instant = Instant.now(); // 기준시 UTC, GMT
		System.out.println(instant);
		System.out.println(instant.atZone(ZoneId.of("UTC")));

		ZoneId zone = ZoneId.systemDefault();
		System.out.println(zone);
		ZonedDateTime zonedDateTime = instant.atZone(zone);
		System.out.println(zonedDateTime);

		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);

		LocalDateTime birthDay = LocalDateTime.of(1990, Month.JULY, 25, 15, 30, 0);
		System.out.println(birthDay);

		ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of(("Asia/Seoul")));
		System.out.println(nowInKorea);

		ZonedDateTime zonedDateTime1 = instant.atZone(ZoneId.of("Asia/Seoul"));
		System.out.println(zonedDateTime1);

		LocalDate today = LocalDate.now();
		LocalDate thisYearBirthDay = LocalDate.of(2023, Month.JULY, 25);
		Period period = Period.between(today, thisYearBirthDay);
		System.out.println(period.getDays());

		Period until = today.until(thisYearBirthDay);
		System.out.println(until.get(ChronoUnit.DAYS));

		Instant now1 = Instant.now();
		Instant plus = now1.plus(10, ChronoUnit.SECONDS);
		Duration between = Duration.between(now1, plus);
		System.out.println(between.getSeconds());

		DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		System.out.println(now.format(MMddyyyy));

		LocalDate parse = LocalDate.parse("07/25/1990", MMddyyyy);
		System.out.println(parse);

		Date date1 = new Date();
		Instant instant1 = date1.toInstant();
		Date newDate = Date.from(instant1);

		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		LocalDateTime dateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		ZonedDateTime zonedDateTime2 = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());
		GregorianCalendar from = GregorianCalendar.from(zonedDateTime2);

		ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
		TimeZone timeZone = TimeZone.getTimeZone(zoneId);

		LocalDateTime plus1 = now.plus(10, ChronoUnit.DAYS);
	}
}
