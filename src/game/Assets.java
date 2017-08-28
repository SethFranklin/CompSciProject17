package game;
import java.util.HashMap;
import java.util.Map;

public class Assets {

	// public static (Type) (Name)
	
	public static backend.Shader Simple;
	public static backend.Shader Background;
	
	public static backend.Texture Arushi;
	public static backend.Texture BrickDiffuse;
	public static backend.Texture BrickNormal;
	public static backend.Texture BrickSpecular;
	
	public static backend.Texture Elephant;
	public static backend.Texture Chocobo;
	public static backend.Texture Tails;
	public static backend.Texture DonkeyKong;
	public static backend.Texture Ant;
	public static backend.Texture Dog;
	
	public static void Load()
	{
		
		Map<backend.Shader.Uniform, String> map = new HashMap<backend.Shader.Uniform, String>();
		
		map.put(backend.Shader.Uniform.TRANSLATE_UNIFORM, "Translate");
		map.put(backend.Shader.Uniform.ROTATE_UNIFORM, "Rotation");
		map.put(backend.Shader.Uniform.SCALE_UNIFORM, "Scale");
		map.put(backend.Shader.Uniform.ALPHA_UNIFORM, "Alpha");
		map.put(backend.Shader.Uniform.TEXTURE_UNIFORM, "Texture");
		map.put(backend.Shader.Uniform.DEPTH_UNIFORM, "Z");
		map.put(backend.Shader.Uniform.LIGHT_UNIFORM, "LightPosition");
		map.put(backend.Shader.Uniform.COLOR_UNIFORM, "LightColor");
		
		Simple = new backend.Shader("simple", map);
		
		Map<backend.Shader.Uniform, String> map2 = new HashMap<backend.Shader.Uniform, String>();
		
		map2.put(backend.Shader.Uniform.TRANSLATE_UNIFORM, "Translate");
		map2.put(backend.Shader.Uniform.ROTATE_UNIFORM, "Rotation");
		map2.put(backend.Shader.Uniform.SCALE_UNIFORM, "Scale");
		map2.put(backend.Shader.Uniform.ALPHA_UNIFORM, "Alpha");
		map2.put(backend.Shader.Uniform.TEXTURE_UNIFORM, "Texture");
		map2.put(backend.Shader.Uniform.DEPTH_UNIFORM, "Z");
		map2.put(backend.Shader.Uniform.NORMAL_UNIFORM, "NormalMap");
		map2.put(backend.Shader.Uniform.SPECULAR_UNIFORM, "SpecularMap");
		map2.put(backend.Shader.Uniform.LIGHT_UNIFORM, "LightPosition");
		map2.put(backend.Shader.Uniform.COLOR_UNIFORM, "LightColor");
		
		Background = new backend.Shader("background", map2);
		
		BrickDiffuse = new backend.Texture("BrickDiffuse");
		BrickNormal = new backend.Texture("BrickNormal");
		BrickSpecular = new backend.Texture("BrickSpecular");
		Arushi = new backend.Texture("Arushi");
		
		Elephant = new backend.Texture("Elephant");
		Chocobo = new backend.Texture("Chocobo");
		Dog = new backend.Texture("Dog");
		Tails = new backend.Texture("Tails");
		Ant = new backend.Texture("Ant");
		DonkeyKong = new backend.Texture("DonkeyKong");
		
	}
	
	public static void Unload()
	{
		
		Simple.Delete();
		Arushi.Delete();
		
	}

}
