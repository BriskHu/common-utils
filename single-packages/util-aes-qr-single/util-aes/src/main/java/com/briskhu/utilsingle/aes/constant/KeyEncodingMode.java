package com.briskhu.utilsingle.aes.constant;

/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-11-10
 **/
public enum KeyEncodingMode {
    PLATIN("普通文本", 1),
    BASE64("Base64", 2)
    ;

    private String encodingName;
    private int type;

    KeyEncodingMode(String encodingName, int type){
        this.encodingName = encodingName;
        this.type = type;
    }

    public String getEncodingName() {
        return encodingName;
    }

    public int getType() {
        return type;
    }


}
