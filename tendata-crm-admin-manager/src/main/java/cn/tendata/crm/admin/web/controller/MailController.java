package cn.tendata.crm.admin.web.controller;

import cn.tendata.crm.admin.web.bind.annotation.CurrentUser;
import cn.tendata.crm.admin.web.model.JsonResponse;
import cn.tendata.crm.admin.web.model.SubmitMailDto;
import cn.tendata.crm.data.EmailSource;
import cn.tendata.crm.data.MailType;
import cn.tendata.crm.data.domain.Mail;
import cn.tendata.crm.data.domain.MailAttachment;
import cn.tendata.crm.data.domain.User;
import cn.tendata.crm.service.MailAttachmentService;
import cn.tendata.crm.service.MailService;
import cn.tendata.crm.service.UserService;
import com.qiniu.http.Response;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Luo Min on 2016/12/7.
 */
@Controller
@RequestMapping("/admin/mails")
public class MailController {

    public static final int MAIL_PAGE_SIZE = 10;

    private final MailService mailService;
    private final MailAttachmentService attachmentService;
    private final UserService userService;


    @Autowired
    public MailController(MailService mailService,
                          MailAttachmentService attachmentService,
                          UserService userService) {
        this.mailService = mailService;
        this.attachmentService = attachmentService;
        this.userService = userService;
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


    @RequestMapping(value = "/upload")
    @ResponseBody
    public JsonResponse upload(@RequestParam("file") MultipartFile file,
                               JsonResponse response,
                               HttpSession session) throws IOException {
        String filename = file.getOriginalFilename()+UUID.randomUUID();
        Response uploadResponse = mailService.upload(filename, file.getBytes(), "minjay");
        if (uploadResponse.statusCode !=200){
            response.setStatus(500);
        }
        MailAttachment attachment = new MailAttachment();
        attachment.setName(filename);
        attachment.setUrl(uploadResponse.url());
        attachment.setSize(file.getSize());
        List<MailAttachment> attachments = (List<MailAttachment>) session.getAttribute("attachments");
        if (CollectionUtils.isEmpty(attachments)){
            attachments = new ArrayList<>();
        }
        attachments.add(attachment);
        session.setAttribute("attachments",attachments);
        return response;
    }

    @RequestMapping(value = "/read",method = RequestMethod.POST)
    public ResponseEntity<Void> read(@RequestParam("id") Mail mail){
        mail.setReaded(true);
        mailService.save(mail);
        return new ResponseEntity<Void>()
    }

    @RequestMapping(value = "/reply",method = RequestMethod.POST)
    public ResponseEntity<Void> mailSubmit(@Valid @RequestBody SubmitMailDto submitMailDto,
                                           @CurrentUser User user,
                                           HttpSession session){
        Mail mail = new Mail();
        mail.setTitle(submitMailDto.getTitle());
        mail.setContent(submitMailDto.getContent());
        mail.setFromUser(user);
        mail.setType(MailType.REPLYSEND);
        mail.setToUser(userService.getById(submitMailDto.getToUser()));
        mail.setSource(EmailSource.INSIDE);
        mailService.save(mail);

        Mail fromMail = mailService.findById(submitMailDto.getFromMailId());
        fromMail.setReplyMail(mail);
        mailService.save(fromMail);

        List<MailAttachment> attachments =(List<MailAttachment>) session.getAttribute("attachments");
        session.removeAttribute("attachments");
        if (!CollectionUtils.isEmpty(attachments)) {
            for (MailAttachment attachment:attachments){
                attachment.setMail(mail);
                attachment.setUser(user);
            }
            attachmentService.save(attachments);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);

    }

}
