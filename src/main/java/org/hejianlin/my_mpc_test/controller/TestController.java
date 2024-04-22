package org.hejianlin.my_mpc_test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/print")
    public String print() {
        log.info("启动成功啦！");
        return "启动成功";
    }
}
