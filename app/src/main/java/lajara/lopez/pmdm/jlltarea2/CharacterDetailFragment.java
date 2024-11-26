package lajara.lopez.pmdm.jlltarea2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import lajara.lopez.pmdm.jlltarea2.databinding.CharacterDetailFragmentBinding;

/**
 * Fragmento que representa la pantalla de detalle de un personaje.
 * Este fragmento es contenido por {@link CharacterListFragment}
 * y muestra información detallada sobre un personaje seleccionado.
 */

public class CharacterDetailFragment extends Fragment {

    private CharacterDetailFragmentBinding binding;

    /**
     * Infla el diseño (layout) para este fragmento e inicializa el data binding.
     *
     * @param inflater           Objeto LayoutInflater que se utiliza para inflar las vistas del fragmento.
     * @param container          ViewGroup opcional al que se adjuntará este fragmento.
     * @param savedInstanceState Si no es nulo, este fragmento se está reconstruyendo
     *                           a partir de un estado guardado previamente
     *                           representado por el objeto Bundle.
     *                           Este fragmento puede optar por utilizar la información de este objeto.
     * @return La vista raíz del diseño inflado para este fragmento.
     */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Obtiene los datos del personaje pasados como argumentos y los muestra en la interfaz de usuario.
     *
     * @param view               La vista devuelta por onCreateView.
     * @param savedInstanceState Si no es nulo, este fragmento se está reconstruyendo
     *                           a partir de un estado guardado previamente, representado por el objeto Bundle.
     *                           Este fragmento puede optar por utilizar la información de este objeto.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Cambiar el título de la Toolbar
        if (getArguments() != null) {
            String name = getArguments().getString("name", "");
            if (!name.isEmpty()) {
                ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle(name);
            }
        }

        // Obteniene los datos del argumento que inicia este fragmento
        if (getArguments() != null) {
            String imageName = getArguments().getString("image2", ""); // Nombre del recurso como String
            String name = getArguments().getString("name", "");// Nombre del personaje
            String description = getArguments().getString("description", "");
            String skills = getArguments().getString("skills", "");
            // Convierte el nombre del recurso a un ID válido
            int imageResId = requireContext().getResources().getIdentifier(imageName, "drawable", requireContext().getPackageName());

            // Valida y muestra la imagen
            if (imageResId != 0) {
                binding.image.setImageResource(imageResId);
            } else {
                binding.image.setImageResource(R.drawable.placeholder); // Imagen por defecto
            }

            // Configura el texto que aparece en el detalle del personaje
            binding.name.setText(name);
            binding.description.setText(description);
            binding.skills.setText(skills);
            // Muestra un mensaje indicando el personaje que se ha seleccionado
            if (!name.isEmpty()) {
                Toast.makeText(requireContext(), getString(R.string.character_selected) + name, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

