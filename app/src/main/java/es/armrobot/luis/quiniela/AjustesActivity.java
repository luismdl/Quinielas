package es.armrobot.luis.quiniela;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AjustesActivity extends AppCompatActivity {

    EditText p1;
    EditText px;
    Button aceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        p1=(EditText) findViewById(R.id.editText_p1);
        px=(EditText) findViewById(R.id.editText_px);
        aceptar=(Button) findViewById(R.id.botonAceptar);
    }

    public void aceptar(View view) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast;
        if (p1.getText().toString().equals("") || px.getText().toString().equals("")) {
            toast=Toast.makeText(context, R.string.textEmpty, duration);
            toast.show();
        } else {
            Double p1d=Double.parseDouble(p1.getText().toString());
            Double pxd=Double.parseDouble(px.getText().toString());
            if (p1d + pxd > 1.0){
                toast=Toast.makeText(context, R.string.text1, duration);
                toast.show();
            }else{
                Intent returnIntent = new Intent();
                Bundle data = new Bundle();
                data.putDouble("p1",p1d);
                data.putDouble("px",pxd);
                returnIntent.putExtras(data);
                setResult(RESULT_OK,returnIntent);
                finish();
            }
        }
    }
}
