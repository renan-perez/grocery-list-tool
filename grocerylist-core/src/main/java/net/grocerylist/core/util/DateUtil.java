package net.grocerylist.core.util;

import java.time.LocalDate;
import java.time.Period;

public class DateUtil {

	public static Integer periodInYears(LocalDate inicialDate, LocalDate finalDate) {
		Period period = Period.between(inicialDate, finalDate);
		return (period.getMonths()+1) == 12 ? period.getYears()+1 : period.getYears();
	}
	
	public static Integer periodInMonths(LocalDate inicialDate, LocalDate finalDate) {
		Period period = Period.between(inicialDate, finalDate);
		return (period.getMonths()+1) == 12 ? 0 : period.getMonths();
	}
	
}
