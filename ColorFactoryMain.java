import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/*
Author: Michele Pierini
Date: 04/09/13
Program Name: ColorFactoryMain.java
Objective: Instantiates the ColorFactory class.
*/

import java.util.*;
import java.io.*;

public class ColorFactoryMain 
{
	//******************************mkWrapper()*******************************
	public static void mkWrapper()
	{
		String s = Thread.currentThread().getStackTrace()[1].getClassName();
		String wrapperName = s;
		File f = new File(wrapperName);
		if(!f.exists())
		{
			try
			{
				PrintWriter pw = new PrintWriter(f);
				pw.println("#!/bin/bash");
				pw.println("export CLASSPATH=.:$CLASSPATH");
                pw.println("java $0 ${1+\"$@\"}");
                pw.close();
             }catch(FileNotFoundException e){System.err.println(e);}
		}
		try
		{
			Process p;
			p = new ProcessBuilder("chmod", "+x", f.getAbsolutePath()).start();
			}catch(IOException e){System.err.println(e);}
		}
	
	public static void main(String[] args) 
	{
		mkWrapper();
		new ColorFactory();
	}
}
