package cn.shopin.oneposition.activities.register;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import cn.shopin.oneposition.R;
import cn.shopin.oneposition.activities.BaseMvpActivity;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends BaseMvpActivity<RegisterPresenter> implements RegisterContract.IRegisterView {

    @BindView(R.id.username)
    EditText mEtUserName;
    @BindView(R.id.userpwd)
    EditText mEtUserPwd;
    @BindView(R.id.identifyCode)
    EditText mEtIdentifyCode;
    private String identifyCode;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initEventAndData() {
        init();
    }

    @OnClick(R.id.btnGetIdentifyCode)
    public void btnGetIdentifyCode(View view) {
        SMSSDK.registerEventHandler(eh); //注册短信回调
        SMSSDK.getVerificationCode("86", "13716107504");
    }

    @OnClick(R.id.register)
    public void btnRegister(View view) {
        register();
    }

    @Override
    public void register() {
//        PollingUtils.startPollingService(this, 5, HeartService.class, HeartService.ACTION);
        checkRegisterInfo();
    }

    @Override
    public void checkRegisterInfo() {
        identifyCode = mEtIdentifyCode.getText().toString();
        if (identifyCode == null || mEtIdentifyCode.getText().toString().equals("")) {
            Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            mPresenter.register(mEtUserName.getText().toString(), mEtUserPwd.getText().toString(), identifyCode);
        }
    }

    @Override
    public void registerResult(boolean isSucc, String msg) {
        Toast.makeText(this, isSucc + "   " + msg, Toast.LENGTH_LONG).show();
    }

    public void init() {
    }

    EventHandler eh = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            Looper.prepare();
            Toast.makeText(RegisterActivity.this, "" + result, Toast.LENGTH_SHORT).show();
            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成,判断data类型:http://wiki.mob.com/android-%E7%9F%AD%E4%BF%A1sdk%E6%93%8D%E4%BD%9C%E5%9B%9E%E8%B0%83/
                HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                String country = (String) phoneMap.get("country");
                String phone = (String) phoneMap.get("phone");
                Toast.makeText(RegisterActivity.this, country + "     " + phone, Toast.LENGTH_SHORT).show();
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    Toast.makeText(RegisterActivity.this, "提交验证码成功", Toast.LENGTH_SHORT).show();
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    Toast.makeText(RegisterActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                    Toast.makeText(RegisterActivity.this, "返回支持发送验证码的国家列表", Toast.LENGTH_SHORT).show();
                }
            } else {
                ((Throwable) data).printStackTrace();
            }
        }

        @Override
        public void onRegister() {
            super.onRegister();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }
}
