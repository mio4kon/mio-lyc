package mio.kon.lyc.framework;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import mio.kon.lyc.R;
import mio.kon.lyc.auth.AuthListener;
import mio.kon.lyc.auth.AuthListenerWrapper;
import mio.kon.lyc.auth.AuthUtils;
import mio.kon.sdk.util.LogUtils;

/**
 * Created by mio on 15-5-8.
 * 验证是否Auth认证,如果没有提示认证
 */
public class AuthedActivity extends LycBaseActivity {

    protected String TAG = "AuthedActivity";
    private  AuthListener.AuthCallBack authCallBack = new AuthListener.AuthCallBack () {

        @Override
        public void authSuccess() {
            Toast.makeText (AuthedActivity.this,"授权成功",Toast.LENGTH_SHORT).show ();
            //授权成功后读取数据
            initData ();
        }

        @Override
        public void authCancel() {
            Toast.makeText (AuthedActivity.this,"授权取消",Toast.LENGTH_SHORT).show ();
        }

        @Override
        public void authFailed(String code) {
            Toast.makeText (AuthedActivity.this,"授权失败",Toast.LENGTH_SHORT).show ();
        }
    };


    public boolean checkTokenValid() {
        boolean tokenValid = AuthUtils.checkToken (this);
        LogUtils.d (TAG, "token是否有效:" + tokenValid);
        return tokenValid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        if(actLayoutID!=-1){
            _setContentView (actLayoutID);
        }else if(actLayoutView !=null){
            _setContentView (actLayoutView);
        }
        initView ();
        if (!checkTokenValid ()) {
            showAuthDialog ();
        } else {    /** token未过期才读取数据 **/
            initData ();
        }
    }

    protected void initView() {
    }

    protected void initData() {

    }

    /**
     * 显示授权dialog
     */
    private void showAuthDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder (this);
        builder
                .setTitle (getString (R.string.auth_tip))
                .setMessage (getString (R.string.auth_message))
                .setPositiveButton (getString (R.string.accept), new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AuthUtils.auth (AuthedActivity.this, authCallBack);
                    }
                })
                .setNegativeButton (getString (R.string.deny), new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish ();
                    }
                }).show ();
    }
}
