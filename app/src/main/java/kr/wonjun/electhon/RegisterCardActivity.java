package kr.wonjun.electhon;

import android.view.View;
import android.widget.EditText;

import kr.wonjun.electhon.databinding.ActivityRegisterCardBinding;

public class RegisterCardActivity extends BaseActivity {
    ActivityRegisterCardBinding binding;

    @Override
    protected void setDefault() {
        setToolbarTitle("카드 등록");
        binding = (ActivityRegisterCardBinding) baseBinding;
        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNotEmpty(binding.cardBirth, binding.cardEndDate, binding.cardNum, binding.cardPassword1, binding.cardPassword2)) {
                    if (binding.cardBirth.getText().length() == 6
                            && binding.cardNum.getText().length() == 16
                            && binding.cardEndDate.getText().toString().replace("-", "").length() == 6) {
                        finish();
                    }
                }
            }
        });
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_register_card;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }

    public boolean isNotEmpty(EditText... editTexts) {
        for (EditText e : editTexts) {
            if (e.getText().toString().trim().equals("")) return false;
        }
        return true;
    }
}
