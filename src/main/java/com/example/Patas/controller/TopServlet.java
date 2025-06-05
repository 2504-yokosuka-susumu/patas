package com.example.Patas.controller;

import com.example.Patas.controller.form.TaskForm;
import com.example.Patas.service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@SessionAttributes(value= "errorMessages")
public class TopServlet {
    @Autowired
    HttpSession session;
    @Autowired
    TaskService taskService;

    /*
     * 投稿内容表示処理
     */
    @GetMapping
    public ModelAndView top() {
        ModelAndView mav = new ModelAndView();
        // 返信form用の空のentityを準備
        TaskForm tasksForm = new TaskForm();
        // 投稿を全件取得
        List<TaskForm> taskData = taskService.findAllTask();
        //エラーメッセージを取得
        mav.addObject("mavErrorMessages", session.getAttribute("errorMessages"));
        //タスクステータスリスト作成


        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        String today = new SimpleDateFormat("yyyy-MM-dd").format(currentTimestamp);
        session.invalidate();
        // 画面遷移先を指定
        mav.setViewName("/top");
        // 投稿データオブジェクトを保管
        mav.addObject("formTask", tasksForm);
        mav.addObject("tasks", taskData);
        mav.addObject("today", today);

        return mav;
    }

}