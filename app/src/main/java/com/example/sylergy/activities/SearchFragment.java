package com.example.sylergy.activities;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.sylergy.R;
import com.example.sylergy.logs.LogException;
import com.example.sylergy.logs.Logs;
import com.example.sylergy.logs.LogsView;
import com.example.sylergy.objects.Context;
import com.example.sylergy.objects.Events;
import com.example.sylergy.presenter.Presenter;

public class SearchFragment extends Fragment implements UpdateActivity {
    private SearchView searchView;
    private String[] items = new String[] { "Search by name" };
    private int select=0;
    private AlertDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_name, container, false);
        searchView = view.findViewById(R.id.searchView);

        dialog = new AlertDialog.Builder(getActivity()).setTitle("Search by ...")
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        select=which;
                        setSearchBy();
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();

        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        if (items[select].equals("")) {
                            LogsView advise = new LogsView(Logs.NO_SEARCH_NAME);
                            advise.showInfo(getActivity());
                            return false;
                        } else {
                            // draw.show();
                            Presenter.getInstance()
                                    .action(new Context(Events.SEARCH_PRODUCT_NAME,
                                            query,
                                            SearchFragment.this));
                            return true;
                        }

                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                }
        );

        return view;
    }

    private void setSearchBy(){
        searchView.setQueryHint(items[select]);
    }

    @Override
    public void updateWithCommandResult(Context context) throws LogException {

    }
}
