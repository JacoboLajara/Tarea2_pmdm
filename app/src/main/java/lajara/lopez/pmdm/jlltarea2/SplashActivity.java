package lajara.lopez.pmdm.jlltarea2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Actividad de bienvenida (SplashActivity) que muestra una pantalla inicial al iniciar la aplicación.
 * Esta pantalla es visible durante un tiempo limitado antes de abrir la actividad principal (MainActivity).
 * Maneja manualmente la splash screen en versiones de Android inferiores a 12 (API 31).
 */
public class SplashActivity extends AppCompatActivity {

    // Tiempo de duración de la pantalla Splash en milisegundos (3 segundos)
    private static final int SPLASH_TIME_OUT = 3000;

    /**
     * Método que se ejecuta cuando se crea la actividad.
     * Configura la pantalla Splash dependiendo de la versión de Android y gestiona la transición a la actividad principal.
     *
     * @param savedInstanceState Objeto Bundle que contiene el estado guardado de la actividad, si aplica (puede ser null).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Verifica si la versión de la API es inferior a Android 12 (API 31)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            // Configura un diseño personalizado para la splash screen
            setContentView(R.layout.splash);

            // Usa un Handler para retrasar la transición a la MainActivity
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Inicia la actividad principal (MainActivity) después del tiempo de la splash
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);

                    // Finaliza la SplashActivity para evitar que vuelva a abrirse
                    finish();
                }
            }, SPLASH_TIME_OUT);
        } else {
            // Para versiones de Android 12 o superiores, usa la splash screen nativa
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);

            // Finaliza la SplashActivity ya que la splash nativa de Android se maneja automáticamente
            finish();
        }
    }
}
