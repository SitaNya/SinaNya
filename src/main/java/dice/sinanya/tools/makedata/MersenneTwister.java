package dice.sinanya.tools.makedata;

import java.io.*;

/**
 * 网上拿来的马特赛特旋转演算法用于生成随机数
 *
 * @author zhangxiaozhou
 */
public strictfp class MersenneTwister extends java.util.Random implements Serializable, Cloneable {
    /**
     * @param serialVersionUID Serialization
     * @param N Period parameters
     * @param MATRIX_A private static final * constant vector a
     * @param UPPER_MASK most significant w-r bits
     * @param LOWER_MASK least significant r bits
     * @param TEMPERING_MASK_B Tempering parameters
     * @param mt the array for the state vector
     * @param mti mti==N+1 means mt[N] is not initialized
     */
    private static final long serialVersionUID = -8219700664442619525L;
    private static final int N = 624;
    private static final int M = 397;
    private static final int MATRIX_A = 0x9908b0df;
    private static final int UPPER_MASK = 0x80000000;
    private static final int LOWER_MASK = 0x7fffffff;
    private static final int TEMPERING_MASK_B = 0x9d2c5680;
    private static final int TEMPERING_MASK_C = 0xefc60000;
    private int[] mt;
    private int mti;
    private int[] mag01;

    private double nextNextGaussian;
    private boolean haveNextNextGaussian;


    /**
     * Constructor using the default seed.
     */
    public MersenneTwister() {
        this(System.currentTimeMillis());
    }

    /**
     * Constructor using a given seed.  Though you pass this seed in
     * as a long, it's best to make sure it's actually an integer.
     */
    private MersenneTwister(long seed) {
        super(seed);
        setSeed(seed);
    }

    /**
     * Initalize the pseudo random number generator.  Don't
     * pass in a long that's bigger than an int (Mersenne Twister
     * only uses the first 32 bits for its seed).
     */

    @Override
    synchronized public void setSeed(long seed) {
        // it's always good style to call super
        super.setSeed(seed);

        // Due to a bug in java.util.Random clear up to 1.2, we're
        // doing our own Gaussian variable.
        haveNextNextGaussian = false;

        mt = new int[N];

        mag01 = new int[2];
        mag01[1] = MATRIX_A;

        mt[0] = (int) (seed);
        mt[0] = (int) seed;
        for (mti = 1; mti < N; mti++) {
            mt[mti] =
                    (1812433253 * (mt[mti - 1] ^ (mt[mti - 1] >>> 30)) + mti);
            /* See Knuth TAOCP Vol2. 3rd Ed. P.106 for multiplier. */
            /* In the previous versions, MSBs of the seed affect   */
            /* only MSBs of the array mt[].                        */
            /* 2002/01/09 modified by Makoto Matsumoto             */
            // mt[mti] &= 0xffffffff;
            /* for >32 bit machines */
        }
    }


    /**
     * Returns an integer with <i>bits</i> bits filled with a random number.
     */
    @Override
    synchronized protected int next(int bits) {
        int y;

        if (mti >= N)
        {
            int kk;
            final int[] mt = this.mt;
            final int[] mag01 = this.mag01;

            for (kk = 0; kk < N - M; kk++) {
                y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
                mt[kk] = mt[kk + M] ^ (y >>> 1) ^ mag01[y & 0x1];
            }
            for (; kk < N - 1; kk++) {
                y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
                mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ mag01[y & 0x1];
            }
            y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
            mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ mag01[y & 0x1];

            mti = 0;
        }

        y = mt[mti++];
        y ^= y >>> 11;
        y ^= (y << 7) & TEMPERING_MASK_B;
        y ^= (y << 15) & TEMPERING_MASK_C;
        y ^= (y >>> 18);

        return y >>> (32 - bits);
    }

    /* If you've got a truly old version of Java, you can omit these
       two next methods. */

    private synchronized void writeObject(ObjectOutputStream out)
            throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    /**
     * This method is missing from jdk 1.0.x and below.  JDK 1.1
     * includes this for us, but what the heck.
     */
    @Override
    public boolean nextBoolean() {
        return next(1) != 0;
    }

    /**
     * This method is missing from JDK 1.1 and below.  JDK 1.2
     * includes this for us, but what the heck.
     */

    @Override
    public int nextInt(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive, got: " + n);
        }

        if ((n & -n) == n) {
            return (int) ((n * (long) next(31)) >> 31);
        }

        int bits, val;
        do {
            bits = next(31);
            val = bits % n;
        }
        while (bits - val + (n - 1) < 0);
        return val;
    }


    /**
     * A bug fix for versions of JDK 1.1 and below.  JDK 1.2 fixes
     * this for us, but what the heck.
     */
    @Override
    public double nextDouble() {
        return (((long) next(26) << 27) + next(27))
                / (double) (1L << 53);
    }

    /**
     * A bug fix for versions of JDK 1.1 and below.  JDK 1.2 fixes
     * this for us, but what the heck.
     */

    @Override
    public float nextFloat() {
        return next(24) / ((float) (1 << 24));
    }


    /**
     * A bug fix for all versions of the JDK.  The JDK appears to
     * use all four bytes in an integer as independent byte values!
     * Totally wrong. I've submitted a bug report.
     */

    @Override
    public void nextBytes(byte[] bytes) {
        for (int x = 0; x < bytes.length; x++) {
            bytes[x] = (byte) next(8);
        }
    }

    /**
     * A bug fix for all JDK code including 1.2.  nextGaussian can theoretically
     * ask for the log of 0 and divide it by 0! See Java bug
     * <a href="http://developer.java.sun.com/developer/bugParade/bugs/4254501.html">
     * http://developer.java.sun.com/developer/bugParade/bugs/4254501.html</a>
     */

    @Override
    synchronized public double nextGaussian() {
        if (haveNextNextGaussian) {
            haveNextNextGaussian = false;
            return nextNextGaussian;
        } else {
            double v1, v2, s;
            do {
                v1 = 2 * nextDouble() - 1;
                v2 = 2 * nextDouble() - 1;
                s = v1 * v1 + v2 * v2;
            } while (s >= 1 || s == 0);
            double multiplier = StrictMath.sqrt(-2 * StrictMath.log(s) / s);
            nextNextGaussian = v2 * multiplier;
            haveNextNextGaussian = true;
            return v1 * multiplier;
        }
    }

    /**
     * We're overriding all internal data, to my knowledge, so this should be okay
     *
     * @return 原值
     */
    @Override
    public Object clone() {
        try {
            MersenneTwister f = (MersenneTwister) (super.clone());
            f.mt = mt.clone();
            f.mag01 = mag01.clone();
            return f;
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

}