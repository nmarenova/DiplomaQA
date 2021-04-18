package ru.netology.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreditModel {
    String id;
    public String bank_id;
    String created;
    public String status;
}
