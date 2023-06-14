package com.example.api.IO;

import java.io.File;

/**
 * 此类 主要用于演示file类的使用
 *
 * File 类在java中是代表一个文件或者一个路径
 * 即文件和目录路径名的抽象表示形式，与平台无关
 *
 * File 能新建、删除、重命名文件和目录，
 * 但 File 不能访问文件内容本身。如果需要访问文件内容本身，则需要使用输入/输出流。
 *
 * File对象可以作为参数传递给流的构造函数
 * @author lwj
 * @version 1.00
 * @time 2020/6/22 0022  上午 11:31
 */
public class FileTest {

    /**
     * 创建一个代表目录路径的file对象并创建目录文件夹
     *
     */

    public void createFile(){
        File file = new File("E:\\io-test");
        //判断路径是否存在
        boolean exists = file.exists();
        System.out.println("文件是否存在："+ file.exists());
        //如果不存在 就创建
        if(!exists){
            //创建一级目录
            file.mkdir();
            System.out.println("文件是否存在："+ file.exists());
        }
    }


    /**
     * File类的一些操作
     *
     * 访问文件：
     * getName()
     * getPath()
     * getAbsoluteFile()
     * getAbsolutePath()
     * getParent()
     * renameTo(File newName)
     *
     *
     * 文件检测
     * exists()
     * canWrite()
     * canRead()
     * isFile()
     * isDirectory()
     *
     * 获取常规文件信息
     * lastModified()
     * length()
     *
     *
     * 文件操作相关
     * createNewFile()
     * delete()
     *
     *
     * 目录操作相关
     * mkDir()
     * mkDirs()
     * list()
     * listFiles()
     */
    public void fileApi(){

        File file = new File("E:\\FileApi");
        String name = file.getName();
        System.out.println("文件名："+name);

        String path = file.getPath();
        System.out.println("文件的路径为："+ path);

        String absolutePath = file.getAbsolutePath();
        System.out.println("文件的绝对路径为："+ absolutePath);

        
        //获取类路径下的1.txt
        File file1 = new File("src\\main\\resources\\fileDir\\1.txt");
        String name1 = file1.getName();
        long length = file1.length();
        System.out.println("文件的长度为："+length);
        System.out.println("文件名：" + name1);
        //绝对路径是从盘符开始的路径
        String absolutePath1 = file1.getAbsolutePath();
        System.out.println("绝对路径为："+ absolutePath1);
        //path 为创建时候写的路径
        String path1 = file1.getPath();
        System.out.println("路径为："+ path1);
    }


    /**
     * 使用递归方法 打印出某个文件夹下已.java 结尾的文件
     *
     */
    public void diguiTest(){
        //D:\IdeaWork\activiti-atcrowding
        File file = new File("D:\\IdeaWork\\activiti-atcrowding");
        //获取路径下的所有路径或者文件
        digui(file);

    }

    private void digui(File file) {
        File[] files = file.listFiles();

        //遍历循环
        for (File file1 : files) {
            //如果不是文件 是目录
            if(!file1.isFile()){
               digui(file1);
            }else {
                //如果是文件 判断是否以 .java 结尾
                if(file1.getName().endsWith(".java")){
                    System.out.println(file1.getName());
                }
            }
        }
    }

}
