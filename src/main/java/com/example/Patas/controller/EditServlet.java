package com.example.Patas.controller;

import com.example.Patas.controller.form.TaskForm;
import com.example.Patas.service.EditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EditServlet {
    @Autowired
    EditService editService;

    /*
     *編集画面表示
     */
    @GetMapping("/edit/1")
    public ModelAndView editTask(){
        ModelAndView mav = new ModelAndView();

//        if(result.hasErrors()){
//            List<String> errorMessages = new ArrayList<String>();
//            //resultからデフォルトのエラーメッセージを取得
//            //入っているerrorsの分だけ回してListに詰める(今回は「不正なパラメータです」のみだが、増えた時を考えてリスト)
//            for(ObjectError error : result.getAllErrors()){
//                errorMessages.add(error.getDefaultMessage());
//            }
//            redirectAttributes.addFlashAttribute("errorMessages", errorMessages);
//
//            return new ModelAndView("redirect:/");
//        }else{
            TaskForm taskForm = new TaskForm();

            TaskForm editData = editService.editTask(1);

            //if(editData != null) {
                mav.setViewName("/edit");
                //selectしてきた編集対象の投稿データを保管
                mav.addObject("formModel", editData);
//            }else{
//                String error = "不正なパラメータです";
//                redirectAttributes.addFlashAttribute("error", error);
//                mav.setViewName("redirect:/");
//            }

            return mav;
        //}
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