package game;

public class Background extends backend.GameObject
{
	
	backend.Texture Normal;
	backend.Texture Specular;
	float Timer;
	
	public Background()
	{
		
		shader = Assets.Background;
		texture = Assets.BrickDiffuse;
		Normal = Assets.BrickNormal;
		Specular = Assets.BrickSpecular;
		Scale.X *= (700f / texture.Width);
		Scale.Y *= (700f / texture.Height);
		Position.X = 350f;
		Position.Y = 350f;
		ZIndex = -99;
		
	}
	
	public void Update(float DeltaTime)
	{
		
		Timer += 0.0166667f;
		
		float[] NewColor = hslToRgb(((float) Math.sin((float) Timer) / 2f) + 1f, 0.5f, 0.5f);
		
		Game.LightPosition = Game.LightPosition.Lerp(Game.MousePosition, 0.1f);
		Game.LightColor = new math.Color3(1f, 1f, 1f);
		
		if (Game.LeftMouseDown) Game.LightColor = new math.Color3(NewColor[0], NewColor[1], NewColor[2]);
		if (Game.RightMouseDown) Game.LightColor = new math.Color3(1f, 1f, 1f).ScalarMultiply(((float) Math.sin((float) Timer * 20f) / 2f) + 1f);
		
	}
	
	float[] hslToRgb(float h, float s, float l)
	{
	    float r, g, b;

	    if(s == 0){
	        r = 1f;
	        g = 1f;
	        b = 1f;
	    }else{

	        float q;
	        if (l < 0.5f) q = l * (1f + s);
	        else q = l + s - l * s;
	        float p = 2 * l - q;
	        r = hue2rgb(p, q, h + (1f/3f));
	        g = hue2rgb(p, q, h);
	        b = hue2rgb(p, q, h - (1f/3f));
	    }

	    return new float[] {r, g, b};
	}
	
	float hue2rgb(float p, float q, float t)
	{
        if(t < 0f) t += 1;
        if(t > 1f) t -= 1;
        if(t < (1f/6f)) return p + (q - p) * 6f * t;
        if(t < (1f/2f)) return q;
        if(t < (2f/3f)) return p + (q - p) * (2f/3f - t) * 6f;
        return p;
    }
	
	protected void AdditionalUniforms()
	{
		
		Normal.Use(1);
		Specular.Use(2);
		
		shader.SetUniform(backend.Shader.Uniform.NORMAL_UNIFORM, 1);
		shader.SetUniform(backend.Shader.Uniform.SPECULAR_UNIFORM, 2);
		
	}

}
