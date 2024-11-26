package lajara.lopez.pmdm.jlltarea2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import java.util.Locale;
import java.util.Objects;

import lajara.lopez.pmdm.jlltarea2.databinding.ActivityMainBinding;

/**
 * Actividad principal de la aplicación que implementa un menú lateral (DrawerLayout)
 * y gestiona la navegación entre fragmentos usando NavController.
 * También incluye opciones de configuración y gestión de eventos como la selección
 * de elementos en el menú lateral y acciones específicas en la barra de herramientas.
 */
public class MainActivity extends AppCompatActivity {

    // Layout del menú lateral
    private DrawerLayout drawerLayout;

    // Vista de navegación para el menú lateral
    private NavigationView navigationView;

    // Controlador de navegación para gestionar la navegación entre fragmentos
    private NavController navController;

    // Botón de menú (hamburguesa) en la barra de herramientas
    private ActionBarDrawerToggle toggle;

    // Enlace al diseño principal de la actividad
    private ActivityMainBinding binding;

    /**
     * Método que se ejecuta cuando se crea la actividad.
     * Configura el diseño principal, el menú lateral, la barra de herramientas
     * y el controlador de navegación.
     *
     * @param savedInstanceState Objeto Bundle con el estado guardado de la actividad, si aplica.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Muestra una pantalla de carga al iniciar la aplicación
        SplashScreen.installSplashScreen(this);

        // Aplica el idioma guardado previamente por el usuario
        applySavedLanguage();

        super.onCreate(savedInstanceState);

        // Habilita el modo EdgeToEdge para una mejor experiencia de visualización
        EdgeToEdge.enable(this);

        // Configura el binding para el diseño principal
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configura la barra de herramientas como ActionBar
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Main menú");

        // Vincula el DrawerLayout y NavigationView con el binding
        drawerLayout = binding.drawerLayout;
        navigationView = binding.navView;

        // Configura el NavController para gestionar la navegación
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Escucha cambios en las vistas navegadas para actualizar la barra de herramientas
        navController.addOnDestinationChangedListener(this::onChangeView);


        // Configura el botón de menú (hamburguesa)
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, binding.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Cambiar el título dinámicamente según el destino actual
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.characterDetailFragment) {
                // Cambia el título al nombre del personaje
                if (arguments != null && arguments.containsKey("name")) {
                    String characterName = arguments.getString("name", getString(R.string.detalles_del_personaje));
                    Objects.requireNonNull(getSupportActionBar()).setTitle(characterName);
                } else {
                    Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.detalles_del_personaje);
                }
            } else {
                // Restaura el título original para  que sea distinto en los otros fragmentos
                Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.pantalla_principal);
            }
        });

        // Maneja las acciones de los elementos del menú lateral
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.home) {
                navController.navigate(R.id.characterListFragment);
            } else if (id == R.id.settings) {
                navController.navigate(R.id.settingsFragment);
            } else if (id == R.id.action_exit) {
                showExitConfirmationDialog();
                return true;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    /**
     * Método que gestiona los cambios en la vista activa dentro del NavController.
     * Actualiza el botón de menú (hamburguesa) y el botón "Atrás" según la pantalla actual.
     *
     * @param navController  Controlador de navegación.
     * @param navDestination Destino actual de la navegación.
     * @param bundle         Datos asociados a la navegación, si aplica.
     */
    private void onChangeView(NavController navController, NavDestination navDestination, Bundle bundle) {
        if (toggle == null) return;

        if (navDestination.getId() == R.id.characterDetailFragment) {
            // Deshabilita el botón de menú y habilita el botón "Atrás" en la pantalla de detalle
            toggle.setDrawerIndicatorEnabled(false);
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            toggle.setToolbarNavigationClickListener(view -> navController.navigateUp());
        } else {
            // Restaura el botón de menú en las demás pantallas
            toggle.setDrawerIndicatorEnabled(true);
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
            toggle.setToolbarNavigationClickListener(null);
        }

        // Sincroniza el estado del toggle con el DrawerLayout
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * Crea el menú de opciones de la barra de herramientas.
     *
     * @param menu Objeto Menu donde se inflará el menú de opciones.
     * @return true si el menú se creó correctamente.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        return true;
    }

    /**
     * Maneja las acciones seleccionadas en el menú de opciones de la barra de herramientas.
     *
     * @param item Elemento del menú que fue seleccionado.
     * @return true si se manejó correctamente, false en caso contrario.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            new AlertDialog.Builder(this, R.style.CustomAlertDialogTheme)
                    .setTitle(R.string.acerca_de)
                    .setMessage(R.string.developed_by)
                    .setPositiveButton("Sí", (dialog, which) -> dialog.dismiss())
                    .setIcon(R.mipmap.ic_launcher_round)
                    .show();
        } else if (item.getItemId() == R.id.action_exit) {
            showExitConfirmationDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Muestra un diálogo de confirmación para salir de la aplicación.
     * Si el usuario confirma, cierra la aplicación.
     */
    private void showExitConfirmationDialog() {
        new AlertDialog.Builder(this, R.style.CustomAlertDialogTheme)
                .setTitle(R.string.Exit)
                .setMessage(R.string.exit_confirm)
                .setPositiveButton(R.string.yes, (dialog, which) -> finishAffinity())
                .setNegativeButton(R.string.no, (dialog, which) -> dialog.dismiss())
                .show();
    }

    /**
     * Maneja el evento de clic en un personaje.
     * Crea un Bundle con los datos del personaje seleccionado y navega a la pantalla de detalle.
     *
     * @param characterData Objeto CharacterData con los datos del personaje seleccionado.
     * @param view          Vista que fue clicada.
     */
    public void characterClicked(CharacterData characterData, View view) {
        Bundle bundle = new Bundle();
        bundle.putString("name", characterData.getName());
        bundle.putString("image", String.valueOf(characterData.getImage()));
        bundle.putString("image2", String.valueOf(characterData.getImage2()));
        bundle.putString("description", characterData.getDescription());
        bundle.putString("skills", characterData.getSkills());

        Navigation.findNavController(view).navigate(R.id.characterDetailFragment, bundle);
    }

    /**
     * Permite la navegación "hacia arriba" cuando se presiona el botón "Atrás".
     *
     * @return true si la navegación se realizó correctamente.
     */
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    /**
     * Aplica el idioma guardado previamente por el usuario al cargar la aplicación.
     */
    private void applySavedLanguage() {
        SharedPreferences preferences = getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
        String savedLanguage = preferences.getString("selected_language", "en");
        Locale locale = new Locale(savedLanguage);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);

        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }
}
