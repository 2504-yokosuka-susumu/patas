package com.example.Patas.controller;

import com.example.Patas.controller.form.TaskForm;
import com.example.Patas.service.AddService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Controller
public class AddServlet {
    @Autowired
    AddService addService;

    /*
     * タスク追加画面表示
     */
    @GetMapping("/new")
    public ModelAndView newContent() {
        ModelAndView mav = new ModelAndView();
        // form用の空のentityを準備
        TaskForm taskForm = new TaskForm();
        // 画面遷移先を指定
        mav.setViewName("/new");
        // 準備した空のFormを保管
        mav.addObject("formModel", taskForm);
        return mav;
    }

    /*
     * タスク追加処理
     */
    @PostMapping("/add")
    public ModelAndView addContent(@ModelAttribute("formModel") @Valid TaskForm taskForm,
                                   BindingResult result, ModelAndView mav) {
        if(result.hasErrors()) {
            // 画面遷移先を指定
            mav.setViewName("/new");
            return mav;
        } else {
            // 投稿をテーブルに格納
            addService.saveTask(taskForm);
            // rootへリダイレクト
            return new ModelAndView("redirect:/");
        }
    }
}
