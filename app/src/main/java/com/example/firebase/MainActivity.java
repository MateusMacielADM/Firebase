package com.example.firebase;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebase.Data.Dados;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView nome;
    TextView cargo;
    Button banco;
    Button editar;
    com.firebase.client.Firebase mRef;
    EditText edit_nome;
    EditText edit_cargo;
    String text_nome;
    String text_cargo;
    String Firebase_URL ="https://sisvend-5221b.firebaseio.com/situacao";
    Dados dados;

    @Override
    protected void onStart() {
        super.onStart();

        edit_cargo = (EditText) findViewById(R.id.edit_cargo);
        edit_nome = (EditText) findViewById(R.id.edit_nome);
        nome = (TextView) findViewById(R.id.nome);
        cargo = (TextView) findViewById(R.id.cargo);
        banco = (Button) findViewById(R.id.banco);
        editar = (Button) findViewById(R.id.editar);
        mRef = new com.firebase.client.Firebase(Firebase_URL);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                nome.setText(text);
                Toast.makeText(getApplicationContext(), "Deu Certo", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_SHORT).show();
            }
        });
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                if (edit_nome.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Campo Está Vazio, escreva alguma coisa", Toast.LENGTH_SHORT).show();
                } else {
                    text_nome = edit_nome.getText().toString();
                    mRef.setValue(text_nome);
                }

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
