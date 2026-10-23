package ru.mirea.kartyshovav.listviewapp;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String[] books = {
            "Джордж Оруэлл — 1984",
            "Джордж Оруэлл — Скотный двор",
            "Фёдор Достоевский — Преступление и наказание",
            "Фёдор Достоевский — Братья Карамазовы",
            "Лев Толстой — Война и мир",
            "Лев Толстой — Анна Каренина",
            "Михаил Булгаков — Мастер и Маргарита",
            "Михаил Булгаков — Собачье сердце",
            "Александр Пушкин — Евгений Онегин",
            "Николай Гоголь — Мёртвые души",
            "Иван Тургенев — Отцы и дети",
            "Антон Чехов — Вишнёвый сад",
            "Максим Горький — На дне",
            "Борис Пастернак — Доктор Живаго",
            "Александр Солженицын — Архипелаг ГУЛАГ",
            "Владимир Набоков — Лолита",
            "Сергей Есенин — Стихотворения",
            "Анна Ахматова — Реквием",
            "Марина Цветаева — Стихотворения",
            "Иосиф Бродский — Стихотворения",
            "Габриэль Гарсиа Маркес — Сто лет одиночества",
            "Габриэль Гарсиа Маркес — Любовь во время чумы",
            "Эрнест Хемингуэй — Старик и море",
            "Эрнест Хемингуэй — Прощай, оружие!",
            "Фрэнсис Скотт Фицджеральд — Великий Гэтсби",
            "Джером Сэлинджер — Над пропастью во ржи",
            "Рэй Брэдбери — 451 градус по Фаренгейту",
            "Станислав Лем — Солярис",
            "Аркадий и Борис Стругацкие — Пикник на обочине",
            "Джон Р. Р. Толкин — Властелин колец",
            "Джоан Роулинг — Гарри Поттер и философский камень",
            "Агата Кристи — Убийство в «Восточном экспрессе»",
            "Артур Конан Дойл — Приключения Шерлока Холмса",
            "Оскар Уайльд — Портрет Дориана Грея",
            "Герман Мелвилл — Моби Дик"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView bookListView = findViewById(R.id.bookListView);

        // Для связки данных и виджета
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, books) {

            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent); // Позиция элемента, переиспользуемый View, родительский ViewGroup

                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);

                text1.setText(String.valueOf(position + 1));
                text2.setText(getItem(position));

                return view;
            }
        };

        bookListView.setAdapter(adapter);
    }
}