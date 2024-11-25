package lajara.lopez.pmdm.jlltarea2;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
//import androidx.preference.PreferenceManager;

import java.util.Locale;

public class SettingsFragment extends Fragment {

    private Switch switchLanguage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Inicializa el Switch
        switchLanguage = view.findViewById(R.id.change_language);

        // Configura el estado inicial del Switch según el idioma guardado
        String currentLanguage = getSavedLanguage();
        switchLanguage.setChecked("es".equals(currentLanguage));

        // Configura el listener para cambiar el idioma
        switchLanguage.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                setLocale("es"); // Cambiar a español
            } else {
                setLocale("en"); // Cambiar a inglés
            }
        });
//
        return view;
    }
//
    private void setLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        // Configuración moderna para cambiar el idioma
        Configuration config = new Configuration();
        config.setLocale(locale);

        // Aplica la configuración al contexto actual
        requireActivity().getResources().updateConfiguration(config, requireActivity().getResources().getDisplayMetrics());

        // Guarda el idioma seleccionado en SharedPreferences
        saveLanguage(languageCode);

        // Reinicia la actividad para reflejar el cambio de idioma
        requireActivity().recreate();
    }
//
    private void saveLanguage(String languageCode) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("selected_language", languageCode);
        editor.apply();
    }
//
    private String getSavedLanguage() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        return preferences.getString("selected_language", "en"); // Predeterminado: inglés
    }
}

