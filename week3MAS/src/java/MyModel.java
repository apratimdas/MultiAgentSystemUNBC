import jason.asSyntax.Literal;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyModel extends GridWorldModel
{
	private static final int GRID_WIDTH = 10;
	private static final int GRID_HEIGHT = 10;
	
	enum state{notblocked, isblocked};
	
	Location locRobot;
	List<Location> dirts = new ArrayList<Location>();
	List<Location> blocks = new ArrayList<Location>();
	
	public MyModel()
	{
		super(GRID_WIDTH, GRID_HEIGHT, 1);
		locRobot = new Location(1, 1);
		setAgPos(0, locRobot);
		generateDirts();
		generateBlocks();
		
	}
	
	boolean moveLeft()
	{
		Location l = getAgPos(0);
		if (l.x == 0)
			return false;
		
		//addPercept(Literal.parseLiteral("position(" + l.x + ", " + l.y +")"));
		l.x--;
		setAgPos(0, l);
		return true;
	}
	
	boolean moveRight()
	{
		Location l = getAgPos(0);
		if (l.x >= GRID_WIDTH - 1)
			return false;
		l.x++;
		setAgPos(0, l);
		return true;
	}
	
	boolean moveUp()
	{
		Location l = getAgPos(0);
		if (l.y == 0)
			return false;
		l.y--;
		setAgPos(0, l);
		return true;
	}
	
	boolean moveDown()
	{
		Location l = getAgPos(0);
		if (l.y >= GRID_HEIGHT - 1)
			return false;
		l.y++;
		setAgPos(0, l);
		return true;
	}
	
	boolean suck()
	{
		Location l = getAgPos(0);
		boolean result = removeDirt(l);
		return result;
	}
	
	
	boolean destroy(int x, int y)
	{
		Location l = new Location(x, y);
		boolean result = removeBlock(l);
		return result;
	}
	
	
	// **** helpers 
	
	public List<Location> getDirtLocations()
	{
		return dirts;
	}
	
	public List<Location> getBlockLocations()
	{
		return blocks;
	}
	
	
	private boolean removeDirt(Location l)
	{
		for (int i=0; i<dirts.size(); i++)
		{
			if (l.x == dirts.get(i).x && l.y == dirts.get(i).y)
			{
				dirts.remove(i);
				return true;
			}
		}
		return false;
	}
	
	
	
	private boolean removeBlock(Location l)
	{
		for(int i=0; i<blocks.size(); ++i)
		{
			if(l.x == blocks.get(i).x && l.y == blocks.get(i).y)
			{
				blocks.remove(i);
				return true;
			}
		}
		return false;
	}
	
	
	private void generateBlocks()
	{
		blocks.clear();
//		Random r = new Random();
//		int numberOfBlocks = 4;
		
//		Location lBlock1 = new Location(r.nextInt(GRID_WIDTH), r.nextInt(GRID_HEIGHT));
		
		Location lBlock1 = new Location(4,1);
		Location lBlock2 = new Location(2,2);
		Location lBlock3 = new Location(8,2);
		Location lBlock4 = new Location(3,6);
		
		if(!blockExists(lBlock1))
			blocks.add(lBlock1);
		if(!blockExists(lBlock2))
			blocks.add(lBlock2);
		if(!blockExists(lBlock3))
			blocks.add(lBlock3);
		if(!blockExists(lBlock4))
			blocks.add(lBlock4);
		
	}
	
	private void generateDirts()
	{
		dirts.clear();
		Random r = new Random();
//		int numberOfDirts = 2;
		
		Location lDirt1 = new Location(2,3);
		Location lDirt2 = new Location(9,2);
		
		if(!dirtExists(lDirt1))
			dirts.add(lDirt1);
		if(!dirtExists(lDirt2))
			dirts.add(lDirt2);
		
//		for (int i=0; i<numberOfDirts; i++)
//		{
//			Location lDirt = new Location(r.nextInt(GRID_WIDTH), r.nextInt(GRID_HEIGHT));
//			if (!dirtExists(lDirt))
//				dirts.add(lDirt);
//			else 
//				i--;
//		}
		
		
	}
	
	boolean dirtExists(Location loc)
	{
		for (int i=0; i<dirts.size(); i++)
		{
			if (loc.x == dirts.get(i).x && loc.y == dirts.get(i).y)
				return true;
		}
		return false;
	}
	
	boolean blockExists(Location loc)
	{
		for (int i=0; i<blocks.size(); i++)
		{
			if (loc.x == blocks.get(i).x && loc.y == blocks.get(i).y)
				return true;
		}
		return false;
	}

	
	
	
}