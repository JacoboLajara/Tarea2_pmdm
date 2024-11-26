package lajara.lopez.pmdm.jlltarea2;

import androidx.recyclerview.widget.RecyclerView;

import lajara.lopez.pmdm.jlltarea2.databinding.CharacterCardviewBinding;

/**
 * ViewHolder para el RecyclerView que maneja cada tarjeta de personaje.
 * Se encarga de enlazar los datos del personaje con las vistas del diseño de la tarjeta.
 */
public class CharacterViewHolder extends RecyclerView.ViewHolder {

    // Enlace al diseño de la tarjeta de personaje
    private final CharacterCardviewBinding binding;

    /**
     * Constructor de la clase CharacterViewHolder.
     *
     * @param binding Objeto CharacterCardviewBinding que permite acceder a las vistas del diseño.
     */
    public CharacterViewHolder(CharacterCardviewBinding binding) {
        super(binding.getRoot()); // Obtiene la vista raíz del diseño enlazado
        this.binding = binding;
    }

    /**
     * Enlaza los datos de un personaje con las vistas del diseño de la tarjeta.
     *
     * @param character Objeto CharacterData que contiene los datos del personaje (nombre, imagen, etc.).
     */
    public void bind(CharacterData character) {
        // Asigna la imagen del personaje al ImageView usando su ID de recurso
        binding.image.setImageResource(character.getImage());

        // Establece el nombre del personaje en el TextView
        binding.name.setText(character.getName());

        // Asegura que se procesen cambios pendientes en el binding
        binding.executePendingBindings();
    }
}

