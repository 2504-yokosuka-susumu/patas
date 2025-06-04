package com.example.Patas.controller;

import com.example.Patas.controller.form.TaskForm;
import com.example.Patas.service.EditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

public class EditServlet {
    @Autowired
    EditService editService;

    /*
     *編集画面表示
     */
    @GetMapping("/edit/{id}")
    public ModelAndView editTask(@PathVariable("id") Integer id){
        ModelAndView mav = new ModelAndView();

        TaskForm taskForm = new TaskForm();

        TaskForm editData = editService.editTask(id);

        mav.setViewName("/edit");
        // selectしてきた編集対象の投稿データを保管
        mav.addObject("formModel", editData);

        return mav;
    }

    @PutMapping("/update/{id}")
    public ModelAndView editTask(@PathVariable("id") Integer id,
                                 @ModelAttribute("formModel") @Validated TaskForm task, BindingResult result){

        if(result.hasErrors()){
            ModelAndView mav = new ModelAndView();
            mav.addObject("formModel", task);
            mav.setViewName("/edit");
            return mav;
        }else {
            task.setId(id);
            editService.saveTask(task);

            return new ModelAndView("redirect:/");
        }
    }
}