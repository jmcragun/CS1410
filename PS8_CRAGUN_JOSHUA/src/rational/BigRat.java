package rational;

import java.math.BigInteger;

/**
 * Provides rational number (fraction) objects. The rational arithmetic provided by Rational objects is subject to
 * integer overflow if the numerator and/or denominator becomes too large.
 */
public class BigRat
{
    /**
     * The numerator of this Rat. The gcd of |num| and den is always 1.
     */
    private long longNum;

    /**
     * The denominator of this Rat. den must be positive.
     */
    private long longDen;

    /**
     * Creates the rational number 0
     */
    public BigRat ()
    {
        longNum = 0;
        longDen = 1;
    }

    /**
     * Creates the rational number n
     */
    public BigRat (long n)
    {
        longNum = n;
        longDen = 1;
    }

    /**
     * If d is zero, throws an IllegalArgumentException. Otherwise creates the rational number n/d
     */
    public BigRat (long n, long d)
    {
        if (d == 0)
        {
            throw new IllegalArgumentException();
        }

        // Deals with signs
        if (d < 0)
        {
            d = -d;
            n = -n;
        }

        // Deal with lowest terms
        long g = gcd(Math.abs(n), d);
        longNum = n / g;
        longDen = d / g;
    }

    /**
     * The numerator of a BigRat with BigIntegers as inputs. The gcd of |bigNum| and bigDen is always 1.
     */
    private BigInteger num;
    /**
     * The denominator of a BigRat with BigIntegers as inputs. Must be positive.
     */
    private BigInteger den;

    /**
     * Creates the rational number n
     */
    public BigRat (BigInteger n)
    {
        num = n;
        den = new BigInteger("1");
    }

    /**
     * If d is zero, throws an IllegalArgumentException. Otherwise creates the rational number n/d
     */
    public BigRat (BigInteger r, BigInteger d)
    {
        if (d.intValue() == 0)
        {
            throw new IllegalArgumentException();
        }
        // handles signs
        if (d.compareTo(new BigInteger("0")) < 0)
        {
            d = d.multiply(new BigInteger("-1"));
            r = r.multiply(new BigInteger("-1"));
        }
        BigInteger g = d.gcd(r);
        num = r.divide(g);
        den = d.divide(g);
    }

    /**
     * Returns the sum of this and r Rat x = new Rat(5, 3); Rat y = new Rat(1, 5); Rat z = x.add(y); a/b + c/d = (ad +
     * bc) / bd
     */
    public BigRat add (BigRat r)
    {
        if (this.num != null)
        {
            BigInteger n = this.num.multiply(r.den).add(this.den.multiply(r.num));
            BigInteger d = this.den.multiply(r.den);
            return new BigRat(n, d);
        }
        else
        {
            long n = this.longNum * r.longDen + this.longDen * r.longNum;
            long d = this.longDen * r.longDen;
            return new BigRat(n, d);
        }
    }

    /**
     * Returns the difference of this and r a/b - c/d = (ad - bc) / bd
     */
    public BigRat sub (BigRat r)
    {
        if (this.num != null)
        {
            BigInteger n = this.num.multiply(r.den).subtract(this.den.multiply(r.num));
            BigInteger d = this.den.multiply(r.den);
            return new BigRat(n, d);
        }
        else
        {
            long n = this.longNum * r.longDen - this.longDen * r.longNum;
            long d = this.longDen * r.longDen;
            return new BigRat(n, d);
        }
    }

    /**
     * Returns the product of this and r Rat x = new Rat(5, 3); Rat y = new Rat(1, 5); Rat z = x.mul(y); a/b * c/d =
     * ac/bd
     */
    public BigRat mul (BigRat r)
    {
        if (this.num != null)
        {
            return new BigRat(this.num.multiply(r.num), this.den.multiply(r.den));
        }
        else
        {
            return new BigRat(this.longNum * r.longNum, this.longDen * r.longDen);
        }
    }

    /**
     * If r is zero, throws an IllegalArgumentException. Otherwise, returns the quotient of this and r. a/b / c/d = ad /
     * bc
     */
    public BigRat div (BigRat r)
    {
        if (this.num != null)
        {
            if (r.num.intValue() == 0)
            {
                throw new IllegalArgumentException();
            }
            else
            {
                return new BigRat(this.num.multiply(r.den), this.den.multiply(r.num));
            }
        }
        else
        {
            if (r.longNum == 0)
            {
                throw new IllegalArgumentException();
            }
            else
            {
                return new BigRat(this.longNum * r.longDen, this.longDen * r.longNum);
            }
        }
    }

    /**
     * Returns a negative number if this < r, zero if this = r, a positive number if this > r To compare a/b and c/d,
     * compare ad and bc
     */
    public int compareTo (BigRat r)
    {
        if (this.num != null)
        {
            BigInteger diff = this.num.multiply(r.den).subtract(this.den.multiply(r.num));
            if (diff.compareTo(new BigInteger("0")) < 0)
            {
                return -1;
            }
            else if (diff.compareTo(new BigInteger("0")) > 0)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        else
        {
            long diff = this.longNum * r.longDen - this.longDen * r.longNum;
            if (diff < 0)
            {
                return -1;
            }
            else if (diff > 0)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }

    /**
     * Returns a string version of this in simplest and lowest terms. Examples: 3/4 => "3/4" 6/8 => "3/4" 2/1 => "2" 0/8
     * => "0" 3/-4 => "-3/4"
     */
    public String toString ()
    {
        if (this.num != null)
        {
            if (this.den.intValue() == 1 || this.num.intValue() == 0)
            {
                return this.num.toString() + "";
            }
            else
            {
                return this.num.toString() + "/" + this.den.toString();
            }
        }
        else
        {
            if (longDen == 1)
            {
                return longNum + "";
            }
            else
            {
                return longNum + "/" + longDen;
            }
        }
    }
    /**
     * Returns the greatest common divisor of a and b, where a >= 0 and b > 0.
     * 
     * @param a
     * @param b
     * @return
     */
    public static long gcd (long a, long b)
    {
        while (b > 0)
        {
            long remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }

}
