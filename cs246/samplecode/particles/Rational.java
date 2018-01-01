/**
 * The Rational class represents a rational number as a numerator and 
 * denominator of int's. The number is represented in lowest terms with
 * any sign on the numerator. Thus 6/4 becomes 3/2, 0/100 becomes 0/1
 * and -(3/4) becomes (-3)/4.
 *
 * @version Last Modified 6/11/99
 * @author Andrew G. Bennett
 * @see LongRational
 */
public class Rational extends Object {

    // Numerator and Denominator
    private int num,denom;

    /**
     * Null Constructor gives the default value = 0/1
     */
    public Rational() {
        num=0;
        denom=1;
    }

    /**
     * n/d will be reduced to lowest terms and any sign will be placed 
     * on the numerator.
     * @param n is the numerator
     * @param d is the denominator
     * @exception java.lang.Arithmetic if denominator==0
     */
    public Rational(int n, int d) {
        
        int c; // greatest common divisor
        int s; // sign of number

        if (d==0)  {
            throw new ArithmeticException("Denominator must be non-zero.");
        }
        if (n*d<0) {
            s=-1;
        } else {
            s=1;
        }
        n=Math.abs(n);
        d=Math.abs(d);
        c=gcd(n,d);
        num=s*n/c;
        denom=d/c;
    }

    /**
     * Converts a float to the nearest Rational with denom<=maxdenom. 
     * Note that this is not the same as rounding to the nearest 1/maxdenom.
     * For example, with maxdenom=1000, 0.1428 converts to 1/7, not 143/1000. 
     * The error is bounded above by 1/maxdenom, but will usually be less.
     * Use LongRational.approx() if f is very large or small to avoid roundoff 
     * errors and overflow. As always any sign will be placed on the numerator,
     * so -.1428 converts to (-1)/7. 
     * @param f is the float to convert
     * @param maxdenom is the maximum size of the denominator. It must be greater than 0.
     * @exception java.lang.ArithmeticException if maxdenom<=0
     * @see LongRational.approx(double,int);
     */
    public static Rational approx(float f, int maxdenom){
        int a,a0,a1,b,b0,b1;
        int ipart;  // integer part of float
        int sign;   // signum of float
        
        // Validate maxdenom
        if (maxdenom<=0) throw new ArithmeticException("maxdenom must be >=1");
        // Test for 0 input
        if (f==0) return new Rational(0,1);
        // Identify signum
        sign=1;
        if (f<0) sign=-1;
        f=Math.abs(f);
        
        /* Compute continued fraction expansion */
        
        // Initialize continued fraction expansion
        a0=0;
        a1=1;
        b0=1;
        b1=0;
        // Compute initial term
        ipart=Math.round(f-.5f); // floor function
        f=f-ipart;
        a=a1*ipart+a0;
        b=b1*ipart+b0;
        a0=a1;
        b0=b1;
        a1=a;
        b1=b;
        // Loop for continued fraction expansion
        while (f!=0 && b1<=maxdenom) {
            f=1/f;
            ipart=Math.round(f-.5f); // floor function
            f=f-ipart;
            a=a1*ipart+a0;
            b=b1*ipart+b0;
            a0=a1;
            b0=b1;
            a1=a;
            b1=b;
        }
        if (b1<=maxdenom) { 
            return new Rational(sign*a1,b1);
        } else {  // go back one term if denom too large
            return new Rational (sign*a0,b0);
        }
    }
    
    /**
     * Converts a float to the nearest Rational with denom<=1000. 
     * Note that this is not the same as rounding to the nearest 1/1000.
     * For example, 0.1428 converts to 1/7, not 143/1000. Theoretically,
     * the error is bounded above by 1/1000, but will usually be less.
     * Roundoff error and overflows can be significant when f is 
     * either over 1000 or less than 1/1000. Use LongRational.approx()
     * if roundoff error or overflows cause problems.
     * As always any sign will be placed on the numerator, 
     * so -0.1428 converts to (-1)/7. 
     * @param f is the float to convert
     * @see LongRational(double)
     */
    public static Rational approx(float f) {
        return approx(f,1000);
    }
    

    /**
     * @returns numerator of Rational number
     */
    public int getNum() {
        return(num);
    }
    
    /**
     * Changes the numerator of this Rational. Note that the Rational 
     * is automatically reduced to lowest terms, which may change the 
     * numerator from the set value. For example, if the denominator
     * is 4, setNum(2) changes the Rational to 2/4 = 1/2 and so
     * getNum() will return 1, not 2.
     * @param n is the new numerator
     */
    public void setNum(int n) {
        int c; // greatest common divisor
        int s; // signum of number

        s=1;
        if (n<0) s=-1;
        n=Math.abs(n);
        c=gcd(n,denom);
        num=s*n/c;
        denom=denom/c;
    }

    /**
     * @returns denominator of Rational number
     */
    public int getDenom() {
        return denom;
    }
    
    /**
     * Changes the denominator of this Rational. Note that the Rational 
     * is automatically reduced to lowest terms, which may change the 
     * denominator from the set value. For example, if the numerator is
     * is 2, setDenom(4) changes the Rational to 2/4 = 1/2 and so
     * getDenom() will return 2, not 4.
     * @param d is the new numerator
     * @exception java.lang.ArithmeticException if d==0
     */
    public void setDenom(int d) {
        int n; // absolute value of numerator
        int c; // greatest common divisor
        int s; // signum of number
        
        if (d==0) {
            throw new ArithmeticException("Denominator must be non-zero");
        }
        s=1;
        if (num<0) s=-1;
        n=Math.abs(num);
        c=gcd(n,d);
        num=s*n/c;
        denom=denom/c;
    }
 

    private static final int gcd(int dvr,int dvd) {
        int rem;
        while (dvr!=0) {
            rem=dvd%dvr;
            dvd=dvr;
            dvr=rem;
        }
        return dvd;
    }
    
    /**
     * @param s is the Rational to test against this Rational
     * @return true if s and this Rational are equal
     */
    public boolean equals(Rational s) {
        return (num*s.denom==denom*s.num);
    }
    
    /**
     * Determines if this rational is greater than another
     * @param s is the Rational to test against this Rational
     * @return true if this Rational > s
     */
    public boolean greaterThan(Rational s) {
        return (num*s.denom>denom*s.num);
    }
    
    /**
     * @param s is the Rational to test against this Rational
     * @return true if this Rational < s
     */
    public boolean lessThan(Rational s) {
        return (num*s.denom<denom*s.num);
    }
    
    /**
     * Converts a Rational to a String
     * @return Numerator/Denominator
     */
    public String toString() {
        return ((denom == 1) ? String.valueOf(num) :
                String.valueOf(num)+"/"+String.valueOf(denom));
    }
    
    /**
     * @return value of the Rational as a float
     */
    public float floatValue() {
        return ((float) num)/denom;
    }
    
    /**
     * @param s is the Rational to add to this Rational
     * @return this Rational + s
     */
    public Rational plus(Rational s) {
        return new Rational(num*s.denom+denom*s.num,denom*s.denom);
    }
    
    /**
     * @param s is the Rational to subtract from this Rational
     * @return this Rational - s
     */
    public Rational minus(Rational s) {
        return new Rational(num*s.denom-denom*s.num,denom*s.denom);
    }
    
    /**
     * @param s is the Rational to multiply by this Rational
     * @return this Rational times s
     */
    public Rational times(Rational s) {
        return new Rational(num*s.num,denom*s.denom);
    }
    
    /**
     * @param s is the Rational to divide this Rational by
     * @return this Rational / s
     * @exception java.lang.ArithmeticException if s is 0
     */
    public Rational dividedBy(Rational s) {
        if (s.num==0) throw new ArithmeticException("Rational divison by 0");
        return new Rational(num*s.denom,denom*s.num);
    }

}
