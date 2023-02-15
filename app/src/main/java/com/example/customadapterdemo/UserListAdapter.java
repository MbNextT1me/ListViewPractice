package com.example.customadapterdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class UserListAdapter extends BaseAdapter {
    Context ctx; ArrayList<User> users;

    public void sortByName() {
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User user, User t1) {
                return user.name.compareTo(t1.name);
            }
        });
        notifyDataSetChanged();
    }

    public void sortByPhone() {
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User user, User t1) {
                return user.phoneNumber.compareTo(t1.phoneNumber);
            }
        });
        notifyDataSetChanged();
    }

    public void sortBySex() {
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User user, User t1) {
                return user.sex.compareTo(t1.sex);
            }
        });
        notifyDataSetChanged();
    }

    public UserListAdapter(Context ctx, ArrayList<User> users) {
        this.ctx = ctx;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // получаем данные из коллекции
        Date begin = new Date();
        User u = users.get(position);

        // создаём разметку (контейнер)
        convertView = LayoutInflater.from(ctx).
                inflate(R.layout.useritem, parent, false);
        // получаем ссылки на элементы интерфейса
        ImageView ivUserpic = convertView.findViewById(R.id.userpic);
        ivUserpic.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(R.color.blue);
            }
        });
        TextView tvName = convertView.findViewById(R.id.name);
        TextView tvPhone = convertView.findViewById(R.id.phone);

        // задаём содержание
        tvName.setText(u.name);
        tvPhone.setText(u.phoneNumber);
        switch (u.sex) {
            case MAN: ivUserpic.setImageResource(R.drawable.user_man); break;
            case WOMAN: ivUserpic.setImageResource(R.drawable.user_woman); break;
            case UNKNOWN: ivUserpic.setImageResource(R.drawable.user_unknown); break;
        }
        Date finish = new Date();
        Log.d("mytag", "getView time: "+(finish.getTime()-begin.getTime()));
        return convertView;
    }
}
