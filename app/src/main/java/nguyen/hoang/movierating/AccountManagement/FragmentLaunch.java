package nguyen.hoang.movierating.AccountManagement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import nguyen.hoang.movierating.R;
import nguyen.hoang.movierating.Utils;

public class FragmentLaunch extends Fragment implements View.OnClickListener {
    @Bind(R.id.btn_sign_in) Button mBtnSignIn;
    @Bind(R.id.btn_sign_up) Button mBtnSignUp;

    public static String TAG = "FragmentLaunch";
    public FragmentLaunch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_launch, container, false);
        ButterKnife.bind(this, v);
        mBtnSignIn.setOnClickListener(this);
        mBtnSignUp.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        if (view == mBtnSignIn) {
            FragmentSignIn fragmentSignIn = new FragmentSignIn();
            Utils.replaceFragmentInAccountManagement(getActivity(), fragmentSignIn, fragmentSignIn.TAG, false);
        } else if (view == mBtnSignUp){
            FragmentSignUp fragmentSignUp = new FragmentSignUp();
            Utils.replaceFragmentInAccountManagement(getActivity(), fragmentSignUp, fragmentSignUp.TAG, false);
        }
    }
}
