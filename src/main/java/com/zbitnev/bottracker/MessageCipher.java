import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MessageCipher {
    private final static String KEY = "SomeunkownKeyKey";

    public byte[] getCipher(String messageWithID) throws GeneralSecurityException {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(messageWithID.getBytes());

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        throw new GeneralSecurityException();
    }

    public byte[] getDecrypt(byte[] bytes) throws GeneralSecurityException {
        try {
            Cipher decriptCipher = Cipher.getInstance("AES");
            SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), "AES");
            decriptCipher.init(Cipher.DECRYPT_MODE, key);
            return decriptCipher.doFinal(bytes);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        throw new GeneralSecurityException();
    }

    public static void printByteArr(byte[] bytes) {
        for (byte b : bytes) {
            System.out.print((char) b);
        }
        System.out.println("\n");
    }
}
