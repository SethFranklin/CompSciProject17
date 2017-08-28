package backend;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL15;

//import static org.lwjgl.opengl.ARBVertexArrayObject.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class GameObject {

	protected Texture texture;
	protected Shader shader;
	
	static int VertexArrayObject, VertexBufferObject;
	
	static float[] Verticies =
	{	
		
		0.5f, -0.5f,
		1.0f, 0.0f,
		-0.5f, -0.5f,
		0.0f, 0.0f,
		-0.5f,  0.5f,
		0.0f, 1.0f,
		0.5f,  0.5f,
		1.0f, 1.0f,
		0.5f, -0.5f,
		1.0f, 0.0f,
		-0.5f,  0.5f,
		0.0f, 1.0f
			
	};
	
	protected math.Vector2 Position = new math.Vector2(0f, 0f);
	protected math.Vector2 Scale = new math.Vector2(1f, 1f);
	protected float Rotation = 0f;
	protected float Transparency = 1f;
	protected int ZIndex = 0;
	
	static final void BufferToGPU()
	{
		
		
		VertexArrayObject = glGenVertexArrays();
		glBindVertexArray(VertexArrayObject);
		
		VertexBufferObject = GL15.glGenBuffers();
		
		//glGenBuffers();
		
		GL15.glBindBuffer(GL_ARRAY_BUFFER, VertexBufferObject);
		
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(Verticies.length * Float.BYTES);
		byteBuf.order(ByteOrder.nativeOrder());
		FloatBuffer vbuffer = byteBuf.asFloatBuffer();
		vbuffer.put(Verticies);
		vbuffer.position(0);
		
		GL15.glBufferData(GL_ARRAY_BUFFER, Verticies, GL_STATIC_DRAW);
		
		// Vertex Attributes
		
		glEnableVertexAttribArray(0);
		glVertexAttribPointer(0, 2, GL_FLOAT, false, 4 * Float.BYTES, (long) 0);
		glEnableVertexAttribArray(1);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 4 * Float.BYTES, (long) (2 * Float.BYTES));
		
		glBindVertexArray(0); // Unbind
		
	}
	
	static final void FreeData()
	{
		
		glBindVertexArray(0);
		
		glDeleteVertexArrays(VertexArrayObject);
		
		glDeleteBuffers(VertexBufferObject);
		
	}
	
	public void Update(float DeltaTime)
	{
		
		// Extend this
		
	}
	
	protected void AdditionalUniforms()
	{
		
		
		
	}
	
	public final void Render()
	{
		
		if (shader != null) shader.Use();
		if (texture != null) texture.Use(0); // Simple 2D Game, so texture will be bound to slot zero
		
		// The use can use additional textures using the AdditionalUniforms function
		
		// Set uniforms
		
		shader.SetUniform(Shader.Uniform.TEXTURE_UNIFORM, 0);
		shader.SetUniform(Shader.Uniform.TRANSLATE_UNIFORM, Position);
		shader.SetUniform(Shader.Uniform.SCALE_UNIFORM, new math.Vector2(Scale.X * (float) texture.Width, (float) Scale.Y * texture.Height));
		shader.SetUniform(Shader.Uniform.ROTATE_UNIFORM, Rotation);
		shader.SetUniform(Shader.Uniform.ALPHA_UNIFORM, Transparency);
		shader.SetUniform(Shader.Uniform.DEPTH_UNIFORM, -((float) ZIndex) / 100f);
		shader.SetUniform(Shader.Uniform.LIGHT_UNIFORM, game.Game.LightPosition, game.Game.LightHeight);
		shader.SetUniform(Shader.Uniform.COLOR_UNIFORM, game.Game.LightColor);
		
		AdditionalUniforms(); // User-defined uniforms
		
		glBindVertexArray(VertexArrayObject);
		
		glDrawArrays(GL_TRIANGLES, 0, 6);
		
		glBindVertexArray(0);
		
	}
	
}
