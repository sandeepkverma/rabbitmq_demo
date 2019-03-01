package com.san;

import java.io.*;
import java.net.*;

public class MyClient2 {
	public static void main(String[] args) {
		try {
			Socket s = new Socket("localhost", 6666);
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			for (int i = 0; i < 1000000; i++) {
				Thread.sleep(12000);
				dout.writeUTF("Hello Server1");
				dout.flush();	
			}
			
			dout.close();
			s.close();
		} catch (Exception e) { 
			System.out.println(e);
		}
	}
}