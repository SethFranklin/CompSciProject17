package game;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwGetMouseButton;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;

public class Game
{
	
	public static ArrayList<backend.GameObject> Objects = new ArrayList<backend.GameObject>();
	
	public static math.Vector2 MousePosition;
	public static math.Vector2 LightPosition = new math.Vector2(350f, 350f);
	public static math.Color3 LightColor = new math.Color3(1f, 1f, 1f);
	public static float LightHeight = 1.2f;
	public static boolean LeftMouseDown;
	public static boolean RightMouseDown;
	
	public static void Start()
	{
		
		Objects.add(new Background());
		Objects.add(new Animal(Assets.Elephant, 100f, 10, 1f, null));
		Objects.add(new Animal(Assets.DonkeyKong, 200f, 10, 1.3f, null));
		Objects.add(new Animal(Assets.Ant, 300f, 10, 1f, null));
		Objects.add(new Animal(Assets.Tails, 400f, 10, 1.5f, null));
		Objects.add(new Animal(Assets.Dog, 500f, 10, 1f, null));
		Objects.add(new Animal(Assets.Chocobo, 600f, 10, 2f, null));
		
		
	}
	
	public static void Update(float DeltaTime)
	{
		
		DoubleBuffer X = BufferUtils.createDoubleBuffer(1);
		glfwGetCursorPos(backend.Main.Window, X, null);
		
		DoubleBuffer Y = BufferUtils.createDoubleBuffer(1);
		glfwGetCursorPos(backend.Main.Window, null, Y);
		
		MousePosition = new math.Vector2((float) X.get(), (float) -Y.get() + 700f);
		
		if (glfwGetMouseButton(backend.Main.Window, 0) == GLFW_PRESS) LeftMouseDown = true;
		else LeftMouseDown = false;
		
		if (glfwGetMouseButton(backend.Main.Window, 1) == GLFW_PRESS) RightMouseDown = true;
		else RightMouseDown = false;
		
		for (backend.GameObject ObjectToUpdate : Objects)
		{
			
			ObjectToUpdate.Update(DeltaTime);
			
		}
		
	}
	
	public static void Render()
	{
		
		for (backend.GameObject ObjectToRender : Objects)
		{
			
			ObjectToRender.Render();
			
		}
		
	}

}
