package cn.slimsmart.common.util.string;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串操作工具类
 * @author Zhu.TW
 *
 */
public class StringUtil extends StringUtils {
	
	/**
	 * 获取字符串字节数
	 * @param str
	 * @return
	 */
	public static long  byteLength(String str){
		int length = str.length();       
	    for(int i = 0,l = str.length(); i <l; i++){       
	        if(str.charAt(i) > 127){       
	        	length = length+2;       
	        }       
	    }
	    return length;
	}

	/**
	 * 去除字符串两边空白
	 * @param str
	 * @return
	 */
	public static String trimString(String str){
		 if (StringUtils.isNotBlank(str)) {
			 str = str.trim();
		    }
		    return str;
	 }
	
	/**
	 * 格式化字符串  如：format("{0}，{1}","你好",123)  ==》"你好，123"
	 * @param desc
	 * @param str
	 * @return
	 */
	public static String format(String desc,Object ... str){
		if(desc == null || str==null || str.length == 0){
			return desc;
		}
		int length = str.length;
		for(int i= 0 ; i<length; i++){
			desc = desc.replace("{"+i+"}", str[i].toString());
		}
		return desc;
	}
	
	/**
	 * string to Unicode
	 * @param str
	 * @return
	 */
	public static String toUnicode(String str){
        char[]arChar=str.toCharArray();
        int iValue=0;
        String uStr="";
        for(int i=0;i<arChar.length;i++){
            iValue=(int)str.charAt(i);           
            if(iValue<=256){
                uStr+="\\u00"+Integer.toHexString(iValue);
            }else{
                uStr+="\\u"+Integer.toHexString(iValue);
            }
        }
        return uStr;
    }
	
	/**
	 * 首字母转小写
	 * 
	 * @param str
	 * @return
	 */
	public static String lowercaseFirstLetter(String str) {
		char c = str.charAt(0);
		String temp = new String(c + "");
		return str.replaceFirst(temp, org.apache.commons.lang.StringUtils.lowerCase(temp));
	}
}
