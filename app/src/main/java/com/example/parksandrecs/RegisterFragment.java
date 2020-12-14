package com.example.parksandrecs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterFragment extends Fragment {

    private EditText usernameInput;
    private EditText passwordInput;
    private EditText passwordConfirmInput;

    private TextView errorMessage;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        usernameInput = getActivity().findViewById(R.id.username_register);
        passwordInput = getActivity().findViewById(R.id.password_register);
        passwordConfirmInput = getActivity().findViewById(R.id.password_register_confirm);

        errorMessage = getActivity().findViewById(R.id.tv_error_message_register);

        Button registerButton = getActivity().findViewById(R.id.register_btn);
        registerButton.setOnClickListener(v -> validateRegister());
    }

    private void validateRegister() {
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        String passwordConfirm = passwordConfirmInput.getText().toString();

        // Clear error messages
        clearErrorInput();

        // Empty fields
        if(username.equals("")) {
            errorInput(usernameInput, R.string.enter_username);
            return;
        }
        if(password.equals("")) {
            errorInput(passwordInput, R.string.enter_password);
            return;
        }

        // Too short fields
        if(username.length() < 4) {
            errorInput(usernameInput, R.string.short_username);
            return;
        }
        if(password.length() < 6) {
            errorInput(passwordInput, R.string.short_password);
            return;
        }

        // Passwords match
        if(!password.equals(passwordConfirm)) {
            errorInput(passwordConfirmInput, R.string.passwords_not_matching);
            return;
        }

        // TODO: Implement database
        // Check for existing fields

        // =============================================================


        // Valid registration entry at this point
        // 1. User persistent data
        CurrentUserManager.logIn(usernameInput, getActivity());

        // 2. Go to cities activity
        Intent goToCitiesIntent = new Intent(getActivity(), CitiesActivity.class);
        startActivity(goToCitiesIntent);
        getActivity().finish();
    }

    private void clearErrorInput() {
        ColorStateList colorStateList = ColorStateList.valueOf(R.attr.hintTextColor);
        usernameInput.setBackgroundTintList(colorStateList);
        passwordInput.setBackgroundTintList(colorStateList);
        passwordConfirmInput.setBackgroundTintList(colorStateList);
    }

    private void errorInput(EditText input, int errorCode) {
        ColorStateList colorStateList = ColorStateList.valueOf(getActivity().getResources().getColor(R.color.redish));
        input.setBackgroundTintList(colorStateList);
        errorMessage.setText(errorCode);
    }
}