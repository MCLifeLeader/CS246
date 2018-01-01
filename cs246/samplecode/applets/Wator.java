// Wator
// Copyright (C) Robert C. Martin, 1997, Copies may be freely made so long as
// this entire notice is maintained.  Any modifications must be noted hereafter.
// rmartin@oma.com, http://www.oma.com
//----------------------------------
//
// Wator is based upon A. K. Dewdney's "Computer Recreations" article in the
// December, 1984 issue of Scientific American.

import java.applet.Applet;

import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;

import java.util.Vector;
import java.util.Enumeration;
import java.util.Random;

public class Wator extends Applet
{
    // initial parameters of Wator.
    private int fishGestation = 3;
    private int sharkGestation = 4;
    private int sharkStarvation = 3;
    private int startFish = 0;  // can start with non zero starting
    private int startShark = 0; // populations if so dexired.

    // public variables
    public  int cellSize = 3;   // The pixel size of a single cell
    public  int watorWidth;     // The width of the Wator world in cells.
    public  int watorHeight;    // The Height of the Wator world in cells.

    // other variables.  Some are public for efficiency's sake.
    private Animal world[][];          // The wator map
    public  Animal itsAnimals = null;  // The list of living creatures.
    public  Animal itsLastAnimal = null; // last animal in list.
    private Thread itsThread;          // The animation thread
    public  long itsTick = 0;          // counts the number of turns.
    private boolean isStarted = false; // used to create thread
    public  Random rand = new Random();// a random number used all over.

    // Constructor, does nothing.
    public Wator()
    {
    }

    // Set and Get the wator world by using Cells.
    public void SetCell(Cell c, Animal a)
    {
        world[c.GetX()][c.GetY()]=a;
    }

    public Animal GetCell(Cell c)
    {
        return world[c.GetX()][c.GetY()];
    }

    //---------------------------
    // Applet control functions.
    //---------------------------

    // Initialize the applet.
    public void init()
    {
        Rectangle bounds = bounds();
        watorWidth = bounds.width / cellSize;
        watorHeight = bounds.height / cellSize;
        world = new Animal[watorWidth][watorHeight];
    }

    // Start the applet.  Also called when the applet resumes after
    // being stopped.  In either case it resets the world to the
    // starting condition and creates the animation thread.
    public void start()
    {
        Populate();

        // This anonymous inner class implements the animation loop.
        // It runs in a separate thread so that it can be suspended
        // and resumed.
        Runnable chronos = new Runnable()
        {
            public void run()
            {
                // Be nice to other threads.
                Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
                // Animate.
                for(;;)
                {
                    repaint();
                    itsThread.yield();
                }
            }
        };
        itsThread = new Thread(chronos);
        itsThread.start();
        System.out.println("started");
    }

    // Called when the applet is told to stop.  This happens when the
    // browser changes web pages or something like that.  I shut down
    // the animation thread so that CPU resources are no longer used up.
    public void stop()
    {
        itsThread.stop();
        itsThread = null;
        System.out.println("Stopped");
    }

    // Called when part of the window has been damaged by a menu or
    // another window.  Repaints the entire screen.
    public void paint(Graphics g)
    {
        for (int x=0; x<watorWidth; x++)
            for (int y=0; y<watorHeight; y++)
                world[x][y].Draw(g, this);
    }

    // Called by 'repaint'.  This is the heart of the animation.
    public void update(Graphics g)
    {
        Draw(g);
    }

    // The animation itself.  This is the high level loop that deals with
    // a single turn (Chronon) of Wator.
    public void Draw(Graphics g)
    {
        itsTick++; // keep track of time. We use this later.

        // Walk through the list of living animals and have them take
        // their turns.  We use this list so that we don't have to
        // walk through all the empty spaces in the world array.
        for (Animal a = itsAnimals; a != null; a=a.itsForwardLink)
        {
            a.Tic(g, itsTick, this);
        }

        // Mutations are opportunistic.  We decide that we are going
        // to mutate, Then we decide what cell is going to mutate.  And
        // then, if that cell contains the right kind of animal, we mutate it.

        // mutate an algae cell
        if (rand.nextFloat() > .95)
        {
            int x = (int)(rand.nextFloat()*watorWidth);
            int y = (int)(rand.nextFloat()*watorHeight);
            if (world[x][y] instanceof Algae)
            {
                Cell c = Cell.Normalize(x,y,this);
                world[x][y].Unlink(this);
                world[x][y] = new Fish(c, fishGestation, this);
                world[x][y].Draw(g,this);
            }
        }
        // mutate a fish
        if (rand.nextFloat() > .97)
        {
            int x = (int)(rand.nextFloat()*watorWidth);
            int y = (int)(rand.nextFloat()*watorHeight);
            if (world[x][y] instanceof Fish)
            {
                Cell c = Cell.Normalize(x,y,this);
                world[x][y].Unlink(this);
                world[x][y] = new Shark(c, sharkGestation, sharkStarvation,this);
                world[x][y].Draw(g,this);
            }
        }
    }

    // This function is called every time we start or resume the applet.  It
    // puts the wator world back to its start state.  First it clears out
    // the entire world.  Then it randomly loads the world with the specified
    // starting populations of fish and sharks.
    public void Populate()
    {
        itsTick = 0;
        Random r = new Random();
        itsAnimals = null;

        for (int x=0; x<watorWidth; x++)
            for (int y=0; y<watorHeight; y++)
            {
                // call special Algae constructor, tuned for populating.
                world[x][y] = new Algae(Cell.Normalize(x,y,this),this,true);
            }

        for (int f=0; f<startFish; f++)
        {
            Cell c;
            do
            {
                int x = (int)(r.nextFloat()*watorWidth);
                int y = (int)(r.nextFloat()*watorHeight);
                c = Cell.Normalize(x,y,this);
            }while (!(GetCell(c) instanceof Algae));
            SetCell(c, new Fish(c,fishGestation, this));
        }

        for (int s=0; s<startShark; s++)
        {
            Cell c;
            do
            {
                int x = (int)(r.nextFloat()*watorWidth);
                int y = (int)(r.nextFloat()*watorHeight);
                c = Cell.Normalize(x,y,this);
            }while (!(GetCell(c) instanceof Algae));
            SetCell(c, new Shark(c, sharkGestation, sharkStarvation, this));
        }
    }
}

// Cell.
// This class is an abstraction of the coordinates of wator.  It has no
// public constructor!  The only way to make a Cell is to call Normalize.
// Normalize makes sure that the cell is "in bounds".  Thus, no cell can
// ever be created that is not in bounds.
//
// The Normalize function takes care of wrapping the rectangular grid into
// the wator torus.
//
class Cell
{
    private Cell(int x, int y) {itsX = x; itsY = y;}

    private int itsX;
    private int itsY;
    public int GetX() {return itsX;}
    public int GetY() {return itsY;}

    // The direction functions return the Cell that is one step
    // in the specified direction.

    public Cell North(Wator w) {return Normalize(itsX, itsY-1, w);}
    public Cell South(Wator w) {return Normalize(itsX, itsY+1, w);}
    public Cell East(Wator w)  {return Normalize(itsX-1, itsY, w);}
    public Cell West(Wator w)  {return Normalize(itsX+1, itsY, w);}

    public static Cell Normalize(int x, int y, Wator w)
    {
        if (x >= w.watorWidth) x %= w.watorWidth;
        else if (x < 0) x = (x%w.watorWidth)+w.watorWidth;
        if (y >= w.watorHeight) y %= w.watorHeight;
        else if (y < 0) y = (y%w.watorHeight)+w.watorHeight;
        return new Cell(x,y);
    }
}

// Animals
// The animal hierarchy is the heart of the simulation.  Each of the three
// different kinds of animals in Wator (algae, fish, shark) derives from
// Animal.

abstract class Animal
{
    abstract public void Draw(Graphics g, Wator w);
        // Draw the animal.
    abstract protected void ProcessTic(Graphics g, Wator w);
        // Do extra processing once the animal has moved and
        // reproduced.  Sharks use this to see if they starve.
    abstract protected Vector GetCellChoices(Wator w);
        // Return the vector of cells that this animal can legally
        // move into.  If animals appear in these cells, they may be
        // eaten.
    abstract protected void Eat(Animal a, long tick);
        // Eat an animal.
    abstract protected Animal Reproduce(Cell c, Wator w);
        // Make a baby.  Might mutate...
    abstract protected boolean CanReproduce(long tick);
        // Determine if this animal is in a state allowing it
        // to reproduce.

    // Constructor.
    public Animal(Cell c, int g, Wator w)
    {
        itsCell = c;
        itsGestationTimer=g;
        itsGestation=g;
        Link(w); // link it into the wator.itsAnimals list.
    }

    // We use this linked list for speed.  It is much faster to traverse
    // the linked list of fish and sharks than it would be to walk through
    // every cell in the world.  This is because we never have to
    // see if Algae reproduce or move.  Algae is really just a NULL object.
    protected void Link(Wator w)
    {
        // link this animal onto the head of the list.
        if (w.itsAnimals != null)
        {
            w.itsAnimals.itsBackLink = this;
            itsForwardLink = w.itsAnimals;
            itsBackLink = null;
            w.itsAnimals = this;
        }
        else
        {
            w.itsAnimals=w.itsLastAnimal=this;
            itsForwardLink = itsBackLink = null;
        }
        isLinked=true;

    }

    // Sometimes we want to link animals onto the end of the list.
    // This happens when we wake them up after a period of dormancy.
    // They get woken up while the list if partially traversed, and
    // we want them to have their turn during the same pass of the
    // list.  (BTW, this means that some animals can go dormant,
    // and then wake up on the same turn.  In effect they get two
    // turns.  But that's probably ok).
    protected void LinkBack(Wator w)
    {
        if (w.itsAnimals != null)
        {
            w.itsLastAnimal.itsForwardLink = this;
            itsBackLink = w.itsLastAnimal;
            itsForwardLink = null;
            w.itsLastAnimal = this;
        }
        else
        {
            w.itsAnimals=w.itsLastAnimal=this;
            itsForwardLink = itsBackLink = null;
        }
        isLinked=true;
    }

    // Unlink just unlinks the animal from the Wator.itsAnimals list.
    // We use a doubly linked list because we don't want to have
    // to search forward from the beginning to find the animal previous
    // to the one that is dying.
    protected void Unlink(Wator w)
    {
        if (isLinked)
        {
            if (itsForwardLink != null)
                itsForwardLink.itsBackLink = itsBackLink;
            else
                w.itsLastAnimal = itsBackLink;

            if (itsBackLink != null)
                itsBackLink.itsForwardLink = itsForwardLink;
            else
                w.itsAnimals = itsForwardLink;
            isLinked = false;
        }
    }

    public Cell GetCell() {return itsCell;}
    public int  GetGestation() {return itsGestation;}

    // The heart of each animal.  This function sets the overall policy
    // that all animals follow.  Each type of animal modifies this
    // policy by overriding one of the abstract functions above.
    public void Tic(Graphics g, long tick, Wator w)
    {
        itsGestationTimer--; // count down for reproduction.
        Cell oldCell = itsCell;
        Vector choices = this.GetCellChoices(w);
        if (choices.size() != 0)
        {
             // choose the cell to move into.
            int choice = (int)(w.rand.nextFloat() * choices.size());
            Cell chosenCell = (Cell)choices.elementAt(choice);

            // Eat whatever is in the chosen cell
            Animal loser = w.GetCell(chosenCell);
            loser.Unlink(w);
            Eat(loser, tick);

            // Either reproduce or move into the chosen cell.
            // We add the random element here because otherwise all the
            // animals tend to reproduce at the same time.  The random
            // factor spreads the animals' reproduction times out in time.

            if (itsGestationTimer < 0 &&
                w.rand.nextFloat() > 0.1 &&
                CanReproduce(tick))
            {
                w.SetCell(chosenCell, Reproduce(chosenCell,w));
                itsGestationTimer=itsGestation;
            }
            else
            {
                // move out of the current cell;  We do this by putting
                // algae into the cell where we were.
                w.SetCell(oldCell, new Algae(oldCell, w));
                w.SetCell(chosenCell, this);
                itsCell=chosenCell;
            }
            // now draw the two cells that changed.
            w.GetCell(chosenCell).Draw(g, w);
            w.GetCell(oldCell).Draw(g,w);
        }
        else /* choices.size() == 0 */
            // If the animal is ready to reproduce, then we'll make it dormant.
            if (itsGestationTimer < 0)
                Dormant(true,w); // if we can't move, we may be dormant.

        // Finally, check to see if the animal has any extra processing
        // to perform.
        ProcessTic(g,w);
    }

    // When an animal cannot move, and is ready to reproduce, then
    // we tell it to go dormant.  At this point only Fish actually
    // go Dormant.  Dormancy does not change the behavior of the
    // animal at all.  It just takes it out of the list of animals
    // that will be Tic'd.  This dramatically speeds things up.
    public void Dormant(boolean isDormant, Wator w) {};
    public boolean IsDormant() {return false;}

    private Cell itsCell;
    private int itsGestationTimer;
    private int itsGestation;
    private Animal itsBackLink;
    public  Animal itsForwardLink;
    private boolean isLinked = false;
}

// Sharks are regular Animals, except that they can starve if they don't
// eat regularly.
class Shark extends Animal
{
    public Shark(Cell c, int g, int s, Wator w)
    {
        super(c, g, w);
        itsStarvationTimer = s;
        itsStarvation = s;
        itsLastMealTick = 0;
        itsMealCount = 0;
    }

    // After all the moving and eating is done, see if we managed to
    // get a meal.  If not, then perhaps we'll die and be replaced
    // by algae.
    //
    // Shark father to little boy Shark: "Son, when we die, our bodies
    // become the algae, and the fish eat the algae.  And so we are all
    // bound in the great circle of life..."
    protected void ProcessTic(Graphics g, Wator w)
    {
        if (--itsStarvationTimer < 0)
        {
            Unlink(w);
            w.SetCell(GetCell(), new Algae(GetCell(), w));
            w.GetCell(GetCell()).Draw(g, w);
        }
    }

    // Sharks have choices!  If there are any fish near enough to eat
    // then we will ignore empty spaces.  Otherwise we can move East, West,
    // North or South, displacing any Algae that happen to be there.
    protected Vector GetCellChoices(Wator w)
    {
        Vector choices = new Vector();
        Cell myCell = GetCell();
        Cell north = myCell.North(w);
        Cell south = myCell.South(w);
        Cell east =  myCell.East(w);
        Cell west =  myCell.West(w);

        boolean canEat = false;
        Animal northAnimal = w.GetCell(north);
        Animal southAnimal = w.GetCell(south);
        Animal eastAnimal = w.GetCell(east);
        Animal westAnimal = w.GetCell(west);

        if ((northAnimal instanceof Fish) ||
            (northAnimal instanceof Shark &&
             itsStarvation > ((Shark)northAnimal).itsStarvation))
        {
            choices.addElement(north);
            canEat = true;
        }

        if ((southAnimal instanceof Fish) ||
            (southAnimal instanceof Shark &&
             itsStarvation > ((Shark)southAnimal).itsStarvation))
        {
            choices.addElement(south);
            canEat = true;
        }

        if ((eastAnimal instanceof Fish) ||
            (eastAnimal instanceof Shark &&
             itsStarvation > ((Shark)eastAnimal).itsStarvation))
         {
            choices.addElement(east);
            canEat = true;
        }

        if ((westAnimal instanceof Fish) ||
            (westAnimal instanceof Shark &&
             itsStarvation > ((Shark)westAnimal).itsStarvation))
        {
            choices.addElement(west);
            canEat = true;
        }

        if (!canEat)
        {
            if (northAnimal instanceof Algae)
                choices.addElement(north);
            if (southAnimal instanceof Algae)
                choices.addElement(south);
            if (eastAnimal instanceof Algae)
                choices.addElement(east);
            if (westAnimal instanceof Algae)
                choices.addElement(west);
        }

        return choices;
    }

    // We eat the algae, but it doesn't do us any good.
    protected void Eat(Animal a, long tick)
    {
        if (a instanceof Fish || (a instanceof Shark))
        {
            itsStarvationTimer = itsStarvation;
            itsLastMealTick = tick;
            itsMealCount++;
        }
    }

    // We can only reproduce if we have eaten more than once,
    // the second time recently.
    protected boolean CanReproduce(long tick)
    {
        return (itsMealCount > 1) && ((tick - itsLastMealTick) <= 2);
    }

    // Make a baby shark.  But first, see if that shark should be a
    // mutant.  We can mutate along two axes.  Its gestation period can
    // change, or its starvation time can change.  Gestation can never
    // go below 1.  If starvation goes below 1, then the shark dies as soon
    // as it is born, so who cares.
    protected Animal Reproduce(Cell c, Wator w)
    {
        Random r = new Random();
        int g = GetGestation() + (int)(r.nextGaussian()/2.8);
        int s = itsStarvation  + (int)(r.nextGaussian()/2.8);
        if (g<1) g=1;
        itsMealCount = 0;

        return new Shark(c, g, s, w);
    }

    // Draw the shark in its rainbow array of mutant colors.
    public void Draw(Graphics g, Wator w)
    {
        Cell cell = GetCell();
        int x = cell.GetX() * w.cellSize;
        int y = cell.GetY() * w.cellSize;
        Color c;
        switch(itsStarvation)
        {
            case 0:
            case 1:
            case 2: c = Color.yellow; break;

            case 3: c = Color.orange; break;
            case 4: c = Color.red; break;
            case 5: c = Color.gray; break;
            case 6: c = Color.magenta; break;
            case 7: c = Color.blue; break;
            case 8: c = Color.cyan; break;
            default: c = Color.black; break;
        }
        g.setColor(c);
        g.fillRect(x,y,w.cellSize-1, w.cellSize-1);
    }

    private int itsStarvation;
    private int itsStarvationTimer;
    private long itsLastMealTick;
    private long itsMealCount;
}

// Fish are regular animals.  There are no surprises here.
class Fish extends Animal
{
    public Fish(Cell c, int g, Wator w)
    {
        super(c, g, w);
    }

    // No extra processing for Fish.
    protected void ProcessTic(Graphics g, Wator w)
    {
    }

    // Fish can move into any adjacent algae.  Poor algae.
    protected Vector GetCellChoices(Wator w)
    {
        Vector choices = new Vector();
        Cell myCell = GetCell();
        Cell north = myCell.North(w);
        Cell south = myCell.South(w);
        Cell east =  myCell.East(w);
        Cell west =  myCell.West(w);
        Animal a = w.GetCell(north);
        if (a instanceof Algae)
            choices.addElement(north);
        a = w.GetCell(south);
        if (a instanceof Algae)
            choices.addElement(south);
        a = w.GetCell(east);
        if (a instanceof Algae)
            choices.addElement(east);
        a = w.GetCell(west);
        if (a instanceof Algae)
            choices.addElement(west);
        return choices;
    }

    // Nothing interesting happens when a Fish eats.
    protected void Eat(Animal a, long tick)
    {
    }

    protected boolean CanReproduce(long tick)
    {
        return true;
    }

    protected Animal Reproduce(Cell c, Wator w)
    {
        return new Fish(c, GetGestation(), w);
    }

    public void Draw(Graphics g, Wator w)
    {
        Cell c = GetCell();
        int x = c.GetX() * w.cellSize;
        int y = c.GetY() * w.cellSize;
        g.setColor(Color.green);
        g.fillRect(x,y,w.cellSize-1, w.cellSize-1);
    }

    public void Dormant(boolean isDormant, Wator w)
    {
        if (isDormant != itsDormancy) // a change has occurred
        {
            itsDormancy = isDormant;
            if (isDormant)
                Unlink(w);
            else
                Link(w);
        }
    }

    public boolean IsDormant()
    {
        return itsDormancy;
    }

    private boolean itsDormancy=false;
}

// Algae are wierd little animals.  They don't do anything much.
// Algae is an example of the "Null Object" pattern by "Bobby Woolf".
// (See Pattern Languages of Program Design, Volume 3, Addison Wesley);

class Algae extends Animal
{
    // special constructor of Algae, tuned for Populate function.
    // does not do dormancy processing.
    public Algae(Cell c, Wator w, boolean populate)
    {
        super(c, 0, w);
    }

    // normal constructor of Algae, does dormancy processing.
    public Algae(Cell c, Wator w)
    {
        super(c, 0, w);

        // When Algae is created in a cell, the adjacent cells
        // are woken up.

        Cell north = c.North(w);
        Cell south = c.South(w);
        Cell east =  c.East(w);
        Cell west =  c.West(w);

        w.GetCell(north).Dormant(false,w);
        w.GetCell(south).Dormant(false,w);
        w.GetCell(east).Dormant(false,w);
        w.GetCell(west).Dormant(false,w);

    }

    // We override Tic just in case some blockhead calls it.  Since Algae
    // are never put into the Wator.itsAnimals list; Tic should never be
    // called.  (And if by some chance an Algae does get put into the list,
    // I don't want the program to misbehave.  It's a benign tumor.
    public void Tic(Graphics g, long tick, Wator w)
    {
        System.out.println("Algae Tic!!!");
    }

    protected void ProcessTic(Graphics g, Wator w)
    {
    }

    protected Vector GetCellChoices(Wator w)
    {
        return new Vector();
    }

    protected void Eat(Animal a, long tick)
    {
    }

    protected void Unlink(Wator w) {}
    protected void Link(Wator w) {}

    protected boolean CanReproduce(long tick)
    {
        return false;
    }

    protected Animal Reproduce(Cell c, Wator w)
    {
        return null;
    }

    // You may have been asking yourself why this class exists.  Well,
    // here is the real reason.  I don't want to have if statements in
    // the drawing code that check for null.  So I created Algae that can
    // be drawn.
    public void Draw(Graphics g, Wator w)
    {
        Cell c = GetCell();
        int x = c.GetX() * w.cellSize;
        int y = c.GetY() * w.cellSize;
        g.setColor(Color.white);
        g.fillRect(x,y,w.cellSize-1, w.cellSize-1);
    }

    public boolean IsDormant() {return true;} // Algae is always dormant

}
