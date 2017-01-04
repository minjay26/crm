package cn.tendata.crm.qiniu;

import cn.tendata.crm.qiniu.config.QiniuConfig;
import cn.tendata.crm.qiniu.model.SimpleUploadData;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.util.Assert;

/**
 * Created by Luo Min on 2016/12/30.
 */
public class QiniuManagerImpl implements QiniuManager {

    @Override
    public String getUpToken(String bucketName) {
        return auth().uploadToken(bucketName);
    }

    @Override
    public UploadManager getUploadManager(int zoneIndex) {
        Zone zone = getZone(zoneIndex);
        Configuration configuration = new Configuration(zone);
        return new UploadManager(configuration);

    }

    @Override
    public Response upload(UploadManager uploadManager, SimpleUploadData data) throws QiniuException {
        Assert.notNull(uploadManager);
        Response response = uploadManager.put(data.getBytes(), data.getFilename(), data.getUpToken());
        return response;
    }

    private Auth auth() {
        return Auth.create(QiniuConfig.ACCESS_KEY, QiniuConfig.SECRET_KEY);
    }

    private Zone getZone(int zoneIndex) {
        Zone zone;
        switch (zoneIndex) {
            case 0:
                zone = Zone.zone0();
                break;
            case 1:
                zone = Zone.zone1();
                break;
            case 2:
                zone = Zone.zone2();
                break;
            default:
                zone = Zone.autoZone();
        }
        return zone;
    }
}
