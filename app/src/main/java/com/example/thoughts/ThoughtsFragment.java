package com.example.thoughts;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.UUID;

public class ThoughtsFragment extends Fragment {

    private static final String ARG_THOUGHT_ID = "thought_id";

    private Thought mThought;
    private EditText mTitleText;
    private EditText mContentText;

    public static ThoughtsFragment newInstance(UUID id) {
        
        Bundle args = new Bundle();
        args.putSerializable(ARG_THOUGHT_ID, id);
        ThoughtsFragment fragment = new ThoughtsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID thoughtId = (UUID) getArguments().getSerializable(ARG_THOUGHT_ID);
        mThought = ThoughtLab.get(getActivity()).getThought(thoughtId);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        ThoughtLab.get(getActivity()).updateThought(mThought);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_thoughts, container, false);

        mTitleText = (EditText) v.findViewById(R.id.thought_title);
        mTitleText.setText(mThought.getTitle());
        mTitleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mThought.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mContentText = (EditText) v.findViewById(R.id.thought_content);
        mContentText.setText(mThought.getContent());
        mContentText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mThought.setContent(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_thoughts, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_thought:
                ThoughtLab.get(getActivity()).deleteThought(mThought);
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
