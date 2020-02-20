package com.springboot.learn.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

	
public class StreamUtil {
	/**
	 * 从网络Url中下载文件
	 * 
	 * @param urlStr
	 * @param fileName
	 * @param savePath
	 * @throws IOException
	 */
	public static boolean downLoadFromUrl(String urlStr, String fileName,
			String savePath) {
		URL url;
		try {
			url = new URL(urlStr);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置超时间为3秒
			conn.setConnectTimeout(3 * 1000);
			// 防止屏蔽程序抓取而返回403错误
			conn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

			// 得到输入流
			InputStream inputStream = conn.getInputStream();
			// 获取自己数组
			byte[] getData = readInputStream(inputStream);

			// 文件保存位置
			File saveDir = new File(savePath);
			if (!saveDir.exists()) {
				saveDir.mkdirs();
			}
			File file = new File(saveDir + File.separator + fileName);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(getData);
			if (fos != null) {
				fos.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}

			System.out.println("info:" + url + " download success");
		} catch (Exception e) {
//			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 从输入流中获取字节数组
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] readInputStream(InputStream inputStream)
			throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}

	public static String getEncoding(InputStream input) throws IOException {
		int p = (input.read() << 8) + input.read();
		String code = null;
		switch (p) {
		case 0xefbb:
			code = CharCodeConst.UTF8;
			break;
		case 0xfffe:
			code = CharCodeConst.UNICODE;
			break;
		case 0xfeff:
			code = CharCodeConst.UTF16BE;
			break;
		default:
			code = CharCodeConst.UTF8;
		}
		System.out.println(code);
		return code;
	}

	/**
	 * @功能 读取流
	 * @param $inStream
	 * @return 字节数组
	 * @throws Exception
	 */
	public static byte[] readStream(InputStream $inStream) throws Exception {
		int count = $inStream.available();
		System.out.println(count);

		byte[] b = new byte[count];
		$inStream.read(b);
		return b;
	}

	/**
	 * 将字符串写入本地文件
	 * 
	 * @param filePath
	 * @param con
	 */
	public static void writeStringToFile(String filePath, String con) {
		PrintStream ps = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				// 如果文件夹不存在，先创建文件夹
				File parentFile = file.getParentFile();
				if (!parentFile.exists()) {
					if (!parentFile.isDirectory() || !parentFile.mkdirs()) {
						throw new RuntimeException(
								"Parent File isn't a directory or mkdirs is failed.");
					}
				}

				file.createNewFile();
			}
			ps = new PrintStream(new FileOutputStream(file), true,
					CharCodeConst.UTF8);
			ps.println(con);// 往文件里写入字符串
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
		}
	}

	public static String readFileToString(String filePath) {
		String str = "";
		File file = new File(filePath);
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			// size 为字串的长度 ，这里一次性读完
			int size = in.available();
			byte[] buffer = new byte[size];
			in.read(buffer);
			in.close();
			str = new String(buffer);
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("readFileToString---");
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
//					e.printStackTrace();
				}
		}
		return str;

	}
}
