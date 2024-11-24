package lajara.lopez.pmdm.jlltarea2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import lajara.lopez.pmdm.jlltarea2.databinding.CharacterDetailFragmentBinding;


public class CharacterDetailFragment extends Fragment {

    private CharacterDetailFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        binding = CharacterDetailFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener datos del argumento que inicia este fragmento
        if (getArguments() != null) {
            String imageName = getArguments().getString("image2", ""); // Nombre del recurso como String
            String name = getArguments().getString("name", "");// Nombre del personaje
            String description= getArguments().getString("description","");
            String skills = getArguments().getString("skills","");
            // Convertir el nombre del recurso a un ID válido
            int imageResId = requireContext().getResources().getIdentifier(imageName, "drawable", requireContext().getPackageName());

            // Validar y mostrar la imagen
            if (imageResId != 0) {
                binding.image.setImageResource(imageResId);
            } else {
                binding.image.setImageResource(R.drawable.placeholder); // Imagen por defecto
            }

            // Configurar el texto del personaje
            binding.name.setText(name);
            binding.description.setText(description);
            binding.skills.setText(skills);

            if (!name.isEmpty()) {
                Toast.makeText(requireContext(), "Se ha seleccionado el personaje " + name, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

