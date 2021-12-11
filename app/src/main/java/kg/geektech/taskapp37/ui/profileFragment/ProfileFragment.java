package kg.geektech.taskapp37.ui.profileFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.io.File;

import kg.geektech.taskapp37.Prefs;
import kg.geektech.taskapp37.R;
import kg.geektech.taskapp37.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private Uri image;
    private Prefs prefs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        prefs = new Prefs(requireContext());

        return view;

    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri uri) {
                    image = uri;

                    Glide.with(requireContext()).load(image).into(binding.imageView);
                    prefs.saveImage(image);
                }
            });

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        find();
        logicEditText();
        conclusion();
    }

    private void conclusion() {
        if (prefs.getImage().equals(""))
            binding.imageView.setImageResource(R.color.cardview_dark_background);
        else {
            image = Uri.parse(prefs.getImage());
            Glide.with(requireContext()).load(image).into(binding.imageView);
        }
        binding.editText.setText(prefs.getEditText());
    }

    private void logicEditText() {
        binding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                prefs.saveEditText(editable.toString());

            }
        });
    }


    private void find() {

        binding.imageView.setOnClickListener(view -> {
            if (image == null) {

                mGetContent.launch("image/*");

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setNegativeButton("Swap", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mGetContent.launch("image/*");
                    }
                });
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        prefs.clearIMAGEUri();
                        image = null;
                        binding.imageView.setImageResource(R.color.cardview_dark_background);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });
    }

}