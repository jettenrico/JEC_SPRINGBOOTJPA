package com.bcafinance.jecspringbootjpa.utils;

public class ConstantMessage {

    /*
    Memperbolehkan nilai numerik dari 0 hingga 9.
    Memperbolehkan Huruf besar dan huruf kecil dari a sampai z.
    Yang diperbolehkan hanya garis bawah “_”, tanda hubung “-“ dan titik “.”
    Titik tidak diperbolehkan di awal dan akhir local part (sebelum tanda @).
    Titik berurutan tidak diperbolehkan.
    Local part, maksimal 64 karakter.
     */
//    public final static String REGEX_EMAIL_STRICT = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";

    /*REGEX*/
    public final static String REGEX_PHONE = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$";
    /*
    * Tidak memperbolehkan tanda | (pipa) dan ' (petik 1)
    */
    public final static String REGEX_EMAIL_STANDARD_RFC5322  = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    public final static String REGEX_DATE_YYYYMMDD  = "^([0-9][0-9])?[0-9][0-9]-(0[0-9]||1[0-2])-([0-2][0-9]||3[0-1])$";
    public final static String REGEX_DATE_DDMMYYYY  = "^([0-2][0-9]||3[0-1])-(0[0-9]||1[0-2])-([0-9][0-9])?[0-9][0-9]$";

    /*Global*/
    public final static String SUCCESS_SAVE = "DATA BERHASIL DIBUAT";
    public final static String SUCCESS_SAVE_BULK = "BULK INSERT BERHASIL";
    public final static String SUCCESS_FIND_BY = "OK";
    public final static String WARNING_NOT_FOUND = "DATA TIDAK DITEMUKAN";
    public final static String WARNING_DATA_EMPTY = "DATA TIDAK ADA";
    public final static String SUCCESS_SEND_EMAIL = "SILAHKAN CEK EMAIL YANG TELAH ANDA DAFTARKAN";
    public final static String ERROR_DATA_INVALID = "DATA TIDAK VALID";
    public final static String ERROR_INTERNAL_SERVER = "INTERNAL SERVER ERROR";
    public final static String ERROR_MAIL_FORM_JSON = "Malformed JSON request";
    public final static String ERROR_EMAIL_FORMAT_INVALID = "FORMAT EMAIL TIDAK SESUAI (Nomor/Karakter@Nomor/Karakter)";
    public final static String ERROR_PHONE_NUMBER_FORMAT_INVALID = "FORMAT NOMOR HANDPHONE TIDAK SESUAI (+628XX-)";
    public final static String ERROR_DATE_FORMAT_YYYYMMDD = "FORMAT TANGGAL TIDAK SESUAI (YYYY-mm-dd)";

    public final static String ERROR_UNEXPECTED = "UNEXPECTED ERROR";
    public final static String ERROR_UNPROCCESSABLE = "Validation error. Check 'errors' field for details.";

    public final static String ERROR_NO_CONTENT = "PERMINTAAN TIDAK DAPAT DIPROSES";
    public final static String WELCOME_MESSAGE = "This is Springboot BootCamp BCAF BATCH 1";
    public final static String TAKE_TOUR = "Ready To Start";

    /*Customer*/
    public final static String SUCCESS = "";
    public final static String ERROR = "";
    public final static String WARNING_EMAIL_EXIST = "EMAIL SUDAH TERDAFTAR";
    public final static String WARNING_CUSTOMER_NOT_FOUND = "CUSTOMER BELUM TERDAFTAR";

    /*Products*/
    public final static String WARNING_PRODUCT_NOT_FOUND = "PRODUK TIDAK DITEMUKAN";
    public final static String WARNING_PRODUCT_PRICE_SOP = "HARGA TIDAK BOLEH 1/2 ATAU 3 KALI DARI HARGA SEBELUMNYA";

    /*Suppliers*/
    public final static String WARNING_SUPPLIER_EMAIL_NOT_FOUND = "EMAIL SUPPLIER TIDAK DITEMUKAN";
    public final static String WARNING_NOT_FOUND_SUPPLIER = "NAMA PERUSAHAAN TIDAK DITEMUKAN";
    public final static String WARNING_NOT_FOUND_SUPERVISOR = "NAMA SUPERVISOR TIDAK DITEMUKAN";
    public final static String WARNING_BLANK_COMPANY = "NAMA PERUSAHAAN TIDAK BOLEH KOSONG!";

    /*Warehouses*/
    public final static String WARNING_BLANK_ADDRESS = "ALAMAT WAREHOUSE TIDAK BOLEH KOSONG";
    public final static String WARNING_BLANK_SUPERVISOR = "SUPERVISOR WAREHOUSE TIDAK BOLEH KOSONG";
    public final static String WARNING_WAREHOUSE_NOT_FOUND = "WAREHOUSE BELUM TERDAFTAR";
    public final static String WARNING_WAREHOUSE_ADDRESS_MAX_LENGTH = "ALAMAT WAREHOUUSE TERLALU PANJANG";
    public final static String WARNING_WAREHOUSE_SUPERVISOR_MAX_LENGTH = "NAMA SUPERVISOR WAREHOUSE TERLALU PANJANG";

    /*Stores*/
    public final static String WARNING_STORES_MAX_LENGTH = "NAMA TOKO TERLALU PANJANG";
    public final static String WARNING_BLANK_STORES = "NAMA TOKO TIDAK BOLEH KOSONG";
    public final static String WARNING_BLANK_ADDRESS_STORE = "ALAMAT WAREHOUSE TIDAK BOLEH KOSONG";
    public final static String WARNING_STORE_NOT_FOUND = "TOKO BELUM TERDAFTAR";

    /*Users*/
    public final static String WARNING_USER_FULLNAME_MANDATORY = "NAMA ANDA TIDAK BOLEH KOSONG";
    public final static String WARNING_USER_BIRTHDATE_MANDATORY = "TANGGAL LAHIR ANDA TIDAK BOLEH KOSONG";
    public final static String WARNING_USER_EMAIL_MANDATORY = "EMAIL ANDA TIDAK BOLEH KOSONG";
    public final static String WARNING_USER_PASSWORD_MANDATORY = "PASSWORD ANDA TIDAK BOLEH KOSONG";
    public final static String WARNING_FAILED_AUTHENTICATION = "OTENTIKASI GAGAL";
    public final static String SUCCESS_AUTHENTICATION = "EMAIL VALID";
}
