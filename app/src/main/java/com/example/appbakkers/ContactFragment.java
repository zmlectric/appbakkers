package com.example.appbakkers;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {

    private EditText Naam;
    private EditText Email;
    private EditText Telefoonnummer;
    private EditText Onderwerp;
    private EditText Bericht;
    private Button Verzenden;
    private ProgressBar progressBar;

    public ContactFragment() {

        // Required empty public constructor
    }

    public void toggleProgressIndicator()
    {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progressBar.getVisibility() == View.VISIBLE) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.contact, container, false);

        ImageButton contact_button = view.findViewById(R.id.contact_button);
        contact_button.setVisibility(View.GONE);

        String text= getString(R.string.contact);
        TextView myTextView = view.findViewById(R.id.pagina_titel);
        myTextView.setText(text);

        progressBar = view.findViewById(R.id.pbLoading);
        Naam = view.findViewById(R.id.contact_naam);
        Email = (EditText)view.findViewById(R.id.contact_email);
        Telefoonnummer = (EditText)view.findViewById(R.id.contact_telefoonnummer);
        Onderwerp = (EditText)view.findViewById(R.id.contact_onderwerp);
        Bericht = (EditText)view.findViewById(R.id.contact_bericht);
        Verzenden = (Button)view.findViewById(R.id.verzenden_button);

        Verzenden.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(final View view) {
               String ContactNaam = Naam.getText().toString();
               String ContactEmail = Email.getText().toString();
               String ContactTelefoonnummer = Telefoonnummer.getText().toString();
               String ContactOnderwerp = Onderwerp.getText().toString();
               String ContactBericht = Bericht.getText().toString();

               if (TextUtils.isEmpty(ContactNaam)){
                   Naam.setError("Vul een naam in");
                   Naam.requestFocus();
                   return;
               }

               Boolean onError = false;
               if (!isValidEmail(ContactEmail)) {
                   onError = true;
                   Email.setError("Ongeldig emailadres");
                   return;
               }

               if (TextUtils.isEmpty(ContactTelefoonnummer)){
                   Telefoonnummer.setError("Vul een telefoonnummer in");
                   Telefoonnummer.requestFocus();
                   return;
               }

               if (TextUtils.isEmpty(ContactOnderwerp)){
                   Onderwerp.setError("Vul een onderwerp in");
                   Onderwerp.requestFocus();
                   return;
               }

               if (TextUtils.isEmpty(ContactBericht)){
                   Bericht.setError("Vul een bericht in");
                   Bericht.requestFocus();
                   return;
               }

               toggleProgressIndicator();
               Thread thread = new Thread(new Runnable() {

                   @Override
                   public void run() {
                       try {

                           RequestBody formBody = new FormBody.Builder()
                                   .add("naam", Naam.getText().toString())
                                   .add("email", Email.getText().toString())
                                   .add("telefoonnummer", Telefoonnummer.getText().toString())
                                   .add("onderwerp", Onderwerp.getText().toString())
                                   .add("bericht", Bericht.getText().toString())

                                   .build();
                           Request request = new Request.Builder()
                                   .url("https://api.appbakkers.nl/api/mail")
                                   .post(formBody)
                                   .build();

                           OkHttpClient client = new OkHttpClient();

                           try {
                               Response Response = client.newCall(request).execute();

                               if (Response.isSuccessful()) {

                                   getActivity().runOnUiThread(new Runnable() {
                                       @Override
                                       public void run() {
                                           // ALERT DIALOG HIER
                                        createAlertDialog();
//                                           Toast.makeText(getContext(), "Je bericht is verstuurd!", Toast.LENGTH_LONG).show();
                                       }

//                                       void draw() {}

                                       void createAlertDialog() {
                                           //Create the AlertDialog
                                           AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                                           //Set the title
//                                           alertDialog.setTitle("Alert");
                                           //Set the message
                                           alertDialog.setMessage("Je bericht is verstuurd!");
                                           //Add the OK button
                                           alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                                               public void onClick(DialogInterface dialog, int which) {
                                                   Naam.setText("");
                                                   Email.setText("");
                                                   Telefoonnummer.setText("");
                                                   Onderwerp.setText("");
                                                   Bericht.setText("");
                                                   //The OK button has been clicked
                                               }});
                                           //Display the AlertDialog
                                           alertDialog.show();
                                       }
                                   });

                               }
                            else {
                                // toast met versturen werkt niet
                               }
                           } catch(IOException e){
                               e.printStackTrace();
                               // alert dialog tonen voor als verzenden fout gaat
                           }
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                       finally {
                           // stop progress indicator
                           toggleProgressIndicator();
                       }
                   }
               });

               thread.start();
           }
        });

        return view;
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}