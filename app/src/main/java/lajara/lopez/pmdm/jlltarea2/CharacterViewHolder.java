package lajara.lopez.pmdm.jlltarea2;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import lajara.lopez.pmdm.jlltarea2.databinding.CharacterCardviewBinding;

public class CharacterViewHolder extends RecyclerView.ViewHolder {

    private final CharacterCardviewBinding binding;

    public CharacterViewHolder(CharacterCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }


    public void bind(CharacterData character) {
        // Asignar la imagen al ImageView usando el ID del recurso
        binding.image.setImageResource(character.getImage());

        // Configurar el texto del personaje
        binding.name.setText(character.getName());

        // Asegura que los cambios en el binding se procesen
        binding.executePendingBindings();
    }
}
