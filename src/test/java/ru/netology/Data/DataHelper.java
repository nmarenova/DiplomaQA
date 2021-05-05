package ru.netology.Data;

import com.github.javafaker.Faker;
import ru.netology.Models.CardModel;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    static String validCard = "4444 4444 4444 4441";
    static String invalidCard = "4444 4444 4444 4442";

    public static CardModel generatedApprovedCard(String local) {
        Faker faker = new Faker(new Locale(local));
        return new CardModel(validCard,
                getMonthFromArray(setGoodMonth()),
                String.valueOf(LocalDate.now().plusYears(getRandomInt()).getYear()).substring(2),
                faker.name().fullName(),
                faker.numerify("###"));
    }

    public static CardModel generatedDeclinedCard(String local) {
        Faker faker = new Faker(new Locale("en"));
        return new CardModel(invalidCard,
                getMonthFromArray(setGoodMonth()),
                String.valueOf(LocalDate.now().plusYears(getRandomInt()).getYear()).substring(2),
                faker.name().lastName() + " " + faker.name().firstName(),
                faker.numerify("###"));
    }

    private static List<String> setGoodMonth() {
        List<String> goodMonth = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        return goodMonth;
    }

    private static String getMonthFromArray(List<String> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

    private static int getRandomInt() {
        Random random = new Random();
        return random.nextInt(5) + 1;
    }
}
