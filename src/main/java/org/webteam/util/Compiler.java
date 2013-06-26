package org.webteam.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.webteam.io.CreateDir;

public class Compiler {

	private static final String BASE_DIR = ReadConfig.readValue("BaseDir");

	private static final String BIN_DIR = ReadConfig.readValue("bin");

	private static final String SRC_DIR = ReadConfig.readValue("src");

	@SuppressWarnings("unchecked")
	public static Object[] compilersource(String username, String projectname,
			String packagename, String classname) {
		// 生成源文件(.java)所在的路径
		String source_url = BASE_DIR + username + "/" + projectname + "/"
				+ SRC_DIR + Package2URL.package2url(packagename) + "/"
				+ classname;
		// 配置编译后的.class文件存放的位置
		String class_url = BASE_DIR + username + "/" + projectname + "/"
				+ BIN_DIR;
		// 配置需要加载的jar文件目录
		String jardir = BASE_DIR + username + "/" + projectname + "/bin";
		// 配置需要加载的lib文件目录
		String libdir = BASE_DIR + username + "/" + projectname + "/lib";
		String s_result = "";

		CreateDir.mkdir(class_url);
		// 获取当前系统的Java编译器
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		// 获取监听器，用来获取诊断信息至一个列表中
		DiagnosticCollector diagnostics = new DiagnosticCollector();
		// 获取标准文件管理的新实例，文件管理器将使用给定的监听器来获取诊断信息
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
				diagnostics, null, null);
		// 获取表示给定文件的文件对象
		Iterable compilationUnits = fileManager
				.getJavaFileObjectsFromStrings(Arrays.asList(source_url));
		List<String> url = Arrays.asList(source_url);
		for (String a : url)
			System.out.println(a);
		// 参数-d表示存放类文件位置
		Iterable options = Arrays.asList("-d", class_url, "-classpath", libdir,
				"-classpath", jardir);

		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
				diagnostics, options, null, compilationUnits);
		// 执行编译任务
		boolean issuccess = task.call();
		System.out.println((issuccess) ? classname + "编译成功" : classname
				+ "编译失败");
		if (!issuccess) {
			// 如果编译错误，则获取编译的诊断信息
			List<Diagnostic> list = diagnostics.getDiagnostics();
			Iterator<Diagnostic> iterator = list.iterator();
			String info = classname + "编译失败，原因如下：\n";
			int errorCount = 0;
			while (iterator.hasNext()) {

				Diagnostic diagnostic = iterator.next();

				String source = diagnostic.getSource().toString();

				source = source.substring((BASE_DIR + username).length(),
						source.length());

				String message = diagnostic.getMessage(null);

				message = message.substring((BASE_DIR + username).length(),
						message.length());
				info = info + "Source:" + source + "\nMessage:" + message
						+ "\n";
				info = info.replace("'", "");
				s_result = s_result + info;
				errorCount++;
			}
			s_result += "\n"+errorCount+" 错误";
		} else {
			s_result = classname + "编译成功\n";
		}
		return new Object[] { s_result };
	}
}
