package ma.fstt.gnotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ModuleAdapter extends ArrayAdapter {

    Context context;
    int resource;
    List<Module> modules;

    public ModuleAdapter(@NonNull Context context, int resource, @NonNull List<Module> modules) {
        super(context, resource, modules);
        this.context= context;
        this.resource = resource;
        this.modules = modules;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row ;
        LayoutInflater rowInflater = LayoutInflater.from(context);
        row = rowInflater.inflate(resource , parent, false);
        TextView label = (TextView) row.findViewById(R.id.module);
        TextView note = (TextView) row.findViewById(R.id.note);
        Module module = modules.get(position);
        label.setText(module.getLabel().toString());
        note.setText(String.valueOf(module.getNote()));
        return row;


    }
}
