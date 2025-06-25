package dev.brenolucks.clicker_backend.utils;

import org.springframework.stereotype.Component;

@Component
public class ClickerUtils {
    public int generateRandomNUmber() { return (int) (Math.random() * 101); }
    public int generateAvailableClicks(int randomNumber) { return (int) (Math.random() * randomNumber); }
}
