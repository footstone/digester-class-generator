package me.footstone.tools.digester.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * 
 * 
 * @author footstone
 *
 */
public class ClassGenerator {
	
	private String dir;
	private String pkg;
	public static final String FILE_SPLIT = File.separator  ;
	
	private volatile static ClassGenerator instance = null;
	
	private ClassGenerator(){}
	
	/**
	 * @param dir
	 * @param pkg
	 * @return
	 */
	public static ClassGenerator getInstance(String dir,String pkg){
		if (instance == null){
			synchronized(ClassGenerator.class){
				if (instance == null){
					instance = new ClassGenerator();
					instance.dir = dir;
					instance.pkg = pkg;
				}
			}
		}
		return instance;
	}
	
	/**
	 * @param element
	 * @throws IOException
	 */
	public void generator(Element element) throws IOException{
		if (element == null){
			return;
		}
		String classDir = dir + FILE_SPLIT + pkg.replaceAll("\\.", "/");
		File dirFile = new File(classDir);
		if (!dirFile.exists()){
			dirFile.mkdirs();
		}
		
//		List<Element> list = element.getChildren();
//		for(Element node:list){
		String nodeName = element.getName();
		String fileName = GeneratorUtil.formatName(nodeName);
		
		String filePath = classDir + FILE_SPLIT + fileName + ".java";
		
		System.out.println("create:"+filePath);
		output(filePath,element);
		
		List<Element> children = element.getChildren();
		if (children!=null){
			for (Element e:children){
				generator(e);
			}
		}
	//	}
		
	}
	
	/**
	 * 
	 * @param fileName
	 * @param element
	 * @throws IOException 
	 */
	private void output(String filePath,Element element) throws IOException{
		File file = new File(filePath);
		if (!file.exists()){
			file.createNewFile();
		}
		
		String className = GeneratorUtil.formatName(element.getName());
		
		FileOutputStream output = new FileOutputStream(file);
		// build java file content
		StringBuffer sb = new StringBuffer();
		
		sb.append("package ").append(this.pkg).append(";");
		sb.append("\r\n").append("\r\n");
		sb.append("import java.util.List;");
		sb.append("\r\n");
		sb.append("import java.util.ArrayList;");
		sb.append("\r\n").append("\r\n");
		
		
		//sb.append("\r\n").append("\r\n");
		sb.append("public class ").append(className).append(" { ");
		sb.append("\r\n").append("\r\n");
		
		// fields
		//List<String> fields = cd.getFields();
		List<Attribute> attrs = element.getAttributes();
		for (Attribute attr:attrs){
			String fieldName = GeneratorUtil.toLowerFirstCase(attr.getName());
			String mFieldName = GeneratorUtil.toUpperFirstCase(fieldName);
			
			sb.append("	private String ").append(fieldName).append(";")
			.append("\r\n").append("\r\n");
			
			sb.append("	public void set").append(mFieldName).append("(").
			append("String ").append(fieldName).append("){").append("\r\n");
			sb.append("		this.").append(fieldName).append("=").append(fieldName).append(";").append("\r\n");
			sb.append("	}");
			sb.append("\r\n").append("\r\n");
			
			sb.append("	public String get").append(mFieldName).append("(){")
			.append("\r\n");
			sb.append("		return this.").append(fieldName).append(";").append("\r\n");
			sb.append("	}");
			sb.append("\r\n").append("\r\n");
		}
		sb.append("\r\n");
		
		//
		List<Element> children = element.getChildren();
		if (children != null){
			List doneList = new ArrayList();
			for (Element child:children){
				String childName = child.getName();
				if (doneList.contains(childName)){
					continue;
				}
				String childClassName = GeneratorUtil.formatName(childName);
				String attrName = GeneratorUtil.toLowerFirstCase(childClassName);
				String attrsName = attrName+"s"; 
				
				sb.append("	private List ").append(attrsName).append(" = new ArrayList();").append("\r\n");
				
				sb.append("	public void add").append(childClassName).append(" (").append(childClassName).append(" ").append(attrName).append("){").append("\r\n");
				sb.append("		this.").append(attrsName).append(".add").append("(").append(attrName).append(");").append("\r\n");
				sb.append("	}");
				sb.append("\r\n").append("\r\n");
				
				
				sb.append("	public List").append(" get").append(childClassName).append("s(){").append("\r\n");
				//sb.append("this.add(").append(attr.getName()).append(")");
				sb.append("		return this.").append(attrsName).append(";").append("\r\n");
				sb.append("	}");
				sb.append("\r\n").append("\r\n");
				
				doneList.add(childName);
			}
		}
		sb.append("}");
		
		output.write(sb.toString().getBytes());
		output.flush();
		output.close();
	}
	
	public static void main(String[] args) throws JDOMException, IOException {
		if (args == null || args.length != 3){
			System.out.println("---------------------------------");
			System.out.println("You must set 3 input parameter:");
			System.out.println("class-directory,class-package,xmlfile-name");
			System.out.println("---------------------------------");
			System.exit(-1);
		}
		String dir = args[0];
		String pkg = args[1];
		String xml = args[2];
		ClassGenerator cg = ClassGenerator.getInstance(dir, pkg);
		//InputStream input = new FileInputStream(new File(xml));
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(xml);
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(input);
		Element root = doc.getRootElement();
		System.out.println("--------------START-------------");
		cg.generator(root);
		System.out.println("--------------DONE-------------");
	}
	
	public static void main2(String[] args) throws JDOMException {
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("rule-conf.xml");
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(input);
		Element root = doc.getRootElement();
		List<Attribute> attrs = root.getAttributes();
		for(Attribute attr:attrs){
			System.out.println(attr.getName());
		}
	}
	
}
