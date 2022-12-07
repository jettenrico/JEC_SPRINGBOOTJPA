package com.bcafinance.jecspringbootjpa.services;
/*
Created By IntelliJ IDEA 2022.2.3 (Ultimate Edition)
@Author Jett a.k.a. Jett Enrico Chandra
CTO
Created on 12/5/2022
@Last Modified 12/5/2022 1:21 PM
Version 1.0
*/
import com.bcafinance.jecspringbootjpa.dto.CreditorsDTO;
import com.bcafinance.jecspringbootjpa.models.Creditors;
import com.bcafinance.jecspringbootjpa.repos.CreditorRepo;
import com.bcafinance.jecspringbootjpa.utils.CsvReader;
import lombok.Getter;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CreditorService {
    @Getter
    private CreditorRepo creditorRepo;

    @Autowired
    public CreditorService(CreditorRepo creditorRepo) {
        this.creditorRepo = creditorRepo;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Creditors> saveBulkCreditor(MultipartFile multipartFile) throws Exception
    {
        try{
            List<Creditors> lsCreditor = CsvReader.csvToCreditorData(multipartFile.getInputStream());
            return creditorRepo.saveAll(lsCreditor);
        }catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    public List<Creditors> findAllCreditor()
    {
        return (List<Creditors>)creditorRepo.findAll();
    }

    public Page<Creditors> pagingFindCreditorByInstallmentGrtr(Double greater, Pageable pageable)
    {
        return creditorRepo.findByInstallmentGreaterThanEqual(greater,pageable);
    }

    public Page<Creditors> pagingFindAppDurBetween(Integer min, Integer max, Pageable pageable){
        return creditorRepo.findByApplicationDurationBetween(min, max, pageable);
    }

    public Page<Creditors> pagingAppDateBetween(String start, String end, Pageable pageable){
        LocalDate dateStart = LocalDate.parse(start);
        LocalDate dateEnd = LocalDate.parse(end);
        return creditorRepo.findByApplicationDateBetween(dateStart, dateEnd, pageable);
    }

    public Map<String, Object> mappingDto(Page<Creditors> m, List<CreditorsDTO> lsDto){
        Map<String,Object> mapz = new HashMap<>();
        mapz.put("content", lsDto);
        mapz.put("currentPage", m.getNumber());
        mapz.put("totalItems", m.getTotalElements());
        mapz.put("totalPages", m.getTotalPages());
        mapz.put("numberOfElements", m.getNumberOfElements());
        mapz.put("pageable", m.getPageable());
        return mapz;
    }
}
