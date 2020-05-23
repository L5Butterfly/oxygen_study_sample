package com.oxygen.other;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * 流水号工具类
 * @author empathy
 *
 */
public class SeqNo {
	private static final int MAX_RANDOM = 1000000000;
	private static final String FORMAT = "%17s-%010d";
	private static final String SYSTEM_FORMAT = "%3s-%28s";
	private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

	/**
	 * 获取当前时间-随机数的流水号
	 * 极端情况下非唯一
	 * @return 28位字符串流水号
	 */
	public static String generateSeqNo() {
		String dateTime = LocalDateTime.now().format(DATE_TIME_FORMAT);
		Random random = new Random();
		return String.format(FORMAT, dateTime, random.nextInt(MAX_RANDOM));
	}

	/**
	 * 生成带系统编号的随机流水号
	 * @param systemNo 系统编号
	 * @return 32位字符串流水号
	 */
	public static String generateSystemSeqNo(String systemNo) {
		return String.format(SYSTEM_FORMAT, systemNo, generateSeqNo());
	}
}
