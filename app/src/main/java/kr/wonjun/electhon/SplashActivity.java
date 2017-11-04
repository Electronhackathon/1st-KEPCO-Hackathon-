package kr.wonjun.electhon;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import kr.wonjun.electhon.databinding.ActivitySplashBinding;
import kr.wonjun.electhon.models.User;
import kr.wonjun.electhon.utils.CredentialsManager;
import kr.wonjun.electhon.utils.NetworkHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {

    ActivitySplashBinding binding;

    @Override
    protected void setDefault() {
        binding = (ActivitySplashBinding) baseBinding;
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AuthActivity.class));
                finish();
            }
        });
        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                finish();
            }
        });
        Log.e("asdf1", CredentialsManager.getInstance().getActiveUser().first + "");
        Log.e("asdf1", CredentialsManager.getInstance().getActiveUser().second.getEmail() + "");
        Log.e("asdf1", CredentialsManager.getInstance().getActiveUser().second.getName() + "");
        Log.e("asdf1", CredentialsManager.getInstance().getActiveUser().second.getPw() + "");
        Log.e("asdf1", CredentialsManager.getInstance().getActiveUser().first + "");

        if (CredentialsManager.getInstance().getActiveUser().first) {
            NetworkHelper.getNetworkInstance().loginLocal(CredentialsManager.getInstance().getActiveUser().second.getEmail()
                    , CredentialsManager.getInstance().getActiveUser().second.getPw()).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.e("Asdf", "" + response.message());
                    switch (response.code()) {
                        case 200:
                            CredentialsManager.getInstance().updateUserInfo(response.body());
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            Toast.makeText(SplashActivity.this, response.body().getName() + "님 환영합니다!", Toast.LENGTH_SHORT).show();
                            finish();
                            break;
                        default:
                            showUI();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("asdf", t.getLocalizedMessage());
                }
            });
        } else showUI();
    }


    private void showUI() {
        binding.buttonContainer.setVisibility(View.VISIBLE);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return 0;
    }
}
