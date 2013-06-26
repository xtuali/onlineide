package org.webteam.util;

public class Package2URL {
	public static String package2url(String packagename){
	//	System.out.println("packagename:" + packagename);
		packagename = packagename.replace('.', ',');
		String[] pack_arr = packagename.split(",");
	//	System.out.println(pack_arr.length);
		StringBuffer child_dir = new StringBuffer();
		for (String pack : pack_arr) {
			child_dir.append("/").append(pack);
		}
		child_dir.replace(0, 1, "");
		// child_dir = child_dir + "/";
	//	System.out.println(child_dir);
		return child_dir.toString().trim();
	}
	
	public static void main(String[] args) {
		System.out.println(package2url("org.webteam.test"));
	}
}
