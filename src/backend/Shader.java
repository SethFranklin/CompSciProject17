package backend;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//import org.lwjgl.opengl.ARBShaderObjects;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class Shader {
	
	int ShaderProgram;
	
	Map<Uniform, UniformLocation> UniformMap = new HashMap<Uniform, UniformLocation>(); // Maps in java can't have primitive for some reason, so I made a simple uniform location class to act as one
	
	public enum Uniform
	{
		
		TRANSLATE_UNIFORM,
		ROTATE_UNIFORM,
		SCALE_UNIFORM,
		TRANSPARENCY_UNIFORM,
		ALPHA_UNIFORM,
		TEXTURE_UNIFORM,
		DIFFUSE_UNIFORM,
		NORMAL_UNIFORM,
		SPECULAR_UNIFORM,
		DEPTH_UNIFORM,
		LIGHT_UNIFORM,
		COLOR_UNIFORM
		
	}

	public Shader(String Name, Map<Uniform, String> Uniforms)
	{
		
		try
		{
			
			StringBuilder Builder = new StringBuilder(); // Why does this exist
			
			StringBuilder Builderv = new StringBuilder(); // kill me
			StringBuilder Builderf = new StringBuilder();
			
			String a = (new File(getClass().getProtectionDomain().getCodeSource().getLocation().toString())).getParent().substring(6);
			String b = URLDecoder.decode(a, "UTF-8");
			
			Builderv.append(b);
			Builderv.append("/data/shader/" + Name + ".vert");
			
			File Reader = new File(Builderv.toString());
			
			Scanner scanner = new Scanner(Reader);
			
			while (scanner.hasNextLine())
			{
				
				String Line = scanner.nextLine();
				Builder.append(Line);
				Builder.append(System.lineSeparator());
				
			}

			scanner.close();
			
			String VertexSource = Builder.toString();
			
			Builder = new StringBuilder(); // Why does this exist
			
			Builderf.append(b);
			Builderf.append("/data/shader/" + Name + ".frag");
			
			Reader = new File(Builderf.toString());
			
			scanner = new Scanner(Reader);
			
			while (scanner.hasNextLine())
			{
				
				String Line = scanner.nextLine();
				Builder.append(Line);
				Builder.append(System.lineSeparator());
				
			}

			scanner.close();
			
			String FragmentSource = Builder.toString();
			
			int VertexShader = glCreateShader(GL_VERTEX_SHADER);
			
			int FragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
			
			glShaderSource(VertexShader, VertexSource);
            glCompileShader(VertexShader);
            
            if (glGetShaderi(VertexShader, GL_COMPILE_STATUS) == GL_FALSE)
            {
            	
            	System.out.println("Vertex Shader (" + Name + ".vert) Compile Error:");
            	System.out.println(glGetShaderInfoLog(VertexShader));
            	
            }
            
            glShaderSource(FragmentShader, FragmentSource);
            glCompileShader(FragmentShader);
            
            if (glGetShaderi(FragmentShader, GL_COMPILE_STATUS) == GL_FALSE)
            {
            	
            	System.out.println("Fragment Shader (" + Name + ".frag) Compile Error:");
            	System.out.println(glGetShaderInfoLog(FragmentShader));
            	
            }
            
            ShaderProgram = glCreateProgram();
            
            glAttachShader(ShaderProgram, VertexShader);
            glAttachShader(ShaderProgram, FragmentShader);
            
            glLinkProgram(ShaderProgram);
            
            if (glGetProgrami(ShaderProgram, GL_LINK_STATUS) == GL_FALSE)
            {
            	
            	System.out.println("Shader Program (" + Name + ") Link Error:");
            	System.out.println(glGetProgramInfoLog(ShaderProgram));
            	
            }
            
            glDeleteShader(VertexShader);
            glDeleteShader(FragmentShader);
            
            // Getting uniforms set up
            
            for (Map.Entry<Uniform, String> entry : Uniforms.entrySet())
            {
            	
				UniformMap.put(entry.getKey(), new UniformLocation(glGetUniformLocation(ShaderProgram, entry.getValue())));
				
            }
            
		}
		catch (IOException Exception)
		{
			
			Exception.printStackTrace();
			
		}
		
	}
	
	public void Use()
	{
		
		glUseProgram(ShaderProgram);
		
	}
	
	public void Delete()
	{
		
		glDeleteProgram(ShaderProgram);
		
	}
	
	public void SetUniform(Uniform u, float a)
	{
		
		glUseProgram(ShaderProgram);
		glUniform1f(UniformMap.get(u).Get(), a); // Beautiful .get(u).Get()
		
	}
	
	public void SetUniform(Uniform u, float a, float b)
	{
		
		glUseProgram(ShaderProgram);
		glUniform2f(UniformMap.get(u).Get(), a, b);
		
	}
	
	public void SetUniform(Uniform u, int a)
	{
		
		glUseProgram(ShaderProgram);
		glUniform1i(UniformMap.get(u).Get(), a);
		
	}
	
	public void SetUniform(Uniform u, math.Vector2 a)
	{
		
		glUseProgram(ShaderProgram);
		glUniform2f(UniformMap.get(u).Get(), a.X, a.Y);
		
	}
	
	public void SetUniform(Uniform u, math.Vector2 a, float b)
	{
		
		glUseProgram(ShaderProgram);
		glUniform3f(UniformMap.get(u).Get(), a.X, a.Y, b);
		
	}
	
	public void SetUniform(Uniform u, math.Color3 a)
	{
		
		glUseProgram(ShaderProgram);
		glUniform3f(UniformMap.get(u).Get(), a.R, a.G, a.B);
		
	}
	
}
