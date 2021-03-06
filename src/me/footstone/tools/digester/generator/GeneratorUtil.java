package me.footstone.tools.digester.generator;

/**
 * 
 * @author footstone
 *
 */
public class GeneratorUtil {
	
	/**
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		if (str==null || str.length() == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * format the element name
	 * @param str
	 * @return
	 */
	public static String formatName(String str){
		if (isBlank(str)){
			throw new IllegalArgumentException();
		}
		
		StringBuffer sb = new StringBuffer();
		String[] strs = str.split("-");
		for (String s:strs){
			String f = s.substring(0,1);
			sb.append(s.replaceFirst(f, f.toUpperCase()));
		}
	
		return sb.toString();
	}
	
	/**
	 * @param str
	 * @return
	 */
	public static String toLowerFirstCase(String str){
		if(isBlank(str)){
			throw new IllegalArgumentException();
		}
		String f = str.substring(0, 1);
		return str.replaceFirst(f, f.toLowerCase());
	}
	
	/**
	 * @param str
	 * @return
	 */
	public static String toUpperFirstCase(String str){
		if (isBlank(str)){
			throw new IllegalArgumentException();
		}
		String f = str.substring(0, 1);
		return str.replaceFirst(f, f.toUpperCase());
	}
	
	public static void main(String[] args) {
		String str = GeneratorUtil.formatName("c_name");
		System.out.println(str);
	}

}
