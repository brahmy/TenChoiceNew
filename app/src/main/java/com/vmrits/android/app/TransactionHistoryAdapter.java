package com.vmrits.android.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TransactionHistoryAdapter extends RecyclerView.Adapter<TransactionHistoryAdapter.HistoryViewHolder> {
    private Context context;
    private ArrayList<TransactionHistoryPOJO> transactionHistoryPOJOArrayList;
    private LayoutInflater layoutInflater;
    public TransactionHistoryAdapter(Context context, ArrayList<TransactionHistoryPOJO> transactionHistoryArrayList) {
    this.context=context;
    this.transactionHistoryPOJOArrayList=transactionHistoryArrayList;
    this.layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TransactionHistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=layoutInflater.inflate(R.layout.adapter_transaction_history,viewGroup,false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionHistoryAdapter.HistoryViewHolder historyViewHolder, int i) {
        TransactionHistoryPOJO transactionHistoryPOJO=transactionHistoryPOJOArrayList.get(i);
        historyViewHolder.textView_paid_date.setText(transactionHistoryPOJO.getTransaction_date());
        historyViewHolder.textView_approved_date.setText(transactionHistoryPOJO.getTransaction_approved_date());
        historyViewHolder.textView_amount.setText(transactionHistoryPOJO.getTransaction_amount());

    }

    @Override
    public int getItemCount() {
        return transactionHistoryPOJOArrayList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_approved_date,textView_paid_date,textView_amount;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_amount=itemView.findViewById(R.id.id_adapter_transaction_approved_amount_textView);
            textView_approved_date=itemView.findViewById(R.id.id_adapter_transaction_approved_date_textView);
            textView_paid_date=itemView.findViewById(R.id.id_adapter_transaction_paid_status_textView);
        }
    }
}
