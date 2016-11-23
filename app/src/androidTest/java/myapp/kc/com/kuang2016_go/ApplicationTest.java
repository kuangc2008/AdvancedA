package myapp.kc.com.kuang2016_go;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Base64;
import android.util.Log;

import org.json.JSONObject;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import myapp.kc.com.utils.RSA;
import myapp.kc.com.utils.RSAUtils;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }





    public void testRSA() throws Exception {
        String data="FCAAdSDKPlugin（分发插件ID）,PluginFrsOfWis";
//         String publicKeyString="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCISLP98M/56HexX/9FDM8iuIEQozy6kn2JMcbZS5/BhJ+U4PZIChJfggYlWnd8NWn4BYr2kxxyO8Qgvc8rpRZCkN0OSLqLgZGmNvoSlDw80UXq90ZsVHDTOHuSFHw8Bv//B4evUNJBB8g9tpVxr6P5EJ6FMoR/kY2dVFQCQM4+5QIDAQAB";
//         String privateKeyString="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIhIs/3wz/nod7Ff/0UMzyK4gRCjPLqSfYkxxtlLn8GEn5Tg9kgKEl+CBiVad3w1afgFivaTHHI7xCC9zyulFkKQ3Q5IuouBkaY2+hKUPDzRRer3RmxUcNM4e5IUfDwG//8Hh69Q0kEHyD22lXGvo/kQnoUyhH+RjZ1UVAJAzj7lAgMBAAECgYAVh26vsggY0Yl/Asw/qztZn837w93HF3cvYiaokxLErl/LVBJz5OtsHQ09f2IaxBFedfmy5CB9R0W/aly851JxrI8WAkx2W2FNllzhha01fmlNlOSumoiRF++JcbsAjDcrcIiR8eSVNuB6ymBCrx/FqhdX3+t/VUbSAFXYT9tsgQJBALsHurnovZS1qjCTl6pkNS0V5qio88SzYP7lzgq0eYGlvfupdlLX8/MrSdi4DherMTcutUcaTzgQU20uAI0EMyECQQC6il1Kdkw8Peeb0JZMHbs+cMCsbGATiAt4pfo1b/i9/BO0QnRgDqYcjt3J9Ux22dPYbDpDtMjMRNrAKFb4BJdFAkBMrdWTZOVc88IL2mcC98SJcII5wdL3YSeyOZto7icmzUH/zLFzM5CTsLq8/HDiqVArNJ4jwZia/q6Fg6e8KO2hAkB0EK1VLF/ox7e5GkK533Hmuu8XGWN6I5bHnbYd06qYQyTbbtHMBrFSaY4UH91Qwd3u9gAWqoCZoGnfT/o03V5lAkBqq8jZd2lHifey+9cf1hsHD5WQbjJKPPIb57CK08hn7vUlX5ePJ02Q8AhdZKETaW+EsqJWpNgsu5wPqsy2UynO";
        //获取公钥
        PublicKey publicKey= getPublicKey(DEFAULT_PUBLIC_KEY2);

        //获取私钥
        PrivateKey privateKey=getPrivateKey(DEFAULT_PRIVATE_KEY2);

        //公钥加密
        byte[] encryptedBytes=encrypt(data.getBytes(), publicKey);
        String result = Base64.encodeToString(encryptedBytes, Base64.NO_WRAP);
        Log.i("kcc", "加密后："+  result);

        //私钥解密
        byte[] decryptedBytes= decrypt(Base64.decode(result, Base64.NO_WRAP), privateKey);

        Log.i("kcc", "解密后："+new String(decryptedBytes));

    }

    //将base64编码后的公钥字符串转成PublicKey实例
    public  PublicKey getPublicKey(String publicKey) throws Exception{
        byte[ ] keyBytes=  Base64.decode(publicKey.getBytes(), Base64.NO_WRAP);
        X509EncodedKeySpec keySpec=new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    //将base64编码后的私钥字符串转成PrivateKey实例
    public  PrivateKey getPrivateKey(String privateKey) throws Exception{
        byte[ ] keyBytes=  Base64.decode(privateKey.getBytes(), Base64.NO_WRAP);
        PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    //公钥加密
    public  byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception{
        Cipher cipher=Cipher.getInstance("RSA");//java默认"RSA"="RSA/ECB/PKCS1Padding"
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    //私钥解密
    public  byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception{
        Cipher cipher=Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    public void testRSA2() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        RSA a = new RSA();
        String decodde = a.encrypt("hello world");
        String hehe = a.decrypt();


        Log.i("kcc", "decode->" + decodde + "  hehe->" + hehe);
        Log.i("kcc", "key_" + a.getPriKey() + "  native->" + a.getPubKey());
    }






    public void testRSA3() throws Exception {
        System.err.println("公钥加密——私钥解密");
        String source = "这是一行没有任何意义的文字，你看完了等于没看士大夫反反复复反反复复反反复复反反复复多发时段发生的发生的发生的发生的发生的发生的发生的发生的发生的发生的发生的发生的发生的发生的发生，不是吗？这是一行没有任何意义的文字，asdfsdafsdafsfasdfdfsa你看完了等于没看，不是吗？这是一行没有任何意义的文字，你看完了等于没看，不是吗？这是一行没有任何意义的文字，你看完了等于没看，不是吗？这是一行没有任何意义的文字，你看完了等于没看，不是吗？这是一行没有任何意义的文字，你看完了等于没看，不是吗？这是一行没有任何意义的文字，你看完了等于没看，不是吗？";
        System.out.println("\r加密前文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPublicKey(data, DEFAULT_PUBLIC_KEY);
        System.out.println("加密后文字：\r\n" + new String(encodedData));
        byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData,
                DEFAULT_PRIVATE_KEY);
        String target = new String(decodedData);
        System.out.println("解密后文字: \r\n" + target);
    }


    private static final String DEFAULT_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDB9AaQ96uf+A0/IPM56+hg" +
            "+Gvs\n" +
            "tOzpUIsTtbCvx4cSdNZaxuFxpk6cwg5OCb8TSwHBmJU7ibWDIe0m3tty+xra/baQ\n" +
            "qFf9MaKG7/DA/3b+2MIIVw9sz9Q2FGCI5Cejc8DmF7d1bynjxSdIwx1ZR6ZPs1E+\n" +
            "DOPE7r+saRKE9L9yjQIDAQAB\n";


    private static final String DEFAULT_PUBLIC_KEY2 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDB9AaQ96uf+A0/IPM56+hg" +
            "+Gvs" +
            "tOzpUIsTtbCvx4cSdNZaxuFxpk6cwg5OCb8TSwHBmJU7ibWDIe0m3tty+xra/baQ" +
            "qFf9MaKG7/DA/3b+2MIIVw9sz9Q2FGCI5Cejc8DmF7d1bynjxSdIwx1ZR6ZPs1E+" +
            "DOPE7r+saRKE9L9yjQIDAQAB";


    private static final String DEFAULT_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMH0BpD3q5" +
            "/4DT8g\n" +
            "8znr6GD4a+y07OlQixO1sK/HhxJ01lrG4XGmTpzCDk4JvxNLAcGYlTuJtYMh7Sbe\n" +
            "23L7Gtr9tpCoV/0xoobv8MD/dv7YwghXD2zP1DYUYIjkJ6NzwOYXt3VvKePFJ0jD\n" +
            "HVlHpk+zUT4M48Tuv6xpEoT0v3KNAgMBAAECgYBehZGJptoiXN3/zQwcR0b5szfN\n" +
            "pSLsVc9hrKNOFzxRl1U76j7Gh1k6dixIheF0ApL5iWX5HRSzsp4D2ooyUAP2qgq3\n" +
            "bp3LCz0DuruczHXjGAdYqnhz8j4Zq6oA/hpkBDkDVFmmaOKaQeihnyhZWUX/+LjJ\n" +
            "e5t9+X5i7QpXeuNOVQJBAO6S1zGKF0hHXCkwKFDsPtxtUSSOmWV/YMGSWO5zQuxi\n" +
            "XgPCUM2Ju1M4yvArCVTlVxV6aZZZnEndCmCdWqxNen8CQQDQHtC4xKrOfxeCebaJ\n" +
            "wyxOCP9qmekLA2giGWOyktUIsg/3UbSY9gQ6WLa32NwXJfxamdcMf2vww1aCeN0y\n" +
            "vdTzAkACuu93g6sMirSFTCXJ90oo359kGbWrsa97x57465zCc8W7qUefcooQQ8Ae\n" +
            "y39J124Nst7ud4wQVFVAEczt190lAkEAs2c86WmvC96Gt5SQA+VY1dPvyUbvmuzO\n" +
            "4UYdqRvlxF8qYDnwgkLgliNPLuAjqVi3VqpytHVK6PqaIlJp64m0fQJADsaamc09\n" +
            "97AgTGFUKTtvac4LRYRS5QqvG4IohnQC2Wqi9XvcTjSlRTwsLAX/HsH3s62b+3q3\n" +
            "Jh5dkFPjMsFT9A==\n";


    private static final String DEFAULT_PRIVATE_KEY2 =
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMH0BpD3q5/4DT8g" +
            "8znr6GD4a+y07OlQixO1sK/HhxJ01lrG4XGmTpzCDk4JvxNLAcGYlTuJtYMh7Sbe" +
            "23L7Gtr9tpCoV/0xoobv8MD/dv7YwghXD2zP1DYUYIjkJ6NzwOYXt3VvKePFJ0jD" +
            "HVlHpk+zUT4M48Tuv6xpEoT0v3KNAgMBAAECgYBehZGJptoiXN3/zQwcR0b5szfN" +
            "pSLsVc9hrKNOFzxRl1U76j7Gh1k6dixIheF0ApL5iWX5HRSzsp4D2ooyUAP2qgq3" +
            "bp3LCz0DuruczHXjGAdYqnhz8j4Zq6oA/hpkBDkDVFmmaOKaQeihnyhZWUX/+LjJ" +
            "e5t9+X5i7QpXeuNOVQJBAO6S1zGKF0hHXCkwKFDsPtxtUSSOmWV/YMGSWO5zQuxi" +
            "XgPCUM2Ju1M4yvArCVTlVxV6aZZZnEndCmCdWqxNen8CQQDQHtC4xKrOfxeCebaJ" +
            "wyxOCP9qmekLA2giGWOyktUIsg/3UbSY9gQ6WLa32NwXJfxamdcMf2vww1aCeN0y" +
            "vdTzAkACuu93g6sMirSFTCXJ90oo359kGbWrsa97x57465zCc8W7qUefcooQQ8Ae" +
            "y39J124Nst7ud4wQVFVAEczt190lAkEAs2c86WmvC96Gt5SQA+VY1dPvyUbvmuzO" +
            "4UYdqRvlxF8qYDnwgkLgliNPLuAjqVi3VqpytHVK6PqaIlJp64m0fQJADsaamc09" +
            "97AgTGFUKTtvac4LRYRS5QqvG4IohnQC2Wqi9XvcTjSlRTwsLAX/HsH3s62b+3q3" +
            "Jh5dkFPjMsFT9A==";


    static void encodeRequest(JSONObject json) throws Exception{
        JSONObject jsonObject = new JSONObject();
        for(int i=0;i<1000;i++){
            jsonObject.put(i+"","value"+i);
        }
        byte[] data = jsonObject.toString().getBytes();
        byte[] encodedData = RSAUtils.encryptByPublicKey(data, DEFAULT_PUBLIC_KEY2);
        System.out.println("加密后文字：\r\n" + new String(encodedData));
        byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData,
                DEFAULT_PRIVATE_KEY2);
        String target = new String(decodedData);
        System.out.println("解密后文字: \r\n" + target);
    }















//
//
//
//    public void test() throws Exception {
//        PrivateKey privKey = ApplicationTest.get();
//        System.out.println(privKey.toString());
//    }
//
//
//    public static String aaa = "-----BEGIN RSA PRIVATE KEY-----\n" +
//            "MIICXAIBAAKBgQDB9AaQ96uf+A0/IPM56+hg+GvstOzpUIsTtbCvx4cSdNZaxuFx\n" +
//            "pk6cwg5OCb8TSwHBmJU7ibWDIe0m3tty+xra/baQqFf9MaKG7/DA/3b+2MIIVw9s\n" +
//            "z9Q2FGCI5Cejc8DmF7d1bynjxSdIwx1ZR6ZPs1E+DOPE7r+saRKE9L9yjQIDAQAB\n" +
//            "AoGAXoWRiabaIlzd/80MHEdG+bM3zaUi7FXPYayjThc8UZdVO+o+xodZOnYsSIXh\n" +
//            "dAKS+Yll+R0Us7KeA9qKMlAD9qoKt26dyws9A7q7nMx14xgHWKp4c/I+GauqAP4a\n" +
//            "ZAQ5A1RZpmjimkHooZ8oWVlF//i4yXubffl+Yu0KV3rjTlUCQQDuktcxihdIR1wp\n" +
//            "MChQ7D7cbVEkjpllf2DBkljuc0LsYl4DwlDNibtTOMrwKwlU5VcVemmWWZxJ3Qpg\n" +
//            "nVqsTXp/AkEA0B7QuMSqzn8Xgnm2icMsTgj/apnpCwNoIhljspLVCLIP91G0mPYE\n" +
//            "Oli2t9jcFyX8WpnXDH9r8MNWgnjdMr3U8wJAArrvd4OrDIq0hUwlyfdKKN+fZBm1\n" +
//            "q7Gve8ee+OucwnPFu6lHn3KKEEPAHst/SdduDbLe7neMEFRVQBHM7dfdJQJBALNn\n" +
//            "POlprwvehreUkAPlWNXT78lG75rszuFGHakb5cRfKmA58IJC4JYjTy7gI6lYt1aq\n" +
//            "crR1Suj6miJSaeuJtH0CQA7GmpnNPfewIExhVCk7b2nOC0WEUuUKrxuCKIZ0Atlq\n" +
//            "ovV73E40pUU8LCwF/x7B97Otm/t6tyYeXZBT4zLBU/Q=\n" +
//            "-----END RSA PRIVATE KEY-----";
//
//    public static RSAPrivateKey get() throws Exception {
//        byte[] encodedPrivateKey = Base64.decode(privKeyPEM, Base64.DEFAULT);
//
//        try {
//            ASN1Sequence primitive = (ASN1Sequence) ASN1Sequence
//                    .fromByteArray(encodedPrivateKey);
//            Enumeration<?> e = primitive.getObjects();
//            BigInteger v = ((DERInteger) e.nextElement()).getValue();
//
//            int version = v.intValue();
//            if (version != 0 && version != 1) {
//                throw new IllegalArgumentException("wrong version for RSA private key");
//            }
//            /**
//             * In fact only modulus and private exponent are in use.
//             */
//            BigInteger modulus = ((DERInteger) e.nextElement()).getValue();
//            BigInteger publicExponent = ((DERInteger) e.nextElement()).getValue();
//            BigInteger privateExponent = ((DERInteger) e.nextElement()).getValue();
//            BigInteger prime1 = ((DERInteger) e.nextElement()).getValue();
//            BigInteger prime2 = ((DERInteger) e.nextElement()).getValue();
//            BigInteger exponent1 = ((DERInteger) e.nextElement()).getValue();
//            BigInteger exponent2 = ((DERInteger) e.nextElement()).getValue();
//            BigInteger coefficient = ((DERInteger) e.nextElement()).getValue();
//
//            RSAPrivateKeySpec spec = new RSAPrivateKeySpec(modulus, privateExponent);
//            KeyFactory kf = KeyFactory.getInstance("RSA");
//            PrivateKey pk = kf.generatePrivate(spec);
//        } catch (IOException e2) {
//            throw new IllegalStateException();
//        } catch (NoSuchAlgorithmException e) {
//            throw new IllegalStateException(e);
//        } catch (InvalidKeySpecException e) {
//            throw new IllegalStateException(e);
//        }
//    }



//
//    public static RSAPrivateKey get() throws Exception {
//        // PKCS#8 format
//        final String PEM_PRIVATE_START = "-----BEGIN PRIVATE KEY-----";
//        final String PEM_PRIVATE_END = "-----END PRIVATE KEY-----";
//
//        // PKCS#1 format
//        final String PEM_RSA_PRIVATE_START = "-----BEGIN RSA PRIVATE KEY-----";
//        final String PEM_RSA_PRIVATE_END = "-----END RSA PRIVATE KEY-----";
//
////        Path path = Paths.get(pemFileName.getAbsolutePath());
////
//        String privateKeyPem = aaa;
//
//        if (privateKeyPem.indexOf(PEM_PRIVATE_START) != -1) { // PKCS#8 format
//            privateKeyPem = privateKeyPem.replace(PEM_PRIVATE_START, "").replace(PEM_PRIVATE_END, "");
//            privateKeyPem = privateKeyPem.replaceAll("\\s", "");
//
//            byte[] pkcs8EncodedKey = Base64.getDecoder().decode(privateKeyPem);
//
//            KeyFactory factory = KeyFactory.getInstance("RSA");
//            return (RSAPrivateKey) factory.generatePrivate(new PKCS8EncodedKeySpec(pkcs8EncodedKey));
//
//        } else if (privateKeyPem.indexOf(PEM_RSA_PRIVATE_START) != -1) {  // PKCS#1 format
//
//            privateKeyPem = privateKeyPem.replace(PEM_RSA_PRIVATE_START, "").replace(PEM_RSA_PRIVATE_END, "");
//            privateKeyPem = privateKeyPem.replaceAll("\\s", "");
//
//            DerInputStream derReader = new DerInputStream(Base64.getDecoder().decode(privateKeyPem));
//
//            DerValue[] seq = derReader.getSequence(0);
//
//            if (seq.length < 9) {
//                throw new GeneralSecurityException("Could not parse a PKCS1 private key.");
//            }
//
//            // skip version seq[0];
//            BigInteger modulus = seq[1].getBigInteger();
//            BigInteger publicExp = seq[2].getBigInteger();
//            BigInteger privateExp = seq[3].getBigInteger();
//            BigInteger prime1 = seq[4].getBigInteger();
//            BigInteger prime2 = seq[5].getBigInteger();
//            BigInteger exp1 = seq[6].getBigInteger();
//            BigInteger exp2 = seq[7].getBigInteger();
//            BigInteger crtCoef = seq[8].getBigInteger();
//
//            RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(modulus, publicExp, privateExp, prime1, prime2, exp1, exp2, crtCoef);
//
//            KeyFactory factory = KeyFactory.getInstance("RSA");
//
//            return factory.generatePrivate(keySpec);
//        }
//    }
}