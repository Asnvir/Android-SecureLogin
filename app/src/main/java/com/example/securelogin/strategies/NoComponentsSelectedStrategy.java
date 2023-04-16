package com.example.securelogin.strategies;

import com.example.securelogin.CombinationStrategy;

public class NoComponentsSelectedStrategy implements CombinationStrategy {
    @Override
    public void performActions() {
        // Логика выполнения действий для комбинации 0|0|0
        System.out.println("No components selected.");
    }
}