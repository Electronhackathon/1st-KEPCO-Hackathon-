package kr.wonjun.electhon.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.nitrico.lastadapter.Holder;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;

import java.util.ArrayList;
import java.util.Collections;

import kr.wonjun.electhon.BR;
import kr.wonjun.electhon.ChangeDesignActivity;
import kr.wonjun.electhon.R;
import kr.wonjun.electhon.databinding.ContentSettingsBinding;
import kr.wonjun.electhon.databinding.ContentSettingsHeaderBinding;
import kr.wonjun.electhon.databinding.FragmentSettingsBinding;
import kr.wonjun.electhon.models.Settings;
import kr.wonjun.electhon.models.User;
import kr.wonjun.electhon.utils.CredentialsManager;
import kr.wonjun.electhon.utils.NetworkHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragment extends Fragment {
    FragmentSettingsBinding binding;
    LastAdapter adapter;
    ArrayList<Object> arrayList = new ArrayList<>();
    RecyclerView settingRecyclerView;

    public SettingsFragment() {
        Collections.addAll(arrayList,
                "내 프로필",
                new Settings("닉네임 변경", R.drawable.ic_settings_name, "payEphone에서 사용할 닉네임을 변경합니다."),
                "카드",
                new Settings("카드 디자인 변경", R.drawable.ic_settings_change, "사용하고 있는 카드의 디자인을 변경합니다."));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
        settingRecyclerView = binding.settingsRecyclerView;
        settingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LastAdapter(arrayList, BR.content)
                .map(String.class, new ItemType<ContentSettingsHeaderBinding>(R.layout.content_settings_header))
                .map(Settings.class, new ItemType<ContentSettingsBinding>(R.layout.content_settings) {
                    @Override
                    public void onBind(Holder<ContentSettingsBinding> holder) {
                        super.onBind(holder);
                        holder.getBinding().setActivity(SettingsFragment.this);
                    }
                }).into(settingRecyclerView);
        return binding.getRoot();
    }

    public void onListClicked(Settings item) {
        switch (item.getTitle()) {
            case "닉네임 변경":
                new MaterialDialog.Builder(getActivity())
                        .title("닉네임 변경")
                        .input("닉네임을 입력하세요",
                                CredentialsManager.getInstance().getActiveUser().second.getName(), false,
                                new MaterialDialog.InputCallback() {
                                    @Override
                                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                                        NetworkHelper.getNetworkInstance().updateNickname(
                                                CredentialsManager.getInstance().getActiveUser().second.getToken(),
                                                input.toString()
                                        ).enqueue(new Callback<User>() {
                                            @Override
                                            public void onResponse(Call<User> call, Response<User> response) {
                                                switch (response.code()) {
                                                    case 200:
                                                        Toast.makeText(getContext(), "닉네임이 성공적으로 업데이트되었습니다.", Toast.LENGTH_SHORT).show();
                                                        CredentialsManager.getInstance().updateUserInfo(response.body());
                                                        break;
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<User> call, Throwable t) {
                                                Log.e("asdf", t.getLocalizedMessage());
                                            }
                                        });
                                    }
                                })
                        .show();
                break;
            case "카드 디자인 변경":
                startActivity(new Intent(getActivity(), ChangeDesignActivity.class));
                break;
        }
    }
}
