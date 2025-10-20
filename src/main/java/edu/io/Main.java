package edu.io;

public class Main {
    public static void main(String[] args) {
        AppConfig config = AppConfig.getInstance();
        
        config.set("tryb", "ciemny");
        config.set("jezyk", "polski");
        config.set("rozmiar_czcionki", 14);
        
        System.out.println("Aktualna konfiguracja:");
        System.out.println("Tryb: " + config.get("tryb"));
        System.out.println("Język: " + config.get("jezyk"));
        System.out.println("Rozmiar czcionki: " + config.get("rozmiar_czcionki", Integer.class));
        
        AppConfig config2 = AppConfig.getInstance();
        System.out.println("Czy to ta sama instancja? " + (config == config2));
        
        System.out.println("Wszystko działa poprawnie");
    }
}