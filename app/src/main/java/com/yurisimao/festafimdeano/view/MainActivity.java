package com.yurisimao.festafimdeano.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yurisimao.festafimdeano.R;
import com.yurisimao.festafimdeano.constant.Constants;
import com.yurisimao.festafimdeano.data.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final SimpleDateFormat MASK = new SimpleDateFormat("dd/MM/yyyy");

    private ViewHolder mViewHolder = new ViewHolder();

    private SecurityPreferences msSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDaysLeft = findViewById(R.id.text_days_left);
        this.mViewHolder.buttonConfirmation = findViewById(R.id.button_confirmation);

        this.mViewHolder.buttonConfirmation.setOnClickListener(this);
        this.mViewHolder.textToday.setText(MASK.format(Calendar.getInstance().getTime()));
        this.mViewHolder.textDaysLeft.setText(String.format("%s %s", String.valueOf(this.getDaysLeft()), getString(R.string.days)));

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();
    }

    @Override
    public void onClick(final View v) {

        if (v.getId() == R.id.button_confirmation) {

            final String presence = this.msSecurityPreferences.getStoredString(Constants.PRESENCE_KEY);

            // Chamando outra activity
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(Constants.PRESENCE_KEY, presence);
            startActivity(intent);

        }

    }

    private int getDaysLeft() {

        final int today = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        final int dayMax = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_YEAR);

        return dayMax - today;

    }

    private void verifyPresence() {

        final String presence = this.msSecurityPreferences.getStoredString(Constants.PRESENCE_KEY);

        if (presence.isEmpty()){
            this.mViewHolder.buttonConfirmation.setText(getString(R.string.nao_confirmado));
        } else if (Constants.CONFIRMATION_YES.equals(presence)) {
            this.mViewHolder.buttonConfirmation.setText(getString(R.string.sim));
        } else {
            this.mViewHolder.buttonConfirmation.setText(getString(R.string.nao));
        }

    }

    private static class ViewHolder {
        private TextView textToday;
        private TextView textDaysLeft;
        private Button buttonConfirmation;
    }
}

