import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;

/*************************************************************************
  > File Name   : Cat.java
  > Author      : ting
  > Mail        : 1213728568@qq.com
  > Created Time: Sun 02 Aug 2020 07:19:42 PM CST
 ************************************************************************/

public class Cat{
    public static void main(String[] args) throws IOException{
        if(args.length == 0){
            System.out.println("请输入路径名+文件名!!!");
        }else{
            int i = 1;
            System.out.println(args[0]+ "的文件内容如下: ");
            Scanner in = new Scanner(Paths.get(args[0]),"UTF-8"); //读取文件内容--> 必须是已经存在的文件
            while(in.hasNextLine()){
                String str = in.nextLine();
                System.out.println(i +".\t" + str);
                ++i;
            }
            in.close();    
        }   
    }
}
