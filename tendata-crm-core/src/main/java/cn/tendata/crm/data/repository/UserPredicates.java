package cn.tendata.crm.data.repository;

import cn.tendata.crm.data.domain.QUser;
import cn.tendata.crm.data.domain.User;
import com.mysema.query.types.expr.BooleanExpression;

/**
 * Created by Luo Min on 2017/1/20.
 */
public abstract class UserPredicates {

    public static BooleanExpression queryByStatus(Integer status, User currentUser){
        QUser user = QUser.user;
        BooleanExpression expr = null;
        if (status != null) {
            BooleanExpression userExpr = user.status.eq(status);
            expr = expr != null ? expr.and(userExpr) : userExpr;
        }
        if (currentUser != null) {
            BooleanExpression userExpr = user.notIn(currentUser);
            expr = expr != null ? expr.and(userExpr) : userExpr;
        }

        return expr;
    }
}
