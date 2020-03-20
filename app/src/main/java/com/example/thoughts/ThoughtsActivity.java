package com.example.thoughts;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;

import java.util.UUID;

public class ThoughtsActivity extends SingleFragmentActivity {

    private static final String EXTRA_THOUGHT_ID = "thought_id";


    public static Intent newIntent(Context context, UUID id) {
        Intent intent = new Intent(context, ThoughtsActivity.class);
        intent.putExtra(EXTRA_THOUGHT_ID, id);
        return intent;

    }

    @Override
    protected Fragment createFragment() {
        UUID id = (UUID) getIntent().getSerializableExtra(EXTRA_THOUGHT_ID);
        return ThoughtsFragment.newInstance(id);
    }
}
