package cn.tendata.crm.admin.web.controller;

import cn.tendata.crm.data.domain.Department;
import cn.tendata.crm.service.DepartmentService;
import cn.tendata.crm.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by minjay on 2017/2/24.
 */
@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Department>> list() {
        List<Department> departments = CollectionUtils.toList(departmentService.getAll());
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
}
