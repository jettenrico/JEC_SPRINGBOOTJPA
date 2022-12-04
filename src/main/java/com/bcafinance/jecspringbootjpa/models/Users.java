package com.bcafinance.jecspringbootjpa.models;

import com.bcafinance.jecspringbootjpa.utils.ConstantMessage;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Data
@Entity
@Table(name = "MstUsers")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Long id;

    @NotEmpty(message = ConstantMessage.WARNING_USER_FULLNAME_MANDATORY)
    @Column(name = "FullName",length = 80,nullable = false)
    private String fullName;

    @NotNull(message = ConstantMessage.WARNING_USER_BIRTHDATE_MANDATORY)
    @Column(name = "BirthDate",nullable = false)
    private LocalDate birthDate;

    @NotEmpty(message = ConstantMessage.WARNING_USER_EMAIL_MANDATORY)
    @Column(name = "Email",length = 30 ,nullable = false,unique = true)
    private String email;

    @NotEmpty(message = ConstantMessage.WARNING_USER_PASSWORD_MANDATORY)
    @Column(name = "Password", length = 50, nullable = false)
    private String password;

    @Column(name = "Token", nullable = false)
    private String token;

    @Transient//tidak akan menggenerate kolom di tabel
    private short age;//Menggunakan Object Integer karena nilai return dari Period.between adalah Integer

    @Column(name = "CreatedBy",nullable = false)
    private String createdBy = "1";

    //    @Column(name = "CreatedDate",nullable = true, columnDefinition = "datetime2(7) DEFAULT GETDATE() ")
    @Column(name = "CreatedDate",nullable = false)
    private Date createdDate = new Date();//JANGAN GUNAKAN columnDefinition untuk set default kolom, langsung set di variabel nya saja.

    @Column(name = "ModifiedBy",nullable = true)
    private String modifiedBy ;

    @Column(name = "ModifiedDate",nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive",nullable = false)
    private boolean isActive = true;

    public Users() {
    }

    public short getAge() {
        return (short) Period.between(this.birthDate,LocalDate.now()).getYears();//dikonversi ke short dari Integer
    }
}