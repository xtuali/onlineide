package org.webteam.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class XMLOperation {
	Document document = null;

	String url = "";

	public XMLOperation() {

	};

	public XMLOperation(String url) {
		SAXBuilder builder = new SAXBuilder();
		this.url = url;
		try {
			this.document = builder
					.build(new java.io.FileInputStream(this.url));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean readXML() {

		Element root = document.getRootElement();
		// doc.setRootElement(new Element("onlineIDE_update_by_ReadXML"));
		System.out.println(root.getAttributeValue("name"));

		System.out.println(root.getChild("project").getAttributeValue("name"));

		root.getChild("project").setAttribute("name", "onlineIDE");

		XMLOutputter out = new XMLOutputter();
		try {
			out.output(document, new java.io.FileOutputStream(this.url));
			// out.output(document, System.out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// doc.getRootElement().setName("inlineIDE");
		// System.out.println(doc.getRootElement().getName());
		// List<Element> list = root.getChildren();
		// System.out.println("节点个数为:" + list.size());
		// for (Element ele : list) {
		// // System.out.println(ele.getName());
		// System.out.println(ele.getChild("class").getText());
		//			
		// }
		System.out.println("遍历结束");

		// Element e3 = (Element)list.get(3);
		// Element e3 = list.get(3);
		// Element e3n = e3.getChild("姓名");
		// System.out.println(e3n.getText());

		// System.out.println(root.getAttributeValue("班主任"));
		return false;

	}

	public boolean addProject(String proname) {

		return false;
	}

	public static void main(String[] args) {
		XMLOperation read = new XMLOperation("d:/xtuali_pro.xml");
		read.readXML();
	}
}
