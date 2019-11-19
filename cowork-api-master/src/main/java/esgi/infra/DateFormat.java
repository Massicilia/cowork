package esgi.infra;

public class DateFormat {

	public String getFormatTwoChar(int dayMonth){

		String dayMonthString = dayMonth<10?"0"+ dayMonth : dayMonth+"";
		return dayMonthString;
	}
}
