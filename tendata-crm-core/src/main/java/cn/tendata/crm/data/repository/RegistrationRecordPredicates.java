package cn.tendata.crm.data.repository;

import cn.tendata.crm.data.domain.QRegistrationRecord;
import cn.tendata.crm.data.domain.RegistrationRegulation;
import cn.tendata.crm.data.domain.User;
import com.mysema.query.types.expr.BooleanExpression;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;

/**
 * Created by Luo Min on 2017/1/13.
 */
public abstract class RegistrationRecordPredicates {

    public static BooleanExpression checkRegistred(RegistrationRegulation regulation, User user) {
        QRegistrationRecord record = QRegistrationRecord.registrationRecord;
        BooleanExpression expr = null;

        if (regulation != null) {
            BooleanExpression regularationExpr = record.regulation.eq(regulation);
            expr = expr != null ? expr.and(regularationExpr) : regularationExpr;
        }

        if (user != null) {
            BooleanExpression userExpr = record.user.eq(user);
            expr = expr != null ? expr.and(userExpr) : userExpr;
        }
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd 00:00:00");
        BooleanExpression todayRegistrationExpr = record.registrationDate.goe(DateTime.now().withTimeAtStartOfDay());
        expr = expr != null ? expr.and(todayRegistrationExpr) : todayRegistrationExpr;

        todayRegistrationExpr = record.registrationDate.loe(DateTime.now().plusDays(1).withTimeAtStartOfDay());
        expr = expr != null ? expr.and(todayRegistrationExpr) : todayRegistrationExpr;
        return expr;
    }

    public static BooleanExpression search(User user,DateTime start,DateTime end,String registerType){
        QRegistrationRecord record = QRegistrationRecord.registrationRecord;
        BooleanExpression expr = null;

        if (StringUtils.hasText(registerType)){
            BooleanExpression typeExpr = record.regulation.registrationType.eq(registerType);
            expr = expr != null ? expr.and(typeExpr) : typeExpr;
        }

        if(start != null){
            BooleanExpression startExpr = record.createdDate.goe(start);
            expr = expr != null ? expr.and(startExpr) : startExpr;
        }
        if(end != null){
            BooleanExpression endExpr = record.createdDate.loe(end.plusDays(1));
            expr = expr != null ? expr.and(endExpr) : endExpr;
        }

        if(user != null){
            BooleanExpression userExpr = record.user.eq(user);
            expr = expr != null ? expr.and(userExpr) : userExpr;
        }

        return expr;

    }
}
