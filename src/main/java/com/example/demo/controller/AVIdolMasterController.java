package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.AVIdolDataDto;
import com.example.demo.repository.AVIdolDataRepository;
import com.example.demo.service.IdolMasterService;

import lombok.extern.slf4j.Slf4j;

/**
 * ユーザー情報 Controller
 */
@Slf4j
@Controller
public class AVIdolMasterController {
    @Autowired
    AVIdolDataRepository AVIdolDataRepository;
	
	@Autowired
	IdolMasterService idolMasterService;
    /**
     * 初期画面表示
     */
    @RequestMapping("/AVIdolMaster")
    public String initForm(HttpSession session, Model model) {


        return "AVIdolMaster";
    } 
    /**
     * 一覧画面表示
     */
	@RequestMapping(value="/AVIdolMaster/list", method=RequestMethod.POST)
    public String listForm(HttpSession session, Model model, 
		@PageableDefault(page = 0, size = 100, direction = Direction.DESC, sort = { "NAME","ID"  }) Pageable pageable,
        @RequestParam(value = "idolNo" , required = false) String idolNo){

    	try {
 
	        Page<AVIdolDataDto> AVIdolData;
	        ///List<AVIdolDataDto> AVIdolDataDto ;
        	if (idolNo == null || idolNo.equals("")) {
        		AVIdolData = idolMasterService.findAllUsers(pageable);
            	model.addAttribute("empname", ""); 
            }else {
            	AVIdolData = idolMasterService.findUsers(idolNo,pageable);
            	model.addAttribute("empname", idolNo); 
            }
        	model.addAttribute("page", AVIdolData); 
            model.addAttribute("errorMessage", AVIdolData.getContent().size()+ "件");
            model.addAttribute("AVIdolDataList", AVIdolData.getContent());
    	} catch (Exception EmptyResultDataAccessException) {
            model.addAttribute("errorMessage", "該当データがありません");
            return initForm(session, model);
    	}
        // thymeleafでリストを展開して表示する
        return initForm(session, model);     
    }
    /**
     * 詳細画面表示
     */
    @RequestMapping(value="/AVIdolMaster/detail", method=RequestMethod.POST)
    public String DetailForm(HttpSession session, Model model,@ModelAttribute AVIdolDataDto AVIdolDataDto,
                                 @RequestParam("ID") int ID){
        List<AVIdolDataDto> idolList = idolMasterService.findUser(ID);
        model.addAttribute("errorMessage", idolList.size()+ "件");
       	model.addAttribute("AVIdolDataList", idolList);
       	return "AVIdolMasterDetail";     
    }    

    /**
     * 登録画面表示
     */
    @RequestMapping(value="/AVIdolMaster/edit", method=RequestMethod.POST)
    public String EditForm(HttpSession session, Model model,@ModelAttribute AVIdolDataDto AVIdolDataDto,
                                 @RequestParam("DATA_PATH") String DATA_PATH){
    	List<AVIdolDataDto> idolList = idolMasterService.getList3(DATA_PATH);
       	model.addAttribute("IdolMasterDataDto", idolList.get(0));
       	return "AVIdolMasterEdit";     
    }    
    /**
     * 登録!!
     */
    @RequestMapping(value="/AVIdolMaster/regit", method=RequestMethod.POST)
    public String EditRegit(HttpSession session, Model model,@ModelAttribute AVIdolDataDto AVIdolDataDto,
                                 @RequestParam("DATA_PATH") String DATA_PATH){
    	AVIdolDataRepository.save(AVIdolDataDto);
       	return DetailForm(session, model,AVIdolDataDto,AVIdolDataDto.getID().intValue());     
    }    
    /**
     * 削除!!
     */
    @RequestMapping(value="/AVIdolMaster/delete", method=RequestMethod.POST)
    public String EditDelete(HttpSession session
    		               , Model model
    		               , @ModelAttribute AVIdolDataDto AVIdolDataDto){
    	AVIdolDataRepository.delete(AVIdolDataDto);
    	List<AVIdolDataDto> idolList = idolMasterService.findUser(AVIdolDataDto.getNAME());
       	return DetailForm(session, model,idolList.get(0),idolList.get(0).getID().intValue());     
    }    

}