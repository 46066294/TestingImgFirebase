package mat.testingimgfirebase;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
//import org.apache.http;
import org.apache.http.Header;
import org.json.JSONArray;

import com.github.snowdream.android.widget.SmartImageView;
import com.loopj.android.http.*;

public class MainActivity extends AppCompatActivity {
SmartImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagen = (SmartImageView) findViewById(R.id.my_image);
    }

    public void verImagen(View view){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://192.168.1.102:8080/FILES/bender.png", new AsyncHttpResponseHandler() {
            //crear un javascript que retorne un array json_encode
            //https://www.youtube.com/watch?v=ZLah5YtZK_M
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                if(statusCode == 200){
                    try {
                        JSONArray obj = new JSONArray(new String(response));
                        //String urlImagen = obj.getJSONObject(0).getString("Imagen");
                        String urlImagen = "bender.png";
                        Rect rect = new Rect(imagen.getLeft(),
                                imagen.getTop(), imagen.getRight(), imagen.getBottom());

                                imagen.setImageUrl(urlImagen, rect);

                                Toast.makeText(getApplicationContext(), "Imagen: " +
                                urlImagen, Toast.LENGTH_LONG).show();

                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }
}
