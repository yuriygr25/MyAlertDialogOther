package com.example.yura.myalertdialogother;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends Activity {

    AlertDialog.Builder ad;
    Context context;

    // идентификатор диалогового окна AlertDialog с кнопками
    private final int IDD_THREE_BUTTONS = 0;
    private final int IDD_LIST_CATS = 1;
    private final int IDD_RADIO_CATS = 2;
    private final int IDD_CHECK_CATS = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                showDialog(IDD_THREE_BUTTONS);
                break;

            case R.id.button2:
                showDialog(IDD_LIST_CATS);
                break;

            case R.id.button3:
                showDialog(IDD_RADIO_CATS);
                break;

            case R.id.button4:
                showDialog(IDD_CHECK_CATS);
                break;
        }
    }

/*
    public void onClick(View v) {
        //ad.show();
        showDialog(IDD_THREE_BUTTONS);
    }
*/

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case IDD_THREE_BUTTONS:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Выберите правильный ответ")
                        .setCancelable(false)
                        .setPositiveButton("Мяу",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        Toast.makeText(getApplicationContext(),
                                                "Выбрано Мяу",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .setNeutralButton("Гав",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        Toast.makeText(getApplicationContext(),
                                                "Выбрано Гав",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                })
                        .setNegativeButton("Сам дурак!",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        Toast.makeText(getApplicationContext(),
                                                "Выбрано 'Сам дурак'",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                return builder.create();
            case IDD_LIST_CATS:

                final String[] mCatsName ={"Васька", "Рыжик", "Мурзик"};

                builder = new AlertDialog.Builder(this);
                builder.setTitle("Выбираем кота"); // заголовок для диалога

                builder.setItems(mCatsName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getApplicationContext(),
                                "Выбранный кот: " + mCatsName[item],
                                Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setCancelable(false);
                return builder.create();
            case IDD_RADIO_CATS:

                final String[] mChooseCats = { "Васька", "Рыжик", "Мурзик" };
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Выберите любимое имя кота")
                        .setCancelable(false)

                        // добавляем одну кнопку для закрытия диалога
                        .setNeutralButton("Назад",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                })

                        // добавляем переключатели
                        .setSingleChoiceItems(mChooseCats, -1,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int item) {
                                        Toast.makeText(
                                                getApplicationContext(),
                                                "Любимое имя кота: "
                                                        + mChooseCats[item],
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                return builder.create();
            case IDD_CHECK_CATS:
                final boolean[] mCheckedItems = { false, true, false };
                final String[] checkCatsName = { "Васька", "Рыжик", "Мурзик" };
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Выберите котов")
                        .setCancelable(false)

                        .setMultiChoiceItems(checkCatsName, mCheckedItems,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which, boolean isChecked) {
                                        mCheckedItems[which] = isChecked;
                                    }
                                })

                        // Добавляем кнопки
                        .setPositiveButton("Готово",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        StringBuilder state = new StringBuilder();
                                        for (int i = 0; i < checkCatsName.length; i++) {
                                            state.append("" + checkCatsName[i]);
                                            if (mCheckedItems[i])
                                                state.append(" выбран\n");
                                            else
                                                state.append(" не выбран\n");
                                        }
                                        Toast.makeText(getApplicationContext(),
                                                state.toString(), Toast.LENGTH_LONG)
                                                .show();
                                    }
                                })

                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();

                                    }
                                });
                return builder.create();
            default:
                return null;
        }
    }
}