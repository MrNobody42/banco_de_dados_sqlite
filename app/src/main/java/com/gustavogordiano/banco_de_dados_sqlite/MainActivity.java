package com.gustavogordiano.banco_de_dados_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.sql.SQLData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            ///Banco de Dados

            SQLiteDatabase bancoDados =openOrCreateDatabase( "app", MODE_PRIVATE, null );

            ///Tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas( nome VARCHAR, Idade INT(3), ) ");

            ///Inserir Dados
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES('Gustavo',20)");
            bancoDados.execSQL("INSERT INTO pessoas (nome,idade) VALUES('Maria',26)");

            ///Recuperar Pessoas
            Cursor cursor = bancoDados.rawQuery("SELECT nome, idade FROM pessoas", null"");

            ///Indices da Tabela
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();
            while (cursor !=null){
                Log.i("RESULTADO - nome: ", Cursor.getString(indiceNome));
                Log.i("RESULTADO - idade: ", Cursor.getString(indiceIdade));
                cursor.moveToNext();
            }
        }

         catch (Exception e){e.printStackTrace();

    }

}