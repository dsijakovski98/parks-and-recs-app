package com.example.parksandrecs;

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


public class LoginFragment extends Fragment {

    EditText usernameInput;
    EditText passwordInput;

    TextView errorMessage;

    public LoginFragment() {
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        usernameInput = getActivity().findViewById(R.id.username_login);
        passwordInput = getActivity().findViewById(R.id.password_login);

        errorMessage = getActivity().findViewById(R.id.tv_error_message_login);

        Button loginBtn = getActivity().findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(v -> validateLogin());

    }

    private void validateLogin() {
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

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

        // TODO: Implement database
        // Check for existing input
        // if username exists
        if(!usernameExists(username)) {
            errorInput(usernameInput, R.string.username_doesnt_exist);
            return;
        }

        // and if password is correct, sign user in
        if(!correctPassword(username, password)) {
            errorInput(passwordInput, R.string.password_incorrect);
            return;
        }
        // =============================================================

        // Valid login at this point
        // 1. User persistent data
        CurrentUserManager.logIn(username, getActivity());

        // 2. Go to cities activity
        Intent goToCitiesIntent = new Intent(getActivity(), CitiesActivity.class);
        startActivity(goToCitiesIntent);
        getActivity().finish();
    }

    private boolean correctPassword(String username, String password) {
        MyDatabase handler = new MyDatabase(getActivity());
        return handler.checkCorrectPassword(username, password);
    }

    private boolean usernameExists(String username) {
        MyDatabase handler = new MyDatabase(getActivity());
        return handler.checkUsernameTaken(username);
    }

    private void clearErrorInput() {
        ColorStateList colorStateList = ColorStateList.valueOf(R.attr.hintTextColor);
        usernameInput.setBackgroundTintList(colorStateList);
        passwordInput.setBackgroundTintList(colorStateList);
    }

    private void errorInput(EditText input, int errorCode) {
        ColorStateList colorStateList = ColorStateList.valueOf(getActivity().getResources().getColor(R.color.redish));
        input.setBackgroundTintList(colorStateList);
        errorMessage.setText(errorCode);
    }


}