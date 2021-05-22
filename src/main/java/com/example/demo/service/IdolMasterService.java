package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AVIdolDataDto;
import com.example.demo.repository.AVIdolDataRepository;

@Service
public class IdolMasterService {

//    @Autowired
//    @Qualifier("zipCodeSearchRestTemplate")
//    RestTemplate restTemplate;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	NamedParameterJdbcTemplate namedJdbcTemplate;
    @Autowired
    AVIdolDataRepository AVIdolDataRepository;


	public Page<AVIdolDataDto> findAllUsers(Pageable pageable){
//	      SqlParameterSource parameters = 
//	              new MapSqlParameterSource("LIMIT", 100)
//	                              .addValue("OFFSET", 0);
//	    
//	    String sql = "SELECT * "  
//                   + "  FROM AV_IDOL_DATA T11"
//                   + " WHERE 1=1"
//                   + "   AND DATA_PATH = ("
//                   + "         SELECT MIN(DATA_PATH)"             
//                   + "           FROM AV_IDOL_DATA T12"
//                   + "          WHERE T11.NAME = T12.NAME"
//                   + "            AND T11.TYPE = T12.TYPE"
//                   + "   ) "
//                   + "   AND ID > 0 " 
//                   + " ORDER BY NAME,TYPE" 
//                   + " LIMIT :LIMIT OFFSET :OFFSET" ;
//
//        RowMapper<AVIdolDataDto> rowMapper = new BeanPropertyRowMapper<AVIdolDataDto>(AVIdolDataDto.class);
//		return namedJdbcTemplate.query(sql, parameters,rowMapper);
		//return jdbcTemplate.query(sql, rowMapper);
		return AVIdolDataRepository.findAllUsers(pageable );
	}
	/**
	 * 
	 * @param arg1:検索ワード[]
	 * @return
	 */
	public Page<AVIdolDataDto> findUsers(String arg1,Pageable pageable){
		// JPA（Pageable）によるデータ取得サンプル
		return AVIdolDataRepository.findUsers(arg1.trim(), pageable );
	}
	/**
	 * 
	 * @param arg1:検索ワード[NAME]
	 * @return
	 */
	public List<AVIdolDataDto> findUser(int arg1){
		// namedJdbcTemplateによるデータ取得サンプル
//        SqlParameterSource parameters = 
//            new MapSqlParameterSource("NAME",  arg1  )
//                            .addValue("LIMIT", 100)
//                            .addValue("OFFSET", 0);
//        
//        String sql = "SELECT * FROM AV_IDOL_DATA T11"  
//                   + " WHERE 1=1"
//                   + "   AND NAME = :NAME  "
//                   + "   AND ID > 0 "
//                   + " ORDER BY NAME,TYPE,DATA_PATH"
//                   + " LIMIT :LIMIT OFFSET :OFFSET" ;
//         
//         
//	    RowMapper<AVIdolDataDto> rowMapper = new BeanPropertyRowMapper<AVIdolDataDto>(AVIdolDataDto.class);
//		return namedJdbcTemplate.query(sql, parameters,rowMapper);
		return AVIdolDataRepository.findUser(arg1 );
	}
	/**
	 * 
	 * @param arg1:検索ワード[NAME]
	 * @return
	 */
	public List<AVIdolDataDto> findUser(String arg1){
		// namedJdbcTemplateによるデータ取得サンプル
        SqlParameterSource parameters = 
            new MapSqlParameterSource("NAME",  arg1  )
                            .addValue("LIMIT", 100)
                            .addValue("OFFSET", 0);
        
        String sql = "SELECT * FROM AV_IDOL_DATA T11"  
                   + " WHERE 1=1"
                   + "   AND NAME = :NAME  "
                   + "   AND ID > 0 "
                   + " ORDER BY NAME,TYPE,DATA_PATH"
                   + " LIMIT :LIMIT OFFSET :OFFSET" ;
         
         
	    RowMapper<AVIdolDataDto> rowMapper = new BeanPropertyRowMapper<AVIdolDataDto>(AVIdolDataDto.class);
		return namedJdbcTemplate.query(sql, parameters,rowMapper);
	}

	/**
	 * 
	 * @param arg1:検索ワード[NAME]
	 * @return
	 */
	public List<AVIdolDataDto> getList3(String arg1){

        SqlParameterSource parameters = new MapSqlParameterSource("DATA_PATH",  arg1  );
        
        String sql = "SELECT * FROM AV_IDOL_DATA T11"  
                   + " WHERE 1=1"
                   + "   AND DATA_PATH = :DATA_PATH  "
                   + "   AND ID > 0 "
                   + " ORDER BY NAME,TYPE" ;
		RowMapper<AVIdolDataDto> rowMapper = new BeanPropertyRowMapper<AVIdolDataDto>(AVIdolDataDto.class);
		return namedJdbcTemplate.query(sql, parameters,rowMapper);
	}

}
