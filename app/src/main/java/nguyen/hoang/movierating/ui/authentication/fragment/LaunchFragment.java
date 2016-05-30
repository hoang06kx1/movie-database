package nguyen.hoang.movierating.ui.authentication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import nguyen.hoang.movierating.R;
import nguyen.hoang.movierating.util.Utils;

public class LaunchFragment extends Fragment implements View.OnClickListener {
    public static String TAG = "FragmentLaunch";
    private View mBtnSignIn;
    private View mBtnSignUp;

    public LaunchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_launch, container, false);
        ButterKnife.bind(this, v);
        mBtnSignIn = v.findViewById(R.id.btn_sign_in);
        mBtnSignUp = v.findViewById(R.id.btn_sign_up);
        mBtnSignIn.setOnClickListener(this);
        mBtnSignUp.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        if (view == mBtnSignIn) {
            SignInFragment signInFragment = new SignInFragment();
            Utils.replaceFragmentInAccountManagement(getActivity(), signInFragment, signInFragment.TAG, false);
        } else if (view == mBtnSignUp){
            SignUpFragment signUpFragment = new SignUpFragment();
            Utils.replaceFragmentInAccountManagement(getActivity(), signUpFragment, signUpFragment.TAG, false);
        }
    }
}
