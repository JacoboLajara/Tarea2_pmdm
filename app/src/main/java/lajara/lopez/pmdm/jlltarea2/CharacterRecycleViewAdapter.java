package lajara.lopez.pmdm.jlltarea2;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;

import lajara.lopez.pmdm.jlltarea2.databinding.CharacterCardviewBinding;


public class CharacterRecycleViewAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    private final ArrayList<CharacterData> characters;
    private final Context context;

    public CharacterRecycleViewAdapter(ArrayList<CharacterData> characters, Context context) {
        this.characters = characters;
        this.context = context;
    }
    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        CharacterCardviewBinding binding = CharacterCardviewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new CharacterViewHolder(binding);

    }

    public void onBindViewHolder (@NonNull CharacterViewHolder holder, int position){

        CharacterData currentCharacter = this.characters.get(position);
        holder.bind(currentCharacter);

        holder.itemView.setOnClickListener(view -> itemClicked(currentCharacter, view));

    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    private void itemClicked(CharacterData currentCharacter, View view) {
        ((MainActivity) context).characterClicked(currentCharacter, view);
    }


}
