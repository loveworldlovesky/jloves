package com.free.love.core.utils;

import java.util.List;
import java.util.StringTokenizer;

public class UtilString {
	public static final String arrayToString(String[] strSource,
			String strDelimiter, boolean bProcessEmpty) {
		if (strSource.length == 0)
			return "";

		StringBuilder buf = new StringBuilder();

		for (int i = 0; i < strSource.length; i++) {
			if ((!bProcessEmpty)
					|| ((strSource[i] != null) && (!"".equals(strSource[i])) && (strSource[i]
							.length() != 0))) {
				buf.append(strSource[i]);
				if (i != strSource.length - 1) {
					buf.append(strDelimiter);
				}
			}
		}
		return buf.toString();
	}

	public static final String arrayToString(String[] strSource,
			String strDelimiter) {
		return arrayToString(strSource, strDelimiter, true);
	}

	public static final String[] stringToArray(String strSource,
			String delimiter) {
		if (strSource == null)
			return null;

		if (strSource.substring(strSource.length() - delimiter.length())
				.equals(delimiter)) {
			strSource = strSource.substring(0,
					strSource.length() - delimiter.length());
		}
		StringTokenizer token = new StringTokenizer(strSource, delimiter);
		String[] array = new String[token.countTokens()];
		int i = 0;

		while (token.hasMoreTokens()) {
			array[i] = token.nextToken();
			i++;
		}
		return array;
	}

	public static final String[] splitAndTrim(String strSource, String delimiter) {
		return strSource.split(new StringBuilder().append("\\s*[")
				.append(delimiter).append("]\\s*").toString());
	}

	public static final String replaceAll(String strData, String strSource,
			String strTarget) {
		if (strData == null)
			return null;
		StringBuilder buf = new StringBuilder(strData);
		int pos = buf.indexOf(strSource);
		while (pos >= 0) {
			buf.replace(pos, pos + strSource.length(), strTarget);
			pos = buf.indexOf(strSource);
		}
		return buf.toString();
	}

	public static final String repeat(String src, int cnt) {
		if (src == null)
			return null;
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < cnt; i++) {
			buf.append(src);
		}
		return buf.toString();
	}

	public static final String[] stringToArray(String str) {
		return stringToArray(str, ",");
	}

	public static final boolean isWhiteSpace(String strSource) {
		if (strSource.length() == 0)
			return false;

		char[] ch = strSource.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if (!Character.isWhitespace(ch[i]))
				return false;
		}
		return true;
	}

	public static int indexOfWhiteSpace(String source, int start) {
		for (int i = start; i < source.length(); i++) {
			if (Character.isWhitespace(source.charAt(i)))
				return i;
		}
		return -1;
	}

	public static final String replaceWhiteSpace(String strSource,
			String strReplace) {
		if (strSource == null)
			return null;

		String data = strSource.trim();
		boolean found = false;
		StringBuilder buf = new StringBuilder();

		if ((data.length() == 0) || (data == null))
			return "";

		char[] ch = data.toCharArray();
		for (int i = 0; i < ch.length; i++)
			if (Character.isWhitespace(ch[i])) {
				if (!found) {
					buf.append(strReplace);
					found = true;
				}
			} else {
				found = false;

				buf.append(ch[i]);
			}
		return buf.toString();
	}

	public static String byteToHexString(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			if (stmp.length() == 1) {
				hs = new StringBuilder().append(hs).append("0").append(stmp)
						.toString();
			} else {
				hs = new StringBuilder().append(hs).append(stmp).toString();
			}

			if (n < b.length - 1)
				hs = new StringBuilder().append(hs).append("").toString();
		}

		return hs;
	}

	public static String string2Unicode(String s) {
		if ((s == null) || (s.length() == 0))
			return "";
		char[] charA = s.toCharArray();
		StringBuilder t = new StringBuilder("");
		String tt = "";
		for (int i = 0; i < charA.length; i++) {
			tt = Integer.toHexString(charA[i]);
			if (tt.length() == 2)
				tt = new StringBuilder().append("%").append(tt).toString();
			else
				tt = new StringBuilder().append("%u").append(tt).toString();
			t.append(tt);
		}
		return t.toString();
	}

	public static String encodeString(String s, String charSetName) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ((c >= 0) && (c <= 'ÿ')) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes(charSetName);
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append(new StringBuilder().append("%")
							.append(Integer.toHexString(k).toUpperCase())
							.toString());
				}
			}
		}
		return sb.toString();
	}

	public static String wordCap(String source) {
		if ((source == null) || (source.length() == 0))
			return "";
		char firstChar = source.charAt(0);
		if (Character.isLetter(firstChar)) {
			String rc = new StringBuilder()
					.append(Character.toUpperCase(firstChar))
					.append(source.substring(1)).toString();
			return rc;
		}

		return source;
	}

	public static List<String> wordSplit(String source) {
//		int j = 0;
//
//		char[] chrs = source.toCharArray();
//		List list = new ArrayList();
//		while (true) {
//			char chr = chrs[j];
//			if (isWord(chr, true)) {
//				for (int i = j + 1; (i < chrs.length) && (isWord(chrs[i], false)); i++){
//					
//				}
//			}else{
//				for (int i = j + 1; (i < chrs.length) && (isWord(chrs[i], true) != true); i++){
//					
//					list.add(String.copyValueOf(chrs, j, i - j));
//					j = i;
//					if (i == chrs.length){
//						break;
//					}
//				}
//			}
//
//			
//		}

		return null;
	}

	public static boolean isWord(char chr, boolean firstChr) {
		if (((chr >= 'A') && (chr <= 'Z')) || ((chr >= 'a') && (chr <= 'z'))
				|| (chr == '_'))
			return true;
		if ((chr >= '0') && (chr <= '9') && (!firstChr))
			return true;

		return false;
	}

	public static int indexOfWord(String srcString, String word) {
		int lenStr = srcString.length();
		int lenWord = word.length();
		int fromIndex = 0;
		int pos;
		char ch;
		do {
//			char ch;
			do {
				pos = srcString.indexOf(word, fromIndex);
				if (pos < 0)
					return pos;

				fromIndex = pos + lenWord;

				if (pos <= 0)
					break;
				ch = srcString.charAt(pos - 1);
			} while (isWord(ch, false) == true);

			if (pos + lenWord >= lenStr)
				break;
			ch = srcString.charAt(pos + lenWord);
		} while (isWord(ch, false) == true);

		return pos;
	}

	public static boolean hasItem(String src, String item, String split) {
		if ((src == null) || (item == null))
			return false;

		String[] items = src.split(split);
		for (String i : items) {
			if (item.equals(i))
				return true;
		}

		return false;
	}

	public static String getShortClassName(String className) {
		int pos = className.lastIndexOf(".");
		if (pos > 0)
			className = className.substring(pos + 1, className.length());
		return className;
	}

	public static boolean isEquals(String str1, String str2) {
		if ((str1 == null) && (str2 == null))
			return true;
		if (str1 == null)
			return false;

		return str1.equals(str2);
	}

	public static boolean isEmpty(String str) {
		if ((str == null) || (str.trim().length() == 0))
			return true;
		return false;
	}

	public static String formatValue(String value) {
		if (value == null)
			return null;

		if (value.indexOf("\r") >= 0) {
			value = value.replaceAll("\r", "");
		}
		if (value.indexOf("\n") >= 0) {
			value = value.replaceAll("\n", "");
		}

		if (value.indexOf("\"") >= 0) {
			value = value.replaceAll("\"", "");
		}

		if (value.indexOf("'") >= 0) {
			value = value.replaceAll("'", "");
		}

		if (value.indexOf("\\") >= 0) {
			value = value.replaceAll("\\\\", "\\\\\\\\");
		}

		return value;
	}

	public static void main(String[] args) {
		System.out
				.println(indexOfWord(
						"select a,fromT,from_b,from2,b_from,b_2from from table",
						"from"));
	}
}
