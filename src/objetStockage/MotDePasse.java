package objetStockage;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base64;

public class MotDePasse {

    private static final int iterations = 20*1000;
    private static final int longeurSel = 32;
    private static final int longeurDeCleDemandee = 512;


    public static String getSelDeHash(String motDePasse) throws Exception {

        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(longeurSel);

        return Base64.encodeBase64String(salt) + "$" + hash(motDePasse, salt);
    }

    public static boolean verification(String motDePasse, String hashEnregistre) throws Exception{

        String[] saltAndPass = hashEnregistre.split("\\$");
        if (saltAndPass.length != 2) {
            throw new IllegalStateException("Le mot de passe hashé sauvegardé n'est pas du format 'sel$hash'");
        }
        String hashOfInput = hash(motDePasse, Base64.decodeBase64(saltAndPass[0]));
        return hashOfInput.equals(saltAndPass[1]);
    }

    // hash SHA-512

    private static String hash(String motDePasse, byte[] sel) throws Exception {

        if (motDePasse == null || motDePasse.length() == 0){
            throw new IllegalArgumentException("Mots de passe vide non supportés");
        }

        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        SecretKey key = f.generateSecret( new PBEKeySpec( motDePasse.toCharArray(), sel, iterations, longeurDeCleDemandee) );

        return Base64.encodeBase64String(key.getEncoded());
    }

}
