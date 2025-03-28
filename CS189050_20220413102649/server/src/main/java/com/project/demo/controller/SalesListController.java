package com.project.demo.controller;

import com.project.demo.entity.SalesList;
import com.project.demo.service.SalesListService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *销售榜单：(SalesList)表控制层
 *
 */
@RestController
@RequestMapping("/sales_list")
public class SalesListController extends BaseController<SalesList,SalesListService> {

    /**
     *销售榜单对象
     */
    @Autowired
    public SalesListController(SalesListService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
