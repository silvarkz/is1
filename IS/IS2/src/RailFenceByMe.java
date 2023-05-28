import java.util.Scanner;

public class RailFenceByMe {

	private static String encryptRailFence(String text, int key) {

		String encrypted_string = "";
		boolean check = false;
		int j = 0;
		int row = key;
		int cols = text.length();

		char a[][] = new char[row][cols];
		for (int i = 0; i < cols; i++) {
			if (j == 0 || j == key - 1) {
				check = !check;
			}
			a[j][i] = text.charAt(i);

			if (check)
				j++;
			else
				j--;
		}

		for (int i = 0; i < row; i++) {
			for (int k = 0; k < cols; k++) {
				if (a[i][k] != 0)

					encrypted_string += a[i][k];

			}
		}

		for (int i = 0; i < row; i++) {
			for (int k = 0; k < cols; k++) {
				System.out.print(a[i][k] + " ");
			}
			System.out.print("\n");
		}

		return encrypted_string;
	}

	private static String decryptRailfence(String text, int key) {
		String decryptedtext = "";
		boolean check = false;
		int j = 0;
		int row = key;
		int cols = text.length();

		char a[][] = new char[row][cols];
		for (int i = 0; i < cols; i++) {
			if (j == 0 || j == key - 1) {
				check = !check;
			}
			a[j][i] = '*';

			if (check)
				j++;
			else
				j--;
		}

		int index = 0;
		check=false;
		for (int i = 0; i < row; i++) {
			for (int k = 0; k < cols; k++) {
				if (a[i][k] == '*' && index < cols) {
					a[i][k] = text.charAt(index++);
				}

			}
		}
		
		j=0;
		for (int i = 0; i < cols; i++) {
			if (j == 0 || j == key - 1) {
				check = !check;
			}
			decryptedtext += a[j][i];

			if (check)
				j++;
			else
				j--;
		}

		return decryptedtext;
	}

	public static void main(String[] args) {

		Scanner sn = new Scanner(System.in);
		System.out.print("Enter Text :");
		String Text = sn.nextLine();

		System.out.print("Enter Key");
		int key = sn.nextInt();

		System.out.println("Encrypted Message: ");
		String Encrypted_text = encryptRailFence(Text, key);
		System.out.print("\n" + Encrypted_text);

		System.out.println("\n\nDecrypted Message :");
		System.out.print(decryptRailfence(Encrypted_text, key));

	}

}
