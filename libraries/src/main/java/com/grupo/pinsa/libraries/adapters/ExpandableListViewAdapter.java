package com.grupo.pinsa.libraries.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;

import com.grupo.pinsa.libraries.R;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Juan J. Palomera on 13/03/2019
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private final ArrayList<View> parentList;
    private final Map<View, ArrayList<View>> childMap;
    private final Context context;

    public ExpandableListViewAdapter(ArrayList<View> parentList, Map<View, ArrayList<View>> childMap, Context context) {
        this.parentList = parentList;
        this.childMap = childMap;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return parentList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return Objects.requireNonNull(childMap.get(parentList.get(groupPosition))).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return Objects.requireNonNull(childMap.get(parentList.get(groupPosition))).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return parentList.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return Objects.requireNonNull(childMap.get(parentList.get(groupPosition))).get(childPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.grupopinsa_expandable_list_parent, null);

        if (((View) getGroup(groupPosition)).getParent() != null)
            ((ViewGroup) ((View) getGroup(groupPosition)).getParent()).removeAllViews();

        LinearLayout linearLayout = convertView.findViewById(R.id.id_grupopinsa_expandable_list_parent);
        linearLayout.addView((View) getGroup(groupPosition));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.grupopinsa_expandable_list_child, null);

        if (((View) getChild(groupPosition, childPosition)).getParent() != null)
            ((ViewGroup) ((View) getChild(groupPosition, childPosition)).getParent()).removeAllViews();

        LinearLayout linearLayout = convertView.findViewById(R.id.id_grupopinsa_expandable_list_child);
        linearLayout.addView((View) getChild(groupPosition, childPosition));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
