package com.example.kalkulator_binnus;

import androidx.annotation.NonNull;

public class History {
    private String history;
    private String id;

    public String getHistory() { return  history; }

    public void setHistory(String history) { this.history = history; }

    public String getId() { return  id; }

    public History(String id, String history) {
        this.id = id;
        this.history = history;
    }

    @NonNull
    @Override
    public String toString() { return history; }
}
