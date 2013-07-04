package org.webteam.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.webteam.util.ReadConfig;

public class CreateUserSpace {

	public void createUserSpace(String username) {
		Document document = new Document();
		Element root = new Element("user");
		CreateDir.mkdir(ReadConfig.readValue("BaseDir")+username+"/");
		String child_url = username + "_pro.xml";
		document.setRootElement(root);
		root.setAttribute("name", username);
		root.addContent("");
		XMLOutputter out = new XMLOutputter();
		// out.setFormat(Format.getPrettyFormat().setEncoding("GBK"));
		try {
			out.output(document, new java.io.FileOutputStream(new File(
					ReadConfig.readValue("BaseDir")+username+"/", child_url)));
			out.output(document, System.out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 生成一条处理指令
		// Document jdom = new Document(new Element("root"));
		// ProcessingInstruction pi = new
		// ProcessingInstruction("xml-stylesheet",
		// "type='text/xsl'href='form.xsl'");
		// jdom.addContent(pi);

	}

	public static void main(String[] args) {
		CreateUserSpace cus = new CreateUserSpace();
		cus.createUserSpace("xtuali");
	}
}
