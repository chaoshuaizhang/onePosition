package cn.shopin.oneposition.activities.welcome;

import javax.inject.Inject;

/**
 * Created by zcs on 2017/4/13.
 */

public class WelcomePresenter extends WelcomeContract.IWelcomePresenter {
    private WelcomeContract.IWelcomeView welcomeView;
    private WelcomeContract.IWelcomeModel welcomeModel;

    @Inject
    public WelcomePresenter(WelcomeContract.IWelcomeView iWelcomeView) {
        super(iWelcomeView);
        welcomeView = iWelcomeView;
        welcomeModel = new WelcomeModel();
    }
}
