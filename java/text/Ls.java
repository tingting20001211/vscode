import java.io.File;
import java.util.Arrays;

/*************************************************************************
  > File Name   : Ls.java
  > Author      : ting
  > Mail        : 1213728568@qq.com
  > Created Time: Mon 03 Aug 2020 03:38:21 PM CST
 ************************************************************************/

public class Ls{
    public static void main(String[] args){
        String dir = System.getProperty("user.dir");
        File[] files = dir.listFiles();

        System.out.println(Arrays.toString(file));
    }
}



public class Diroperation {
	static String str;
	static String filename;
	static String dirnow;//当前目录
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //获取控制台输入对象
		System.out.println("请输入命令"+'\n');
		do {
			str=br.readLine();//str等于从控制台读到的内容
			if(str.startsWith("cd")){
				dirnow = System.getProperty("user.dir");//获取当前的路径
				filename = str.substring(2).replace(" ", "");//获取用户输入的路径信息
				if(filename.equals("")) {
					System.out.println("目录名填写不合法,请重新输入！");
				}else {
					dirnow=filename;//把用户输入的路径信息变当前路径
					cdd();
				}
			}else if(str.startsWith("ls")) {
				String dirname = str.substring(2).replace(" ","");
				if(dirname.equals(" ")) {
					File dir = new File(dirnow);
					lss(dir);
				}else{
					File dir = new File(dirnow+File.separator+dirname);
					lss(dir);
				}
			}else if(str.startsWith("show")) {
				show();
			}else if(str.equals("end")){
				System.out.println("退出命令");
				return;
			}else {
				System.out.println("WrongOrder");
			}
		}while(!str.equals("end"));
	}

	private static void show() throws IOException {
		String filename = str.substring(4).replace(" ", "");
		File f = new File(dirnow+File.separator+filename);//文件所在地址
		FileInputStream in = new FileInputStream(f);//创建文件的读入对象
		byte[] b = new byte[1024];
		int temp = 0;
		int len = 0;
		while((temp=in.read())!=-1) {//=-1相当于读完了
			b[len] = (byte)temp;
			len++;
		}
		in.close();
		System.out.println(new String(b,0,len));
	}

	private static void cdd() {
		System.out.print("当前路径是:"+dirnow);
	}

	private static void lss(File dir) {
		File[] files = dir.listFiles();
		if(files!=null) {
			for(File f:files) {
				if(f.isDirectory()) {
					lss(f);
				}
				System.out.println(f);
			}
		}else {
			System.out.println("该文件夹为空");
		}
	}
}

