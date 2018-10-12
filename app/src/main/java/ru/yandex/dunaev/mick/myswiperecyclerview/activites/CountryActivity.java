package ru.yandex.dunaev.mick.myswiperecyclerview.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.yandex.dunaev.mick.myswiperecyclerview.R;

public class CountryActivity extends AppCompatActivity {
    public static String EXTRA_COUNTRY_NAME = "ExtraCountryName";
    public static String EXTRA_COUNTRY_CODE = "ExtraCountryCode";
    public static String EXTRA_COUNTRY_FLAG_URL = "ExtraCountryFlagUrl";

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.flag) ImageView mFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        String countryName = getIntent().getStringExtra(EXTRA_COUNTRY_NAME);
        String countryCode = getIntent().getStringExtra(EXTRA_COUNTRY_CODE);
        String countryFlagUrl = getIntent().getStringExtra(EXTRA_COUNTRY_FLAG_URL);

        ButterKnife.bind(this);

        mToolbar.setTitle(countryName);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        SvgLoader.pluck()
                .with(this)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(countryFlagUrl, mFlag);

    }
}
