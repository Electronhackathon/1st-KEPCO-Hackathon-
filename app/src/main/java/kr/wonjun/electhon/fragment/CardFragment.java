package kr.wonjun.electhon.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;

import kr.wonjun.electhon.HWPayInfoActivity;
import kr.wonjun.electhon.R;
import kr.wonjun.electhon.RegisterCardActivity;
import kr.wonjun.electhon.databinding.FragmentCardBinding;
import kr.wonjun.electhon.databinding.FragmentSettingsBinding;
import kr.wonjun.electhon.models.Card;
import kr.wonjun.electhon.utils.CredentialsManager;

import static kr.wonjun.electhon.R.layout.fragment_card;

public class CardFragment extends Fragment {
    FragmentCardBinding binding;
    Card card = null;

    public CardFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, fragment_card, container, false);
        card = CredentialsManager.getInstance().getCardInfo();
        if (card == null) {
            binding.emptyContainer.setVisibility(View.VISIBLE);
            binding.nonEmptyContainer.setVisibility(View.GONE);
        } else {
            binding.emptyContainer.setVisibility(View.GONE);
            binding.nonEmptyContainer.setVisibility(View.VISIBLE);
            binding.cardNum.setText("" + card);
            binding.cardContainer.setBackground(new ColorDrawable(Color.parseColor(
                    CredentialsManager.getInstance().getColorBackground()
            )));
        }
        binding.emptyContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RegisterCardActivity.class);
                startActivity(intent);
            }
        });
        binding.charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (binding != null) {
            card = CredentialsManager.getInstance().getCardInfo();
            if (card == null) {
                binding.emptyContainer.setVisibility(View.VISIBLE);
                binding.nonEmptyContainer.setVisibility(View.GONE);
            } else {
                binding.emptyContainer.setVisibility(View.GONE);
                binding.nonEmptyContainer.setVisibility(View.VISIBLE);
                binding.cardNum.setText("" + card);
                binding.cardContainer.setBackground(new ColorDrawable(Color.parseColor(
                        CredentialsManager.getInstance().getColorBackground()
                )));
            }
        }
    }
}
