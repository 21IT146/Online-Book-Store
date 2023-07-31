package com.book.store.Entities;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PriceHistory {

    private Timestamp time;
    private int price;

}
