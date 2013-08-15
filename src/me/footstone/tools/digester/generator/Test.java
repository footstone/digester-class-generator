package me.footstone.tools.digester.generator;

import java.io.File;

public class Test {
	
	public  static void print(File mFile, int mlevel){
        for(int i = 0; i < mlevel; i++){
            System.out.print("\t");
        }
        if (mFile.isDirectory()){            
            System.out.println("<" + getPath(mFile) + ">");    
            String[] str = mFile.list();
            for (int i = 0; i < str.length; i++){
                print(new File(mFile.getPath() + "\\" + str[i]) , mlevel + 1);
            }            
        }else{
            System.out.println(getPath(mFile));
        }        
    }
    
    public  static String  getPath(File mFile){
        String fullPath = mFile.getPath();
        String[] str = fullPath.split("\\\\");
        return str[str.length - 1];
    }
    
    public static void main(String[] args) {
		String path = "E:/Books";
		print(new File(path),0);
	}

}
