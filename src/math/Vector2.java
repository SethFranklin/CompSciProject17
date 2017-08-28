package math;

public class Vector2 {

	public float X, Y;
	
	public Vector2(float nx, float ny)
	{
		
		X = nx;
		Y = ny;
		
	}
	
	// Java doesn't have operator overloading... make everything a function instead :)
	
	public Vector2 Add(Vector2 other)
	{
		
		return new Vector2(X + other.X, Y + other.Y);
		
	}
	
	public Vector2 Subtract(Vector2 other)
	{
		
		return new Vector2(X - other.X, Y - other.Y);
		
	}
	
	public Vector2 ScalarMultiply(float Scalar)
	{
		
		return new Vector2(X * Scalar, Y * Scalar);
		
	}
	
	public Vector2 ScalarDivide(float Scalar)
	{
		
		return new Vector2(X / Scalar, Y / Scalar);
		
	}
	
	public float DotProduct(Vector2 other)
	{
		
		return (X * other.X) + (Y * other.Y); // when am i gonna ever need this
		
	}
	
	public Vector2 Lerp(Vector2 Target, float Percent)
	{
		
		return new Vector2((Percent * (Target.X - X)) + X, (Percent * (Target.Y - Y)) + Y);
		
	}
	
	public float Magnitude()
	{
		
		return (float) Math.sqrt(Math.pow((double) X, 2.0d) + Math.pow((double) Y, 2.0d));
		
	}
	
	public math.Vector2 Normalize()
	{
		
		return ScalarDivide(Magnitude());
		
	}
	
}
