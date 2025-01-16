package br.ifrn.edu.jeferson.ecommerce.utils;
import java.util.InputMismatchException;

public class CPFUtils {

    public static boolean isValidCPF(String cpf) {
        cpf = cpf.replaceAll("[\\.\\-]", "");

        if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            char dig10, dig11;
            int sm, i, r, num, peso;


            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = cpf.charAt(i) - 48;
                sm += (num * peso);
                peso--;
            }
            r = 11 - (sm % 11);
            dig10 = (r == 10 || r == 11) ? '0' : (char) (r + 48);


            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = cpf.charAt(i) - 48;
                sm += (num * peso);
                peso--;
            }
            r = 11 - (sm % 11);
            dig11 = (r == 10 || r == 11) ? '0' : (char) (r + 48);

            return (dig10 == cpf.charAt(9) && dig11 == cpf.charAt(10));
        } catch (InputMismatchException e) {
            return false;
        }
    }

}
