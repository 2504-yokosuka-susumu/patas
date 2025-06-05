package com.example.Patas.controller;

import com.example.Patas.controller.form.TaskForm;
import com.example.Patas.service.EditService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EditServlet {
    @Autowired
    EditService editService;

    /*
     *編集画面表示
     */
    @GetMapping("/edit/{id}")
    public ModelAndView editTask(@PathVariable("id") String taskId,
                                 RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();

        if (!StringUtils.isBlank(taskId) && taskId.matches("^[0-9]*$")){
            int id = Integer.parseInt(taskId);
            TaskForm editData = editService.editTask(id);
            if (editData != null) {
                mav.setViewName("/edit");
                //selectしてきた編集対象の投稿データを保管
                mav.addObject("formModel", editData);
            } else {
                String error = "不正なパラメータです";
                redirectAttributes.addFlashAttribute("error", error);
                mav.setViewName("redirect:/");
            }
        } else {
            String error = "不正なパラメータです";
            redirectAttributes.addFlashAttribute("error", error);
            mav.setViewName("redirect:/");
        }
        return mav;
    }


    @PostMapping("/update/{id}")
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