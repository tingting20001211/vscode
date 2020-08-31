import java.io.File;

/*************************************************************************
  > File Name   : Mkdir.java
  > Author      : ting
  > Mail        : 1213728568@qq.com
  > Created Time: Mon 03 Aug 2020 12:02:34 PM CST
 ************************************************************************/

public class Mkdir{
    public static void main(String[] args){
        if(args.length == 0)  System.out.println("请输入创建的路径+文件夹~~");
        else{
            File file = new File(args[0]); 
            if(file.exists())  System.out.println("该文件已经存在~");
            else{
                if(file.mkdirs())  System.out.println("创建文件夹成功~");
                else System.out.println("创建文件夹失败~");
            }
        }
    }
}




