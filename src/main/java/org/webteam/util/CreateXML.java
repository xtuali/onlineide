package org.webteam.util;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class CreateXML {

	public boolean createXML() {
		Document document = new Document();
		Element root = new Element("onlineIDE");
		document.setRootElement(root);

		root.addContent(new Element("ѧ��").setAttribute("id", "00001")
				.addContent(new Element("����").setText("����"))
				.addContent(new Element("�Ա�").setText("Ů"))
				.addContent(new Element("����").setText("10")));

		root.addContent(new Element("ѧ��").setAttribute("id", "00002")
				.addContent(new Element("����").setText("����"))
				.addContent(new Element("�Ա�").setText("��"))
				.addContent(new Element("����").setText("102")));

		XMLOutputter out = new XMLOutputter();
		//out.setFormat(Format.getPrettyFormat().setEncoding("GBK"));
		try {
			out.output(document, new java.io.FileOutputStream("d:/1.xml"));
			out.output(document, System.out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ����һ������ָ��
//		Document jdom = new Document(new Element("root"));
//		ProcessingInstruction pi = new ProcessingInstruction("xml-stylesheet",
//				"type='text/xsl'href='form.xsl'");
//		jdom.addContent(pi);

		return false;
	}

	public static void main(String[] args) {
		CreateXML create = new CreateXML();
		create.createXML();
	}
}
