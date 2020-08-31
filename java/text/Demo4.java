import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import java.nio.file.Paths;

/*************************************************************************
  > File Name   : Demo4.java
  > Author      : ting
  > Mail        : 1213728568@qq.com
  > Created Time: Sun 02 Aug 2020 05:14:09 PM CST
 ************************************************************************/

public class Demo4{ 
    public static void main(String[] args) throws IOException
    {
        Scanner in = new Scanner(Paths.get("/root/Workplace/java/text/GetPath.java"),"UTF-8"); //读取文件内容--> 必须是已经存在的文件
        while(in.hasNextLine()){
            String str = in.nextLine();
            System.out.println(str);
        }
        in.close();

        PrintWriter out = new PrintWriter("xxx.txt", "UTF-8"); // 必须是不存在的文件
        for(int i=0;i<10;i++){
            out.println("fuck you~~~");
        }
        out.println("hello");
        out.flush();
        out.close();

        /*
        Scanner scanner = new Scanner(System.in);//构造一个Scanner对象, 并与标准输入流System.in关联
        System.out.println("What is your name?");
        String name = scanner.nextLine(); // nextLine 读取一行, 包含空格

        //如果不包含空格, 请使用 next方法
        String notspace = scanner.next();
        // 读取一个整数
        int age = scanner.nextInt();

        System.out.println("Hello, "+name+"!~");
        System.out.println("Hello, "+notspace+"!~");
        System.out.println(name +"的age是"+ age+ "!");
   

        
        //读取密码
        Console form = System.console();
        String username = form.readLine("User name: ");
        char[] passwd = form.readPassword("PassWord: "); // 这里输入的看不见
        
        System.out.println(username);       
        System.out.println(passwd);
        */
    }
}

