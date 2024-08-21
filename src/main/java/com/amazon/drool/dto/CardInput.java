package com.amazon.drool.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardInput {
    String card;
    String cardType;

    @Override
    public String toString() {
        return "CardInput{" +
                "card='" + card + '\'' +
                ", cardType='" + cardType + '\'' +
                '}';
    }
}
