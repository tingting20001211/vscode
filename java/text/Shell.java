/*************************************************************************
  > File Name   : Ls.java
  > Author      : ting
  > Mail        : 1213728568@qq.com
  > Created Time: Mon 03 Aug 2020 03:38:21 PM CST
 ************************************************************************/
import java.io.*;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Shell{
    public static void main (String[] args) throws IOException {
        //echo  pwd  ls  mkdir  cp   cat  cd  grep
        Scanner in = new Scanner(System.in);
        String temp = null;
        label:while(in.hasNext()){
            temp = in.nextLine();
            String[] split = temp.split(" ");
            switch (split[0]){
                case "pwd"  : pwd(split);  break;
                case "ls"   : ls(split);   break;
                case "mkdir": mkdir(split);break;
                case "cat"  : cat(split);  break;
                case "cd"   : cd(split);   break;
                case "cp"   : cp(split);   break;
                case "echo" : echo(temp); break;
                case "grep" : grep(split); break;
                case "exit" : break label; //直接跳出去
                default: System.out.println("请输入正确的命令!");
            }
        }
        in.close();
    }
    /**
     * 更新路径,当输入路径为../../../xxx路径时,此时"user.dir"中会存在../../的记号,需要去处该记号
     * 但是需要注意,当../../的数目过多时,需要判断是否已经到达根目录了,而且根目录后必须添加"/",否则可能出错.
     * @param str-path : 传进来的是当前的路径,需要将路径化简.
     * @return new-path : 化简后的路径
     * @throws IOException
     */
    public static String updatePath(String str){
        String[] split = str.split("[\\\\/]");
        ArrayList<String> paths = new ArrayList<>();
        for(String i : split){
            if(i.equals("..") && paths.size() != 1){
                paths.remove(paths.size()-1);
            }else paths.add(i);
        }
        if(paths.size() == 1) return paths.get(0)+"/";
        String path = String.join("/", paths);
        return path.equals("C:/..")?"C:/":path;
    }

    /**
     * 获取当前路径
     * @param str 该参数只能有一个pwd
     * @throws IOException
     */
    public static void pwd(String[] str)throws IOException{
        System.out.println(System.getProperty("user.dir"));
    }

    /**
     * 获取当前文件夹下的目录(可以改进,获取某个文件夹下的路径)
     * @param str 只有一个ls
     * @throws IOException
     */
    public static void ls(String[] str)throws IOException{
        File dir = new File(System.getProperty("user.dir"));
        String[] fileList = dir.list();
        if (fileList == null) System.out.println("当前目录为空");
        else {
            for (String s : fileList) System.out.print(s + "\t");
            System.out.println();
        }
    }

    /**
     * 创建一个文件夹
     * @param str
     * @throws IOException
     */
    public static void mkdir(String[] str)throws IOException{
        if(str.length == 1) System.out.println("请输入要创建文件夹的路径!");
        else{
            File file = new File(str[1]);
            if (file.exists() && file.isDirectory()) System.out.println("文件夹已经存在");
            else file.mkdirs();
        }
    }

    /**
     * 打印某个目录下的文件
     * @param str 第二个常数是路径名
     * @throws MalformedInputException
     * @throws IOException
     */
    public static void cat(String[] str)throws MalformedInputException,IOException{
        if(str.length == 1)  System.out.println("请输入文件名!");
        else{
            File file = new File(System.getProperty("user.dir")+"\\"+str[1]);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String info = null;
            int i = 1;
            while ((info = reader.readLine()) != null)
                System.out.println(i++ +". \t" + info);
            reader.close();
        }
    }

    /**
     * 切换目录
     * @param directoryName
     */
    public static void cd(String[] directoryName) {
        if(directoryName[1].equals("..") || directoryName[1].equals("../")){
            File directory = new File(System.getProperty("user.dir"));
            if(directory.getParentFile()==null){
                System.out.println("文件夹不存在");
                return;
            }
            directory = directory.getParentFile();
            System.setProperty("user.dir", directory.getAbsolutePath());
        }else if(directoryName[1].equals("~") || directoryName[1].equals("/")){
            File tempDirectory = new File(System.getProperty("user.dir"));
            File directory = null;
            while(tempDirectory != null){
                directory = tempDirectory;
                tempDirectory = tempDirectory.getParentFile();
            }
            System.setProperty("user.dir", directory.getAbsolutePath());
        } else{
            File directory = new File(System.getProperty("user.dir")+"\\"+directoryName[1]);
            if(directory.exists() && directory.isDirectory())
                System.setProperty("user.dir", directory.getAbsolutePath());
            else System.out.println("文件夹不存在");

            String newPaths = updatePath(System.getProperty("user.dir"));
            System.setProperty("user.dir", newPaths);
        }
    }

    /**
     * 复制文件夹
     * @param str
     * @throws IOException
     */
    public static void cp(String[] str)throws IOException{
        ArrayList<String> content = new ArrayList<>();
        File file_Read = new File(System.getProperty("user.dir")+"\\"+str[1]);//只能获取当前文件夹下的文件
        BufferedReader reader = new BufferedReader(new FileReader(file_Read));
        String info = null;
        while ((info = reader.readLine()) != null)
            content.add(info);
        reader.close();

        File file_Write = new File(System.getProperty("user.dir")+"\\"+str[2]);
        if(!file_Write.createNewFile()){
            System.out.println("文件创建失败");
            return;
        }

        FileWriter fileWriter = new FileWriter(file_Write.getAbsoluteFile());
        BufferedWriter writer = new BufferedWriter(fileWriter);
        for(String i : content)
            writer.write(i+'\n');
        writer.close();
    }

    /**
     * 打印字符串
     * @param str
     * @throws IOException
     */
    public static void echo(String str)throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 5; i < str.length(); i++) {
            char temp = str.charAt(i);
            if (temp == '\"') {
                i++;
                while (i < str.length()) {
                    char tempMatch = str.charAt(i);
                    if (tempMatch == '\"') {
                        temp = '\n';
                        break;
                    }
                    sb.append(tempMatch);
                    i++;
                }
            } else if (temp == '\'') {
                i++;
                while (i < str.length()) {
                    char tempMatch = str.charAt(i);
                    if (tempMatch == '\'') {
                        temp = '\n';
                        break;
                    }
                    sb.append(tempMatch);
                    i++;
                }
            } else if (temp == ' ')
                temp = '\n';
            sb.append(temp);
        }
        System.out.println(sb.toString());
    }

    /**
     * 查找某个文件夹下的匹配的内容
     * @param str 第二个参数是查找的内容(没试过正则匹配),一般第二个参数
     * @throws IOException
     */
    public static void grep(String[] str)throws IOException{
        //grep test *file
        ArrayList<String> content = new ArrayList<>();
        File file = new File(System.getProperty("user.dir")+"\\"+str[2]);
        if(!file.exists()) {
            System.out.println("文件不存在!");
            return;
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String info = null;
        while ((info = reader.readLine()) != null)
            content.add(info);
        reader.close();

        Pattern match_str = Pattern.compile(str[1]);
        for(String i : content){
            if(match_str.matcher(i).find())
                System.out.println(i);
        }
    }
}