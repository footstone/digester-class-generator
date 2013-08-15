package me.footstone.tools.digester.generator;

import java.util.ArrayList;
import java.util.List;

public class ClassDefinition<T> {
	
	private String name;
	private String pkg;
	private List<String> fields = new ArrayList<String>();
	private List<ClassDefinition<T>> children = new ArrayList<ClassDefinition<T>>();
	
	public ClassDefinition(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPkg() {
		return pkg;
	}
	public void setPkg(String pkg) {
		this.pkg = pkg;
	}
	public List<ClassDefinition<T>> getChildren() {
		return children;
	}
//	public void set(List<ClassDefinition> fields) {
//		this.fields = fields;
//	}
	public void addChild(ClassDefinition<T> cd){
		this.children.add(cd);
	}
	
	public void addField(String field){
		this.fields.add(field);
	}
	
	public List<String> getFields(){
		return this.fields;
	}

}
