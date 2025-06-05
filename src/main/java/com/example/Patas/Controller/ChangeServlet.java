package com.example.Patas.controller;

import com.example.Patas.controller.form.TaskForm;
import com.example.Patas.service.ChangeService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChangeServlet {
    ChangeService changeService;

    @PostMapping("/change")
    public ModelAndView editTask(@ModelAttribute("formModel") TaskForm task) {
        changeService.saveTask(task);
        return new ModelAndView("redirect:/");
    }
}
