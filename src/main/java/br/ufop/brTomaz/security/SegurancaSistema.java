package br.ufop.brTomaz.security;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SegurancaSistema {


    public static String criptografarSenha(String password) {
        try{
            MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
            byte digestMessage[] = algoritmo.digest(password.getBytes("UTF-8"));
            StringBuilder hexPassword = new StringBuilder();
            for (byte aByte : digestMessage) {
                hexPassword.append(String.format("%02X", 0xFF & aByte));
            }
            return hexPassword.toString();
        }
        catch(UnsupportedEncodingException  e){
            return "A senha não foi codificada com êxito";
        }
        catch(NoSuchAlgorithmException e) {
            return "A senha não foi codificada com êxito";
        }
    }
}
