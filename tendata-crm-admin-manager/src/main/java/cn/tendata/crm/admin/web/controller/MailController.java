package cn.tendata.crm.admin.web.controller;

import cn.tendata.crm.admin.web.bind.annotation.CurrentUser;
import cn.tendata.crm.admin.web.model.JsonResponse;
import cn.tendata.crm.data.domain.Mail;
import cn.tendata.crm.data.domain.User;
import cn.tendata.crm.service.MailService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Luo Min on 2016/12/7.
 */
@Controller
@RequestMapping("/admin/mails")
public class MailController {

    public static final int MAIL_PAGE_SIZE = 10;

    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Page<Mail>> getALlUnRead(
                                     @PageableDefault(sort = {"createdDate"}, direction = Sort.Direction.DESC) Pageable pageable,
                                     @CurrentUser User user) {
        Page<Mail> mails = mailService.getAllByReaded(user, false, pageable);
        return new ResponseEntity<>(mails,HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Mail> getMail(@PathVariable("id") Long id){
        Mail mail=mailService.getById(id);
        return new ResponseEntity<Mail>(mail,HttpStatus.OK);
    }
}
