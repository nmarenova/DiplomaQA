package ru.netology.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class CardModel {
    String number;
    String month;
    String year;
    String owner;
    String cvc;
}