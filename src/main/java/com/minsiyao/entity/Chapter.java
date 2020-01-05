package com.minsiyao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Chapter {

    private String id;
    private String album_id;
    private String title;
    private Double size;
    private String duration;
    private String audio;


}
