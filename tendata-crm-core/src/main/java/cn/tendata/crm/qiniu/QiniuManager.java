package cn.tendata.crm.qiniu;

import cn.tendata.crm.qiniu.model.SimpleUploadData;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;

/**
 * Created by Luo Min on 2016/12/30.
 */
public interface QiniuManager {

    String getUpToken(String bucketName);

    Response upload(UploadManager uploadManager, SimpleUploadData data) throws QiniuException;

    UploadManager getUploadManager(int zoneIndex);
}
