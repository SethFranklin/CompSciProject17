package game;

public class Test extends backend.GameObject
{
	
	float Timer = 0;
	
	public Test(math.Vector2 newPos)
	{
		
		Position = newPos;
		shader = Assets.Simple;
		texture = Assets.Elephant;
		Scale = new math.Vector2(10f, 10f);
		
	}
	
	public void Update(float DeltaTime)
	{
		
		Timer += 0.01666667f;
		
		Position = Position.Lerp(Game.MousePosition, 0.007f);
		
	}

}