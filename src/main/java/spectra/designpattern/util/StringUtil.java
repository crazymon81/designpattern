package spectra.designpattern.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

/**
 * String(문자열) 조작 및 관련 데이터의 컨버젼 기능을 가진다.
 *
 * @author kspark
 */
public class StringUtil extends StringUtils
{	
    /** 스크립트 구분자 시작문자열 : [% */
    protected static final String VAR_BEGIN = "[%";

    /** 스크립트 구분자 종료문자열 : %] */
    protected static final String VAR_END = "%]";

    /**
    * 주어진 문자 배열에 대해 각 배열값 사이에 주어진 append값을 삽입하여<br>
    * String 문자열로 리턴하는 메소드(문자열이 null이거나 ""이면 "" 리턴).
    *
    * @param strArr String 배열
    * @param appendStr 각 배열값 사이에 넣게될 문자열
    *
    * @return 문자열
    *
    * @deprecated Jakarta Commons Lang 2.0 - StringUtils.join 메소드를 사용. 즉, StringUtil.join 을 사용
    */
    public static String putString(String[] strArr, String appendStr)
    {
        if(strArr==null) {
			return "";
		}
        StringBuffer sb = new StringBuffer("");

        for(int i=0; i<strArr.length; i++)
        {
            if( strArr[i]==null) {
				strArr[i] = "";
			}

            sb.append(strArr[i]);
            if(i<strArr.length-1) {
				sb.append(appendStr);
			}
        }
        return sb.toString();
    }

    /**
    * 주어진 문자 배열에 대해 각 배열값 사이에 "-"를 삽입하여<br>
    * String 문자열로 리턴하는 메소드(문자열이 null이거나 ""이면 "" 리턴).
    *
    * @param strArr String 배열
    *
    * @return "-"로 연결된 문자열
    */
    public static String putDash(String[] strArr)
    {
        return join(strArr, '-');
    }

    /**
    * delimit 단위로 Token을 실시하며 <br>
    * delimit단위로 값이 존재하지 않더라도 빈문자열을 포함시켜 ArrayList를 리턴한다.
    *
    * @param str token하게될 문자열
    * @param delimit delimiter
    *
    * @return 토큰목록 array list
    */
    public static ArrayList tokenizerArrayList(String str, String delimit) // NOPMD by yshwang on 13. 11. 11 오후 3:33
    {
        int idx = 0, i = 0;
        ArrayList vret = new ArrayList();
        String value = str;
        while(true)
        {
            if((idx=value.indexOf(delimit)) != -1) // NOPMD by yshwang on 13. 11. 11 오후 3:33
            {
                vret.add(i, value.substring(0, idx));
                value = value.substring(idx+delimit.length(), value.length());
            }
            else
            {
                vret.add(i, value);
                break;
            }
            i++;
        }
        return vret;
    }

    /**
    * delimit 단위로 Token을 실시하며 <br>
    * delimit단위로 값이 존재하지 않더라도 빈문자열을 포함여부에 따라 String[] 를 리턴한다.
    *
    * @param value token하게될 문자열
    * @param delimit delimiter
    * @param isIncludeEmptyString 빈문자열 포함 여부 True인 경우 포함시킨다.
    *
    * @return String [] delimiter로 분리된 string array.
    */

    public static String [] split(String value, String delimit, boolean isIncludeEmptyString)
    {
        String [] aStrRet = null;
        if(isIncludeEmptyString)
        {
            ArrayList vRet = tokenizerArrayList(value, delimit);
            aStrRet = new String[vRet.size()];
            for(int i=0; i < vRet.size(); i++)
            {
                aStrRet[i] = (String)vRet.get(i);
            }
        }
        else
        {
            return split(value, delimit);
        }

        return aStrRet;
    }

    /**
    * delimit 단위로 Token을 실시하며 <br>
    * 리턴 타입에 따라 Hashtable의 key,value 값에 값을 넣어 리턴한다.<br>
    * 리턴 타입으로 0일 경우 key, 1일 경우 value, 2일 경우 key,value 모두에 값을 넣는다.<br>
    * 0,1일 경우 다른 key,value에는 배열의 인덱스를 넣어준다.
    *
    * @param value the value
    * @param delimit the delimit
    * @param iRetType 리턴 타입으로 0일 경우 key, 1일 경우 value, 2일 경우 key,value 모두에 값을 넣는다.
    *
    * @return Hashtable delimiter로 분리된 string을 담은 Hashtable 
    *         (리턴 타입으로 0일 경우 key, 1일 경우 value, 2일 경우 key,value 모두에 값을 넣는다)
    */
    public static Hashtable splitToHashtable(String value, String delimit, int iRetType) // NOPMD by yshwang on 13. 11. 11 오후 3:33
    {
        Hashtable htRet = new Hashtable();
        String [] aStrTemp = split(value, delimit);
        for(int i=0; i < aStrTemp.length; i++ )
        {
            switch(iRetType)
            {
                case 0 :
                    htRet.put(aStrTemp[i], i+""); // NOPMD by yshwang on 13. 11. 11 오후 3:33
                    break;
                case 1 :
                    htRet.put(i+"", aStrTemp[i]); // NOPMD by yshwang on 13. 11. 11 오후 3:33
                    break;
                case 2:
                    htRet.put(aStrTemp[i], aStrTemp[i]);
                    break;
                default :
                    htRet.put(aStrTemp[i], "");
            }
        }
        return htRet;
    }

    /**
    * delimit 단위로 Token을 실시하며 <br>
    * Hashtable의 key,value 값에 값을 넣어 리턴한다.<br>
    * 문자열은 key값에, value에는 배열의 인덱스를 넣어준다.
    *
    * @param value delimiter로 연결된 문자열
    * @param delimit delimiter
    *
    * @return Hashtable delimiter로 구분된 string 목록의 Hashtable.
    *         [token,index(0 based)] 형식
    */
    public static Hashtable splitToHashtable(String value, String delimit) // NOPMD by yshwang on 13. 11. 11 오후 3:33
    {
        return splitToHashtable(value, delimit, 0);
    }

    /**
    * SQL문에서 varchar2, char type일경우 [columName = 'columnValue']처럼<br>
    * columnValue에 Single Quote가 붙는다. 주어진 값의 양쪽에 Single Quote를 붙여서 리턴한다.
    *
    * @param str Single Quote를 붙일 문자열
    *
    * @return Single Quote를 붙인 문자열, 만약 null 값이거나 ""인 경우에는 "''"를 리턴한다.
    */
    public static String renderSQuote(String str)
    {
        if( str == null || "".equals(str)) {
			return "''";
		}
        return "'" + str + "'";
    }

    /**
    * html코드가 적용되지 않는 문장으로 데이터베이스에 넣기위해<br>
    * &lt; 를 &amp;lt; 로, &gt; 를 &amp;gt; 로, &quot; 를 &amp;quot; 로 변환시켜 리턴한다.<br>
    *
    * @param str 변환할 문자열
    *
    * @return 변환된 문자열
    *
    * @deprecated Jakarta Commons Lang 2.0 - StringEscapeUtils.escapeHtml 메소드를 사용
    */
    public static String changeHtmlTag(String str)
    {
        return StringEscapeUtils.escapeHtml(str);
    }

    /**
    * 주어진 a tag에 target을 추가함.<br>
    *
    * @param str 변환할 문자열
    * @param target 교체할 target 문자열
    *
    * @return String 변환된 문자열
    */
    public static String changeHtmlLinkTarget(String str, String target)
    {
        if(str == null) {
			return "";
		}
        String reStr = str.trim();
        String strTarget = target;
        
        if(strTarget == null || strTarget.equals("")) {
			strTarget = "_blank";
		}

        return replaceAll(reStr, "<A", "<A target='"+strTarget+"'");
    }
    
    /**
     * 문자열중에  http 링크가 있으면 <a 태그로 감싼다.
     * 
     * @param html 원문 html 문자열
     * @param target 교체할 target 문자열
     * 
     * @return http:// 태그가 <a target=''></a> 태그로 감싸진 결과
     */
    public static String anchorHtmlLinkTarget(String html, String target)
    {
        if(html == null) {
			return "";
		}
        
        String strHtml = html;
        String strTarget = target;
        
        if(strTarget == null || strTarget.equals("")) {
			strTarget = "_blank";
		}
        
        String regex = "([[a-zA-Z0-9]]+)://([a-z0-9.\\-&%=?:@#~\\_/]+)";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(strHtml);
        
        return m.replaceAll("<a href='http://$2' target='"+strTarget+"'>http://$2</a>");
    }
    
    /**
     * 문자열중에  공백 + http 링크가 있으면 <a 태그로 감싼다.
     * 입력파라미터에 <a href='http://xxx.xxx.com'>xxx</a> 와 같이 존재할 경우 제외하고 태그를 넣는다.
     * 
     * @param html 원문 html 문자열
     * @param target 교체할 target 문자열
     * 
     * @return http:// 태그가 <a target=''></a> 태그로 감싸진 결과
     */
    public static String anchorHtmlLinkTargetBeginWithBlank(String html, String target)
    {
        if(html == null) {
			return "";
		}
        
        String strHtml = html;
        String strTarget = target;
        
        if(strHtml.startsWith("http://")) {
			strHtml = " " + strHtml; // NOPMD by yshwang on 13. 11. 11 오후 3:33
		}
        
        if(strTarget == null || strTarget.equals("")) {
			strTarget = "_blank";
		}
        
        String regex = " ([[a-zA-Z0-9]]+)://([a-z0-9.\\-&%=?:@#~\\_/]+)";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(strHtml);
        return m.replaceAll("<a href='http://$2' target='"+strTarget+"'>http://$2</a>");
    }
    

    /**
    * 대소문자 구분없이 주어진 문자열에서 old String에 해당하는 문자를 new String값으로 대체해서 리턴한다.
    *
    * @param string 변환하고자하는 문자열
    * @param oldString 대체하고자 하는 문자열
    * @param newString 대체할 하는 문자열
    *
    * @return String 변환된 문자열
    */
    public static String replaceAll(String string, String oldString, String newString)
    {
    	try
    	{
	        if(string == null || oldString == null || newString == null )
	        {
	            return null;
	        }
	        
	        if (isEmpty(oldString)) {
	            return string;
	        }

	        String str = string;
	        String uppperOldString = oldString.toUpperCase();
	        String upperStr = str.toUpperCase();
	        String realStr = "";
	        int idx = -1;
	        int fromIdx = 0;
	
	        while( (idx = upperStr.indexOf(uppperOldString , fromIdx)) != -1 ) // NOPMD by yshwang on 13. 11. 11 오후 3:33
	        {
	            realStr = str.substring(idx, idx+oldString.length());
	            realStr = realStr.toUpperCase();
	            str = str.substring(0, idx) + realStr + str.substring(idx+oldString.length(), str.length());
	            fromIdx = idx+oldString.length();
	        }
	
	        return replace(str, uppperOldString, newString);
    	}
    	catch(Exception e)
        {
    		//e.printStackTrace();
    		return string;
        }        
    }

    /**
    * Single Quote(') --> '' 로 변환하여 리턴한다.
    *
    * @param str 변환할 문자열
    *
    * @return String 변환된 문자열
    *
    * @deprecated Jakarta Commons Lang 2.0 - StringEscapeUtils.escapeSql 메소드를 사용
    */
    public static String  convertSQuoteForSQL(String str)
    {
        return StringEscapeUtils.escapeSql(str);
    }

    /**
    * Double Quote(") --> \" 로 변환하여 리턴한다.
    *
    * @param str 변환할 문자열
    *
    * @return String 변환된 문자열
    */
    public static String  convertDQuote(String str)
    {
        StringBuffer sb = new StringBuffer(str);
        int len = sb.length();
        for( int i = 0; i < len; i++)
        {
            if(sb.charAt(i) == '"') {
				sb.insert( i, '\\');
			}
        }
        return sb.toString();
    }

    /**
    * 오른쪽으로 부터 길이를 계산하여 문자열을 잘라낸 값을 리턴한다.
    *
    * @param str 문자열
    * @param i 절삭할 길이.
    *
    * @return 절삭된 문자열
    *
    * @deprecated Jakarta Commons Lang 2.0 - StringUtils.right 메소드를 사용, 즉, StringUtil.right
    */
    public static String getRSubstring(String str, int i)
    {
        return right(str, i);
    }

    /**
    * 오라클의 decode함수와 동일한 기능을 가진 메소드이다.<br>
    * 값을 비교하여 비교값과 같다면 cmpValue값으로 반환한다.
    *
    * @param strValue 비교대상이 되는 값
    * @param cmpValue 비교할 값.
    * @param decodeValue value와 cmpValue값이 동일할때 이 값을 리턴한다..
    * @param diffValue 같지안을 때 반환할 값.
    *
    * @return value와 cmpValue값이 동일할때 decodeValue값으로 아니면 diffValue값으로 반환된다.
    */
    public static String decode (String strValue, String cmpValue, String decodeValue, String diffValue)
    {
        String value = defaultString(strValue, "");

        if (value.trim().equals(cmpValue)) {
			return decodeValue;
		}

        return diffValue;
    }

    /**
    * 오라클의 decode함수와 동일한 기능을 가진 메소드이다.<br>
    * 값을 비교하여 비교값과 같다면 cmpValue값으로 반환한다.
    *
    * @param value 비교대상이 되는 값
    * @param cmpValue 비교할 값.
    * @param decodeValue value와 cmpValue값이 동일할때 이 값을 리턴한다..
    *
    * @return value와 cmpValue값이 동일할때 decodeValue값으로 아니면 value값으로 반환된다.
    */
    public static String decode (String value, String cmpValue, String decodeValue)
    {
        return decode (value, cmpValue, decodeValue, value);
    }

    /**
    * 주어진 문자열에서 (char)29, (char)30, (char)31을 다른 문자로 대체해서 리턴한다.
    *
    * @param str 변환하고자하는 문자열
    * @param replaceStr 대체할 하는 문자열
    *
    * @return (char)29, (char)30, (char)31 구분자를 replaceStr로 변경한 string.
    */
    public static String replaceDelimiterChar(String str, String replaceStr)
    {
        //char[] removeChar = {(char)30, (char)29, (char)31};
        StringBuffer sb = new StringBuffer(str);
        //int len = sb.length();
        for(int i=0; i<str.length(); i++)
        {
            switch(sb.charAt(i))
            {
                case (char)30:
                case (char)29:
                case (char)31:
                    sb.replace(i, i+1, replaceStr);
                    break;
                default:    
            }

        }
        return sb.toString();
    }

    /**
    * <pre>
    * 주어진 문자열에서 정규식으로 HTML을 제거한 문자만 리턴한다.
    * JSDK 1.4.x 버전이상에서 지원되는 String.replaceAll 메소드를 사용하고 있음
    * VM 버전이 1.4.x 이하일 경우에 이 메소드 사용시 에러가 발생함.
    *
    * @param str 변환하고자하는 문자열
    *
    * @return html tag가 제거된 문자열.
    *
    * @throws Exception the run util exception
    */
    public static String removeHtmlTag(String str) throws Exception
    {
        try
        {
            if( isEmpty(str) ) {
				return "";
			} else {
				return str.replaceAll("<(/)?([a-zA-Z0-9]*)(\\s[a-zA-Z0-9\\-\\:]*=[^>]*)?(\\s)*(/)?>","");
			}
        }
        catch(java.lang.NoSuchMethodError e)
        {
            throw new Exception("StringUtil.removeHtmlTag Method Error!! (Support JVM 1.4.x)"); // NOPMD by yshwang on 13. 11. 11 오후 3:33
        }
    }

    /**
    * 주어진 문자열에서 정규식으로 <A> 태그만 제거한 문자를 리턴한다.
    *
    * @param str 변환하고자하는 문자열
    *
    * @return &lt;A&gt; tag가 제거된 문자열
    *
    * @throws Exception the run util exception
    */
    public static String removeHtmlATag(String str)  throws Exception
    {
        try
        {
            if( isEmpty(str) ) {
				return "";
			} else {
				return str.replaceAll("(?:<\\s*[a|A]\\s*(?:[^>'\"]*|\".*?\"|'.*?')+>)|(?:<\\s*/[a|A]\\s*>)","");
			}
        }
        catch(java.lang.NoSuchMethodError e)
        {
            throw new Exception("StringUtil.removeHtmlATag Method Error!! (Support JVM 1.4.x)"); // NOPMD by yshwang on 13. 11. 11 오후 3:33
        }
    }

    /**
    * 주어진 문자열을 주어진 Delimiter로 분리하여 String으로 리턴한다.
    *
    * @param str token하게될 문자열
    * @param delimit Delimiter
    *
    * @return String Delimiter가 제거된 문자열
    */
    public static String splitToString(String str, String delimit)
    {
        StringTokenizer stk = new StringTokenizer(str, delimit);
        StringBuffer sb = new StringBuffer();
        while(stk.hasMoreTokens())
        {
            sb.append(stk.nextToken());
        }
        return sb.toString();
    }

    /**
     * 내용을 4000 자 이하로 잘라 배열로 반환하는 함수<BR>
     * Contents 관련 테이블이 모두 4000 자 이상은 Insert 가 금지되어 있기때문에<BR>
     * DB에 인서트 하기 위해서는 4000자 이하로 잘라서 Insert 를 해야 한다.<BR>
     *
     * @param src 원래 본문
     * @param iLength 1 string당 자를 길이
     *
     * @return 4000자 이하로 잘린 String 의 배열
     */
    public static String[] splitContents(String src, int iLength)
    {
        String buf = src;
        int len = buf.length();
        int count = len / iLength;
        int imod = len % iLength;

        if (imod > 0 || len == 0) {
			count++;
		}
        String[] out = new String[count];

        for (int i = 0; i < (count - 1); i++)
        {
            out[i] = buf.substring(0, iLength);
            buf = buf.substring(iLength);
        }
        out[count - 1] = buf;
        return out;
    }

    /**
    * 주어진 문자가 null이면 defaultValue를 리턴함.
    *
    * @param src 문자열
    * @param defaultValue src가 null일때 리턴할 문자.
    *
    * @return 주어진 문자가 null이면 defaultValue를 리턴. 아니면 주어진 문자열을 리턴.
    *
    * @deprecated defaultString(str, defaultValue) 메소드로 변경하세요.
    */
    public static String nvl(String src, String defaultValue)
    {
        return defaultString(src, defaultValue);
    }

    /**
    * 주어진 문자가 null이면 "" 리턴함.
    *
    * @param src 문자열
    *
    * @return 주어진 문자가 null이면 ""를 리턴. 아니면 주어진 문자열을 리턴.
    *
    * @deprecated defaultString(str) 메소드로 변경하세요.
    */
    public static String nvl(String src)
    {
        return defaultString(src);
    }

    /**
     * 주어진 문자가 null이면 "" 리턴함.
     *
     * @param str 문자열
     *
     * @return 주어진 문자가 null이면 ""를 리턴. 아니면 주어진 문자열을 리턴.
     */
    public static String defaultIfBlank(String str)
    {
        return isBlank(str) ? "" : str;
    }

    /**
     *  주어진 문자가 null이면 defaultValue를 리턴함.
     *
     * @param str 문자열
     * @param defaultStr  str이 null일때 리턴할 문자.
     *
     * @return 주어진 문자가 null이면 defaultValue를 리턴. 아니면 주어진 문자열을 리턴.
     */
    public static String defaultIfBlank(String str, String defaultStr)
    {
        return isBlank(str) ? defaultStr : str;
    }

    /**
     * Left pad.
     *
     * @param iSrc source number to pad.
     * @param size the size of total digits.
     * @param paddStr the pad string
     *
     * @return lpad된 string.
     */
    public static String leftPad(int iSrc, int size, String paddStr)
    { 
        String strRet = iSrc + ""; // NOPMD by yshwang on 13. 11. 11 오후 3:33

        if( iSrc < Math.pow(10, size-1) )
        {
            strRet = leftPad(strRet, size, paddStr);
        }
        return strRet;
    }

    /**
     * checks if the array contains the string.
     *
     * @param arrString array of string
     * @param str the string to find from the array.
     *
     * @return true, if successful
     */
    public static boolean contains(String [] arrString, String str)
    {
        boolean bRet = false;
        for( int i=0; i < arrString.length; i++ )
        {
            if( arrString[i].equals(str) )
            {
                bRet = true;
                break;
            }
        }

        return bRet;
    }

    /**
     * script tag를 주석처리함.
     *
     * (?im)는 대소문자를 구분하지 않는 옵션.
     *
     * @param str script tag가 포함된 html string.
     * @param onlyScript true이면, script tag만 주석처리함. 아니면, object, embed, iframe도 주석처리함.
     *
     * @return 처리된 string.
     */
    public static String changeScripts(String str, boolean onlyScript)
    {
        String contents = str;
        contents = contents.replaceAll("(?im)<script", "<!-- script");
        contents = contents.replaceAll("(?im)/script>", "/script -->");

        if( onlyScript ) {
			return contents;
		}

        contents = contents.replaceAll("(?im)<object", "<!-- object");
        contents = contents.replaceAll("(?im)/object>", "/object -->");
        contents = contents.replaceAll("(?im)<embed", "<!-- embed");
        contents = contents.replaceAll("(?im)/embed>", "/embed -->");
        contents = contents.replaceAll("(?im)<iframe", "<!-- iframe");
        contents = contents.replaceAll("(?im)/iframe>", "/iframe -->");

        return contents;
    }

    /**
     * Checks if is not equal.
     *
     * @param str1 문자열1
     * @param str2 문자열2
     *
     * @return true, if is not equal
     */
    public static boolean isNotEqual(String str1, String str2)
    {
        return equals(str1, str2) ? false : true;
    }

    /**
     * Checks if is less than.
     *
     * @param str1 문자열1
     * @param str2 문자열2
     *
     * @return true, if is less than
     */
    public static boolean isLessThan(String str1, String str2)
    {
        return str1.compareTo(str2) < 0;
    }

    /**
     * Checks if is less than equal.
     *
     * @param str1 문자열1
     * @param str2 문자열2
     *
     * @return true, if is less than equal
     */
    public static boolean isLessThanEqual(String str1, String str2)
    {
        return str1.compareTo(str2) <= 0;
    }

    /**
     * Checks if is greater than.
     *
     * @param str1 문자열1
     * @param str2 문자열2
     *
     * @return true, if is greater than
     */
    public static boolean isGreaterThan(String str1, String str2)
    {
        return str1.compareTo(str2) > 0;
    }

    /**
     * Checks if is greater than equal.
     *
     * @param str1 문자열1
     * @param str2 문자열2
     *
     * @return true, if is greater than equal
     */
    public static boolean isGreaterThanEqual(String str1, String str2)
    {
        return str1.compareTo(str2) >= 0;
    }

    /**
     * 입력문자열을 제한된 바이트 크기만큼 제한된 횟수까지 리스트로 반환한다.
     * 영/한문이 섞인 문자열에서 특정바이트만큼 문자열을 추출할때 사용한다.
     *
     * @param src - 입력문자열
     * @param splitSize -Token의 최대 바이트 크기.
     * @param maxCount - 결과 리스트의 제한횟수.
     *
     * @return 잘린 Token의 목록 List<String>
     */
    public static List splitStringBySize(String src, int splitSize, int maxCount)
    {
        ArrayList retList = new ArrayList();

        int iUnitSize=0;
        int iStartIndex=0;
        char curr;
        int iSrcSize = src.length();
        for (int iCurrIndex=0; iCurrIndex < iSrcSize; iCurrIndex++)
        {
            // 현재 인덱스의 문자의 바이트 크기를 구한다.
            // 0x007F 보다 크면 한글 2바이트로 간주한다. 부정확한 방법이지만 국내용 MO 에 적용하기에는 충분하다.
            curr = src.charAt(iCurrIndex);

            if ((int) curr > 0x007F) {
				iUnitSize += 2;
			} else {
				iUnitSize++;
			}

            // 문자열의 마지막일때 리스트에 단위문자열을 추가한다.
            if (iCurrIndex == iSrcSize-1)
            {
                retList.add(src.substring(iStartIndex, iCurrIndex+1));
                break;
            }

            // 제한 크기에 다다랐을때 리스트에 단위문자열을 추가한다.
            if (iUnitSize >= splitSize)
            {
                retList.add(src.substring(iStartIndex, iCurrIndex+1));
                iStartIndex = iCurrIndex+1;
                iUnitSize = 0;

                if (retList.size() >= maxCount) {
					break;
				}
            }
            else if (iUnitSize == splitSize-1 && ((int) src.charAt(iCurrIndex+1)) > 0x007F)
            {
                // 다음문자가 2바이트 문자이고 현재 크기가 제한크기-1 이라면
                retList.add(src.substring(iStartIndex, iCurrIndex+1));
                iStartIndex = iCurrIndex+1;
                iUnitSize = 0;

                if (retList.size() >= maxCount) {
					break;
				}
            }
        }
        return retList;
    }
    
    /**
     * 입력문자열을 제한된 바이트 크기만큼 리스트로 반환한다.
     * 영/한문이 섞인 문자열에서 특정바이트만큼 문자열을 추출할때 사용한다.
     *
     * @param src - 입력문자열
     * @param splitSize -Token의 최대 바이트 크기.
     *
     * @return 잘린 Token의 목록 List<String>
     */
    public static List splitStringBySize(String src, int splitSize)
    {
        ArrayList retList = new ArrayList();

        int iUnitSize=0;
        int iStartIndex=0;
        char curr;
        int iSrcSize = src.length();
        for (int iCurrIndex=0; iCurrIndex < iSrcSize; iCurrIndex++)
        {
            // 현재 인덱스의 문자의 바이트 크기를 구한다.
            // 0x007F 보다 크면 한글 2바이트로 간주한다. 부정확한 방법이지만 국내용 MO 에 적용하기에는 충분하다.
            curr = src.charAt(iCurrIndex);

            if ((int) curr > 0x007F) {
                iUnitSize += 2;
            } else {
                iUnitSize++;
            }

            // 문자열의 마지막일때 리스트에 단위문자열을 추가한다.
            if (iCurrIndex == iSrcSize-1)
            {
                retList.add(src.substring(iStartIndex, iCurrIndex+1));
                break;
            }

            // 제한 크기에 다다랐을때 리스트에 단위문자열을 추가한다.
            if (iUnitSize >= splitSize)
            {
                retList.add(src.substring(iStartIndex, iCurrIndex+1));
                iStartIndex = iCurrIndex+1;
                iUnitSize = 0;
            }
            else if (iUnitSize == splitSize-1 && ((int) src.charAt(iCurrIndex+1)) > 0x007F)
            {
                // 다음문자가 2바이트 문자이고 현재 크기가 제한크기-1 이라면
                retList.add(src.substring(iStartIndex, iCurrIndex+1));
                iStartIndex = iCurrIndex+1;
                iUnitSize = 0;
            }
        }
        return retList;
    }

    /**
     * Ellipsis string.
     *
     * @param str the string to ellipse.
     * @param size the size to ellipse the string when the string exceeded the size.
     * @param ellipsis the ellipsis string to be add.
     *
     * @return ellipsis된 string
     */
    public static String ellipsis(String str, int size, String ellipsis)
    {
        return (StringUtil.length(str) <= size) ? str : str.substring(0, size) + ellipsis; // NOPMD by yshwang on 13. 11. 11 오후 3:33
    }

    /**
     * Regex replace. <br/>
     * 정규표현식으로 문자열의 특정 문자패턴을 찾아서 변경한다.
     *
     * @param regexPattern 정규 표현식 패턴
     * @param replacement 변경할 문자열
     * @param s 검사할 문자열
     * @param flag 매치 플래그,  CASE_INSENSITIVE, MULTILINE, DOTALL, UNICODE_CASE, CANON_EQ, UNIX_LINES, LITERAL, COMMENTS
     *
     * @return 변환된 문자열
     */
    public static String regexReplace(String regexPattern, String replacement, String s)
    {
        return regexReplace(regexPattern, replacement, s, 0);
    }
    
    public static String regexReplace(String regexPattern, String replacement, String s, int flag)
    {
        Pattern p = Pattern.compile(regexPattern, flag);
        Matcher m = p.matcher(s);
        return m.replaceAll(replacement);
    }
    
    /**
     * Sql Injection에 사용되는 ; -- < > 문자를 치환한다.
     * 웹에서 사용하는 파라미터 XSS 와 Sql Injection 체크를 위해서는 StringEscapeUtil.escapeXSS(String) method 를 사용하라.
     * 
     * @param szOrg   <code>String</code> to be translated.
     * @return  the translated <code>String</code>.
     */
    public static String blockSQLHack(String szOrg)
    {
        if (szOrg == null) { return null; }
        String szTarget = null;
        try
        {
            szTarget = szOrg;
            szTarget = szTarget.replaceAll(";","");
            szTarget = szTarget.replaceAll("--","");
            szTarget = szTarget.replaceAll("<","&lt;");
            szTarget = szTarget.replaceAll(">","&gt;");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return szTarget;
    }
    
    
    /**
     * 쿼리에 포함된 SQL스크립트를 파라미터맵을 참조하여 번역한다.
     *
     * @param query 쿼리.
     * @param paramMap 파라미터맵.
     * @return 번역된 쿼리.
     */
    public static String translateQuery(String query, Map paramMap)
    {
        String resultQuery = query;
        Set paramSet = paramMap.entrySet();
        Iterator i = paramSet.iterator();
        while(i.hasNext())
        {
            Map.Entry e = (Map.Entry) i.next();
            String key = (String) e.getKey();
            String value = (String) e.getValue();
            String variable = VAR_BEGIN + key + VAR_END;
            resultQuery = StringUtil.replaceAll(resultQuery, variable, value);
        }
        return resultQuery;
    }
    
    /**
     * 아래와 같은 문자열을 Map으로 변환한다.
     * 
     * key1:value2;key2:value2;key3:value3
     * 
     * @param str Map으로 변환할 대상 문자열
     * @param seperatorPairs 구분자
     * @param seperatorkeyValue 구분자
     * @return Map을 리턴한다.
     */
    public static Map<String, String> convertToMap(String str, char seperatorPairs, char seperatorkeyValue)
    {
        return convertToMap(str, String.valueOf(seperatorPairs), String.valueOf(seperatorkeyValue));
    }

    public static Map<String, String> convertToMap(String str, String seperatorPairs, String seperatorkeyValue)
    {
        if (str == null || seperatorPairs == null || seperatorkeyValue == null)
        {
            return null;
        }

        String[] pairs = StringUtil.split(str, seperatorPairs);
        if (pairs == null)
        {
            throw new IllegalArgumentException();
        }
         
        Map<String, String> map = new HashMap<String, String>();
        for (String pair: pairs)
        {
            String[] keyValue = StringUtil.split(pair, seperatorkeyValue);
            if (keyValue != null && keyValue.length == 2)
            {
                map.put(keyValue[0], keyValue[1]);    
            }
        }

        return map;
    }
    
    /**
     * 주어진 파일의 파일 디렉토리 경로를 알아온다.
     * 파일 디렉토리 보안을 위해 사용하는 메소드이므로 파일 유틸로 절대경로를 리턴 하는 것이 아니라,
     * File.separator만 변환해주고, .. 등의 문자는 제거하지 않는 문자 원형 그대로 리턴한다.
     * @param value 대상 문자열
     * @return String 리턴한다.
     */
    public static String getFileDirStr(String paramValue)
    {
        String value = paramValue;
        
        String strRet = null;
        if(value != null && !"".equals(value))
        {
            value = value.replaceAll("\\\\", "/");
            strRet = value.substring(0, value.lastIndexOf("/")); // NOPMD by yshwang on 13. 11. 11 오후 3:33
            if (isEmpty(strRet) || strRet.indexOf("/")==-1)
            {
                strRet = StringUtil.append(strRet,"/");
            }
            
            String fileSeparator = File.separator;
            if ("\\".equals(File.separator))
            {
                fileSeparator = "\\\\";
            }
            strRet = strRet.replaceAll("/", fileSeparator);
        }
        return strRet;
    }
    
    /**
     * 해당 OS에 맞는 폴더 구분자로 변경하여 반환한다.
     * @param src
     * @return
     */
    public static final String replaceFileSeparator(String src)
    {
        return src.replace("\\\\", File.separator).replace("/", File.separator);
    }
    
    /**
     * ibatis 다이나믹 쿼리를 사용할 경우.
     * oracle에서 IN절에 1000개이상(9i이후) 들어가면 에러가 발생하므로 특정갯수 만큼 잘라서 리턴한다.
     * @param strArr 문자열 배열.
     * @param sepCount 자를 갯수
     * @return
     */
    public static List<String[]> strArrayDivide(String[] strArr, int sepCount)
    {
        List<String[]> retlist = new ArrayList<String[]>();
        
        if(strArr != null && strArr.length > 0)
        {
            int length = strArr.length;
            int storeTemp = 0;

            while (length > 0)
            {
                if(length - storeTemp > sepCount)
                {
                    String[] dest = new String[sepCount];
                    System.arraycopy(strArr, 0+storeTemp, dest, 0, sepCount );
                    retlist.add(dest);
                    storeTemp = storeTemp + sepCount;
                }
                else
                {
                    String[] dest = new String[length-storeTemp];
                    System.arraycopy(strArr, 0+storeTemp, dest, 0, length-storeTemp );

                    retlist.add(dest);
                    break;
                }
            }
        }
        return retlist;
    }

    public static boolean validationId(String Source, String pattern, int length)
    {
    	boolean returnValue = true;
    	
        String regex = pattern+"\\d{"+length+"}";
        
        if(pattern == null || "".equals(pattern))
        {
        	returnValue = false;
        }
        else if (!Source.matches(regex)) { 
        	returnValue = false;
        }
        
        return returnValue;
    }
    
    public static int defaultInt(String src, int defaultInt)
    {
        return isEmpty(src)? defaultInt : Integer.parseInt(src);
    }
    
    public static StringBuffer append(StringBuffer sb, Object ...args)
    {
        for(Object str : args)
        {
            sb.append(str);
        }
        return sb;
    }
    
    public static String append(Object ... args)
    {
        StringBuffer sb = new StringBuffer();
        return append(sb, args).toString();
    }
    
    /**
     * 하나라도 비었으면 true 반환
     * @param args
     * @return
     */
    public static boolean isAnyEmpty(String ... args)
    {
        for (String str : args)
        {
            if (isEmpty(str))
            {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * String배열에 String을 추가한다.
     * @param arr
     * @param str
     * @return
     */
    public static String[] addToArray(String[] arr, String str)
    {
        return addToArray(arr, str, false);
    }
    
    /**
     * String배열에 String을 추가한다. 
     * @param arr
     * @param str
     * @param isDistinct : 중복된 값일 경우 제외할지 여부
     * @return
     */
    public static String[] addToArray(String[] arr, String str, boolean isDistinct)
    {
        if (str == null)
        {
            return arr;
        }
        else if(arr == null || arr.length < 1)
        {
            return new String[]{str};
        }
        
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(arr));
        if (!isDistinct || !list.contains(str))
        {
            list.add(str);
        }
        
        return list.toArray(new String[list.size()]);
    }
    
    /**
     * src문자열에서 start로 시작해서 end로 끝나는 구문을 모두 반환한다.
     * 
     *  src = START0123456789
     *  start = START
     *  end = 7
     *  result = 0123456
     * 
     * @param src
     * @param start
     * @param end
     * @return
     */
    /*public static List<String> substring(String src, String start, String end)
    {
        return substring(src, start, new String[]{end});
    }*/
    
    /**
     * src문자열에서 start로 시작해서 ends로 끝나는 구문을 모두 반환한다.
     * ends중 먼저 매칭되는 부분으로 잘라 반환한다.
     * 
     *  src = START0123456789
     *  start = START
     *  ends = 9, 7
     *  result = 0123456
     * 
     * @param src
     * @param start
     * @param ends
     * @return
     */
    /*public static List<String> substring(String src, String start, String[] ends)
    {
        List<String> strList = new ArrayList<String>();
        
        if (src==null||start==null||ends==null||ends.length < 1)
        {
            return strList;
        }
        
        String temp = src;
        int startLen = start.length();
        int startIdx = temp.indexOf(start);
        int endIdx = -1;
        int tmpIdx = -1;
        
        PARSING : while(startIdx != -1)
        {
            temp = temp.substring(startIdx+startLen);
            
            for (String end : ends)
            {
                tmpIdx = temp.indexOf(end);
                endIdx = (endIdx!=-1 && tmpIdx!=-1 && endIdx < tmpIdx)? endIdx : tmpIdx;
            }
            
            if (endIdx == -1)
            {
                break PARSING;
            }
            
            strList.add(temp.substring(0, endIdx));
            temp = temp.substring(endIdx);
            startIdx = temp.indexOf(start);
            endIdx = -1;
        }
        
        return strList;
    }*/
}