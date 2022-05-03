/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Michelle Naffate
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Pruebas prueba= new Pruebas();
        System.out.println(prueba.generateRandomEANCode());
        System.out.print(prueba.controlCodeCalculator("903510037790"));
        System.out.println(" ");
    }
     private int controlCodeCalculator(String firstTwelveDigits) {
        char[] charDigits = firstTwelveDigits.toCharArray();
        int[] ean13
                = {
                    1, 3
                };
        int sum = 0;
        for (int i = 0; i < charDigits.length; i++) {
            sum += Character.getNumericValue(charDigits[i]) * ean13[i % 2];
        }
        int checksum = 10 - sum % 10;

        if (checksum == 10) {
            checksum = 0;
        }

        return checksum;
    }
     public String generateRandomEANCode() {
        String randomCode = "";
        double random = 0;
        int number = 0;

        for (int i = 0; i < 12; i++) {
            random = Math.random() * 10;
            number = (int) Math.floor(random);
            if (number == 10) {
                number = 0;
            }
            randomCode += String.valueOf(number);
        }
        return randomCode;
    }
}
