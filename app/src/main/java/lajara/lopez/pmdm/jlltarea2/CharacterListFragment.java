package lajara.lopez.pmdm.jlltarea2;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import lajara.lopez.pmdm.jlltarea2.databinding.CharacterListFragmentBinding;

public class CharacterListFragment extends Fragment {
    private CharacterListFragmentBinding binding; //binding para el layout
    private ArrayList<CharacterData> characters;//lista de personajes
    private CharacterRecycleViewAdapter adapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding= CharacterListFragmentBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //inicializa la lista de personajes
        loadCharacters();

        // configure  el RecyclerView
        adapter= new CharacterRecycleViewAdapter(characters, getActivity());

        binding.characterRecyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.characterRecyclerview.setAdapter(adapter);

    }

    private void loadCharacters() {
        characters = new ArrayList<CharacterData>();
        characters.add(new CharacterData(
                R.drawable.yoshi,
                R.drawable.yoshi_p,
                "Yoshi",
                "Adorable dinosaurio verde",
                "Traga enemigos"

        ));
        characters.add(new CharacterData(
                R.drawable.mario,
                R.drawable.supermario_p,
                "Mario",
                "LLeva peto azul y la inicial M en su gorra",
                "Salta y lanza bolas de fuego"

        ));

        characters.add(new CharacterData(
                R.drawable.peach,
                R.drawable.peach_p,
                "Peach",
                "Es la princesa del Reino Champinon",
                "Valentia y astucia"

        ));
        characters.add(new CharacterData(
                R.drawable.luigi,
                R.drawable.luigi_p,
                "Luigi",
                "Luigi es el hermano menor de Mario y su compi",
                "Puede saltar mas alto que Mario"

        ));

        Snackbar.make(binding.getRoot(), "Bienvenidos al mundo de Mario", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getActivity() != null ) {

                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.lista_de_personajes);

        }
    }
}
