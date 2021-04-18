package ru.netology.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderModel {
    String credit_id;
    public String payment_id;
    String id;
    String created;
}
