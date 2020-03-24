package com.yurisimao.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.yurisimao.festafimdeano.R;
import com.yurisimao.festafimdeano.constant.Constants;
import com.yurisimao.festafimdeano.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences msSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        msSecurityPreferences = new SecurityPreferences(this);
        this.mViewHolder.checkParticipate = findViewById(R.id.check_participate);
        this.mViewHolder.checkParticipate.setOnClickListener(this);

        this.loadDataFromActivity();

    }

    private void loadDataFromActivity() {

        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            final String presence = extras.getString(Constants.PRESENCE_KEY);
            if (presence != null && presence.equals(Constants.CONFIRMATION_YES)) {
                this.mViewHolder.checkParticipate.setChecked(true);
            }
        }
    }

    @Override
    public void onClick(final View v) {

        if (v.getId() == R.id.check_participate) {

            if (this.mViewHolder.checkParticipate.isChecked()) {
                this.msSecurityPreferences.storeString(Constants.PRESENCE_KEY, Constants.CONFIRMATION_YES);
            } else {
                this.msSecurityPreferences.storeString(Constants.PRESENCE_KEY, Constants.CONFIRMATION_NO);
            }

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private static class ViewHolder {
        private CheckBox checkParticipate;
    }
}
