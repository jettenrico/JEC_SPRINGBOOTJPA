package com.bcafinance.jecspringbootjpa.utils;

import com.bcafinance.jecspringbootjpa.models.Creditors;
import com.bcafinance.jecspringbootjpa.models.Wallets;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.tomcat.jni.Local;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public static boolean isCsv(MultipartFile multipartFile)
    {
        if(!ConstantMessage.CONTENT_TYPE_CSV.equals(multipartFile.getContentType()))
        {
            return false;
        }
        return true;
    }

    public static List<Wallets> csvToWalletData(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        CSVParser csvParser = new CSVParser(bufferedReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().
                        withIgnoreHeaderCase().
                        withTrim()
        );
        List<Wallets> lsWallets = new ArrayList<Wallets>();
        try {
            Iterable<CSVRecord> iterRecords = csvParser.getRecords();

            for (CSVRecord record : iterRecords) {
                Wallets wallets = new Wallets();
                wallets.setFirstName(record.get("firstName"));
                wallets.setLastName(record.get("lastName"));
                wallets.setBtcAddress(record.get("btcAddress"));
                wallets.setEthAddress(record.get("ethAddress"));
                lsWallets.add(wallets);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {

            if (!csvParser.isClosed()) {
                csvParser.close();
            }
            return lsWallets;
        }
    }

    public static List<Creditors> csvToCreditorData(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        CSVParser csvParser = new CSVParser(bufferedReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().
                        withIgnoreHeaderCase().
                        withTrim()
        );
        List<Creditors> lsCreditor = new ArrayList<Creditors>();
        try {
            Iterable<CSVRecord> iterRecords = csvParser.getRecords();

            for (CSVRecord record : iterRecords) {
                Creditors creditors = new Creditors();
                creditors.setAccountNumber(Double.parseDouble(record.get("accountNumber")));
                creditors.setApplicationDuration(Integer.parseInt(record.get("applicationDuration")));
                creditors.setInstallment(Double.parseDouble(record.get("installment")));
                creditors.setAdministration(Double.parseDouble(record.get("administration")));
                creditors.setApplicationDate(LocalDate.parse(record.get("applicationDate")));
                creditors.setEndedDate(LocalDate.parse(record.get("endedDate")));
                lsCreditor.add(creditors);
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        } finally {

            if (!csvParser.isClosed()) {
                csvParser.close();
            }
            return lsCreditor;
        }
    }
}