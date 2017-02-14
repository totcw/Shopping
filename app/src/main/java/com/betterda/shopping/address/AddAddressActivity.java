package com.betterda.shopping.address;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.betterda.mylibrary.Utils.PermissionUtil;
import com.betterda.shopping.R;
import com.betterda.shopping.address.contract.AddAddressContract;
import com.betterda.shopping.address.presenter.AddAddressPresenterImpl;
import com.betterda.shopping.base.BaseActivity;
import com.betterda.shopping.utils.UiUtils;
import com.betterda.shopping.widget.NormalTopBar;
import com.zcw.togglebutton.ToggleButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kankan.wheel.widget.WheelDialog;

/**
 * 添加收货地址
 * Created by Administrator on 2016/12/14.
 */

public class AddAddressActivity extends BaseActivity<AddAddressContract.Presenter> implements AddAddressContract.View {

    private static final int REQUEST_PERMISSION_CODE_TAKE_PIC = 1;//请求权限的请求码
    private static final int REQUEST_PERMISSION_SEETING = 2;//去设置界面的请求码

    @BindView(R.id.topbar_addaddress)
    NormalTopBar mTopbarAddaddress;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_number)
    EditText mEtNumber;
    @BindView(R.id.relative_addaddress_choseconstanct)
    RelativeLayout mRelativeAddaddressChoseconstanct;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.relative_addaddress_province)
    RelativeLayout mRelativeAddaddressProvince;
    @BindView(R.id.et_address2)
    EditText mEtAddress2;
    @BindView(R.id.btn_address_moren)
    Button mBtnAddressMoren;
    @BindView(R.id.tbtn_addaddress)
    ToggleButton mToogleButton;

    private StringBuilder stringBuilder;

    @Override
    protected AddAddressContract.Presenter onLoadPresenter() {
        return new AddAddressPresenterImpl();
    }

    @Override
    public void initView() {
        super.initView();
        setContentView(R.layout.activity_addaddress);
    }

    @Override
    public void init() {
        super.init();
        mTopbarAddaddress.setTitle("添加收货地址");
    }

    @Override
    public void initListener() {
        super.initListener();
        mToogleButton.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                getPresenter().onToggle(on);
            }
        });
    }

    @OnClick({R.id.relative_addaddress_choseconstanct, R.id.relative_addaddress_province, R.id.btn_address_moren,R.id.bar_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_addaddress_choseconstanct://选择联系人
                requestContactPermission();
                break;
            case R.id.relative_addaddress_province://选择地址
                choseAddress();
                break;
            case R.id.btn_address_moren://保存
                getPresenter().save();
                break;
            case R.id.bar_back:
                back();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            queryContact(data);
        } else if (requestCode == REQUEST_PERMISSION_SEETING) {
            //如果是从设置界面回来就重新请求权限
            requestContactPermission();
        }
    }


    /**
     * 检测权限的回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionsResult(int requestCode, final String[] permissions, int[] grantResults) {

        if (requestCode == REQUEST_PERMISSION_CODE_TAKE_PIC) {
            if (PermissionUtil.verifyPermissions(grantResults)) {//有权限

                //请求权限成功
                startToContact();
            } else {
                //没有权限
                if (!PermissionUtil.shouldShowPermissions(this, permissions)) {//这个返回false 表示勾选了不再提示
                    UiUtils.showSnackBar(mEtName, "请去设置界面设置权限", "去设置", new UiUtils.showSnackBarListener() {
                        @Override
                        public void doSnack() {
                            startToSetting();
                        }
                    });
                } else {
                    //表示没有权限 ,但是没勾选不再提示
                    UiUtils.showSnackBar(mEtName, "请允许权限请求!");
                }
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * 请求联系人权限
     */
    private void requestContactPermission() {
        PermissionUtil.checkPermission(getmActivity(), mEtName, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_PERMISSION_CODE_TAKE_PIC, new PermissionUtil.permissionInterface() {
            @Override
            public void success() {
                //请求权限成功
                startToContact();
            }
        });
    }

    /**
     * 去联系人界面
     */
    private void startToContact() {

        Intent intent = new Intent(
                Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, 0);
    }

    /**
     * 去设置界面
     */
    private void startToSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_PERMISSION_SEETING);
    }

    /**
     * 获取联系信息,因为需要权限 所以做好try
     *
     * @param data
     */
    private void queryContact(Intent data) {
        Cursor cursor = null, phone = null;
        try {
            ContentResolver reContentResolverol = getContentResolver();
            Uri contactData = data.getData();
            cursor= reContentResolverol.query(contactData, null, null, null, null);
            if (cursor != null && !cursor.isClosed()) {
                if (cursor.moveToFirst()) {
                    String username = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    phone = reContentResolverol.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                            null,
                            null);
                    if (phone != null && !phone.isClosed()) {
                        while (phone.moveToNext()) {
                            String usernumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            if (!TextUtils.isEmpty(usernumber)) {
                                if (mEtName != null) {
                                    mEtName.setText(username);
                                }
                                if (mEtNumber != null) {
                                    mEtNumber.setText(usernumber);
                                }
                                break;
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {

        } finally {
            if (null != cursor&&!cursor.isClosed()) {
                cursor.close();
                cursor = null;
            }
            if (null != phone&&!phone.isClosed()) {
                phone.close();
                phone = null;
            }
        }
    }


    /**
     * 选择区域
     */
    private void choseAddress() {
        WheelDialog wheelDialog = new WheelDialog(getmActivity());
        wheelDialog.setOnAddressCListener(new WheelDialog.OnAddressCListener() {
            @Override
            public void onClick(String s, String s1, String s2) {
                stringBuilder = new StringBuilder();
                if (!TextUtils.isEmpty(s)) {
                    stringBuilder.append(s);

                }
                if (!TextUtils.isEmpty(s1)) {
                    stringBuilder.append(s1);

                }
                if (!TextUtils.isEmpty(s2)) {
                    stringBuilder.append(s2);

                }
                mTvAddress.setText(stringBuilder.toString());
            }
        });
        wheelDialog.show();
    }


    @Override
    public String getNameText() {

        return mEtName.getText().toString().trim();
    }

    @Override
    public String getNumberText() {
        return mEtNumber.getText().toString().trim();
    }

    @Override
    public String getAreaText() {
        return mTvAddress.getText().toString().trim();
    }

    @Override
    public String getaddressText() {
        return mEtAddress2.getText().toString().trim();
    }

    @Override
    public View getTvPwd() {
        return mTvAddress;
    }
}
