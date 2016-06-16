package com.markbro.dzd.common.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.io.*;
import java.security.Key;
import java.security.SecureRandom;

public class Des {
	Key key;

	public Des() {
		getKey("sdtjqf_ict");
	}

	public void getKey(String strKey) {
		try {
			KeyGenerator _generator = KeyGenerator.getInstance("DES");
			_generator.init(new SecureRandom(strKey.getBytes()));
			this.key = _generator.generateKey();
			_generator = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getEncString(String strMing) {
		String strMi = "";
		try {
			return byte2hex(getEncCode(strMing.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return strMi;
	}

	public String getDesString(String strMi) {
		String strMing = "";
		try {
			return new String(getDesCode(hex2byte(strMi.getBytes())));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return strMing;
	}

	private byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = (byte[]) null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(1, this.key);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	private byte[] getDesCode(byte[] byteD) {
		byte[] byteFina = (byte[]) null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(2, this.key);
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public static byte[] hex2byte(byte[] b) {
		if (b.length % 2 != 0) {
			throw new IllegalArgumentException("长度不是偶数");
		}
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);

			b2[(n / 2)] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	public static void main(String[] args) {
		System.out.println("加解密程序开始运行...");
		System.out.println("输入1进入加密阶段");
		System.out.println("输入2进入解密阶段");
		System.out.println("输入3退出程序");
		int flag = 0;
		Des des = new Des();
		while (true)
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				String in = br.readLine();
				if (in.equalsIgnoreCase("1")) {
					System.out.println("请输入需要加密的文件路径：");
					flag = 1;
					continue;
				}
				if (in.equalsIgnoreCase("2")) {
					System.out.println("请输入需要解密的文件路径：");
					flag = 2;
					continue;
				}
				if (in.equalsIgnoreCase("3")) {
					System.exit(0);
					continue;
				}
				if (!in.equals("")) {
					String inPath = in;
					if (flag == 1)
						System.out.println("请输入加密后生成的文件路径：");
					else if (flag == 2) {
						System.out.println("请输入解密后生成的文件路径：");
					}
					String outPath = br.readLine();
					File inFile = new File(inPath);
					boolean temp = false;
					if (!inFile.exists()) {
						if (flag == 1)
							System.out.println("需要加密的文件不存在");
						else
							System.out.println("需要解密的文件不存在");
					} else {
						String newoutPath = outPath.replace("/", "\\");
						newoutPath = newoutPath.substring(0,
								newoutPath.lastIndexOf("\\"));
						File outFile = new File(outPath);
						if (!new File(newoutPath).exists()) {
							if (flag == 1)
								System.out.println("加密后生成的文件夹不存在");
							else
								System.out.println("解密后生成的文件夹不存在");
						} else {
							if (!outFile.exists()) {
								outFile.createNewFile();
							}
							InputStreamReader read = new InputStreamReader(
									new FileInputStream(inFile));
							BufferedReader bufferedReader = new BufferedReader(
									read);
							BufferedWriter write = new BufferedWriter(
									new FileWriter(outFile));
							String lineTXT = "";
							StringBuffer writeTXT = new StringBuffer();
							if ("".equals(lineTXT))
								continue;
							if (lineTXT.substring(0, 1).equals("#"))
								continue;
							if (!writeTXT.toString().equals(""))
								writeTXT.append("\n");
							if (flag == 1)
								writeTXT.append(des.getEncString(lineTXT));
							else
								writeTXT.append(des.getDesString(lineTXT));
							if ((lineTXT = bufferedReader.readLine()) != null) {
								continue;
							}

							write.write(writeTXT.toString());
							write.close();
							read.close();
							temp = true;
							if (temp) {
								if (flag == 1)
									System.out.println("加密成功！！！");
								else
									System.out.println("解密成功！！！");
							} else if (flag == 1)
								System.out.println("加密失败！！！");
							else {
								System.out.println("解密失败！！！");
							}

						}

					}

				}

				System.out.println("");
				System.out.println("输入1进入加密阶段");
				System.out.println("输入2进入解密阶段");
				System.out.println("输入3退出程序");
				continue;
			} catch (Exception localException) {
				System.out.println(localException.toString());
			}
	}
}
