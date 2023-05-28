package is3;

import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

public class DESExample {

    public static void main(String[] args) throws Exception {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        SecretKey secretKey = keyGenerator.generateKey();

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        Scanner sn = new Scanner(System.in);
        System.out.print("Enter Text: ");
        String plaintext = sn.nextLine();

        System.out.println("\nIntermediate Results (16 Rounds - Encryption):");
        byte[] input = plaintext.getBytes();
        for (int i = 1; i <= 16; i++) {
            // Pad the input to a multiple of 8 bytes
            int paddedLength = (int) Math.ceil((double) input.length / 8) * 8;
            byte[] paddedInput = new byte[paddedLength];
            System.arraycopy(input, 0, paddedInput, 0, input.length);

            input = performDESRound(paddedInput, secretKey, Cipher.ENCRYPT_MODE);
            System.out.println("Round " + i + ": " + DatatypeConverter.printHexBinary(input));
        }

        byte[] ciphertext = cipher.doFinal(plaintext.getBytes());
        System.out.println("\nEncrypted Message: " + Base64.getEncoder().encodeToString(ciphertext));

        System.out.println("\nIntermediate Results (16 Rounds - Decryption):");
        byte[] output = input;
        for (int i = 16; i >= 1; i--) {
            output = performDESRound(output, secretKey, Cipher.DECRYPT_MODE);
            System.out.println("Round " + i + ": " + DatatypeConverter.printHexBinary(output));
        }

        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(ciphertext);
        System.out.println("\nDecrypted Message: " + new String(decryptedBytes));
    }

    private static byte[] performDESRound(byte[] input, SecretKey key, int cipherMode) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(cipherMode, key);
        return cipher.doFinal(input);
    }
}
