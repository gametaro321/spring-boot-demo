package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.IdolMasterDataDto;

/**
 * ユーザー情報 Controller
 */
@Controller
public class IdolMasterController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	NamedParameterJdbcTemplate namedJdbcTemplate;
    @RequestMapping("/IdolMaster")
    public String IdolMasterForm(HttpSession session, Model model) {
        return "IdolMaster";
    } 
    @RequestMapping(value="/IdolMaster/confirm", method=RequestMethod.POST)
    public String IdolMasterConfirm(HttpSession session, Model model,
                                 @RequestParam("idolNo") String idolNo){
    	try {
        	if (idolNo == null || idolNo.equals("")) {
                String sql = "SELECT * FROM IDOL_MASTER_DATA WHERE ID > 0" ;
                RowMapper<IdolMasterDataDto> rowMapper = new BeanPropertyRowMapper<IdolMasterDataDto>(IdolMasterDataDto.class);
                List<IdolMasterDataDto> idolList = jdbcTemplate.query(sql, rowMapper);
     			model.addAttribute("IdolDataList", idolList);

            }else {
                // Params

                SqlParameterSource parameters = new MapSqlParameterSource("NAME", "%"+ idolNo + "%" )
                        .addValue("FURIGANA", "%" + idolNo+  "%");

                String sql = "SELECT * FROM IDOL_MASTER_DATA WHERE (NAME LIKE :NAME OR FURIGANA LIKE :FURIGANA) AND ID > 0" ;
                RowMapper<IdolMasterDataDto> rowMapper = new BeanPropertyRowMapper<IdolMasterDataDto>(IdolMasterDataDto.class);
                List<IdolMasterDataDto> idolList = namedJdbcTemplate.query(sql, parameters,rowMapper);
               	model.addAttribute("IdolDataList", idolList);
            }
    	} catch (Exception EmptyResultDataAccessException) {
            model.addAttribute("errorMessage", "該当データがありません");
            return IdolMasterForm(session, model);
    	}
        // thymeleafでリストを展開して表示する
        return IdolMasterForm(session, model);     
    }
}