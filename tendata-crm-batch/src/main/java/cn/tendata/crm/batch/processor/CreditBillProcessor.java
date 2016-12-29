
package cn.tendata.crm.batch.processor;


import cn.tendata.crm.batch.model.CreditBill;
import org.springframework.batch.item.ItemProcessor;


public class CreditBillProcessor implements ItemProcessor<CreditBill, CreditBill> {

    public CreditBill process(CreditBill bill) throws Exception {
        System.out.println(bill.toString());
        return bill;
    }
}
