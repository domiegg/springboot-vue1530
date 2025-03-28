package com.project.demo.controller;

import com.project.demo.entity.MessageBox;
import com.project.demo.service.MessageBoxService;
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
 *留言箱：(MessageBox)表控制层
 *
 */
@RestController
@RequestMapping("/message_box")
public class MessageBoxController extends BaseController<MessageBox,MessageBoxService> {

    /**
     *留言箱对象
     */
    @Autowired
    public MessageBoxController(MessageBoxService service) {
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
