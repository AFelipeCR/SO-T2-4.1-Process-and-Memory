package edu.uptc.util;

public class Util {
	/**
	 * Verifica si el numero ingresado es potencia de 2
	 * @param n Numero a evaluar
	 * @return true si cumple
	 */
	public static boolean isPowerOfTwo(int n) {
		return (int) (Math.ceil((Math.log(n) / Math.log(2)))) == (int) (Math.floor(((Math.log(n) / Math.log(2)))));
	}
}
