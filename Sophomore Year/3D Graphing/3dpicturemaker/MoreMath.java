package classes.extras;

import java.util.List;

/**
    The MoreMath class provides functions not contained in the
    java.lang.Math or java.lang.StrictMath classes.
*/
public class MoreMath {

    /**
        Returns the sign of the number. Returns -1 for negative,
        1 for positive, and 0 otherwise.
    */
    public static int sign(short v) {
        return (v>0)?1:(v<0)?-1:0;
    }


    /**
        Returns the sign of the number. Returns -1 for negative,
        1 for positive, and 0 otherwise.
    */
    public static int sign(int v) {
        return (v>0)?1:(v<0)?-1:0;
    }


    /**
        Returns the sign of the number. Returns -1 for negative,
        1 for positive, and 0 otherwise.
    */
    public static int sign(long v) {
        return (v>0)?1:(v<0)?-1:0;
    }


    /**
        Returns the sign of the number. Returns -1 for negative,
        1 for positive, and 0 otherwise.
    */
    public static int sign(double v) {
        return (v>0)?1:(v<0)?-1:0;
    }


    /**
        Returns the sign of the number. Returns -1 for negative,
        1 for positive, and 0 otherwise.
    */
    public static int sign(float v) {
        return (v>0)?1:(v<0)?-1:0;
    }


    /**
        Faster ceil function to convert a double to an int.
        Contrary to the java.lang.Math ceil function, this
        function takes a double as an argument, returns an int
        instead of a double, and does not consider special cases.
    */
    public static int ceil(double f) {
        if (f > 0) {
            return (int)f + 1;
        }
        else {
           return (int)f;
        }
    }


    /**
        Faster floor function to convert a double to an int.
        Contrary to the java.lang.Math floor function, this
        function takes a double as an argument, returns an int
        instead of a double, and does not consider special cases.
    */
    public static int floor(double f) {
        if (f >= 0) {
            return (int)f;
        }
        else {
           return (int)f - 1;
        }
    }


    /**
        Returns a random integer from 0 to max (inclusive).
    */
    public static int random(int max) {
        return (int)Math.round(Math.random() * max);
    }


    /**
        Returns a random integer from min to max (inclusive).
    */
    public static int random(int min, int max) {
        return min + random(max-min);
    }


    /**
        Returns a random double from 0 to max (inclusive).
    */
    public static double random(double max) {
        return (double)Math.random()*max;
    }


    /**
        Returns a random double from min to max (inclusive).
    */
    public static double random(double min, double max) {
        return min + random(max-min);
    }


    /**
        Returns a random object from a List.
    */
    public static Object random(List list) {
        return list.get(random(list.size() - 1));
    }


    /**
        Returns true if a random "event" occurs. The specified
        value, p, is the probability (0 to 1) that the random
        "event" occurs.
    */
    public static boolean chance(double p) {
        return (Math.random() <= p);
    }

    /**
        Returns true if the specified number is a power of 2.
    */
    public static boolean isPowerOfTwo(int n) {
        return ((n & (n-1)) == 0);
    }


    /**
        Gets the number of "on" bits in an integer.
    */
    public static int getBitCount(int n) {
        int count = 0;
        while (n > 0) {
            count+=(n & 1);
            n>>=1;
        }
        return count;
    }
}
