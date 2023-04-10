package com.projNinja.Utils;

public class systemProperty {

	public static void main(String[] args) {
		// will show list of all property fileds like OS, JAVA versiin
			System.getProperties().list(System.out);
			
			System.out.println(System.getProperty("os.name"));
			System.out.println(System.getProperty("os.version"));
			System.out.println(System.getProperty("user.name"));
			System.out.println(System.getProperty("java.version"));


}}
