package Items;

import java.util.ArrayList;

public abstract class Sod
{
    Grid g;
    public abstract void addBox(Box b);
    public abstract Box getBox(int x,int y);
    public abstract ArrayList<String> getPens();
	public abstract void check();
	public abstract ArrayList<Box> getBoxAsArray();
}
