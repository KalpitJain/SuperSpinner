package com.creotix.superspinner;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class SuperSpinner extends RelativeLayout {
    private Spinner spinner;
    private EditText editText;
    private SuperSpinnerAdapter superSpinnerAdapter;

    private String label = "SuperSpinner";

    public SuperSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.super_spinner, SuperSpinner.this, true);

        editText = (EditText) this.findViewById(R.id.edittext);
        spinner = (Spinner) this.findViewById(R.id.spinner);
        superSpinnerAdapter = new SuperSpinnerAdapter(getContext(), R.layout.textview);

        spinner.setAdapter(superSpinnerAdapter);

        editText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.performClick();
            }
        });

        setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        init(getLabel());
    }

    public void init(String label) {
        this.label = label;
        ((TextInputLayout) this.findViewById(R.id.textinputlayout)).setHint(getLabel());
    }

    public void setError(CharSequence error) {
        editText.setError(error);
    }

    public int getSelectedItemPosition() {
        if (spinner.getSelectedItemPosition() == 0) {
            return -1;
        } else {
            return spinner.getSelectedItemPosition();
        }
    }

    public void setOnItemSelectedListener(final AdapterView.OnItemSelectedListener onItemSelectedListener) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editText.setError(null);
                if (position != 0) {
                    editText.setText(((TextView) view).getText());
                } else {
                    editText.setText("");
                }
                onItemSelectedListener.onItemSelected(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                onItemSelectedListener.onNothingSelected(parent);
            }
        });
    }

    public void setSelection(int position) {
        spinner.setSelection(position, true);
    }

    public View getSelectedView() {
        if (getSelectedItemPosition() < 0) {
            return null;
        } else {
            return spinner.getSelectedView();
        }
    }

    public void add(SpinnerItem spinnerItem) {
        superSpinnerAdapter.addAll(spinnerItem);
    }

    public void addAll(List<SpinnerItem> spinnerItems) {
        superSpinnerAdapter.addAll(spinnerItems);
    }

    private String getLabel() {
        return label;
    }

    private class SuperSpinnerAdapter extends ArrayAdapter<SpinnerItem> {

        public SuperSpinnerAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.textview, null, false);
            }
            if (position == 0) {
                ((TextView) convertView).setText(getLabel());
                convertView.setTag(null);
            } else {
                ((TextView) convertView).setText(getItem(position).getDisplayText());
                convertView.setTag(getItem(position).getId());
            }
            return convertView;
        }

        @Override
        public int getCount() {
            return super.getCount() + 1;
        }

        @Override
        public SpinnerItem getItem(int position) {
            if (position == 0) {
                return null;
            }
            return super.getItem(position - 1);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getDropDownView(position, convertView, parent);
        }
    }

    public static class SpinnerItem implements Serializable {
        private String displayText, id;

        public SpinnerItem() {
        }

        public SpinnerItem(String displayText, String id) {
            this.displayText = displayText;
            this.id = id;
        }

        public void setDisplayText(String displayText) {
            this.displayText = displayText;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDisplayText() {
            return this.displayText;
        }

        public String getId() {
            return this.id;
        }
    }
}
