package backend;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.util.ArrayList;

import org.lwjgl.opengl.GL;

import game.Assets;
import game.Game;

public class Main {
	
	static int Width = 700;
	static int Height = 700;
	
	public static long Window;
	
	static boolean Running = true;
	
	static String WindowName = "Seth Franklin - Computer Science Summer Project - Animal Lines";
	
	static int FPS = 60;
	
	ArrayList<GameObject> Objects = new ArrayList<GameObject>();
	
	public static void main(String args[]) {

		Main.Start();
		
	}
	
	static public void Start()
	{
		
		Initialize();
		Run();
		
	}
	
	static public void Shutdown()
	{
		
		Running = false;
		
	}
	
	static void Initialize()
	{
		
		if (!glfwInit()) {

			System.err.println("GLFW Initialization failed.");

		}
		
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3); // OpenGL Version 3.3 for compatability
	    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
	    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE); // Don't resize
		
		Window = glfwCreateWindow(Width, Height, WindowName, 0, NULL);
		
		if (Window == NULL) {

			System.err.println("Window Creation Failed!");

		}
		
		glfwMakeContextCurrent(Window);
		glfwShowWindow(Window);
		
		GL.createCapabilities(); // woooo opengl
		
		glEnable(GL_DEPTH_TEST);
		
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); // Alpha blending
		glEnable(GL_BLEND);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT); // Texture wrapping, coordinates are S, T, R
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR); // Bilinear filtering
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
		
		Assets.Load(); // Loads Assets
		
		GameObject.BufferToGPU(); // Buffer vertex data to GPU
		
	}
	
	static void Run()
	{
		
		Game.Start();
		
		long LastFrameTime = System.nanoTime();

		long TenToTheNine = 1000000000L;

		long FrameTime = TenToTheNine / ((long) FPS);

		while (Running) // Stops when running is false
		{

			long CurrentTime = System.nanoTime();

			long ChangeInTime = CurrentTime - LastFrameTime;

			if (ChangeInTime >= FrameTime)
			{

				Update((float) (ChangeInTime / TenToTheNine)); // Use change in time instead of frame time incase the game is lagging, otherwise the game would slow down
				
				LastFrameTime = CurrentTime;

			}

			if (glfwWindowShouldClose(Window))
			{

				Running = false;

			}

		}
		
		ShutdownProcedure();
		
	}
	
	static void Update(float DeltaTime)
	{
		
		glfwPollEvents(); // Input polling
		
		// Game logic
		
		game.Game.Update(DeltaTime);
		
		// Rendering
		
		glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // Clear em up
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		game.Game.Render();
		
		glfwSwapBuffers(Window); // Bring picture to window
		
	}
	
	static void ShutdownProcedure()
	{
		
		GameObject.FreeData();
		
		Assets.Unload();
		
		glfwTerminate();
		
	}

}
