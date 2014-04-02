package tetris.Movement;

public enum Direction
{
	Up(0,-1,-1),Down(0,1,1),Left(-1,0,-1),Right(1,0,1),ClockWise(1,1,1),CounterClockWise(-1,-1,-1),Stay(0,0,0);
	int x,y;
	int value;
	Direction(int x2,int y2,int v)
	{
		x=x2;y=y2;value=v;
	}
}
