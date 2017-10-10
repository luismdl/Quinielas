package es.armrobot.luis.quiniela;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Quiniela quiniela;
    //componentes graficos
    TextView textViewInformacion;
    Button botonGenerar;
    Button botonLimpiar;
    Button botonAjustes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //inicializa quiniela
        quiniela=new Quiniela(0.6,0.2,0.25,0.25,0.25);
        //inicializa botones
        textViewInformacion=(TextView)findViewById(R.id.textView_informacion);
        botonAjustes=(Button)findViewById(R.id.botonAjustes);
        botonGenerar=(Button)findViewById(R.id.botonGenerar);
        botonLimpiar=(Button)findViewById(R.id.botonLimpiar);

    }

    public void botones(View view){
        switch (view.getId()) {
            case R.id.botonGenerar:
                String quiniel = quiniela.dameQuiniela();
                SpannableString sb = new SpannableString( quiniel );
                for(int i=0;i<quiniel.length()-4;i++){
                    if(quiniel.charAt(i)=='1') {
                        sb.setSpan(new ForegroundColorSpan(Color.GREEN), i, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }else if(quiniel.charAt(i)=='2'){
                        sb.setSpan(new ForegroundColorSpan(Color.RED),i,i+1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }else if(quiniel.charAt(i)=='X'){
                        sb.setSpan(new ForegroundColorSpan(Color.BLACK),i,i+1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
                textViewInformacion.setText(sb);
                break;
            case R.id.botonLimpiar:
                textViewInformacion.setText(R.string.text_main);
                break;
            case R.id.botonAjustes:
                Intent ajustes = new Intent(this, AjustesActivity.class);
                startActivityForResult(ajustes,1);
                break;
            default:
                break;
        }


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            if (resultCode == RESULT_OK){
                Double p1 = data.getDoubleExtra("p1",0.4);
                Double px = data.getDoubleExtra("px",0.2);
                Log.d("p1","p1: "+p1);
                Log.d("px","px: "+px);
                quiniela.setProbabilidades(p1,px);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(R.id.ajustesIcon==item.getItemId()){
            botones(botonAjustes);
        }
        return true;

    }
}
