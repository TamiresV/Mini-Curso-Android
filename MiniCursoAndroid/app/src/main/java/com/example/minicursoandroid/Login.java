package com.example.minicursoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    //EMAIL E SENHA CRIADOS APENAS PARA TESTE
    private final String emailSalvo = "tamires@teste.com", senhaSalva = "teste";

    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    private EditText edtEmail,edtSenha;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);

        btLogin = findViewById(R.id.btLogin);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtEmail.getText().toString().isEmpty() && !edtSenha.getText().toString().isEmpty()){
                    //VERIFICA SE EMAIL É VALIDO
                    if(checkEmail(edtEmail.getText().toString())){
                        //VERIFICA SE SENHA TEM TAMANHO MAIOR QUE TRÊS
                        if(edtSenha.getText().toString().length() > 3){
                            //VERIFICA SE EMAIL E SENHA SÃO AS MESMAS SALVAS NO SISTEMA
                            if(checkLogin(edtEmail.getText().toString(), edtSenha.getText().toString())){
                                Toast.makeText(Login.this, "Login efetuado", Toast.LENGTH_LONG).show();
                                Intent mainIntent = new Intent(Login.this, Home.class);
                                mainIntent.putExtra("EMAIL",edtEmail.getText().toString());
                                Login.this.startActivity(mainIntent);
                                Login.this.finish();
                            }else{
                                Toast.makeText(Login.this, "Email ou senha incorretos", Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(Login.this, "Senha precisa ter 4 ou mais caracteres", Toast.LENGTH_LONG).show();
                            edtSenha.setError("Senha inválida");
                        }
                    }else{
                        Toast.makeText(Login.this, "E-mail inválido", Toast.LENGTH_LONG).show();
                        edtEmail.setError("Email inválido");
                    }
                }else {
                    //SE CAMPOS SENHA E/OU EMAIL ESTIVEREM VAZIOS DA ALERTA PARA USUÁRIO
                    Toast.makeText(Login.this, "E-mail e Senha são obrigatórios", Toast.LENGTH_LONG).show();
                    if(edtEmail.getText().toString().isEmpty()){
                        edtEmail.setError("Email obrigatório");
                    }
                    if(edtSenha.getText().toString().isEmpty()) {
                        edtSenha.setError("Senha obrigatória");
                    }
                }
            }
        });
    }

    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    private boolean checkLogin(String email, String senha){
        boolean podeEntrar = false;

        if(email.equals(emailSalvo) && senha.equals(senhaSalva)){
            podeEntrar =true;
        }

        return podeEntrar;
    }
}