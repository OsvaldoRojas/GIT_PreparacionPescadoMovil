package com.grupo.pinsa.sip.simulador.utilerias;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Encriptacion {

    private static byte[] encryptMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        return md5.digest();
    }

    public static String md5(String input){
        byte [] md5Input= input.getBytes();
        BigInteger md5Data = null;
        try{
            md5Data = new BigInteger(1, Encriptacion.encryptMD5(md5Input));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        String mdSting = md5Data.toString(16);
        return mdSting;
    }
}
