package objetStockage;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
//import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base64;

/**
 * Regroupe toutes les méthodes pour gérer les mots de passe de façon sécurisée
 */
public class MotDePasse {

    private static final int iterations = 20*1000;
    //private static final int longeurSel = 32;
    private static final int longeurDeCleDemandee = 512;

/*
*    /**
*    * Retourne le mot de passe hash sous la forme : sel$motDePasseHashed
*     * @param motDePasse (String)
*     * @return Sel (String)
*     * @throws Exception si Mot de passe est nul
*     /*
*    public static String getMotDePassePourStockage(String motDePasse) throws Exception {
*
*        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(longeurSel);
*
*        return Base64.encodeBase64String(salt) + "$" + hash(motDePasse, salt);
*    }
*/
    /**
     * Vérifie si le mot de passe correspond au mot de passe hash
     * @param motDePasse (String)
     * @param hashEnregistre (String)
     * @return Boolean
     * @throws Exception si le mot de passe hashé sauvegardé n'est pas du format 'sel$hash'
     */
    public static boolean verification(String motDePasse, String hashEnregistre) throws Exception{

        if( hashEnregistre==null ){
            return false;
        }
        String[] saltAndPass = hashEnregistre.split("\\$");
        if (saltAndPass.length != 2) {
            throw new IllegalStateException("Le mot de passe hashé sauvegardé n'est pas du format 'sel$hash'");
        }
        String hashOfInput = hash(motDePasse, Base64.decodeBase64(saltAndPass[0]));
        return hashOfInput.equals(saltAndPass[1]);
    }

    /**
     * Hash le mot de passe en SHA-512+sel
     *
     * @param motDePasse (String)
     * @param sel (byte[])
     * @return hash (String)
     * @throws Exception  si le mot de passe entré est vide
     */
    private static String hash(String motDePasse, byte[] sel) throws Exception {

        if (motDePasse == null || motDePasse.length() == 0){
            throw new IllegalArgumentException("Mots de passe vide non supportés");
        }

        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        SecretKey key = f.generateSecret( new PBEKeySpec( motDePasse.toCharArray(), sel, iterations, longeurDeCleDemandee) );

        return Base64.encodeBase64String(key.getEncoded());
    }

}
