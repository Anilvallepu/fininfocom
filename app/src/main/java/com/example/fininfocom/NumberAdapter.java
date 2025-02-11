package com.example.fininfocom;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.NumberViewHolder> {

    private List<Integer> numbers;
    private String highlightRule = "None";  // Default highlighting rule

    public NumberAdapter(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        int number = numbers.get(position);
        holder.numberText.setText(String.valueOf(number));

        holder.numberText.setBackgroundColor(Color.WHITE); // Default color (no highlight)

        // Apply rule-based highlighting when a rule is selected
        if (highlightRule.equals("Odd") && number % 2 != 0) {
            holder.numberText.setBackgroundColor(Color.YELLOW);
        } else if (highlightRule.equals("Even") && number % 2 == 0) {
            holder.numberText.setBackgroundColor(Color.CYAN);
        } else if (highlightRule.equals("Prime") && isPrime(number)) {
            holder.numberText.setBackgroundColor(Color.GREEN);
        } else if (highlightRule.equals("Fibonacci") && isFibonacci(number)) {
            holder.numberText.setBackgroundColor(Color.MAGENTA);
        }
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    public void updateHighlightingRule(String rule) {
        this.highlightRule = rule;
        notifyDataSetChanged();  // Notify the adapter to update the grid when the rule changes
    }

    private boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    private boolean isFibonacci(int number) {
        int a = 0, b = 1, c = 0;
        while (c < number) {
            c = a + b;
            a = b;
            b = c;
        }
        return c == number;
    }

    class NumberViewHolder extends RecyclerView.ViewHolder {
        TextView numberText;

        public NumberViewHolder(View itemView) {
            super(itemView);
            numberText = itemView.findViewById(R.id.numberText);  // Reference to the TextView in the grid item layout
        }
    }
}
