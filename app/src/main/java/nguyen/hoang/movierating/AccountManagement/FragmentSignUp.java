package nguyen.hoang.movierating.AccountManagement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import nguyen.hoang.movierating.R;
import nguyen.hoang.movierating.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSignUp extends Fragment implements View.OnClickListener{
    @Bind(R.id.tv_sign_in) TextView mTvSignIn;

    public static String TAG = "FragmentSignUp";
    public FragmentSignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, v);
        mTvSignIn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        if (view == mTvSignIn) {
            FragmentSignIn fragmentSignIn = new FragmentSignIn();
            Utils.replaceFragmentInAccountManagement(getActivity(), fragmentSignIn, fragmentSignIn.TAG, false);
        }
    }
}
