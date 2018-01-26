// Environment code for project session3two2

import jason.asSyntax.*;
import jason.environment.*;
import jason.environment.grid.Location;
import java.util.List;
import java.util.logging.*;

public class World extends Environment {

    private Logger logger = Logger.getLogger("session3two2."+World.class.getName());
    
    private static final Literal moveLeft = Literal.parseLiteral("left");
    private static final Literal moveRight = Literal.parseLiteral("right");
    private static final Literal moveUp = Literal.parseLiteral("up");
    private static final Literal moveDown = Literal.parseLiteral("down");
    private static final Literal blocked = Literal.parseLiteral("blocked");
    private static final Literal destroy = Literal.parseLiteral("destroy");
    
    private static final Literal suck = Literal.parseLiteral("suck");
    
    MyModel model;
    
    /** Called before the MAS execution with the args informed in .mas2j */
    
    @Override
    public void init(String[] args) {
        super.init(args);
        model = new MyModel();
        updatePercepts();
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        
    	boolean result = false;
    	
    	if (action.equals(moveLeft))
    		result = model.moveLeft();
    	else if (action.equals(moveRight))
    		result = model.moveRight();
    	else if (action.equals(moveUp))
    		result = model.moveUp();
    	else if (action.equals(moveDown))
    		result = model.moveDown();
    	else if (action.equals(suck))
    		result = model.suck();
    	else if (agName.equals("agent1") && action.getFunctor().equals("destroy"))
		{
			List<Term> l = action.getTerms();
		    int x = Integer.parseInt(l.get(0).toString());
		    int y = Integer.parseInt(l.get(1).toString());
		    result = model.destroy(x, y);
		}
    	else
    		logger.info("executing: "+action+", but not implemented!");
    	
    	if (result)
    	{
    		updatePercepts();
    		try { Thread.sleep(1000); } catch (InterruptedException x) { }
    	}
    	
    	return result;
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
    
    private void updatePercepts()
    {
    	clearPercepts();
    	Location loc = model.getAgPos(0);
    	addPercept(Literal.parseLiteral("position(" + loc.x + ", " + loc.y +")"));
    	
    	List<Location> dirts = model.getDirtLocations();
    	for (int i=0; i<dirts.size(); i++)
    	{
    		addPercept(Literal.parseLiteral("dirt(" + dirts.get(i).x + ", " + dirts.get(i).y + ")"));
    	}
    	
    	
    }
    
}