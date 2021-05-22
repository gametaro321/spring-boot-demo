package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.AVIdolDataDto;

@Repository
public interface AVIdolDataRepository extends JpaRepository<AVIdolDataDto, Integer> {
	@Query( value = "SELECT * "  
                  + "  FROM AV_IDOL_DATA T11"
                  + " WHERE 1=1"
                  + "   AND DATA_PATH = ("
                  + "         SELECT MIN(DATA_PATH)"             
                  + "           FROM AV_IDOL_DATA T12"
                  + "          WHERE T11.NAME = T12.NAME"
                  + "            AND T11.TYPE = T12.TYPE"
                  + "   ) "
                  + "   AND ID > 0 " 
                  + " ORDER BY NAME"
                  + "        , TYPE" 
	   , nativeQuery = true
    ) // SQL
	Page<AVIdolDataDto> findAllUsers(Pageable pageable); 

	@Query(value = "SELECT * "
	             + "  FROM AV_IDOL_DATA T11"
	             + " WHERE 1=1"
	             + "   AND NAME LIKE %:name%"
                 + "   AND DATA_PATH = ("
                 + "         SELECT MIN(DATA_PATH)"             
                 + "           FROM AV_IDOL_DATA T12"
                 + "          WHERE T11.NAME = T12.NAME"
                 + "            AND T11.TYPE = T12.TYPE"
                 + "   ) "
                 + "   AND ID > 0 " 
                 + " ORDER BY NAME" 
                 + "        , TYPE" 
	  	   , nativeQuery = true
	) // SQL
	Page<AVIdolDataDto> findUsers(String name,Pageable pageable); 

	@Query(value = "SELECT * "
                 + "  FROM AV_IDOL_DATA T11"
                 + " WHERE 1=1"
                 + "   AND (NAME,TYPE) IN ("
                 + "         SELECT NAME,TYPE "             
                 + "           FROM AV_IDOL_DATA T12"
                 + "          WHERE T12.ID = :ID"
                 + "   ) "
                 + "   AND ID > 0 " 
                 + " ORDER BY NAME" 
                 + "        , TYPE" 
 	   , nativeQuery = true
    ) // SQL
    List<AVIdolDataDto> findUser(int ID); 

}

