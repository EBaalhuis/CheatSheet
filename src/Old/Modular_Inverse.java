package Old;

import java.util.Scanner;

public class Modular_Inverse {

	// Compute inverse of a in Z/pZ, with p prime.
	// I.e. x such that ax = 1 (mod p).
	static long modInvPrime(long a, long p) {
		return (long) Math.pow(a, p - 2) % p;
	}

	// Compute inverse of a in Z/rZ, with a and r co-prime.
	// If they are not co-prime, inverse does not exist.
	static long modInv(long a, long r) {
		if (extgcd(a, r)[0] != 1) {
			return -1;
		}
		return (extgcd(a, r)[1] + r) % r;
	}

	// extgcd method only for testing, required by modInv.
	static long[] extgcd(long p, long q) {
		if (q == 0) {
			return new long[] { Math.abs(p), Long.signum(p), 0 };
		}
		long[] vals = extgcd(q, p % q);
		long d = vals[0];
		long a = vals[2];
		long b = vals[1] - (p / q) * vals[2];
		return new long[] { d, a, b };
	}

	// main method only for testing.
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int r = in.nextInt();
		System.out.println(modInv(a, r));
	}
}