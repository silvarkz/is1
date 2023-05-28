import java.math.BigDecimal;
import java.math.BigInteger;

class RSA {
	public static void main(String args[]) {
		int d = 0, i, e;

		int msg = 25;
		double c;
		BigInteger msgback;

		int p = 3;
		int q = 11;

		int n = p * q; // modulus

		int z = (p - 1) * (q - 1); // totient(phi)

		System.out.print("\nFirst Prime Number (p) :"+p);
		System.out.print("\nSecond Prime Number (q) :"+q);
		System.out.println("\nthe value of Phi(z) = " + z);

		for (e = 2; e < z; e++) {

			// e is for public key exponent
			if (gcd(e, z) == 1) {
				break;
			}
		}
		System.out.println("the value of e = " + e);
		
		
		for (i = 0; i <= 9; i++) {
			int x = 1 + (i * z);

			// d is for private key exponent
			if (x % e == 0) {
				d = x / e;
				break;
			}
		}
		System.out.println("the value of d = " + d);
		
		
		
		c = (Math.pow(msg, e)) % n;
		System.out.println("Encrypted message is : " + c);

		// converting int value of n to BigInteger
		BigInteger N = BigInteger.valueOf(n);

		// converting float value of c to BigInteger
		BigInteger C = BigDecimal.valueOf(c).toBigInteger();
		msgback = (C.pow(d)).mod(N);
		System.out.println("Decrypted message is : " + msgback);
	}

	static int gcd(int a, int b) {
	    while (b != 0) {
	        int temp = b;
	        b = a % b;
	        a = temp;
	    }
	    return a;
	}

}



//Sure! Let's go through the RSA algorithm step by step, including the formulas and an example:
//
//Choose two distinct prime numbers, p and q.
//
//Example: Let's choose p = 3 and q = 11.
//Calculate the modulus n by multiplying p and q.
//
//n = p * q
//Example: n = 3 * 11 = 33
//Calculate the totient φ(n) (phi) of n.
//
//φ(n) = (p - 1) * (q - 1)
//Example: φ(n) = (3 - 1) * (11 - 1) = 2 * 10 = 20
//Choose a public key exponent e.
//
//e must be a positive integer greater than 1 and less than φ(n).
//It should be relatively prime to φ(n), meaning gcd(e, φ(n)) = 1.
//Example: Let's choose e = 3.
//Calculate the private key exponent d.
//
//d is the multiplicative inverse of e modulo φ(n).
//It satisfies the equation (e * d) ≡ 1 (mod φ(n)).
//Example: We calculate d using the extended Euclidean algorithm or the modInverse function.
//The public key is (n, e).
//
//Example: Public key is (33, 3).
//The private key is (n, d).
//
//Example: Private key is (33, 7).
//Encryption:
//
//To encrypt a message m, raise it to the power of the public key exponent e and take the remainder modulo n.
//c = (m^e) % n
//Example: Encrypting a message m = 12 using the public key (n, e) = (33, 3) gives us c = (12^3) % 33 = 20.
//Decryption:
//
//To decrypt the ciphertext c, raise it to the power of the private key exponent d and take the remainder modulo n.
//m = (c^d) % n
//Example: Decrypting the ciphertext c = 20 using the private key (n, d) = (33, 7) gives us m = (20^7) % 33 = 12.
//The decrypted value m should match the original message.
//
//In summary, the RSA algorithm involves selecting prime numbers p and q, calculating the modulus n and totient φ(n), choosing the public key exponent e, calculating the private key exponent d, and using the public and private keys for encryption and decryption. The encryption process involves raising the message to the power of e and taking the remainder, while decryption involves raising the ciphertext to the power of d and taking the remainder.
