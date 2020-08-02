package es.ismaelrp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * Comparación de fechas en JAVA
 */
public class DatesComparison {
	public static void main(String[] args) {
		try {
			comparingDatesWithDateCompareTo();
			comparingDatesWithDateObject();
			comparingDatesWithCalendar();
			comparingDatesWithLocalDate();
			comparingDatesWithLocalDateTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Un método clásico para comparar dos java.util.Date en Java. Java 7+
	 *
	 * Devuelve 0 si ambas fechas son iguales.
	 * Devuelve mayor que 0, si fecha es posterior al argumento de fecha.
	 * Devuelve menor que 0, si la fecha es anterior al argumento de la fecha.
	 */
	private static void comparingDatesWithDateCompareTo() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date1 = sdf.parse("20-07-2020");
		Date date2 = sdf.parse("21-07-2020");

		System.out.println("Fecha 1 : " + sdf.format(date1));
		System.out.println("Fecha 2 : " + sdf.format(date2));

		if (date1.compareTo(date2) > 0) {
			System.out.println("Fecha 1 es posterior a Fecha 2");
		} else if (date1.compareTo(date2) < 0) {
			System.out.println("Fecha 1 es anterior a Fecha 2");
		} else if (date1.compareTo(date2) == 0) {
			System.out.println("Fecha 1 es igual a Fecha 2");
		}
	}

	/**
	 * Un método más fácil de usar para comparar dos java.util.Date
	 */
	private static void comparingDatesWithDateObject() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date1 = sdf.parse("20-07-2020");
		Date date2 = sdf.parse("21-07-2020");

		System.out.println("Fecha 1 : " + sdf.format(date1));
		System.out.println("Fecha 2 : " + sdf.format(date2));

		if (date1.after(date2)) {
			System.out.println("Fecha 1 es posterior a Fecha 2");
		}

		if (date1.before(date2)) {
			System.out.println("Fecha 1 es anterior a Fecha 2");
		}

		if (date1.equals(date2)) {
			System.out.println("Fecha 1 es igual a Fecha 2");
		}
	}

	/**
	 * Método de ejemplo de comparación de fechas con dos objetos java.util.Calendar
	 *
	 * @throws ParseException en caso de error en el parseo.
	 */
	private static void comparingDatesWithCalendar() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date1 = sdf.parse("20-07-2020");
		Date date2 = sdf.parse("21-07-2020");

		System.out.println("Fecha 1 : " + sdf.format(date1));
		System.out.println("Fecha 2 : " + sdf.format(date2));

		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);

		if (cal1.after(cal2)) {
			System.out.println("Fecha 1 es posterior a Fecha 2");
		}

		if (cal1.before(cal2)) {
			System.out.println("Fecha 1 es anterior a Fecha 2");
		}

		if (cal1.equals(cal2)) {
			System.out.println("Fecha 1 es igual a Fecha 2");
		}
	}

	/**
	 * En Java 8, se pueden usar los nuevos isBefore(), isAfter(), isEqual() y compareTo() para comparar LocalDate, LocalTime y LocalDateTime.
	 *
	 * En este método comparamos dos objetos java.time.LocalDate
	 */
	private static void comparingDatesWithLocalDate() {
		// Fecha actual
		LocalDate today = LocalDate.now();
		// Generamos una segunda fecha diferente (sumamos o restamos una unidad de fecha, como día, mes, año...)
		LocalDate anotherDay = today.plus(1, ChronoUnit.DAYS);

		// ¿es igual? = false
		System.out.println(today.isEqual(anotherDay));
		// ¿es posterior? = true
		System.out.println(today.isAfter(anotherDay));
		// ¿es anterior? = false
		System.out.println(today.isBefore(anotherDay));

		// Ahora comparamos con compareTo
		int diff = today.compareTo(anotherDay);
		if (diff > 0) {
			// Si diff es mayor que 0, indica que la comparación es mayor
			System.out.println(today + " es mayor que " + anotherDay);
		} else if (diff < 0) {
			// Si diff es menor que 0, indica que la comparación es menor
			System.out.println(today + " es menor que " + anotherDay);
		} else {
			// Si no, indica que la comparación es igual
			System.out.println(today + " es igual que " + anotherDay);
		}
	}

	/**
	 * Java 8+
	 *
	 * LocalDateTime y ZonedDateTime también usan parte del tiempo durante la comparación
	 * Ambas instancias son el mismo día pero el tiempo es diferente en 100 mili segundos
	 * No son iguales durante la comparación
	 *
	 */
	private static void comparingDatesWithLocalDateTime() {
		// Fecha actual
		LocalDateTime instance = LocalDateTime.now();
		// Generamos una segunda fecha diferente (sumamos o restamos una unidad de tiempo, mili-segundos, segundos, minutos...)
		LocalDateTime anotherInstance = instance.plus(100, ChronoUnit.MILLIS);

		// false
		System.out.println(instance.isEqual(anotherInstance));
		// false
		System.out.println(instance.isAfter(anotherInstance));
		// true
		System.out.println(instance.isBefore(anotherInstance));

		// Comparamos solo la parte de fecha sin hora desde el LocalDateTime
		// true
		System.out.println(
			instance.toLocalDate().isEqual(anotherInstance.toLocalDate()));
	}
}
