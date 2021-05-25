package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
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
    public String initForm(Model model) {


        return "AVIdolMaster";
    } 
    /**
     * 一覧画面表示
     */
	@RequestMapping(value="/AVIdolMaster/list", method=RequestMethod.POST)
    public String listForm(Model model
    	  	                   , @PageableDefault(page = 0, size = 100, direction = Direction.DESC, sort = { "NAME","ID"  }) Pageable pageable
                               , @RequestParam(value = "idolNo" , required = false) String idolNo){

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
            return initForm( model);
    	}
        // thymeleafでリストを展開して表示する
        return initForm(model);     
    }
    /**
     * 詳細画面表示
     */
    @RequestMapping(value="/AVIdolMaster/detail", method=RequestMethod.POST)
    public String DetailForm(Model model
    		               , @ModelAttribute AVIdolDataDto AVIdolDataDto
                           , @RequestParam("ID") int ID){
        List<AVIdolDataDto> idolList = idolMasterService.findUser(ID);
        model.addAttribute("errorMessage", idolList.size()+ "件");
       	model.addAttribute("AVIdolDataList", idolList);
       	return "AVIdolMasterDetail";     
    }    

    /**
     * 登録画面表示
     */
    @RequestMapping(value="/AVIdolMaster/edit", method=RequestMethod.POST)
    public String EditForm(@ModelAttribute AVIdolDataDto AVIdolDataDto
   		                 , Model model){
   	    List<AVIdolDataDto> idolList = idolMasterService.getList3(AVIdolDataDto.getDATA_PATH());
       	model.addAttribute("AVIdolDataDto", idolList.get(0));
       	return "AVIdolMasterEdit";     
    }    
    /**
     * 登録!!
     */
    @RequestMapping(value="/AVIdolMaster/regit", method=RequestMethod.POST)
    public String EditRegit(@ModelAttribute @Validated AVIdolDataDto AVIdolDataDto
    						, BindingResult bindingResult
    						, Model model
    						){
    	if (bindingResult.hasErrors()) {
    		List<String> errorList = new ArrayList<String>();
    		for(ObjectError error : bindingResult.getAllErrors()) {
    			errorList.add(error.getDefaultMessage());
    		}

    		model.addAttribute("validationError", errorList);
    		model.addAttribute("AVIdolDataDto", AVIdolDataDto);
    		return "AVIdolMasterEdit";    
    	}
    	AVIdolDataRepository.save(AVIdolDataDto);
       	return DetailForm( model,AVIdolDataDto,AVIdolDataDto.getID().intValue());     
    }    
    /**
     * 削除!!
     */
    @RequestMapping(value="/AVIdolMaster/delete", method=RequestMethod.POST)
    public String EditDelete( Model model
    		               , @ModelAttribute AVIdolDataDto AVIdolDataDto){
    	AVIdolDataRepository.delete(AVIdolDataDto);
    	List<AVIdolDataDto> idolList = idolMasterService.findUser(AVIdolDataDto.getNAME());
       	return DetailForm(model,idolList.get(0),idolList.get(0).getID().intValue());     
    }    

}