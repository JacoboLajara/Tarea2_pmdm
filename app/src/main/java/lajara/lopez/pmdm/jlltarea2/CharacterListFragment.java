package lajara.lopez.pmdm.jlltarea2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import lajara.lopez.pmdm.jlltarea2.databinding.CharacterListFragmentBinding;

/**
 * Fragmento que muestra una lista de personajes.
 * Este fragmento es parte de una aplicación que gestiona información de personajes.
 * Contiene una lista con información básica sobre cada personaje,
 * incluyendo su imagen, descripción y habilidades.
 */
public class CharacterListFragment extends Fragment {
    // Enlace entre el código Java y el diseño XML del fragmento
    private CharacterListFragmentBinding binding;

    // Lista que almacena los datos de los personajes
    private ArrayList<CharacterData> characters;

    // Adaptador para conectar los datos de los personajes con el RecyclerView
    private CharacterRecycleViewAdapter adapter;

    /**
     * Este método se llama para crear y configurar la interfaz de usuario del fragmento.
     * Aquí se infla el diseño (layout) y se utiliza el sistema de data binding.
     *
     * @param inflater           Objeto LayoutInflater utilizado para inflar el diseño del fragmento.
     * @param container          Contenedor padre al que se adjuntará el diseño inflado (puede ser null).
     * @param savedInstanceState Objeto Bundle que contiene el estado anterior del fragmento,
     *                           si se está recreando (puede ser null).
     * @return La vista raíz del diseño inflado para este fragmento.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Infla el diseño del fragmento utilizando el binding
        binding = CharacterListFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot(); // Devuelve la vista raíz del diseño
    }

    /**
     * Este método se llama después de que la vista del fragmento haya sido creada.
     * Aquí se inicializan los datos de los personajes y se configura el RecyclerView.
     *
     * @param view               La vista creada por el método onCreateView.
     * @param savedInstanceState Objeto Bundle que contiene el estado anterior del fragmento,
     *                           si se está recreando (puede ser null).
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializa la lista de personajes
        loadCharacters();

        // Configura el RecyclerView con el adaptador
        adapter = new CharacterRecycleViewAdapter(characters, getActivity());
        binding.characterRecyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.characterRecyclerview.setAdapter(adapter);
    }

    /**
     * Método privado que carga la lista de personajes con datos predefinidos.
     * Añade varios personajes a la lista y muestra un mensaje emergente (Snackbar).
     */
    private void loadCharacters() {
        // Inicializa la lista de personajes
        characters = new ArrayList<>();

        // Añade datos de cada personaje a la lista
        characters.add(new CharacterData(
                R.drawable.yoshi,                // Imagen del personaje
                R.drawable.yoshi_p,              // Imagen del perfil del personaje
                getString(R.string.yoshi_name),  // Nombre del personaje
                getString(R.string.yoshi_description), // Descripción del personaje
                getString(R.string.yoshi_skills) // Habilidades del personaje
        ));
        characters.add(new CharacterData(
                R.drawable.mario,
                R.drawable.supermario_p,
                getString(R.string.mario_name),
                getString(R.string.mario_description),
                getString(R.string.mario_skills)
        ));
        characters.add(new CharacterData(
                R.drawable.peach,
                R.drawable.peach_p,
                getString(R.string.peach_name),
                getString(R.string.peach_description),
                getString(R.string.peach_skills)
        ));
        characters.add(new CharacterData(
                R.drawable.luigi,
                R.drawable.luigi_p,
                getString(R.string.luigi_name),
                getString(R.string.luigi_description),
                getString(R.string.luigi_skills)
        ));

        // Muestra un mensaje emergente con información
        Snackbar.make(binding.getRoot(), R.string.snack_text, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Método que se llama cuando el fragmento se vuelve visible al usuario.
     * Configura el título de la barra de acción (ActionBar).
     */
    @Override
    public void onStart() {
        super.onStart();

        // Si el fragmento está asociado a una actividad
        if (getActivity() != null) {
            // Configura el título de la barra de acción
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.lista_de_personajes);
        }
    }
}
