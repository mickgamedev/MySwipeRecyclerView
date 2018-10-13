package ru.yandex.dunaev.mick.myswiperecyclerview.activites;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmadrosid.svgloader.SvgLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.yandex.dunaev.mick.myswiperecyclerview.R;
import ru.yandex.dunaev.mick.myswiperecyclerview.model.Country;
import ru.yandex.dunaev.mick.myswiperecyclerview.utils.CountiesHelper;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.recycler) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        CountiesHelper.getInstance().getCountriesList().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if(response.body() == null) return;
                List<Country> countries = response.body();

                mRecyclerView.setAdapter(new RecyclerView.Adapter() {


                    @NonNull
                    @Override
                    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                        CardView cv = (CardView)LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.country_card,viewGroup,false);
                        return new RecyclerView.ViewHolder(cv) {};
                    }

                    @Override
                    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                        CardView cv = (CardView) viewHolder.itemView;
                        final String countryCode = countries.get(i).getCode();
                        final String countryName = countries.get(i).getCountryName();
                        String url = countries.get(i).getCountryFlagURL();

                        cv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this,CountryActivity.class);
                                intent.putExtra(CountryActivity.EXTRA_COUNTRY_NAME,countryName);
                                intent.putExtra(CountryActivity.EXTRA_COUNTRY_CODE,countryCode);
                                intent.putExtra(CountryActivity.EXTRA_COUNTRY_FLAG_URL,url);
                                startActivity(intent);
                            }
                        });
                        ImageView iv = (ImageView)cv.findViewById(R.id.country_flag);
                        TextView textCode = (TextView)cv.findViewById(R.id.country_code);
                        TextView textDesc = (TextView)cv.findViewById(R.id.country_description);

                        textCode.setText(countryCode);
                        textDesc.setText(countryName);


                        SvgLoader.pluck()
                                .with(MainActivity.this)
                                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                                .load(url, iv);
                    }

                    @Override
                    public int getItemCount() {
                        return countries.size();
                    }
                });
                ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                        int delIndex = viewHolder.getAdapterPosition();
                        countries.remove(delIndex);
                        mRecyclerView.getAdapter().notifyItemRemoved(delIndex);
                        mRecyclerView.getAdapter().notifyDataSetChanged();
                    }
                };
                new ItemTouchHelper(callback).attachToRecyclerView(mRecyclerView);
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SvgLoader.pluck().close();
    }
}
