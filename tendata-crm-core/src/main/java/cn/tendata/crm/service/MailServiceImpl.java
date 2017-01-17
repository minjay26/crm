package cn.tendata.crm.service;

import cn.tendata.crm.data.domain.Mail;
import cn.tendata.crm.data.domain.User;
import cn.tendata.crm.data.repository.MailRepository;
import cn.tendata.crm.qiniu.QiniuManager;
import cn.tendata.crm.qiniu.model.SimpleUploadData;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Luo Min on 2016/12/7.
 */
@Service
public class MailServiceImpl extends EntityServiceSupport<Mail,Long,MailRepository> implements MailService{

    private QiniuManager qiniuManager;

    @Autowired
    public MailServiceImpl(MailRepository repository, QiniuManager qiniuManager) {
        super(repository);
        this.qiniuManager = qiniuManager;
    }

    public Page<Mail> getAllByCategory(User user, int category, Pageable pageable) {
        switch (category) {
            case 1:
                return getRepository().findAllByToUserAndReaded(user, false, pageable);
            case 2:
                return getRepository().findAll(pageable);
            case 3:
                return null;
            default:return  null;
        }

    }

    @Override
    public Response upload(String filename, byte[] bytes, String bucketName) throws QiniuException {
        String upToken = qiniuManager.getUpToken(bucketName);
        SimpleUploadData data = new SimpleUploadData();
        data.setFilename(filename);
        data.setBytes(bytes);
        data.setUpToken(upToken);

        UploadManager uploadManager = qiniuManager.getUploadManager(Integer.MAX_VALUE);
        Response response = qiniuManager.upload(uploadManager, data);

        return response;
    }
}
