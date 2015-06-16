package com.tanghai.library.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLEncoder;

/**
 * 字符串处理类。
 * @time 2013年    下午3:56:54
 * @author wang_fei
 */
public class StringUtil
{

	private final static int BUFFER_SIZE = 1024;

	/**
	 * 将InputStream转换成String。
	 * @param in
	 * @return
	 * @throws Exception
	 * @time 2013年    下午3:57:05
	 * @author wang_fei
	 */
	public static String InputStreamTOString(InputStream in) throws Exception
	{
		BufferedReader brs = new BufferedReader(new InputStreamReader(in));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = brs.readLine()) != null)
		{
			buffer.append(line);
		}
		return buffer.toString();
	}

	/**
	 * 将InputStream转换成某种字符编码的String。
	 * @param in
	 * @param encoding
	 * @return
	 * @throws Exception
	 * @time 2013年    下午3:57:23
	 * @author wang_fei
	 */
	public static String InputStreamTOString(InputStream in, String encoding)throws Exception
	{

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1){
			outStream.write(data, 0, count);
		}
		data = null;
		return new String(outStream.toByteArray(), "UTF-8");
	}

	/**
	 * 将String转换成InputStream。
	 * @param in
	 * @return
	 * @throws Exception
	 * @time 2013年    下午3:57:37
	 * @author wang_fei
	 */
	public static InputStream StringTOInputStream(String in) throws Exception
	{
//		ByteArrayInputStream is = new ByteArrayInputStream(in.getBytes());
//		ByteArrayInputStream is = new ByteArrayInputStream(in.getBytes("gbk"));
		ByteArrayInputStream stream = new ByteArrayInputStream(in.getBytes());
		return stream;
	}

	/**
	 * 将InputStream转换成byte数组。
	 * @param in
	 * @return
	 * @throws IOException
	 * @time 2013年    下午3:57:48
	 * @author wang_fei
	 */
	public static byte[] InputStreamTOByte(InputStream in) throws IOException
	{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1){
			outStream.write(data, 0, count);
		}
		data = null;
		return outStream.toByteArray();
	}

	/**
	 * 将byte数组转换成InputStream。
	 * @param in
	 * @return
	 * @time 2013年    下午3:57:58
	 * @author wang_fei
	 */
	public static InputStream byteTOInputStream(byte[] in)
	{
		if (in == null || in.length == 0){
			return null;
		}
		ByteArrayInputStream is = new ByteArrayInputStream(in);
		return is;
	}

	/**
	 * 将byte数组转换成String。
	 * @param in
	 * @return
	 * @throws Exception
	 * @time 2013年    下午3:58:12
	 * @author wang_fei
	 */
	public static String byteTOString(byte[] in) throws Exception
	{
		InputStream is = byteTOInputStream(in);
		return InputStreamTOString(is);
	}

	/**
	 * 编码特定的特殊字符'&'---->&amp;  '"'---->&quot; '<'----&lt;  '>'----&gt;
	 * @param original
	 * @return
	 * @time 2013年    下午3:58:22
	 * @author wang_fei
	 */
	public static String translation(String original)
	{
		return original.replaceAll("&", "&amp;").replaceAll("\"", "&quot;")
				.replaceAll("<", "&lt;").replaceAll(">", "&gt;")
				.replaceAll("'", "&apos;");
	}

	/**
	 * 反编码特定的字符 &amp;---->'&';    &quot;---->'"';  &lt;---->'<'; &gt;---->'>'
	 * @param original
	 * @return
	 * @time 2013年    下午3:58:44
	 * @author wang_fei
	 */
	public static String untranslation(String original)
	{
		String result = null;
		if (original != null)
		{
			result = original.replaceAll("&amp;", "&")
					.replaceAll("&quot;", "\"").replaceAll("&lt;", "<")
					.replaceAll("&gt;", ">").replaceAll("&apos;", "'");
		}
		return result;
	}

	/**
	 * 返回异常对象字符描述信息
	 * @param throwObj
	 * @return
	 * @time 2013年    下午3:59:12
	 * @author wang_fei
	 */
	public static String getStackTrace(Throwable throwObj)
	{
		if (null == throwObj)
		{
			return "";
		}
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		throwObj.printStackTrace(pw);
		pw.flush();
		sw.flush();
		return sw.toString();
	}
	
	/**
	 * 对URL进行编码
	 * @param stringObj
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String urlEncode(String stringObj)
	{
		return URLEncoder.encode(stringObj).toLowerCase();
	}
	
	

}
