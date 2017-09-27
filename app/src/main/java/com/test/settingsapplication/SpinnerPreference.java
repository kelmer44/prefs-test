package com.test.settingsapplication;

/**
 * Created by gabriel on 27/09/2017.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceViewHolder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;


/**
 * Created by gabriel on 29/05/2017.
 */

public class SpinnerPreference extends Preference {
    private int mSelection = 0;


    protected String[] mEntries = new String[0];
    protected String[] mEntryValues = new String[0];
    private LayoutInflater mLayoutInflater;

    public SpinnerPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setWidgetLayoutResource(R.layout.spinner_preference);
        init(context, attrs);
    }

    public SpinnerPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWidgetLayoutResource(R.layout.spinner_preference);
        init(context, attrs);
    }

    public SpinnerPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    private void init(Context context, AttributeSet attrs) {
        mLayoutInflater = LayoutInflater.from(getContext());
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SpinnerPreference);
        int entriesResId = ta.getResourceId(R.styleable.SpinnerPreference_entries, 0);
        if (entriesResId != 0) {
            mEntries = context.getResources().getStringArray(entriesResId);
        }
        int valuesResId = ta.getResourceId(R.styleable.SpinnerPreference_entryValues, 0);
        if (valuesResId != 0) {
            mEntryValues = context.getResources().getStringArray(valuesResId);
        }
        ta.recycle();
    }


    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        final Spinner spinner = (Spinner) holder.findViewById(R.id.spinner);
        spinner.setSelection(mSelection);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.performClick();
            }
        });
        spinner.setAdapter(new SpinnerAdapter() {

            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getCount() {
                return mEntries.length;
            }

            @Override
            public Object getItem(int i) {
                return mEntries[i];
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {
                return getDropDownView(position, view, viewGroup);
            }

            @Override
            public int getItemViewType(int i) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public boolean isEmpty() {
                return mEntries.length == 0;
            }

            @Override
            public View getDropDownView(int position, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = createDropDownView(position, viewGroup);
                }
                bindDropDownView(position, view);
                return view;
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                mSelection = position;
                persistString(mEntryValues[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    protected View createDropDownView(int position, ViewGroup parent) {
        return mLayoutInflater.inflate(R.layout.spinner_pref_dropdown, parent, false);
    }

    protected void bindDropDownView(int position, View view) {
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(mEntries[position]);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getString(index);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        super.onSetInitialValue(restorePersistedValue, defaultValue);
        String value = restorePersistedValue ? getPersistedString(null) : (String) defaultValue;
        for (int i = 0; i < mEntryValues.length; i++) {
            if (TextUtils.equals(mEntryValues[i], value)) {
                mSelection = i;
                break;
            }
        }
    }



}
