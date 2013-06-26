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
		// ����Դ�ļ�(.java)���ڵ�·��
		String source_url = BASE_DIR + username + "/" + projectname + "/"
				+ SRC_DIR + Package2URL.package2url(packagename) + "/"
				+ classname;
		// ���ñ�����.class�ļ���ŵ�λ��
		String class_url = BASE_DIR + username + "/" + projectname + "/"
				+ BIN_DIR;
		// ������Ҫ���ص�jar�ļ�Ŀ¼
		String jardir = BASE_DIR + username + "/" + projectname + "/bin";
		// ������Ҫ���ص�lib�ļ�Ŀ¼
		String libdir = BASE_DIR + username + "/" + projectname + "/lib";
		String s_result = "";

		CreateDir.mkdir(class_url);
		// ��ȡ��ǰϵͳ��Java������
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		// ��ȡ��������������ȡ�����Ϣ��һ���б���
		DiagnosticCollector diagnostics = new DiagnosticCollector();
		// ��ȡ��׼�ļ��������ʵ�����ļ���������ʹ�ø����ļ���������ȡ�����Ϣ
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
				diagnostics, null, null);
		// ��ȡ��ʾ�����ļ����ļ�����
		Iterable compilationUnits = fileManager
				.getJavaFileObjectsFromStrings(Arrays.asList(source_url));
		List<String> url = Arrays.asList(source_url);
		for (String a : url)
			System.out.println(a);
		// ����-d��ʾ������ļ�λ��
		Iterable options = Arrays.asList("-d", class_url, "-classpath", libdir,
				"-classpath", jardir);

		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
				diagnostics, options, null, compilationUnits);
		// ִ�б�������
		boolean issuccess = task.call();
		System.out.println((issuccess) ? classname + "����ɹ�" : classname
				+ "����ʧ��");
		if (!issuccess) {
			// �������������ȡ����������Ϣ
			List<Diagnostic> list = diagnostics.getDiagnostics();
			Iterator<Diagnostic> iterator = list.iterator();
			String info = classname + "����ʧ�ܣ�ԭ�����£�\n";
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
			s_result += "\n"+errorCount+" ����";
		} else {
			s_result = classname + "����ɹ�\n";
		}
		return new Object[] { s_result };
	}
}
