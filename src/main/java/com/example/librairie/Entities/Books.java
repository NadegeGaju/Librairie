package com.example.librairie.Entities;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Books {

    private int id;
    private String title;
    private String author;
    private int copies;
    private boolean isAvail;

}
