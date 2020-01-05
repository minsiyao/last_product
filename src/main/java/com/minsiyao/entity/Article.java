package com.minsiyao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Article implements Serializable {
    private String id;

    private String guru_id;

    private String author;

    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GTM-8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_date;

    private String content;

    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GTM-8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publish_date;

    private String type;

}