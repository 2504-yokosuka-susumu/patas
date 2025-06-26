package com.example.Patas.controller;

import com.example.Patas.controller.form.TaskForm;
import com.example.Patas.service.TaskService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
                            @RequestParam(value = "end", required = false)String end,
                            Model model) throws ParseException {
        ModelAndView mav = new ModelAndView();

        // 投稿を全件取得
        List<TaskForm> taskData = taskService.findAllTask();
        //エラーメッセージを取得
        mav.addObject("mavErrorMessages", session.getAttribute("errorMessages"));
        //タスクステータスリスト作成
        HashMap<Integer,String> choicesMap= new HashMap<Integer,String>();
        choicesMap.put(0,null);
        choicesMap.put(1,"未着手");
        choicesMap.put(2,"実行中");
        choicesMap.put(3,"ステイ中");
        choicesMap.put(4,"完了");
        choicesMap.put(5,"すべて");

        //タスクステータスリスト作成
        HashMap<Integer,String> statusChoices= new HashMap<Integer,String>();
        statusChoices.put(1,"未着手");
        statusChoices.put(2,"実行中");
        statusChoices.put(3,"ステイ中");
        statusChoices.put(4,"完了");

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
//        taskData = taskService.findSerchTask(start, end, tasksForm);
        taskData = taskService.findAllTask();

        // 画面遷移先を指定
        mav.setViewName("/top");
        // 投稿データオブジェクトを保管
        mav.addObject("formTask", tasksForm);
        mav.addObject("tasks", taskData);
        mav.addObject("today", today);
        mav.addObject("choices", choicesMap);
        mav.addObject("statusChoices", statusChoices);
        mav.addObject("start", start);
        mav.addObject("end", end);

        String errorMessage = (String)model.getAttribute("error");
        if(errorMessage != null){
            mav.addObject("errorMessage", errorMessage);
        }

        return mav;
    }

}