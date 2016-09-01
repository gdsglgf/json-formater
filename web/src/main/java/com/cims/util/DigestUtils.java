package com.cims.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密函数类.
 * @author Luo Guofu
 */
public class DigestUtils {
	/**
	 * 获取MD5加密后的密码.
	 * @param password - 未经MD5加密的密码
	 * @return MD5加密后的密码
	 */
	public static String md5Hex(String data) {
		String md5 = "";
		if (data == null || data.isEmpty()) {
			return "";
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(data.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xff) | 0x100).substring(1, 3));
			}
			md5 = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}

	public static void main(String[] args) {
		System.out.println("e10adc3949ba59abbe56e057f20f883e");
		System.out.println(md5Hex("123456"));
	}
}
