package com.grupo.pinsa.libraries.components.design;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;

import com.grupo.pinsa.libraries.R;

/**
 * Created by Juan J. Palomera on 13/03/2019
 */

@BindingMethods({
        @BindingMethod(type = ExpandableList.class, attribute = "app:onGroupExpandListener", method = "setOnGroupExpandListener"),
        @BindingMethod(type = ExpandableList.class, attribute = "app:onGroupCollapseListener", method = "setOnGroupCollapseListener")
})
public class ExpandableList extends LinearLayout {
    private LinearLayout mContentView;
    private ExpandableListView expandableListView;

    public ExpandableList(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.grupopinsa_expandable_list, this);

        mContentView = findViewById(R.id.id_grupopinsa_detail_expandable_list_content);
        expandableListView = findViewById(R.id.id_grupopinsa_detail_expandable_list);
    }

    public void setAdapter(BaseExpandableListAdapter baseExpandableListAdapter) {
        expandableListView.setAdapter(baseExpandableListAdapter);
    }

    public void setOnGroupExpandListener(ExpandableListView.OnGroupExpandListener onGroupExpandListener) {
        expandableListView.setOnGroupExpandListener(onGroupExpandListener);
    }

    public void setOnGroupCollapseListener(ExpandableListView.OnGroupCollapseListener onGroupCollapseListener) {
        expandableListView.setOnGroupCollapseListener(onGroupCollapseListener);
    }
}
