
package com.longbro.note.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.longbro.note.service.CommentDiaryService;
/**
 * 日记评论表控制器
 * @author longbro
 * @date 2019-10-26 07:50:14
 * @copyright 多啦学娱网络科技有限公司
 */
@Controller
public class CommentDiaryController{
    @Autowired
    CommentDiaryService commentDiaryService;
    
    
}
