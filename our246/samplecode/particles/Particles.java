// $Header: /usr/local/cvsroot/courses/cs246/samplecode/particles/Particles.java,v 1.6 2006/05/25 16:43:52 neffr Exp $

import java.util.ArrayList;
import java.util.Collection;

public class Particles
{
   Collection<Particle> mParticles;

   public static void main(String[] args)
   {
      new Particles(args).run();
   }

   public Particles(String[] args)
   {
      mParticles = new ArrayList<Particle>();

      for (String arg : args)
      {
	  try
	  {
	      mParticles.add((Particle) Class.forName(arg).newInstance());
	  }
	  catch (Exception e) { System.out.println(e); }
      }
   }

   public void run()
   {
      for (Particle p : mParticles)
      {
         System.out.println(p);
      }
   }
}

interface ChargedParticle
{
   public Rational getCharge();
}

class Nucleon
   extends Particle
   implements ChargedParticle
{
   Collection mComponents;

   public Nucleon()
   {
      String name = System.getProperty("nucleon.class.components.collection",
                                       "QuarkCollection");
      try
      {
         mComponents = (Collection) Class.forName(name).newInstance();
      }
      catch (Exception e)
      {
         System.out.println(e);
         mComponents = new ArrayList();
      }
   }

   public Rational getCharge()
   {
      Rational rtnval = new Rational();
      // iterate over Quark components and compute the charge
      // mComponents
      for (Object object : mComponents)
      {
         ChargedParticle cp = (ChargedParticle) object;
         rtnval = rtnval.plus(cp.getCharge());
      }
      return rtnval;
   }
   public String toString() { return super.toString() + " has charge " + getCharge(); }
}

class Gluon extends Particle {}

class Proton
   extends Nucleon
{
   public Proton()
   {
      mComponents.add(new UpQuark()); // 2/3
      mComponents.add(new UpQuark()); // 2/3
      mComponents.add(new DownQuark()); // -1/3
   }
}

class Neutron
   extends Nucleon
{
   public Neutron()
   {
      mComponents.add(new UpQuark()); // 2/3
      mComponents.add(new DownQuark()); // -1/3
      mComponents.add(new DownQuark()); // -1/3
   }
}

class Electron
   extends Particle
   implements ChargedParticle
{
   public Rational getCharge() { return new Rational(-1, 1); }
   public String toString() { return super.toString() + " has charge " + getCharge(); }
}

abstract class Quark
   extends Particle
   implements ChargedParticle
{
   public Rational getCharge() { return null; }
}

class UpQuark
   extends Quark
{
   public Rational getCharge() { return new Rational(2, 3); }
}

class DownQuark
   extends Quark
{
   public Rational getCharge() { return new Rational(-1, 3); }
}
