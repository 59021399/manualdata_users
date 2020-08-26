import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;




public class DAOHelper {
	protected ServiceLocator locator;
	private static final TimeZone timeZone = TimeZone.getTimeZone("GMT+7");
	//protected static final Locale localeDefault = Locale.US;
	protected static final Locale localeTH = new Locale("th", "TH");
	//protected static final Gson GSON = new Gson();
		
	protected class DATE_FORMAT {
		public static final String DEFAULT = "dd/MM/yyyy";
		public static final String DBFORMAT = "yyyy-MM-dd";
		public static final String DATETIMEZONE = "yyyy-MM-dd'T'HH:mm:ssXXX";

	}

	protected class TIME_FORMAT {
		public static final String DEFAULT = "HH:mm";
		public static final String HMS = "HH:mm:ss";
		public static final String AMPM = "HH:mm:ss aa";
	}

	protected class DATE_DEFAUL {
		public static final String DEFAULT_END_DATE = "01/01/2099";
		public static final int PROBATION_DATE = 119;

	}

	public class EMAL_FORMAT {
		public static final String DEFAULT_EMAIL_FORMAT = "@smebank.co.th";

	}
	
	public class FILL_STRING_TYPE {
		public static final int POSTFIX = 1;
		public static final int PREFIX = 0;
	}

	public class TABLE_OBJECT_A2M {
		public static final String USER_OBJECT_M = "TEST_USER_OBJECT_M";
	}


	public Object whenNull(Object obj, Object objElse) {
		if (isNull(obj)) {
			return objElse;
		}

		return obj;
	}

	public boolean isNull(Object obj) {
		if (obj != null) {
			if (!"".equals(obj.toString())) {
				return false;
			}
		}

		return true;
	}

	public Calendar getCalendar() {
		return Calendar.getInstance();
	}

	public Date getCurrentDate() {
		return new Date(getCalendar().getTimeInMillis());
	}

	public Timestamp getCurrentTimeStamp() {
		return new Timestamp(getCalendar().getTimeInMillis());
	}

	public Date getEndOfYear(Integer asYear) {
		if (asYear == null || asYear < 1) {
			throw new IllegalArgumentException("As Year can't be null or less then zero!");
		}
		Calendar calendar = getCalendar();
		calendar.set(Calendar.YEAR, asYear);
		calendar.set(Calendar.MONTH, 11); // 11 = december
		calendar.set(Calendar.DAY_OF_MONTH, 31); // new years eve
		return new Date(calendar.getTimeInMillis());
	}

	public Date getStartOfYear(Integer asYear) {
		if (asYear == null || asYear < 1) {
			throw new IllegalArgumentException("As Year can't be null or less then zero!");
		}
		Calendar calendar = getCalendar();
		calendar.clear();
		calendar.set(Calendar.YEAR, asYear);
		return new Date(calendar.getTimeInMillis());
	}

	public String convertDateToString(Date date, String dateFormat) {
		if (isNull(date) || isNull(dateFormat))
			return "";

		DateFormat df = new SimpleDateFormat(dateFormat);
		//df.setTimeZone(timeZone);
		return df.format(date);
	}
	public String convertDateToString(Date date, String dateFormat,Locale locale) {
		if (isNull(date) || isNull(dateFormat))
			return "";

		DateFormat df = new SimpleDateFormat(dateFormat,locale);
		//df.setTimeZone(timeZone);
		return df.format(date);
	}
	
	public String convertTimestampToString(Timestamp timeStamp, String datetimeFormat) {
		if (isNull(timeStamp) || isNull(datetimeFormat))
			return "";

		DateFormat df = new SimpleDateFormat(datetimeFormat);
		df.setTimeZone(timeZone);
		return df.format(timeStamp);
	}

	public Time convertStringToTime(String timeString, String timeFormat) throws Exception {
		if (isNull(timeString) || isNull(timeFormat))
			return null;

		Time result = null;
		DateFormat df = new SimpleDateFormat(timeFormat);
		df.setTimeZone(timeZone);
		try {
			result = new Time(df.parse(timeString).getTime());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public Time getTimeFromTimeStamp(Timestamp timeStamp) throws Exception {
		Time result = null;
		try {
			result = new Time(timeStamp.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public Date getDateFromTimeStamp(Timestamp timeStamp) throws Exception {
		Date result = null;
		try {
			result = new Date(timeStamp.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public double timediffMinute(Time timeFrom, Time timeTo) {
		long result = timeTo.getTime() - timeFrom.getTime();
		return Double.parseDouble(String.valueOf(result / 1000.0 / 60.0));
	}
 

 
	public int diffMonth(Date startDate) {
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(startDate);
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(getCurrentDate());

		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
		return diffMonth;
	}

	public int diffMonth(Date startDate, Date endDate) {
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(startDate);
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(endDate);

		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
		return diffMonth;
	}

	public int diffDay(Date startDate, Date endDate) {
		return (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
	}

	public Date dateAddYear(Date date, double value) {
		Calendar result = Calendar.getInstance();
		int year = (int) Math.floor(value);
		int month = (int) Math.floor((value - Math.floor(value)) * 12.0);

		result.setTime(date);
		result.add(Calendar.YEAR, year);
		result.add(Calendar.MONTH, month);

		return new Date(result.getTime().getTime());
	}

	public float getDigitFloat(float value) {

		String[] splitter = Float.toString(value).split("\\.");

		return Float.parseFloat("0." + splitter[1]);
	}

	public float getValueFloat(float value, float number) {

		String[] splitter = Float.toString(value).split("\\.");

		return (Float.parseFloat(splitter[0]) + number);
	}



	/**
	 * Encrypt use AES algorithm 128bits.
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	
	/**
	 * Decrypt use AES algorithm 128bits.
	 * 
	 * @param encrypted
	 * @return
	 * @throws Exception
	 */
	

	/**
	 * SQL Date to Util date.
	 * 
	 * @param sqlDate
	 * @return utilDate
	 */
	public java.util.Date parseDate(java.sql.Date sqlDate) {
		if (sqlDate == null) {
			return null;
		}
		return new java.util.Date(sqlDate.getTime());
	}

	/**
	 * Util Date to SQL date.
	 * 
	 * @param utilDate
	 * @return sqlDate
	 */
	public java.sql.Date parseDate(java.util.Date utilDate) {
		if (utilDate == null) {
			return null;
		}
		return new java.sql.Date(utilDate.getTime());

	}
	
	public String fillString(String value, int fillType, String character, int length) {
		StringBuffer result = new StringBuffer(value);
		
		while (result.length() < length) {
			if (FILL_STRING_TYPE.POSTFIX == fillType) {
				result.append(character);
			} else {
				result.insert(0, character);
			}
		}
		
		if (result.length() > length) {
			if (FILL_STRING_TYPE.POSTFIX == fillType) {
				result = new StringBuffer(result.substring(0, length));
			} else {
				result = new StringBuffer(result.substring(result.length() - length, result.length()));
			}
		}
		
		return result.toString();
	}

	
	//Yo : date thai full and short
	
	public String getFullThaiDate(String dateString) throws Exception {
		String dateConvert = "";
		String[] monthList = new String[]{"มกราคม","กุมภาพันธ์","มีนาคม","เมษายน","พฤษภาคม","มิถุนายน", "กรกฎาคม","สิงหาคม","กันยายน","ตุลาคม","พฤศจิกายน","ธันวาคม"};
		if(dateString!=null){
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			java.util.Date getDate = (java.util.Date)formatter.parse(dateString);
			Calendar cal = Calendar.getInstance();
		    cal.setTime(getDate);
		    int curDay = cal.get(Calendar.DAY_OF_MONTH);
		    int curMonth = cal.get(Calendar.MONTH);
		    int curYear = cal.get(Calendar.YEAR);
		    dateConvert = "วันที่ "+curDay+" เดือน "+monthList[curMonth]+" พ.ศ."+(curYear+543);
		}
	    return dateConvert;
	}
	public String getShortThaiDate(String dateString) throws Exception {
		String dateConvert = "";
		String[] monthList = new String[]{"ม.ค.","ก.พ.","มี.ค.","เม.ย.","พ.ค.","มิ.ย.","ก.ค.","ส.ค.","ก.ย.","ต.ค.", "พ.ย.","ธ.ค."};
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		if(dateString!=null){
			java.util.Date getDate = (java.util.Date)formatter.parse(dateString);
			Calendar cal = Calendar.getInstance();
		    cal.setTime(getDate);
		    int curDay = cal.get(Calendar.DAY_OF_MONTH);
		    int curMonth = cal.get(Calendar.MONTH);
		    int curYear = cal.get(Calendar.YEAR);
		    dateConvert = curDay+" "+monthList[curMonth]+" "+(curYear+543);
		}
	    return dateConvert;
	}
	public static java.util.Date convertTimestampToDateUtil(Timestamp timeStamp) throws Exception {
		java.util.Date result = null;
		try {
			result = new java.util.Date(timeStamp.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return result;
	}
	public static Timestamp convertDateUtilToTimestamp(java.util.Date date) throws Exception {
		Timestamp result = null;
		try {
			result = new Timestamp(date.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return result;
	}
	
	public static Period  calculateAge(Long longTime){
		Period p =null;
		try {
			LocalDate now =  LocalDate.now();
		
			java.util.Date birthDate = new java.util.Date(longTime);
			Instant instant = birthDate.toInstant();
			ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
			LocalDate birthLocalDate = zdt.toLocalDate();
			
			 
			p = Period.between(birthLocalDate, now);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return p;
	}
}
