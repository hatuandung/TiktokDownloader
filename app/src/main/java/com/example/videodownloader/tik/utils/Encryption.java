package com.example.videodownloader.tik.utils;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {
    public static String encryption(String str3) {
        try {
            String str ="ZLDXDo2Bc4VPrWht";
            String str2 ="q2u5ilxHSGYzPm5o";
            IvParameterSpec ivParameterSpec = new IvParameterSpec(str2.getBytes("ISO-8859-1"));
            SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes("ISO-8859-1"), "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            instance.init(1, secretKeySpec, ivParameterSpec);
            return Base64.encodeToString(instance.doFinal(str3.getBytes()), 0);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

}
