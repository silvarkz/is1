public class BitwiseOperators {

    public static void main(String[] args) {
        String str = "Hello World";

        // AND each character in the string with 127
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            c &= 127;
            System.out.print(c);
        }
        System.out.println();

        // XOR each character in the string with 127
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            c ^= 127;
            System.out.print(c);
        }
        System.out.println();
    }
}