package com.briskhu.utilsingle.aes.constant;


/**
 * <p/>
 *
 * @author Brisk Hu
 * created on 2019-11-10
 **/
public enum AesModeEnum {
    ALL_DEFAULT("全默认方式", 1),
    CBCPKCS7("CBC/PKCS7方式", 2)
    ;

    String modeName = null;
    int type = -1;

    AesModeEnum(String modeName, int type){
        this.modeName = modeName;
        this.type = type;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
