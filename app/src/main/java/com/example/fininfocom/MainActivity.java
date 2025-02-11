package com.example.fininfocom;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Spinner ruleSpinner;
    private NumberAdapter adapter;
    private List<Integer> numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.numberGrid);
        ruleSpinner = findViewById(R.id.ruleSpinner);

        numbers = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            numbers.add(i);
        }

        recyclerView.setLayoutManager(new GridLayoutManager(this, 10)); // 10 columns
        adapter = new NumberAdapter(numbers);
        recyclerView.setAdapter(adapter);

        final String[] rules = {"None", "Odd", "Even", "Prime", "Fibonacci"};
        ruleSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, rules));

        ruleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String rule = rules[position];
                adapter.updateHighlightingRule(rule); // Update the rule in the adapter
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
