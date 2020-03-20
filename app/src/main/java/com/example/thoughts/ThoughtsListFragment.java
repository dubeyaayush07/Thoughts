package com.example.thoughts;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ThoughtsListFragment extends Fragment {
    private RecyclerView mThoughtRecycler;
    private ThoughtAdapter mAdapter;

    public static ThoughtsListFragment newInstance() {
        return new ThoughtsListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_thoughts_list, container, false);
        mThoughtRecycler = (RecyclerView) v.findViewById(R.id.thought_recycler);
        mThoughtRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return v;
    }

    private void updateUI() {
        List<Thought> mThoughts = ThoughtLab.get(getActivity()).getThoughts();

        if (mAdapter == null) {
            mAdapter = new ThoughtAdapter(mThoughts);
            mThoughtRecycler.setAdapter(mAdapter);
        } else {
            mAdapter.setThoughts(mThoughts);
            mAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_thoughts_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_thought:
                Thought thought = new Thought();
                ThoughtLab.get(getActivity()).addThought(thought);
                Intent intent = ThoughtsActivity.newIntent(getActivity(), thought.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class ThoughtHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Thought mThought;
        private TextView mTitleTextView;


        public void bind(Thought thought) {
            mThought = thought;
            mTitleTextView.setText(thought.getTitle());
        }

        public ThoughtHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_thought, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.thought_list_title);
        }

        @Override
        public void onClick(View v) {
            Intent intent = ThoughtsActivity.newIntent(getActivity(), mThought.getId());
            startActivity(intent);
        }
    }

    private class ThoughtAdapter extends RecyclerView.Adapter<ThoughtHolder> {

        private List<Thought> mThoughts;

        public ThoughtAdapter(List<Thought> thoughts) {
            mThoughts = thoughts;
        }

        @NonNull
        @Override
        public ThoughtHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new ThoughtHolder(inflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull ThoughtHolder thoughtHolder, int i) {
            Thought thought = mThoughts.get(i);
            thoughtHolder.bind(thought);
        }

        @Override
        public int getItemCount() {
            return mThoughts.size();
        }

        public void setThoughts(List<Thought> thoughts) {
            mThoughts = thoughts;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
