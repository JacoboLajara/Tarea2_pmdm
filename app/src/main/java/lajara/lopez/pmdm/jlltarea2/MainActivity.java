package lajara.lopez.pmdm.jlltarea2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import lajara.lopez.pmdm.jlltarea2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

   private NavController navController;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configura el Toolbar como ActionBar
        setSupportActionBar(binding.toolbar);

        // Configura el NavController
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
      AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
     NavigationUI.setupActionBarWithNavController(this, navController);

    }

    // Método para manejar el clic en un juego
    public void characterClicked(CharacterData characterData, View view) {
        // Crear un Bundle para pasar los datos al GameDetailFragment
        Bundle bundle = new Bundle();
        bundle.putString("name", characterData.getName()); // Pasa el nombre del juego
        bundle.putString("image", String.valueOf(characterData.getImage())); // Pasa la imagen del juego
        bundle.putString("image2",String.valueOf(characterData.getImage2()));
        bundle.putString("description",characterData.getDescription());
        bundle.putString("skills",characterData.getSkills());

        // Navegar al GameDetailFragment con el Bundle
        Navigation.findNavController(view).navigate(R.id.characterDetailFragment,bundle);
    }
    @Override
    public boolean onSupportNavigateUp() {
        // Utiliza el método navigateUp del NavController
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}