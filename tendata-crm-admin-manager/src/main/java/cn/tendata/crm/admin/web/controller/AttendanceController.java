package cn.tendata.crm.admin.web.controller;

import cn.tendata.crm.admin.web.bind.annotation.CurrentUser;
import cn.tendata.crm.data.domain.*;
import cn.tendata.crm.message.MessageProcess;
import cn.tendata.crm.model.RegistrationRecordDto;
import cn.tendata.crm.service.GoOutRecordService;
import cn.tendata.crm.service.RegistrationRecordService;
import cn.tendata.crm.service.RegistrationRegulationService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Luo Min on 2017/1/13.
 */
@Controller
@RequestMapping("/admin/attendance")
public class AttendanceController {

    private final RegistrationRegulationService registrationRegulationService;
    private final RegistrationRecordService registrationRecordService;
    private final GoOutRecordService goOutRecordService;
    private final MessageProcess messageProcess;

    @Autowired
    public AttendanceController(RegistrationRegulationService registrationRegulationService,
                                RegistrationRecordService registrationRecordService,
                                GoOutRecordService goOutRecordService,
                                MessageProcess messageProcess) {
        this.registrationRegulationService = registrationRegulationService;
        this.registrationRecordService = registrationRecordService;
        this.goOutRecordService = goOutRecordService;
        this.messageProcess = messageProcess;
    }

    @RequestMapping(value = "/regulations", method = RequestMethod.GET)
    public ResponseEntity<List<RegistrationRecordDto>> getAllRegulation(
            @SortDefault(sort = {"regulationDate"}, direction = Sort.Direction.ASC) Sort sort, @CurrentUser User user) {
        List<RegistrationRegulation> regulations = registrationRegulationService.getAll(sort);
        List<RegistrationRecordDto> dtos = registrationRecordService.getTodayRegistration(regulations, user);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Void> register(@RequestParam("id") RegistrationRegulation regulation, @CurrentUser User user) {
        RegistrationRecord record = new RegistrationRecord();
        record.setRegulation(regulation);
        record.setRegistrationDate(DateTime.now());
        record.setUser(user);
        registrationRecordService.save(record);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/working_records", method = RequestMethod.GET)
    public ResponseEntity<Page<RegistrationRecord>> records(@PageableDefault(sort = {"registrationDate"}, direction = Sort.Direction.ASC) Pageable pageable,
                                                            @CurrentUser User user,
                                                            @RequestParam(name = "startDate", required = false) DateTime startDate,
                                                            @RequestParam(name = "endDate", required = false) DateTime endDate,
                                                            @RequestParam(name = "registerType", required = false) String registerType) {
        Page<RegistrationRecord> records = registrationRecordService.search(user, startDate, endDate, registerType, pageable);
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public ResponseEntity<Void> apply(@RequestBody MatterRecord record, @CurrentUser User user) {
        record.setUser(user);
        GoOutRecord outRecord = new GoOutRecord();
        outRecord.setMatterRecord(record);
        outRecord = goOutRecordService.save(outRecord);
        messageProcess.apply(outRecord);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/goOutRecords", method = RequestMethod.GET)
    public ResponseEntity<Page<GoOutRecord>> goOutRecords(@PageableDefault(sort = {"createdDate"}, direction = Sort.Direction.DESC) Pageable pageable,
                                                          @CurrentUser User user) {
        Page<GoOutRecord> records = goOutRecordService.getAll(pageable,user);
        return new ResponseEntity<>(records, HttpStatus.OK);
    }


}
