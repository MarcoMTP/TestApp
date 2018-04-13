package com.example.encuentas.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.encuentas.R;
import com.example.encuentas.listener.InteractionListener;


public class EncuestaFragment extends Fragment implements View.OnClickListener{

private EditText mEditText;
private InteractionListener mListener;

    public static EncuestaFragment newInstance() {
        EncuestaFragment fragment = new EncuestaFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_encuesta, container, false);
        mEditText = (EditText) view.findViewById(R.id.fragment_our_edit_text);
        Button button = (Button) view.findViewById(R.id.fragment_our_button);
        button.setOnClickListener(this);
        return view;
    }
    public void sentString() {
        mListener.onFragmentInteraction(mEditText.getText().toString());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InteractionListener) {
            mListener = (InteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement InteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        sentString();
    }
}
