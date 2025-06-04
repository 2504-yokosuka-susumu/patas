package com.example.Patas.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes(value= "errorMessages")
public class TopServlet {
    @Autowired
    HttpSession session;
    /*
     * 投稿内容表示処理
     */
    @GetMapping
    public ModelAndView top() {
        ModelAndView mav = new ModelAndView();
//        // 返信form用の空のentityを準備
//        CommentForm commentsForm = new CommentForm();
//        // 投稿を全件取得 日付検索に変えた
//        List<ReportForm> reportData = reportService.findByCreated_dateReport(start, end);
//        // 返信を全件取得
//        List<CommentForm> commentData = commentService.findAllComment();
//        //エラーメッセージを取得
//        mav.addObject("mavErrorMessages", session.getAttribute("errorMessages"));
//        mav.addObject("reportId", session.getAttribute("reportId"));
//        session.invalidate();
        // 画面遷移先を指定
        mav.setViewName("/top");
        // 投稿データオブジェクトを保管
//        mav.addObject("formModel", commentsForm);
//        mav.addObject("reports", reportData);
//        mav.addObject("comments", commentData);
//        mav.addObject("start", start);
//        mav.addObject("end", end);

        return mav;
    }

}