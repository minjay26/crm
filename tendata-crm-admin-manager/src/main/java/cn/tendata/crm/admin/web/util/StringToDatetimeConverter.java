package cn.tendata.crm.admin.web.util;

import org.joda.time.DateTime;
import org.springframework.core.convert.converter.Converter;


public class StringToDatetimeConverter implements Converter<String, DateTime> {

    @Override
    public DateTime convert(String source) {
        return DateTime.parse(source);
    }
}
