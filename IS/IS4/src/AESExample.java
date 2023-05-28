import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class AESExample {

    public static void main(String[] args) throws Exception {

        String keyString = "0123456789abcdef";
        byte[] keyBytes = keyString.getBytes(StandardCharsets.UTF_8);
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        Scanner sn = new Scanner(System.in);
        System.out.print("Enter Text: ");
        String plaintext = sn.nextLine();

        // Pad the input to a multiple of 16 bytes
        int paddedLength = (int) Math.ceil((double) plaintext.length() / 16) * 16;
        byte[] paddedInput = new byte[paddedLength];
        System.arraycopy(plaintext.getBytes(), 0, paddedInput, 0, plaintext.getBytes().length);

        byte[] ciphertext = cipher.doFinal(paddedInput);
        System.out.println("Encrypted Message: " + Base64.getEncoder().encodeToString(ciphertext));

        System.out.println("\nIntermediate Results (Encryption):");
        byte[] input = paddedInput;
        for (int i = 1; i <= 10; i++) {
            input = performAESRound(input, secretKey, Cipher.ENCRYPT_MODE);
            System.out.println("Round " + i + ": " + DatatypeConverter.printHexBinary(input));
        }

        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(ciphertext);
        System.out.println("\nDecrypted Message: " + new String(decryptedBytes));

        System.out.println("\nIntermediate Results (Decryption):");
        byte[] output = input;
        for (int i = 10; i >= 1; i--) {
            output = performAESRound(output, secretKey, Cipher.DECRYPT_MODE);
            System.out.println("Round " + i + ": " + DatatypeConverter.printHexBinary(output));
        }
    }

    private static byte[] performAESRound(byte[] input, SecretKey key, int mode) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(mode, key);
        return cipher.doFinal(input);
    }
}
