package com.pajx.server.app.utils.file;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理文件上传的工具类
 * @author taller
 * 
 */
public class FileUtil {
	public FileUtil() {
	}
	/**
	 * 获取文件后缀名 并且改为小写
	 */
	public static String getFileSuffix(String fileName){
		String suffix;
		suffix=fileName.trim().substring(fileName.trim().lastIndexOf(".")+1).toLowerCase();
		return suffix;
	}
	/**
	 * 删除一个文件，如果是目录就删除目录和目录下的所有文件
	 */
	public static boolean deleteFile(File file){
		boolean ok=true;
		if(file.isFile()){
			//文件
			if(file.exists()){
				file.delete();
			}
		}else if(file.isDirectory()){
			//目录，递归删除
			if(file.listFiles()==null||file.listFiles().length==0){
				file.delete();
			}else{
				File[] f=file.listFiles();
				for(int i=0;i<f.length;i++){
					deleteFile(f[i]);
				}
			}
			file.delete();
		}
		return ok;
	}
	public static File createFileDir(File file) {
		File f;
		if (file.exists()) {
			// 路径已经存在
			f = file;
		} else {
			// 路径不存在
			if (file.mkdirs()) {
				// 如果创建成功
				f = file;
			} else {
				// 创建失败
				f = null;
			}
		}
		return f;
	}
	/**
	 * 替换路径中的/和\
	 */
	public static String changePath(String path){
		return path.replace("//", File.separator).replace("\\", File.separator).replace("/", File.separator);
	}
	/**
	 * 构造一个绝对路径
	 */
	public static File changePathToAbsolute(HttpServletRequest request,String path){
		return FileUtil.createFileDir(new File( request.getServletContext().getRealPath("/")+changePath(path)));
	}
	public static String writeFile(File image,String imageFileName,HttpServletRequest request,String path,String studentnum){
		  if(imageFileName==null||image==null){
			  return "";//如果没有上传照片,则返回一个空路径
		  }
		  //if("".equals(imageFileName)||imageFileName.lastIndexOf(".")==-1){
			//  return "";
		 // }
		  String newFileName=getNewFileName(imageFileName,studentnum);
		  FileUtil.changePathToAbsolute(request,path);//创建图片目录
		  path=FileUtil.changePath(path);//设置系统的目录分隔符
		  String photoUrl=path+newFileName;
		  try {
		   File upload =new File(request.getServletContext().getRealPath("/")+photoUrl);
		   if(upload.exists()){
			   //如果已经存在该图片，则删除，然后重新创建
			   upload.delete();
		   }
		   FileUtils.copyFile(image,upload);
		   return photoUrl;
		  }catch (IOException e){
		   //e.printStackTrace();
		   return null;
		  }
	
		 
	 }
    public static String getNewFileName(String fileName,String studentnum){
    	//给文件重新命名
    	String newFileName="";
        String fileExt=getFileSuffix(fileName);
		newFileName=studentnum+"."+fileExt;
    	return newFileName;
    }
    public static boolean deleteImage(HttpServletRequest request,String path){
    	//删除图片
    	if(path==null||"".equals(path)){
    	   return true;
    	}
    	File image=new File(request.getServletContext().getRealPath("/")+changePath(path));
    	if(!image.exists()){
    	   return true;
    	}
    	return deleteFile(image);
    }
}
