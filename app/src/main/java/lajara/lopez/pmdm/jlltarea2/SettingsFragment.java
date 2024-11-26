package lajara.lopez.pmdm.jlltarea2;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

/**
 * Fragmento de configuración que permite al usuario cambiar el idioma de la aplicación.
 * Incluye un Switch para alternar entre inglés y español, y guarda la configuración
 * seleccionada para futuras sesiones.
 */
public class SettingsFragment extends Fragment {

    // Switch para seleccionar el idioma
    private Switch switchLanguage;

    /**
     * Método que se ejecuta cuando se crea la vista del fragmento.
     * Configura el diseño y la lógica del cambio de idioma.
     *
     * @param inflater           Objeto LayoutInflater usado para inflar el diseño del fragmento.
     * @param container          Contenedor padre al que se añadirá la vista inflada (puede ser null).
     * @param savedInstanceState Objeto Bundle que contiene el estado guardado del fragmento, si aplica (puede ser null).
     * @return La vista inflada del diseño del fragmento.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Infla el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Inicializa el Switch para cambiar de idioma
        switchLanguage = view.findViewById(R.id.change_language);

        // Desactiva el listener para evitar que se active durante la inicialización
        switchLanguage.setOnCheckedChangeListener(null);

        // Configura el estado inicial del Switch según el idioma guardado
        String currentLanguage = getSavedLanguage();
        switchLanguage.setChecked("es".equals(currentLanguage));

        // Reactiva el listener para manejar cambios en el Switch
        switchLanguage.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                setLocale("es"); // Cambia el idioma a español
            } else {
                setLocale("en"); // Cambia el idioma a inglés
            }
        });

        return view;
    }

    /**
     * Cambia el idioma de la aplicación y reinicia la actividad para aplicar los cambios.
     *
     * @param languageCode Código del idioma al que se cambiará (por ejemplo, "en" o "es").
     */
    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        // Configura el idioma seleccionado en la aplicación
        Configuration config = new Configuration();
        config.setLocale(locale);

        // Aplica la configuración al contexto de la actividad actual
        requireActivity().getResources().updateConfiguration(config, requireActivity().getResources().getDisplayMetrics());

        // Guarda el idioma seleccionado en las preferencias compartidas
        saveLanguage(languageCode);

        // Reinicia la actividad para que el cambio de idioma sea visible
        requireActivity().recreate();
    }

    /**
     * Guarda el idioma seleccionado por el usuario en las preferencias compartidas.
     *
     * @param languageCode Código del idioma seleccionado.
     */
    private void saveLanguage(String languageCode) {
        SharedPreferences preferences = requireContext().getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("selected_language", languageCode);
        editor.apply();
    }

    /**
     * Obtiene el idioma guardado previamente en las preferencias compartidas.
     *
     * @return Código del idioma guardado, o "en" si no hay un idioma guardado.
     */
    private String getSavedLanguage() {
        SharedPreferences preferences = requireContext().getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
        return preferences.getString("selected_language", "en"); // Por defecto, inglés
    }
}



