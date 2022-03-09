package edu.uptc.util.logger;

/**
 * Logger de la aplicación
 * @author Felipe
 *
 */
public interface Logger {
	public static void log(Object... elements) {
		String[] r = new String[elements.length];
		
		for (int i = 0;i < elements.length;i++) {
			r[i] = elements[i].toString();
		}
		
		System.out.println(String.join(" ", r));
	}
}
