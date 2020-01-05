package com.minsiyao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Introduction {
    private String thumbnail;
    private String title;
    private String score;
    private String author;
    private String broadcast;
    private String set_count;
    private String brief;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GTM+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_date;
}
