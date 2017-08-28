package math;

public class Color3
{

	public float R, G, B;
	
	public Color3(float nr, float ng, float nb)
	{
		
		R = nr;
		G = ng;
		B = nb;
		
	}
	
	public Color3 ScalarMultiply(float a)
	{
		
		return new Color3(a * R, a * B, a * G);
		
	}

}
