package lajara.lopez.pmdm.jlltarea2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import lajara.lopez.pmdm.jlltarea2.databinding.CharacterCardviewBinding;

/**
 * Adaptador para el RecyclerView que muestra una lista de personajes.
 * Este adaptador gestiona cómo se enlazan los datos de los personajes con las vistas individuales
 * dentro del RecyclerView.
 */
public class CharacterRecycleViewAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    // Lista de datos de los personajes
    private final ArrayList<CharacterData> characters;

    // Contexto de la actividad o fragmento donde se utiliza el adaptador
    private final Context context;

    /**
     * Constructor del adaptador.
     *
     * @param characters Lista de personajes que se mostrará en el RecyclerView.
     * @param context    Contexto donde se utiliza el adaptador (normalmente una actividad).
     */
    public CharacterRecycleViewAdapter(ArrayList<CharacterData> characters, Context context) {
        this.characters = characters;
        this.context = context;
    }

    /**
     * Crea un nuevo ViewHolder para el RecyclerView.
     *
     * @param parent   Vista padre (generalmente el RecyclerView) donde se añadirá el ViewHolder.
     * @param viewType Tipo de vista (útil para vistas con diseños diferentes, aunque no se usa aquí).
     * @return Una instancia de CharacterViewHolder con el diseño inflado.
     */
    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla el diseño de cada tarjeta de personaje
        CharacterCardviewBinding binding = CharacterCardviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CharacterViewHolder(binding);
    }

    /**
     * Enlaza los datos de un personaje con un ViewHolder.
     * Configura los datos del personaje en las vistas de la tarjeta y añade un listener de clic.
     *
     * @param holder   El ViewHolder que se está configurando.
     * @param position La posición del personaje en la lista.
     */
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        // Obtiene el personaje actual según la posición
        CharacterData currentCharacter = this.characters.get(position);

        // Enlaza los datos del personaje con el ViewHolder
        holder.bind(currentCharacter);

        // Configura un listener de clic para la tarjeta del personaje
        holder.itemView.setOnClickListener(view -> itemClicked(currentCharacter, view));
    }

    /**
     * Devuelve el número total de personajes en la lista.
     *
     * @return El tamaño de la lista de personajes.
     */
    @Override
    public int getItemCount() {
        return characters.size();
    }

    /**
     * Maneja el evento de clic en un personaje.
     * Llama a un método en la actividad principal para gestionar el clic.
     *
     * @param currentCharacter El personaje que fue clicado.
     * @param view             La vista que fue clicada.
     */
    private void itemClicked(CharacterData currentCharacter, View view) {
        ((MainActivity) context).characterClicked(currentCharacter, view);
    }
}

