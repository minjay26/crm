package cn.tendata.crm.message;

import cn.tendata.crm.data.domain.GoOutRecord;

/**
 * Created by minjay on 2017/2/23.
 */
public interface MessageProcess {

    void apply(GoOutRecord record);

}
