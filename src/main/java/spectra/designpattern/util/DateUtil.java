package spectra.designpattern.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;


/**
 * org.apache.commons.lang.time.DateUtils 클래스를 확장한 날짜/시간 유틸리티 클래스.
 *
 * @author kspark
 */
public class DateUtil extends DateUtils
{
    
    /** The Constant DT_FORMAT_YYYYMMDD. */
    public static final String DT_FORMAT_YYYYMMDD = "yyyyMMdd";
    
    /** The Constant DT_FORMAT_YYYYMMDD. */
    public static final String DT_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /** The Constant DT_FORMAT_YYYYMMDD_DASH. */
    public static final String DT_FORMAT_YYYYMMDD_DASH = "yyyy-MM-dd";

    /** The Constant DT_FORMAT_YYYYMMDD_SLASH. */
    public static final String DT_FORMAT_YYYYMMDD_SLASH = "yyyy/MM/dd";
    
    public static final String DT_FORMAT_YYYYMMDDHHMMSS_SLASH_COLON =  "yyyy-MM-dd HH:mm:ss";

    /**
    * DateFormatUtils.format() 메소드를 사용한다.<br>
    * 날짜를 주어진 포맷에 맞추어 변경한다.
    * Format Pattern                         Result<br>
    * --------------                         -------<br>
    * "yyyy.MM.dd G 'at' hh:mm:ss z"    ->>  1996.07.10 AD at 15:08:56 PDT<br>
    * "EEE, MMM d, ''yy"                ->>  Wed, July 10, '96<br>
    * "h:mm a"                          ->>  12:08 PM<br>
    * "hh 'o''clock' a, zzzz"           ->>  12 o'clock PM, Pacific Daylight Time<br>
    * "K:mm a, z"                       ->>  0:00 PM, PST<br>
    * "yyyyy.MMMMM.dd GGG hh:mm aaa"    ->>  1996.July.10 AD 12:08 PM<br>
    * <br>
    * ex) DateUtil.getStringFromDate(new java.util.Date(), "yyyy-MM-dd");<br>
    * <br>
    *
    * @param format the format
    * @param d java.util.Date 객체
    *
    * @return 주어진 포맷에 대한 문자열
    *
    * @see org.apache.commons.lang.time.DateFormatUtils#format(java.util.Date, java.lang.String)
    */
    public static String getStringFromDate(Date d, String format)
    {
        return DateFormatUtils.format(d, format);
    }

    /**
    * DateFormatUtils.format() 메소드를 사용한다.<br>
    * 날짜를 주어진 포맷에 맞추어 변경한다.
    * Format Pattern                         Result<br>
    * --------------                         -------<br>
    * "yyyy.MM.dd G 'at' hh:mm:ss z"    ->>  1996.07.10 AD at 15:08:56 PDT<br>
    * "EEE, MMM d, ''yy"                ->>  Wed, July 10, '96<br>
    * "h:mm a"                          ->>  12:08 PM<br>
    * "hh 'o''clock' a, zzzz"           ->>  12 o'clock PM, Pacific Daylight Time<br>
    * "K:mm a, z"                       ->>  0:00 PM, PST<br>
    * "yyyyy.MMMMM.dd GGG hh:mm aaa"    ->>  1996.July.10 AD 12:08 PM<br>
    * <br>
    * ex) DateUtil.getStringFromDate(new java.util.Date(), "yyyy-MM-dd");<br>
    * <br>
    *
    * @param format the format
    * @param d java.util.Date 객체
    * @param timeZone the time zone
    *
    * @return 주어진 포맷에 대한 문자열
    *
    * @see org.apache.commons.lang.time.DateFormatUtils#format(java.util.Date, java.lang.String)
    */
    public static String getStringFromDate(Date d, String format, TimeZone timeZone)
    {
        return DateFormatUtils.format(d, format, timeZone);
    }

    /**
    * 주어진 포맷으로 데이터를 문자열을 Parsing 한 후 java.util.Date 객체로 리턴한다.
    *
    * @param dt 주어진 포맷에 대한 문자열
    * @param format 주어진 문자열의 현재 Date Format
    *
    * @return java.util.Date 객체
    *
    * @throws Exception the run util exception
    *
    * @exception spectra.base.util.Exception
    */
    public static Date getDateFromString(String dt, String format) throws Exception
    {
        return getDateFromString(dt, format, TimeZone.getDefault());
    }

    /**
    * 주어진 포맷으로 데이터를 문자열을 Parsing 한 후 java.util.Date 객체로 리턴한다.
    *
    * @param dt 주어진 포맷에 대한 문자열
    * @param format 주어진 문자열의 현재 Date Format
    * @param timeZone the time zone
    *
    * @return java.util.Date 객체
    *
    * @throws Exception the run util exception
    *
    * @exception spectra.base.util.Exception
    */
    public static Date getDateFromString(String dt, String format, TimeZone timeZone) throws Exception
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format); // NOPMD by yshwang on 13. 11. 5 오후 2:47
        formatter.setTimeZone(timeZone);
        Date uDate = null;
        StringBuffer sbError = null;
        try
        {
            uDate = formatter.parse(dt);
        }
        catch(ParseException e)
        {
            sbError = new StringBuffer().append("DateUtil.getDateFromString - Mismatch Date format : ")
                                        .append(dt).append("[").append(format).append("]");
            throw new Exception(sbError.toString(),  e);
        }
        finally
        {
            formatter = null;
            sbError = null;
        }

        return uDate;
    }

    /**
     * yyyyMMdd형식의 날짜스트링을 "yyyy-MM-dd"형식으로 변환하여 리턴.
     *
     * @param dt yyyyMMdd형식의 날짜스트링
     *
     * @return 포맷된 스트링
     *
     * @throws Exception the run util exception
     */
    public static String formatDateString(String dt) throws Exception
    {
        String strDt = dt;
        if(dt.length()>8)
        {
            strDt=dt.substring(0,8);
        }
        return formatDateString(strDt, DT_FORMAT_YYYYMMDD, DT_FORMAT_YYYYMMDD_DASH, TimeZone.getDefault());
    }

    /**
     * yyyyMMddHHmmss형식의 날짜스트링을 "yyyy-MM-dd HH:mm:ss"형식으로 변환하여 리턴.
     *
     * @param dt yyyyMMddHHmmss형식의 날짜스트링
     *
     * @return 포맷된 스트링
     *
     * @throws Exception the run util exception
     */
    public static String formatDateTimeString(String dt) throws Exception
    {
        return formatDateString(dt, DT_FORMAT_YYYYMMDDHHMMSS, "yyyy-MM-dd HH:mm:ss", TimeZone.getDefault());
    }

    /**
     * "yyyy-MM-dd HH:mm:ss"형식의 날짜스트링을 yyyyMMddHHmmss형식으로 변환하여 리턴.
     *
     * @param dt yyyy-MM-dd HH:mm:ss형식의 날짜스트링
     *
     * @return 포맷된 스트링
     *
     * @throws Exception the run util exception
     */
    public static String formatStringDateTime(String dt) throws Exception
    {
        return formatDateString(dt, "yyyy-MM-dd HH:mm:ss", DT_FORMAT_YYYYMMDDHHMMSS, TimeZone.getDefault());
    }


    /**
    * 주어진 포맷으로 데이터를 문자열을 Parsing 한 후 원하는 날짜 포맷의 문자열 객체로 리턴한다.
    *
    * @param dt 주어진 포맷에 대한 문자열
    * @param currFormat 주어진 문자열의 현재 Date Format
    * @param changeFormat 주어진 문자열에 대해 변경하려는 Date Format
    *
    * @return 바꾸어진 포맷의 String 객체
    *
    * @throws Exception the run util exception
    *
    * @exception spectra.base.util.Exception
    */
    public static String formatDateString(String dt, String currFormat, String changeFormat) throws Exception
    {
        return formatDateString(dt, currFormat, changeFormat, TimeZone.getDefault());
    }

    /**
    * 주어진 포맷으로 데이터를 문자열을 Parsing 한 후 원하는 날짜 포맷의 문자열 객체로 리턴한다.
    *
    * @param dt 주어진 포맷에 대한 문자열
    * @param currFormat 주어진 문자열의 현재 Date Format
    * @param changeFormat 주어진 문자열에 대해 변경하려는 Date Format
    * @param timeZone 포맷할때 적용할 타임존
    *
    * @return 바꾸어진 포맷의 String 객체
    *
    * @throws Exception the run util exception
    *
    * @exception spectra.base.util.Exception
    */
    public static String formatDateString(String dt, String currFormat, String changeFormat, TimeZone timeZone) throws Exception
    {
        SimpleDateFormat formatter = new SimpleDateFormat(currFormat); // NOPMD by yshwang on 13. 11. 5 오후 2:47
        formatter.setTimeZone(timeZone);
        Date uDate = null;
        StringBuffer sbError = null;
        try
        {
            uDate = formatter.parse(dt);
        }
        catch(ParseException e)
        {
            sbError = new StringBuffer().append("DateUtil.formatDateString - Mismatch Date format : ")
                                        .append(dt).append("[").append(currFormat).append("]");
            throw new Exception(sbError.toString(),  e);
        }

        return getStringFromDate(uDate, changeFormat);
    }
    
    /**
     * 주어진 포맷으로 데이터를 문자열을 Parsing 한 후 원하는 날짜 포맷의 문자열 객체로 리턴한다.
     *
     * @param dt 주어진 포맷에 대한 문자열
     * @param currFormat 주어진 문자열의 현재 Date Format
     * @param changeFormat 주어진 문자열에 대해 변경하려는 Date Format
     *
     * @return 바꾸어진 포맷의 String 객체
     *
     * @throws Exception the run util exception
     *
     * @exception spectra.base.util.Exception
     */
     public static String formatDateString(String dt, String currFormat, String changeFormat, Locale locale) throws Exception
     {
         return formatDateString(dt, currFormat, changeFormat, TimeZone.getDefault(), locale);
     }
     
    /**
     * 주어진 포맷으로 데이터를 문자열을 Parsing 한 후 원하는 날짜 포맷의 문자열 객체로 리턴한다.
     *
     * @param dt 주어진 포맷에 대한 문자열
     * @param currFormat 주어진 문자열의 현재 Date Format
     * @param changeFormat 주어진 문자열에 대해 변경하려는 Date Format
     * @param timeZone 포맷할때 적용할 타임존
     * @param locale 적용할 로케일
     *
     * @return 바꾸어진 포맷의 String 객체
     *
     * @throws Exception the run util exception
     *
     * @exception spectra.base.util.Exception
     */
     public static String formatDateString(String dt, String currFormat, String changeFormat, TimeZone timeZone, Locale locale) throws Exception
     {
         SimpleDateFormat formatter = new SimpleDateFormat(currFormat, locale);
         formatter.setTimeZone(timeZone);
         Date uDate = null;
         StringBuffer sbError = null;
         try
         {
             uDate = formatter.parse(dt);
         }
         catch(ParseException e)
         {
             sbError = new StringBuffer().append("DateUtil.formatDateString - Mismatch Date format : ")
                                         .append(dt).append("[").append(currFormat).append("]");
             throw new Exception(sbError.toString(),  e);
         }
         finally
         {
             formatter = null;
             sbError = null;
         }
         return getStringFromDate(uDate, changeFormat);
     }

    /**
    * DateFormatUtils.format() 메소드를 사용한다.<br>
    * 주어진 포맷에 따라 String 값으로 현재의 날짜를 가져온다.<br>
    * 포맷은 getStringFromDate 메소드를 참조.
    * ex) DateUtil.getCurrDate("yyyy-MM-dd"); --> 20030515<br>
    * DateUtil.getCurrDate("yyyy-MM-dd-HH:mm:ss:SSS");--> 2003-05-04-20:59:21:187<br>
    * <br>
    *
    * @param format the format
    *
    * @return 주어진 포맷에 대한 현재날짜의 문자열
    *
    * @see org.apache.commons.lang.time.DateFormatUtils#format(java.util.Date, java.lang.String)
    */
    public static String getCurrDate(String format)
    {
        return DateFormatUtils.format(new Date(), format);
    }

    /**
    * DateFormatUtils.format() 메소드를 사용한다.<br>
    * 주어진 포맷에 따라 String 값으로 현재의 날짜를 가져온다.<br>
    * 포맷은 getStringFromDate 메소드를 참조.
    * ex) DateUtil.getCurrDate("yyyy-MM-dd"); --> 20030515<br>
    * DateUtil.getCurrDate("yyyy-MM-dd-HH:mm:ss:SSS");--> 2003-05-04-20:59:21:187<br>
    * <br>
    *
    * @param format 현재날짜를 포맷할 포맷.
    * @param timeZone 날짜 변환에 적용할 타임존
    *
    * @return 주어진 포맷에 대한 현재날짜의 문자열
    *
    * @see org.apache.commons.lang.time.DateFormatUtils#format(java.util.Date, java.lang.String)
    */
    public static String getCurrDate(String format, TimeZone timeZone)
    {
        return DateFormatUtils.format(new Date(), format, timeZone);
    }

    /**
    * DateFormatUtils.format() 메소드를 사용한다.<br>
    * Default format "yyyy-MM-dd"에 대한 날짜를 리턴한다.<br>
    *
    * @return String yyyy-MM-dd로 포맷된 오늘날짜
    *
    * @see spectra.base.util.DateUtil#getCurrDate
    */
    public static String getCurrDate()
    {
        return getCurrDate(DT_FORMAT_YYYYMMDD_DASH);
    }

    /**
    * Default format "yyyyMMddHHmmss"에 대한 날짜를 리턴한다.<br>
    *
    * @return String 오늘 날짜시간 TimeStamp (yyyyMMddHHmmss형식)
    *
    * @see spectra.base.util.DateUtil#getCurrDate
    */
    public static String getCurrDateTimeStamp()
    {
        return getCurrDate(DT_FORMAT_YYYYMMDDHHMMSS);
    }

    /**
     * Default format "yyyyMMddHHmmssSSS"에 대한 날짜를 리턴한다.<br>
     *
     * @return String 오늘 날짜시간(밀리초 포함) TimeStamp (yyyyMMddHHmmssSSS형식)
     *
     * @see spectra.base.util.DateUtil#getCurrDate
     */
    public static String getCurrDateMillisTimeStamp()
    {
        return getCurrDate("yyyyMMddHHmmssSSS");
    }

    /**
    * DateFormatUtils.format() 메소드를 사용한다.<br>
    * Default format "yyyy-MM-dd"에 대한 날짜를 리턴한다.<br>
    *
    * @param timeZone 날짜 포맷에 적용할 타임존.
    *
    * @return String 오늘 날짜
    *
    * @see spectra.base.util.DateUtil#getCurrDate
    */
    public static String getCurrDate(TimeZone timeZone)
    {
        return getCurrDate(DT_FORMAT_YYYYMMDD_DASH, timeZone);
    }

    /**
    * "yyyy-MM-dd", "yyyy/MM/dd" 와 같이 날짜의 구분자가 -, / 등의 문자일 경우에는 yyyyMMdd 로<br>
    * yyyyMMdd 형태의 날짜일 경우에는 Default format "yyyy-MM-dd"로 변환하여 리턴한다.
    *
    * @param dt 날짜 string (포맷된 날짜 또는 datetime stamp)
    *
    * @return String 변환된 날짜
    *
    * @throws Exception the run util exception
    *
    * @exception spectra.base.util.Exception
    */
    public static String getDate(String dt) throws Exception
    {
        if(dt == null || dt!=null && dt.length() < 8 )
        {
            return dt;
        }

        int dtLen = dt.length();
        StringBuffer sbError = null;

        try
        {
            if( dtLen < 10 )
            {
                return getStringFromDate(getDateFromString(dt, DT_FORMAT_YYYYMMDD), DT_FORMAT_YYYYMMDD_DASH);
            }
            else
            {
                return StringUtil.replace(dt, dt.substring(4,5), "");
            }
        }
        catch(Exception e )
        {
            sbError = new StringBuffer().append("DateUtil.getDate - Invaild Date Format : ").append(dt);
            throw new Exception(sbError.toString(),  e);
        }
        finally
        {
            sbError = null;
        }
    }

    /**
    * int 형으로 오늘날짜의 년도를 가져온다.(2003)<br>
    *
    * @return int year
    *
    * @see spectra.base.util.DateUtil#getCurrDate
    */
    public static int getYear()
    {
        return Integer.parseInt(getCurrDate("yyyy"));
    }

    /**
    * int 형으로 오늘날짜의 월을 가져온다.(1월-->1)<br>
    *
    * @return int month
    *
    * @see spectra.base.util.DateUtil#getCurrDate
    */
    public static int getMonth()
    {
        return Integer.parseInt(getCurrDate("MM"));
    }

    /**
    * int 형으로 오늘날짜의 일을 가져온다.(9일-->9)<br>
    *
    * @return int day
    *
    * @see spectra.base.util.DateUtil#getCurrDate
    */
    public static int getDay()
    {
        return Integer.parseInt(getCurrDate("dd"));
    }

    /**
    * int 형으로 현재의 시간을 가져온다.(오후2시-->14)<br>
    *
    * @return int hours
    *
    * @see spectra.base.util.DateUtil#getCurrDate
    */
    public static int getHours()
    {
        return Integer.parseInt(getCurrDate("HH"));
    }

    /**
    * int 형으로 현재의 분을 가져온다.(55분-->55)<br>
    *
    * @return int minutes
    *
    * @see spectra.base.util.DateUtil#getCurrDate
    */
    public static int getMinutes()
    {
        return Integer.parseInt(getCurrDate("mm"));
    }

    /**
    * int 형으로 현재의 초를 가져온다.(33초-->33)<br>
    *
    * @return int seconds
    *
    * @see spectra.base.util.DateUtil#getCurrDate
    */
    public static int getSeconds()
    {
        return Integer.parseInt(getCurrDate("ss"));
    }

    /**
    * 현재의 날짜를 java.sql.Timestamp로 가져온다.
    *
    * @return 현재의 날짜의 java.sql.Timestamp
    */
    public static java.sql.Timestamp getCurrTimestamp()
    {
        return new java.sql.Timestamp(System.currentTimeMillis());
    }

    /**
    * Default format "yyyy-MM-dd"에 대한 Validation을 체크하여<br>
    * 맞으면 true, 틀리면 false를 리턴한다.
    *
    * @param dt 체크할 날짜 문자열
    *
    * @return "yyyy-MM-dd"타입으로 체크한 Validation 결과
    */
    public static boolean check (String dt)
    {
        return check(dt, DT_FORMAT_YYYYMMDD_DASH);
    }

    /**
    * 주어진 날짜 format 에 대한 Validation을 체크하여<br>
    * 맞으면 true, 틀리면 false를 리턴한다.
    *
    * @param dt 체크할 날짜 문자열
    * @param format 체크할 날짜 format
    *
    * @return 주어진 포맷에 대해 체크한 결과.
    */
    public static boolean check(String dt, String format)
    {
        if ( dt == null ) {
			return false;
		}
        if ( format == null ) {
			return false;
		}

        Date date = null;
        try
        {
            date = getDateFromString(dt, format);
        }
        catch (Exception e)
        {
            return false;
        }

        if (!getStringFromDate(date, format).equals(dt)) {
			return false;
		}

        return true;
    }

    /**
    * Default format "yyyy-MM-dd"에 대한 Validation을 체크하여<br>
    * 맞으면 true, 틀리면 false를 리턴한다.
    *
    * @param dt 체크할 날짜 문자열
    *
    * @return yyyyMMddHHmmss타입으로 문자열을 체크한 결과
    */
    public static boolean checkTimestamp(String dt)
    {
        return check(dt, DT_FORMAT_YYYYMMDDHHMMSS);
    }

    /**
    * 주어진 년도월(yyyyMM-200305)에 주어진 기간(Term:개월수)만큼 더하거나 뺀다.<br>
    * 예제)<br>
    * String calMon = DateUtil.calMonths("200310", -12);<br>
    * calMon ==> "200210"<br>
    *
    * @param strMonth 날짜(년도월)
    * @param term 주어진 월에 빼거나 더할 달의 수
    *
    * @return 날짜(년도월)
    *
    * @throws Exception the run util exception
    *
    * @exception spectra.base.util.Exception
    */
    public static String calMonths(String strMonth, int term) throws Exception
    {
        if(strMonth == null || strMonth!=null && strMonth.length() < 6 )
        {
            throw new Exception("DateUtil.calMonths - Invaild Date Format.(correct format - yyyyMM) : " + strMonth);
        }

        String retMonth = "";
        try
        {
            int year = Integer.parseInt(strMonth.substring(0,4));
            int month = Integer.parseInt(strMonth.substring(4,6))-1;

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, 1);
            calendar.add(Calendar.MONTH, term);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH)+1;
            retMonth = year + (month<10? "0"+month:Integer.toString(month)); // NOPMD:UselessParentheses
        }
        catch(Exception e)
        {
            throw new Exception("DateUtil.calMonths Method Error !!  " + strMonth, e);
        }

        return retMonth;
    }

    /**
     * 주어진 년도월(yyyyMMdd-20030501)에 주어진 기간(Term:개월수)만큼 더하거나 뺀다.<br>
     * 예제)<br>
     * String calMon = DateUtil.calMonths("20031001", -12);<br>
     * calMon ==> "20021001"<br>
     *
     * @param fromDate 날짜(년도월일)
     * @param term 주어진 월에 빼거나 더할 달의 수
     *
     * @return 날짜(년도월일)
     *
     * @throws Exception the run util exception
     *
     * @exception spectra.base.util.Exception
     */
    public static String calMonthsFromDate(String fromDate, int term) throws Exception
    {
        if(fromDate == null || fromDate!=null && fromDate.length() < 8 )
        {
            throw new Exception("DateUtil.calMonthsFromDate - Invaild Date Format.(correct format - yyyyMMdd) : " + fromDate);
        }

        String retMonth = "";
        try
        {
            int year = Integer.parseInt(fromDate.substring(0,4));
            int month = Integer.parseInt(fromDate.substring(4,6))-1;
            int day = Integer.parseInt(fromDate.substring(6,8));
            
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            calendar.add(Calendar.MONTH, term);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH)+1;
            day = calendar.get(Calendar.DATE);
            retMonth = year + (month<10? "0"+month:Integer.toString(month)) + (day<10? "0"+day:Integer.toString(day)); // NOPMD:UselessParentheses
        }
        catch(Exception e)
        {
            throw new Exception("DateUtil.calMonthsFromDate Method Error !!  " + fromDate, e);
        }

        return retMonth;
    }
    
    /**
    * 주어진 날짜(yyyyMMdd-20030503)에 주어진 기간(Term:일수)만큼 더하거나 뺀다.<br>
    * 예제)<br>
    * String calDay = DateUtil.calDays("20030517", -12);<br>
    * calDay ==> "20030505"<br>
    *
    * @param strDay 날짜(년도월일-yyyyMMdd)
    * @param term 주어진 날에 빼거나 더할 일의 수
    *
    * @return 날짜(년도월일-yyyyMMdd)
    *
    * @throws Exception the run util exception
    *
    * @exception spectra.base.util.Exception
    */
    public static String calDays(String strDay, int term) throws Exception
    {
        if(strDay == null || strDay!=null && strDay.length() < 8 )
        {
            throw new Exception("DateUtil.calDays - Invaild Date Format.(correct format - yyyyMMdd) : " + strDay);
        }
        String retDay = "";
        try
        {
            int year = Integer.parseInt(strDay.substring(0,4));
            int month = Integer.parseInt(strDay.substring(4,6))-1;
            int day = Integer.parseInt(strDay.substring(6,8));

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            calendar.add(Calendar.DATE, term);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH)+1;
            day = calendar.get(Calendar.DATE);
            retDay = year + (month<10? "0"+month:Integer.toString(month))+(day<10? "0"+day:Integer.toString(day)); // NOPMD:UselessParentheses
        }
        catch(Exception e)
        {
            throw new Exception("DateUtil.calDays Method Error !!  " + strDay, e);
        }

        return retDay;
    }

    /**
    * 주어진 날짜와시간(yyyyMMddHH/yyyyMMddHHmm/yyyyMMddHHmmss-2003101215/200310121532/20031012153210)에<br>
    * 주어진 시간(Term:시간)만큼 더하거나 뺀다.<br>
    * 예제)<br>
    * String calHour = DateUtil.calHours("20031012093210", -12); or DateUtil.calHours("2003101209", -12)<br>
    * calHour ==> "20031011213210" or "20031011210000"<br>
    *
    * @param strHour 시간(년도월일시간[분초]-yyyyMMddHH[mmss])
    * @param term 주어진 시간에 빼거나 더할 시간의 수
    *
    * @return 시간(년도월일시간분초-yyyyMMddHHmmss)
    *
    * @throws Exception the run util exception
    *
    * @see spectra.base.util.DateUtil#calTimes
    * @exception spectra.base.util.Exception
    */
    public static String calHours(String strHour, int term) throws Exception
    {
        return calTimes(strHour, term, Calendar.HOUR_OF_DAY);
    }

    /**
    * 주어진 날짜와시간(yyyyMMddHH/yyyyMMddHHmm/yyyyMMddHHmmss-2003101215/200310121532/20031012153210)에<br>
    * 주어진 시간(Term:분)만큼 더하거나 뺀다.<br>
    * 예제)<br>
    * String strMin = DateUtil.calMins("20031012093210", -10); or DateUtil.calHours("2003101209", -10)<br>
    * strMin ==> "20031012092210" or "20031012085000"<br>
    *
    * @param strMin 시간(년도월일시간[분초]-yyyyMMddHH[mmss])
    * @param term 주어진 시간에 빼거나 더할 분의 수
    *
    * @return 시간(년도월일시간분초-yyyyMMddHHmmss)
    *
    * @throws Exception the run util exception
    *
    * @see spectra.base.util.DateUtil#calTimes
    * @exception spectra.base.util.Exception
    */
    public static String calMins(String strMin, int term) throws Exception
    {
        return calTimes(strMin, term, Calendar.MINUTE);
    }

    /**
    * 주어진 날짜와시간(yyyyMMddHH/yyyyMMddHHmm/yyyyMMddHHmmss-2003101215/200310121532/20031012153210)에<br>
    * 주어진 시간(Term:초)만큼 더하거나 뺀다.<br>
    * 예제)<br>
    * String strSecs = DateUtil.calSecs("20031012093210", -10); or DateUtil.calHours("200310120932", -10)<br>
    * strSecs ==> "20031012093200" or "20031012093150"<br>
    *
    * @param strSecs 시간(년도월일시간[분초]-yyyyMMddHH[mmss])
    * @param term 주어진 시간에 빼거나 더할 초의 수
    *
    * @return 시간(년도월일시간분초-yyyyMMddHHmmss)
    *
    * @throws Exception the run util exception
    *
    * @see spectra.base.util.DateUtil#calTimes
    * @exception spectra.base.util.Exception
    */
    public static String calSecs(String strSecs, int term) throws Exception
    {
        return calTimes(strSecs, term, Calendar.SECOND);
    }

    /**
    * 주어진 날짜와시간(yyyyMMddHHmmss-20031012153210)에<br>
    * 주어진 시간(Term:시간수(시간,분,초))만큼 더하거나 뺀다.<br>
    * 예제)<br>
    * String calTime = DateUtil.calTimes("20031012093210", -12, java.util.Calendar.HOUR_OF_DAY);<br>
    * calTime ==> "20031011213210"<br>
    *
    * @param strTime 시간(년도월일시간[분초]-yyyyMMddHH[mmss])
    * @param term 주어진 시간에 빼거나 더할 시간,분,초의 수
    * @param calTimeType : Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND 세가지 타입중에 하나이다.
    *
    * @return 시간(년도월일시간분초-yyyyMMddHHmmss)
    *
    * @throws Exception the run util exception
    *
    * @exception spectra.base.util.Exception
    */
    public static String calTimes(String strTime, int term, int calTimeType) throws Exception
    {
        if(strTime == null || strTime!=null && strTime.length() < 8 )
        {
            throw new Exception("DateUtil.calTimes - Invaild Date Format.(correct format - yyyyMMddHH/yyyyMMddHHmm/yyyyMMddHHmmss) : " + strTime);
        }
        String retTimes = "";

        try
        {
            int year = Integer.parseInt(strTime.substring(0,4));
            int month = Integer.parseInt(strTime.substring(4,6))-1;
            int day = Integer.parseInt(strTime.substring(6,8));
            int hour = Integer.parseInt(strTime.substring(8,10));
            int min = 0;
            if(strTime.length()>=12) {
				min = Integer.parseInt(strTime.substring(10,12));
			}
            int sec = 0;
            if(strTime.length()>=14) {
				sec = Integer.parseInt(strTime.substring(12,14));
			}

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day, hour, min, sec);
            calendar.add(calTimeType, term);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH)+1;
            day = calendar.get(Calendar.DATE);
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            min = calendar.get(Calendar.MINUTE);
            sec = calendar.get(Calendar.SECOND);
            retTimes = year + (month<10? "0"+month:Integer.toString(month))+(day<10? "0"+day:Integer.toString(day)) // NOPMD:UselessParentheses
                    +(hour<10? "0"+hour:Integer.toString(hour))+(min<10? "0"+min:Integer.toString(min))+(sec<10? "0"+sec:Integer.toString(sec)); // NOPMD:UselessParentheses
        }
        catch(Exception e)
        {
            throw new Exception("DateUtil.calTimes Method Error !!  " + strTime, e);
        }

        return retTimes;
    }

    /**
    * 주어진 두 날짜의 차이가 나는 년수를 리턴한다.<br>
    * firstDt - secondDt 로서 첫번째 날짜가 빠를 경우에는 plus를 <br>
    * 두번째 날짜가 더 빠를 경우에는 minus를 리턴한다.<br>
    * 예제)<br>
    * DateUtil.betweenYears("20031205", "20021231"); ==> 0.9287671232876712<br>
    * DateUtil.betweenYears("20021231", "20031205"); ==> -0.9287671232876712<br>
    *
    * @param firstDt 날짜(yyyyMMdd - 20031205)
    * @param secondDt 날짜(yyyyMMdd - 20021231)
    *
    * @return 날짜 차이에 대한 double형 결과.(double형 +,-)
    *
    * @throws Exception the run util exception
    *
    * @exception spectra.base.util.Exception
    */
     public static double betweenYears (String firstDt, String secondDt) throws Exception
     {
        double between = -1;
        StringBuffer sbError = null;
        try
        {
            between = Integer.parseInt(firstDt.substring(0,4)) - Integer.parseInt(secondDt.substring(0,4));
            double d = 0;
            d = Integer.parseInt(firstDt.substring(4,6)) - Integer.parseInt(secondDt.substring(4,6));
            between += d/12;
            d = Integer.parseInt(firstDt.substring(6,8)) - Integer.parseInt(secondDt.substring(6,8));
            between += d/365;
        }
        catch ( Exception e )
        {
            sbError = new StringBuffer().append("DateUtil.betweenYears - (Correct format - yyyyMMdd) : ")
                                        .append(firstDt).append(",").append(secondDt);

            throw new Exception(sbError.toString(), e);
        }
        finally
        {
            sbError = null;
        }

        return between;
     }

    /**
    * 주어진 두 날짜의 차이가 나는 개월 수를 리턴한다.<br>
    * firstDt - secondDt 로서 첫번째 날짜가 빠를 경우에는 plus를 <br>
    * 두번째 날짜가 더 빠를 경우에는 minus를 리턴한다.<br>
    * 예제)<br>
    * DateUtil.betweenMonths("20031205", "20021231"); ==> 11.133333333333333<br>
    * DateUtil.betweenMonths("20021231", "20031205"); ==> -11.133333333333333<br>
    *
    * @param firstDt 날짜(yyyyMMdd - 20031205)
    * @param secondDt 날짜(yyyyMMdd - 20021231)
    *
    * @return 날짜 차이에 대한 double형 결과.(double형 +,-)
    *
    * @throws Exception the run util exception
    *
    * @exception spectra.base.util.Exception
    */
    public static double betweenMonths (String firstDt, String secondDt) throws Exception
    {
        double between = 0;
        StringBuffer sbError = null;
        try
        {
            between = (Integer.parseInt(firstDt.substring(0,4)) - Integer.parseInt(secondDt.substring(0,4)) ) * 12 ;
            double d = 0;
            d = Integer.parseInt(firstDt.substring(4,6)) - Integer.parseInt(secondDt.substring(4,6));
            between += d;
            d = Integer.parseInt(firstDt.substring(6,8)) - Integer.parseInt(secondDt.substring(6,8));
            between += d/30;

        }
        catch ( Exception e )
        {
            sbError = new StringBuffer().append("DateUtil.betweenMonths - (Correct format - yyyyMMdd) : ")
                                        .append(firstDt).append(",").append(secondDt);

            throw new Exception(sbError.toString(), e);
        }
        finally
        {
            sbError = null;
        }

        return between;
    }

    /**
    * 주어진 두 날짜의 차이가 나는 날짜 수를 리턴한다.<br>
    * 날짜의 순서는 상관없이 항상 양수를 리턴한다. <br>
    * 예제)<br>
    * DateUtil.betweenDays("20031205", "20021231"); ==> 339<br>
    * DateUtil.betweenDays("20021231", "20031205"); ==> 339<br>
    *
    * @param firstDt 날짜(yyyyMMdd - 20031205)
    * @param secondDt 날짜(yyyyMMdd - 20021231)
    *
    * @return 날짜 차이에 대한 날짜 수(항상 +)
    *
    * @throws Exception the run util exception
    *
    * @exception spectra.base.util.Exception
    */
    public static long betweenDays (String firstDt, String secondDt) throws Exception
    {
        return Math.abs(diffDays(firstDt, secondDt));
    }
    /**
    * 주어진 두 날짜(from, to)의 차이가 나는 날짜 일수를 리턴한다.<br>
    * first 가 second 보다 크면 양수, first 보다 second 가 크면 음수로 반환된다.<br>
    * 예제)<br>
    * DateUtil.diffDays("20031205", "20031204"); ==> 1<br>
    * DateUtil.diffDays("20031204", "20031205"); ==> -1<br>
    *
    * @param firstDt 날짜(yyyyMMdd - 20031205)
    * @param secondDt 날짜(yyyyMMdd - 20021231)
    *
    * @return 날짜 차이에 대한 날짜 일수(first > second => +, first < second => -, first = secnod => 0)
    *
    * @throws Exception the run util exception
    *
    * @exception spectra.base.util.Exception
    */
    public static long diffDays (String firstDt, String secondDt) throws Exception
    {
        long between = 0;
        StringBuffer sbError = null;
        try
        {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(firstDt.substring(0,4)), Integer.parseInt(firstDt.substring(4,6)) - 1, Integer.parseInt(firstDt.substring(6,8)));
            Date firstDay = calendar.getTime();

            calendar.set(Integer.parseInt(secondDt.substring(0,4)), Integer.parseInt(secondDt.substring(4,6)) - 1, Integer.parseInt(secondDt.substring(6,8)));
            Date secondDay = calendar.getTime();

            between = ( firstDay.getTime() - secondDay.getTime() ) / 1000 / 60 / 60 / 24;
        }
        catch ( Exception e )
        {
            sbError = new StringBuffer().append("DateUtil.diffDays - (Correct format - yyyyMMdd) : ")
                                        .append(firstDt).append(",").append(secondDt);

            throw new Exception(sbError.toString(), e);
        }
        finally
        {
            sbError = null;
        }

        return between;
    }

    /**
     * 주어진 두 날짜/시간의 시간차(단위:초)를 구하는 메소드.<br>
     * 날짜/시간의 순서는 상관없이 항상 양수를 리턴한다. <br>
     * 다만 입력되는 시작시간과 종료시간의 길이는 14자리 이상이어야 한다.
     * <br><br>
     * author kwlee
     *
     * @param strFirstDateTime 시작 날짜/시간. (yyyyMMddHHmmss)
     * @param strSecondDateTime 종료 날짜/시간. (yyyyMMddHHmmss)
     *
     * @return 시간차(단위:초).
     *
     * @throws spectra.base.util.Exception      * @throws Exception the run util exception
     */
    public static long betweenSec(String strFirstDateTime, String strSecondDateTime) throws Exception
    {
        long lBetween = 0;
        StringBuffer sbError = null;

        try
        {
            Calendar calFirst = Calendar.getInstance();
            Calendar calSecond = Calendar.getInstance();

            calFirst.set(Integer.parseInt(strFirstDateTime.substring(0,4)),
                       Integer.parseInt(strFirstDateTime.substring(4,6)) - 1,
                       Integer.parseInt(strFirstDateTime.substring(6,8)),
                       Integer.parseInt(strFirstDateTime.substring(8,10)),
                       Integer.parseInt(strFirstDateTime.substring(10,12)),
                       Integer.parseInt(strFirstDateTime.substring(12,14)));

            calSecond.set(Integer.parseInt(strSecondDateTime.substring(0,4)),
                       Integer.parseInt(strSecondDateTime.substring(4,6)) - 1,
                       Integer.parseInt(strSecondDateTime.substring(6,8)),
                       Integer.parseInt(strSecondDateTime.substring(8,10)),
                       Integer.parseInt(strSecondDateTime.substring(10,12)),
                       Integer.parseInt(strSecondDateTime.substring(12,14)));

            lBetween = Math.abs((long)(calFirst.getTimeInMillis() / 1000) - (long)(calSecond.getTimeInMillis() / 1000));
        }
        catch(Exception e)
        {
            sbError = new StringBuffer().append("DateUtil.betweenSec - (Correct format - yyyyMMddHHmmss) : ")
            .append(strFirstDateTime).append(",").append(strSecondDateTime);

            throw new Exception(sbError.toString(), e);
        }

        return lBetween;
    }

    /**
    * 오늘 날짜 기준의 요일을 1~7값으로 리턴.
    * 1 : SUNDAY, 2 : MONDAY, 3 : TUESDAY, 4 : WEDNESDAY, 5 : THURSDAY, 6 : FRIDAY, 7 : SATURDAY
    *
    * @return 오늘 날짜 기준의 요일값
    */
    public static int getWeekDay()
    {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
    * 주어진 날짜 기준의 요일을 1~7값으로 리턴.
    * 1 : SUNDAY, 2 : MONDAY, 3 : TUESDAY, 4 : WEDNESDAY, 5 : THURSDAY, 6 : FRIDAY, 7 : SATURDAY
    *
    * @param dt yyyyMMdd형식의 날짜 스트링.
    *
    * @return 주어진 날짜 기준의 요일값
    */
    public static int getWeekDay(String dt)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(
                Integer.parseInt(dt.substring(0, 4)),
                Integer.parseInt(dt.substring(4, 6)) - 1,
                Integer.parseInt(dt.substring(6, 8))
                );
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
    * 주어진 날짜 기준의 요일을 1~7값으로 리턴.
    * 1 : SUNDAY, 2 : MONDAY, 3 : TUESDAY, 4 : WEDNESDAY, 5 : THURSDAY, 6 : FRIDAY, 7 : SATURDAY
    *
    * @param year yyyy년도
    * @param month MM월(based on 0)
    * @param day dd일 
    *
    * @return 주어진 날짜 기준의 요일값
    */
    public static int getWeekDay(int year, int month, int day)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
    * 주어진 날짜 기준의 요일을 1~7값으로 리턴.
    * 1 : SUNDAY, 2 : MONDAY, 3 : TUESDAY, 4 : WEDNESDAY, 5 : THURSDAY, 6 : FRIDAY, 7 : SATURDAY
    *
    * @param date : "yyyyMMdd" 파라미터
    *
    * @return 주어진 날짜 기준의 요일값
    */
    public static int getWeekDay(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
    * 주어진 int 형 값(월)에 대해 월의 영문이름을 리턴한다.<br>
    * 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 값에 대해<br>
    * JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC 값을 리턴한다.<br>
    *
    * @param imon 월에 대한 숫자 (based on 1)
    *
    * @return 월에 대한 영문약어 이름.
    */
    public static String getMonthName(int imon)
    {
        String monthName = "";
        switch(imon%12)
        {
            case 1 :  monthName = "JAN"; break;
            case 2 :  monthName = "FEB"; break;
            case 3 :  monthName = "MAR"; break;
            case 4 :  monthName = "APR"; break;
            case 5 :  monthName = "MAY"; break;
            case 6 :  monthName = "JUN"; break;
            case 7 :  monthName = "JUL"; break;
            case 8 :  monthName = "AUG"; break;
            case 9 :  monthName = "SEP"; break;
            case 10 : monthName = "OCT"; break;
            case 11 : monthName = "NOV"; break;
            case 0 :  monthName = "DEC"; break;
            default : 
        }
        return monthName;
    }

    /**
    * 주어진 포맷에 따라 두 날짜에 대한 Min Date를 계산하여 리턴한다.
    *
    * @param dt "yyyyMMdd" - 비교할 날짜
    * @param dt1 "yyyyMMdd" - 비교할 날짜
    *
    * @return Min Date, 만약 에러가 발생시 "" 값을 리턴한다.
    *
    * @throws Exception the run util exception
    *
    * @exception spectra.base.util.Exception
    */
    public static String minDate(String dt, String dt1) throws Exception
    {
        StringBuffer sbError = null;
        try
        {
            int idt = Integer.parseInt(dt);
            int idt1 = Integer.parseInt(dt1);
            return idt>=idt1? dt1:dt;
        }
        catch(Exception e)
        {
            sbError = new StringBuffer().append("DateUtil.MinDate - (Correct format - yyyyMMdd) : ")
                                        .append(dt).append(",").append(dt1);

            throw new Exception(sbError.toString(), e);
        }
        finally
        {
            sbError = null;
        }
    }

    /**
    * 주어진 포맷에 따라 두 날짜에 대한 Max Date를 계산하여 리턴한다.
    *
    * @param dt "yyyyMMdd" - 비교할 날짜
    * @param dt1 "yyyyMMdd" - 비교할 날짜
    *
    * @return Max Date, 만약 에러가 발생시 "" 값을 리턴한다.
    *
    * @throws Exception the run util exception
    *
    * @exception spectra.base.util.Exception
    */
    public static String maxDate(String dt, String dt1) throws Exception
    {
        StringBuffer sbError = null;
        try
        {
            int idt = Integer.parseInt(dt);
            int idt1 = Integer.parseInt(dt1);
            return idt>=idt1? dt:dt1;
        }
        catch(Exception e)
        {
            sbError = new StringBuffer().append("DateUtil.maxDate - (Correct format - yyyyMMdd) : ")
                                        .append(dt).append(",").append(dt1);

            throw new Exception(sbError.toString(), e);
        }
        finally
        {
            sbError = null;
        }
    }

    /**
    * 주어진 포맷에 따라 두 날짜에 대한 Max Date를 계산하여 리턴한다.
    *
    * @param dt 비교할 날짜
    * @param dt1 비교할 날짜
    * @param format Date Format - dt, dt1에서 입력한 날짜들에 대한 포맷을 지정한다.
    *
    * @return Max Date, 만약 에러가 발생시 "" 값을 리턴한다.
    *
    * @throws Exception the run util exception
    *
    * @see spectra.base.util.DateUtil#maxDate(String, String)
    * @exception spectra.base.util.Exception
    */
    public static String maxDate(String dt, String dt1, String format) throws Exception
    {
        StringBuffer sbError = null;
        try
        {
            Date date = getDateFromString(dt, format);
            Date date1 = getDateFromString(dt1, format);

            return maxDate(getStringFromDate(date, DT_FORMAT_YYYYMMDD), getStringFromDate(date1,DT_FORMAT_YYYYMMDD));
        }
        catch(Exception e)
        {
            sbError = new StringBuffer().append("DateUtil.maxDate - (input format - ").append(format)
                                        .append(") : ").append(dt).append(",").append(dt1);

            throw new Exception(sbError.toString(), e);
        }
        finally
        {
            sbError = null;
        }
    }

    /**
    * 첫번째 날짜가 두번째 날짜보다 크거나 같을 경우 boolean 형으로 리턴한다.
    *
    * @param dt "yyyyMMdd" - 비교할 날짜
    * @param dt1 "yyyyMMdd" - 비교할 날짜
    *
    * @return true, 첫번째 날짜가 두번째 날짜보다 크거나 같을 경우
    *
    * @throws Exception the run util exception
    *
    * @see spectra.base.util.DateUtil#maxDate(String, String)
    * @exception spectra.base.util.Exception
    */
    public static boolean isMaxDate(String dt, String dt1)  throws Exception
    {
        String maxDate = maxDate(dt, dt1);
        return maxDate.equals(dt);
    }

    /**
    * 첫번째 날짜가 두번째 날짜보다 크거나 같을 경우 boolean 형으로 리턴한다.
    *
    * @param dt 비교할 날짜
    * @param dt1 비교할 날짜
    * @param dtFormat 날짜스트링의 format
    *
    * @return true, 첫번째 날짜가 두번째 날짜보다 크거나 같을 경우
    *
    * @throws Exception the run util exception
    *
    * @see spectra.base.util.DateUtil#maxDate(String, String, String)
    * @exception spectra.base.util.Exception
    */
    public static boolean isMaxDate(String dt, String dt1, String dtFormat) throws Exception
    {
        String maxDate = maxDate(dt, dt1, dtFormat);
        return maxDate.equals(dt);
    }

    /**
     * 주어진 포맷에 따라 두 날짜에 대한 Max Date를 계산하여 리턴한다.
     *
     * @param dt "yyyyMMddHHmmss" - 비교할 날짜시간
     * @param dt1 "yyyyMMddHHmmss" - 비교할 날짜시간
     *
     * @return Max Date, 만약 에러가 발생시 "" 값을 리턴한다.
     *
     * @throws Exception the run util exception
     *
     * @exception spectra.base.util.Exception
     */
     public static String maxTimeStamp(String dt, String dt1) throws Exception
     {
         StringBuffer sbError = null;
         try
         {
             long idt = Long.parseLong(dt);
             long idt1 = Long.parseLong(dt1);
             return idt>=idt1? dt:dt1;
         }
         catch(Exception e)
         {
             sbError = new StringBuffer().append("DateUtil.maxDate - (Correct format - yyyyMMdd) : ")
                                         .append(dt).append(",").append(dt1);

             throw new Exception(sbError.toString(), e);
         }
         finally
         {
             sbError = null;
         }
     }

     /**
     * 주어진 포맷에 따라 두 날짜에 대한 Max Date를 계산하여 리턴한다.
     *
     * @param dt 비교할 날짜시간
     * @param dt1 비교할 날짜시간
     * @param format Date Format - dt, dt1에서 입력한 날짜들에 대한 포맷을 지정한다.
     *
     * @return Max Date, 만약 에러가 발생시 "" 값을 리턴한다.
     *
     * @throws Exception the run util exception
     *
     * @see spectra.base.util.DateUtil#maxDate(String, String)
     * @exception spectra.base.util.Exception
     */
     public static String maxTimeStamp(String dt, String dt1, String format) throws Exception
     {
         StringBuffer sbError = null;
         try
         {
             Date date = getDateFromString(dt, format);
             Date date1 = getDateFromString(dt1, format);

             return maxTimeStamp(getStringFromDate(date, DT_FORMAT_YYYYMMDDHHMMSS), getStringFromDate(date1, DT_FORMAT_YYYYMMDDHHMMSS));
         }
         catch(Exception e)
         {
             sbError = new StringBuffer().append("DateUtil.maxTimeStamp - (input format - ").append(format)
                                         .append(") : ").append(dt).append(",").append(dt1);

             throw new Exception(sbError.toString(), e);
         }
         finally
         {
             sbError = null;
         }
     }

     /**
     * 첫번째 날짜가 두번째 날짜보다 크거나 같을 경우 boolean 형으로 리턴한다.
     *
     * @param dt "yyyyMMddHHmmss" - 비교할 날짜시간
     * @param dt1 "yyyyMMddHHmmss" - 비교할 날짜시간
     *
     * @return true, 첫번째 날짜가 두번째 날짜보다 크거나 같을 경우
     *
     * @throws Exception the run util exception
     *
     * @see spectra.base.util.DateUtil#maxDate(String, String)
     * @exception spectra.base.util.Exception
     */
     public static boolean isMaxTimeStamp(String dt, String dt1)  throws Exception
     {
         String maxDate = maxTimeStamp(dt, dt1);
         return maxDate.equals(dt);
     }

     /**
     * 첫번째 날짜가 두번째 날짜보다 크거나 같을 경우 boolean 형으로 리턴한다.
     *
     * @param dt 비교할 날짜시간
     * @param dt1 비교할 날짜시간
     * @param dtFormat 날짜스트링의 format
     *
     * @return true, 첫번째 날짜가 두번째 날짜보다 크거나 같을 경우
     *
     * @throws Exception the run util exception
     *
     * @see spectra.base.util.DateUtil#maxDate(String, String, String)
     * @exception spectra.base.util.Exception
     */
     public static boolean isMaxTimeStamp(String dt, String dt1, String dtFormat) throws Exception
     {
         String maxDate = maxTimeStamp(dt, dt1, dtFormat);
         return maxDate.equals(dt);
     }
     
    /**
    * 해당 년도와 월에 대한 마지막 일자(즉, 해당월의 총일수)를 구한다.
    *
    * @param year 년도
    * @param month 월
    *
    * @return 마지막 일자.
    *
    * @see spectra.base.util.DateUtil#getDayCount(String)
    */
    public static int getDayCount(int year, int month)
    {
        int days[] = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
            30, 31
        };
        if(year % 4 == 0 && month == 2) {
			return 29;
		} else {
			return days[month- 1];
		}
    }

    /**
    * 해당 년도와 월에 대한 마지막 일자(즉, 해당월의 총일수)를 구한다.
    *
    * @param yyyyMM 년도월
    *
    * @return 마지막 일자.
    *
    * @throws Exception the run util exception
    *
    * @see spectra.base.util.DateUtil#getDayCount(int, int)
    */
    public static int getDayCount(String yyyyMM) throws Exception
    {
        int year = 0;
        int month = 0;
        try
        {
            year = Integer.parseInt(yyyyMM.substring(0, 4));
            month = Integer.parseInt(yyyyMM.substring(4, 6));
        }
        catch(Exception e)
        {
            throw new Exception("DateUtil.getDayCount -(Correct format - yyyyMM) : " + yyyyMM, e);
        }

        return getDayCount(year, month);
    }

    /**
    * 년도월일에 대해 유효한 날짜인지를 체크해서 유효하면 true, 아니면 false를 리턴한다.
    *
    * @param year 년
    * @param month 월
    * @param day 일
    *
    * @return 유효한 날자이면 true 아니면 false
    */
    public static boolean isValidateDate(int year, int month, int day)
    {
        if(year < 0) {
			return false;
		}
        if(month <= 0 || month > 12) {
			return false;
		}
        int realDay = getDayCount(year, month);
        return day >= 1 && day <= realDay;
    }

    /**
    * 년도월일에 대해 유효한 날짜인지를 체크해서 유효하면 true, 아니면 false를 리턴한다.
    *
    * @param dt 년도월일(yyyyMMdd)
    *
    * @return 유효한 날자이면 true 아니면 false
    */
    public static boolean isValidateDate(String dt)
    {
        int year = 0;
        int month = 0;
        int day = 0;
        try
        {
            year = Integer.parseInt(dt.substring(0, 4));
            month = Integer.parseInt(dt.substring(4, 6));
            day = Integer.parseInt(dt.substring(6, 8));
        }
        catch(Exception e)
        {
            return false;
        }
        return isValidateDate(year, month, day);
    }

    /**
     * GMT로부터의 시간차를 구함.
     *
     * @param tz 타임존
     *
     * @return GMT로부터의 시간차(시간단위)
     */
    public static double diffGMT( TimeZone tz )
    {
        return (double) tz.getRawOffset() / (60 * 60 * 1000);
    }

    /**
    * 월요일~일요일까지 해당주의 월요일날짜를 리턴한다.
    * getSundayOfTheWeek("20061220") ==> "20061218"
    *
    * @param dt yyyyMMdd형식의 날짜 스트링.
    *
    * @return 주어진 날짜의 주에 대한 월요일날짜를 리턴.
    */
    public static String getMondayOfTheWeek(String dt)
    {
        String date = null;
        try
        {
            Calendar c = Calendar.getInstance();
            c.set(Integer.parseInt(dt.substring(0,4)), Integer.parseInt(dt.substring(4,6)) - 1, Integer.parseInt(dt.substring(6,8)));
            int dayOfTheWeek = c.get (Calendar.DAY_OF_WEEK);
            switch (dayOfTheWeek)
            {
                case Calendar.MONDAY :  // 2
                    date = calDays(dt, 0);
                    break;
                case Calendar.TUESDAY :  // 3
                    date = calDays(dt, -1);
                    break;
                case Calendar.WEDNESDAY :  // 4
                    date = calDays(dt, -2);
                    break;
                case Calendar.THURSDAY :  // 5
                    date = calDays(dt, -3);
                    break;
                case Calendar.FRIDAY :  // 6
                    date = calDays(dt, -4);
                    break;
                case Calendar.SATURDAY :  // 7
                    date = calDays(dt, -5);
                    break;
                case Calendar.SUNDAY :  // 1
                    date = calDays(dt, -6);
                    break;
                default :     
            }
        }
        catch(Exception e)
        {
            return null;
        }
        return date;
    }

    /**
    * <pre>
    * 해당 기간내의 기간을 월요일부터 일요일까지 yyyyMMdd 포맷으로 데이타값을 넘겨준다.
    * getWeeks("20061205", "20061213", "~") ==> 20061205~20061210, 20061211~20061213
    * </pre>
     *
     * @param startDate 기간 시작일자
     * @param endDate 기간 마지막일자
     * @param separator 날짜 범위 문자
     *
     * @return 기간내 주별 기간 목록
     *
     * @throws Exception the run util exception
     */
    public static List getWeeks(String startDate, String endDate, String separator) throws Exception
    {
        return getWeeks(startDate, endDate, separator, null);
    }

    /**
     * <pre>
     * 해당 기간내의 기간을 월요일부터 일요일까지 주어진 포맷으로 데이타값을 넘겨준다.
     * getWeeks("20061205", "20061213", "~", "yyyy/MM/dd") ==> 2006/12/05~2006/12/10, 2006/12/11~2006/12/13
     * </pre>
     *
     * @param startDate 기간 시작일자
     * @param endDate 기간 마지막일자
     * @param separator 날짜 범위 문자
     * @param format 리턴받을 날짜에 대한 포맷
     *
     * @return 기간내 주별 기간 목록
     *
     * @throws Exception the run util exception
     */
    public static List getWeeks(String startDate, String endDate, String separator, String format) throws Exception
    {
        List vResult = null;
        String strFormat = format;
        try
        {
            String strMonday = null;
            String strSunday = null;
            String strStartWeekday = null;
            String strEndWeekday = null;

            Calendar cStart = Calendar.getInstance();
            cStart.set(Integer.parseInt(startDate.substring(0,4)), Integer.parseInt(startDate.substring(4,6)) - 1, Integer.parseInt(startDate.substring(6,8)));
            Calendar cEnd = Calendar.getInstance();
            cEnd.set(Integer.parseInt(endDate.substring(0,4)), Integer.parseInt(endDate.substring(4,6)) - 1, Integer.parseInt(endDate.substring(6,8)));
            if (strFormat == null)
            {
                strFormat = DT_FORMAT_YYYYMMDD;
            }
            String initDate = startDate;

            vResult = new ArrayList();
            while (!cStart.after(cEnd))
            {
                strMonday = getMondayOfTheWeek(initDate);
                strSunday = calDays(strMonday, 6);
                cStart.set(Integer.parseInt(strMonday.substring(0,4)), Integer.parseInt(strMonday.substring(4,6)) - 1, Integer.parseInt(strMonday.substring(6,8)));

                strStartWeekday = maxDate(strMonday, startDate); // 시작일
                strEndWeekday = minDate(strSunday, endDate); // 종료일
                vResult.add(formatDateString(strStartWeekday, DT_FORMAT_YYYYMMDD, strFormat) + separator + formatDateString(strEndWeekday, DT_FORMAT_YYYYMMDD, strFormat));

                cStart.add(Calendar.DATE, 7); // 일주일을 더한다.
                initDate = cStart.get(Calendar.YEAR) + StringUtil.leftPad(cStart.get(Calendar.MONTH)+1, 2, "0") + StringUtil.leftPad(cStart.get(Calendar.DATE), 2, "0");
            }
        }
        catch (Exception e)
        {
            throw new Exception("DateUtil.getWeeks -(Correct format - yyyyMMdd) : " + strFormat, e);
        }
        return vResult;
    }

    /**
     * 해당 기간내의 지정된 월들을 리턴한다. (yyyyMM타입)
     *
     * @param startDate 시작일자
     * @param endDate 종료일자
     *
     * @return 해당 기간내의 지정된 년월 리스트  
     *
     * @throws Exception the run util exception
     */
    public static List getMonths(String startDate, String endDate) throws Exception
    {
        return getMonths(startDate, endDate, null);
    }

    /**
     * 해당 기간내의 지정된 월들을 리턴한다.
     *
     * @param startDate 시작일자
     * @param endDate 종료일자
     * @param format 리턴받을 년월에 대한 포맷
     *
     * @return 해당 기간내의 지정된 년월 리스트  
     *
     * @throws Exception the run util exception
     */
    public static List getMonths(String startDate, String endDate, String format) throws Exception
    {
        List vResult = null;
        String strFormat = format;
        try
        {
            Calendar cStart = Calendar.getInstance();
            cStart.set(Integer.parseInt(startDate.substring(0,4)), Integer.parseInt(startDate.substring(4,6)) - 1, 1);
            Calendar cEnd = Calendar.getInstance();
            cEnd.set(Integer.parseInt(endDate.substring(0,4)), Integer.parseInt(endDate.substring(4,6)) - 1, 1);
            String month = null;
            String year = null;
            vResult = new ArrayList();
            if (strFormat == null)
            {
                strFormat = "yyyyMM";
            }
            while (!cStart.after(cEnd))
            {
                year = Integer.toString(cStart.get(Calendar.YEAR));
                month = StringUtil.leftPad(cStart.get(Calendar.MONTH) + 1, 2, "0");
                if (!vResult.contains(month))
                {
                    vResult.add(DateUtil.formatDateString(year + month, "yyyyMM", strFormat));
                }
                cStart.add(Calendar.MONTH, 1); // 한달을 더한
            }
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            throw new Exception("DateUtil.getMonths ", e);
        }
        return vResult;
    }

    /**
     * <pre>
     * 해당 기간내의 지정된 요일의 날짜들을 리턴한다.
     * </pre>
     *
     * @param startDate yyyyMMdd 형식
     * @param endDate yyyyMMdd 형
     * @param week the week
     * @param format 리턴받을 날짜에 대한 포맷
     *
     * @return ArrayList String 해당 기간내의 지정된 요일의 날짜 리스트
     *
     * @author dhheo
     */
    public static List getAllWeekDate(String startDate, String endDate, String format, int week)
    {
        ArrayList vResult = null;
        String strFormat = format;
        try
        {
            Calendar cStart = Calendar.getInstance();

            cStart.set(Integer.parseInt(startDate.substring(0,4)), Integer.parseInt(startDate.substring(4,6), 10) - 1, Integer.parseInt(startDate.substring(6,8), 10));
            Calendar cEnd = Calendar.getInstance();
            cEnd.set(Integer.parseInt(endDate.substring(0,4)), Integer.parseInt(endDate.substring(4,6), 10) - 1, Integer.parseInt(endDate.substring(6,8), 10));
            int iStartWeek = cStart.get (Calendar.DAY_OF_WEEK);       //1-일, 2- 월...
            //int iEndWeek = cEnd.get (Calendar.DAY_OF_WEEK);       //1-일, 2- 월...
            vResult = new ArrayList();
            if (strFormat == null)
            {
                strFormat = DT_FORMAT_YYYYMMDD;
            }

            cStart.add(Calendar.DATE, (7 + week - iStartWeek) % 7);

            //최대 100번만 가능하도록 한다. 대략 2년정도
            int iMaxCount = 100;
            int i = 0;

            for (;!cStart.after(cEnd);cStart.add(Calendar.DATE, 7))
            {
                vResult.add(DateFormatUtils.format(cStart.getTime(), strFormat));
                if (i++ > iMaxCount) { // NOPMD by yshwang on 13. 11. 5 오후 1:57
					break;
				}
            }
        }
        catch (Exception e)
        {
            vResult = null;
        }
        return vResult;
    }

    /**
     * 주어진 초(second)를 hh:mi:ss 패턴의 문자열로 바꿔주는 메소드.
     *
     * @param lSec 주어진 초(second).
     * @return hh:mi:ss 패턴으로 변경된 문자열.
     */
    public static String toTimeString(long lSec)
    {
        long lSecond = Math.abs(lSec % 60L);
        long lTemp = lSec / 60L;
        long lMinute = Math.abs(lTemp % 60L);
        long lHour = lTemp / 60L;

        return new StringBuffer(StringUtil.leftPad(Long.toString(lHour), 2, '0'))
                   .append(":")
                   .append(StringUtil.leftPad(Long.toString(lMinute), 2, '0'))
                   .append(":")
                   .append(StringUtil.leftPad(Long.toString(lSecond), 2, '0')).toString();
    }
    /**
     * 주어진 초(second)를 dd-hh:mi:ss 패턴의 문자열로 바꿔주는 메소드.
     *
     * @param lSec 주어진 초(second).
     * @return hh:mi:ss 패턴으로 변경된 문자열.
     */
    public static String toDayAndTimeString(long lSec)
    {
        String timeString = "";
        long lSecond = Math.abs(lSec % 60L);
        long lTemp = lSec / 60L;
        long lMinute = Math.abs(lTemp % 60L);
        long lTempHour = lTemp / 60L;
        long lHour = Math.abs(lTempHour % 24L);
        long lDay = lTempHour / 24L;
        
        if(lDay > 0)
        {
            timeString = new StringBuffer(Long.toString(lDay))
            .append("day ")
            .append(StringUtil.leftPad(Long.toString(lHour), 2, '0'))
            .append(":")
            .append(StringUtil.leftPad(Long.toString(lMinute), 2, '0'))
            .append(":")
            .append(StringUtil.leftPad(Long.toString(lSecond), 2, '0')).toString();
        }
        else
        {
            timeString = new StringBuffer(StringUtil.leftPad(Long.toString(lHour), 2, '0'))
            .append(":")
            .append(StringUtil.leftPad(Long.toString(lMinute), 2, '0'))
            .append(":")
            .append(StringUtil.leftPad(Long.toString(lSecond), 2, '0')).toString();
        }
        
        
        return timeString;
    }
    
    /**
     * 주어진 분(minute)를 hh:mi 패턴의 문자열로 바꿔주는 메소드.
     *
     * @param lMin 주어진 분(minute).
     * @return hh:mi 패턴으로 변경된 문자열.
     */
    public static String toTimeMinString(long lMin)
    {
        return toTimeMinString(lMin, ":", "", true);
    } 
    
    /**
     * 주어진 분(minute)를 hh(separatorHour)mi(separatorMin) 패턴의 문자열로 바꿔주는 메소드.
     *
     * @param lMin 주어진 분(minute).
     * @return hh(separatorHour)mi(separatorMin) 패턴으로 변경된 문자열.
     */
    public static String toTimeMinString(long lMin, String separatorHour, String separatorMin, boolean isFormat)
    {
        long lMinute = Math.abs(lMin % 60L);
        long lHour = lMin / 60L;

        StringBuffer retBuffer = new StringBuffer();
        if( isFormat )
        {
            retBuffer.append(StringUtil.leftPad(Long.toString(lHour), 2, '0'))
                 .append(separatorHour)
                 .append(StringUtil.leftPad(Long.toString(lMinute), 2, '0'))
                 .append(separatorMin).toString();
        }
        else
        {
            if( lHour>0 ) {
				retBuffer.append(lHour).append(separatorHour);
			}
            
            if( lMinute>0 ) {
				retBuffer.append(lMinute).append(separatorMin).toString();
			}            
        }
        
        return retBuffer.toString();
    }    

    /**
     * 현재날짜의 분기를 구하는 메소드이다.
     * @return
     */
    public static int getCurrQuarter()
    {
    	return getQuarter(getCurrDate(DT_FORMAT_YYYYMMDD));
    }
    
    /**
     * 날짜를 받아서 분기를 구하는 메소드이다.
     * @param dt
     * @return
     */    
    public static int getQuarter(String dt)
    {
    	int iResult = -1;
    	try
    	{
	    	int month = Integer.parseInt(dt.substring(4, 6));
	    	if (month > 0 && month <= 3) {
				iResult = 1;
			} else if (month > 3 && month <= 6) {
				iResult = 2;
			} else if (month > 6 && month <= 9) {
				iResult = 3;
			} else if (month > 9 && month <= 12) {
				iResult = 4;
			}	    	
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	return iResult;
    }
    
    /**
     * 특정날짜의 분기별 시작/종료 일자를 가져온다.
     * @param dt
     * @return
     */
    public static String [] getQuarterStartEndDate(String dt)
    {
    	String [] arResult = new String[2];
    	try
    	{
	    	String startDate = null;
	    	String endDate = null;
	    	int quarter = getQuarter(dt);
	    	switch (quarter)
	    	{
	    		case 1 :
	    			startDate = dt.substring(0, 4) + "0101";
	    			endDate = dt.substring(0, 4) + "0331";
	    			break;
	    		case 2 :
	    			startDate = dt.substring(0, 4) + "0401";
	    			endDate = dt.substring(0, 4) + "0630";
	    			break;
	    		case 3 :
	    			startDate = dt.substring(0, 4) + "0701";
	    			endDate = dt.substring(0, 4) + "0930";
	    			break;
	    		case 4 :
	    			startDate = dt.substring(0, 4) + "1001";
	    			endDate = dt.substring(0, 4) + "1231";
	    			break;
	    		default:
	    	}	    	
	    	arResult[0] = startDate;
	    	arResult[1] = endDate;
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	return arResult;
    }
    /**
     * 주어진 날짜/시간에 대해 checkDate(체크할 시간)가 시작과 종료 날짜/시간 내에 들어오지를 체크한다.
     * 디폴트 날짜 포맷은 "yyyyMMdd"를 기준으로 한다.
     * 
     * @param startDate 시작시간
     * @param endDate 종료시간
     * @param checkDate 체크할 시간
     * @return 체크할 시간이 시작과 종료시간내에 있을 경우 true를 아니면, false를 리턴한다.
     * @throws Exception
     */
    public static boolean isIncludeDate(String startDate, String endDate, String checkDate) throws Exception
    {
        return isIncludeDate(startDate, endDate, checkDate, DT_FORMAT_YYYYMMDD);
    }
    /**
     * 주어진 날짜/시간에 대해 checkDate(체크할 시간)가 시작과 종료 날짜/시간 내에 들어오지를 체크한다.
     * 디폴트 날짜 포맷은 "yyyyMMddHHmmss"를 기준으로 한다.
     *  
     * @param startDate 시작시간
     * @param endDate 종료시간
     * @param checkDate 체크할 시간
     * @return 체크할 시간이 시작과 종료시간내에 있을 경우 true를 아니면, false를 리턴한다.
     * @throws Exception
     */
    public static boolean isIncludeTimeStamp(String startDate, String endDate, String checkDate) throws Exception
    {
        return isIncludeDate(startDate, endDate, checkDate, DT_FORMAT_YYYYMMDDHHMMSS);
    }
    /**
     * 주어진 날짜/시간에 대해 checkDate(체크할 시간)가 시작과 종료 날짜/시간 내에 들어오지를 체크한다.
     * 
     * @param startDate 시작시간
     * @param endDate 종료시간
     * @param checkDate 체크할 시간
     * @param format 날짜 포맷
     * @return 체크할 시간이 시작과 종료시간내에 있을 경우 true를 아니면, false를 리턴한다.
     * @throws Exception
     */
    public static boolean isIncludeDate(String startDate, String endDate, String checkDate, String format) throws Exception
    {
        StringBuffer sbError = null;
        try
        {
            long lStartDt = getDateFromString(startDate, format).getTime();
            long lEndDt = getDateFromString(endDate, format).getTime();
            long lCheckDt = getDateFromString(checkDate, format).getTime();

            return  lStartDt <= lCheckDt && lEndDt >= lCheckDt;
        }
        catch(Exception e)
        {
            sbError = new StringBuffer().append("DateUtil.isIncludeDate - (input format - ").append(format)
                                        .append(") : ").append(startDate).append(",").append(endDate).append(",").append(checkDate);

            throw new Exception(sbError.toString(), e);
        }
        finally
        {
            sbError = null;
        }        
    }
    
    /**
     * 
     * 데이터의 유효기간에 대한 체크를 하는 함수이다.
     * 주어진 날짜/시간에 대해 checkDate(체크할 시간)가 시작과 종료 날짜/시간 내에 들어오지를 체크하며
     * 시작 날짜/시간 NULL인 경우에는 20000101000000으로 , 종료 날짜/시간이 없는 경우에는 230001231235959로 셋팅한다.
     * 날짜 포맷이 맞지않아 에러가 나는 경우에는 true로 리턴한다.
     * 
     * System.out.println(isValidDate("","","20130321192327"));  => true
     * System.out.println(isValidDate("20130330000000","","20130321192327")); => false
     * System.out.println(isValidDate("","20130320235959","20130321192327")); => false
     * 
     * @param paramFromDate 시작시간 "yyyyMMddHHmmss"
     * @param paramToDate 종료시간 "yyyyMMddHHmmss"
     * @param checkDate 체크할 시간 "yyyyMMddHHmmss"
     * @return 체크할 시간이 시작과 종료시간내에 있을 경우 true를 아니면, false를 리턴한다.
     */
    public static boolean isValidTermDate(String paramFromDate, String paramToDate, String checkDate)
    {
        boolean bRet = true;
        try
        {
            String fromDate = paramFromDate;
            String toDate = paramToDate;
            if( fromDate == null || "".equals(fromDate) || "00000000000000".equals(fromDate) )
            {
                fromDate = "20000101000000";
            }
            
            if( toDate == null || "".equals(toDate) || "99999999999999".equals(toDate) )
            {
                toDate = "230001231235959";
            }                
            
            bRet = DateUtil.isIncludeTimeStamp(fromDate, toDate, checkDate);
        }
        catch(Exception rue)
        {
            bRet = true; // 로그 기록하지 않음.
        }
        return bRet;
    }
    
    /**
     * 시간값을 넣으면 format에 따라 일/시간 단위로 나누어 보여준다.
     * ex)
     *  DateUtil.getDayTimeFormat(23, "일", "시간") -> 23시간
     *  DateUtil.getDayTimeFormat(24, "일", "시간") -> 1일
     *  DateUtil.getDayTimeFormat(27, "일", "시간") -> 1일 3시간
     *  DateUtil.getDayTimeFormat(0, "일", "시간")  -> 0일
     * @param paramHour
     * @param dayStr
     * @param timeStr
     * @return
     */
    public static String getDayTimeFormat(long paramHour, String dayStr, String timeStr)
    {
        StringBuffer sb = new StringBuffer();
        Integer[] times = new Integer[]{(int)(paramHour / 24), (int)(paramHour % 24)};
        String[] formats = new String[]{dayStr, timeStr};
        for (int i=0 ; i<times.length ; i++)
        {
            if (times[i]<=0)
            {
                continue;
            }
            sb.append(times[i]).append(formats[i]).append(" ");
        }
        return StringUtil.isEmpty(sb.toString())?"0"+dayStr:sb.toString().trim();
    }
}