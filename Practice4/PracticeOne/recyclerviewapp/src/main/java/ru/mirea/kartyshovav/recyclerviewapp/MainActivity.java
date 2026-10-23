package ru.mirea.kartyshovav.recyclerviewapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<HistoricalEvent> events = getListData();

        EventAdapter adapter = new EventAdapter(events);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<HistoricalEvent> getListData() {
        List<HistoricalEvent> list = new ArrayList<>();

        list.add(new HistoricalEvent(
                "Крещение Руси",
                "988 год — принятие христианства князем Владимиром",
                R.mipmap.ic_launcher));

        list.add(new HistoricalEvent(
                "Куликовская битва",
                "1380 год — победа Дмитрия Донского над Мамаем",
                R.mipmap.ic_launcher));

        list.add(new HistoricalEvent(
                "Бородинское сражение",
                "1812 год — битва с Наполеоном",
                R.mipmap.ic_launcher));

        list.add(new HistoricalEvent(
                "Отмена крепостного права",
                "1861 год — реформа Александра II",
                R.mipmap.ic_launcher));

        list.add(new HistoricalEvent(
                "Октябрьская революция",
                "1917 год — приход большевиков к власти",
                R.mipmap.ic_launcher));

        list.add(new HistoricalEvent(
                "Великая Отечественная война",
                "1941–1945 — война с фашистской Германией",
                R.mipmap.ic_launcher));

        list.add(new HistoricalEvent(
                "Первый полёт человека в космос",
                "1961 год — Юрий Гагарин",
                R.mipmap.ic_launcher));

        list.add(new HistoricalEvent(
                "Распад СССР",
                "1991 год — образование СНГ",
                R.mipmap.ic_launcher));

        return list;
    }
}