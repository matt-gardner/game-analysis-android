package com.gardner.gameanalysis.androidclient;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        List<String> games = Arrays.asList(
                "Labyrinth",
                "Tic Tac Toe"
                );
        LinearLayout layout = (LinearLayout) findViewById(R.id.main_layout);
        for (String game : games) {
            Button button = new Button(this);
            button.setText(game);
            button.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                                                    ViewGroup.LayoutParams.WRAP_CONTENT));
            layout.addView(button);
        }
    }

    public void setHostAndPort(View view) {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText host = new EditText(this);
        host.setSingleLine();
        host.setText(prefs.getString("host", "localhost"));
        TextView hostView = new TextView(this);
        hostView.setText("\nHost:");
        layout.addView(hostView);
        layout.addView(host);
        final EditText port = new EditText(this);
        port.setSingleLine();
        port.setText(prefs.getInt("port", 2255));
        TextView portView = new TextView(this);
        portView.setText("\nPort:");
        layout.addView(portView);
        layout.addView(port);
        new AlertDialog.Builder(this)
                .setPositiveButton(android.R.string.ok, null)
                .setView(layout)
                .setTitle("Enter host and port")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("host", host.getText().toString());
                        editor.putInt("port", Integer.valueOf(port.getText().toString()));
                        editor.commit();
                    }
                })
                .create()
                .show();
    }
}
