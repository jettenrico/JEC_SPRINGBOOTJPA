package com.bcafinance.jecspringbootjpa.controllers;
/*
Created By IntelliJ IDEA 2022.2.3 (Ultimate Edition)
@Author Jett a.k.a. Jett Enrico Chandra
CTO
Created on 12/5/2022
@Last Modified 12/5/2022 1:21 PM
Version 1.0
*/

import com.bcafinance.jecspringbootjpa.dto.CreditorsDTO;
import com.bcafinance.jecspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.jecspringbootjpa.handler.ResponseHandler;
import com.bcafinance.jecspringbootjpa.models.Creditors;
import com.bcafinance.jecspringbootjpa.services.CreditorService;
import com.bcafinance.jecspringbootjpa.utils.ConstantMessage;
import com.bcafinance.jecspringbootjpa.utils.CsvReader;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/")
public class CreditorController {
    @Getter
    private CreditorService creditorService;
    @Autowired
    private ModelMapper modelMapper;

    private List<Creditors> lsCreditor = new ArrayList<Creditors>();

    @Autowired
    public CreditorController(CreditorService creditorService) {
        this.creditorService = creditorService;
    }

    @PostMapping("/v1/creditor/upload/csv")
    public ResponseEntity<Object>
    uploadCreditor(@Valid @RequestParam("demoFile") MultipartFile multipartFile) throws Exception {
        try{
            if(CsvReader.isCsv(multipartFile))
            {
                creditorService.saveBulkCreditor(multipartFile);
                return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,
                        HttpStatus.CREATED,null,null,null);
            }
            else
            {
                throw new ResourceNotFoundException(ConstantMessage.ERROR_NOT_CSV_FILE+" -- "+multipartFile.getOriginalFilename());
            }
        }catch (Exception e)
        {
            throw new Exception(ConstantMessage.ERROR_UPLOAD_CSV+multipartFile.getOriginalFilename());
        }
    }

    @GetMapping("/v1/creditor/datas/all/dto")
    public ResponseEntity<Object> findAllCreditorDTO()throws Exception {

        List<Creditors> lsCreditor = creditorService.findAllCreditor();

        if(lsCreditor.size()!=0)
        {
            List<CreditorsDTO> lsCreditorDTO = modelMapper.map(lsCreditor, new TypeToken<List<CreditorsDTO>>() {}.getType());

            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsCreditorDTO,null,null);
        }
        throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
    }

    @GetMapping("/v1/creditor/search/dto/{size}/{page}/Installment")
    public ResponseEntity<Object> pageFindCreditorByAppDurGreaterEqDTO(@RequestParam Double Greater,
                                                           @PathVariable("size") int size,
                                                           @PathVariable("page") int page )throws Exception {

        Pageable pageable = PageRequest.of(page,size);
        Page<Creditors> m = creditorService.pagingFindCreditorByInstallmentGrtr(Greater,pageable);
        List<Creditors> ls = m.getContent();
        List<CreditorsDTO> lsDto = modelMapper.map(ls, new TypeToken<List<CreditorsDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK, creditorService.mappingDto(m, lsDto)
                        ,null,null);
    }

    @GetMapping("/v1/creditor/search/dto/{size}/{page}/ApplicationDuration")
    public ResponseEntity<Object> pageFindCreditorByAppDurBetween(@RequestParam Integer min,
                                                                       @RequestParam Integer max,
                                                                       @PathVariable("size") int size,
                                                                       @PathVariable("page") int page )throws Exception {

        Pageable pageable = PageRequest.of(page,size);
        Page<Creditors> m = creditorService.pagingFindAppDurBetween(min,max,pageable);
        List<Creditors> ls = m.getContent();
        List<CreditorsDTO> lsDto = modelMapper.map(ls, new TypeToken<List<CreditorsDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK, creditorService.mappingDto(m, lsDto)
                        ,null,null);
    }

    @GetMapping("/v1/creditor/search/dto/{size}/{page}/ApplicationDate")
    public ResponseEntity<Object> pageFindCreditorByAppDurBetween(@RequestParam String start,
                                                                  @RequestParam String end,
                                                                  @PathVariable("size") int size,
                                                                  @PathVariable("page") int page )throws Exception {

        Pageable pageable = PageRequest.of(page,size);
        Page<Creditors> m = creditorService.pagingAppDateBetween(start,end,pageable);
        List<Creditors> ls = m.getContent();
        List<CreditorsDTO> lsDto = modelMapper.map(ls, new TypeToken<List<CreditorsDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK, creditorService.mappingDto(m, lsDto)
                        ,null,null);
    }
}