package com.springboot.learn.Utils;

import java.io.File;

public class FileUtil {
	
	/**
	 * 如果目录不存在，则创建目录。并且会连父目录一起创建
	 * 如果目录存在，则什么都不做。
	 * @param path
	 * @return
	 */
	public static boolean mkdirIfNotExist(String path) {
		File file = new File(path);
		if(!file.exists()){
			return file.mkdirs();
		}
		return true;
	}
	
	public static String getFileDir(String filepath) {
		return filepath.substring(0,filepath.lastIndexOf("/") + 1);
	}
	
	public static String getFileName(String filepath) {
		return filepath.substring(filepath.lastIndexOf("/") + 1);
	}
	
	public static void main(String[] args) {
		System.out.println(getFileName("cache/guild/images/2存款产品简介.png"));
		System.out.println(getFileDir("cache/guild/images/2存款产品简介.png"));
	}
	

}
