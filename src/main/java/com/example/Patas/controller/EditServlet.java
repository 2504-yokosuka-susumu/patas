package com.example.Patas.controller;

import com.example.Patas.controller.form.TaskForm;
import com.example.Patas.service.EditService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EditServlet {
    @Autowired
    EditService editService;
    @Autowired
    public SmartValidator validator;

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
                                 @ModelAttribute("formModel") TaskForm taskForm, BindingResult result){
        TaskForm replaceTaskForm = taskForm;
        String replaceContent = replaceTaskForm.getContent();
        replaceContent = replaceContent.replaceFirst("^[\\s　]+", "").replaceFirst("[\\s　]+$", "");
        replaceContent = replaceContent.replaceAll("\\r\\n|\\r|\\n", "");
        replaceTaskForm.setContent(replaceContent);

        validator.validate(replaceTaskForm, result);
        if(result.hasErrors()){
            ModelAndView mav = new ModelAndView();

            mav.addObject("formModel", taskForm);
            mav.setViewName("/edit");
            return mav;
        }else {
            taskForm.setId(id);
            editService.saveTask(taskForm);

            return new ModelAndView("redirect:/");
        }
    }
}