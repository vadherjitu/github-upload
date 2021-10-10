package com.testdemo.acronymstest.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.testdemo.acronymstest.R;
import com.testdemo.acronymstest.databinding.MyListBinding;
import com.testdemo.acronymstest.model.modelclass.FullForm;

import java.util.List;

public class MyAcronymAdapter extends RecyclerView.Adapter<MyAcronymAdapter.ViewHolder> {
    private List<FullForm> arrayList;
    private Context context;
    private LayoutInflater layoutInflater;

    public MyAcronymAdapter(List<FullForm> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        MyListBinding myListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.row_acronyms, parent, false);
        return new ViewHolder(myListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FullForm mFullForm = arrayList.get(position);
        holder.bind(mFullForm);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private MyListBinding myListBinding;

        public ViewHolder(@NonNull MyListBinding myListBinding) {
            super(myListBinding.getRoot());
            this.myListBinding = myListBinding;
        }

        public void bind(FullForm myli) {
            this.myListBinding.setMylistmodel(myli);
            myListBinding.executePendingBindings();
        }
    }
}
