package com.marvel.utils;



import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Component
public class Md5 {

    final String timezone = "1";
    final String privateKey = "0a27551f025ae93f594759b9f2b117ea0aabebea";
    final String publicKey = "770f2f2e02524cf4a62106b1fc6e1aca";

    /** Method convert  timezone + public + private key
     * into a Md5 hashcode required to call API Marvel
     * params hash
     * **/
    public String hashMd5(){

        String hashMd5 = timezone+privateKey+publicKey;
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(hashMd5.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
