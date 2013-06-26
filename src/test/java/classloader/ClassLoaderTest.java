package classloader;

public class ClassLoaderTest {
	public static void main(String[] args) {
		System.out.println(ClassLoaderTest.class.getClassLoader().getResource("").toString());
		ClassLoader loader = ClassLoaderTest.class.getClassLoader();
		while(loader.getParent() != null){
			System.out.println(loader.getClass().getCanonicalName());
			loader = loader.getParent();
		}
		
	}
}
