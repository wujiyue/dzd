package com.markbro.dzd.common.util;

import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Pinyin {
	public static String converterToFirstSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > '')
				try {
					pinyinName = pinyinName
							+ net.sourceforge.pinyin4j.PinyinHelper
									.toHanyuPinyinStringArray(nameChar[i],
											defaultFormat)[0].charAt(0);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			else {
				pinyinName = pinyinName + nameChar[i];
			}
		}
		return pinyinName;
	}

	public static String converterToSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > '')
				try {
					pinyinName = pinyinName
							+ net.sourceforge.pinyin4j.PinyinHelper
									.toHanyuPinyinStringArray(nameChar[i],
											defaultFormat)[0];
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			else {
				pinyinName = pinyinName + nameChar[i];
			}
		}
		return pinyinName;
	}
}
