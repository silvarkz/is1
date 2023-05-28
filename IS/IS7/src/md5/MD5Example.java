package md5;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Example {
    public static void main(String[] args) {
        String text = "Hello, World!"; // The text for which you want to calculate the MD5 digest
        
        try {
            // Create an instance of MessageDigest with MD5 algorithm
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // Update the message digest with the input text
            md.update(text.getBytes());
            
            // Calculate the message digest
            byte[] digest = md.digest();
            
            // Convert the byte array to a hexadecimal string representation
            BigInteger number = new BigInteger(1, digest);
            String md5 = number.toString(16);
            
            // Pad the string with leading zeros if necessary
            while (md5.length() < 32) {
                md5 = "0" + md5;
            }
            
            // Print the MD5 digest
            System.out.println("MD5 Digest: " + md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
