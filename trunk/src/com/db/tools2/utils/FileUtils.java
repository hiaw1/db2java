package com.db.tools2.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

 /**
 @Author H.W
 @createTime 2012-9-2 下午04:54:41
 **/

public class FileUtils {
	
	
	private static Map<Integer, String> infoMap = new HashMap<Integer, String>();
	
	private static Locale locale = Locale.SIMPLIFIED_CHINESE;
	
	/**
	 * 操作成功
	 */
	private static int SUCCESS = 1;
	
	/**
	 * 操作失败
	 */
	private static int FAILED = 0;
	
	/**
	 * IO错误
	 */
	private static int IO_ERROR = -1;
	
	/**
	 * 文件己存�?
	 */
	private static int FILE_ALREADY_EXISTS = 2;
	
	/**
	 * 目录非空
	 */
	private static int DIRECTORY_NOT_EMPTY = 3;
	
	static{
		//加载信息文件提供获取错误码信息功�?
		ResourceBundle rb = ResourceBundle.getBundle("fileResource", locale);
		Enumeration<String> enumer = rb.getKeys();
		while(enumer.hasMoreElements()){
			String keyStr = enumer.nextElement();
			String value = rb.getString(keyStr);
			Integer key = Integer.parseInt(keyStr);
			infoMap.put(key, value);
		}
	}
	
	public static String getMessageInfo(Integer errorCode){
		return infoMap.get(errorCode);
	}
	

	
	
	
	/**
	 * 
	 * 删除，非空目录不删除 
	 * @param path
	 * @return
	 */
	public static Integer delete(String path){
		return delete(new File(path), false);
	}
	
	/**
	 * 
	 * 删除，非空目录不删除 
	 * @param path
	 * @return
	 */
	public static Integer delete(File file){
		return delete(file, false);
	}
	
	/**
	 * 
	 * 删除，非空目录直接删�?
	 * @param path
	 * @return
	 */
	public static Integer deleteAll(String path){
		return delete(path, true);
	}
	
	/**
	 * 
	 * 删除，非空目录直接删�?
	 * @param path
	 * @return
	 */
	public static Integer deleteAll(File file){
		return delete(file, true);
	}
	
	/**
	 * 
	 * 删除文件夹下�?��文件
	 * @param path
	 * @return
	 */
	public static Integer deleteFileByDir(String path){
		return deleteFileByDir(new File(path));
	}
	
	/**
	 * 
	 * 文件删除，对于非空目录进行提�?
	 * @param path
	 * @param notEmptyDir 非空目录是否直接删除
	 * @return
	 */
	public static Integer delete(String path, boolean notEmptyDir){
		File file = new File(path);
		return delete(file, notEmptyDir);
	}
	
	/**
	 * 
	 * 创建目录，如果有，则不创�?
	 * @param path
	 * @return
	 */
	public static int mkDir(String path){
		File file = new File(path);
		return mkDir(file);	
	}
	
	
	/**
	 * 
	 * 文件删除，对于非空目录进行提�?
	 * @param path
	 * @param notEmptyDir 非空目录是否直接删除
	 * @return
	 */
	public static Integer delete(File file, boolean notEmptyDir){
		if(file.isFile()){
			return getErrorCodeByBool(file.delete());
		}else if(file.isDirectory()){
			if(file.listFiles().length > 0){
				if(notEmptyDir){
					deleteAll(file);
				}else{
					return DIRECTORY_NOT_EMPTY;
				}
			}else{
				return getErrorCodeByBool(file.delete());
			}
		}
		return FAILED;
	}
	
	
	
	/**
	 * 
	 * 删除文件夹下�?��文件
	 * @param file
	 * @return
	 */
	public static Integer deleteFileByDir(File file){
		File[] files = file.listFiles();
		for(File tempFile:files){
			Integer errorCode = deleteAll(tempFile);
			System.out.println(tempFile.getAbsolutePath() + File.separator + tempFile.getName() + " ERRORCODE:" + getMessageInfo(errorCode));
		}
		return SUCCESS;
	}
	
	
	
	/**
	 * 
	 * 这里�?��描述方法 
	 * @param file
	 * @return
	 */
	public static Integer mkDir(File file){
		if(file.exists()){
			return FILE_ALREADY_EXISTS;
		}
		return getErrorCodeByBool(file.mkdir());
	}
	
	/**
	 * 
	 * 绝对创建目录，有子文件夹，或者子文件都删�?
	 * 然后创建目录
	 * @param path
	 * @return
	 */
	public static Integer absoluteMkDir(String path){
		File file = new File(path);
		if(file.exists()){
			FileUtils.deleteAll(path);
		}
		return mkDir(path);
	}
	
	
	/**
	 * 
	 * 绝对创建目录，有子文件夹，或者子文件都删�?
	 * 然后创建目录
	 * @param path
	 * @return
	 */
	public static Integer absoluteMkDir(File file){
		if(file.exists()){
			FileUtils.deleteAll(file);
		}
		return mkDir(file);
	}
	
	
	/**
	 * 
	 * 根据错误码得到错误信�?
	 * @param bool
	 * @return
	 */
	private static Integer getErrorCodeByBool(Boolean bool){
		if(bool){
			return 1;
		}else{
			return 0;
		}
	}
	
	/**
	 * 
	 * 得到文件的扩展名
	 * @param filePath
	 * @return
	 */
	public static String getFileExtensionFile(String fileName){
		int startIndex = fileName.lastIndexOf(".");
		if(startIndex > 0){
			fileName.substring(startIndex + 1, fileName.length());
		}
		return "";
	}
	
	/**
	 * 
	 * 从流生成文件 
	 * @param ins
	 * @param file
	 */
	public static void inputstream2File(InputStream ins,File file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		  os.close();
		  ins.close();
	  	} catch (Exception e) {
	  		e.printStackTrace();
	  	}
	 }
	
	
	/**
	 * 
	 * 写入内容到文件 
	 * @param content
	 * @param file
	 */
	public static void writeToFile(String content, String file){
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(new File(file)));
			 writer.write(content);
		     writer.close();
		} catch (IOException e) {
			LoggerUtils.getLogger().error(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * 创建文件
	 * @param destFileName
	 * @return
	 */
	public static boolean createFile(String destFileName){
		File file = new File(destFileName);
		if (file.exists()) {
			LoggerUtils.getLogger().error("创建单个文件" + destFileName + "失败，目标文件已存在！");
			return false;
		}
		   
		   
		if (destFileName.endsWith(File.separator)) {
			LoggerUtils.getLogger().error("创建单个文件" + destFileName + "失败，目标不能是目录！");
		    return false;
		}
		   
		if (!file.getParentFile().exists()) {
			LoggerUtils.getLogger().error("目标文件所在路径不存在，准备创建。。。");
			if (!file.getParentFile().mkdirs()) {
				LoggerUtils.getLogger().error("创建目录文件所在的目录失败！");
				return false;
			}
	   }
		   
		
		try {
		    if (file.createNewFile()) {
		    	LoggerUtils.getLogger().error("创建单个文件" + destFileName + "成功！");
			    return true;
		    }else{
		    	LoggerUtils.getLogger().error("创建单个文件" + destFileName + "失败！");
			    return false;
		    }
		}catch(IOException e) {
			LoggerUtils.getLogger().error("创建单个文件" + destFileName + "失败！" + e.getMessage(), e);
		    return false;
		}
	}
	
	public static void main(String[] ags){
		ResourceBundle rb = ResourceBundle.getBundle("fileResource");
		Enumeration<String> enumer = rb.getKeys();
		while(enumer.hasMoreElements()){
			String key = enumer.nextElement();
			System.out.println(key);
		}
	}
}
