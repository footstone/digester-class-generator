package me.footstone.tools.digester.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ClassGenerator {
	
	private String dir;
	
	public ClassGenerator(String dir){
		this.dir = dir;
	}
	
	/**
	 * 生成Class文件内容
	 * @param fileName
	 * @throws IOException 
	 */
	public void output(String fileName,ClassDefinition cd) throws IOException{
		File file = new File(fileName);
		if (!file.exists()){
			file.createNewFile();
		}
		FileOutputStream output = new FileOutputStream(file);
		// build java file content
		StringBuffer sb = new StringBuffer();
		
		sb.append("package me.footstone.tools.test").append(";");
		sb.append("\r\n").append("\r\n");
		sb.append("public class ").append(cd.getName()).append(" { ");
		sb.append("\r\n").append("\r\n");
		
		List<String> fields = cd.getFields();
		for (String attr:fields){
			sb.append("	private String ").append(attr).append(";")
			.append("\r\n").append("\r\n");
			
			sb.append("	public void set").append(GeneratorUtil.formatName(attr)).append("(").
			append("String ").append(attr).append("){").append("\r\n");
			sb.append("		this.").append(attr).append("=").append(attr).append(";").append("\r\n");
			sb.append("	}");
			sb.append("\r\n").append("\r\n");
			
			sb.append("	public String get").append(GeneratorUtil.formatName(attr)).append("(){")
			.append("\r\n");
			sb.append("		return this.").append(attr).append(";").append("\r\n");
			sb.append("	}");
			sb.append("\r\n").append("\r\n");
		}
		sb.append("\r\n");
		sb.append("}");
		
		
		output.write(sb.toString().getBytes());
		output.flush();
		output.close();
		
	}
	
	public static void main(String[] args) throws IOException {
		ClassDefinition cd = new ClassDefinition(null);
		cd.setName("Ruledefinition");
		cd.addField("engine_impl");
		cd.addField("interceptor");
		cd.addField("version");
		
		String fileName = "D:/workspace/rule/digester-pojo-generator/src/me/footstone/tools/test";
		ClassGenerator gen = new ClassGenerator(null);
		gen.output(fileName+"/"+cd.getName()+".java", cd);
		
		
	}

}
