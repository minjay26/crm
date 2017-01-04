package cn.tendata.crm.qiniu.model;

import javax.validation.constraints.NotNull;

/**
 * Created by Luo Min on 2016/12/30.
 */
public class SimpleUploadData {

    @NotNull
    private byte[] bytes;

    @NotNull
    private String filename;

    @NotNull
    private String upToken;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUpToken() {
        return upToken;
    }

    public void setUpToken(String upToken) {
        this.upToken = upToken;
    }
}
