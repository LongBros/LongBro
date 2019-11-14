package com.longbro.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
* @Description:增量打包工具类
* @author:huasi_lin
* @date:2018年3月22日上午9:51:28
 */
public class patchPagUtil {

    public static String patchFile="C:/Users/Administrator/Desktop/packet/2019-11-15.txt";//补丁文件
      
//    public static String classPath="D:/workspace_git/lcfex-loan/target/lcfex-loan/";//class所在路径  
    public static String classPath="D:/apache-tomcat-8.5.35/webapps/LongMusic/";//class所在路径  
      
    public static String outputPath="C:/Users/Administrator/Desktop/packet/";//生成的增量输出放路径 
      
    public static String version="2019-11-15/";//增量包版本  
    
//    public static String proName="loan/";//项目名称
    public static String proName="/";//项目名称
      
    public static void main(String[] args) throws Exception {  
        copyFiles(getPatchFileList());  
    }  
      
    @SuppressWarnings("resource")
	public static List<String> getPatchFileList() throws Exception{
        List<String> fileList=new ArrayList<String>();  
        FileInputStream f = new FileInputStream(patchFile);   
        BufferedReader br=new BufferedReader(new InputStreamReader(f,"UTF-8"));  
        String line;
        while((line = br.readLine()) != null){
        	fileList.add(line);
//            if(line.indexOf("Index:")!=-1){
//                line=line.replaceAll(" ","");
//                line=line.substring(line.indexOf(":")+1,line.length());  
//                fileList.add(line);  
//            }  
        }   
        return fileList;  
    }  
      
    public static void copyFiles(List<String> list){  
          
        for(String fullFileName:list){  
            if(fullFileName.indexOf("src/main/java")!=-1){//对源文件目录下的文件处理  
            	//得到class文件物理路径
                String fileName=fullFileName.replace("src/main/java","WEB-INF/classes").replace(".java", ".class");
                String tempDesPath=fileName.substring(0,fileName.lastIndexOf("/"));  
                String desFilePathStr=outputPath+version+proName+tempDesPath;  
                String desFileNameStr=outputPath+version+proName+fileName;  
                File desFilePath=new File(desFilePathStr);  
                if(!desFilePath.exists()){  
                    desFilePath.mkdirs();  
                }  
                copyFile(classPath+fileName, desFileNameStr);  
                System.out.println("复制完成："+fileName);  
            }else if(fullFileName.indexOf("src/main/resources")!=-1){
            	//得到class文件物理路径
                String fileName=fullFileName.replace("src/main/resources","WEB-INF/classes");
                String tempDesPath=fileName.substring(0,fileName.lastIndexOf("/"));  
                String desFilePathStr=outputPath+version+proName+tempDesPath;  
                String desFileNameStr=outputPath+version+proName+fileName;  
                File desFilePath=new File(desFilePathStr);  
                if(!desFilePath.exists()){  
                    desFilePath.mkdirs();  
                }  
                copyFile(classPath+fileName, desFileNameStr);  
                System.out.println("复制完成："+fileName);  
            }else{//对普通目录的处理  
                String fileName=fullFileName.replaceAll("src/main/webapp/","");  
                String fullDesFileNameStr=outputPath+version+proName+fileName; //将要复制的文件全路径 
                String desFilePathStr=fullDesFileNameStr.substring(0,fullDesFileNameStr.lastIndexOf("/"));  
                File desFilePath=new File(desFilePathStr);  
                if(!desFilePath.exists()){  
                    desFilePath.mkdirs();  
                }  
                copyFile(classPath+fileName, fullDesFileNameStr);  
                System.out.println("复制完成："+fileName);  
            }  
              
        }  
          
    }  
  
    private static void copyFile(String sourceFileNameStr, String desFileNameStr) {  
        File srcFile=new File(sourceFileNameStr);  
        File desFile=new File(desFileNameStr);  
        try {  
            copyFile(srcFile, desFile);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
    
    public static void copyFile(File sourceFile, File targetFile) throws IOException {  
        BufferedInputStream inBuff = null;  
        BufferedOutputStream outBuff = null;  
        try {  
            // 新建文件输入流并对它进行缓冲  
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));  
            // 新建文件输出流并对它进行缓冲  
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));  
            // 缓冲数组  
            byte[] b = new byte[1024 * 5];  
            int len;  
            while ((len = inBuff.read(b)) != -1) {  
                outBuff.write(b, 0, len);  
            }  
            // 刷新此缓冲的输出流  
            outBuff.flush();  
        } finally {  
            // 关闭流  
            if (inBuff != null)  
                inBuff.close();  
            if (outBuff != null)  
                outBuff.close();  
        }  
    }  

}  