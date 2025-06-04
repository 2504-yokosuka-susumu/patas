package com.example.Patas.controller;

import com.example.Patas.controller.form.TaskForm;
import com.example.Patas.service.EditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

public class EditServlet {
    @Autowired
    EditService editService;

    /*
     *編集画面表示
     */
    @GetMapping("/edit/{id}")
    public ModelAndView editContent(@PathVariable("id") Integer id){
        ModelAndView mav = new ModelAndView();

        TaskForm reportForm = new TaskForm();

        TaskForm editData = editService.editTask(id);

        mav.setViewName("/edit");
        // selectしてきた編集対象の投稿データを保管
        mav.addObject("formModel", editData);

        return mav;
    }
}
