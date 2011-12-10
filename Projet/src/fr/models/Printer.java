package fr.models;

import java.io.IOException;

public class Printer
{
	private static String	emptyLine = "|                                                        |\n";
	private static String	filledLine = "+--------------------------------------------------------+\n";
	
	public static void writeEmpty()
	{
		System.out.print(emptyLine);
	}
	
	public static void writeFull()
	{
		System.out.print(filledLine);
	}
	
	public static void write(String text)
	{
		int len = text.length();
		int linesize = emptyLine.length() - 5;
		int nbline = (len - 1) / linesize;
		
		
		if (len == -1)
			len = 0;
		for (int line = 0; line <= nbline; ++line)
		{
			System.out.print("| ");
			for (int i = 0; i < emptyLine.length() - 5; ++i)
			{
				if (line * linesize + i < len)
					System.out.print(text.charAt(line * linesize + i));
				else
					System.out.print(" ");
			}
			System.out.print(" |\n");
		}
	}
	
	private static void write_centered(String tmp, int len)
	{
		int	nb_space;
		String text = tmp.substring(0);
		
		if (len < text.length())
			text = text.substring(0, len);
		nb_space = (len - text.length()) / 2;
		for (int i = 0; i < nb_space; ++i)
			text = " " + text + " ";
		if (text.length() < emptyLine.length() - 5)
			text += " ";
		Printer.write(text);
	}
	public static void writeCenter(String text)
	{
		int len = emptyLine.length() - 5;

		while (len < text.length())
		{
			write_centered(text, len);
			text = text.substring(len);
		}
		if (text.length() != 0)
			write_centered(text, len);
	}
	
	public static String ask(String question)
	{
		char	c;
		String rep = "";
		
		try
		{
			while (0 < System.in.available())
				if (System.in.read() <= 0)
					return rep;
			Printer.write(question);
			System.out.print("|   - ");
			while (0 < (c = (char)System.in.read()) && (c != '\n' && c != '\r'))
				rep += c;
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rep;
	}
	
	public static int askInt(String question)
	{
		String	rep = ask(question);
		return (rep == "" ? -1 : Integer.parseInt(rep));
	}
}
