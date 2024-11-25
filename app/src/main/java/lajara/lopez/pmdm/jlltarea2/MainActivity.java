package lajara.lopez.pmdm.jlltarea2;

import android.app.AlertDialog;
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

import java.util.Objects;

import lajara.lopez.pmdm.jlltarea2.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private NavController navController;
    private ActionBarDrawerToggle toggle;
    private ActivityMainBinding binding;
//    private AppBarConfiguration appBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configura el Toolbar como ActionBar
        setSupportActionBar(binding.toolbar);

        // Vincular el DrawerLayout y NavigationView
        drawerLayout = binding.drawerLayout;
        navigationView = binding.navView;

        // Configurar el NavController con NavigationView
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);

        navController.addOnDestinationChangedListener(this::onChangeView);
// Configurar toggle para el botón de menú
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, binding.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Manejar eventos del menú
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.home) {
                navController.navigate(R.id.characterListFragment);
            } else if (id == R.id.settings) {
                navController.navigate(R.id.settingsFragment);
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

//        // Obtener el NavController desde el NavHostFragment
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment);
//        navController = navHostFragment.getNavController();


    }

//    private void onChangeView(NavController navController, NavDestination navDestination, Bundle bundle) {
//        if (toggle == null) return;
//        if (navDestination.getId() == R.id.characterDetailFragment) {
//            toggle.setDrawerIndicatorEnabled(false);
//        } else toggle.setDrawerIndicatorEnabled(true);
//
//    }

    private void onChangeView(NavController navController, NavDestination navDestination, Bundle bundle) {
        if (toggle == null) return;

        // Verifica si estás en el fragmento específico
        if (navDestination.getId() == R.id.characterDetailFragment) {
            // Deshabilitar hamburguesa y mostrar botón "Atrás"
            toggle.setDrawerIndicatorEnabled(false); // Desactiva hamburguesa
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); // Muestra botón "Atrás"
            toggle.setToolbarNavigationClickListener(view -> navController.navigateUp()); // Acción para botón "Atrás"
        } else {
            // Restaurar hamburguesa en las demás pantallas
            toggle.setDrawerIndicatorEnabled(true); // Activa hamburguesa
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false); // Oculta botón "Atrás"
            toggle.setToolbarNavigationClickListener(null); // Restaurar evento predeterminado
        }

        // Sincroniza siempre el toggle con el DrawerLayout
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            new AlertDialog.Builder(this, R.style.CustomAlertDialogTheme)
                    .setTitle("Acerca de:")
                    .setMessage("Aplicación desarrollada por Jacobo Lajara López, versión 1.0")
                    .setPositiveButton("Sí", (dialog, which) -> dialog.dismiss())
                    .setIcon(R.mipmap.ic_launcher_round)
                    .show();
            ;

        }
        return super.onOptionsItemSelected(item);
    }

    // Método para manejar el clic en un juego
    public void characterClicked(CharacterData characterData, View view) {
        // Crear un Bundle para pasar los datos al GameDetailFragment
        Bundle bundle = new Bundle();
        bundle.putString("name", characterData.getName()); // Pasa el nombre del juego
        bundle.putString("image", String.valueOf(characterData.getImage())); // Pasa la imagen del juego
        bundle.putString("image2", String.valueOf(characterData.getImage2()));
        bundle.putString("description", characterData.getDescription());
        bundle.putString("skills", characterData.getSkills());

        // Navegar al GameDetailFragment con el Bundle
        Navigation.findNavController(view).navigate(R.id.characterDetailFragment, bundle);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Utiliza el método navigateUp del NavController
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    public void setBinding(ActivityMainBinding binding) {
        this.binding = binding;
    }

    public static class SplashActivity {
    }
}