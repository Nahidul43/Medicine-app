package com.example.medical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class ShowDetails extends AppCompatActivity {

    TextInputEditText genericName, precaution, contra_Indication, dose, side_effect, madeOfAction, interaction, indication;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        genericName = findViewById(R.id.genericName);
        precaution = findViewById(R.id.precaution);
        contra_Indication = findViewById(R.id.contra_Indication);
        dose = findViewById(R.id.dose);
        side_effect = findViewById(R.id.side_effect);
        madeOfAction = findViewById(R.id.madeOfAction);
        interaction = findViewById(R.id.interaction);
        indication = findViewById(R.id.indication);
        helper = new DatabaseHelper(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");

        Cursor cursor = helper.ShowDetails(id);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToNext();
            // Retrieve values from the cursor
            String genericNameValue = cursor.getString(1);
            String precautionValue = cursor.getString(2);
            String indicationValue = cursor.getString(3);
            String contra_IndicationValue = cursor.getString(4);
            String doseValue = cursor.getString(5);
            String side_effectValue = cursor.getString(6);
            String madeOfActionValue = cursor.getString(8);
            String interactionValue = cursor.getString(9);

            // Set the values to the respective EditText fields
            genericName.setText(genericNameValue);
            precaution.setText(precautionValue);
            indication.setText(indicationValue);
            contra_Indication.setText(contra_IndicationValue);
            dose.setText(doseValue);
            side_effect.setText(side_effectValue);
            madeOfAction.setText(madeOfActionValue);
            interaction.setText(interactionValue);
        }
    }
}
