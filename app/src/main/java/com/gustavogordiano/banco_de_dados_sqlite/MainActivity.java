package com.gustavogordiano.banco_de_dados_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.sql.SQLData;

public class MainActivity extends AppCompatActivity {
    private String TAG = "SQLmain";
    private TextView textView_log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_log = findViewById(R.id.textView_log);

        String logMeu = "";

        Log.w(TAG, "Iniciou a main");

        try {
            ///Banco de Dados

            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            ///Tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas( nome VARCHAR, idade INT(3) ) ");

            ///Inserir Dados
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES('Gustavo',20)");

            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES('Alyson',26)");

            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES('Alicio',45)");

            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES('Brenner',26)");

            ///Deletar Dados
            ///bancoDados.execSQL("DELETE FROM pessoas");

            ///Recuperar Pessoas
            Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas", null);

            ///Indices da Tabela
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();
            while (cursor != null) {

                Log.w(TAG, cursor.getString(indiceNome));
                logMeu = logMeu + " Nome: " + cursor.getString(indiceNome) + "\n";
                logMeu = logMeu + " Idade: " + cursor.getString(indiceIdade) + "\n";
                Log.w(TAG, cursor.getString(indiceIdade));
                cursor.moveToNext();
            }


        } catch (Exception e) {
            e.printStackTrace();

            Log.w(TAG,"EXCEPTION gerada");

        }

        textView_log.setText(logMeu);
    }
}