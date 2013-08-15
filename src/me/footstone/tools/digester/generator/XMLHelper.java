package me.footstone.tools.digester.generator;

import java.io.InputStream;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class XMLHelper {
	
	private String fileName;
	
	public XMLHelper(String fileName){
		this.fileName = fileName;
	}
	
	public String getFileName(){
		return this.fileName;
	}

	public String getRootName(){
		
		
		return null;
	}
	
	public ClassDefinition build(){
		String rootName = "root";
		
		
		return null;
	}
	
	public static ClassDefinition trans(Element e){
		
		if (e!=null && e.getChildren()!=null && e.getChildren().size()>0){
			List<Element> elements = (List<Element>)e.getChildren();
			for (int i=0,len=elements.size();i<len;i++){
				Element element = elements.get(i);
				String className = GeneratorUtil.formatName(element.getName());
				ClassDefinition cd = new ClassDefinition(className);
				List<Attribute> attrs = element.getAttributes();
				for (Attribute attr:attrs){
					cd.addField(attr.getName());
				}
				List<Element> children = element.getChildren();
				for(Element child:children){
					cd.addChild(new ClassDefinition(GeneratorUtil.formatName(child.getName())));
				}
				
				System.out.println(element.getName());
				
				trans(element);
			}
			//for (Element element:elements){
				
			//}
		}
		
//		while(e!=null && e.getChildren()!=null && e.getChildren().size()>0){
//			List<Element> elements = (List<Element>)e.getChildren();
//			for (Element e1:elements){
//				System.out.println(" - "+e1.getName());
//				trans(e1);
//				e = e1;
//				//System.out.println(e.getSerializedForm());
//			}
//		}
		
		return null;
	}
	
	
	
	public static void main(String[] args) throws Exception {
	//	FileInputStream file = new FileInputStream(new File("dependency.xml"));
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("dependency.xml");
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(input);
		Element root = doc.getRootElement();
		//System.out.println(root.getName());
		
		trans(root);
		
//		while(root.getChildren()!=null && root.getChildren().size()>0){
//			System.out.println(root.getName());
//			Element e = null;
//			for (Object obj:root.getChildren()){
//				 e = (Element)obj;
//				//System.out.println(e.getName());
//				
//				System.out.println(" - "+e.getName());
//			}
//			root = e;
//			System.out.println("---");
//		}
		
//		List<Attribute> attributes = root.getAttributes();
//		
//		String rootName = GeneratorUtil.formatName(root.getName());
//		ClassDefinition rootClass = new ClassDefinition(rootName);
//		
//		
//		for (Attribute attr:attributes){
//			//System.out.println(attr.getName()+" ");
//			rootClass.addField(attr.getName());
//		}
//		
//		
//		List<Element> children = (List<Element>)root.getChildren();
//		for (Element e:children){
//			//System.out.println(e.getName()+" ");
//		//	rootClass.addChild(cd);
//			String eName = GeneratorUtil.formatName(e.getName());
//			
//		}
	}
}
