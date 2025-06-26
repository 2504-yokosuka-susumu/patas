package com.example.Patas.controller;

import com.example.Patas.service.DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeleteServlet {

    @Autowired
    DeleteService deleteService;

    @PostMapping ("/delete/{id}")
    public ModelAndView deleteTaskOne(@PathVariable("id") Integer id) {
        // 特定のタスクの削除
        deleteService.delete(id);
        return new ModelAndView("redirect:/");
    }
}
