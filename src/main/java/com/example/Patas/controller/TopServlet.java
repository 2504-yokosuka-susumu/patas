package com.example.Patas.controller;

import com.example.Patas.controller.form.TaskForm;
import com.example.Patas.service.TaskService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.text.ParseException;
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
    public ModelAndView top(@ModelAttribute("formTask")TaskForm tasksForm,
                            @RequestParam(value="start", required = false)String start,
                            @RequestParam(value = "end", required = false)String end) throws ParseException {
        ModelAndView mav = new ModelAndView();

//        if(tasksForm == null){
//            // 返信form用の空のentityを準備
//            TaskForm tasksForm = new TaskForm();
//        }

        // 投稿を全件取得
        List<TaskForm> taskData = taskService.findAllTask();
        //エラーメッセージを取得
        mav.addObject("mavErrorMessages", session.getAttribute("errorMessages"));
        //タスクステータスリスト作成
        HashMap<Integer,String> choicesMap= new HashMap<Integer,String>();
        choicesMap.put(1,"未着手");
        choicesMap.put(2,"実行中");
        choicesMap.put(3,"ステイ中");
        choicesMap.put(4,"完了");

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        String today = new SimpleDateFormat("yyyy-MM-dd").format(currentTimestamp);
        session.invalidate();

        // 絞り込み処理

        if (StringUtils.isNotBlank(start)) {
            start = start + " 00:00:00";
        } else {
            start = "2020-01-01 00:00:00";
        }
        if (StringUtils.isNotBlank(end)) {
            end = end + " 23:59:59";
        } else {
            end = "2100-12-31 23:59:59";
        }

        // 投稿を取得
        taskData = taskService.findSerchTask(start, end, tasksForm);

        // 画面遷移先を指定
        mav.setViewName("/top");
        // 投稿データオブジェクトを保管
        mav.addObject("formTask", tasksForm);
        mav.addObject("tasks", taskData);
        mav.addObject("today", today);
        mav.addObject("choices", choicesMap);
        mav.addObject("start", start);
        mav.addObject("end", end);


        return mav;
    }

}