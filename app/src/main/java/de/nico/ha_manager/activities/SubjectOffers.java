package de.nico.ha_manager.activities;

/* 
 * Author: Nico Alt
 * See the file "LICENSE.txt" for the full license governing this code.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

import de.nico.ha_manager.R;
import de.nico.ha_manager.helper.Subject;
import de.nico.ha_manager.helper.Utils;

public class SubjectOffers extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        update();
        Utils.setupActionBar(this, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void update() {
        String[] subOffers = getResources().getStringArray(
                R.array.subject_offers);
        Arrays.sort(subOffers);

        // Make simple list containing subjects
        ArrayAdapter<String> subAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, subOffers);

        ListView subList = (ListView) findViewById(R.id.listView_main);
        subList.setAdapter(subAdapter);

        subList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos,
                                    long id) {
                // Selected item
                final String item = ((TextView) v).getText().toString();

                AlertDialog.Builder deleteDialog = new AlertDialog.Builder(
                        SubjectOffers.this);
                deleteDialog
                        .setTitle(getString(R.string.action_add) + ": " + item)
                        .setPositiveButton((getString(android.R.string.yes)),
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface d, int i) {
                                        Subject.add(SubjectOffers.this, item);
                                    }
                                })
                        .setNegativeButton((getString(android.R.string.no)),
                                null).show();

            }

        });

    }

}
