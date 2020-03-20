package com.example.thoughts;


import androidx.fragment.app.Fragment;

public class ThoughtsListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return ThoughtsListFragment.newInstance();
    }

}
