import java.util.InputMismatchException;
import java.util.Scanner;

class VariableByteEncoding
{
	public static void main(String[] args)
	{
        //Data Input and Validation
		Scanner keyboard = new Scanner(System.in);
        int n = -1;
        do {
            try {
                System.out.println("Enter an integer:");
                n = keyboard.nextInt();
                while(n<0) {
                	System.out.println("Number must be non negative");
                	System.out.println("Enter an integer:");
                    n = keyboard.nextInt();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
            }
            keyboard.nextLine(); // clears the buffer
        } while (n < 0);
            
        String binary = Integer.toBinaryString(n);
        
        //First scenario: numbers<=127 will be represented with 1 byte
        if(n<=127){
            if(binary.length()< 7){
                do{
                    binary = "0" + binary;
                }while(binary.length()<7);
                binary = "1" + binary;
            }    
            else
                binary = '1' + binary;
            
		    System.out.println("The variable byte code representation of " + n + " is " + binary);
        }
        
        //Second Scenario: numbers>127 will be represented with more than 1 bytes
        else{
            
            // Creating array of string length
            char[] bits = new char[binary.length()];
            String reverse = new StringBuilder(new String(binary)).reverse().toString();
  
            // Copy character by character into array
            for (int i = 0; i < reverse.length(); i++) {
                bits[i] = reverse.charAt(i);
            }

            //LOWER BITS
            char[] lowerBits = new char[7];
            for(int i = 0; i <= 6; i++)
            {
                lowerBits[i] = bits[i];
            }
            String lowerBits1 =new String(lowerBits).toString();
            lowerBits1 = lowerBits1 + "1";
            String newBinary = new StringBuilder(new String(lowerBits1)).reverse().toString();
            
            //HIGHER BITS
            char[] higherBits = new char[8];
            int i = 7;
            int j = 0;
            int k = 0;
            String higherBitsString;
            String newBinary1 = "";
            while(k<higherBits.length && i<bits.length) {
            	higherBits[k] = bits[i];
            	i++;
            	j++;
            	k++;
            	if(j==7) {
            		higherBits[j] = '0';
            		j=0;
            		k=0;
            		higherBitsString = new StringBuilder(new String (higherBits)).reverse().toString();
            		newBinary1 = higherBitsString.concat(" ").concat(newBinary1);
            		for(int w=0;w<higherBits.length;w++) {
            			higherBits[w] = Character.MIN_VALUE;
            		}
            	}
            }
            k = higherBits.length-1;
    		while(!(higherBits[k]=='1' || higherBits[k]=='0') && k>0) {
    			higherBits[k]='0';
    			k--;
    		}
    		higherBitsString = new StringBuilder(new String (higherBits)).reverse().toString();
    		newBinary1 = higherBitsString.concat(" ").concat(newBinary1);
            
            newBinary1 = new StringBuilder(new String(newBinary1)).toString();

            System.out.println("The variable byte code representation of " + n + " is " + newBinary1 + newBinary);

        
		
        }
	}
}