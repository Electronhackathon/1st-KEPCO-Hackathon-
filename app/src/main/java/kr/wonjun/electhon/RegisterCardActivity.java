package kr.wonjun.electhon;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import kr.wonjun.electhon.databinding.ActivityRegisterCardBinding;
import kr.wonjun.electhon.models.Card;
import kr.wonjun.electhon.utils.CredentialsManager;
import kr.wonjun.electhon.utils.NetworkHelper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                    if (binding.cardBirth.getText().length() == 6 &&
                            binding.cardNum.getText().length() == 19 &&
                            binding.cardEndDate.getText().toString().replace("-", "").length() == 6) {
                        NetworkHelper.getNetworkInstance().addCard(CredentialsManager.getInstance().getActiveUser().second.getToken(),
                                binding.cardNum.getText().toString(),
                                binding.cardPassword1.getText().toString() + binding.cardPassword2.getText().toString(),
                                binding.cardBirth.getText().toString(), binding.cardEndDate.getText().toString()).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.code() == 200) {
                                    Toast.makeText(getApplicationContext(), "카드 등록 성공", Toast.LENGTH_SHORT).show();
                                    Card card = new Card(CredentialsManager.getInstance().getActiveUser().second.getToken(),
                                            binding.cardNum.getText().toString(),
                                            binding.cardPassword1.getText().toString() + binding.cardPassword2.getText().toString(),
                                            binding.cardBirth.getText().toString(), binding.cardEndDate.getText().toString());
                                    CredentialsManager.getInstance().saveCardInfo(card);
                                    Log.e("asdf", "" + CredentialsManager.getInstance().getCardInfo().getCardNum());
                                } else {
                                    Toast.makeText(getApplicationContext(), "카드 등록 실패", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "카드 등록 실패", Toast.LENGTH_SHORT).show();
                            }
                        });
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
