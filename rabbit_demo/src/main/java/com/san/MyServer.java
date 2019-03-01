package com.san;

import java.io.*;
import java.net.*;

public class MyServer {
	public static void main(String[] args) {
		ServerSocket ss = null;
		Socket s = null;
		try {
			 ss = new ServerSocket(6666);
			 s = ss.accept();// establishes connection
			DataInputStream dis = new DataInputStream(s.getInputStream());
			String str="";
			while(!str.equals("stop")) {
			str = (String) dis.readUTF();
			System.out.println("message= " + str);
			}
			
			ss.close();
		} catch (Exception e) { 
			System.out.println(e);
			try {
				s = ss.accept();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
