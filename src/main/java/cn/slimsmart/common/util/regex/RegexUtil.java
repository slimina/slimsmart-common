package cn.slimsmart.common.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.slimsmart.common.util.common.string.StringUtil;

/**
 * 正则匹配工具类
 * 
 * @author Zhu.TW
 * 
 */
public class RegexUtil {

	private static Pattern pattern=null;  
    private static Matcher macher=null; 
    
	private RegexUtil() {
	}
	// 匹配图象格式: /相对路径/文件名.后缀 (后缀为gif,dmp,png)
	public static final String ICON_REGEXP = "^(/{0,1}\\w){1,}\\.(gif|dmp|png|jpg)$|^\\w{1,}\\.(gif|dmp|png|jpg)$";
	// 匹配email地址
	public static final String EMAIL_REGEXP = "(?:\\w[-._\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,3}$)";
	// 匹配匹配并提取url
	public static final String URL_REGEXP = "(\\w+)://([^/:]+)(:\\d*)?([^#\\s]*)";
	// 匹配并提取http
	public static final String HTTP_REGEXP = "(http|https|ftp)://([^/:]+)(:\\d*)?([^#\\s]*)";
	// 匹配日期
	public static final String DATE_REGEXP = "^((((19){1}|(20){1})d{2})|d{2})[-\\s]{1}[01]{1}d{1}[-\\s]{1}[0-3]{1}d{1}$";
	// 匹配电话
	public static final String PHONE_REGEXP = "^(?:0[0-9]{2,3}[-\\s]{1}|\\(0[0-9]{2,4}\\))[0-9]{6,8}$|^[1-9]{1}[0-9]{5,7}$|^[1-9]{1}[0-9]{10}$";
	// 匹配身份证
	public static final String ID_CARD_REGEXP = "^\\d{10}|\\d{13}|\\d{15}|\\d{18}$";
	// 匹配邮编代码
	public static final String ZIP_REGEXP = "^[0-9]{6}$";

	// 不包括特殊字符的匹配 (字符串中不包括符号 数学次方号^ 单引号' 双引号" 分号; 逗号, 帽号: 数学减号- 右尖括号>
	// 左尖括号< 反斜杠\ 即空格,制表符,回车符等 )
	public static final String NON_SPECIAL_CHAR_REGEXP = "^[^'\"\\;,:-<>\\s].+$";
	// 匹配非负整数（正整数 + 0)
	public static final String NON_NEGATIVE_INTEGERS_REGEXP = "^\\d+$";
	// 匹配不包括零的非负整数（正整数 > 0)
	public static final String NON_ZERO_NEGATIVE_INTEGERS_REGEXP = "^[1-9]+\\d*$";
	// 匹配正整数
	public static final String POSITIVE_INTEGER_REGEXP = "^[0-9]*[1-9][0-9]*$";
	// 匹配非正整数
	public static final String NON_POSITIVE_INTEGERS_REGEXP = "^((-\\d+)|(0+))$";
	// 匹配负整数
	public static final String NEGATIVE_INTEGERS_REGEXP = "^-[0-9]*[1-9][0-9]*$";
	// 匹配整数
	public static final String INTEGER_REGEXP = "^-?\\d+$";
	// 匹配非负浮点数（正浮点数 + 0）
	public static final String NON_NEGATIVE_RATIONAL_NUMBERS_REGEXP = "^\\d+(\\.\\d+)?$";
	// 匹配正浮点数
	public static final String POSITIVE_RATIONAL_NUMBERS_REGEXP = "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";
	// 匹配非正浮点数（负浮点数 + 0）
	public static final String NON_POSITIVE_RATIONAL_NUMBERS_REGEXP = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";
	// 匹配负浮点数
	public static final String NEGATIVE_RATIONAL_NUMBERS_REGEXP = "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$";
	// 匹配浮点数
	public static final String RATIONAL_NUMBERS_REGEXP = "^(-?\\d+)(\\.\\d+)?$";
	// 匹配由26个英文字母的大写组成的字符串
	public static final String UPWARD_LETTER_REGEXP = "^[A-Z]+$";
	// 匹配由数字和26个英文字母组成的字符串
	public static final String LETTER_NUMBER_REGEXP = "^[A-Za-z0-9]+$";
	// 匹配由数字、26个英文字母或者下划线组成的字符串
	public static final String LETTER_NUMBER_UNDERLINE_REGEXP = "^\\w+$";
	//中文
	public static final String CHINESE_REGEXP = "[\u4e00-\u9fa5]";
	//双字节
	public static final String DOUBLE_BYTE_REGEXP = "[^x00-xff]";
	//html
	public static final String HTML_REGEXP = "/< (.*)>.*|< (.*) />/";
	//正则匹配
	public static boolean isMatcher(String source, String regexp) {
		if(StringUtil.isBlank(source)){
			return false;
		}
		pattern = Pattern.compile(regexp);
		macher = pattern.matcher(source);
		return macher.find();
	}
	
	  /**
	  * @param str
	  * @return 判断字符串是否包含中文
	  */
	 public static boolean isConstainsChinese(String str) {
	   pattern = Pattern.compile(CHINESE_REGEXP);
	   char[] cs = str.toCharArray();
	   for(char c : cs){
		   macher = pattern.matcher(c + "");
	     if (macher.find())
	      return true;
	   }
	   return false;
	 }
	 
	//匹配双字节字符(包括汉字在内)：[^x00-xff]             ---已验证  
     public static boolean isDoubleByteString(String inputString){  
           pattern=Pattern.compile(DOUBLE_BYTE_REGEXP);  
           macher=pattern.matcher(inputString);  
           return macher.find();  
    }  

    //匹配HTML标记的正则表达式：/< (.*)>.*|< (.*) />/      ---未验证：可以实现HTML过滤  
     public static boolean isHtmlString(String inputString){  
           pattern=Pattern.compile(HTML_REGEXP);  
           macher=pattern.matcher(inputString);  
           return macher.find();  
    }  

    //邮箱规则：用户名@服务器名.后缀                                   ---已验证  
     //匹配Email地址的正则表达式：^([a-z0-9A-Z]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}  
    public static boolean isEmail(String inputString){  
           pattern=Pattern.compile(EMAIL_REGEXP);  
           macher=pattern.matcher(inputString);  
           return macher.find();  

    }  

    //匹配网址URL的正则表达式：^http://[a-zA-Z0-9./\\s]      ---已验证  
     public static boolean isUrl(String inputString){  
           pattern=Pattern.compile(URL_REGEXP);  
           macher=pattern.matcher(inputString);  
           return macher.find();  
    }  

    //验证身份证是否有效15位或18位  ^\\d{15}(\\d{2}[0-9xX])?$  ---已验证<包括对年月日的合法性进行验证>  
    public static boolean isIdCard(String inputString){  
           pattern=Pattern.compile(ID_CARD_REGEXP);             
			macher=pattern.matcher(inputString);  
           if(macher.find()){                                 //对年月日字符串的验证  
                   String power=inputString.substring(inputString.length()-12,inputString.length()-4);  
                   pattern=Pattern.compile(DATE_REGEXP);  
                   macher=pattern.matcher(power);  
           }  
           return macher.find();  
    }  

    //验证固定电话号码   ^(([0-9]{3,4})|([0-9]{3,4})-)?[0-9]{7,8}$ ---已验证  
     public static boolean isTelePhone(String inputString){  
           pattern=Pattern.compile(PHONE_REGEXP);  
           macher=pattern.matcher(inputString);  
           return macher.find();  
    }  

    //匹配正整数 ^[1-9]d*$　 　   
     public static boolean isPositiveInteger(String inputString){  
           pattern=Pattern.compile(POSITIVE_INTEGER_REGEXP);  
           macher=pattern.matcher(inputString);  
           return macher.find();  
    }  

    //匹配负整数 ^-[1-9]d*$ 　   
     public static boolean isNegativeInteger(String inputString){  
           pattern=Pattern.compile(NEGATIVE_INTEGERS_REGEXP); 
           macher=pattern.matcher(inputString);  
           return macher.find();  
    }  

    //匹配整数  ^-?[1-9]d*$　　   
     public static boolean isInteger(String inputString){  
           pattern=Pattern.compile(INTEGER_REGEXP);
           macher=pattern.matcher(inputString);  
           return macher.find();  
    }  

    //匹配非负整数（正整数 + 0） ^[1-9]d*|0$　  
     public static boolean isNotNegativeInteger(String inputString){  
           pattern=Pattern.compile(NON_NEGATIVE_INTEGERS_REGEXP);
           macher=pattern.matcher(inputString);  
           return macher.find();  
    }  

    //匹配非正整数（负整数 + 0） ^-[1-9]d*|0$　  
     public static boolean isNotPositiveInteger(String inputString){  
           pattern=Pattern.compile(NON_POSITIVE_INTEGERS_REGEXP);
           macher=pattern.matcher(inputString);  
           return macher.find();  
    }  

    //匹配正浮点数    ^[1-9]d*.d*|0.d*[1-9]d*$　　  
     public static boolean isPositiveFloat(String inputString){  
           pattern=Pattern.compile(POSITIVE_RATIONAL_NUMBERS_REGEXP);
           macher=pattern.matcher(inputString);  
           return macher.find();  
    }  

    //匹配负浮点数    ^-([1-9]d*.d*|0.d*[1-9]d*)$　  
     public static boolean isNegativeFloat(String inputString){  
           pattern=Pattern.compile(NEGATIVE_INTEGERS_REGEXP);
           macher=pattern.matcher(inputString);  
           return macher.find();  
    }  

    //匹配浮点数   ^-?([1-9]d*.d*|0.d*[1-9]d*|0?.0+|0)$　  
     public static boolean isFloat(String inputString){
           pattern=Pattern.compile(RATIONAL_NUMBERS_REGEXP);  
           macher=pattern.matcher(inputString);  
           return macher.find();  
    }  

    //匹配非负浮点数（正浮点数 + 0）^[1-9]d*.d*|0.d*[1-9]d*|0?.0+|0$　　  
     public static boolean isNotNegativeFloat(String inputString){
           pattern=Pattern.compile(NON_NEGATIVE_RATIONAL_NUMBERS_REGEXP);  
           macher=pattern.matcher(inputString);  
           return macher.find();  
    }  

    //匹配非正浮点数（负浮点数 + 0）^(-([1-9]d*.d*|0.d*[1-9]d*))|0?.0+|0$  
    public static boolean isNotPositiveFloat(String inputString){
           pattern=Pattern.compile(NON_POSITIVE_RATIONAL_NUMBERS_REGEXP);  
           macher=pattern.matcher(inputString);  
           return macher.find();  
    }  


    //只能输入m-n位的数字：“^d{m,n}$”  
     public static boolean isNumberLengthBetweenLowerAndUpper(int lower,int upper,String inputString){  
           pattern=Pattern.compile("^d{"+lower+","+upper+"}$");  
           macher=pattern.matcher(inputString);  
           return macher.find();  
    }  

    //只能输入有两位小数的正实数：“^[0-9]+(.[0-9]{2})?$”  
     public static boolean isNumberInPositiveWhichHasTwolengthAfterPoint(String inputString){  
           pattern=Pattern.compile("^[0-9]+(.[0-9]{2})?$");  
           macher=pattern.matcher(inputString);  
           return macher.find();  
    }  

    //只能输入由26个大写英文字母组成的字符串：“^[A-Z]+$”  
     public static boolean isUppercaseEnglishAlphabetString(String inputString){  
           pattern=Pattern.compile(UPWARD_LETTER_REGEXP);  
           macher=pattern.matcher(inputString);  
           return macher.find();  
    }  

    //只能输入由26个小写英文字母组成的字符串：“^[a-z]+$”  
     public static boolean isLowerEnglishAlphabetString(String inputString){  
           pattern=Pattern.compile(UPWARD_LETTER_REGEXP.toLowerCase());  
           macher=pattern.matcher(inputString);  
           return macher.find();  
    }  

    //只能输入由数字和26个英文字母组成的字符串：“^[A-Za-z0-9]+$”  
     public static boolean isNumberEnglishAlphabetString(String inputString){  
           pattern=Pattern.compile(LETTER_NUMBER_REGEXP);  
           macher=pattern.matcher(inputString);  
           return macher.find();  
    }  

    //只能输入由数字、26个英文字母或者下划线组成的字符串：“^w+$”  
     public static boolean isNumberEnglishAlphabetWithUnderlineString(String inputString){  
           pattern=Pattern.compile(LETTER_NUMBER_UNDERLINE_REGEXP);  
           macher=pattern.matcher(inputString);  
           return macher.find();  
    }  
}
