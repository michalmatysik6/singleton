package edu.io;

import org.yaml.snakeyaml.Yaml;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AppConfig {
    private static AppConfig jedynaInstancja;
    private final Map<String, Object> ustawienia;
    
    private AppConfig() {
        ustawienia = new HashMap<>();
    }
    
    public static AppConfig getInstance() {
        if (jedynaInstancja == null) {
            synchronized (AppConfig.class) {
                if (jedynaInstancja == null) {
                    jedynaInstancja = new AppConfig();
                }
            }
        }
        return jedynaInstancja;
    }
    
    public void set(String klucz, Object wartosc) {
        ustawienia.put(klucz, wartosc);
    }
    
    public Object get(String klucz) {
        return ustawienia.get(klucz);
    }
    
    public <T> T get(String klucz, Class<T> typ) {
        Object wartosc = ustawienia.get(klucz);
        if (wartosc == null) {
            return null;
        }
        
        try {
            return typ.cast(wartosc);
        } catch (ClassCastException e) {
            throw new ClassCastException("Nie udało się przekonwertować " + wartosc.getClass().getSimpleName() + " na " + typ.getSimpleName());
        }
    }
    
    public void load(String sciezkaPliku) {
        Yaml yaml = new Yaml();
        try (FileReader czytnik = new FileReader(sciezkaPliku)) {
            Map<String, Object> wczytaneUstawienia = yaml.load(czytnik);
            if (wczytaneUstawienia != null) {
                ustawienia.clear();
                ustawienia.putAll(wczytaneUstawienia);
            }
        } catch (IOException e) {
            throw new RuntimeException("Nie udało się wczytać pliku: " + sciezkaPliku);
        }
    }
    
    public void save(String sciezkaPliku) {
        Yaml yaml = new Yaml();
        try (FileWriter zapis = new FileWriter(sciezkaPliku)) {
            yaml.dump(ustawienia, zapis);
        } catch (IOException e) {
            throw new RuntimeException("Nie udało się zapisać pliku: " + sciezkaPliku);
        }
    }
}