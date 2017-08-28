package backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.ByteBuffer;

import backend.PNGDecoder.Format;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL30.*;

public class Texture {

	public int Width, Height, ID;
	
	public Texture(String Name)
	{
		
		try
		{
			
			StringBuilder Builder = new StringBuilder();
			
			String a = (new File(getClass().getProtectionDomain().getCodeSource().getLocation().toString())).getParent().substring(6);
			String b = URLDecoder.decode(a, "UTF-8");
			
			Builder.append(b);
			Builder.append("/data/texture/" + Name + ".png");
			
			InputStream Input = new FileInputStream(new File(Builder.toString()));
			
			PNGDecoder Decoder = new PNGDecoder(Input);
			
			Width = Decoder.getWidth();
			Height = Decoder.getHeight();
			
			ByteBuffer Buffer = ByteBuffer.allocateDirect(4 * Decoder.getWidth() * Decoder.getHeight());
			Decoder.decode(Buffer, Decoder.getWidth() * 4, Format.RGBA);
			
			Buffer.flip();
			Input.close();
			
			ID = glGenTextures();
			
			glBindTexture(GL_TEXTURE_2D, ID);
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, Width, Height, 0, GL_RGBA, GL_UNSIGNED_BYTE, Buffer);
			glGenerateMipmap(GL_TEXTURE_2D);
			
		}
		catch (IOException Exception)
		{
			
			Exception.printStackTrace();
			
		}
		
	}
	
	public void Use(int Slot)
	{
		
		glActiveTexture(GL_TEXTURE0 + Slot);
		glBindTexture(GL_TEXTURE_2D, ID);
		
	}
	
	public void Delete()
	{
		
		glBindTexture(GL_TEXTURE_2D, 0);
		glDeleteTextures(ID);
		
	}
	
}
