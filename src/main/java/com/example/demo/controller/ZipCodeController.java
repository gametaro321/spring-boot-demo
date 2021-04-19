package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.ZipCodeDataDto;
import com.example.demo.dto.ZipCodeDto;
import com.example.demo.service.ZipCodeService;

@Controller
public class ZipCodeController {

    @Autowired
    ZipCodeService zpcService;

    /**
     * 郵便番号入力フォーム
     * @return "zipcode"
     */
    @RequestMapping("/zipcode")
    public String zipcodeForm(HttpSession session, Model model) {
        return "zipcode";
    }

    /**
     * 郵便番号情報表示
     * @return "zipcode-confirm"
     */
    @RequestMapping(value="/zipcode/confirm", method=RequestMethod.POST)
    public String zipcodeConfirm(HttpSession session, Model model,
                                 @RequestParam("zipcode") String zipcode){

        // 一応必須チェックのみ 数字・桁数チェックは省略
        // nullまたは空文字の場合、入力フォームにエラーメッセージを表示
        if (zipcode == null || zipcode.equals("")) {
            model.addAttribute("errorMessage", "郵便番号を入力してください。");
            return zipcodeForm(session, model);
        }

        // 郵便番号検索APIサービス呼び出し
        ZipCodeDto zipCodeDto = zpcService.service(zipcode);
        model.addAttribute("zipcodeMsg", zipCodeDto.getMessage());
        model.addAttribute("status", zipCodeDto.getStatus());
        // thymeleafでリストを展開して表示する
        model.addAttribute("zipcodeList", zipCodeDto.getResults());
        return zipcodeForm(session, model);
    }

    @RequestMapping(value="/zipcode/edit", method=RequestMethod.POST)
    public String zipcodeEdit(HttpSession session, Model model,@ModelAttribute ZipCodeDataDto zipCodeDataDto,
                                 @RequestParam("zipcode") String zipcode){
        //ポイント4
        ZipCodeDto zipCodeDto = zpcService.service(zipcode);
        //ポイント5
        BeanUtils.copyProperties(zipCodeDto.getResults().get(0), zipCodeDataDto);



        return "zipedit";
    }
    @RequestMapping(value="/zipcode/post", method=RequestMethod.POST)
    public String zipcodePost(HttpSession session, Model model,@ModelAttribute ZipCodeDataDto zipCodeDataDto,
                                 @RequestParam("zipcode") String zipcode){
        //ポイント4
        ZipCodeDto zipCodeDto = zpcService.service(zipcode);
        zpcService.service(zipCodeDto);
        return zipcodeConfirm(session, model,zipcode);
    }
    @RequestMapping(value="/zipcode/post2", method=RequestMethod.POST)
    public String zipcodePost2(HttpSession session, Model model,@ModelAttribute ZipCodeDataDto zipCodeDataDto){
        //ポイント4
    	ZipCodeDto zipCodeDto =  new ZipCodeDto();
    	List<ZipCodeDataDto> list = new ArrayList<ZipCodeDataDto>();
    	list.add(zipCodeDataDto);
    	zipCodeDto.setResults(list);
        zpcService.service(zipCodeDto);
        return zipcodeConfirm(session, model,zipCodeDataDto.getZipcode());
    }

}
