package game;

public class Animal extends backend.GameObject
{

	Animal Parent = null;
	Animal Child = null;
	math.Vector2 RallyPoint;
	float DistanceFromParent = 60f;
	float Speed = 0.08f;
	
	public Animal(backend.Texture NewTexture, float InitialY, int ChainLength, float NewScale, Animal NewParent)
	{
		
		shader = Assets.Simple;
		
		Parent = NewParent;
		
		Scale = Scale.ScalarMultiply(NewScale);
		
		RallyPoint = new math.Vector2(600f, InitialY);
		
		texture = NewTexture;
		
		if (Parent == null) Position.X = 600f;
		else Position.X = Parent.Position.X - DistanceFromParent;
		
		Position.Y = InitialY;
		
		if (ChainLength != 0)
		{
			
			Child = new Animal(NewTexture, InitialY, ChainLength - 1, NewScale, this);
			Game.Objects.add(Child);
			
		}
		
	}
	
	public void Update(float DeltaTime)
	{
		
		if (Parent == null)
		{
			
			RotateTowards(Game.MousePosition);
			
			if (Game.RightMouseDown)
			{
				
				MoveTowards(RallyPoint);
				Rotation = 0f;
				
			}
			else if (Game.LeftMouseDown) MoveTowards(Game.MousePosition);
			else if (!Game.RightMouseDown && !Game.LeftMouseDown) MoveTowards(Position);
			
		}
		else
		{
			
			if (Game.RightMouseDown) MoveTowards(new math.Vector2(Parent.Position.X - DistanceFromParent, Parent.Position.Y));
			else MoveTowards(Position.Subtract(Parent.Position).Normalize().ScalarMultiply(DistanceFromParent).Add(Parent.Position)); // This is why overriding operators should be a thing in java
			
			RotateTowards(Parent.Position);
			
		}
		
	}
	
	void MoveTowards(math.Vector2 Target)
	{
		
		Position = Position.Lerp(Target, Speed);
		
	}
	
	void RotateTowards(math.Vector2 Target)
	{
		
		float Angle = (float) Math.atan((double) (Target.Subtract(Position).Y / Target.Subtract(Position).X));
		
		if (Target.X < Position.X) Rotation = Angle + (float) Math.PI;
		else Rotation = Angle;
		
	}

}
